package BinarySearchTree;

public class AVLTree extends BinarySearchTree {

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

        insureBalance(newNode);

    }

    void insureBalance(Node base) {

        Node x,y,z;

        x = base;
        if (x != null) {
            y = x.parent;
            if (y != null) {
                z = y.parent;
                if (z != null) {

                    do {

                        int balance = Balance(z);
                        //If unbalanced
                        if (Math.abs(balance) > 1) {

                            if ( y == z.left ) {
                                if ( x == y.left) {
                                    //Left left
                                    RightRotate(z);
                                } else {
                                    //Left right
                                    LeftRotate(y);
                                    RightRotate(z);
                                }
                            } else {
                                if ( x == y.left) {
                                    //Right left
                                    RightRotate(y);
                                    LeftRotate(z);
                                } else {
                                    //Right right
                                    LeftRotate(z);
                                }
                            }

                        }

                        if (z.parent != null) {
                            x = y;
                            y = z;
                            z = z.parent;
                        }

                    } while (z.parent != null);

                }
            }
        }



    }

}
