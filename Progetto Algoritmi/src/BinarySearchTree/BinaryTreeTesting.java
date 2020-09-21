package BinarySearchTree;

import Utils.ArrayUtils;

public class BinaryTreeTesting {

    public static boolean TestBinaryTree(IBinaryTree tree, int[] keys) {

        Node root = new Node(keys[0], "" + keys[0]);

        for (int i = 1; i < keys.length; i++) {

            Node newNode = new Node(keys[i], "" + keys[i]);

            tree.insert(root, newNode);

            root = tree.FindRoot(root);

            if (newNode.key() > newNode.parent.key()) {

                if (newNode != newNode.parent.right) System.out.println("PARENT ERROR!");

            } else {

                if (newNode != newNode.parent.left) System.out.println("PARENT ERROR!");

            }

            System.out.println("Inserting " + keys[i] + " in tree");

            //((BinarySearchTree)tree).LeftRotate(root);

            tree.InOrderVisit(root);
            System.out.println("");

        }

        int[] reconstruction = new int[keys.length];

        for (int i = 0; i < keys.length; i++) {

            reconstruction[i] = tree.search(root, keys[i]).key();

        }

        System.out.println("Original and reconstruction arrays respectively:");
        ArrayUtils.PrintArray(keys);
        ArrayUtils.PrintArray(reconstruction);

        for (int i = 0; i < keys.length; i++) {

            if  (keys[i] != reconstruction[i]) return false;

        }

        return true;

    }

}
