package Selection;
import Utils.ArrayUtils;

class MOMPivotCalculator {

    public static int GetPivot(int[] array, int start, int end) {

        if ( (end-start) <= 5 ) {

            ArrayUtils.Sort(array, start, end);
            return (end+start)/2;

        }

        //array = CloneArray(array);

        int intervalLength = Math.abs(end-start);

        int amountOfGroups = (int)Math.ceil( intervalLength/5.0 );

        int lastGroupSize = 5 - ((amountOfGroups * 5) - intervalLength);

        if (amountOfGroups > 1) {

            for (int i = 0; i < amountOfGroups - 1; i++) {

                int groupStart = start + (i*5), groupEnd = start + (((i+1)*5) - 1), groupCenter = start + ((i*5) + 2);

                ArrayUtils.Sort(array, groupStart, groupEnd);

                ArrayUtils.Swap(array, start + i, groupCenter);

            }

        }

        //Last group
        if (lastGroupSize > 0) {
            int startOffset = start + ((amountOfGroups - 1)*5);
            ArrayUtils.Sort(array, startOffset, end);
            ArrayUtils.Swap(array, startOffset + (int)Math.floor(lastGroupSize/2), start + amountOfGroups - 1);
        }

        //Median of medians
        return QuickSelectMedian(array, start, start + amountOfGroups - 1);

    }

    public static int QuickSelectMedian(int[] array, int start, int end) {

        return MedianOfMediansSelection.QuickSelectRec(array, (end+start)/2, start, end, (a,s,e) -> GetPivot(a,s,e));

    }

}