package net.npike.android.cursorloader;

import java.util.ArrayList;

/**
 * Represents the selection and selection args portion of constructing a cursor
 * loader. A selection can contain multiple selectParts, i.e.: column =? and
 * column2 = ? or column3 =?
 * 
 * @author npike
 * 
 */
public interface Selection {

	public String getSelectStatement();

	public String[] getSelectionArgs();

	public class Builder {
		private ArrayList<String> mSelectionArgs;
		private StringBuilder mSelection;

		public Builder() {

		}
		
		public Builder select(SelectPart.Builder b) {
			return select(b.build());
		}

		public Builder select(SelectPart selectPart) {
			mSelectionArgs = new ArrayList<String>();
			mSelection = new StringBuilder();

			mSelectionArgs.add(selectPart.getSelectionArg());
			mSelection.append(selectPart.getSelection());

			return this;
		}
		
		public Builder and(SelectPart.Builder b) {
			return and(b.build());
		}

		public Builder and(SelectPart selectPart) {
			mSelectionArgs.add(selectPart.getSelectionArg());
			mSelection.append(" and " + selectPart.getSelection());

			return this;
		}
		
		public Builder or(SelectPart.Builder b) {
			return or(b.build());
		}

		public Builder or(SelectPart selectPart) {
			mSelectionArgs.add(selectPart.getSelectionArg());
			mSelection.append(" or " + selectPart.getSelection());

			return this;
		}

		public Selection build() {
			String[] selectArgs = new String[mSelectionArgs.size()];
			selectArgs = mSelectionArgs.toArray(selectArgs);
		    
			return new SelectionImpl(mSelection.toString(), 
					selectArgs);
		}

	}

	public final class SelectionImpl implements Selection {
		private String mSelectStatement = "";
		private String[] mSelectArgs = new String[0];

		public SelectionImpl(String selectStatement, String[] selectionArgs) {
			mSelectStatement = selectStatement;
			mSelectArgs = selectionArgs;
		}

		@Override
		public String getSelectStatement() {
			return mSelectStatement;
		}

		@Override
		public String[] getSelectionArgs() {
			return mSelectArgs;
		}
	}

}