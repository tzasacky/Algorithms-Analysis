package matrixChainOrder;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

public class MatrixChainOrderTests {
	/**
	 * Verifies that calculateMatrixChainOrder throws an exception if the
	 * provided dimensions array is null.
	 */
	@Test
	public void calculateMatrixChainOrderThrowsIfNullArray() {
		// Setup input
		ArrayList<Integer> input = null;

		// Setup expected result
		String errorMessage = "Error: null dimensions array";

		// Call the function, expecting failure
		try {
			MatrixChainOrder.calculateMatrixChainOrder(input);

			// The call should have thrown an exception
			Assert.fail("Failed to throw exception on null input");
		} catch (Exception e) {
			Assert.assertEquals("Should have thrown exception on null array", errorMessage, e.getMessage());
		}
	}

	// Checks that exception thrown if dimensions negative
	@Test
	public void checkMatrixChainOrderThrowsifNegDim() {
		// Setup input
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(10, 100, -5, 50));
		// Setup expected result
		String errorMessage = "Error: negative matrix dimension";
		// Call the function, expecting failure
		try {
			MatrixChainOrder.calculateMatrixChainOrder(input);
			// The call should have thrown an exception
			Assert.fail("Failed to throw exception on negative dimensioned input");
		} catch (Exception e) {
			Assert.assertEquals("Should have thrown exception on negative dimensions", errorMessage, e.getMessage());
		}
	}

	// Checks that exception thrown if dimensions zero
	@Test
	public void checkMatrixChainOrderThrowsifZeroDim() {
		// Setup input
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(10, 100, 0, 50));
		// Setup expected result
		String errorMessage = "Error: negative matrix dimension";
		// Call the function, expecting failure
		try {
			MatrixChainOrder.calculateMatrixChainOrder(input);
			// The call should have thrown an exception
			Assert.fail("Failed to throw exception on zero dimensioned input");
		} catch (Exception e) {
			Assert.assertEquals("Should have thrown exception on zero dimension", errorMessage, e.getMessage());
		}
	}

	// Checks that error id thrown if 1 dimensions is input
	@Test
	public void checkMatrixChainOrderThrowsOneDim() {
		// Setup input
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(10));
		// Setup expected result
		String errorMessage = "Error: missing dimension for single matrix";
		// Call the function, expecting failure
		try {
			MatrixChainOrder.calculateMatrixChainOrder(input);
			// The call should have thrown an exception
			Assert.fail("Failed to throw exception on one dimension input");
		} catch (Exception e) {
			Assert.assertEquals("Should have thrown exception on one dimension input", errorMessage, e.getMessage());
		}
	}
	
	// Checks that empty solution is returned if no dimensions are input
	@Test
	public void checkMatrixChainOrderNoDim() {
		// Setup input
		ArrayList<Integer> input = new ArrayList<Integer>();
		// Setup expected result
		MatrixChainOrderSolution expected = new MatrixChainOrderSolution("", 0);
		// Call the function, not expecting failure
		try {
			MatrixChainOrderSolution actualResult = MatrixChainOrder.calculateMatrixChainOrder(input);
			// Verify the result
			Assert.assertEquals("No dim test: strings", expected.parenthesizedChain, actualResult.parenthesizedChain);
			Assert.assertEquals("No dim test: values", expected.scalarMultiplications,
					actualResult.scalarMultiplications);
		} catch (Exception e) {
			Assert.fail(String.format("Should not have thrown an exception (%s)", e.getMessage()));
		}
	}

	// Checks that matrix is returned if 2 dimensions are input
	@Test
	public void checkMatrixChainOrderOneMatrix() {
		// Setup input
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(10, 100));
		// Setup expected result
		MatrixChainOrderSolution expected = new MatrixChainOrderSolution("A1", 0);
		// Call the function, not expecting failure
		try {
			MatrixChainOrderSolution actualResult = MatrixChainOrder.calculateMatrixChainOrder(input);
			// Verify the result
			Assert.assertEquals("One matrix test 1: strings", expected.parenthesizedChain,
					actualResult.parenthesizedChain);
			Assert.assertEquals("One matrix test 1: values", expected.scalarMultiplications,
					actualResult.scalarMultiplications);
		} catch (Exception e) {
			Assert.fail(String.format("Should not have thrown an exception (%s)", e.getMessage()));
		}
	}

	/**
	 * Verifies that calculateMatrixChainOrder returns the correct chain for the
	 * happy-path solution given in class.
	 */
	@Test
	public void calculateMatrixChainOrderHappyPath1() {
		// Setup input
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(10, 100, 5, 50));

		// Setup expected result
		MatrixChainOrderSolution expectedResult = new MatrixChainOrderSolution("((A1A2)A3)", 7500);

		// Call the function, expecting no failure
		try {
			MatrixChainOrderSolution actualResult = MatrixChainOrder.calculateMatrixChainOrder(input);

			// Verify the result
			Assert.assertEquals("Happy path test 1: strings", expectedResult.parenthesizedChain,
					actualResult.parenthesizedChain);
			Assert.assertEquals("Happy path test 1: values", expectedResult.scalarMultiplications,
					actualResult.scalarMultiplications);
		} catch (Exception e) {
			Assert.fail(String.format("Should not have thrown an exception (%s)", e.getMessage()));
		}
	}
	
	/**
	 * Verifies that calculateMatrixChainOrder returns the correct chain for the
	 * happy-path solution in the homework.
	 */
	@Test
	public void calculateMatrixChainOrderHappyPath2() {
		// Setup input
		ArrayList<Integer> input = new ArrayList<Integer>(Arrays.asList(5, 10, 3, 12, 5, 50, 6));

		// Setup expected result
		MatrixChainOrderSolution expectedResult = new MatrixChainOrderSolution("((A1A2)((A3A4)(A5A6)))", 2010);

		// Call the function, expecting no failure
		try {
			MatrixChainOrderSolution actualResult = MatrixChainOrder.calculateMatrixChainOrder(input);

			// Verify the result
			Assert.assertEquals("Happy path test 2: strings", expectedResult.parenthesizedChain,
					actualResult.parenthesizedChain);
			Assert.assertEquals("Happy path test 2: values", expectedResult.scalarMultiplications,
					actualResult.scalarMultiplications);
		} catch (Exception e) {
			Assert.fail(String.format("Should not have thrown an exception (%s)", e.getMessage()));
		}
	}

}
