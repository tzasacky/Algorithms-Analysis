package encoding;

public class CharacterNode implements Comparable<CharacterNode> {

    // All nodes have a frequency
    private float freq;

    // If the node is a leaf, the character will be set (and the children null)
    private Character c;

    // If the node is internal, these will be set (and c null)
    private final CharacterNode leftChild;
    private final CharacterNode rightChild;

    private boolean isLeaf;

    public CharacterNode(char c, float freq) {
        this.c = c;
        this.freq = freq;

        this.isLeaf = true;

        this.leftChild = null;
        this.rightChild = null;
    }
    
    public CharacterNode(CharacterNode leftChild, CharacterNode rightChild) {
        this.leftChild = leftChild;
        this.rightChild = rightChild;

        this.freq = leftChild.getFreq() + rightChild.getFreq();

        this.isLeaf = false;

        this.c = null;
    }

    public char getChar() {
        return this.c;
    }

    public float getFreq() {
        return this.freq;
    }

    public CharacterNode getLeftChild() {
        return this.leftChild;
    }

    public CharacterNode getRightChild() {
        return this.rightChild;
    }

    public boolean isLeaf() {
        return this.isLeaf;
    }

    @Override
    public int compareTo(CharacterNode other) {
        return Float.compare(this.freq, other.getFreq());
    }
}
