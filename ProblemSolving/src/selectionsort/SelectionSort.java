package selectionsort;

public class SelectionSort {

    public void sort(int[] array) {
        int maxIndex = -1;
        int N = array.length -1;
        for(int i = 0 ; i<N ;i++) {
            int max = Integer.MIN_VALUE;

            for(int j = 0 ; j<=N-i;j++) {
                if(array[j] > max) {
                    maxIndex = j;
                    max = array[j];
                }
            }
            swap(array,maxIndex,N- i);
        }
    }
    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
