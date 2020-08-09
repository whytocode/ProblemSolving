package problems;

import java.util.Stack;

public class MaxAreaUnderHistogram {
    private final int[] heights;

    public MaxAreaUnderHistogram(int[] input) {
        this.heights = input;
    }

    public int compute() {
        Stack<Integer> stack = new Stack<>();
        int maxArea = 0;

        if(heights.length == 0) return 0;
        if(heights.length == 1) return heights[0];

        int area,i;
        for(i = 0; i< heights.length ; i++) {
            if(i == 0) {
                // push the first element unconditionally
                stack.push(i);
                continue;
            }
            if(heights[i] >= heights[stack.peek()]) {
                // If current element is greater than or equal to the stack top push it in.
                stack.push(i);
            }
            else {
                // If the current element is lower than the stack top, start popping the bars out and
                // calculate the area on each pop.
                while(!stack.empty() && heights[stack.peek()] >= heights[i]) {
                    int top = stack.pop();
                    // if the stack is not empty then calculate the area like this.
                    if(!stack.empty()) {
                        area = heights[top] * (i - stack.peek() -1);
                    } else {
                        area = heights[top] * i;
                    }
                    if(area > maxArea) {
                        maxArea = area;
                    }
                }
                stack.push(i);
            }
        }

        while(!stack.empty()) {
            int top = stack.pop();
            // if the stack is not empty then calculate the area like this.
            if(!stack.empty()) {
                area = heights[top] * (i - stack.peek() -1);
            } else {
                area = heights[top] * i;
            }
            if(area > maxArea) {
                maxArea = area;
            }
        }

        return maxArea;
    }
}
