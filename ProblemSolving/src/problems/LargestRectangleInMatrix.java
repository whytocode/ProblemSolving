package problems;

public class LargestRectangleInMatrix {

    private int[][] MATRIX;
    private int largestArea = -1;
    public LargestRectangleInMatrix() {
        MATRIX = new int[][] {
                {0, 1, 1, 0},
                {1, 1, 1, 1},
                {1, 1, 1, 1},
                {1, 1, 0, 0}
        };
    }

    public int findMaxArea() {
        for(int i = 0 ; i<4 ; i++) {
            for(int j = 0 ; j<4; j++) {
                int l=0,b=0;
                if(MATRIX[i][j] == 1) {
                    // Calculate the horizontal spread.
                    int h = j;
                    while (h < 4 && MATRIX[i][h] == 1) {
                        h++;
                        l++;
                    }
                    int x = i;
                    // Calculate the vertical spread.
                    while (x< 4 && MATRIX[x][i] == 1) {
                        x++;
                        b++;
                    }
                    if (l == 1 && b == 1) {
                        continue;
                    }

                    if (l == 1 && b > 1) {
                        largestArea = b;
                    } else if (b == 1 && l > 1) {
                        largestArea = l;
                    } else {
                        for (int k = i; k < i + b; k++) {
                            int temp = 0;
                            for (int m = j; m < j + l; m++) {
                                if (MATRIX[k][m] == 1) {
                                    temp++;
                                }
                            }
                            if (temp < l) {
                                l = temp;
                            }
                        }
                        if(largestArea < l*b) {
                            largestArea = l * b;
                        }
                    }
                }
            }
        }
        return largestArea;
    }
}
