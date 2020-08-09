package datastructures;

import datastructures.heaps.MinHeap;

public class Utils {

    public static class Arrays {
        public static void swap(int[] array, int i,int j) {
            if(i >= 0 && j >= 0) {
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        public static void printArray(int[] a) {
            System.out.println(java.util.Arrays.toString(a));
        }
    }

    public static class Heap {
        public static void print(MinHeap a) {
            Integer val;
            while ( (val = a.extractMin()) != null) {
                System.out.println("Value: "+val);
            }
        }
    }
}
