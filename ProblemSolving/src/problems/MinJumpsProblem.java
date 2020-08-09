package problems;

public class MinJumpsProblem {

    private final int[] _input;

    public MinJumpsProblem(int[] arr) {
        _input = arr;
    }

    public int compute() {
        if(_input.length == 0) {
            return 0;
        }
        if(_input.length == 1) {
            return _input[0];
        }

        int[] result = new int[_input.length];

        // process the array in reverse.
        int max = _input.length - 1;
        // fill result with 0s
        for(int i = 0 ; i<max+1 ;i++) {
            result[i] = 0;
        }

        if(_input[max-1] >= 1) {
            result[max - 1] = 1;
        }

        for(int i = _input.length-3; i>=0 ; i--) {
            if(_input[i] > 0) {
                int minJumps = Integer.MAX_VALUE;
               // see if we can reach end directly or min will be 1 + result[i+1]
               boolean flag = ifWeCanReachEndDirectly(i);
               if(flag) {
                   result[i] = 1;
               } else {
                   // We need to make all possible jumps from the current index and see if we have any minimum
                   for(int k = 1 ; k<= _input[i];k++) {
                       if(i+k <= _input.length-1) {
                               minJumps = Math.min(minJumps, 1 + result[i + k]);
                       }
                   }
                   result[i] = minJumps;
               }
            }
            else {
                result[i] = 0;
            }
        }
        return result[0];
    }

    private boolean ifWeCanReachEndDirectly(int i) {
        return _input[i] + i >= _input.length - 1;
    }
}
