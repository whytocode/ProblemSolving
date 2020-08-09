package newproblems;

public class RotationCountArray {
    public RotationCountArray() {

    }

    public int rotationCount(int[] array) {
        int start = 0;
        int end = array.length - 1;
        int mid = -1;

        if (array.length == 0) {
            return 0;
        }

        if(array[start] < array[end]) {
            return 0;
        }
        while (start < end) {
            mid = (start + end) / 2;

            if (array[mid] < array[mid - 1]) {
                return mid;
            }
            if (array[mid] > array[mid + 1]) {
                return mid + 1;
            }

            if (array[mid] < array[end]) {
                end = mid - 1;
            } else if (array[mid] > array[start]) {
                start = mid + 1;
            }
        }
        return mid;
    }
}
