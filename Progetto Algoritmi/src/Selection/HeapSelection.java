package Selection;
import Utils.ArrayUtils;

public class HeapSelection {

    public static int select(int[] array, int k) {

        BuildHeap(array, true, array.length);

        int helperSize = 1;

        int[] helper = new int[array.length];

        helper[0] = array[0];

        for (int i = 0; i < k; i++) {

            int root = helper[0];
            int rootIndex = SearchMinHeap(array, root);

            ArrayUtils.Swap(helper, 0, helperSize - 1);
            helperSize--;
            MinHeapifyRec(helper, 0, helperSize);

            if (left(rootIndex) < array.length) {
                helperSize++;
                ArrayUtils.ShiftArrayRight(helper, 0, helperSize - 1);
                helper[0] = array[left(rootIndex)];
                MinHeapifyRec(helper, 0, helperSize);
            }

            if (right(rootIndex) < array.length) {
                helperSize++;
                ArrayUtils.ShiftArrayRight(helper, 0, helperSize - 1);
                helper[0] = array[right(rootIndex)];
                MinHeapifyRec(helper, 0, helperSize);
            }

        }

        return helper[0];

    }

    public static boolean CheckMinHeap(int[] array, int size) {

        for (int i = 0; i < size; i++) {

            if (left(i) < size) {
                if (array[left(i)] < array[i]) return false;
            }

            if (right(i) < size) {
                if (array[right(i)] < array[i]) return false;
            }

        }

        return true;

    }

    //Returns index of the value in a minHeap if present. If missing returns -1
    public static int SearchMinHeap(int[] array, int value) {

        return SearchMinHeapRec(array, value, 0);

    }

    public static int SearchMinHeapRec(int[] array, int value, int index) {

        if (index >= array.length) return -1;
        if (array[index] == value) return index;
        if (array[index] > value) return -1;

        int left = -1, right = -1;

        left = SearchMinHeapRec(array, value, left(index));
        if (left == -1) {
            right = SearchMinHeapRec(array, value, right(index));
            return right;
        } else return left;

    }

    public static void BuildHeap(int[] array, boolean isMinHeap, int size) {

        if (!isMinHeap) {
            for (int i = size/2 - 1; i >= 0; i--) {
                MaxHeapifyRec(array, i, size);
            }
        } else {
            for (int i = size/2 - 1; i >= 0; i--) {
                MinHeapifyRec(array, i, size);
            }
        }

        //if (!CheckMinHeap(array, size)) {
        //  println("#### THIS IS NOT A HEAP ####");
        //  PrintArray(array);
        //}

    }

    public static void MaxHeapifyRec(int[] array, int current, int size) {

        int largest;

        if (left(current) < size && array[left(current)] > array[current]) {
            largest = left(current);
        } else largest = current;

        if (right(current) < size && array[right(current)] > array[largest]) largest = right(current);

        if (largest != current) {
            ArrayUtils.Swap(array, current, largest);
            MaxHeapifyRec(array, largest, size);
        }

    }

    public static void MinHeapifyRec(int[] array, int current, int size) {

        int smallest;

        if (left(current) < size && array[left(current)] < array[current]) {
            smallest = left(current);
        } else smallest = current;

        if (right(current) < size && array[right(current)] < array[smallest]) smallest = right(current);

        if (smallest != current) {
            ArrayUtils.Swap(array, current, smallest);
            MinHeapifyRec(array, smallest, size);
        }

    }

    public static int left(int i) { return (2*i)+1;}
    public static int right(int i) { return (2*i)+2;}
    public static int parent(int i) { return (i-1)/2;}


}