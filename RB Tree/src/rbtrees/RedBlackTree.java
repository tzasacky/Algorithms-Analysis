package rbtrees;

public class RedBlackTree {

    private RedBlackNode root;

    public static RedBlackNode nil = new RedBlackNode(Integer.MIN_VALUE, Color.BLACK); // always black, represents a NIL node

    public RedBlackTree() {
        this.root = RedBlackTree.nil;
    }

    public RedBlackNode getRoot() {
        return this.root;
    }

    public RedBlackNode findNode(int key) {
        if (this.root == RedBlackTree.nil) {
            return null;
        }

        return this.root.findNode(key);
    }

    public RedBlackNode findMinimum() {
        if (this.root == RedBlackTree.nil) {
            return null;
        } else {
            return this.findMinimum(this.root);
        }
    }

    public RedBlackNode findMinimum(RedBlackNode node) {
        if (node == null) {
            return null;
        }

        while (node.hasLeftChild()) {
            node = node.getLeftChild();
        }

        return node;
    }
    
    public RedBlackNode findMaximum() {
        if (this.root == RedBlackTree.nil) {
            return null;
        } else {
            return this.findMaximum(this.root);
        }
    }

    public RedBlackNode findMaximum(RedBlackNode node) {
        if (node == null) {
            return null;
        }

        while (node.hasRightChild()) {
            node = node.getRightChild();
        }

        return node;
    }

    public void insertNode(RedBlackNode z) {
        RedBlackNode y = RedBlackTree.nil;
        RedBlackNode x = this.root;

        while (x != RedBlackTree.nil) {
            y = x;
            if (z.getKey() < x.getKey()) {
                x = x.getLeftChild();
            } else {
                x = x.getRightChild();
            }
        }

        z.setParent(y);

        if (y == RedBlackTree.nil) {
            this.root = z;
        } else if (z.getKey() < y.getKey()) {
            y.setLeftChild(z);
        } else {
            y.setRightChild(z);
        }

        z.setLeftChild(RedBlackTree.nil);
        z.setRightChild(RedBlackTree.nil);
        z.setColor(Color.RED);

        this.insertFixup(z);
    }

    public void deleteNode(RedBlackNode z) {
        RedBlackNode x;
        RedBlackNode y = z;
        Color yOrigColor = y.getColor();

        if (z.getLeftChild() == RedBlackTree.nil) {
            x = z.getRightChild();
            this.transplant(z, z.getRightChild());
        } else if (z.getRightChild() == RedBlackTree.nil) {
            x = z.getLeftChild();
            this.transplant(z, z.getLeftChild());
        } else {
            y = this.findMinimum(z.getRightChild());
            yOrigColor = y.getColor();
            x = y.getRightChild();

            if (y.getParent() == z) {
                x.setParent(y);
            } else {
                this.transplant(y, y.getRightChild());
                y.setRightChild(z.getRightChild());
                y.getRightChild().setParent(y);
            }

            this.transplant(z, y);
            y.setLeftChild(z.getLeftChild());
            y.getLeftChild().setParent(y);
            y.setColor(z.getColor());
        }

        if (yOrigColor == Color.BLACK) {
            this.deleteFixup(x);
        }
    }

