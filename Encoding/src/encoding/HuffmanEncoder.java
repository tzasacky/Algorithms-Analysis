package encoding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class HuffmanEncoder extends Encoder {

    private HashMap<Character, String> encodingTable;

    private CharacterNode treeRoot;

    public HuffmanEncoder(HashMap<Character, Float> frequencies) {
        this.encodingTable = new HashMap<Character, String>();
        
        this.buildEncodingTable(frequencies);
    }

    /**
     * Encodes the provided string using the stored encoding table.
     */
    @Override
    public String encodeString(String s) {
    	String encodedMessage = "";
    	HashMap<Character, String> encoder = encodingTable;
    	for(char c : s.toCharArray()) {
        	encodedMessage += encoder.get(c);	
        }
        return encodedMessage;
    }

    /**
     * Decodes the provided string using the stored encoding tree.
     */
    @Override
    public String decodeString(String s) {
    	String decodedMessage = "";
    	CharacterNode node = this.treeRoot;
    	for(char c : s.toCharArray()) {
        	if(c == '0') {
        		node = node.getLeftChild();
        	}else { 
        		node = node.getRightChild();
        	}
        	if(node.isLeaf()){
        		decodedMessage += node.getChar();
        		node = this.treeRoot;
        	}
        }
    	return decodedMessage;
    }
    
    /**
     * Builds the encoding table using the Huffman algorithm to build
     * a binary tree corresponding to the per-character frequencies provided.
     * (Hint: call buildEncodingTableFromTree to kick off the recursion of actually
     * translating a tree into a table of character codes once you have built the tree.)
     * @param frequencies - a map from character to frequency (should sum to 1)
     */
    private void buildEncodingTable(HashMap<Character, Float> frequencies) {
    	this.treeRoot = buildHuffmanEncodingTree(frequencies);
    	buildEncodingTableFromTree(treeRoot, "");
    }

    /**
     * Builds the encoding table by traversing the encoding tree.  When a
     * leaf is reached, the current string s of 0s and 1s is stored in the
     * encoding table.  For an internal node, both the left (appending "0") to s
     * and right (appending "1") children are traversed.
     * @param node - the current node
     * @param s - the string of 0s and 1s so far to get to node
     */
    private void buildEncodingTableFromTree(CharacterNode node, String s) {
        if(node.isLeaf()){
        	this.encodingTable.put(node.getChar(), s);
        	return;       	
        }else {
        	buildEncodingTableFromTree(node.getLeftChild(), s + "0");
        	buildEncodingTableFromTree(node.getRightChild(), s + "1");
        }
    }
    /**
     * Builds the Huffman encoding tree. Intended to be used as a helper method for
     * the buildEncodingTable method. 
     * Returns root of resulting tree.
     * @param frequencies - the set of characters with their (intended) frequencies 
     * 						for the Huffman encoding scheme
     */
    private CharacterNode buildHuffmanEncodingTree(HashMap<Character, Float> frequencies){
    	int n = frequencies.size();
    	PriorityQueue<CharacterNode> q = new PriorityQueue<CharacterNode>();
    	//Queue works better when all characters already exist as nodes
    	for(char c: Encoder.characters.toCharArray()) {
    		if(frequencies.get(c) != null){
    			CharacterNode node = new CharacterNode(c, frequencies.get(c));
    			q.add(node);
    		}
    	}
    	//Builds encoding tree
    	for(int i = 1; i < n; i++) {
    		CharacterNode left = q.remove();
    		CharacterNode right = q.remove();
    		CharacterNode parent = new CharacterNode(left, right);
    		q.add(parent);
    	}
    	//This is safe because the queue will contain only the root after loop terminates
    	return q.remove();
    }
}
