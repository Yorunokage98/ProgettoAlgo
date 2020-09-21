package Utils;

import java.util.Random;

public class ArrayUtils {

    public static void Sort(int[] array) {

        Sort(array, 0, array.length - 1);

    }

    //Estremi inclusi
    public static void Sort(int[] array, int start, int end) {

        MergeSort(array, start, end);

    }

    public static void ShiftArrayRight(int[] array, int start, int end) {

        for (int i = end; i > start; i--) {

            Swap(array, i, i-1);

        }

    }

    public static void ShiftArrayLeft(int[] array, int start, int end) {

        for (int i = start; i < end; i++) {

            Swap(array, i, i+1);

        }

    }

    public static void InPlaceSwap(int[] array, int a, int b) {

        array[a] += array[b];
        array[b] = array[a] - array[b];
        array[a] -= array[b];

    }

    public static void Swap(int[] array, int a, int b) {

        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;

    }

    public static void Swap(String[] array, int a, int b) {

        String temp = array[a];
        array[a] = array[b];
        array[b] = temp;

    }

    public static void PrintArray(int[] array, int start, int end) {

        System.out.print("{");

        for (int i = start; i < end; i++) {

            System.out.print(array[i] + ",");

        }

        System.out.print(array[end] + "}");
        System.out.println();

    }

    public static void PrintArray(int[] array) {

        if (array.length == 0) {
            PrintArray(array, 0, 0);
            return;
        }

        PrintArray(array, 0, array.length-1);

    }

    public static void MergeSort(int[] array, int start, int end) {

        if (start < end) {

            int center = (start + end)/2;

            MergeSort(array, start, center);
            MergeSort(array, center + 1, end);

            Merge(array, start, center, end);

        }

    }

    public static void Merge(int[] array, int start, int center, int end) {

        int start2 = center+1;

        if (array[center] <= array[start2]) return;

        while(start <= center && start2 <= end) {

            if (array[start] <= array[start2]) {
                start++;
            } else {
                int value = array[start2];
                int index = start2;

                // Shift all the elements between element 1
                // element 2, right by 1.
                while (index != start) {
                    array[index] = array[index - 1];
                    index--;
                }
                array[start] = value;

                // Update all the pointers
                start++;
                center++;
                start2++;
            }

        }

    }

    public static int[] CloneArray(int[] array) {

        int[] output = new int[array.length];
        for (int i = 0; i < array.length; i++) {
            output[i] = array[i];
        }
        return output;

    }

    public static int[] RandomArray(int length) {

        int[] arr = new int[length];
        Random r = new Random();

        for (int i = 0; i < length; i++) {
            arr[i] = r.nextInt();
        }

        return arr;
    }

}