    private void insertFixup(RedBlackNode z) {
        while (z.getParent().getColor() == Color.RED) {
            if (z.getParent() == z.getParent().getParent().getLeftChild()) {
                RedBlackNode y = z.getParent().getParent().getRightChild();
                if (y.getColor() == Color.RED) {                        // case 1
                    z.getParent().setColor(Color.BLACK);                // case 1
                    y.setColor(Color.BLACK);                            // case 1
                    z.getParent().getParent().setColor(Color.RED);      // case 1
                    z = z.getParent().getParent();                      // case 1
                } else {
                    if (z == z.getParent().getRightChild()) {       // case 2
                        z = z.getParent();                          // case 2
                        this.leftRotate(z);                         // case 2
                    }

                    z.getParent().setColor(Color.BLACK);                // case 3
                    z.getParent().getParent().setColor(Color.RED);      // case 3
                    this.rightRotate(z.getParent().getParent());        // case 3
                }
            } else {
                RedBlackNode y = z.getParent().getParent().getLeftChild();
                if (y.getColor() == Color.RED) {
                    z.getParent().setColor(Color.BLACK);
                    y.setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getLeftChild()) {
                        z = z.getParent();
                        this.rightRotate(z);
                    }

                    z.getParent().setColor(Color.BLACK);
                    z.getParent().getParent().setColor(Color.RED);
                    this.leftRotate(z.getParent().getParent());
                }
            }
        }
        this.root.setColor(Color.BLACK);
        
    }

    private void deleteFixup(RedBlackNode x) {
        while (x != this.root && x.getColor() == Color.BLACK) {
            if (x == x.getParent().getLeftChild()) {
                RedBlackNode w = x.getParent().getRightChild();

                if (w.getColor() == Color.RED) {            // case 1
                    w.setColor(Color.BLACK);                // case 1
                    x.getParent().setColor(Color.RED);      // case 1
                    this.leftRotate(x.getParent());         // case 1
                    w = x.getParent().getRightChild();      // case 1
                }

                if (w.getLeftChild().getColor() == Color.BLACK &&       // case 2
                    w.getRightChild().getColor() == Color.BLACK) {      // case 2
                    w.setColor(Color.RED);                              // case 2
                    x = x.getParent();                                  // case 2
                } else {                                                    // case 3
                    if (w.getRightChild().getColor() == Color.BLACK) {      // case 3
                        w.getLeftChild().setColor(Color.BLACK);             // case 3
                        w.setColor(Color.RED);                              // case 3
                        this.rightRotate(w);                                // case 3
                        w = x.getParent().getRightChild();                  // case 3
                    }

                    w.setColor(x.getParent().getColor());           // case 4
                    x.getParent().setColor(Color.BLACK);            // case 4
                    w.getRightChild().setColor(Color.BLACK);        // case 4
                    this.leftRotate(x.getParent());                 // case 4
                    x = this.root;                                  // case 4
                }
            } else {
                RedBlackNode w = x.getParent().getLeftChild();

                if (w.getColor() == Color.RED) {
                    w.setColor(Color.BLACK);
                    x.getParent().setColor(Color.RED);
                    this.rightRotate(x.getParent());
                    w = x.getParent().getLeftChild();
                }

                if (w.getRightChild().getColor() == Color.BLACK &&
                    w.getLeftChild().getColor() == Color.BLACK) {
                    w.setColor(Color.RED);
                    x = x.getParent();
                } else {
                    if (w.getLeftChild().getColor() == Color.BLACK) {
                        w.getRightChild().setColor(Color.BLACK);
                        w.setColor(Color.RED);
                        this.leftRotate(w);
                        w = x.getParent().getLeftChild();
                    }

                    w.setColor(x.getParent().getColor());
                    x.getParent().setColor(Color.BLACK);
                    w.getLeftChild().setColor(Color.BLACK);
                    this.rightRotate(x.getParent());
                    x = this.root;
                }
            }
        }

        x.setColor(Color.BLACK);
    }

    // Note: the tests will not hit all lines of this function
    private void leftRotate(RedBlackNode x) {
        RedBlackNode y = x.getRightChild();
        x.setRightChild(y.getLeftChild());

        if (y.getLeftChild() != RedBlackTree.nil) {
            y.getLeftChild().setParent(x);
        }

        y.setParent(x.getParent());

        if (x.getParent() == RedBlackTree.nil) {
            this.root = y;
        } else if (x == x.getParent().getLeftChild()) {
            x.getParent().setLeftChild(y);
        } else {
            x.getParent().setRightChild(y);
        }

        y.setLeftChild(x);
        x.setParent(y);
    }

    // Note: the tests will not hit all lines of this function
    private void rightRotate(RedBlackNode x) {
        RedBlackNode y = x.getLeftChild();
        x.setLeftChild(y.getRightChild());

        if (y.getRightChild() != RedBlackTree.nil) {
            y.getRightChild().setParent(x);
        }

        y.setParent(x.getParent());

        if (x.getParent() == RedBlackTree.nil) {
            this.root = y;
        } else if (x == x.getParent().getRightChild()) {
            x.getParent().setRightChild(y);
        } else {
            x.getParent().setLeftChild(y);
        }

        y.setRightChild(x);
        x.setParent(y);
    }

    private void transplant(RedBlackNode u, RedBlackNode v) {
        if (u.getParent() == RedBlackTree.nil) {
            this.root = v;
        } else if (u == u.getParent().getLeftChild()) {
            u.getParent().setLeftChild(v);
        } else {
            u.getParent().setRightChild(v);
        }

        v.setParent(u.getParent());
    }

    /**
     * This function can be assumed to be bug-free!
     * @return A boolean indicating whether the tree is a valid red-black tree
     */
    public boolean isValid() {
        // Check BST property: left key is <=, right key is >=
        if (!this.isValidBinarySearchTree(this.root)) {
            return false;
        }

        // Check property 1: every node is either red or black
        if (!this.areAllNodesColored(this.root)) {
            return false;
        }

        // Check property 2: the root is black
        if (this.root.getColor() != Color.BLACK) {
            return false;
        }

        // Check property 3: every leaf is black
        if (RedBlackTree.nil.getColor() != Color.BLACK || !this.areAllLeavesNil(this.root)) {
            return false;
        }

        // Check property 4: for every red node, both of its children are black
        if (!this.containsNoRedParentChildPairs(this.root)) {
            return false;
        }

        // Check property 5: valid black-heights
        if (!this.hasValidBlackHeights(this.root)) {
            return false;
        }

        return true;
    }

    private boolean isValidBinarySearchTree(RedBlackNode x) {
        if (x == RedBlackTree.nil) {
            return true;
        } else {
            if ((x.hasLeftChild() && x.getLeftChild().getKey() > x.getKey()) ||
                (x.hasRightChild() && x.getRightChild().getKey() < x.getKey())) {
                return false;
            }
            return this.isValidBinarySearchTree(x.getLeftChild()) &&
                   this.isValidBinarySearchTree(x.getRightChild());
        }
    }

    private boolean areAllNodesColored(RedBlackNode x) {
        if (x == RedBlackTree.nil) {
            return x.getColor() != null;
        } else {
            return this.areAllNodesColored(x.getLeftChild()) &&
                   this.areAllNodesColored(x.getRightChild());
        }
    }

    private boolean areAllLeavesNil(RedBlackNode x) {
        if (x == RedBlackTree.nil) {
            return true;
        } else {
            if (x.getLeftChild() == null || x.getRightChild() == null) {
                return false;
            } else {
                return this.areAllLeavesNil(x.getLeftChild()) &&
                       this.areAllLeavesNil(x.getRightChild());
            }
        }
    }

    private boolean containsNoRedParentChildPairs(RedBlackNode x) {
        if (x == RedBlackTree.nil) {
            return true;
        } else {
            if (x.getColor() == Color.RED && (x.getLeftChild().getColor() == Color.RED ||
                                              x.getRightChild().getColor() == Color.RED)) {
                return false;
            } else {
                return this.containsNoRedParentChildPairs(x.getLeftChild()) &&
                       this.containsNoRedParentChildPairs(x.getRightChild());
            }
        }
    }

    private boolean hasValidBlackHeights(RedBlackNode x) {
        return this.getBlackHeight(this.root) != Integer.MIN_VALUE;
    }

    private int getBlackHeight(RedBlackNode x) {
        if (x == RedBlackTree.nil) {
            return 0;
        } else {
            int leftBh = this.getBlackHeight(x.getLeftChild());
            int rightBh = this.getBlackHeight(x.getRightChild());
            if (leftBh == Integer.MIN_VALUE || rightBh == Integer.MIN_VALUE) {
                return Integer.MIN_VALUE; // invalid
            } else {
                if (x.getLeftChild().getColor() == Color.BLACK) {
                    leftBh++;
                }
                if (x.getRightChild().getColor() == Color.BLACK) {
                    rightBh++;
                }
                if (leftBh != rightBh) {
                    return Integer.MIN_VALUE;
                } else {
                    return leftBh;
                }
            }
        }
    }
}
