package BinarySearchTree;

public class RBTree extends BinarySearchTree {

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

        newNode.isRed = true;

        insureInvariant(newNode);

    }

    public void insureInvariant(Node newNode) {

        if (newNode.parent == null) return;

        if (newNode.parent.isRed && newNode.isRed) {

            RBTreeFixUp(newNode);

        }

        Node root = FindRoot(newNode);
        root.isRed = false;

    }

    public void RBTreeFixUp(Node newNode) {

        Node parent = newNode.parent;
        Node granpa = parent.parent;
        if (granpa == null) return;
        Node uncle;
        if (parent == granpa.right) {
            uncle = granpa.left;
        } else {
            uncle = granpa.right;
        }


        if (uncle == null || !uncle.isRed) {

            if (parent == granpa.left) {
                //Left case

                if (newNode == parent.left) {
                    RightRotate(granpa);
                    parent.isRed = false;
                    granpa.isRed = true;
                } else {
                    LeftRightRotate(granpa);
                    parent.isRed = false;
                    granpa.isRed = true;
                }

            } else {
                //Right case

                if (newNode == parent.right) {
                    LeftRotate(granpa);
                    parent.isRed = false;
                    granpa.isRed = true;
                } else {
                    RightLeftRotate(granpa);
                    parent.isRed = false;
                    granpa.isRed = true;
                }

            }

        } else {

            parent.isRed = false;
            uncle.isRed = false;
            granpa.isRed = true;
            insureInvariant(granpa);

        }

    }

}