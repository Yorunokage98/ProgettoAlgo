package Selection;
import Utils.ArrayUtils;

public class Selection {

    public static int Select(int[] array, int k, ISelectionAlgorithm algo) {
        return algo.select(array, k);
    }

    public static boolean TestSelectAlgorithm(ISelectionAlgorithm algo, int[] array) {

        int[] sortedArray = ArrayUtils.CloneArray(array);
        ArrayUtils.Sort(sortedArray);

        int[] selectedArray = new int[array.length];

        for (int i = 0; i < array.length; i++) {

            int[] clone = ArrayUtils.CloneArray(array);

            //selectedArray[i] = QuickSelect(clone, i);
            selectedArray[i] = algo.select(clone, i);

        }

        System.out.println("Original, sorted and selected arrays respectively:");
        ArrayUtils.PrintArray(array);
        ArrayUtils.PrintArray(sortedArray);
        ArrayUtils.PrintArray(selectedArray);

        for (int i = 0; i < array.length; i++) {

            if (selectedArray[i] != sortedArray[i]) return false;

        }

        return true;

    }

}
