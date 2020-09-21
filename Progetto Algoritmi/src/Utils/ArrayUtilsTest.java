package Utils;

import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.Random;

import static org.junit.Assert.*;

public class ArrayUtilsTest {

    @org.junit.Test
    public void sort() {

        int[] arr = ArrayUtils.RandomArray(100);

        ArrayUtils.Sort(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            Assert.assertTrue(arr[i] <= arr[i+1]);
        }

    }

    @org.junit.Test
    public void sort1() {

        int[] arr = ArrayUtils.RandomArray(100);

        Random r = new Random();

        int e = r.nextInt(arr.length);
        if (e == 0) e = 1;
        int s = r.nextInt(e);

        ArrayUtils.Sort(arr, s, e);

        for (int i = s; i < e - 1; i++) {
            Assert.assertTrue(arr[i] <= arr[i+1]);
        }

    }

    @org.junit.Test
    public void shiftArrayRight() {

        int[] arr = ArrayUtils.RandomArray(100);
        int[] copy = ArrayUtils.CloneArray(arr);

        Random r = new Random();
        int e = r.nextInt(arr.length);
        if (e == 0) e = 1;
        int s = r.nextInt(e);

        ArrayUtils.ShiftArrayRight(copy, s, e);

        for (int i = s; i < e; i++) {
            Assert.assertTrue(arr[i] == copy[(i+1)%arr.length]);
        }

    }

    @org.junit.Test
    public void shiftArrayLeft() {

        int[] arr = ArrayUtils.RandomArray(100);
        int[] copy = ArrayUtils.CloneArray(arr);

        Random r = new Random();
        int e = r.nextInt(arr.length);
        if (e == 0) e = 1;
        int s = r.nextInt(e);

        ArrayUtils.ShiftArrayLeft(arr, s, e);

        for (int i = s; i < e; i++) {
            Assert.assertEquals((long)arr[i], (long)(copy[(i+1)%arr.length]));
        }

    }

    @org.junit.Test
    public void inPlaceSwap() {

        int[] arr = ArrayUtils.RandomArray(100);
        int[] copy = ArrayUtils.CloneArray(arr);

        Random r = new Random();
        int e = r.nextInt(arr.length);
        if (e == 0) e = 1;
        int s = r.nextInt(e);

        ArrayUtils.InPlaceSwap(arr, s, e);

        Assert.assertTrue(arr[s] == copy[e] && arr[e] == copy[s]);

    }

    @org.junit.Test
    public void swap() {

        int[] arr = ArrayUtils.RandomArray(100);
        int[] copy = ArrayUtils.CloneArray(arr);

        Random r = new Random();
        int e = r.nextInt(arr.length);
        if (e == 0) e = 1;
        int s = r.nextInt(e);

        ArrayUtils.Swap(arr, s, e);

        Assert.assertTrue(arr[s] == copy[e] && arr[e] == copy[s]);

    }

    @org.junit.Test
    public void mergeSort() {

        for (int j = 0; j < 500; j++) {

            int[] arr = ArrayUtils.RandomArray(100);

            Random r = new Random();

            int e = r.nextInt(arr.length);
            if (e == 0) e = 1;
            int s = r.nextInt(e);

            ArrayUtils.MergeSort(arr, s, e);

            for (int i = s; i < e - 1; i++) {
                Assert.assertTrue(arr[i] + " is greater than " + arr[i + 1], arr[i] <= arr[i + 1]);
            }

        }

    }

    @org.junit.Test
    public void cloneArray() {
        int[] arr = ArrayUtils.RandomArray(100);
        int[] copy = ArrayUtils.CloneArray(arr);

        for (int i = 0; i < arr.length; i++) {
            Assert.assertTrue(arr[i] == copy[i]);
        }
    }

    @Test
    public void randomArray() {
        int[] arr = ArrayUtils.RandomArray(1000);

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (i != j) Assert.assertNotEquals(arr[i], arr[j]);
            }
        }
    }
}