package net.npike.android.cursorloader;

import java.util.Locale;

public interface SelectPart {

	public String getSelection();

	public String getSelectionArg();

	public class Builder {
		private String mColumn = ""; 
		private String mOperation = "";
		private String mSelectionArg = "";

		public Builder column(String column) {
			mColumn = column;

			return this;
		}
		
		public Builder column(String table, String column) {
			mColumn = String.format(Locale.US, "%s.%s", table, column);

			return this;
		}

		public Builder isEqualTo(String value) {
			mOperation = "=?";
			mSelectionArg = value;

			return this;
		}
		
		public Builder isEqualTo(int value) {
			return isEqualTo(String.valueOf(value));
		}
		
		public Builder isEqualTo(boolean value) {
			return isEqualTo(String.valueOf(value));
		}

		public SelectPart build() {
			StringBuilder select = new StringBuilder();
			select.append(mColumn).append(mOperation);
			
			return new SelectPartImpl(select.toString(), mSelectionArg);
		}
	}

	public static final class SelectPartImpl implements SelectPart {
		protected String mSelection = "";
		protected String mSelectionArg = "";

		public SelectPartImpl(String selection, String selectionArg) {
			mSelection = selection;
			mSelectionArg = selectionArg;
		}

		@Override
		public String getSelection() {
			return mSelection;
		}

		@Override
		public String getSelectionArg() {
			return mSelectionArg;
		}
	}

}
