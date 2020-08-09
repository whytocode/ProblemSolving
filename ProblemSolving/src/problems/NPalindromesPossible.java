package problems;

/**
 * Given a string, a partitioning of the string is a palindrome partitioning if every substring of the partition
 * is a palindrome. For example, “aba|b|bbabb|a|b|aba” is a palindrome partitioning of “ababbbabbababa”.
 * Determine the fewest cuts needed for palindrome partitioning of a given string. For example, minimum 3 cuts are
 * needed for “ababbbabbababa”. The three cuts are “a|babbbab|b|ababa”. If a string is palindrome, then minimum 0 cuts
 * are needed. If a string of length n containing all different characters, then minimum n-1 cuts are needed.
 */
public class NPalindromesPossible {

    public NPalindromesPossible() {
    }

    public int minSplits(String arg) {
       char[] copy = arg.toCharArray();
       int n = copy.length;
       int[][] R = new int[n][n];

      // We know that each character is a palindrome. So let's populate the result array
        for(int i=0 ; i<n ; i++) {
            R[i][i] = 0;
        }

        // We will then consider strings of spread l [0,n-1]
        for(int l= 2 ; l<=n ; l++) {
            for(int startIndex = 0; startIndex<=n-l ;startIndex++ ) {
                // Calculate ending index
                int j = startIndex+l-1;
                // We skip single chars as its already populated.
                if(isPalindrome(copy,startIndex,j)) {
                    R[startIndex][j] = 0;
                }
                else {
                    if(l == 2) {
                        if(copy[startIndex] == copy[j]) {
                            R[startIndex][j] = 0;
                        }
                        else {
                            R[startIndex][j] = 1;
                        }
                    } else {
                        // More than  2 char word, check all possible partitions
                        R[startIndex][j] = Integer.MAX_VALUE;
                        for (int k = startIndex; k < j - 1; k++) {
                            R[startIndex][j] = Math.min(R[startIndex][j], 1 + R[startIndex][k] + R[k + 1][j]);
                        }
                    }
                }
            }
        }

        return R[0][n-1];
    }

    private boolean isPalindrome(char[] array, int start, int end) {
        if(start>end) {
            return false;
        }
        if (start == end) {
            return true;
        }
        if (array[start] == array[end]) {
            return isPalindrome(array, start + 1, end - 1);
        }
        else {
            return false;
        }
    }

}
