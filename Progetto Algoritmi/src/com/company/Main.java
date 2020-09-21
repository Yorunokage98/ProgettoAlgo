package com.company;

import Selection.*;
import BinarySearchTree.*;

public class Main {

    public static void main(String[] args) {

        int[] test = {123, 42, 3, 54342, 6, 1, -23, -1111, 11, 24, -3};

        Selection.TestSelectAlgorithm((a, k) -> MedianOfMediansSelection.select(a, k), test);

        System.out.println("###########################################################################");

        BinaryTreeTesting.TestBinaryTree(new AVLTree(), test);

    }
}
