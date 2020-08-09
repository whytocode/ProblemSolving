package quicksort;


public class Partition {

    public Partition() {}

    public int partition(int[] array,int low, int high) {
        int pivot = array[high];
        int i = low;  // Keeps track of index at which we add an element less than pivot.
        for(int j = low ; j<high ; j++) {
            if(array[j] < pivot) {
                swap(array,i,j);
                i++;
            }
        }
        swap(array,i,high);
        return i;
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }

    public void printArray(int[] a) {
        System.out.println();
        for(int i : a) {
            System.out.print(i+",");
        }
    }
}
