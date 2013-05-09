package net.npike.android.cursorloader;

import net.npike.android.cursorloader.SelectPart;

import org.junit.Test;

import static org.fest.assertions.api.Assertions.*;

public class SelectPartTest {
	private static final String DATA_FIRST_NAME_DENISE = "denise";
	private static final String DATA_LAST_NAME_DENISE = "n";

	@Test
	public void buildSimple() {
		String expectedSelection = TestContract.Test.COLUMN_NAME_FIRST_NAME + "=?";

		SelectPart.Builder builder = new SelectPart.Builder();
		builder.column(TestContract.Test.COLUMN_NAME_FIRST_NAME).equals(DATA_FIRST_NAME_DENISE);

		SelectPart s = builder.build();

		assertThat(s.getSelection()).isEqualTo(expectedSelection);
		assertThat(s.getSelectionArg()).isEqualTo(DATA_FIRST_NAME_DENISE);
	}

	@Test
	public void dontRepeat() {

		String expectedSelection = TestContract.Test.COLUMN_NAME_LAST_NAME + "=?";

		SelectPart.Builder builder = new SelectPart.Builder();
		builder.column(TestContract.Test.COLUMN_NAME_FIRST_NAME).equals(DATA_FIRST_NAME_DENISE);
		
		//  should replace existing column definition
		builder.column(TestContract.Test.COLUMN_NAME_LAST_NAME).equals(DATA_LAST_NAME_DENISE);

		SelectPart s = builder.build();
		
		assertThat(s.getSelection()).isEqualTo(expectedSelection);
		assertThat(s.getSelectionArg()).isEqualTo(DATA_LAST_NAME_DENISE);

	}
}
