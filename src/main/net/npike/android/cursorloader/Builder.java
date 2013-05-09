package net.npike.android.cursorloader;

import java.util.ArrayList;
import java.util.Locale;

import android.content.Context;
import android.net.Uri;
import android.provider.BaseColumns;
import android.support.v4.content.CursorLoader;

public class Builder {

	private static final String ARG_LAT = null;
	private ArrayList<String> mProjection = new ArrayList<String>();
	private Context mContext;
	private Uri mUri;
	private Selection mSelection;
	private String mSortColumn;
	private Sort mSort;
	private String mSortString;

	public static void main(String[] args) {
		Context context = null;

		CursorLoader loader = new Builder(context)
				.addProjectionColumn(BaseColumns._ID)
				.select(new Selection.Builder().select(new SelectPart.Builder().column("id").equals("3").build())
						.build()).build();

		CursorLoader loader2 = new Builder(context).addProjectionColumn(BaseColumns._ID)
				.select(new SelectPart.Builder().column("id").equals("3").build()).build();

		CursorLoader loader3 = new Builder(context)
				.addProjectionColumn(BaseColumns._ID)
				.select(new Selection.Builder().select(new SelectPart.Builder().column("id").equals("3").build())
						.and(new SelectPart.Builder().column("name").equals("jon").build())

						.build()).build();

		CursorLoader loader4 = new Builder(context)
				.addProjectionColumn(BaseColumns._ID)
				.select(new Selection.Builder().select(new SelectPart.Builder().column("id").equals("3").build())
						.and(new SelectPart.Builder().column("name").equals("denise").build())
						.or(new SelectPart.Builder().column("last_name").equals("nicoletti").build()).build()).build();

		// addSelection(new Selection.Builder().column(ID).equals(5).and(new
		// Selection.Builder().column(name).equals("foo")).or

		// select(
		// new Selection.Builder().addSelect( new
		// Select.Builder().column(ID).equals(5) )
		// .and(new Select.Builder().column(ID).equals(5) )
		// .or(new Select.Builder().column(ID).equals(5) )
		// )

		// CursorLoader modelsForMakeCursorLoader = new Builder(getActivity())
		// .uri(ModelContract.Models.CONTENT_URI)
		// .addProjectionColumn(BaseColumns._ID)
		// .addProjectionColumn(ModelContract.Models.COLUMN_NAME_MAKE_NAME)
		// .addProjectionColumn(ModelContract.Models.COLUMN_NAME_MODEL)
		// .select(
		// new
		// SelectPart.Builder().column(ModelContract.Models.COLUMN_NAME_MAKE_NAME).equals("Jeep").build()
		// )
		// .sort(ModelContract.Models.COLUMN_NAME_MODEL, Sort.ASC)
		// .build();
	}

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
