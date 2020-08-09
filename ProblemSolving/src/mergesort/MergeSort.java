package mergesort;

@SuppressWarnings("ManualArrayCopy")
public class MergeSort {

    private void merge(int[] array, int start, int mid, int end) {
        int l1 = (mid-start)+1;
        int l2 = (end-mid);
        int[] first = new int[l1];
        int[] second = new int[l2];

        for(int i = 0; i<first.length ; i++) {
            first[i] = array[start+i];
        }
        for(int i = 0; i<second.length ; i++) {
            second[i] = array[mid+i+1];
        }
        int i=0; int j = 0; int k = start;

        while(i < first.length && j < second.length) {
            if(first[i] <= second[j]) {
                array[k] = first[i];
                i++; k++;
            } else if(first[i] > second[j]) {
                array[k] = second[j];
                j++; k++;
            }
        }

        // When the above while loop ends, means either of the array is exhausted.

        while(i< first.length) {
            // first is left.
            array[k] = first[i];
            i++;
            k++;
        }

        while(j< second.length) {
            // second is left.
            array[k] = second[j];
            j++; k++;
        }
    }

    private void sort(int[] array, int start, int end) {

        if(start  < end) {
            int mid = (start+end)/2;
            sort(array,start,mid);
            sort(array,mid+1,end);
            merge(array,start,mid,end);
        }
    }

    public int[] apply(int[] array) {
        sort(array,0,array.length-1);
        return array;
    }
}
