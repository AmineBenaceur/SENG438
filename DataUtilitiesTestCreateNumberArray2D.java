package org.jfree.data.test;

import static org.junit.Assert.*;

import java.security.InvalidParameterException;

import org.jfree.data.DataUtilities;
import org.jfree.data.Values2D;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.*;

public class DataUtilitiesTestCreateNumberArray2D {
	Mockery mockingContext;
	Values2D values;

	@BeforeClass 
	public static void setUpBeforeClass() throws Exception { 

	}

	@Before
	public void setUp() throws Exception { 

	}

	@Test
	public void createNumberArrayWithSinglePositiveValue(){
		double[][] input = {{15.6}};

		Number[][] output = DataUtilities.createNumberArray2D( input );
		assertEquals( 1, output.length);
		assertEquals( 1, output[0].length);
		assertEquals( input[0][0] , output[0][0].doubleValue(), 0.000001d );
	}

	@Test
	public void createNumberArrayWithSingleNegativeValue(){
		double[][] input = {{-12.2}};

		Number[][] output = DataUtilities.createNumberArray2D( input );
		assertEquals( 1, output.length);
		assertEquals( 1, output[0].length);
		assertEquals( input[0][0] , output[0][0].doubleValue(), 0.000001d );
	}

	@Test
	public void createNumberArrayWithManyRows(){
		double[][] input = new double[5][1];

		for (int i = 0; i < 5; i++) input[i][0] = Math.random();

		Number[][] output = DataUtilities.createNumberArray2D( input );
		assertEquals( 5, output.length);
		assertEquals( 1, output[0].length);
		
		for (int i = 0; i < 5; i++) 
			assertEquals( input[i][0] , output[i][0].doubleValue(), 0.000001d );
	}

	@Test
	public void createNumberArrayWithManyColumns(){
		double[][] input = new double[1][5];

		for (int j = 0; j < 5; j++) input[0][j] = Math.random();

		Number[][] output = DataUtilities.createNumberArray2D( input );
		
		assertEquals( 1, output.length);
		assertEquals( 5, output[0].length);
		
		for (int j = 0; j < 5; j++) 
			assertEquals( input[0][j] , output[0][j].doubleValue(), 0.000001d );
	}

	@Test
	public void createNumberArrayWithManyRowsAndColums() {
		double[][] input = new double[5][5];
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				input[i][j] = Math.random();

		Number[][] output = DataUtilities.createNumberArray2D( input );
		assertEquals( 5, output.length);
		assertEquals( 5, output[0].length);
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) 
				assertEquals( input[i][j] , output[i][j].doubleValue(), 0.000001d );
	}

	@Test
	public void createNumberArrayWithNullInput() {
		boolean thrown = false;
		try {
			DataUtilities.createNumberArray2D( new double[10][10] );
		} catch (IllegalArgumentException e) {
			thrown = true;
		}
		
		assert ( thrown );
	}

	@After
	public void tearDown() throws Exception {

	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception { 

	}

}
