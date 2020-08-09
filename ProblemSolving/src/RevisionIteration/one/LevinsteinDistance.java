package RevisionIteration.one;



public class LevinsteinDistance {

    public int get(String one, String two, int m, int n) {

        if (m == 0) {
            return n;
        }

        if (n == 0) {
            return m;
        }

        if (one.charAt(m - 1) == two.charAt(n - 1)) {
            return get(one, two, m - 1, n - 1);
        } else {
            // We have to try our additions, deletions and replacement.
            // hor  ro -- ho -> ro
            int addAttempt = get(one, two, m, n - 1);
            int delAttempt = get(one, two, m - 1, n);
            int replace = get(one, two, m - 1, n - 1);

            return 1 + Utility.min(addAttempt, delAttempt, replace);
        }
    }

    /**     "" e  f  g  h
     *  ""
     *  a             0
     *  b
     *  c
     *  d
     */
    public static class Dynamic {

        public int get(String one, String two, int m, int n) {

            // create a solutions matrix.
            int[][] dp = new int[m+1][n+1];

            // converting one -> "" fill first column.
            for(int i = 0; i<m ; i++) {
                dp[i][0] = i;
            }

            // converting "" -> two // fill first row.
            for(int i = 0 ; i<n ; i++) {
                dp[0][i] = i;
            }

            for(int i = 1 ; i<m+1 ; i++) {
                for(int j = 1; j<n+1 ; j++) {
                    if( one.charAt(i-1) == two.charAt(j-1)) {
                        dp[i][j] = dp[i-1][j-1];
                    } else {
                        dp[i][j] =  1 + Utility.min( dp[i-1][j], dp[i][j-1],dp[i-1][j-1]);
                    }
                }
            }

            return dp[m][n];
        }
    }

}
