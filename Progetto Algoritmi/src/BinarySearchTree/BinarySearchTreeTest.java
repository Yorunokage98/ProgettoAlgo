package BinarySearchTree;

import Utils.ArrayUtils;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import sun.security.ssl.Debug;

import java.util.Random;
import java.util.Stack;

import static org.junit.Assert.*;

public class BinarySearchTreeTest {

    int[] randomKeys;
    String[] randomValues;

    BinarySearchTree bst;
    Node root;
    Node[] nodes;

    @Before
    public void setUp() throws Exception {
        randomKeys = ArrayUtils.RandomArray(100);
        randomValues = new String[randomKeys.length];
        for (int i = 0; i < randomKeys.length; i++) {
            randomValues[i] = "" + randomKeys[i];
        }

        bst = new BinarySearchTree();
        nodes = new Node[randomKeys.length];
        root = new Node(randomKeys[0], randomValues[0]);
        nodes[0] = root;

        for(int i = 1; i < randomKeys.length; i++) {
            nodes[i] = new Node(randomKeys[i], randomValues[i]);
            bst.insert(root, nodes[i]);
        }

        nodes = bst.GetNodes(bst.FindRoot(root));
    }

    @After
    public void assertInvariants() {

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

            bst.insert(root, newNodes[i]);

        }

        for (int i = 0; i < newNodes.length; i++) {

            assertEquals(newNodes[i], bst.search(root, newNodes[i].key()));

        }

        for (int i = 0; i < nodes.length; i++) {

            assertEquals(nodes[i].key(), bst.search(root, nodes[i].key()).key());

        }

    }

    @Test
    public void search() {

        for (int i = 0; i < randomKeys.length; i++) {

            int toSearch = randomKeys[i];
            Node n = bst.search(root, toSearch);

            Assert.assertEquals(n.key(), toSearch);
            Assert.assertEquals(n.value, randomValues[i]);

        }

    }

    @Test
    public void findRoot() {

        for (int i = 0; i < nodes.length; i++) {

            Assert.assertEquals(root, bst.FindRoot(nodes[i]));

        }

    }


    @Test
    public void rotate() {

        for (int i = 0; i < nodes.length; i++) {

            if(nodes[i].left != null) bst.RightRotate(nodes[i]);

            if(nodes[i].right != null) bst.LeftRotate(nodes[i]);

        }

    }

    @Test
    public void height() {

        for (int i = 0; i < nodes.length; i++) {

            Assert.assertEquals(bst.Height(nodes[i]), 1 + Math.max(bst.Height(nodes[i].left), bst.Height(nodes[i].right)));

        }

    }

    @Test
    public void balance() {

        for (int i = 0; i < nodes.length; i++) {

            Assert.assertEquals(bst.Balance(nodes[i]), bst.Height(nodes[i].right) - bst.Height(nodes[i].left));

        }

    }
}