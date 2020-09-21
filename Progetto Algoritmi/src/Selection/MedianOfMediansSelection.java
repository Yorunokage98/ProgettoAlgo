package Selection;
import Utils.ArrayUtils;

public class MedianOfMediansSelection {

    public static int select(int[] array, int k) {

        return array[QuickSelectRec(array, k, 0, array.length - 1, (a,s,e) -> MOMPivotCalculator.GetPivot(a,s,e))];

    }

    public static int QuickSelectRec(int[] array, int k, int a, int b, IPivotCalculator pivotAlg) {

        if (a == b) return a;

        int pivot = pivotAlg.GetPivot(array, a, b);

        pivot = Partition(array, a, b, pivot);

        if (pivot == k) {
            return pivot;
        } else if (pivot > k) {
            return QuickSelectRec(array, k, a, pivot - 1, pivotAlg);
        } else {
            return QuickSelectRec(array, k, pivot + 1, b, pivotAlg);
        }

    }

    public static int Partition(int[] array, int a, int b, int pivot) {

        int smallest = a;

        //Place pivot at the end of array
        ArrayUtils.Swap(array, b, pivot);
        ArrayUtils.ShiftArrayLeft(array, pivot, b - 1);

        int pivotValue = array[b];

        for (int i = a; i <= b; i++) {

            if (array[i] < pivotValue) {

                ArrayUtils.Swap(array, smallest, i);
                smallest++;

            }

        }

        ArrayUtils.Swap(array, smallest, b); //Move pivot to its intended location

        //println("Partitioned array on pivot: " + array[smallest] + " found in position: " + smallest);
        //PrintArray(array, a, b);

        return smallest;

    }

}
