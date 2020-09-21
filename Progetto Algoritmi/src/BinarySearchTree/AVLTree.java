package BinarySearchTree;

import org.junit.Assert;

public class AVLTree extends BinarySearchTree {

    public void insert(Node root, Node newNode) {

        super.insert(root, newNode);

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

                        int balance = Balance(z);
                        //If unbalanced
                        if (Math.abs(balance) > 1) {

                            if ( y == z.left ) {
                                if ( x == y.left) {
                                    //Left left
                                    RightRotate(z);
                                } else {
                                    Assert.assertEquals(x, y.right);
                                    //Left right
                                    LeftRotate(y);
                                    RightRotate(z);
                                }
                            } else {
                                if ( x == y.left) {
                                    //Right left
                                    Assert.assertEquals(y, z.right);
                                    RightRotate(y);
                                    LeftRotate(z);
                                } else {
                                    Assert.assertEquals(y, z.right);
                                    Assert.assertEquals(x, y.right);
                                    //Right right
                                    LeftRotate(z);
                                }
                            }

                        }

                        insureBalance(y);

                }
            }
        }



    }

}
