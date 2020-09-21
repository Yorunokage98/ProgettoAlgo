package BinarySearchTree;

import Utils.ArrayUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;
import java.util.Stack;

import static org.junit.Assert.assertEquals;

public class AVLTreeTest {

    int[] randomKeys;
    String[] randomValues;

    AVLTree avl;
    Node root;
    Node[] nodes;

    @Before
    public void setUp() throws Exception {
        randomKeys = ArrayUtils.RandomArray(100);
        randomValues = new String[randomKeys.length];
        for (int i = 0; i < randomKeys.length; i++) {
            randomValues[i] = "" + randomKeys[i];
        }

        avl = new AVLTree();
        nodes = new Node[randomKeys.length];
        root = new Node(randomKeys[0], randomValues[0]);
        nodes[0] = root;

        for(int i = 1; i < randomKeys.length; i++) {
            nodes[i] = new Node(randomKeys[i], randomValues[i]);
            Assert.assertNotNull(root);
            avl.insert(root, nodes[i]);
            root = avl.FindRoot(root);
        }

        root = avl.FindRoot(root);

        Assert.assertEquals(nodes.length, avl.Size(root));
    }

    @After
    public void insureInvariant() {

        root = avl.FindRoot(root);

        for (int i = 0; i < nodes.length; i++) {
            Assert.assertTrue(Math.abs(avl.Balance(nodes[i])) <= 1);
        }

        Stack<Node> inspecting = new Stack<Node>();

        inspecting.push(root);

        while (!inspecting.empty()) {
            Node parent = inspecting.pop();

            if (parent.left != null) {
                inspecting.push(parent.left);
                Assert.assertTrue(parent.key() >= parent.left.key());
            }

            if (parent.right != null) {
                inspecting.push(parent.right);
                Assert.assertTrue(parent.key() <= parent.right.key());
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

        Random r = new Random();
        Node[] newNodes = new Node[100];

        for (int i = 0; i < newNodes.length; i++) {

            int val = r.nextInt();
            newNodes[i] = new Node(val, "" + val);

            avl.insert(root, newNodes[i]);

            root = avl.FindRoot(root);

            Assert.assertEquals(i + nodes.length + 1, avl.Size(root));

        }

        for (int i = 0; i < newNodes.length; i++) {

            Node search = avl.search(root, newNodes[i].key());
            Assert.assertNotNull(search);
            assertEquals(newNodes[i].key(), search.key());

        }
        for (int i = 0; i < nodes.length; i++) {

            Node search = avl.search(root, nodes[i].key());
            Assert.assertNotNull(search);
            assertEquals(nodes[i].key(), search.key());

        }

    }

    @Test
    public void insureBalance() {

        Node[] newNodes = new Node[100];

        Random r = new Random();
        BinarySearchTree bst = new BinarySearchTree();

        for (int i = 0; i < newNodes.length; i++) {

            int val = r.nextInt();
            newNodes[i] = new Node(val, "" + val);

            bst.insert(root, newNodes[i]);
            avl.insureBalance(newNodes[i]);

            root = avl.FindRoot(root);

            for (int j = 0; j < nodes.length; j++) {
                Assert.assertTrue(Math.abs(avl.Balance(nodes[j])) <= 1);
            }

        }

    }

    @Test
    public void testTree() {

        for(int j = 0; j < 500; j++) {

            int[] keys = ArrayUtils.RandomArray(100);

            Node newRoot = new Node(keys[0], "" + keys[0]);

            for (int i = 1; i < keys.length; i++) {

                Node newNode = new Node(keys[i], "" + keys[i]);

                avl.insert(newRoot, newNode);

                newRoot = avl.FindRoot(newRoot);

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

                reconstruction[i] = avl.search(newRoot, keys[i]).key();

            }

            Assert.assertArrayEquals(keys, reconstruction);

        }

    }
}