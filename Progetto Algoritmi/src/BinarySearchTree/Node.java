package BinarySearchTree;

public class Node {

    protected int nodeKey;
    public String value;

    public Node left;
    public Node right;
    public Node parent;

    public boolean isRed = true;

    public Node() {

        nodeKey = 0;
        this.value = "";

    }

    public Node(int newKey, String value) {

        nodeKey = newKey;
        this.value = value;

    }

    public int key() {
        return nodeKey;
    }

}
