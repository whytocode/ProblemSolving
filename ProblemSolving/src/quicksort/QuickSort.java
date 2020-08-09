package quicksort;

public class QuickSort {

    private final Partition mPartition;

    public QuickSort() {
        this.mPartition = new Partition();
    }

    public void sort(final int[] toBeSortedArray) {
        _internalSort(toBeSortedArray,0, toBeSortedArray.length-1);
    }

    private void _internalSort(int[] mArray, int low, int high) {
        if(low < high) {
            int pi = mPartition.partition(mArray,low,high);
            _internalSort(mArray,low,pi-1);
            _internalSort(mArray,pi+1,high);
        }
    }

    public void printArray(int[] a) {
        System.out.println();
        for(int i : a) {
            System.out.print(i+",");
        }
    }
}
