package insertionsort;

public class InsertionSort {

    public void sort(int[] array) {
        for(int i = 0; i<array.length ;i++) {
            int temp = array[i];
            int j = i;
            while(j > 0 && temp < array[j - 1]) {
                array[j] = array[j-1];
                j--;
            }
            array[j] = temp;
        }
    }
}
