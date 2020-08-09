package problems;


import java.util.Objects;

public class MergeKLists {

    public static class ListNode {
        public int val;
        public ListNode next;
        public ListNode(int x) { val = x; }
    }


    ListNode[] arrayNodes = null;

    public ListNode mergeKLists(ListNode[] lists) {
        int finalSize = 0;
        ListNode result = null;
        for(int i = 0; i<lists.length; i++) {
            finalSize += getListSize(lists[i]);
        }

        arrayNodes = new ListNode[finalSize];
        int count = 0;
        for (ListNode list : lists) {
            ListNode head = list;
            while (head != null) {
                arrayNodes[count] = head;
                head = head.next;
                count++;
            }
        }

        // I have a array of ListNodes which I can heapify.
        int N = (arrayNodes.length/2) -1;
        for(int i = N ; i>=0 ;i--) {
            doMinHeapifyAt(i,arrayNodes.length-1);
        }

        // We have a Min heap here.
        // Copy items one by one in the list.
        ListNode newList = extractMin();
        ListNode head = newList;
        for(int i = 0 ; i<finalSize-1 ; i++) {
            if(newList !=null) {
                newList.next = extractMin();
                newList = newList.next;
            }
        }
        return head;
    }

    private int getListSize(ListNode head) {
        int count = 0;
        while(head != null) {
            head = head.next;
            count++;
        }

        return count;
    }

    private void doMinHeapifyAt(int position,int length ) {
        int left = 2*position + 1;
        int right = 2*position + 2;
        int min = position;

        if( left < length && arrayNodes[left].val < arrayNodes[min].val) min = left;
        if( right < length && arrayNodes[right].val < arrayNodes[min].val) min = right;

        if(min != position) {
            swap(arrayNodes,min,position);
            doMinHeapifyAt(min,length);
        }

    }

    private void swap(ListNode[] nodes, int a,int b) {
        ListNode temp = nodes[a];
        nodes[a] = nodes[b];
        nodes[b] = temp;
    }


    private ListNode extractMin() {
        if(arrayNodes.length <= 0) {
            return null;
        }

        ListNode res = arrayNodes[0];
        // get the last element and move it to the root position, which will remove the root            and then we will heapify at root
        arrayNodes[0] = arrayNodes[arrayNodes.length - 1];

        // We copy the old array to a new array with size mArray.length - 1;
        ListNode[] copy = new ListNode[arrayNodes.length - 1];
        System.arraycopy(arrayNodes,0,copy,0,arrayNodes.length-1);
        arrayNodes = copy;
        doMinHeapifyAt(0,arrayNodes.length-1);
        return res;
    }
}