package problems;

import java.util.Arrays;
import java.util.Map;

public class LongestNonDecreasing {
    private int[] array = new int[] {50, 3, 10, 7, 40, 80};

    public LongestNonDecreasing() {

    }

    public int longestSubSequence() {
        int[] result = new int[array.length];
        Arrays.fill(result,1);


        return result[array.length-1];
    }
}
