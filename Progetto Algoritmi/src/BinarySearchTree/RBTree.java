package BinarySearchTree;

import org.junit.Assert;

public class RBTree extends BinarySearchTree {

    public void insert(Node root, Node newNode) {

        super.insert(root, newNode);

        newNode.isRed = true;

        insureInvariant(newNode);

    }

    public void insureInvariant(Node newNode) {

        if (newNode.parent != null) {

            if (newNode.parent.isRed) {

                RBTreeFixUp(newNode);

            }

        }

        Node root = FindRoot(newNode);
        root.isRed = false;

    }

    public void RBTreeFixUp(Node newNode) {

        Node parent = newNode.parent;
        Node grandpa = parent.parent;
        if (grandpa == null) return;
        Node uncle;
        if (parent == grandpa.right) {
            uncle = grandpa.left;
        } else {
            Assert.assertEquals(parent, grandpa.left);
            uncle = grandpa.right;
        }


        if (uncle == null || !uncle.isRed) {

            if (parent == grandpa.left) {
                //Left case

                if (newNode == parent.left) {
                    RightRotate(grandpa);
                    parent.isRed = false;
                    grandpa.isRed = true;
                } else {
                    LeftRotate(parent);
                    RightRotate(grandpa);
                    //insureInvariant(parent);
                    newNode.isRed = false;
                    grandpa.isRed = true;
                }

            } else {
                //Right case

                if (newNode == parent.right) {
                    LeftRotate(grandpa);
                    parent.isRed = false;
                    grandpa.isRed = true;
                } else {
                    RightRotate(parent);
                    LeftRotate(grandpa);
                    //insureInvariant(parent);
                    newNode.isRed = false;
                    grandpa.isRed = true;
                }

            }

        } else {

            parent.isRed = false;
            uncle.isRed = false;
            grandpa.isRed = true;
            insureInvariant(grandpa);

        }

    }

}