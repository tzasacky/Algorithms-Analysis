package rbtrees;

public class RedBlackNode {

    private int key;

    private RedBlackNode left;
    private RedBlackNode right;

    private RedBlackNode parent;

    private Color color;

    public RedBlackNode(int key) {
        this.key = key;

        this.left = null;
        this.right = null;

        this.parent = null;

        this.color = null;
    }

    public RedBlackNode(int key, Color c) {
        this.key = key;

        this.left = null;
        this.right = null;

        this.parent = null;

        this.color = c;
    }

    public int getKey() {
        return this.key;
    }

    public Color getColor() {
        return this.color;
    }

    public void setColor(Color c) {
        this.color = c;
    }

    public RedBlackNode getLeftChild() {
        return this.left;
    }

    public RedBlackNode getRightChild() {
        return this.right;
    }

    public boolean hasLeftChild() {
        return this.left != RedBlackTree.nil;
    }

    public boolean hasRightChild() {
        return this.right != RedBlackTree.nil;
    }

    public RedBlackNode getParent() {
        return this.parent;
    }

    public void setLeftChild(RedBlackNode node) {
        this.left = node;
    }

    public void setRightChild(RedBlackNode node) {
        this.right = node;
    }

    public void setParent(RedBlackNode node) {
        this.parent = node;
    }

    public RedBlackNode findNode(int key) {
        if (this.key == key) {
            return this;
        } else if (key < this.key && this.hasLeftChild()) {
            return this.left.findNode(key);
        } else if (key > this.key && this.hasRightChild()) {
            return this.right.findNode(key);
        } else {
            return null;
        }
    }
}
