package RevisionIteration.one;

/**
 * Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition is a
 * palindrome. For example, “aba|b|bbabb|a|b|aba” is a palindrome partitioning of “ababbbabbababa”.
 * Determine the fewest cuts needed for palindrome partitioning of a given string.
 * For example, minimum 3 cuts are needed for “ababbbabbababa”. The three cuts are “a|babbbab|b|ababa”. If a string is
 * palindrome, then minimum 0 cuts are needed. If a string of length n containing all different characters,
 * then minimum n-1 cuts are needed.
 */
public class MinPalindromicCuts {


    public int minCuts(String input) {
        char[] s = input.toCharArray();
        int[][] dp = new int[s.length][s.length];

        if(isPalindrome(s,0,s.length-1)) {
            return 0;
        }

        // for length = 1
        for (int i = 0; i < s.length; i++) {
            dp[i][i] = 0;
        }

        for (int length = 2; length <= s.length; length++) {
            for (int start = 0; start <= (s.length - length); start++) {
                int end = start + length - 1;
                if (isPalindrome(s, start, end)) {
                    dp[start][end] = 0;
                } else {
                    if (length == 2) {
                        if (s[start] == s[end]) {
                            dp[start][end] = 0;
                        } else {
                            dp[start][end] = 1;
                        }
                    } else {
                        // If length of span >= 3.
                        dp[start][end] = Integer.MAX_VALUE;
                        for (int k = start; k <= end - 1; k++) {
                            dp[start][end] = Math.min(dp[start][end], dp[start][k] + dp[k + 1][end] + 1);
                        }

                    }
                }
            }
        }
        return dp[0][s.length-1];
    }

    private boolean isPalindrome(char[] array, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return false;
        }
        if (startIndex == endIndex) {
            return true;
        }
        if (array[startIndex] == array[endIndex]) {
            return isPalindrome(array, startIndex + 1, endIndex - 1);
        } else {
            return false;
        }
    }

    private int min(int a, int b, int c) {
        int min;
        if (a <= b && a <= c) {
            min = a;
        } else if (b <= a && b <= c) {
            min = b;
        } else {
            min = c;
        }
        return min;
    }
}
