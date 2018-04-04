package rbtrees;

import org.junit.Assert;
import org.junit.Test;

public class RedBlackTreeTests {

    @Test
    public void constructorSetsUpMembers() {
        // Set up input
        int nilKey = Integer.MIN_VALUE;

        // Call the constructor
        RedBlackTree tree = new RedBlackTree();

        // Verify the members are set correctly
        Assert.assertNotNull(RedBlackTree.nil);
        Assert.assertEquals(Color.BLACK, RedBlackTree.nil.getColor());
        Assert.assertEquals(nilKey, RedBlackTree.nil.getKey());

        Assert.assertEquals(RedBlackTree.nil, tree.getRoot());

        //Assert.assertTrue(tree.isValid());
    }

    @Test
    public void findNodeReturnsNullForNilRoot() {
        // Set up input
        int key = 2;
        RedBlackTree tree = new RedBlackTree();

        //Assert.assertTrue(tree.isValid());

        // Call the function
        RedBlackNode actualResult = tree.findNode(key);

        // Verify the output
        Assert.assertNull(actualResult);
    }

    @Test
    public void findNodeFindsNode() {
        // Set up input
        int key = 3;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode node = new RedBlackNode(key);
        tree.insertNode(node);

        //Assert.assertTrue(tree.isValid());

        // Set up expected output
        RedBlackNode expectedResult = node;

        // Call the function
        RedBlackNode actualResult = tree.findNode(key);

        // Verify the output
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findMinimumReturnsNullForNilRoot() {
        // Set up input
        RedBlackTree tree = new RedBlackTree();

        //Assert.assertTrue(tree.isValid());

        // Set up expected output
        RedBlackNode expectedResult = null;

        // Call the function
        RedBlackNode actualResult = tree.findMinimum();

        // Verify the output
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findMinimumFindsMinimumNode() {
        // Set up input
        int minKey = 5;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode n1 = new RedBlackNode(minKey);
        RedBlackNode n2 = new RedBlackNode(minKey + 1);
        RedBlackNode n3 = new RedBlackNode(minKey + 2);
        tree.insertNode(n1);
        tree.insertNode(n2);
        tree.insertNode(n3);

        //Assert.assertTrue(tree.isValid());

        // Set up expected output
        RedBlackNode expectedResult = n1;

        // Call the function
        RedBlackNode actualResult = tree.findMinimum();

        // Verify the output
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findMinimumNodeHandlesNullNode() {
        // Set up input
        RedBlackTree tree = new RedBlackTree();

        //Assert.assertTrue(tree.isValid());

        // Set up expected output
        RedBlackNode expectedResult = null;

        // Call the function
        RedBlackNode actualResult = tree.findMinimum(null);

        // Verify the output
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findMinimumNodeFindsMinimumNode() {
        // Set up input
        int minKey = 7;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode n1 = new RedBlackNode(minKey);
        RedBlackNode n2 = new RedBlackNode(minKey + 1);
        RedBlackNode n3 = new RedBlackNode(minKey + 2);
        tree.insertNode(n1);
        tree.insertNode(n2);
        tree.insertNode(n3);

        //Assert.assertTrue(tree.isValid());

        // Set up expected output
        RedBlackNode expectedResult = n3;

        // Call the function
        RedBlackNode actualResult = tree.findMinimum(n3);

        // Verify the output
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findMaximumReturnsNullForNilRoot() {
        // Set up input
        RedBlackTree tree = new RedBlackTree();

        //Assert.assertTrue(tree.isValid());

        // Set up expected output
        RedBlackNode expectedResult = null;

        // Call the function
        RedBlackNode actualResult = tree.findMaximum();

        // Verify the output
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findMaximumFindsMaximumNode() {
        // Set up input
        int maxKey = 9;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode n1 = new RedBlackNode(maxKey - 1);
        RedBlackNode n2 = new RedBlackNode(maxKey);
        tree.insertNode(n1);
        tree.insertNode(n2);

        //Assert.assertTrue(tree.isValid());

        // Set up expected output
        RedBlackNode expectedResult = n2;

        // Call the function
        RedBlackNode actualResult = tree.findMaximum();

        // Verify the output
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findMaximumNodeHandlesNullNode() {
        // Set up input
        RedBlackTree tree = new RedBlackTree();

        //Assert.assertTrue(tree.isValid());

        // Set up expected output
        RedBlackNode expectedResult = null;

        // Call the function
        RedBlackNode actualResult = tree.findMaximum(null);

        // Verify the output
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findMaximumNodeFindsMaximumNode() {
        // Set up input
        int maxKey = 11;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode n1 = new RedBlackNode(maxKey - 1);
        RedBlackNode n2 = new RedBlackNode(maxKey);
        tree.insertNode(n1);
        tree.insertNode(n2);

        //Assert.assertTrue(tree.isValid());

        // Set up expected output
        RedBlackNode expectedResult = n2;

        // Call the function
        RedBlackNode actualResult = tree.findMaximum(n2);

        // Verify the output
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void insertNodeInsertsIntoEmptyTree() {
        // Set up input
        int key = 114;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode node = new RedBlackNode(key);

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.insertNode(node);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Verify the node is the root of the tree, and has only NIL children
        Assert.assertEquals(node, tree.getRoot());
        Assert.assertEquals(RedBlackTree.nil, node.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, node.getRightChild());

        // Verify the node is colored black and has no parent
        Assert.assertEquals(Color.BLACK, node.getColor());
        Assert.assertEquals(RedBlackTree.nil, node.getParent());
    }

    @Test
    public void insertNodeInsertsRootsLeftChild() {
        // Set up input
        int key = 115;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode rootNode = new RedBlackNode(key + 1);
        tree.insertNode(rootNode);
        RedBlackNode childNode = new RedBlackNode(key);

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.insertNode(childNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Verify the root is unchanged
        Assert.assertEquals(rootNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, rootNode.getRightChild());

        // Verify that the child node is the root's left child, and is colored red
        Assert.assertEquals(childNode, tree.getRoot().getLeftChild());
        Assert.assertEquals(Color.RED, childNode.getColor());
        Assert.assertEquals(rootNode, childNode.getParent());

        // Verify that the child node has NIL for its children
        Assert.assertEquals(RedBlackTree.nil, childNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, childNode.getRightChild());
    }

    @Test
    public void insertNodeInsertsRootsRightChild() {
        // Set up input
        int key = 116;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode rootNode = new RedBlackNode(key - 1);
        tree.insertNode(rootNode);
        RedBlackNode childNode = new RedBlackNode(key);

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.insertNode(childNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Verify the root is unchanged
        Assert.assertEquals(rootNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, rootNode.getLeftChild());

        // Verify that the child node is the root's right child, and is colored red
        Assert.assertEquals(childNode, tree.getRoot().getRightChild());
        Assert.assertEquals(Color.RED, childNode.getColor());
        Assert.assertEquals(rootNode, childNode.getParent());

        // Verify that the child node has NIL for its children
        Assert.assertEquals(RedBlackTree.nil, childNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, childNode.getRightChild());
    }

    @Test
    public void insertNodeInsertsLeftTwice() {
        // Set up input
        int key = 117;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode rootNode = new RedBlackNode(key + 2);
        RedBlackNode childNode = new RedBlackNode(key + 1);
        tree.insertNode(rootNode);
        RedBlackNode newNode = new RedBlackNode(key);

        rootNode.setLeftChild(childNode);
        childNode.setParent(rootNode);
        childNode.setLeftChild(RedBlackTree.nil);
        childNode.setRightChild(RedBlackTree.nil);
        childNode.setColor(Color.RED);

        // Verify the tree beforehand
        Assert.assertEquals(rootNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, rootNode.getRightChild());

        Assert.assertEquals(childNode, tree.getRoot().getLeftChild());
        Assert.assertEquals(Color.RED, childNode.getColor());
        Assert.assertEquals(rootNode, childNode.getParent());

        Assert.assertEquals(RedBlackTree.nil, childNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, childNode.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.insertNode(newNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // The insert should have caused a right-rotate, resulting in
        // the original "child" being the new root, and the original
        // root being the right-child of the new root
        Assert.assertEquals(childNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getParent());
        Assert.assertEquals(rootNode, tree.getRoot().getRightChild());

        Assert.assertEquals(Color.RED, tree.getRoot().getRightChild().getColor());
        Assert.assertEquals(childNode, tree.getRoot().getRightChild().getParent());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getRightChild().getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getRightChild().getRightChild());

        // Verify that the new node is the new root's left child, and is colored red
        Assert.assertEquals(newNode, tree.getRoot().getLeftChild());
        Assert.assertEquals(Color.RED, newNode.getColor());
        Assert.assertEquals(childNode, newNode.getParent());

        // Verify that the new node has NIL for its children
        Assert.assertEquals(RedBlackTree.nil, newNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, newNode.getRightChild());
    }

    @Test
    public void insertNodeInsertsRightTwice() {
        // Set up input
        int key = 118;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode rootNode = new RedBlackNode(key - 2);
        RedBlackNode childNode = new RedBlackNode(key - 1);
        tree.insertNode(rootNode);
        RedBlackNode newNode = new RedBlackNode(key);

        rootNode.setRightChild(childNode);
        childNode.setParent(rootNode);
        childNode.setLeftChild(RedBlackTree.nil);
        childNode.setRightChild(RedBlackTree.nil);
        childNode.setColor(Color.RED);

        // Verify the tree beforehand
        Assert.assertEquals(rootNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, rootNode.getLeftChild());

        Assert.assertEquals(childNode, tree.getRoot().getRightChild());
        Assert.assertEquals(Color.RED, childNode.getColor());
        Assert.assertEquals(rootNode, childNode.getParent());

        Assert.assertEquals(RedBlackTree.nil, childNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, childNode.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.insertNode(newNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // The insert should have caused a left-rotate, resulting in
        // the original "child" being the new root, and the original
        // root being the left-child of the new root
        Assert.assertEquals(childNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getParent());
        Assert.assertEquals(rootNode, tree.getRoot().getLeftChild());

        Assert.assertEquals(Color.RED, tree.getRoot().getLeftChild().getColor());
        Assert.assertEquals(childNode, tree.getRoot().getLeftChild().getParent());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getLeftChild().getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getLeftChild().getRightChild());

        // Verify that the new node is the new root's right child, and is colored red
        Assert.assertEquals(newNode, tree.getRoot().getRightChild());
        Assert.assertEquals(Color.RED, newNode.getColor());
        Assert.assertEquals(childNode, newNode.getParent());

        // Verify that the new node has NIL for its children
        Assert.assertEquals(RedBlackTree.nil, newNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, newNode.getRightChild());
    }

    @Test
    public void insertNodeInsertsSecondChildAsRight() {
        // Set up input
        int key = 119;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode rootNode = new RedBlackNode(key - 1);
        RedBlackNode childNode = new RedBlackNode(key - 2);
        tree.insertNode(rootNode);
        RedBlackNode newNode = new RedBlackNode(key);

        rootNode.setLeftChild(childNode);
        childNode.setParent(rootNode);
        childNode.setLeftChild(RedBlackTree.nil);
        childNode.setRightChild(RedBlackTree.nil);
        childNode.setColor(Color.RED);

        // Verify the tree beforehand
        Assert.assertEquals(rootNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, rootNode.getRightChild());

        Assert.assertEquals(childNode, tree.getRoot().getLeftChild());
        Assert.assertEquals(Color.RED, childNode.getColor());
        Assert.assertEquals(rootNode, childNode.getParent());

        Assert.assertEquals(RedBlackTree.nil, childNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, childNode.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.insertNode(newNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // The insert should have simply put the new node as the right child
        // of the root, resulting in no change to the root or its left child
        Assert.assertEquals(rootNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getParent());
        Assert.assertEquals(childNode, tree.getRoot().getLeftChild());

        Assert.assertEquals(Color.RED, tree.getRoot().getLeftChild().getColor());
        Assert.assertEquals(rootNode, tree.getRoot().getLeftChild().getParent());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getLeftChild().getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getLeftChild().getRightChild());

        // Verify that the new node is the root's right child, and is colored red
        Assert.assertEquals(newNode, tree.getRoot().getRightChild());
        Assert.assertEquals(Color.RED, newNode.getColor());
        Assert.assertEquals(rootNode, newNode.getParent());

        // Verify that the new node has NIL for its children
        Assert.assertEquals(RedBlackTree.nil, newNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, newNode.getRightChild());
    }

    @Test
    public void insertNodeInsertsSecondChildAsLeft() {
        // Set up input
        int key = 120;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode rootNode = new RedBlackNode(key + 1);
        RedBlackNode childNode = new RedBlackNode(key + 2);
        tree.insertNode(rootNode);
        RedBlackNode newNode = new RedBlackNode(key);

        rootNode.setRightChild(childNode);
        childNode.setParent(rootNode);
        childNode.setLeftChild(RedBlackTree.nil);
        childNode.setRightChild(RedBlackTree.nil);
        childNode.setColor(Color.RED);

        // Verify the tree beforehand
        Assert.assertEquals(rootNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, rootNode.getLeftChild());

        Assert.assertEquals(childNode, tree.getRoot().getRightChild());
        Assert.assertEquals(Color.RED, childNode.getColor());
        Assert.assertEquals(rootNode, childNode.getParent());

        Assert.assertEquals(RedBlackTree.nil, childNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, childNode.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.insertNode(newNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // The insert should have simply put the new node as the left child
        // of the root, resulting in no change to the root or its right child
        Assert.assertEquals(rootNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getParent());
        Assert.assertEquals(childNode, tree.getRoot().getRightChild());

        Assert.assertEquals(Color.RED, tree.getRoot().getRightChild().getColor());
        Assert.assertEquals(rootNode, tree.getRoot().getRightChild().getParent());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getRightChild().getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getRightChild().getRightChild());

        // Verify that the new node is the root's left child, and is colored red
        Assert.assertEquals(newNode, tree.getRoot().getLeftChild());
        Assert.assertEquals(Color.RED, newNode.getColor());
        Assert.assertEquals(rootNode, newNode.getParent());

        // Verify that the new node has NIL for its children
        Assert.assertEquals(RedBlackTree.nil, newNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, newNode.getRightChild());
    }

    @Test
    public void insertNodePassesComplexTest1() {
        // Set up input, based on Figure 13.5(a) - z's uncle is red,
        // and z is a right child
        int key = 121;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode nodeA = new RedBlackNode(key - 1);
        RedBlackNode nodeC = new RedBlackNode(key + 2);
        RedBlackNode nodeD = new RedBlackNode(key + 3);
        tree.insertNode(nodeC);
        RedBlackNode newNode = new RedBlackNode(key); // node B

        nodeC.setLeftChild(nodeA);
        nodeC.setRightChild(nodeD);
        nodeA.setParent(nodeC);
        nodeD.setParent(nodeC);
        nodeA.setLeftChild(RedBlackTree.nil);
        nodeA.setRightChild(RedBlackTree.nil);
        nodeD.setLeftChild(RedBlackTree.nil);
        nodeD.setRightChild(RedBlackTree.nil);
        nodeA.setColor(Color.RED);
        nodeD.setColor(Color.RED);

        // Verify the tree beforehand
        Assert.assertEquals(nodeC, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(nodeA, nodeC.getLeftChild());
        Assert.assertEquals(nodeD, nodeC.getRightChild());

        Assert.assertEquals(Color.RED, nodeA.getColor());
        Assert.assertEquals(nodeC, nodeA.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());

        Assert.assertEquals(Color.RED, nodeD.getColor());
        Assert.assertEquals(nodeC, nodeD.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeD.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeD.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.insertNode(newNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // The new node (B) should be A's right child; all other positions
        // should be unchanged
        Assert.assertEquals(newNode, nodeA.getRightChild());
        Assert.assertEquals(nodeA, newNode.getParent());

        Assert.assertEquals(RedBlackTree.nil, newNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, newNode.getRightChild());

        Assert.assertEquals(nodeC, tree.getRoot());
        Assert.assertEquals(nodeA, nodeC.getLeftChild());
        Assert.assertEquals(nodeD, nodeC.getRightChild());
        Assert.assertEquals(nodeC, nodeA.getParent());
        Assert.assertEquals(nodeC, nodeD.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeD.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeD.getRightChild());

        // The root should still be black
        Assert.assertEquals(Color.BLACK, nodeC.getColor());

        // The new node should be red, but nodes A and D should be black now
        Assert.assertEquals(Color.RED, newNode.getColor());
        Assert.assertEquals(Color.BLACK, nodeA.getColor());
        Assert.assertEquals(Color.BLACK, nodeD.getColor());
    }

    @Test
    public void insertNodePassesComplexTest2() {
        // Set up input, based on Figure 13.5(b) - z's uncle is red,
        // and z is a left child
        int key = 122;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode nodeB = new RedBlackNode(key + 1);
        RedBlackNode nodeC = new RedBlackNode(key + 2);
        RedBlackNode nodeD = new RedBlackNode(key + 3);
        tree.insertNode(nodeC);
        RedBlackNode newNode = new RedBlackNode(key); // node A

        nodeC.setLeftChild(nodeB);
        nodeC.setRightChild(nodeD);
        nodeB.setParent(nodeC);
        nodeD.setParent(nodeC);
        nodeB.setLeftChild(RedBlackTree.nil);
        nodeB.setRightChild(RedBlackTree.nil);
        nodeD.setLeftChild(RedBlackTree.nil);
        nodeD.setRightChild(RedBlackTree.nil);
        nodeB.setColor(Color.RED);
        nodeD.setColor(Color.RED);

        // Verify the tree beforehand
        Assert.assertEquals(nodeC, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(nodeB, nodeC.getLeftChild());
        Assert.assertEquals(nodeD, nodeC.getRightChild());

        Assert.assertEquals(Color.RED, nodeB.getColor());
        Assert.assertEquals(nodeC, nodeB.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeB.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeB.getRightChild());

        Assert.assertEquals(Color.RED, nodeD.getColor());
        Assert.assertEquals(nodeC, nodeD.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeD.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeD.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.insertNode(newNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // The new node (A) should be B's left child; all other positions
        // should be unchanged
        Assert.assertEquals(newNode, nodeB.getLeftChild());
        Assert.assertEquals(nodeB, newNode.getParent());

        Assert.assertEquals(RedBlackTree.nil, newNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, newNode.getRightChild());

        Assert.assertEquals(nodeC, tree.getRoot());
        Assert.assertEquals(nodeB, nodeC.getLeftChild());
        Assert.assertEquals(nodeD, nodeC.getRightChild());
        Assert.assertEquals(nodeC, nodeB.getParent());
        Assert.assertEquals(nodeC, nodeD.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeB.getRightChild());
        Assert.assertEquals(RedBlackTree.nil, nodeD.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeD.getRightChild());

        // The root should still be black
        Assert.assertEquals(Color.BLACK, nodeC.getColor());

        // The new node should be red, but nodes B and D should be black now
        Assert.assertEquals(Color.RED, newNode.getColor());
        Assert.assertEquals(Color.BLACK, nodeB.getColor());
        Assert.assertEquals(Color.BLACK, nodeD.getColor());
    }

    @Test
    public void insertNodePassesComplexTest3() {
        // Set up input, based on mirror of Figure 13.5(a) - z's other uncle
        // is red, and z is a right child
        int key = 123;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode nodeA = new RedBlackNode(key - 3); // root's left child
        RedBlackNode nodeB = new RedBlackNode(key - 2); // root
        RedBlackNode nodeC = new RedBlackNode(key - 1); // root's right child, D's parent
        tree.insertNode(nodeB);
        RedBlackNode newNode = new RedBlackNode(key); // node D

        nodeB.setLeftChild(nodeA);
        nodeB.setRightChild(nodeC);
        nodeA.setParent(nodeB);
        nodeC.setParent(nodeB);
        nodeA.setLeftChild(RedBlackTree.nil);
        nodeA.setRightChild(RedBlackTree.nil);
        nodeC.setLeftChild(RedBlackTree.nil);
        nodeC.setRightChild(RedBlackTree.nil);
        nodeA.setColor(Color.RED);
        nodeC.setColor(Color.RED);

        // Verify the tree beforehand
        Assert.assertEquals(nodeB, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeC, nodeB.getRightChild());

        Assert.assertEquals(Color.RED, nodeA.getColor());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());

        Assert.assertEquals(Color.RED, nodeC.getColor());
        Assert.assertEquals(nodeB, nodeC.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.insertNode(newNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // The new node (D) should be C's right child; all other positions
        // should be unchanged
        Assert.assertEquals(newNode, nodeC.getRightChild());
        Assert.assertEquals(nodeC, newNode.getParent());

        Assert.assertEquals(RedBlackTree.nil, newNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, newNode.getRightChild());

        Assert.assertEquals(nodeB, tree.getRoot());
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeC, nodeB.getRightChild());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(nodeB, nodeC.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getLeftChild());

        // The root should still be black
        Assert.assertEquals(Color.BLACK, nodeB.getColor());

        // The new node should be red, but nodes A and C should be black now
        Assert.assertEquals(Color.RED, newNode.getColor());
        Assert.assertEquals(Color.BLACK, nodeA.getColor());
        Assert.assertEquals(Color.BLACK, nodeC.getColor());
    }

    @Test
    public void insertNodePassesComplexTest4() {
        // Set up input, based on mirror of Figure 13.5(b) - z's other uncle
        // is red, and z is a left child
        int key = 124;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode nodeA = new RedBlackNode(key - 2); // root's left child
        RedBlackNode nodeB = new RedBlackNode(key - 1); // root
        RedBlackNode nodeD = new RedBlackNode(key + 1); // root's right child, parent of C
        tree.insertNode(nodeB);
        RedBlackNode newNode = new RedBlackNode(key); // node C

        nodeB.setLeftChild(nodeA);
        nodeB.setRightChild(nodeD);
        nodeA.setParent(nodeB);
        nodeD.setParent(nodeB);
        nodeA.setLeftChild(RedBlackTree.nil);
        nodeA.setRightChild(RedBlackTree.nil);
        nodeD.setLeftChild(RedBlackTree.nil);
        nodeD.setRightChild(RedBlackTree.nil);
        nodeA.setColor(Color.RED);
        nodeD.setColor(Color.RED);

        // Verify the tree beforehand
        Assert.assertEquals(nodeB, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeD, nodeB.getRightChild());

        Assert.assertEquals(Color.RED, nodeA.getColor());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());

        Assert.assertEquals(Color.RED, nodeD.getColor());
        Assert.assertEquals(nodeB, nodeD.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeD.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeD.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.insertNode(newNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // The new node (C) should be D's left child; all other positions
        // should be unchanged
        Assert.assertEquals(newNode, nodeD.getLeftChild());
        Assert.assertEquals(nodeD, newNode.getParent());

        Assert.assertEquals(RedBlackTree.nil, newNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, newNode.getRightChild());

        Assert.assertEquals(nodeB, tree.getRoot());
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeD, nodeB.getRightChild());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(nodeB, nodeD.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());
        Assert.assertEquals(RedBlackTree.nil, nodeD.getRightChild());

        // The root should still be black
        Assert.assertEquals(Color.BLACK, nodeB.getColor());

        // The new node should be red, but nodes A and D should be black now
        Assert.assertEquals(Color.RED, newNode.getColor());
        Assert.assertEquals(Color.BLACK, nodeA.getColor());
        Assert.assertEquals(Color.BLACK, nodeD.getColor());
    }

    @Test
    public void insertNodePassesComplexTest5() {
        // Set up input, based on Figure 13.6(b) - z's uncle is black,
        // and z is a right child
        int key = 125;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode nodeA = new RedBlackNode(key - 1);
        RedBlackNode nodeC = new RedBlackNode(key + 2);
        tree.insertNode(nodeC);
        RedBlackNode newNode = new RedBlackNode(key); // node B

        nodeC.setLeftChild(nodeA);
        nodeC.setRightChild(RedBlackTree.nil);
        nodeA.setParent(nodeC);
        nodeA.setLeftChild(RedBlackTree.nil);
        nodeA.setRightChild(RedBlackTree.nil);
        nodeA.setColor(Color.RED);

        // Verify the tree beforehand
        Assert.assertEquals(nodeC, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(nodeA, nodeC.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getRightChild());

        Assert.assertEquals(Color.RED, nodeA.getColor());
        Assert.assertEquals(nodeC, nodeA.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.insertNode(newNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // The new node (B) should be the root, and A and C are its children
        Assert.assertEquals(newNode, tree.getRoot());
        Assert.assertEquals(RedBlackTree.nil, newNode.getParent());

        Assert.assertEquals(nodeA, newNode.getLeftChild());
        Assert.assertEquals(newNode, nodeA.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());

        Assert.assertEquals(nodeC, newNode.getRightChild());
        Assert.assertEquals(newNode, nodeC.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getRightChild());

        // The new node (B) should be black, and A and C should be red
        Assert.assertEquals(Color.BLACK, newNode.getColor());

        Assert.assertEquals(Color.RED, nodeA.getColor());
        Assert.assertEquals(Color.RED, nodeC.getColor());
    }

    @Test
    public void insertNodePassesComplexTest6() {
        // Set up input, based on mirror of Figure 13.6(b) - z's other uncle
        // is black, and z is a right child
        int key = 126;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode nodeA = new RedBlackNode(key - 1); // original root
        RedBlackNode nodeC = new RedBlackNode(key + 2); // original root's right child
        tree.insertNode(nodeA);
        RedBlackNode newNode = new RedBlackNode(key); // node B

        nodeA.setLeftChild(RedBlackTree.nil);
        nodeA.setRightChild(nodeC);
        nodeC.setParent(nodeA);
        nodeC.setLeftChild(RedBlackTree.nil);
        nodeC.setRightChild(RedBlackTree.nil);
        nodeC.setColor(Color.RED);

        // Verify the tree beforehand
        Assert.assertEquals(nodeA, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(nodeC, nodeA.getRightChild());

        Assert.assertEquals(Color.RED, nodeC.getColor());
        Assert.assertEquals(nodeA, nodeC.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.insertNode(newNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // The new node (B) should be the root, and A and C are its children
        Assert.assertEquals(newNode, tree.getRoot());
        Assert.assertEquals(RedBlackTree.nil, newNode.getParent());

        Assert.assertEquals(nodeA, newNode.getLeftChild());
        Assert.assertEquals(newNode, nodeA.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());

        Assert.assertEquals(nodeC, newNode.getRightChild());
        Assert.assertEquals(newNode, nodeC.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getRightChild());

        // The new node (B) should be black, and A and C should be red
        Assert.assertEquals(Color.BLACK, newNode.getColor());

        Assert.assertEquals(Color.RED, nodeA.getColor());
        Assert.assertEquals(Color.RED, nodeC.getColor());
    }

    @Test
    public void deleteNodeHandlesEmptyTree() {
        // Set up input
        int key = 227;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode node = new RedBlackNode(key);
        tree.insertNode(node);

        // Verify the tree beforehand
        Assert.assertEquals(node, tree.getRoot());
        Assert.assertEquals(Color.BLACK, node.getColor());
        Assert.assertEquals(RedBlackTree.nil, node.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, node.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.deleteNode(node);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Verify that the tree is empty
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot());
    }

    @Test
    public void deleteNodeDeletesRootsLeftChild() {
        // Set up input
        int key = 228;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode rootNode = new RedBlackNode(key + 1);
        tree.insertNode(rootNode);
        RedBlackNode leftChildNode = new RedBlackNode(key);
        RedBlackNode rightChildNode = new RedBlackNode(key + 2);

        rootNode.setLeftChild(leftChildNode);
        rootNode.setRightChild(rightChildNode);
        leftChildNode.setParent(rootNode);
        rightChildNode.setParent(rootNode);

        leftChildNode.setColor(Color.RED);
        leftChildNode.setLeftChild(RedBlackTree.nil);
        leftChildNode.setRightChild(RedBlackTree.nil);

        rightChildNode.setColor(Color.RED);
        rightChildNode.setLeftChild(RedBlackTree.nil);
        rightChildNode.setRightChild(RedBlackTree.nil);

        // Verify the tree beforehand
        Assert.assertEquals(rootNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(leftChildNode, rootNode.getLeftChild());
        Assert.assertEquals(rightChildNode, rootNode.getRightChild());

        Assert.assertEquals(Color.RED, leftChildNode.getColor());
        Assert.assertEquals(rootNode, leftChildNode.getParent());
        Assert.assertEquals(RedBlackTree.nil, leftChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, leftChildNode.getRightChild());

        Assert.assertEquals(Color.RED, rightChildNode.getColor());
        Assert.assertEquals(rootNode, rightChildNode.getParent());
        Assert.assertEquals(RedBlackTree.nil, rightChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, rightChildNode.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.deleteNode(leftChildNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Verify the root is unchanged, except that its left child is missing
        Assert.assertEquals(rootNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(rightChildNode, rootNode.getRightChild());

        Assert.assertEquals(RedBlackTree.nil, rootNode.getLeftChild());
    }

    @Test
    public void deleteNodeDeletesRootsRightChild() {
        // Set up input
        int key = 228;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode rootNode = new RedBlackNode(key - 1);
        tree.insertNode(rootNode);
        RedBlackNode leftChildNode = new RedBlackNode(key - 2);
        RedBlackNode rightChildNode = new RedBlackNode(key);

        rootNode.setLeftChild(leftChildNode);
        rootNode.setRightChild(rightChildNode);
        leftChildNode.setParent(rootNode);
        rightChildNode.setParent(rootNode);

        leftChildNode.setColor(Color.RED);
        leftChildNode.setLeftChild(RedBlackTree.nil);
        leftChildNode.setRightChild(RedBlackTree.nil);

        rightChildNode.setColor(Color.RED);
        rightChildNode.setLeftChild(RedBlackTree.nil);
        rightChildNode.setRightChild(RedBlackTree.nil);

        // Verify the tree beforehand
        Assert.assertEquals(rootNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(leftChildNode, rootNode.getLeftChild());
        Assert.assertEquals(rightChildNode, rootNode.getRightChild());

        Assert.assertEquals(Color.RED, leftChildNode.getColor());
        Assert.assertEquals(rootNode, leftChildNode.getParent());
        Assert.assertEquals(RedBlackTree.nil, leftChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, leftChildNode.getRightChild());

        Assert.assertEquals(Color.RED, rightChildNode.getColor());
        Assert.assertEquals(rootNode, rightChildNode.getParent());
        Assert.assertEquals(RedBlackTree.nil, rightChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, rightChildNode.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.deleteNode(rightChildNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Verify the root is unchanged, except that its right child is missing
        Assert.assertEquals(rootNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(leftChildNode, rootNode.getLeftChild());

        Assert.assertEquals(RedBlackTree.nil, rootNode.getRightChild());
    }

    @Test
    public void deleteNodeDeletesNodeWithLeftChild() {
        // Set up input
        int key = 229;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode rootNode = new RedBlackNode(key + 1);
        tree.insertNode(rootNode);
        RedBlackNode leftChildNode = new RedBlackNode(key);
        RedBlackNode rightChildNode = new RedBlackNode(key + 2);

        rootNode.setLeftChild(leftChildNode);
        rootNode.setRightChild(rightChildNode);
        leftChildNode.setParent(rootNode);
        rightChildNode.setParent(rootNode);

        leftChildNode.setColor(Color.BLACK);
        leftChildNode.setRightChild(RedBlackTree.nil);

        rightChildNode.setColor(Color.BLACK);
        rightChildNode.setLeftChild(RedBlackTree.nil);
        rightChildNode.setRightChild(RedBlackTree.nil);

        RedBlackNode grandChildNode = new RedBlackNode(key - 1); // left child of leftChildNode
        grandChildNode.setParent(leftChildNode);
        leftChildNode.setLeftChild(grandChildNode);
        grandChildNode.setLeftChild(RedBlackTree.nil);
        grandChildNode.setRightChild(RedBlackTree.nil);
        grandChildNode.setColor(Color.RED);

        // Verify the tree beforehand
        Assert.assertEquals(rootNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(leftChildNode, rootNode.getLeftChild());
        Assert.assertEquals(rightChildNode, rootNode.getRightChild());

        Assert.assertEquals(Color.BLACK, leftChildNode.getColor());
        Assert.assertEquals(rootNode, leftChildNode.getParent());
        Assert.assertEquals(grandChildNode, leftChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, leftChildNode.getRightChild());

        Assert.assertEquals(Color.BLACK, rightChildNode.getColor());
        Assert.assertEquals(rootNode, rightChildNode.getParent());
        Assert.assertEquals(RedBlackTree.nil, rightChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, rightChildNode.getRightChild());

        Assert.assertEquals(Color.RED, grandChildNode.getColor());
        Assert.assertEquals(leftChildNode, grandChildNode.getParent());
        Assert.assertEquals(RedBlackTree.nil, grandChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, grandChildNode.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.deleteNode(leftChildNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Verify the root and its right child are unchanged
        Assert.assertEquals(rootNode,  tree.getRoot());
        Assert.assertEquals(rootNode, rightChildNode.getParent());
        Assert.assertEquals(rightChildNode, rootNode.getRightChild());
        Assert.assertEquals(RedBlackTree.nil, rightChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, rightChildNode.getRightChild());

        Assert.assertEquals(Color.BLACK, rootNode.getColor());
        Assert.assertEquals(Color.BLACK, rightChildNode.getColor());

        // Verify the root's left child is its former grandchild
        Assert.assertEquals(rootNode, grandChildNode.getParent());
        Assert.assertEquals(grandChildNode, rootNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, grandChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, grandChildNode.getRightChild());

        Assert.assertEquals(Color.BLACK, grandChildNode.getColor());
    }

    @Test
    public void deleteNodeDeletesRootWithBothChildren() {
        // Set up input - else case in line 9, if in line 12
        int key = 230;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode rootNode = new RedBlackNode(key); // will delete the root
        tree.insertNode(rootNode);
        RedBlackNode leftChildNode = new RedBlackNode(key - 1);
        RedBlackNode rightChildNode = new RedBlackNode(key + 1);

        rootNode.setLeftChild(leftChildNode);
        rootNode.setRightChild(rightChildNode);
        leftChildNode.setParent(rootNode);
        rightChildNode.setParent(rootNode);

        leftChildNode.setColor(Color.RED);
        leftChildNode.setLeftChild(RedBlackTree.nil);
        leftChildNode.setRightChild(RedBlackTree.nil);

        rightChildNode.setColor(Color.RED);
        rightChildNode.setLeftChild(RedBlackTree.nil);
        rightChildNode.setRightChild(RedBlackTree.nil);

        // Verify the tree beforehand
        Assert.assertEquals(rootNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(leftChildNode, rootNode.getLeftChild());
        Assert.assertEquals(rightChildNode, rootNode.getRightChild());

        Assert.assertEquals(Color.RED, leftChildNode.getColor());
        Assert.assertEquals(rootNode, leftChildNode.getParent());
        Assert.assertEquals(RedBlackTree.nil, leftChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, leftChildNode.getRightChild());

        Assert.assertEquals(Color.RED, rightChildNode.getColor());
        Assert.assertEquals(rootNode, rightChildNode.getParent());
        Assert.assertEquals(RedBlackTree.nil, rightChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, rightChildNode.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.deleteNode(rootNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Verify the right child is the new root
        Assert.assertEquals(rightChildNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, rightChildNode.getColor());
        Assert.assertEquals(RedBlackTree.nil, rightChildNode.getParent());

        // Verify the new root's right child is empty, and the left child
        // is the original left child
        Assert.assertEquals(RedBlackTree.nil, rightChildNode.getRightChild());
        Assert.assertEquals(leftChildNode, rightChildNode.getLeftChild());
        
        Assert.assertEquals(rightChildNode, leftChildNode.getParent());
        Assert.assertEquals(RedBlackTree.nil, leftChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, leftChildNode.getRightChild());
        Assert.assertEquals(Color.RED, leftChildNode.getColor());
    }

    @Test
    public void deleteNodeDeletesRootWithGrandchildren() {
        // Set up input - else case in line 9, else in line 14
        int key = 231;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode rootNode = new RedBlackNode(key); // will delete the root
        tree.insertNode(rootNode);
        RedBlackNode leftChildNode = new RedBlackNode(key - 2);
        RedBlackNode leftGrandChildNode = new RedBlackNode(key - 1);
        RedBlackNode rightChildNode = new RedBlackNode(key + 2);
        RedBlackNode rightGrandChildNode = new RedBlackNode(key + 1);

        rootNode.setLeftChild(leftChildNode);
        rootNode.setRightChild(rightChildNode);
        leftChildNode.setParent(rootNode);
        rightChildNode.setParent(rootNode);

        leftChildNode.setColor(Color.BLACK);
        leftChildNode.setLeftChild(RedBlackTree.nil);
        leftChildNode.setRightChild(leftGrandChildNode);

        leftGrandChildNode.setColor(Color.RED);
        leftGrandChildNode.setLeftChild(RedBlackTree.nil);
        leftGrandChildNode.setRightChild(RedBlackTree.nil);
        leftGrandChildNode.setParent(leftChildNode);

        rightChildNode.setColor(Color.BLACK);
        rightChildNode.setLeftChild(rightGrandChildNode);
        rightChildNode.setRightChild(RedBlackTree.nil);

        rightGrandChildNode.setColor(Color.RED);
        rightGrandChildNode.setLeftChild(RedBlackTree.nil);
        rightGrandChildNode.setRightChild(RedBlackTree.nil);
        rightGrandChildNode.setParent(rightChildNode);

        // Verify the tree beforehand
        Assert.assertEquals(rootNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(leftChildNode, rootNode.getLeftChild());
        Assert.assertEquals(rightChildNode, rootNode.getRightChild());

        Assert.assertEquals(Color.BLACK, leftChildNode.getColor());
        Assert.assertEquals(rootNode, leftChildNode.getParent());
        Assert.assertEquals(RedBlackTree.nil, leftChildNode.getLeftChild());
        Assert.assertEquals(leftGrandChildNode, leftChildNode.getRightChild());

        Assert.assertEquals(Color.BLACK, rightChildNode.getColor());
        Assert.assertEquals(rootNode, rightChildNode.getParent());
        Assert.assertEquals(rightGrandChildNode, rightChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, rightChildNode.getRightChild());

        Assert.assertEquals(Color.RED, leftGrandChildNode.getColor());
        Assert.assertEquals(leftChildNode, leftGrandChildNode.getParent());
        Assert.assertEquals(RedBlackTree.nil, leftGrandChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, leftGrandChildNode.getRightChild());

        Assert.assertEquals(Color.RED, rightGrandChildNode.getColor());
        Assert.assertEquals(rightChildNode, rightGrandChildNode.getParent());
        Assert.assertEquals(RedBlackTree.nil, rightGrandChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, rightGrandChildNode.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.deleteNode(rootNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Verify the right grandchild is the new root
        Assert.assertEquals(rightGrandChildNode, tree.getRoot());
        Assert.assertEquals(Color.BLACK, rightGrandChildNode.getColor());
        Assert.assertEquals(RedBlackTree.nil, rightGrandChildNode.getParent());

        // Verify the new root's right child is the original right child, and
        // the left child is the original left child
        Assert.assertEquals(rightChildNode, rightGrandChildNode.getRightChild());
        Assert.assertEquals(leftChildNode, rightGrandChildNode.getLeftChild());

        Assert.assertEquals(rightGrandChildNode, leftChildNode.getParent());
        Assert.assertEquals(RedBlackTree.nil, leftChildNode.getLeftChild());
        Assert.assertEquals(leftGrandChildNode, leftChildNode.getRightChild());
        Assert.assertEquals(Color.BLACK, leftChildNode.getColor());

        Assert.assertEquals(leftChildNode, leftGrandChildNode.getParent());
        Assert.assertEquals(RedBlackTree.nil, leftGrandChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, leftGrandChildNode.getRightChild());
        Assert.assertEquals(Color.RED, leftGrandChildNode.getColor());

        Assert.assertEquals(rightGrandChildNode, rightChildNode.getParent());
        Assert.assertEquals(RedBlackTree.nil, rightChildNode.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, rightChildNode.getRightChild());
        Assert.assertEquals(Color.BLACK, rightChildNode.getColor());
    }

    @Test
    public void deleteNodePassesComplexTest1() {
        // Set up input, based on Figure 13.7(a) - x is z's left child,
        // and x's sibling w is red
        int key = 232;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode nodeAlpha = new RedBlackNode(key - 3);
        RedBlackNode oldNode = new RedBlackNode(key - 2); // z (x's original parent, deleted, also y)
        RedBlackNode nodeA = new RedBlackNode(key - 1); // x
        RedBlackNode nodeB = new RedBlackNode(key);
        RedBlackNode nodeGamma = new RedBlackNode(key + 1);
        RedBlackNode nodeC = new RedBlackNode(key + 2);
        RedBlackNode nodeDelta = new RedBlackNode(key + 3);
        RedBlackNode nodeD = new RedBlackNode(key + 4); // w (x's sibling)
        RedBlackNode nodeEpsilon = new RedBlackNode(key + 5);
        RedBlackNode nodeE = new RedBlackNode(key + 6);
        RedBlackNode nodeZeta = new RedBlackNode(key + 7);
        tree.insertNode(nodeB);

        nodeB.setLeftChild(oldNode);
        nodeB.setRightChild(nodeD);
        oldNode.setParent(nodeB);
        nodeD.setParent(nodeB);

        oldNode.setLeftChild(nodeAlpha);
        oldNode.setRightChild(nodeA);
        nodeD.setLeftChild(nodeC);
        nodeD.setRightChild(nodeE);
        oldNode.setColor(Color.BLACK);
        nodeD.setColor(Color.RED);

        nodeAlpha.setLeftChild(RedBlackTree.nil);
        nodeAlpha.setRightChild(RedBlackTree.nil);
        nodeAlpha.setColor(Color.BLACK);
        nodeAlpha.setParent(oldNode);
        nodeA.setLeftChild(RedBlackTree.nil);
        nodeA.setRightChild(RedBlackTree.nil);
        nodeA.setColor(Color.BLACK);
        nodeA.setParent(oldNode);

        nodeC.setLeftChild(nodeGamma);
        nodeC.setRightChild(nodeDelta);
        nodeC.setColor(Color.BLACK);
        nodeC.setParent(nodeD);
        nodeE.setLeftChild(nodeEpsilon);
        nodeE.setRightChild(nodeZeta);
        nodeE.setColor(Color.BLACK);
        nodeE.setParent(nodeD);

        nodeGamma.setLeftChild(RedBlackTree.nil);
        nodeGamma.setRightChild(RedBlackTree.nil);
        nodeGamma.setColor(Color.BLACK);
        nodeGamma.setParent(nodeC);
        nodeDelta.setLeftChild(RedBlackTree.nil);
        nodeDelta.setRightChild(RedBlackTree.nil);
        nodeDelta.setColor(Color.BLACK);
        nodeDelta.setParent(nodeC);

        nodeEpsilon.setLeftChild(RedBlackTree.nil);
        nodeEpsilon.setRightChild(RedBlackTree.nil);
        nodeEpsilon.setColor(Color.BLACK);
        nodeEpsilon.setParent(nodeE);
        nodeZeta.setLeftChild(RedBlackTree.nil);
        nodeZeta.setRightChild(RedBlackTree.nil);
        nodeZeta.setColor(Color.BLACK);
        nodeZeta.setParent(nodeE);

        // Verify the tree beforehand
        Assert.assertEquals(nodeB, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(oldNode, nodeB.getLeftChild());
        Assert.assertEquals(nodeD, nodeB.getRightChild());

        Assert.assertEquals(Color.BLACK, oldNode.getColor());
        Assert.assertEquals(nodeB, oldNode.getParent());
        Assert.assertEquals(nodeAlpha, oldNode.getLeftChild());
        Assert.assertEquals(nodeA, oldNode.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeA.getColor()); // red?
        Assert.assertEquals(oldNode, nodeA.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());

        Assert.assertEquals(Color.RED, nodeD.getColor());
        Assert.assertEquals(nodeB, nodeD.getParent());
        Assert.assertEquals(nodeC, nodeD.getLeftChild());
        Assert.assertEquals(nodeE, nodeD.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeC.getColor());
        Assert.assertEquals(nodeD, nodeC.getParent());
        Assert.assertEquals(nodeGamma, nodeC.getLeftChild());
        Assert.assertEquals(nodeDelta, nodeC.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeE.getColor());
        Assert.assertEquals(nodeD, nodeE.getParent());
        Assert.assertEquals(nodeEpsilon, nodeE.getLeftChild());
        Assert.assertEquals(nodeZeta, nodeE.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.deleteNode(oldNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Node D should now be the root
        Assert.assertEquals(nodeD, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getParent());

        // Node E should be the root's right child, and be black
        Assert.assertEquals(nodeE, nodeD.getRightChild());
        Assert.assertEquals(nodeD, nodeE.getParent());
        Assert.assertEquals(nodeEpsilon, nodeE.getLeftChild());
        Assert.assertEquals(nodeZeta, nodeE.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeE.getColor());

        // Node B should be the root's left child, and be black
        Assert.assertEquals(nodeB, nodeD.getLeftChild());
        Assert.assertEquals(nodeD, nodeB.getParent());
        Assert.assertEquals(Color.BLACK, nodeB.getColor());

        // Node B should have A and C as its children
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeC, nodeB.getRightChild());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(nodeB, nodeC.getParent());

        // Node A should be black
        Assert.assertEquals(Color.BLACK, nodeA.getColor());
        Assert.assertEquals(nodeAlpha, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());

        // By case 2, C should be red
        Assert.assertEquals(Color.RED, nodeC.getColor());
        Assert.assertEquals(nodeGamma, nodeC.getLeftChild());
        Assert.assertEquals(nodeDelta, nodeC.getRightChild());
    }

    @Test
    public void deleteNodePassesComplexTest2() {
        // Set up input, based on mirror of Figure 13.7(a) - x is z's right child,
        // and x's sibling w is red
        int key = 233;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode nodeAlpha = new RedBlackNode(key - 7);
        RedBlackNode nodeA = new RedBlackNode(key - 6);
        RedBlackNode nodeBeta = new RedBlackNode(key - 5);
        RedBlackNode nodeB = new RedBlackNode(key - 4); // w (x's sibling), red
        RedBlackNode nodeGamma = new RedBlackNode(key - 3);
        RedBlackNode nodeC = new RedBlackNode(key - 2);
        RedBlackNode nodeDelta = new RedBlackNode(key - 1);
        RedBlackNode nodeD = new RedBlackNode(key); // original root
        RedBlackNode nodeEpsilon = new RedBlackNode(key + 1);
        RedBlackNode oldNode = new RedBlackNode(key + 2); // z (x's original parent, deleted, also y)
        RedBlackNode nodeE = new RedBlackNode(key + 3); // x
        tree.insertNode(nodeD);

        nodeD.setLeftChild(nodeB);
        nodeD.setRightChild(oldNode);
        nodeB.setParent(nodeD);
        oldNode.setParent(nodeD);

        nodeB.setLeftChild(nodeA);
        nodeB.setRightChild(nodeC);
        oldNode.setLeftChild(nodeEpsilon);
        oldNode.setRightChild(nodeE);
        nodeB.setColor(Color.RED);
        oldNode.setColor(Color.BLACK);

        nodeA.setLeftChild(nodeAlpha);
        nodeA.setRightChild(nodeBeta);
        nodeA.setColor(Color.BLACK);
        nodeA.setParent(nodeB);
        nodeC.setLeftChild(nodeGamma);
        nodeC.setRightChild(nodeDelta);
        nodeC.setColor(Color.BLACK);
        nodeC.setParent(nodeB);

        nodeAlpha.setLeftChild(RedBlackTree.nil);
        nodeAlpha.setRightChild(RedBlackTree.nil);
        nodeAlpha.setColor(Color.BLACK);
        nodeAlpha.setParent(nodeA);
        nodeBeta.setLeftChild(RedBlackTree.nil);
        nodeBeta.setRightChild(RedBlackTree.nil);
        nodeBeta.setColor(Color.BLACK);
        nodeBeta.setParent(nodeA);

        nodeGamma.setLeftChild(RedBlackTree.nil);
        nodeGamma.setRightChild(RedBlackTree.nil);
        nodeGamma.setColor(Color.BLACK);
        nodeGamma.setParent(nodeC);
        nodeDelta.setLeftChild(RedBlackTree.nil);
        nodeDelta.setRightChild(RedBlackTree.nil);
        nodeDelta.setColor(Color.BLACK);
        nodeDelta.setParent(nodeC);

        nodeEpsilon.setLeftChild(RedBlackTree.nil);
        nodeEpsilon.setRightChild(RedBlackTree.nil);
        nodeEpsilon.setColor(Color.BLACK);
        nodeEpsilon.setParent(oldNode);
        nodeE.setLeftChild(RedBlackTree.nil);
        nodeE.setRightChild(RedBlackTree.nil);
        nodeE.setColor(Color.BLACK);
        nodeE.setParent(oldNode);

        // Verify the tree beforehand
        Assert.assertEquals(nodeD, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(nodeB, nodeD.getLeftChild());
        Assert.assertEquals(oldNode, nodeD.getRightChild());

        Assert.assertEquals(Color.RED, nodeB.getColor());
        Assert.assertEquals(nodeD, nodeB.getParent());
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeC, nodeB.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeA.getColor());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(nodeAlpha, nodeA.getLeftChild());
        Assert.assertEquals(nodeBeta, nodeA.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeC.getColor());
        Assert.assertEquals(nodeB, nodeC.getParent());
        Assert.assertEquals(nodeGamma, nodeC.getLeftChild());
        Assert.assertEquals(nodeDelta, nodeC.getRightChild());

        Assert.assertEquals(Color.BLACK, oldNode.getColor());
        Assert.assertEquals(nodeD, oldNode.getParent());
        Assert.assertEquals(nodeEpsilon, oldNode.getLeftChild());
        Assert.assertEquals(nodeE, oldNode.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeE.getColor());
        Assert.assertEquals(oldNode, nodeE.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.deleteNode(oldNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Node B should now be the root
        Assert.assertEquals(nodeB, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getParent());

        // Node A should be the root's left child, and be black
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(nodeAlpha, nodeA.getLeftChild());
        Assert.assertEquals(nodeBeta, nodeA.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeA.getColor());

        // Node D should be the root's right child, and be black
        Assert.assertEquals(nodeD, nodeB.getRightChild());
        Assert.assertEquals(nodeB, nodeD.getParent());
        Assert.assertEquals(Color.BLACK, nodeD.getColor());

        // Node D should have C and E as its children
        Assert.assertEquals(nodeC, nodeD.getLeftChild());
        Assert.assertEquals(nodeE, nodeD.getRightChild());
        Assert.assertEquals(nodeD, nodeC.getParent());
        Assert.assertEquals(nodeD, nodeE.getParent());

        // By case 2, C should be red
        Assert.assertEquals(Color.RED, nodeC.getColor());
        Assert.assertEquals(nodeGamma, nodeC.getLeftChild());
        Assert.assertEquals(nodeDelta, nodeC.getRightChild());

        // Node E should be black
        Assert.assertEquals(Color.BLACK, nodeE.getColor());
        Assert.assertEquals(nodeEpsilon, nodeE.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getRightChild());
    }

    @Test
    public void deleteNodePassesComplexTest3() {
        // Set up input, based on Figure 13.7(b) - z is a left child,
        // x's sibling after the deletion w is black, and both of w's
        // children are black
        int key = 234;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode nodeAlpha = new RedBlackNode(key - 3);
        RedBlackNode oldNode = new RedBlackNode(key - 2); // z (x's original parent, deleted, also y)
        RedBlackNode nodeA = new RedBlackNode(key - 1); // x
        RedBlackNode nodeB = new RedBlackNode(key);
        RedBlackNode nodeC = new RedBlackNode(key + 1);
        RedBlackNode nodeD = new RedBlackNode(key + 2); // w (x's sibling)
        RedBlackNode nodeE = new RedBlackNode(key + 3);
        tree.insertNode(nodeB);

        nodeB.setLeftChild(oldNode);
        nodeB.setRightChild(nodeD);
        oldNode.setParent(nodeB);
        nodeD.setParent(nodeB);

        oldNode.setLeftChild(nodeAlpha);
        oldNode.setRightChild(nodeA);
        nodeD.setLeftChild(nodeC);
        nodeD.setRightChild(nodeE);
        oldNode.setColor(Color.BLACK);
        nodeD.setColor(Color.BLACK);

        nodeAlpha.setLeftChild(RedBlackTree.nil);
        nodeAlpha.setRightChild(RedBlackTree.nil);
        nodeAlpha.setColor(Color.BLACK);
        nodeAlpha.setParent(oldNode);
        nodeA.setLeftChild(RedBlackTree.nil);
        nodeA.setRightChild(RedBlackTree.nil);
        nodeA.setColor(Color.BLACK);
        nodeA.setParent(oldNode);

        nodeC.setLeftChild(RedBlackTree.nil);
        nodeC.setRightChild(RedBlackTree.nil);
        nodeC.setColor(Color.BLACK);
        nodeC.setParent(nodeD);
        nodeE.setLeftChild(RedBlackTree.nil);
        nodeE.setRightChild(RedBlackTree.nil);
        nodeE.setColor(Color.BLACK);
        nodeE.setParent(nodeD);

        // Verify the tree beforehand
        Assert.assertEquals(nodeB, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(oldNode, nodeB.getLeftChild());
        Assert.assertEquals(nodeD, nodeB.getRightChild());

        Assert.assertEquals(Color.BLACK, oldNode.getColor());
        Assert.assertEquals(nodeB, oldNode.getParent());
        Assert.assertEquals(nodeAlpha, oldNode.getLeftChild());
        Assert.assertEquals(nodeA, oldNode.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeA.getColor());
        Assert.assertEquals(oldNode, nodeA.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeD.getColor());
        Assert.assertEquals(nodeB, nodeD.getParent());
        Assert.assertEquals(nodeC, nodeD.getLeftChild());
        Assert.assertEquals(nodeE, nodeD.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeC.getColor());
        Assert.assertEquals(nodeD, nodeC.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeE.getColor());
        Assert.assertEquals(nodeD, nodeE.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.deleteNode(oldNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Node B should now be the root
        Assert.assertEquals(nodeB, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getParent());

        // Node D should be the root's right child and be red
        Assert.assertEquals(nodeD, nodeB.getRightChild());
        Assert.assertEquals(nodeB, nodeD.getParent());
        Assert.assertEquals(Color.RED, nodeD.getColor());

        // Nodes C and E should be D's children, and should be black
        Assert.assertEquals(nodeC, nodeD.getLeftChild());
        Assert.assertEquals(nodeD, nodeC.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeC.getColor());
        
        Assert.assertEquals(nodeE, nodeD.getRightChild());
        Assert.assertEquals(nodeD, nodeE.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeE.getColor());

        // Node A should be the root's left child, and be black
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(nodeAlpha, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeA.getColor());
    }

    @Test
    public void deleteNodePassesComplexTest4() {
        // Set up input, based on mirror of Figure 13.7(b) - z is a right child,
        // x's sibling after the deletion w is black, and both of w's
        // children are black
        int key = 235;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode nodeA = new RedBlackNode(key - 3);
        RedBlackNode nodeB = new RedBlackNode(key - 2); // w (x's sibling), red
        RedBlackNode nodeC = new RedBlackNode(key - 1);
        RedBlackNode nodeD = new RedBlackNode(key); // original root
        RedBlackNode nodeEpsilon = new RedBlackNode(key + 1);
        RedBlackNode oldNode = new RedBlackNode(key + 2); // z (x's original parent, deleted, also y)
        RedBlackNode nodeE = new RedBlackNode(key + 3); // x
        tree.insertNode(nodeD);

        nodeD.setLeftChild(nodeB);
        nodeD.setRightChild(oldNode);
        nodeB.setParent(nodeD);
        oldNode.setParent(nodeD);

        nodeB.setLeftChild(nodeA);
        nodeB.setRightChild(nodeC);
        oldNode.setLeftChild(nodeEpsilon);
        oldNode.setRightChild(nodeE);
        nodeB.setColor(Color.BLACK);
        oldNode.setColor(Color.BLACK);

        nodeA.setLeftChild(RedBlackTree.nil);
        nodeA.setRightChild(RedBlackTree.nil);
        nodeA.setColor(Color.BLACK);
        nodeA.setParent(nodeB);
        nodeC.setLeftChild(RedBlackTree.nil);
        nodeC.setRightChild(RedBlackTree.nil);
        nodeC.setColor(Color.BLACK);
        nodeC.setParent(nodeB);

        nodeEpsilon.setLeftChild(RedBlackTree.nil);
        nodeEpsilon.setRightChild(RedBlackTree.nil);
        nodeEpsilon.setColor(Color.BLACK);
        nodeEpsilon.setParent(oldNode);
        nodeE.setLeftChild(RedBlackTree.nil);
        nodeE.setRightChild(RedBlackTree.nil);
        nodeE.setColor(Color.BLACK);
        nodeE.setParent(oldNode);

        // Verify the tree beforehand
        Assert.assertEquals(nodeD, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(nodeB, nodeD.getLeftChild());
        Assert.assertEquals(oldNode, nodeD.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeD.getColor());
        Assert.assertEquals(nodeD, nodeB.getParent());
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeC, nodeB.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeA.getColor());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeC.getColor());
        Assert.assertEquals(nodeB, nodeC.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getRightChild());

        Assert.assertEquals(Color.BLACK, oldNode.getColor());
        Assert.assertEquals(nodeD, oldNode.getParent());
        Assert.assertEquals(nodeEpsilon, oldNode.getLeftChild());
        Assert.assertEquals(nodeE, oldNode.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeE.getColor());
        Assert.assertEquals(oldNode, nodeE.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.deleteNode(oldNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Node D should now be the root
        Assert.assertEquals(nodeD, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getParent());

        // Node B should be the root's left child and be red
        Assert.assertEquals(nodeB, nodeD.getLeftChild());
        Assert.assertEquals(nodeD, nodeB.getParent());
        Assert.assertEquals(Color.RED, nodeB.getColor());

        // Nodes A and C should be B's children, and should be black
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeA.getColor());
        
        Assert.assertEquals(nodeC, nodeB.getRightChild());
        Assert.assertEquals(nodeB, nodeC.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeC.getColor());

        // Node E should be the root's right child, and be black
        Assert.assertEquals(nodeE, nodeD.getRightChild());
        Assert.assertEquals(nodeD, nodeE.getParent());
        Assert.assertEquals(nodeEpsilon, nodeE.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeE.getColor());
    }

    @Test
    public void deleteNodePassesComplexTest5() {
        // Set up input, based on Figure 13.7(c) - z is a left child,
        // x's sibling after the deletion w is black, and w's right
        // child is black
        int key = 236;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode nodeAlpha = new RedBlackNode(key - 3);
        RedBlackNode oldNode = new RedBlackNode(key - 2); // z (x's original parent, deleted, also y)
        RedBlackNode nodeA = new RedBlackNode(key - 1); // x
        RedBlackNode nodeB = new RedBlackNode(key);
        RedBlackNode nodeGamma = new RedBlackNode(key + 1);
        RedBlackNode nodeC = new RedBlackNode(key + 2);
        RedBlackNode nodeDelta = new RedBlackNode(key + 3);
        RedBlackNode nodeD = new RedBlackNode(key + 4); // w (x's sibling)
        RedBlackNode nodeE = new RedBlackNode(key + 5);
        tree.insertNode(nodeB);

        nodeB.setLeftChild(oldNode);
        nodeB.setRightChild(nodeD);
        oldNode.setParent(nodeB);
        nodeD.setParent(nodeB);

        oldNode.setLeftChild(nodeAlpha);
        oldNode.setRightChild(nodeA);
        nodeD.setLeftChild(nodeC);
        nodeD.setRightChild(nodeE);
        oldNode.setColor(Color.BLACK);
        nodeD.setColor(Color.BLACK);

        nodeAlpha.setLeftChild(RedBlackTree.nil);
        nodeAlpha.setRightChild(RedBlackTree.nil);
        nodeAlpha.setColor(Color.BLACK);
        nodeAlpha.setParent(oldNode);

        nodeA.setLeftChild(RedBlackTree.nil);
        nodeA.setRightChild(RedBlackTree.nil);
        nodeA.setColor(Color.BLACK);
        nodeA.setParent(oldNode);

        nodeC.setLeftChild(nodeGamma);
        nodeC.setRightChild(nodeDelta);
        nodeC.setColor(Color.RED);
        nodeC.setParent(nodeD);

        nodeGamma.setLeftChild(RedBlackTree.nil);
        nodeGamma.setRightChild(RedBlackTree.nil);
        nodeGamma.setColor(Color.BLACK);
        nodeGamma.setParent(nodeC);
        nodeDelta.setLeftChild(RedBlackTree.nil);
        nodeDelta.setRightChild(RedBlackTree.nil);
        nodeDelta.setColor(Color.BLACK);
        nodeDelta.setParent(nodeC);

        nodeE.setLeftChild(RedBlackTree.nil);
        nodeE.setRightChild(RedBlackTree.nil);
        nodeE.setColor(Color.BLACK);
        nodeE.setParent(nodeD);

        // Verify the tree beforehand
        Assert.assertEquals(nodeB, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(oldNode, nodeB.getLeftChild());
        Assert.assertEquals(nodeD, nodeB.getRightChild());

        Assert.assertEquals(Color.BLACK, oldNode.getColor());
        Assert.assertEquals(nodeB, oldNode.getParent());
        Assert.assertEquals(nodeAlpha, oldNode.getLeftChild());
        Assert.assertEquals(nodeA, oldNode.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeA.getColor());
        Assert.assertEquals(oldNode, nodeA.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeD.getColor());
        Assert.assertEquals(nodeB, nodeD.getParent());
        Assert.assertEquals(nodeC, nodeD.getLeftChild());
        Assert.assertEquals(nodeE, nodeD.getRightChild());

        Assert.assertEquals(Color.RED, nodeC.getColor());
        Assert.assertEquals(nodeD, nodeC.getParent());
        Assert.assertEquals(nodeGamma, nodeC.getLeftChild());
        Assert.assertEquals(nodeDelta, nodeC.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeE.getColor());
        Assert.assertEquals(nodeD, nodeE.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.deleteNode(oldNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Node C should now be the root
        Assert.assertEquals(nodeC, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getParent());

        // Node B should be the root's left child and be black
        Assert.assertEquals(nodeB, nodeC.getLeftChild());
        Assert.assertEquals(nodeC, nodeB.getParent());
        Assert.assertEquals(Color.BLACK, nodeB.getColor());

        // Node B should have A as its left child, and gamma as its right child
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(nodeAlpha, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeA.getColor());

        Assert.assertEquals(nodeGamma, nodeB.getRightChild());

        // Node D should be the root's right child and be black
        Assert.assertEquals(nodeD, nodeC.getRightChild());
        Assert.assertEquals(nodeC, nodeD.getParent());
        Assert.assertEquals(Color.BLACK, nodeD.getColor());

        // Node D should have E as its right child, and delta as its left child
        Assert.assertEquals(nodeDelta, nodeD.getLeftChild());

        Assert.assertEquals(nodeE, nodeD.getRightChild());
        Assert.assertEquals(nodeD, nodeE.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeE.getColor());
    }

    @Test
    public void deleteNodePassesComplexTest6() {
        // Set up input, based on mirror of Figure 13.7(c) - z is a right child,
        // x's sibling after the deletion w is black, and w's left
        // child is black
        int key = 237;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode nodeA = new RedBlackNode(key - 5);
        RedBlackNode nodeB = new RedBlackNode(key - 4); // w (x's sibling)
        RedBlackNode nodeGamma = new RedBlackNode(key - 3);
        RedBlackNode nodeC = new RedBlackNode(key - 2);
        RedBlackNode nodeDelta = new RedBlackNode(key - 1);
        RedBlackNode nodeD = new RedBlackNode(key); // original root
        RedBlackNode nodeEpsilon = new RedBlackNode(key + 1);
        RedBlackNode oldNode = new RedBlackNode(key + 2); // z (x's original parent, deleted, also y)
        RedBlackNode nodeE = new RedBlackNode(key + 3); // x
        tree.insertNode(nodeD);

        nodeD.setLeftChild(nodeB);
        nodeD.setRightChild(oldNode);
        nodeB.setParent(nodeD);
        oldNode.setParent(nodeD);

        nodeB.setLeftChild(nodeA);
        nodeB.setRightChild(nodeC);
        oldNode.setLeftChild(nodeEpsilon);
        oldNode.setRightChild(nodeE);
        nodeB.setColor(Color.BLACK);
        oldNode.setColor(Color.BLACK);

        nodeA.setLeftChild(RedBlackTree.nil);
        nodeA.setRightChild(RedBlackTree.nil);
        nodeA.setColor(Color.BLACK);
        nodeA.setParent(nodeB);
        nodeC.setLeftChild(nodeGamma);
        nodeC.setRightChild(nodeDelta);
        nodeC.setColor(Color.RED);
        nodeC.setParent(nodeB);

        nodeGamma.setLeftChild(RedBlackTree.nil);
        nodeGamma.setRightChild(RedBlackTree.nil);
        nodeGamma.setColor(Color.BLACK);
        nodeGamma.setParent(nodeC);
        nodeDelta.setLeftChild(RedBlackTree.nil);
        nodeDelta.setRightChild(RedBlackTree.nil);
        nodeDelta.setColor(Color.BLACK);
        nodeDelta.setParent(nodeC);

        nodeEpsilon.setLeftChild(RedBlackTree.nil);
        nodeEpsilon.setRightChild(RedBlackTree.nil);
        nodeEpsilon.setColor(Color.BLACK);
        nodeEpsilon.setParent(oldNode);
        nodeE.setLeftChild(RedBlackTree.nil);
        nodeE.setRightChild(RedBlackTree.nil);
        nodeE.setColor(Color.BLACK);
        nodeE.setParent(oldNode);

        // Verify the tree beforehand
        Assert.assertEquals(nodeD, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(nodeB, nodeD.getLeftChild());
        Assert.assertEquals(oldNode, nodeD.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeD.getColor());
        Assert.assertEquals(nodeD, nodeB.getParent());
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeC, nodeB.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeA.getColor());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());

        Assert.assertEquals(Color.RED, nodeC.getColor());
        Assert.assertEquals(nodeB, nodeC.getParent());
        Assert.assertEquals(nodeGamma, nodeC.getLeftChild());
        Assert.assertEquals(nodeDelta, nodeC.getRightChild());

        Assert.assertEquals(Color.BLACK, oldNode.getColor());
        Assert.assertEquals(nodeD, oldNode.getParent());
        Assert.assertEquals(nodeEpsilon, oldNode.getLeftChild());
        Assert.assertEquals(nodeE, oldNode.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeE.getColor());
        Assert.assertEquals(oldNode, nodeE.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.deleteNode(oldNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Node C should now be the root
        Assert.assertEquals(nodeC, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getParent());

        // Node B should be the root's left child and be black
        Assert.assertEquals(nodeB, nodeC.getLeftChild());
        Assert.assertEquals(nodeC, nodeB.getParent());
        Assert.assertEquals(Color.BLACK, nodeB.getColor());

        // Node B should have A as its left child, and gamma as its right child
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeA.getColor());

        Assert.assertEquals(nodeGamma, nodeB.getRightChild());

        // Node D should be the root's right child and be black
        Assert.assertEquals(nodeD, nodeC.getRightChild());
        Assert.assertEquals(nodeC, nodeD.getParent());
        Assert.assertEquals(Color.BLACK, nodeD.getColor());

        // Node D should have E as its right child, and delta as its left child
        Assert.assertEquals(nodeDelta, nodeD.getLeftChild());

        Assert.assertEquals(nodeE, nodeD.getRightChild());
        Assert.assertEquals(nodeD, nodeE.getParent());
        Assert.assertEquals(nodeEpsilon, nodeE.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeE.getColor());
    }

    @Test
    public void deleteNodePassesComplexTest7() {
        // Set up input, based on Figure 13.7(d) - z is a left child,
        // x's sibling after the deletion w is black, and w's right
        // child is red
        int key = 238;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode nodeAlpha = new RedBlackNode(key - 3);
        RedBlackNode oldNode = new RedBlackNode(key - 2); // z (x's original parent, deleted, also y)
        RedBlackNode nodeA = new RedBlackNode(key - 1); // x
        RedBlackNode nodeB = new RedBlackNode(key);
        RedBlackNode nodeC = new RedBlackNode(key + 1);
        RedBlackNode nodeD = new RedBlackNode(key + 2); // w (x's sibling)
        RedBlackNode nodeEpsilon = new RedBlackNode(key + 3);
        RedBlackNode nodeE = new RedBlackNode(key + 4);
        RedBlackNode nodeZeta = new RedBlackNode(key + 5);
        tree.insertNode(nodeB);

        nodeB.setLeftChild(oldNode);
        nodeB.setRightChild(nodeD);
        oldNode.setParent(nodeB);
        nodeD.setParent(nodeB);

        oldNode.setLeftChild(nodeAlpha);
        oldNode.setRightChild(nodeA);
        nodeD.setLeftChild(nodeC);
        nodeD.setRightChild(nodeE);
        oldNode.setColor(Color.BLACK);
        nodeD.setColor(Color.BLACK);

        nodeA.setLeftChild(RedBlackTree.nil);
        nodeA.setRightChild(RedBlackTree.nil);
        nodeA.setColor(Color.BLACK);
        nodeA.setParent(oldNode);

        nodeAlpha.setLeftChild(RedBlackTree.nil);
        nodeAlpha.setRightChild(RedBlackTree.nil);
        nodeAlpha.setColor(Color.BLACK);
        nodeAlpha.setParent(oldNode);

        nodeC.setLeftChild(RedBlackTree.nil);
        nodeC.setRightChild(RedBlackTree.nil);
        nodeC.setColor(Color.BLACK);
        nodeC.setParent(nodeD);
        nodeE.setLeftChild(nodeEpsilon);
        nodeE.setRightChild(nodeZeta);
        nodeE.setColor(Color.RED);
        nodeE.setParent(nodeD);

        nodeEpsilon.setLeftChild(RedBlackTree.nil);
        nodeEpsilon.setRightChild(RedBlackTree.nil);
        nodeEpsilon.setColor(Color.BLACK);
        nodeEpsilon.setParent(nodeE);
        nodeZeta.setLeftChild(RedBlackTree.nil);
        nodeZeta.setRightChild(RedBlackTree.nil);
        nodeZeta.setColor(Color.BLACK);
        nodeZeta.setParent(nodeE);

        // Verify the tree beforehand
        Assert.assertEquals(nodeB, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(oldNode, nodeB.getLeftChild());
        Assert.assertEquals(nodeD, nodeB.getRightChild());

        Assert.assertEquals(Color.BLACK, oldNode.getColor());
        Assert.assertEquals(nodeB, oldNode.getParent());
        Assert.assertEquals(nodeAlpha, oldNode.getLeftChild());
        Assert.assertEquals(nodeA, oldNode.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeA.getColor());
        Assert.assertEquals(oldNode, nodeA.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeD.getColor());
        Assert.assertEquals(nodeB, nodeD.getParent());
        Assert.assertEquals(nodeC, nodeD.getLeftChild());
        Assert.assertEquals(nodeE, nodeD.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeC.getColor());
        Assert.assertEquals(nodeD, nodeC.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getRightChild());

        Assert.assertEquals(Color.RED, nodeE.getColor());
        Assert.assertEquals(nodeD, nodeE.getParent());
        Assert.assertEquals(nodeEpsilon, nodeE.getLeftChild());
        Assert.assertEquals(nodeZeta, nodeE.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.deleteNode(oldNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Node D should now be the root
        Assert.assertEquals(nodeD, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getParent());

        // Node B should be the root's left child and be black
        Assert.assertEquals(nodeB, nodeD.getLeftChild());
        Assert.assertEquals(nodeD, nodeB.getParent());
        Assert.assertEquals(Color.BLACK, nodeB.getColor());

        // Node B should have A as its left child, and C as its right child
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(nodeAlpha, nodeA.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeA.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeA.getColor());

        Assert.assertEquals(nodeC, nodeB.getRightChild());
        Assert.assertEquals(nodeB, nodeC.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeC.getColor());

        // Node E should be the root's right child and be black
        Assert.assertEquals(nodeE, nodeD.getRightChild());
        Assert.assertEquals(nodeD, nodeE.getParent());
        Assert.assertEquals(Color.BLACK, nodeE.getColor());

        Assert.assertEquals(nodeEpsilon, nodeE.getLeftChild());
        Assert.assertEquals(nodeZeta, nodeE.getRightChild());
    }

    @Test
    public void deleteNodePassesComplexTest8() {
        // Set up input, based on mirror of Figure 13.7(d) - z is a right child,
        // x's sibling after the deletion w is black, and w's left
        // child is red
        int key = 239;
        RedBlackTree tree = new RedBlackTree();
        RedBlackNode nodeAlpha = new RedBlackNode(key - 5);
        RedBlackNode nodeA = new RedBlackNode(key - 4);
        RedBlackNode nodeBeta = new RedBlackNode(key - 3);
        RedBlackNode nodeB = new RedBlackNode(key - 2); // w (x's sibling)
        RedBlackNode nodeC = new RedBlackNode(key - 1);
        RedBlackNode nodeD = new RedBlackNode(key); // original root
        RedBlackNode nodeEpsilon = new RedBlackNode(key + 1);
        RedBlackNode oldNode = new RedBlackNode(key + 2); // z (x's original parent, deleted, also y)
        RedBlackNode nodeE = new RedBlackNode(key + 3); // x
        tree.insertNode(nodeD);

        nodeD.setLeftChild(nodeB);
        nodeD.setRightChild(oldNode);
        nodeB.setParent(nodeD);
        oldNode.setParent(nodeD);

        nodeB.setLeftChild(nodeA);
        nodeB.setRightChild(nodeC);
        oldNode.setLeftChild(nodeEpsilon);
        oldNode.setRightChild(nodeE);
        nodeB.setColor(Color.BLACK);
        oldNode.setColor(Color.BLACK);

        nodeA.setLeftChild(nodeAlpha);
        nodeA.setRightChild(nodeBeta);
        nodeA.setColor(Color.RED);
        nodeA.setParent(nodeB);
        nodeC.setLeftChild(RedBlackTree.nil);
        nodeC.setRightChild(RedBlackTree.nil);
        nodeC.setColor(Color.BLACK);
        nodeC.setParent(nodeB);

        nodeAlpha.setLeftChild(RedBlackTree.nil);
        nodeAlpha.setRightChild(RedBlackTree.nil);
        nodeAlpha.setColor(Color.BLACK);
        nodeAlpha.setParent(nodeA);
        nodeBeta.setLeftChild(RedBlackTree.nil);
        nodeBeta.setRightChild(RedBlackTree.nil);
        nodeBeta.setColor(Color.BLACK);
        nodeBeta.setParent(nodeA);

        nodeEpsilon.setLeftChild(RedBlackTree.nil);
        nodeEpsilon.setRightChild(RedBlackTree.nil);
        nodeEpsilon.setColor(Color.BLACK);
        nodeEpsilon.setParent(oldNode);
        nodeE.setLeftChild(RedBlackTree.nil);
        nodeE.setRightChild(RedBlackTree.nil);
        nodeE.setColor(Color.BLACK);
        nodeE.setParent(oldNode);

        // Verify the tree beforehand
        Assert.assertEquals(nodeD, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(nodeB, nodeD.getLeftChild());
        Assert.assertEquals(oldNode, nodeD.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeD.getColor());
        Assert.assertEquals(nodeD, nodeB.getParent());
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeC, nodeB.getRightChild());

        Assert.assertEquals(Color.RED, nodeA.getColor());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(nodeAlpha, nodeA.getLeftChild());
        Assert.assertEquals(nodeBeta, nodeA.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeC.getColor());
        Assert.assertEquals(nodeB, nodeC.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getRightChild());

        Assert.assertEquals(Color.BLACK, oldNode.getColor());
        Assert.assertEquals(nodeD, oldNode.getParent());
        Assert.assertEquals(nodeEpsilon, oldNode.getLeftChild());
        Assert.assertEquals(nodeE, oldNode.getRightChild());

        Assert.assertEquals(Color.BLACK, nodeE.getColor());
        Assert.assertEquals(oldNode, nodeE.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getRightChild());

        //Assert.assertTrue(tree.isValid());

        // Call the function
        tree.deleteNode(oldNode);

        // Verify the tree is still a valid red-black tree
        //Assert.assertTrue(tree.isValid());

        // Node B should now be the root
        Assert.assertEquals(nodeB, tree.getRoot());
        Assert.assertEquals(Color.BLACK, tree.getRoot().getColor());
        Assert.assertEquals(RedBlackTree.nil, tree.getRoot().getParent());

        // Node A should be the root's left child and be black
        Assert.assertEquals(nodeA, nodeB.getLeftChild());
        Assert.assertEquals(nodeB, nodeA.getParent());
        Assert.assertEquals(Color.BLACK, nodeA.getColor());

        Assert.assertEquals(nodeAlpha, nodeA.getLeftChild());
        Assert.assertEquals(nodeBeta, nodeA.getRightChild());

        // Node D should be the root's right child and be black
        Assert.assertEquals(nodeD, nodeB.getRightChild());
        Assert.assertEquals(nodeB, nodeD.getParent());
        Assert.assertEquals(Color.BLACK, nodeD.getColor());

        // Node D should have C as its left child, and E as its right child
        Assert.assertEquals(nodeC, nodeD.getLeftChild());
        Assert.assertEquals(nodeD, nodeC.getParent());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeC.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeC.getColor());

        Assert.assertEquals(nodeE, nodeD.getRightChild());
        Assert.assertEquals(nodeD, nodeE.getParent());
        Assert.assertEquals(nodeEpsilon, nodeE.getLeftChild());
        Assert.assertEquals(RedBlackTree.nil, nodeE.getRightChild());
        Assert.assertEquals(Color.BLACK, nodeE.getColor());
    }
}
