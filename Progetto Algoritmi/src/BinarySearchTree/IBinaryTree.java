package BinarySearchTree;

public interface IBinaryTree {

    void insert(Node root, Node newNode);

    Node search(Node root, int nodeKey);

    void InOrderVisit(Node root);

    Node FindRoot(Node node);

}
