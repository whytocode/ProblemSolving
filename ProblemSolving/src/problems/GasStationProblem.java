package problems;

import java.util.ArrayDeque;
import java.util.Queue;

public class GasStationProblem {

    private final int[] gasAvailableArray;
    private final int[] gasExpenditureArray;

    public GasStationProblem() {
        gasAvailableArray = new int[]{6, 3, 7}; // +
        gasExpenditureArray = new int[]{4, 6, 3}; // -
    }

    public int getStationIndex() {
        int answer = -1;
        if (gasAvailableArray.length != gasExpenditureArray.length) {
            throw new IllegalArgumentException("Invalid input array provided");
        }

        Queue<Integer> queue = new ArrayDeque<>();
        int residue = 0;
        for (int index = 0; ; ) {
            if (index >= gasAvailableArray.length) {
                index = index % (gasAvailableArray.length - 1);
            }
            if (index == answer) {
                return answer;
            }
            if (residue + gasAvailableArray[index] - gasExpenditureArray[index] >= 0) {
                if (answer == -1) {
                    answer = index;
                    residue = 0;
                }
                residue += gasAvailableArray[index] - gasExpenditureArray[index];
                queue.add(index);
            } else {
                while (!queue.isEmpty()) {
                    queue.poll();
                    answer = -1;
                }
            }

            index++;
        }
    }

}
