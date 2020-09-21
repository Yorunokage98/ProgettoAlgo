package BinarySearchTree;

import org.junit.Assert;

import java.util.Stack;

public class BinarySearchTree implements IBinaryTree {

    public void insert(Node root, Node newNode) {

        if (newNode.key() >= root.key()) {
            if (root.right == null) {
                root.right = newNode;
                newNode.parent = root;
            } else {
                insert(root.right, newNode);
                return;
            }
        } else {
            if (root.left == null) {
                root.left = newNode;
                newNode.parent = root;
            } else {
                insert(root.left, newNode);
                return;
            }
        }

    }

    public Node search(Node root, int nodeKey) {

        if (root == null) return null;

        if (root.key() == nodeKey) return root;

        if (root.key() > nodeKey) return search(root.left, nodeKey);

        return search(root.right, nodeKey);

    }

    public Node FindRoot(Node node) {

        Node parent = node.parent;

        if (parent == null) return node;

        return FindRoot(parent);

    }

    public void InOrderVisit(Node root) {

        if (root == null) return;

        InOrderVisit(root.left);
        System.out.print("[" + root.key() + "," + root.value + "]");
        InOrderVisit(root.right);

    }

    public void RightLeftRotate(Node root) {

        RightRotate(root.right);
        LeftRotate(root);

    }

    public void LeftRightRotate(Node root) {

        LeftRotate(root.left);
        RightRotate(root);

    }

    public void RightRotate(Node root) {

        Node parent = root.parent;
        Node newRoot = root.left;
        if (newRoot == null) {
            System.out.println("Was null");
        };
        Node ball = newRoot.right;

        if (parent != null) {
            if (root == parent.left) {
                parent.left = newRoot;
            } else {
                parent.right = newRoot;
            }
        }
        newRoot.parent = parent;

        root.left = ball;
        if (ball != null) {
            ball.parent = root;
        }

        newRoot.right = root;
        root.parent = newRoot;


    }

    public void LeftRotate(Node root) {

        Node parent = root.parent;
        Node newRoot = root.right;
        if (newRoot == null) {
            System.out.println("Was null");
        };
        Node ball = newRoot.left;

        if (parent != null) {
            if (root == parent.left) {
                parent.left = newRoot;
            } else {
                parent.right = newRoot;
            }
        }
        newRoot.parent = parent;

        root.right = ball;
        if (ball != null) {
            ball.parent = root;
        }

        newRoot.left = root;
        root.parent = newRoot;


    }

    public int Height(Node root) {

        if (root == null) return -1;

        return Math.max(Height(root.right), Height(root.left)) + 1;

    }

    public int Balance(Node root) {

        return Height(root.right) - Height(root.left);

    }

    public int Size(Node root) {

        if (root == null) return 0;

        return Size(root.left) + Size(root.right) + 1;

    }

    public Node[] GetNodes(Node root) {

        Node[] nodes = new Node[Size(root)];

        int index = 0;

        Stack<Node> inspecting = new Stack<Node>();
        inspecting.push(root);
        nodes[index] = root;
        index++;

        while (!inspecting.empty()) {
            Node parent = inspecting.pop();

            if (parent.left != null) {
                inspecting.push(parent.left);
                nodes[index] = parent.left;
                index++;
            }

            if (parent.right != null) {
                inspecting.push(parent.right);
                nodes[index] = parent.right;
                index++;
            }
        }

        return nodes;

    }


}
