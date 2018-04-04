package fib;

public class Main {
    
    // Choice of whether to display debug messages
    static final boolean DEBUG = true;

    // Choices of available Fibonacci functions
    static final int SIMPLE_RECURSIVE = 1;
    static final int DP_MEMOIZED = 2;
    static final int DP_BOTTOM_UP = 3;

    // Function to use
//    static int functionChoice = SIMPLE_RECURSIVE;
//      static int functionChoice = DP_MEMOIZED;
    static int functionChoice = DP_BOTTOM_UP;
      
    /**
     * Main function, which runs the chosen function to compute
     * the nth Fibonacci number.
     * @param args
     */
    public static void main(String[] args) {
        // Which Fibonacci number to calculate
        int n = 47;
        // Get the start time
        long startNanos = System.nanoTime();

        // Find the optimal rod cuts
        long solution;
        switch (functionChoice) {
            case SIMPLE_RECURSIVE:
                solution = Fibonacci.fib(n);
                break;
            case DP_MEMOIZED:
                solution = Fibonacci.memoizedFib(n);
                break;
            case DP_BOTTOM_UP:
                solution = Fibonacci.bottomUpFib(n);
                break;
            default:
                System.out.println("Invalid function specified.");
                solution = 0;
                break;
        }

        // Get the end time and print out the duration
        long endNanos = System.nanoTime();
        long durationNanos = endNanos - startNanos;

        // Print the results
        if (DEBUG) {
            System.out.println("Fib(" + n + ") = " + solution + "\n");
        }

        System.out.println("The function took " + String.valueOf(durationNanos / 1000000.0) + " milliseconds.");
    }
}
