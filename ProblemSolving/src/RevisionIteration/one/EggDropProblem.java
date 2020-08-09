package RevisionIteration.one;

public class EggDropProblem {

    public int getAttempts(int floors, int eggs) {

        if(floors == 1 || floors == 0) {
            return floors;
        }

        if(eggs == 1) {
            return floors;
        }

        int result = Integer.MAX_VALUE;
        for(int k=1; k<=floors ;k++) {
            int attempt = Math.max( getAttempts(k-1,eggs-1), getAttempts(floors-k,eggs));
            if(attempt<result) {
                result = attempt;
            }
        }
        return result+1;
    }


    public int getAttemptsDynamic(int floors,int eggs) {
        int[][] result = new int[floors+1][eggs+1];

        // for 1 egg
        for(int i = 1 ; i<=floors ;i++) {
            result[i][1] = floors;
        }

        // for zero/one floors
        for(int i = 1 ; i<=eggs; i++) {
            result[1][i] = 1;
        }


        for(int k = 2 ; k<=floors; k++) {
            for(int n = 2; n<=eggs;n++) {
                result[k][n] = Integer.MAX_VALUE;
               // let's drop it from the each floor starting from 1 to k
                for(int i = 1; i<=k;i++) {
                    int temp = Math.max( result[i-1][n-1], result[k-i][n]) + 1;
                    result[k][n] = Math.min(temp,result[k][n]);
                }
            }
        }
        return result[floors][eggs];
    }
}
