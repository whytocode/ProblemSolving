package datastructures.heaps;

import datastructures.Utils;

public class MinHeap {
    public static final int[] SAMPLE_INPUT = new int[]{10, 3, 4, 7, 8, 12, 34, 1, 1, 1, 0};

    private static final int DEFAULT_SIZE = 10;

    private int[] _array;
    private int size;
    private int limit;

    public MinHeap(int[] inputArray) {
       if(inputArray.length == 0 ) {
           _array = new int[DEFAULT_SIZE];
           size = DEFAULT_SIZE;
           limit = 0;
       } else {
           _array = inputArray;
           limit = size = _array.length;
           init();
       }
    }

    public MinHeap() {
        this(new int[]{});
    }

    private void init() {
        int k = limit / 2 - 1;

        if(k > 0) {
            for (int i = k; i >= 0; i--) {
                runHeap(i, limit);
            }
        }
    }

    private void runHeap(int index, int N) {
        if(N > 1) {
            int left = 2 * index + 1;
            int right = 2 * index + 2;

            int min = index;
            if (left < N && _array[left] < _array[min]) min = left;
            if (right < N && _array[right] < _array[left]) min = right;

            // If root is not minimum, then swap.
            if (index != min) {
                Utils.Arrays.swap(_array, index, min);
                runHeap(min, N);
            }
        }
    }

    public int[] array() {
        return _array;
    }

    /**
     * Extract the minimum value in the heap.
     * @return min value
     */
    public Integer extractMin() {
        Integer min;
        if(limit > 0) {
            min = _array[0];
            Utils.Arrays.swap(_array, 0, limit - 1);
            limit--;
            runHeap(0, limit);
            return min;
        } else min = null;

        return min;
    }

    /**
     * Adds a new element into the heap and runs heapify.
     */
    public void add(int value) {
        // See if we have space available in the array if not allocate a new array.
        if(!canAccomodate()) {
            // Expand the array to twice its capacity and work.
            ensureCapacity();
        }
        _array[limit] = value;
        fixHeap(limit);
        limit++;
    }

    private void ensureCapacity() {
        // We increase the size to double the current size.
        int newSize = size * 2;
        int[] newSpace = new int[newSize];
        System.arraycopy(_array,0,newSpace,0,size);
        _array = newSpace;
        size = _array.length;
    }

    // FIXME
    private void fixHeap(int modPos) {
        int pIndex = (modPos - 1 ) / 2;
        while( pIndex > 0) {
            if (_array[pIndex] > _array[modPos]) {
                runHeap(pIndex, limit+1);
            }
            pIndex = (pIndex - 1) / 2;
        }
    }

    private boolean canAccomodate() {
        return (limit < size );
    }
}
