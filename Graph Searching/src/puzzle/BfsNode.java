package puzzle;

public class BfsNode implements Comparable<BfsNode> {

    public final NumberPuzzle state;

    public int distance;

    public BfsNode predecessor;

    public BfsNode(NumberPuzzle state) {
        this.state = state;

        this.distance = Integer.MAX_VALUE;
        this.predecessor = null;
    }

    public int compareTo(BfsNode other) {
        return this.state.compareTo(other.state);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) return true;
        if (!(other instanceof BfsNode)) return false;
        BfsNode that = (BfsNode) other;
        return this.state.equals(that.state);
    }

    public int hashCode() {
        return this.state.hashCode();
    }
}
