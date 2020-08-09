package problems;

// Binary search implementation of Painter's Partition problem
public class PartitionBinarySearch {
    private final int[] mArray;
    private final int N;

    public PartitionBinarySearch(int[] arrayToPartition,int noOfPainters) {
        this.mArray = arrayToPartition;
        this.N  = noOfPainters;
    }

    public int solve() {
        int low = 0;
        int high = findSearchSpaceMax(mArray);
        return _partition(mArray,low,high);
    }

    private int _partition(int[] mArray, int low, int high) {
        int mid;
        while (low<high) {
           mid = (low + high) / 2;

            int painters = getNoOfPainters(mArray, mid);
            boolean validFlag = isValid(painters);

            if (validFlag) {
                high = mid;
                // We need minimum so, go for more better solutions
            } else {
                low = mid+1;
            }
        }
        return low;
    }

    private int getNoOfPainters(int[] mArray, int mid) {
        int painters = 1;
        int timeTaken = 0;
        for (int value : mArray) {
            if(mid < value) {
                return -1;
            }
            if (timeTaken + value <= mid) {
                timeTaken += value;
            } else {
                painters++;
                timeTaken = value;
            }
        }
        return painters;
    }

    private boolean isValid(int painters) {
        return painters <= N && painters!=-1;
    }

    private int findSearchSpaceMax(int[] array) {
        int sum = 0;
        for (int value : array) {
            sum += value;
        }
        return sum;
    }
}
