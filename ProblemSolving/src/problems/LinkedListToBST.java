package problems;

import binarytree.Tree;

import java.util.LinkedList;
import java.util.List;

public class LinkedListToBST {
    private final List<Integer> linkedList = new LinkedList<>();
    private int head = 0;

    public LinkedListToBST() {
        createLinkedList();
    }

    private void createLinkedList() {
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(30);
        linkedList.add(40);
        linkedList.add(50);
        linkedList.add(60);
//        linkedList.add(70);
//        linkedList.add(80);
    }

    public Tree.Node treeify() {
        int n = linkedList.size();
        return _internalTreeify(n);
    }

    private Tree.Node _internalTreeify(int n) {
        if(n<=0) {
            return null;
        }

        Tree.Node left = _internalTreeify(n/2);

        Tree.Node root = new Tree.Node(linkedList.get(head));
        head++;
        root.left = left;

        root.right = _internalTreeify((n-n/2) - 1); // 1 for root.

        return root;
    }

    public Tree.Node treeify2() {
        return _internalTreeify2(0,linkedList.size()-1);
    }

    private Tree.Node _internalTreeify2(int start,int end) {
        if(end < start) {
            return null;
        }
        int middle = (start + end)/2;
        Tree.Node root = new Tree.Node(linkedList.get(middle));

        root.left = _internalTreeify2(start,middle-1);
        root.right = _internalTreeify2(middle+1,end );
        return root;
    }
}
