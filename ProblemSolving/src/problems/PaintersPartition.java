package problems;

public class PaintersPartition {

    //private int MIN_TIME = Integer.MAX_VALUE;

    private final int[] boardsLengthArray;
    private final int n;

    public PaintersPartition(int[] args,int painterCount) {
        this.boardsLengthArray = args;
        this.n = painterCount;
    }

    public int getMinTimeRequired() {
        return _partition(boardsLengthArray,boardsLengthArray.length,n);
    }

    private int _partition(int[] boardsLengthArray, int length, int noOfPartitions) {

        // If one board.
        if(length == 1) {
            return boardsLengthArray[0];
        }
        if(noOfPartitions == 1) {
            return sumInRange(boardsLengthArray,0,length-1);
        }
        int MIN_TIME = Integer.MAX_VALUE;

        for(int i = 0; i<length ; i++) {
           int currentMax =  Math.max(_partition(boardsLengthArray, i, noOfPartitions - 1),
                    sumInRange(boardsLengthArray, i, length - 1));
           MIN_TIME = Math.min(MIN_TIME,currentMax);
        }

        return MIN_TIME;
    }

    private int sumInRange(int[] array, int from, int to) {
        int sum = 0;
        for(int i = from ; i<=to ; i++) {
            sum+=array[i];
        }

        return sum;
    }
}
