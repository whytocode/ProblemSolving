package newproblems;

/**
 * Given a string S and a set of words D, find the longest word in D that is a subsequence of S.
 *
 * Word W is a subsequence of S if some number of characters, possibly zero,
 * can be deleted from S to form W, without reordering the remaining characters.
 *
 * Note: D can appear in any format (list, hash table, prefix tree, etc.
 *
 * For example, given the input of S = "abppplee" and D = {"able", "ale", "apple", "bale", "kangaroo"}
 * the correct output would be "apple"
 *
 * The words "able" and "ale" are both subsequences of S, but they are shorter than "apple".
 * The word "bale" is not a subsequence of S because even though S has all the right letters, they are not in the right order.
 * The word "kangaroo" is the longest word in D, but it isn't a subsequence of S.
 */
public class LongestSubstringPossibleAfterDeletion {

    public LongestSubstringPossibleAfterDeletion() {

    }

    public String getResult(String longSequence, String[] dictionary) {
        int max = Integer.MIN_VALUE;
        String result = "";
        for(String each : dictionary) {
            boolean isPossible = isThereAPossibility(longSequence,each);
            if(isPossible) {
                if(each.length() > max) {
                    max = each.length();
                    result = each;
                }
            }
        }
        return result;
    }

    private boolean isThereAPossibility(String longString, String destinationString) {
        boolean isPossible = false;
        if(longString.length() < destinationString.length()) {
            return false;
        }

        if(longString.equalsIgnoreCase(destinationString)) {
            return true;
        }

       for(int i=longString.length()-1, j=destinationString.length()-1 ; i>=0 && j>=0; ) {
           if(longString.charAt(i) == destinationString.charAt(j)) {
               isPossible = true;
               i--; j--;
           } else {
               i--;
           }
       }
       return isPossible;
    }
}
