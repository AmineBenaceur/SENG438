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

	//	=================== For constrain() =========================
	
	/*
	 * This test covers the case where value is less than the lower bound of the range
	 */
	@Test
	public void constrainForValueLessThanRange() {
		double value = 2;
		double result = exampleRange.constrain(value);
		assertEquals(5, result, 0);
	}
	
	/*
	 * This test covers the case where value is within the range
	 */
	@Test
	public void constrainForValueWithinRange() {
		double value = 7;
		double result = exampleRange.constrain(value);
		assertEquals(7, result, 0);
	}
	
	/*
	 * This test covers the case where value is greater than the upper bound of the range
	 */
	@Test
	public void constrainForValueGreaterThanRange() {
		double value = 12;
		double result = exampleRange.constrain(value);
		assertEquals(10, result, 0);
	}
//	=============================================================
	
//	=================== For combine() ===========================
	
	/*
	 * This test covers the case where range1 is entirely to the left of range2, eg.
	 * 		[	]	(	)
	 */
	@Test
	public void combineWithRange1LeftOfRange2(){
		Range range1 = new Range(5, 10);
		Range range2 = new Range(40, 60);
		Range result = Range.combine(range1, range2);
		assertEquals(5, result.getLowerBound(), 0);
		assertEquals(60, result.getUpperBound(), 0);
	}
	
	/*
	 * This test covers the case where range1 overlaps with only the left of range2, eg.
	 * 		[	(	]	)
	 */
	@Test
	public void combineWithRange1OverlapLeftOfRange2(){
		Range range1 = new Range(10, 30);
		Range range2 = new Range(20, 40);
		Range result = Range.combine(range1, range2);
		assertEquals(10, result.getLowerBound(), 0);
		assertEquals(40, result.getUpperBound(), 0);
	}
	
	/*
	 * This test covers the case where range1 is contained entirely in range2, eg.
	 * 		(	[	]	)
	 */
	@Test
	public void combineWithRange1ContainedInRange2(){
		Range range1 = new Range(20, 30);
		Range range2 = new Range(10, 40);
		Range result = Range.combine(range1, range2);
		assertEquals(10, result.getLowerBound(), 0);
		assertEquals(40, result.getUpperBound(), 0);
	}
	
	/*
	 * This test covers the case where range1 contains range2, eg.
	 * 		[	(	)	]
	 */
	@Test
	public void combineWithRange1ContainingRange2(){
		Range range1 = new Range(10, 40);
		Range range2 = new Range(20, 30);
		Range result = Range.combine(range1, range2);
		assertEquals(10, result.getLowerBound(), 0);
		assertEquals(40, result.getUpperBound(), 0);
	}
	
	/*
	 * This test covers the case where range1 overlaps with only the right of range2, eg.
	 * 		(	[	)	]
	 */
	@Test
	public void combineWithRange1OverlapRightOfRange2(){
		Range range1 = new Range(20, 40);
		Range range2 = new Range(10, 30);
		Range result = Range.combine(range1, range2);
		assertEquals(10, result.getLowerBound(), 0);
		assertEquals(40, result.getUpperBound(), 0);
	}

	/*
	 * This test covers the case where range1 is entirely to the right of range2, eg.
	 * 		(	)	[	]
	 */
	@Test
	public void combineWithRange1RightOfRange2(){
		Range range1 = new Range(75, 80);
		Range range2 = new Range(40, 60);
		Range result = Range.combine(range1, range2);
		assertEquals(40, result.getLowerBound(), 0);
		assertEquals(80, result.getUpperBound(), 0);
	}
	
	/*
	 * This test covers the case where range1 is null
	 */
	@Test
	public void combineWithNullRange1(){
		Range range1 = null;
		Range range2 = new Range(5, 10);
		Range result = Range.combine(range1, range2);
		assertEquals(5, result.getLowerBound(), 0);
		assertEquals(10, result.getUpperBound(), 0);
	}
	
	/*
	 * This test covers the case where range2 is null
	 */
	@Test
	public void combineWithNullRange2(){
		Range range1 = new Range(5, 10);
		Range range2 = null;
		Range result = Range.combine(range1, range2);
		assertEquals(5, result.getLowerBound(), 0);
		assertEquals(10, result.getUpperBound(), 0);
	}
	
	/*
	 * This test covers the case where both ranges are null
	 */
	@Test
	public void combineWithBothNullRange(){
		Range range1 = null;
		Range range2 = null;
		Range result = Range.combine(range1, range2);
		assertNull(result);
	}
	
	/*
	@Test
	public void specialTest() {
		Range r = new Range(10,20);
		Mockery mockingContext = new Mockery();
		final Range r2 = mockingContext.mock(Range.class);
		
		mockingContext.checking(new Expectations() {
			{
				one(r2).getLowerBound();
				will(returnValue(10));
				one(r2).getUpperBound();
				will(returnValue(20));
			}
		});
		System.out.println(r2.getLowerBound());
		System.out.println(r2.getUpperBound());
	}*/

//	=============================================================
	
	@Test
	public void containForValueLessThanRange() {
		Range range = new Range(5,10);
		double value = 2;
		boolean result = range.contains(value);
		assertFalse(result);
	}
	
	@Test
	public void containForValueWithinRange() {
		Range range = new Range(5,10);
		double value = 7;
		boolean result = range.contains(value);
		assertTrue(result);
	}
	
	@Test
	public void containForValueGreaterThanRange() {
		Range range = new Range(5,10);
		double value = 12;
		boolean result = range.contains(value);
		assertFalse(result);
	}
	

	
	@Test
	public void shiftLowerBoundCrossesZeroMovingRight() {
		Range range = new Range(-5,5);
		double delta = 6;
		Range result = Range.shift(range,delta);
		assertEquals(result.getUpperBound(), 11,0);
		assertEquals(result.getLowerBound(), 0,0);
	}
	
	@Test
	public void shiftLowerBoundCrossesZeroMovingLeft() {
		Range range = new Range(1,5);
		double delta = -3;
		Range result = Range.shift(range,delta);
		assertEquals(result.getUpperBound(), 2,0);
		assertEquals(result.getLowerBound(), 0,0);
	}
	
	@Test
	public void shiftUpperBoundCrossesZeroMovingLeft() {
		Range range = new Range(-5,5);
		double delta = -6;
		Range result = Range.shift(range,delta);
		assertEquals(result.getUpperBound(), 0,0);
		assertEquals(result.getLowerBound(), -11,0);
	}
	
	@Test
	public void shiftUpperBoundCrossesZeroMovingRight() {
		Range range = new Range(-5,-1);
		double delta = 3;
		Range result = Range.shift(range,delta);
		assertEquals(result.getUpperBound(), 0,0);
		assertEquals(result.getLowerBound(), -2,0);
	}
	
	@Test
	public void shiftNoBoundsCrossZero() {
		Range range = new Range(1,5);
		double delta = 3;
		Range result = Range.shift(range,delta);
		assertEquals(result.getUpperBound(), 8,0);
		assertEquals(result.getLowerBound(), 4,0);
	}
	
	public void tearDown() throws Exception {
		
	}
	
	@AfterClass
	public static void tearDownAfterClass() throws Exception { 
		
	}

}
