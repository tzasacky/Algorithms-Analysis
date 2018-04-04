package puzzle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class Main {

    /**
     * Uses the BFS algorithm to find the shortest sequence of moves from the
     * provided puzzle state to a solved state.  Terminates when BFS finds
     * a solved state.
     * @param puzzle - the initial puzzle state
     * @return the sequence of puzzle states to the solved state, starting from the initial state
     */
    public static ArrayList<NumberPuzzle> getBestMoves(NumberPuzzle puzzle) {
        ArrayList<NumberPuzzle> bestMoves = new ArrayList<NumberPuzzle>();
        if(puzzle.isSolvable()){
        	
        	//Initialize BFS List, initial state node, and HashSet to test if moves already seen
        	BfsNode currNode = new BfsNode(puzzle);
        	currNode.distance = 0;
        	ArrayList<BfsNode> L = new ArrayList<BfsNode>();
        	HashSet<BfsNode> seenMoves = new HashSet<BfsNode>();
        	L.add(currNode);
        	
        	//BFS Until solved
        	while(!currNode.state.isSolved()){
        		currNode = L.remove(0);
        		ArrayList<NumberPuzzle> neighbors = currNode.state.getNeighbors();
        		for(int i = 0; i < neighbors.size(); i++){
        			BfsNode node = new BfsNode(neighbors.get(i));
        			if(!seenMoves.contains(node)){
        				node.predecessor = currNode;
        				node.distance = currNode.distance + 1;
        				L.add(node);
        				seenMoves.add(node);
        			}
        		}        		
        	}
        	
        	//Build answer by crawling through predecessors
        	//Make Theta(moveset-length) space tradeoff for faster ArrayList build
        	ArrayList<BfsNode> bestMovesReverse = new ArrayList<BfsNode>();
        	while(currNode.predecessor != null){
        		bestMovesReverse.add(currNode.predecessor);
        		currNode = currNode.predecessor;
        	}
        	//Now insert efficiently
        	for(int i = bestMovesReverse.size() -1; i >= 0; i--){
        		bestMoves.add(bestMovesReverse.get(i).state);
        	}
        }
	return bestMoves;
    }

    /**
     * Uses the BFS algorithm to find the shortest sequence of moves from the
     * provided puzzle state to a solved state.  Uses BFS simultaneously from
     * both the initial state and the solved state, and terminates when a puzzle
     * state appears in both visited lists.
     * @param puzzle - the initial puzzle state
     * @return the sequence of puzzle states to the solved state, starting from the initial state
     */
    public static ArrayList<NumberPuzzle> getBestMovesBidrectional(NumberPuzzle puzzle) {
        ArrayList<NumberPuzzle> bestMoves = new ArrayList<NumberPuzzle>();
        if(puzzle.isSolvable()){
        	//Forward BFS List, initial state node, and HashSet to test if moves already seen
        	BfsNode currNode = new BfsNode(puzzle);
        	ArrayList<BfsNode> L = new ArrayList<BfsNode>();
        	HashSet<BfsNode> seenMoves = new HashSet<BfsNode>();
        	L.add(currNode);
        	
        	//Backward BFS List, initial state node, and HashMap to test if moves already seen
        	//Switched to hashMap due to hashSet nastyness getting nodes (for building answer)
        	BfsNode currNode2 = new BfsNode(NumberPuzzle.getSolvedPuzzle());
        	ArrayList<BfsNode> L2 = new ArrayList<BfsNode>();
        	HashMap<Integer, BfsNode> seenMoves2 = new HashMap<Integer, BfsNode>();
        	L2.add(currNode2);
        	
        	//BFS Until solved
        	//Stop when forward search intersects backwards search
        	while(!seenMoves2.containsValue(currNode)){
        		//Forward
        		currNode = L.remove(0);
        		ArrayList<NumberPuzzle> neighbors = currNode.state.getNeighbors();
        		for(int i = 0; i < neighbors.size(); i++){
        			BfsNode node = new BfsNode(neighbors.get(i));
        			if(!seenMoves.contains(node)){
        				node.predecessor = currNode;
        				L.add(node);
        				seenMoves.add(node);
        			}
        		}
        		//Backward
        		currNode2 = L2.remove(0);
        		ArrayList<NumberPuzzle> neighbors2 = currNode2.state.getNeighbors();
        		for(int i = 0; i < neighbors2.size(); i++){
        			BfsNode node = new BfsNode(neighbors2.get(i));
        			if(!seenMoves2.containsValue(node)){
        				node.predecessor = currNode2;
        				L2.add(node);
        				seenMoves2.put(node.hashCode(), node);
        			}
        		}
        	}
        	
        	//Crawl forward search predecessors
        	//Make Theta(moveset-length) space tradeoff for faster ArrayList build
        	ArrayList<BfsNode> bestMovesReverse = new ArrayList<BfsNode>();
        	while(currNode.predecessor != null){
        		bestMovesReverse.add(currNode.predecessor);
        		currNode = currNode.predecessor;
        	}
        	//Now insert efficiently
        	for(int i = bestMovesReverse.size() -1; i >= 0; i--){
        		bestMoves.add(bestMovesReverse.get(i).state);
        	}

        	//Make currNode2 match currNode for predecessor crawl of backward search
        	currNode2 = seenMoves2.get(currNode.hashCode());
        	while(currNode2 != null && currNode2.predecessor != null){
        		bestMoves.add(currNode2.predecessor.state);
        		currNode2 = currNode2.predecessor;
        	}
        }
        return bestMoves;
    }

    public static void main(String[] args) {
//        List<Integer> dataList = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15); // solved
//        List<Integer> dataList = Arrays.asList(4, 7, 13, 0, 9, 2, 12, 10, 8, 6, 11, 5, 15, 14, 3, 1); // not solvable
//        List<Integer> dataList = Arrays.asList(4, 6, 7, 1, 8, 10, 3, 11, 9, 2, 5, 15, 12, 0, 13, 14); // 22 moves - super long
//        List<Integer> dataList = Arrays.asList(0, 4, 1, 2, 8, 13, 5, 3, 9, 14, 10, 6, 12, 15, 11, 7); // 20 moves - super long
//       List<Integer> dataList = Arrays.asList(5, 4, 1, 3, 10, 0, 2, 7, 9, 6, 11, 15, 8, 12, 13, 14); // 18 moves
//       List<Integer> dataList = Arrays.asList(4, 1, 3, 6, 8, 5, 2, 7, 0, 9, 11, 15, 12, 10, 13, 14); // 16 moves
//        List<Integer> dataList = Arrays.asList(1, 2, 3, 7, 5, 0, 11, 10, 4, 8, 9, 6, 12, 13, 14, 15); // 14 moves
//        List<Integer> dataList = Arrays.asList(5, 4, 2, 3, 8, 1, 6, 7, 0, 9, 13, 11, 12, 14, 10, 15); // 12 moves
//        List<Integer> dataList = Arrays.asList(1, 5, 2, 3, 8, 4, 7, 11, 9, 10, 0, 6, 12, 13, 14, 15); // 10 moves
//        List<Integer> dataList = Arrays.asList(4, 1, 2, 3, 5, 6, 10, 7, 0, 13, 9, 11, 8, 12, 14, 15); // 8 moves
//        List<Integer> dataList = Arrays.asList(4, 1, 2, 3, 5, 6, 10, 7, 8, 13, 9, 11, 12, 0, 14, 15); // 6 moves
//        List<Integer> dataList = Arrays.asList(1, 5, 2, 3, 4, 9, 6, 7, 0, 8, 10, 11, 12, 13, 14, 15); // 4 moves
        List<Integer> dataList = Arrays.asList(1, 5, 2, 3, 4, 0, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15); // 2 moves

        int[] data = new int[NumberPuzzle.SIZE];
        for (int i = 0; i < NumberPuzzle.SIZE; i++) {
            data[i] = dataList.get(i);
        }

        NumberPuzzle puzzle = new NumberPuzzle(data);

        System.out.println(puzzle);
        System.out.println("\nIs solvable: " + puzzle.isSolvable() + "\n");

        long startNanos = System.nanoTime();
//        ArrayList<NumberPuzzle> movesToSolution = getBestMoves(puzzle);
        ArrayList<NumberPuzzle> movesToSolution = getBestMovesBidrectional(puzzle);
        long endNanos = System.nanoTime();
        long duration = endNanos - startNanos;

        System.out.println("\nThe search took " + String.valueOf(duration / 1000000.0) + " milliseconds.");
       /*
        System.out.println("\nFound solution:");
        for (NumberPuzzle np : movesToSolution) {
            System.out.println("\n" + np);     
        }
        */
    }

    /**
     * Steps the provided puzzle by n random moves.
     * @param puzzle - the initial puzzle state
     * @param n - the number of random moves to perform
     */
    private static NumberPuzzle shuffle(NumberPuzzle puzzle, int n) {
        Random random = new Random();

        for (int i = 0; i < n; i++) {
            ArrayList<NumberPuzzle> neighbors = puzzle.getNeighbors();

            // Choose a random neighbor
            int idx = random.nextInt(neighbors.size());
            puzzle = neighbors.get(idx);
        }

        return puzzle;
    }
}
