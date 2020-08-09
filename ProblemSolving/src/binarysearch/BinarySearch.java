package binarysearch;

public class BinarySearch {
    private final int[] array;
    private int resultIndex = -1;
    public BinarySearch(int[] array) {
        this.array = array;

    }

    public int find(int element) {
        return _binarySearch(array,element,0,array.length - 1);
    }

    private int _binarySearch(int[] array, int element,int start,int end) {
        int mid = (start + end)/2;
        if(start == end ) {
            if(element == array[start]) {
                return start;
            }
            else {
                return -1;
            }
        }
        if(element <= array[mid]) {
           resultIndex =  _binarySearch(array,element,start,mid);
        } else if(element > array[mid]) {
           resultIndex =   _binarySearch(array,element,mid+1,end);
        }

        return resultIndex;
    }

}
