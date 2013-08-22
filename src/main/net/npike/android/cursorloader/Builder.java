package net.npike.android.cursorloader;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import android.util.Log;

public class Builder {

	private static final String TAG = "Builder";
	private ArrayList<String> mProjection = new ArrayList<String>();
	private Context mContext;
	private Uri mUri;
	private Selection mSelection;
	private String mSortString;
	private boolean mDebug = false;

	public enum Sort {
		ASC, DESC, ASC_NOCASE, DESC_NOCASE
	}

	public Builder(Context context) {
		mContext = context;
		mDebug = false;
	}

	public Builder uri(Uri uri) {
		mUri = uri;

		return this;
	}

	public Builder select(Selection.Builder b) {
		return select(b.build());
	}
	
	public Builder select(Selection select) {
		mSelection = select;

		return this;
	}
	
	public Builder select(SelectPart.Builder b) {
		return select(b.build());
	}

	public Builder select(SelectPart select) {
		mSelection = new Selection.Builder().select(select).build();

		return this;
	}

	public Builder sort(String column, Sort sort) {
		String sortOrder = "";
		if (sort == Sort.ASC && sort == Sort.DESC) {
			sortOrder = sort.name();
		} else {
			if (sort == Sort.ASC_NOCASE) {
				sortOrder = "collate nocase ASC";
			} else if (sort == Sort.DESC_NOCASE) {
				sortOrder = "collate nocase DESC";
			}
		}
		mSortString = String.format(Locale.US, "%s %s", column, sortOrder);

		return this;
	}

	public Builder addProjectionColumn(String column) {
		mProjection.add(column);

		return this;
	}
	
	public Builder addProjectionColumn(String table, String column) {
		mProjection.add(String.format(Locale.US, "%s.%s", table, column));

		return this;
	}

	public Builder debug() {
		mDebug = true;

		return this;
	}

	public CursorLoader build() {
		String[] projection = null;
		if (mProjection != null) {
			projection = new String[mProjection.size()];
			projection = mProjection.toArray(projection);
		} else {
			throw new RuntimeException("Must specify at least one projection column.");
		}

		if (mUri == null) {
			throw new RuntimeException("Must specify a provider URI.");
		}

		if (mDebug) {
			Log.d(TAG, "Select: " + mSelection.getSelectStatement());
			for (int x = 0; x < mSelection.getSelectionArgs().length; x++) {
				Log.d(TAG, "selectionArg: "+mSelection.getSelectionArgs()[x]);
			}
		}

		return new CursorLoader(mContext, mUri, projection,
				mSelection == null ? null : mSelection.getSelectStatement(), mSelection == null ? null
						: mSelection.getSelectionArgs(), mSortString);
	}
}
