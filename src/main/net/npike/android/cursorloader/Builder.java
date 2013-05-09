package net.npike.android.cursorloader;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

public class Builder {

	private ArrayList<String> mProjection = new ArrayList<String>();
	private Context mContext;
	private Uri mUri;
	private Selection mSelection;
	private String mSortString;

	public enum Sort {
		ASC, DESC
	}

	public Builder(Context context) {
		mContext = context;
	}

	public Builder uri(Uri uri) {
		mUri = uri;

		return this;
	}

	public Builder select(Selection select) {
		mSelection = select;

		return this;
	}

	public Builder select(SelectPart select) {
		mSelection = new Selection.Builder().select(select).build();

		return this;
	}

	public Builder sort(String column, Sort sort) {
		mSortString = String.format(Locale.US, "%s %s", column, sort.name());

		return this;
	}

	public Builder addProjectionColumn(String column) {
		mProjection.add(column);

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

		return new CursorLoader(mContext, mUri, projection,
				mSelection == null ? null : mSelection.getSelectStatement(), mSelection == null ? null
						: mSelection.getSelectionArgs(), mSortString);
	}
}
