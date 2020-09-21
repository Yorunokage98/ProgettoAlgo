package BinarySearchTree;

import Utils.ArrayUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class RBTreeTest {

    int[] randomKeys;
    String[] randomValues;

    RBTree rbt;
    Node root;
    Node[] nodes;

    @Before
    public void setUp() throws Exception {
        randomKeys = ArrayUtils.RandomArray(100);
        randomValues = new String[randomKeys.length];
        for (int i = 0; i < randomKeys.length; i++) {
            randomValues[i] = "" + randomKeys[i];
        }

        rbt = new RBTree();
        nodes = new Node[randomKeys.length];
        root = new Node(randomKeys[0], randomValues[0]);
        root.isRed = false;
        nodes[0] = root;

        for(int i = 1; i < randomKeys.length; i++) {
            nodes[i] = new Node(randomKeys[i], randomValues[i]);
            rbt.insert(root, nodes[i]);
            root = rbt.FindRoot(root);
        }

        nodes = rbt.GetNodes(root);
    }

    @After
    public void checkInvariant() throws Exception {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].parent != null) {
                Assert.assertFalse(nodes[i].isRed && nodes[i].parent.isRed);
            }
        }

    }

    @After
    public void insureReciprocity() {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].left != null) Assert.assertEquals(nodes[i], nodes[i].left.parent);
            if (nodes[i].right != null) Assert.assertEquals(nodes[i], nodes[i].right.parent);
        }

    }

    @Test
    public void insert() {

    }

    @Test
    public void testTree() {

        for(int j = 0; j < 500; j++) {

            int[] keys = ArrayUtils.RandomArray(100);

            Node newRoot = new Node(keys[0], "" + keys[0]);

            for (int i = 1; i < keys.length; i++) {

                Node newNode = new Node(keys[i], "" + keys[i]);

                rbt.insert(newRoot, newNode);

                newRoot = rbt.FindRoot(newRoot);

                if (newNode.parent != null) {

                    if (newNode.key() > newNode.parent.key()) {
                        Assert.assertEquals(newNode, newNode.parent.right);
                    } else {
                        Assert.assertEquals(newNode, newNode.parent.left);
                    }

                }

            }

            int[] reconstruction = new int[keys.length];

            for (int i = 0; i < keys.length; i++) {

                reconstruction[i] = rbt.search(newRoot, keys[i]).key();

            }

            Assert.assertArrayEquals(keys, reconstruction);

        }

    }

    @Test
    public void RBTreeFixUp() {

        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].parent != null) {
                if (nodes[i].isRed && nodes[i].parent.isRed) rbt.RBTreeFixUp(nodes[i]);
            }
        }

    }

    @Test
    public void insureInvariant() {
        for (int i = 0; i < nodes.length; i++) {
            rbt.insureInvariant(nodes[i]);
        }
    }
}