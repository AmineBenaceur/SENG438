package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.*;

public class RangeTestIntersects {

	private Range exampleRange;
	
	@BeforeClass 
	public static void setUpBeforeClass() throws Exception { 
		
	}
	
	@Before
	public void setUp() throws Exception { 
		exampleRange = new Range(-1, 1);
	}
	
	@Test
	public void intersectsWithPositiveRangesNotIntersecting(){
		Range range = new Range(5, 10);
		Boolean intersects = range.intersects(20,40);
		assertFalse( intersects );
	}
	
	@Test
	public void intersectsWithNegativeRangesNotIntersecting(){
		Range range = new Range(-50, -20);
		Boolean intersects = range.intersects(-10,-5);
		assertFalse( intersects );
	}
	
	@Test
	public void intersectsWithNegativeRangesNotIntersectingInverseInput(){
		Range range = new Range(5, 10);
		Boolean intersects = range.intersects(40, 20);
		assertFalse( intersects );
	}
	
	@Test
	public void intersectsWithInputRight(){
		Range range = new Range(5, 10);
		Boolean intersects = range.intersects(7,20);
		assert( intersects );
	}
	
	@Test
	public void intersectsWithInputLeft(){
		Range range = new Range(7, 20);
		Boolean intersects = range.intersects(5,10);
		assert( intersects );
	}
	
	@Test
	public void intersectsWithInputInside(){
		Range range = new Range(5, 20);
		Boolean intersects = range.intersects(7, 15);
		assert( intersects );
	}
	
	@Test
	public void intersectsWithInputOutside(){
		Range range = new Range(7, 15);
		Boolean intersects = range.intersects(5, 20);
		assert( intersects );
	}

	
	public void tearDown() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception { 
		
	}

}
