package problems;

import java.util.Arrays;

public class Permutations {

    public void permute(String string) {
        _internalPermute(string.toCharArray(),0,string.toCharArray().length-1);
    }

    private void _internalPermute(char[] array,int left,int right) {
        if(left == right) {
           return;
        }

        for(int i=left ;i<=right;i++) {
            swap(array,left,i);
            System.out.print(" "+ Arrays.toString(array));
            //System.out.print(" "+array[left]);
            _internalPermute(array,left+1,right);
            swap(array,left,i);
        }

    }

    private void swap(char[] array,int i,int j) {
        char c = array[i];
        array[i] = array[j];
        array[j] = c;
    }
}
