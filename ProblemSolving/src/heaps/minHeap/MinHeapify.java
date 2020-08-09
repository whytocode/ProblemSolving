package heaps.minHeap;

public class MinHeapify {
    private int[] mArray;

    public MinHeapify(int[] array) {
        mArray = array;
        perform();
    }
    private int[] perform() {
        if(mArray.length == 0) {
            throw new IllegalArgumentException("Provide a valid array of numbers");
        }
        // find the first non-leaf element.
        int k = mArray.length/2 -1;
        // Heapify starting from k -> 0 in reverse manner.
        for(int i = k ; i>=0 ; i--) {
            _minHeapify(mArray,mArray.length,i);
        }
        return mArray;
    }

    private void _minHeapify(int[] array, int N, int position) {
        // All variables represent indices in array.
        int left = 2*position + 1;
        int right = 2* position + 2;
        int min = position;

        if(left < N && array[left] < array[min]) min = left;
        if(right < N && array[right] < array[min]) min = right;

        // Now we know, minimum of the 2 children. Check if it is less than root.
        if(min!=position) {
            swap(array,min, position);
            _minHeapify(array,N, min);
        }
    }

    private void swap(int[] array, int minimum, int root) {
        int temp = array[minimum];
        array[minimum] = array[root];
        array[root] = temp;
    }

    public Integer peek() {
        if(mArray.length <= 0) {
            return null;
        }

        int x;
        while( (x = peek() )!= 0 ) {

        }
        int res = mArray[0];
        // get the last element and move it to the root position, which will remove the root and then we will heapify at root
        mArray[0] = mArray[mArray.length - 1];

        // We copy the old array to a new array with size mArray.length - 1;
        int[] copy = new int[mArray.length - 1];
        System.arraycopy(mArray,0,copy,0,mArray.length-1);
        mArray = copy;
        _minHeapify(mArray,mArray.length,0);
        return res;
    }

    public void insert(int element) {
        int[] copy = new int[mArray.length+1];
        copy[0] = element;
        System.arraycopy(mArray,0,copy,1,mArray.length);
        mArray = copy;
        _minHeapify(mArray,mArray.length,0);
    }

    public void print() {
        if(mArray.length != 0) {
            for(int i : mArray) {
                System.out.print(i+" ");
            }
        }
    }
}
