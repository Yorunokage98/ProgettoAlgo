package Selection;
import Utils.ArrayUtils;

public class QuickSelection implements ISelectionAlgorithm {

    public int select(int[] array, int k) {

        return QuickSelectRec(array, k, 0, array.length - 1);

    }

    public int QuickSelectRec(int[] array, int k, int a, int b) {

        int pivot = Partition(array, a, b);

        if (pivot == k) {
            return array[pivot];
        } else if (pivot > k) {
            return QuickSelectRec(array, k, a, pivot - 1);
        } else {
            return QuickSelectRec(array, k, pivot + 1, b);
        }

    }

    public int Partition(int[] array, int a, int b) {

        int smallest = a;

        int pivot = array[b];

        for (int i = a; i <= b; i++) {

            if (array[i] < pivot) {

                ArrayUtils.Swap(array, smallest, i);
                smallest++;

            }

        }

        ArrayUtils.Swap(array, smallest, b); //Move pivot to its intended location

        //println("Partitioned array on pivot: " + array[smallest] + " found in position: " + smallest);
        //PrintArray(array);

        return smallest;

    }

}
