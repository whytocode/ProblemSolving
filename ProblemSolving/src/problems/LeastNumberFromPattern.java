package problems;

import java.util.PriorityQueue;
import java.util.Stack;

public class LeastNumberFromPattern {

    private char[] pattern;
    private PriorityQueue<Integer> heap = new PriorityQueue<>(9);
    private StringBuilder result = new StringBuilder();
    private int lastBiggest = 0;
    private int startIndex = -1;
    private int lastPrinted = -1;
    public LeastNumberFromPattern(char[] input) {
        this.pattern = input;
        heap.add(9);
        heap.add(7);
        heap.add(3);
        heap.add(1);
        heap.add(2);
        heap.add(5);
        heap.add(4);
        heap.add(8);
        heap.add(6);
    }

   public void printNumber() {
        lastBiggest = 1;
      for(int i = 0 ; i<pattern.length;) {
          if(pattern[i] == 'I') {
              int nI = countNoOfIFrom(i);
              for(int j = 0 ; j<nI ; j++) {
                  if (i == 0 || nI != 1 || i == pattern.length - 1) {
                          print(lastBiggest + j);
                  }
              }
              lastBiggest+=nI;
              i = i+nI;
          }
          else if(pattern[i] == 'D') {
              int nD = countNoOfDFrom(i);
              lastBiggest+=nD;
              for(int j = 0 ; j<=nD ; j++) {
                print(lastBiggest - j);
              }
              i = i+nD;
          }
      }
   }

    public static String ID(String pattern)
    {
        Stack<Integer> stk= new Stack<>();
        StringBuilder res= new StringBuilder();
        for(int i=0;i<=pattern.length();++i)
        {
            stk.push(i+1);
            if(i==pattern.length() || pattern.charAt(i)=='I')
            {
                while(!stk.isEmpty())
                {
                    res.append(stk.pop());
                }
            }
        }

        return res.toString();
    }

    private void print(int x) {
        System.out.print(x);
    }

    private int countNoOfDFrom(int index) {
        int count = 0;
        for(int i = index ; i<=pattern.length -1 && pattern[i]!='I' ; i++) {
            count++;
        }
        return count;
    }

    private int countNoOfIFrom(int index) {
        int count = 0;
        for(int i = index ; i<=pattern.length -1 && pattern[i]!='D' ; i++) {
            count++;
        }
        return count;
    }
    }

