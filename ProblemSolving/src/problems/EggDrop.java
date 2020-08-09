package problems;

public class EggDrop {
    private int minTries = Integer.MAX_VALUE;
    private int[][] tries;
    public EggDrop() {

    }

    public int fromFloor(int floors,int eggs) {

        if(floors == 1 || floors ==0) {
            return floors;
        }

        if(eggs == 1) {
            return floors;
        }



        for(int k = 1; k<=floors; k++) {
            int res = Math.max(fromFloor(k-1, eggs - 1), fromFloor(floors - k, eggs));
            System.out.println(res);
            if(res < minTries){
                minTries = res;
            }
        }
        return minTries+1;
    }

    public int fromFloorDynamic(int floors,int eggs) {
        tries = new int[floors+1][eggs+1];

        // When we have one egg.
        for(int i=1;i<=floors;i++) {
            tries[i][1] = i;
        }

        // When we have 1 or 0 floors we need 1 and 0 tries respectively.
        for(int i = 1; i<=eggs ;i++) {
            tries[0][eggs] = 0;
            tries[1][eggs] = 1;
        }

        // For eggs and floors >=2
        for(int i = 2; i<=floors ; i++) {
            for(int j = 2; j<=eggs; j++) {
                // Say we drop eggs from kth floor, we have 2 possibilities
                tries[i][j] = Integer.MAX_VALUE;
                for(int k = 1; k<=i; k++) {
                    int worstTries = Math.max(tries[k-1][j - 1], tries[i - k][j])+1;
                    tries[i][j] = Math.min(worstTries,tries[i][j]);
                }
            }
        }
        return tries[floors][eggs];
    }
}
