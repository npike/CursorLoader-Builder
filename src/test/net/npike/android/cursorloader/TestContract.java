package net.npike.android.cursorloader;

import android.net.Uri;
import android.provider.BaseColumns;

public class TestContract {
	public static final class Test implements BaseColumns {

		public static final String COLUMN_NAME_FIRST_NAME = "first_name";
		public static final String COLUMN_NAME_LAST_NAME = "last_name";

		public static final Uri CONTENT_ID_URI_BASE = Uri.parse(TestContract.SCHEME + TestContract.AUTHORITY + "/"
				+ Test.TABLE_NAME + "/");

		public static final Uri CONTENT_URI = Uri.parse(TestContract.SCHEME + TestContract.AUTHORITY + "/"
				+ Test.TABLE_NAME);
		/**
		 * The default sort order for this table
		 */
		public static final String DEFAULT_SORT_ORDER = Test.COLUMN_NAME_LAST_NAME + " DESC";
		/**
		 * The table name offered by this provider
		 */
		public static final String TABLE_NAME = "test";
		/**
		 * 0-relative position of a video ID segment in the path part of a video
		 * ID URI
		 */
		public static final int Test_ID_PATH_POSITION = 1;

		/**
		 * This class cannot be instantiated
		 */
		private Test() {
		}
	}

	/**
	 * Base authority for this content provider
	 */
	public static final String AUTHORITY = "net.npike.android.cursorloader.test";
	/**
	 * The scheme part for this provider's URI
	 */
	private static final String SCHEME = "content://";

	/**
	 * This class cannot be instantiated
	 */
	private TestContract() {
	}
}
