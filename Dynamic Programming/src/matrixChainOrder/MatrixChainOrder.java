package matrixChainOrder;

import java.util.ArrayList;

public class MatrixChainOrder {

    /**
     * Given a split array and the start and end of a chain, generates
     * a string containing the parenthesized chain of matrices.
     * @param s - the split array resulting from the DP solution
     * @param i - the start of the chain
     * @param j - the end of the chain
     * @return a string representing the parenthesized matrix-chain multiplication
     */
    private static String buildOptimalParensString(int[][] s, int i, int j) {
        if (i == j) {
            // Base case: single matrix
            return String.format("A%d", i);
        } else {
            // Recursive case: chain; check s for the split position
            String leftString = buildOptimalParensString(s, i, s[i][j]);
            String rightString = buildOptimalParensString(s, s[i][j] + 1, j);
            return String.format("(%s%s)", leftString, rightString);
        }
    }

    /**
     * Given an array of matrix dimensions, computes the optimal fully parenthesized
     * matrix-chain multiplication to minimize the overall number of scalar
     * multiplications performed in the final multiplication.
     * @param dimensions - an array of n+1 dimensions for a matrix chain of n matrices
     * @return a solution representing the parenthesized matrix-chain multiplication
     * @throws Exception if the provided dimensions array is null or contains non-positive dimensions
     */
    public static MatrixChainOrderSolution calculateMatrixChainOrder(ArrayList<Integer> dimensions) throws Exception {
        // Validate the input array of matrix dimensions
        if (dimensions == null) {
            throw new Exception("Error: null dimensions array");
        }

        for (int dim: dimensions) {
            if (dim <= 0) {
                throw new Exception("Error: negative matrix dimension");
            }
        }
        
        if (dimensions.size() == 1) {
            throw new Exception("Error: missing dimension for single matrix");
        }

        // Check if no multiplications are necessary (no matrices or single matrix)
        if (dimensions.size() == 0) {
            return new MatrixChainOrderSolution("", 0);
        }

        if (dimensions.size() == 2) {
            return new MatrixChainOrderSolution("A1", 0);
        }

        // Prep the dynamic programming results tables
        int n = dimensions.size() - 1;
        int[][] m = new int[n+1][n+1];
        int[][] s = new int[n][n+1];
        for (int i = 0; i <= n; i++) {
            m[i][i] = 0;
        }

     // Consider each possible chain length len, starting with the smallest
        // (note that we skip a length of 2 because a matrix chain of length
        // 1 corresponds to m[i][i] for the start of the chain A_i)
        for (int len = 2; len <= n; len++) {

            // For a chain length of len, consider all possible starting
            // positions i of the chain
            for (int i = 1; i <= n - len + 1; i++) {

                // Compute the minimum number of scalar multiplications needed
                // to compute the matrix product of the chain starting at position
                // i and of length len
                int j = i + len - 1;
                m[i][j] = Integer.MAX_VALUE;

                // Consider all possible split positions k
                for (int k = i; k <= j - 1; k++) {
                    int leftMults = m[i][k];
                    int rightMults = m[k+1][j];
                    int combineMults = dimensions.get(i-1) * dimensions.get(k) * dimensions.get(j);
                    int subproblemVal = leftMults + rightMults + combineMults;

                    // If this split is better than what we've seen, update m and s
                    if (subproblemVal < m[i][j]) {
                        m[i][j] = subproblemVal;
                        s[i][j] = k;
                    }
                }
            }
        }

        // Generate the resulting order by recursively stepping through the s array
        String result = MatrixChainOrder.buildOptimalParensString(s, 1, n);

        // Return the resulting matrix-chain multiplication order
        int bestValue = m[1][n];
        return new MatrixChainOrderSolution(result, bestValue);

    }
}
