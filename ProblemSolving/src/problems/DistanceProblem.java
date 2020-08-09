package problems;

import java.util.Arrays;

/**        m        n
 *      horse -> rose --- hors -> ros ---> hor -> ro  [m,n-1]
 *
 */
public class DistanceProblem {
    private String string1;
    private String string2;

    public DistanceProblem() {}

    public int getMinDistance(String one,String two, int m , int n) {
        // if one is empty then insert all chars in n
        if(m == 0) {
            return n;
        }

        if(n == 0) {
            // remove all in one.
            return m;
        }

        // if both are equal.
        if(one.charAt(m-1) == two.charAt(n-1)) {
            return getMinDistance(one,two,m-1,n-1);
        }

       // If both are not equal then try out each of the operation and see which one gives the minimum
        return 1+ min( getMinDistance(one,two,m,n-1) ,
                        getMinDistance(one,two,m-1,n) ,
                        getMinDistance(one,two,m-1,n-1));
    }

    private int min(int a, int b,int c) {
        if(a <= b && a <= c) {
            return a;
        }
        if(b <= a && b <= c) {
            return b;
        } else  return c;
    }


    public int getMinDistanceDynamic(String word1,String word2) {

       int m = word1.length() + 1;  // columns -> word1
       int n = word2.length() + 1;  // rows.   -> word2

       int[][] dp = new int[n][m];

        for(int j =0 ; j<m ; j++) {
                // Transform the first to "" string
                dp[0][j] = j;
        }

        for(int j =0 ; j<n ; j++) {
            // Transform the second to "" string
            dp[j][0] = j;
        }

        for(int i = 1 ; i<n ; i++) {
            for(int j = 1 ; j<m ; j++) {
                if(word1.charAt(j-1) == word2.charAt(i-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = 1 + min( dp[i-1][j-1] , dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n-1][m-1];

    }

    String printArray(int[][] solution) {
        StringBuilder builder = new StringBuilder();
        for (int[] ints : solution) {
            builder.append("\n[ ");
            for (int j = 0; j < solution.length; j++) {
                builder.append(" ").append(ints[j]);
            }
            builder.append(" ]");
        }
        return builder.toString();
    }
}
