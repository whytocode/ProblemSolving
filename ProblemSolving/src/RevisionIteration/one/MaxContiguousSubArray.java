package RevisionIteration.one;

public class MaxContiguousSubArray {
    private final int[] input;
    private int max = Integer.MIN_VALUE;

    public MaxContiguousSubArray(int[] subArray) {
        this.input = subArray;
    }

    public int get() {
        int[] maxSum = new int[input.length];
        maxSum[0] = input[0];
        for(int i = 1; i<maxSum.length; i++) {
            maxSum[i] = Math.max( input[i], maxSum[i-1]+input[i]);
            if(maxSum[i] > max) {
                max = maxSum[i];
            }
        }
        return max;
    }

}
