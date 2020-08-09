package RevisionIteration.one;

public class MinPalidromicSplits {

    public int find(String inputString) {
        char[] input = inputString.toCharArray();
        int size = input.length;
        int[][] solution = new int[size][size];

        // Individual characters are palindrome so, no splits are required.
        for(int i = 0 ; i < size ; i++) {
            solution[i][i] = 0;
        }

        // Consider strings of all possible length
        for(int length = 2 ; length <=size; length++) {
            // End index wrt of length will be: startIndex+length-1
            for(int startIndex=0; startIndex<=(size-length) ; startIndex++) {
                int endIndex = startIndex + length -1;

                // If already a palindrome, 0 splits required.
                if(isPalindrome(input,startIndex,endIndex) ) {
                    solution[startIndex][endIndex] = 0;
                } else {
                    if (length == 2) {
                        // If the length of picked string is 2
                        if (input[startIndex] == input[endIndex]) {
                            solution[startIndex][endIndex] = 0;
                        } else {
                            solution[startIndex][endIndex] = 1;
                        }
                    } else {
                        // If the length is more than 3, we will try to build the solution
                        // We try to put partition starting at startIndex and put it till endIndex -1
                        solution[startIndex][endIndex] = Integer.MAX_VALUE;
                        for (int k = startIndex; k < endIndex - 1; k++) {
                            solution[startIndex][endIndex] = Math.min(solution[startIndex][endIndex],
                                    solution[startIndex][k] + solution[k + 1][endIndex] + 1);
                        }

                    }
                }
            }
        }
        return solution[0][size-1];
    }

    private boolean isPalindrome(char[] array, int startIndex, int endIndex) {
        if(startIndex>endIndex) {
            return false;
        }
        if (startIndex == endIndex) {
            return true;
        }
        if (array[startIndex] == array[endIndex]) {
            return isPalindrome(array, startIndex + 1, endIndex - 1);
        }
        else {
            return false;
        }
    }

    private int min(int a,int b, int c) {
        int min;
        if( a <= b && a<=c) {
            min = a;
        } else if(b<=a && b<=c) {
            min = b;
        } else {
            min = c;
        }
        return min;
    }
}
