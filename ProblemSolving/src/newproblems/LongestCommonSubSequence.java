package newproblems;

public class LongestCommonSubSequence {

    public LongestCommonSubSequence() {

    }

    public int get(String one, String two, int algorithm) {
        if (algorithm == 0) {
            return _applyAlgorithm(one, two, one.length() - 1, two.length() - 1);
        } else {
            return _applyDP(one, two, one.length(), two.length());
        }
    }

    private int _applyAlgorithm(String one, String two, int m, int n) {

        if (m < 0 || n < 0) {
            return 0;
        }

        if (one.charAt(m) == two.charAt(n)) {
            return 1 + _applyAlgorithm(one, two, m - 1, n - 1);
        } else {
            return Math.max(_applyAlgorithm(one, two, m - 1, n), _applyAlgorithm(one, two, m, n - 1));
        }
    }

    public int _applyDP(String one, String two, int m, int n) {
        int rows = m + 1;
        int cols = n + 1;

        int[][] dp = new int[rows][cols];
        for (int i = 0; i < cols; i++) {
            dp[0][i] = 0;
        }
        for (int i = 0; i < rows; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i < rows; i++) {
            for (int j = 1; j < cols; j++) {
                if(one.charAt(i-1) == two.charAt(j-1)) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        return dp[rows-1][cols-1];
    }
}
