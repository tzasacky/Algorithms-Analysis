package fib;

public class Fibonacci {

    /**
     * Calculates the nth Fibonacci number using the simple recursive solution.
     * @param n - which Fibonacci number to generate
     */
    static long fib(int n) {
        if (n == 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return fib(n-1) + fib(n-2);
        }
    }

    /**
     * Calculates the nth Fibonacci number using memoization.
     * @param n - which Fibonacci number to generate
     */
    static long memoizedFib(int n){
        // fibonacci function using memoization!
    	if(n < 0){
    		System.out.println("Error: negative n not allowed in fibonacci");
    		return -1;
    	}else if(n==0){
    		return 0;
    	}else{
    		long[] memo = new long [n+1];
    		if(n == 1){
    			return 1;
    		}else{
    			memo[0] = 0;
    			memo[1] = 1;
    			long fibn = memo[n];
    			if (fibn == 0) {
    				//Here's the recursive call. I overloaded the method 
    				//because I needed to pass memo table in 
    				//Seems like cheating, but couldn't figure out how to
    				//do it without passing the memo array
    			    fibn = memoizedFib(n-1, memo) + memoizedFib(n-2, memo);
    			    memo[n] = fibn;
    			}
    			return fibn;
    		}
    	}
    }
    static long memoizedFib(int n, long[] memo){
    	if(n == 0){ return 0;}
    	if(n == 1){ return 1;}
    	long fibn = memo[n];
		if (fibn == 0) {
		    fibn = memoizedFib(n-1, memo) + memoizedFib(n-2, memo);
		    memo[n] = fibn;
		}
		return fibn;
    }
    /**
     * Calculates the nth Fibonacci number using an iterative bottom-up approach.
     * @param n - which Fibonacci number to generate
     */
    static long bottomUpFib(int n) {
        // fibonacci function iteratively, bottom-up!
    	long[] table = new long[n+1];
    	table[0] = 0;
    	table[1] = 1;
    	for(int i = 2; i <= n; i++){
    		table[i] = table[i-1] + table[i-2];
    	}
        return table[n];
    }
}
