package problems;

public class KMPAlgorithm {

    public static class LPS {
        private final char[] _pattern;

        public LPS(String pattern) {
            _pattern = pattern.toCharArray();
        }

        public int[] lps() {
            int[] lps = new int[_pattern.length];
            int len = lps.length;
            int index = 0;
            for(int i = 1; i<len ;) {
                if(_pattern[i] == _pattern[index]) {
                    lps[i] = index + 1;
                    index++; i++;
                } else {
                    if(index != 0) {
                        index = lps[index-1];
                    } else {
                        lps[i] = 0;
                        i++;
                    }
                }
            }
            return lps;
        }
    }
}
