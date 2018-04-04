package rbtrees;

import org.junit.Assert;
import org.junit.Test;

public class RedBlackNodeTests {

    @Test
    public void constructorSetsUpMembers() {
        // Set up input
        int key = 1;

        // Call the constructor
        RedBlackNode node = new RedBlackNode(key);

        // Verify the members are set correctly
        Assert.assertEquals(key, node.getKey());
        Assert.assertNull(node.getLeftChild());
        Assert.assertNull(node.getRightChild());
        Assert.assertNull(node.getParent());
        Assert.assertNull(node.getColor());
    }
    
    @Test
    public void constructor2SetsUpMembers() {
        // Set up input
        int key = 2;
        Color color = Color.RED;

        // Call the constructor
        RedBlackNode node = new RedBlackNode(key, color);

        // Verify the members are set correctly
        Assert.assertEquals(key, node.getKey());
        Assert.assertNull(node.getLeftChild());
        Assert.assertNull(node.getRightChild());
        Assert.assertNull(node.getParent());
        Assert.assertEquals(color, node.getColor());
    }

    @Test
    public void findNodeFindsSelf() {
        // Set up input
        int key = 3;
        RedBlackNode node = new RedBlackNode(key);

        // Set up expected result
        RedBlackNode expectedResult = node;

        // Call the function
        RedBlackNode actualResult = node.findNode(key);

        // Verify the output
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findNodeFindsLeftChild() {
        // Set up input
        int key = 4;
        RedBlackNode child = new RedBlackNode(key);
        RedBlackNode node = new RedBlackNode(key + 2);
        node.setLeftChild(child);

        // Set up expected result
        RedBlackNode expectedResult = child;

        // Call the function
        RedBlackNode actualResult = node.findNode(key);

        // Verify the output
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findNodeFindsRightChild() {
        // Set up input
        int key = 5;
        RedBlackNode child = new RedBlackNode(key);
        RedBlackNode node = new RedBlackNode(key - 2);
        node.setRightChild(child);

        // Set up expected result
        RedBlackNode expectedResult = child;

        // Call the function
        RedBlackNode actualResult = node.findNode(key);

        // Verify the output
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findNodeFindsDeepChild() {
        // Set up input
        int key = 6;
        RedBlackNode grandChild = new RedBlackNode(key);
        RedBlackNode leftChild = new RedBlackNode(key - 2);
        leftChild.setRightChild(grandChild);
        RedBlackNode rightChild = new RedBlackNode(key + 6);
        RedBlackNode node = new RedBlackNode(key + 4);
        node.setLeftChild(leftChild);
        node.setRightChild(rightChild);

        // Set up expected result
        RedBlackNode expectedResult = grandChild;

        // Call the function
        RedBlackNode actualResult = node.findNode(key);

        // Verify the output
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findNodeReturnsNullForMissingLeftChild() {
        // Set up input
        int key = 7;
        RedBlackNode node = new RedBlackNode(key + 1);
        node.setLeftChild(RedBlackTree.nil);
        node.setRightChild(RedBlackTree.nil);

        // Set up expected result
        RedBlackNode expectedResult = null;

        // Call the function
        RedBlackNode actualResult = node.findNode(key);

        // Verify the output
        Assert.assertEquals(expectedResult, actualResult);
    }

    @Test
    public void findNodeReturnsNullForMissingRightChild() {
        // Set up input
        int key = 8;
        RedBlackNode node = new RedBlackNode(key - 1);
        node.setLeftChild(RedBlackTree.nil);
        node.setRightChild(RedBlackTree.nil);

        // Set up expected result
        RedBlackNode expectedResult = null;

        // Call the function
        RedBlackNode actualResult = node.findNode(key);

        // Verify the output
        Assert.assertEquals(expectedResult, actualResult);
    }
}
