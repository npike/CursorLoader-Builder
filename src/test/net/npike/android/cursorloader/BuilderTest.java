package net.npike.android.cursorloader;

import junit.framework.Assert;

import org.junit.Test;

import android.support.v4.content.CursorLoader;

public class BuilderTest {
	private static final String DATA_FIRST_NAME_DENISE = "denise";
	private static final String DATA_LAST_NAME_DENISE = "n";

	@Test
	public void buildSimple() {
		
		Builder b = new Builder(null);
		b.uri(TestContract.Test.CONTENT_URI)
		.addProjectionColumn(TestContract.Test.COLUMN_NAME_FIRST_NAME)
		.select(new SelectPart.Builder().column(DATA_FIRST_NAME_DENISE).equals(DATA_FIRST_NAME_DENISE).build());
		
		CursorLoader loader = b.build();
		
		Assert.fail("Not implemented.");
	}
	
	
	@Test
	public void buildAnd() {
		
		Assert.fail("Not implemented.");
	}
	
	@Test
	public void buildOr() {
		
		Assert.fail("Not implemented.");
	}
	
	
	@Test
	public void buildAndOr() {
		
		Assert.fail("Not implemented.");
	}
	
	
	@Test
	public void buildSort() {
		
		Assert.fail("Not implemented.");
	}
	
	
	@Test
	public void buildManyProjections() {
		
		Assert.fail("Not implemented.");
	}

}
