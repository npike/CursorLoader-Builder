package net.npike.android.cursorloader;

import static org.fest.assertions.api.Assertions.assertThat;
import junit.framework.Assert;

import net.npike.android.cursorloader.SelectPart.Builder;

import org.junit.Before;
import org.junit.Test;

public class SelectionTest {
	private static final String DATA_FIRST_NAME_DENISE = "denise";
	private static final String DATA_LAST_NAME_DENISE = "n";
	private SelectPart.Builder mSelectPart1;
	private SelectPart.Builder mSelectPart2;
	private Builder mSelectPart3;

	@Before
	public void setupSelectParts() {
		mSelectPart1 = new SelectPart.Builder();
		mSelectPart1.column(TestContract.Test.COLUMN_NAME_FIRST_NAME).equals(DATA_FIRST_NAME_DENISE);
		
		mSelectPart2 = new SelectPart.Builder();
		mSelectPart2.column(TestContract.Test.COLUMN_NAME_LAST_NAME).equals(DATA_LAST_NAME_DENISE);
		
		mSelectPart3 = new SelectPart.Builder();
		mSelectPart3.column(TestContract.Test.COLUMN_NAME_LAST_NAME).equals(DATA_FIRST_NAME_DENISE);
	}
	
	@Test
	public void buildSimple() { 
		String expectedSelection = TestContract.Test.COLUMN_NAME_FIRST_NAME + "=?";
		String[] expectedSelectionArgs = new String[] { DATA_FIRST_NAME_DENISE };
		
		Selection.Builder b = new Selection.Builder();
		b.select(mSelectPart1.build()); 
		
		assertThat(b.build().getSelectStatement()).isEqualTo(expectedSelection);
		assertThat(b.build().getSelectionArgs()).isEqualTo(expectedSelectionArgs);
		
	}
	
	@Test
	public void buildSimpleAnd() { 
		String expectedSelection = TestContract.Test.COLUMN_NAME_FIRST_NAME + "=? and "+TestContract.Test.COLUMN_NAME_LAST_NAME + "=?";
		String[] expectedSelectionArgs = new String[] { DATA_FIRST_NAME_DENISE,  DATA_LAST_NAME_DENISE};
		
		Selection.Builder b = new Selection.Builder();
		b.select(mSelectPart1.build()); 
		b.and(mSelectPart2.build());
		
		assertThat(b.build().getSelectStatement()).isEqualTo(expectedSelection);
		assertThat(b.build().getSelectionArgs()).isEqualTo(expectedSelectionArgs);
		
	}
	
	@Test
	public void buildSimpleOr() { 
		String expectedSelection = TestContract.Test.COLUMN_NAME_FIRST_NAME + "=? or "+TestContract.Test.COLUMN_NAME_LAST_NAME + "=?";
		String[] expectedSelectionArgs = new String[] { DATA_FIRST_NAME_DENISE,  DATA_LAST_NAME_DENISE};
		
		Selection.Builder b = new Selection.Builder();
		b.select(mSelectPart1.build()); 
		b.or(mSelectPart2.build());
		
		assertThat(b.build().getSelectStatement()).isEqualTo(expectedSelection);
		assertThat(b.build().getSelectionArgs()).isEqualTo(expectedSelectionArgs);	
	}
	
	@Test
	public void buildAndOr() { 
		String expectedSelection = TestContract.Test.COLUMN_NAME_FIRST_NAME + "=? and "+TestContract.Test.COLUMN_NAME_LAST_NAME + "=? or "+TestContract.Test.COLUMN_NAME_LAST_NAME + "=?";
		String[] expectedSelectionArgs = new String[] { DATA_FIRST_NAME_DENISE,  DATA_LAST_NAME_DENISE, DATA_FIRST_NAME_DENISE};
		
		Selection.Builder b = new Selection.Builder();
		b.select(mSelectPart1.build()); 
		b.and(mSelectPart2.build());
		b.or(mSelectPart3.build());
		
		assertThat(b.build().getSelectStatement()).isEqualTo(expectedSelection);
		assertThat(b.build().getSelectionArgs()).isEqualTo(expectedSelectionArgs);		
	}
}
