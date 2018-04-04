package puzzle;

import java.util.ArrayList;

public class NumberPuzzle implements Comparable<NumberPuzzle> {

    public static final int SIZE = 16;
    public static final int WIDTH = 4;

    private int[] state;
    
    /**
     * NumberPuzzle constructor - stores the provided array as the
     * internal state of the puzzle
     * @param data the state of the puzzle
     */
    public NumberPuzzle(int[] data) {
        if (data.length != NumberPuzzle.SIZE) {
            System.out.println("Invalid data size (" + data.length + "), should be: " + SIZE);
        }

        this.state = data;
    }

    /**
     * Returns a solved number puzzle.
     * @return a solved puzzle
     */
    public static NumberPuzzle getSolvedPuzzle() {
        int[] data = new int[NumberPuzzle.SIZE];
        for (int i = 0; i < NumberPuzzle.SIZE; i++) {
            data[i] = i;
        }
        return new NumberPuzzle(data);
    }

    /**
     * Gets the neighboring puzzle states based on all valid
     * single moves of the blank.
     * @return an ArrayList of neighboring puzzle states
     */
    public ArrayList<NumberPuzzle> getNeighbors() {
        ArrayList<NumberPuzzle> neighbors = new ArrayList<NumberPuzzle>();

        // Find the blank element (stored as 0)
        int blankIdx = 0;
        for (int i = 0; i < NumberPuzzle.SIZE; i++) {
            if (this.state[i] == 0) {
                blankIdx = i;
                break;
            }
        }

        // Try moving the blank left
        if (blankIdx % NumberPuzzle.WIDTH != 0) {
            int[] other = new int[NumberPuzzle.SIZE];
            System.arraycopy(this.state, 0, other, 0, NumberPuzzle.SIZE);
            other[blankIdx] = this.state[blankIdx - 1];
            other[blankIdx - 1] = 0;
            neighbors.add(new NumberPuzzle(other));
        }

        // Try moving the blank right
        if (blankIdx % NumberPuzzle.WIDTH != (NumberPuzzle.WIDTH - 1)) {
            int[] other = new int[NumberPuzzle.SIZE];
            System.arraycopy(this.state, 0, other, 0, NumberPuzzle.SIZE);
            other[blankIdx] = this.state[blankIdx + 1];
            other[blankIdx + 1] = 0;
            neighbors.add(new NumberPuzzle(other));
        }

        // Try moving the blank up
        if (blankIdx >= NumberPuzzle.WIDTH) {
            int[] other = new int[NumberPuzzle.SIZE];
            System.arraycopy(this.state, 0, other, 0, NumberPuzzle.SIZE);
            other[blankIdx] = this.state[blankIdx - NumberPuzzle.WIDTH];
            other[blankIdx - NumberPuzzle.WIDTH] = 0;
            neighbors.add(new NumberPuzzle(other));
        }

        // Try moving the blank down
        if (blankIdx < NumberPuzzle.SIZE - NumberPuzzle.WIDTH) {
            int[] other = new int[NumberPuzzle.SIZE];
            System.arraycopy(this.state, 0, other, 0, NumberPuzzle.SIZE);
            other[blankIdx] = this.state[blankIdx + NumberPuzzle.WIDTH];
            other[blankIdx + NumberPuzzle.WIDTH] = 0;
            neighbors.add(new NumberPuzzle(other));
        }

        return neighbors;
    }

    /**
     * Determines whether this puzzle is solvable using the
     * invariant from the theorem.
     * @return a boolean indicating whether the puzzle is solvable
     */
    public boolean isSolvable() {
    	// Find the blank element (stored as 0)
        int blankIdx = 0;
        for (int i = 0; i < NumberPuzzle.SIZE; i++) {
            if (this.state[i] == 0) {
                blankIdx = i;
                break;
            }
        }  
        int blankRow = (int) Math.floor((double)(blankIdx/this.WIDTH));
        
    	int blankRowParity = blankRow % 2;
    	int inversionParity = this.getInversionCount() % 2;
    	//Invariant: InversionCount() parity must match row parity of blank space for solvability
    	if(blankRowParity == inversionParity) {
    		return true;
    	}
        return false;
    }

    /**
     * Determines whether this puzzle is in a solved state.
     * @return a boolean indicating whether the puzzle is solved
     */
    public boolean isSolved() {
        for (int i = 0; i < NumberPuzzle.SIZE; i++) {
            if (this.state[i] != i) {
                return false;
            }
        }

         return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < NumberPuzzle.WIDTH; i++) {
            for (int j = 0; j < NumberPuzzle.WIDTH; j++) {
                sb.append(String.format("%2d", this.state[i*NumberPuzzle.WIDTH + j]).replace(" 0", "  "));

                if (j < NumberPuzzle.WIDTH - 1) {
                    sb.append(" ");
                }
            }

            if (i < NumberPuzzle.WIDTH - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    public int compareTo(NumberPuzzle other) {
        for (int i = 0; i < NumberPuzzle.SIZE; i++) {
            int res = Integer.compare(this.state[i], other.state[i]);
            if (res != 0) {
                return res;
            }
        }

        return 0;
    }

    public boolean equals(NumberPuzzle other) {
        for (int i = 0; i < NumberPuzzle.SIZE; i++) {
            if (this.state[i] != other.state[i]) {
                return false;
            }
        }

        return true;
    }

    public int hashCode() {
        int hashCode = 0;
        for (int i = 0; i < NumberPuzzle.SIZE; i++) {
            hashCode += (this.state[i] * i);
        }

        return hashCode;
    }

    /**
     * Calculates the number of inversions in this puzzle.
     * @return the inversion count in the puzzle's current state
     */
    private int getInversionCount() {
    	//Compare every inversion candidate to get inversion count (Theta(n^2))
    	//By def of inversion, only tiles earlier in order than tile in question can be inversions
        int count = 0;
    	for(int i = 0 ; i < NumberPuzzle.SIZE; i++) {
    		for(int j = NumberPuzzle.SIZE - 1; j > i; j--) {
    			if(this.state[j] != 0 && this.state[i] > this.state[j]) {
    				count++;
    			}
    		}
    	}
        return count;
    }
}
