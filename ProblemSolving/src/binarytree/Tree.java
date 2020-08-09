package binarytree;

import java.util.ArrayDeque;
import java.util.Queue;

public class Tree {

    private Node root = null;
    private Queue<Node> queue = new ArrayDeque<>();
    private Node toBeTraversed;
    private Node _previous;

    public Node getRoot() {
        return root;
    }

    public Tree(Node root) {
        this.root = root;
    }

    public Tree() {

    }
    private Node createNode(int value) {
        return new Node(value);
    }

    public void insert(int value) {
        root = _insert(root,value);
    }

    private Node _insert(Node node, int item) {
        if(node == null) {
            node = createNode(item);
            return node;
        }

        if(item < node.val) {
            node.left = _insert(node.left,item);
        } else {
            node.right = _insert(node.right,item);
        }

        return node;
    }


    public void doInorder() {
        _doInOrder(root);
        print("\n");
    }

    private void _doInOrder(Node root) {
        if(root == null) {
            return;
        }
        _doInOrder(root.left);
        print(root.val);
        print(" ");
        _doInOrder(root.right);
    }


    public void doPreOrder() {
        _doPreOrder(root);
        print("\n");
    }

    private void _doPreOrder(Node root) {
        if(root == null) {
            return;
        }

        print(root.val);
        print(" ");
        _doPreOrder(root.left);
        _doPreOrder(root.right);
    }

    public void doPostOrder() {
        _doPostOrder(root);
        print("\n");
    }

    private void _doPostOrder(Node root) {
        if(root == null) {
            return;
        }

        _doPreOrder(root.left);
        _doPreOrder(root.right);
        print(root.val);
        print(" ");
    }

    public void doBreadthOrder() {
        doBreadthTraversal(root);
    }
    private void doBreadthTraversal(Node root) {
        Queue<Node> queue = new ArrayDeque<>();
        Node temp = root;
        while(temp!=null) {
            print(temp.val+" ");
            if(temp.left!=null)
                 queue.add(temp.left);
            if(temp.right!=null)
                 queue.add(temp.right);
            temp = queue.poll();
        }
    }

    public void delete(int val) {
        Node res = delete(root,val);
        if(res == null) {
            println("Item not found");
        }
    }
    private Node delete(Node node,int val) {
        if(node == null) {
            return null;
        }

        if(val < node.val) {
            node.left = delete(node.left,val);
        } else if(val > node.val) {
            node.right = delete(node.right,val);
        }
        else {
        {
                // node with only one child or no child
                if ((node.left == null) || (node.right == null))
                {
                    Node temp = null;
                    if (temp == node.left)
                        temp = node.right;
                    else
                        temp = node.left;

                    // No child case
                    if (temp == null)
                    {
                        temp = root;
                        node = null;
                    }
                    else // One child case
                        node = temp; // Copy the contents of
                    // the non-empty child
                }
                else
                {

                    // node with two children: Get the inorder
                    // successor (smallest in the right subtree)
                    int temp = getInOrderSuccessor(root.right);

                    // Copy the inorder successor's data to this node
                    node.val = temp;

                    // Delete the inorder successor
                    root.right = delete(root.right, temp);
                }

            }
        }

        return node;
    }

    private int getInOrderSuccessor(Node node) {
        int val = node.val;
        while(node.left!=null) {
            val = node.left.val;
            node = node.left;
        }
        return val;
    }
    private boolean isLeaf(Node node) {
        return node.right ==  null && node.left == null;
    }

    private void _swap(Node a,Node b) {
        int temp = a.val;
        a.val = b.val;
        b.val = temp;
    }
    private void println(int s) {
        System.out.println(wrap(s));
    }

    private void print(String s) {
        System.out.print(s);
    }

    private void print(int s) {
        System.out.print(wrap(s));
    }

    private void println(String s) {
        System.out.print(s);
    }

    private String wrap(int val) {
        return String.valueOf(val);
    }


    public int height(Node node) {
        if(node == null) {
            return -1;
        }
        return 1 + max(height(node.left),height(node.right));
    }

    public boolean isBalanced() {
        return _isBalanced(root);
    }
    private boolean _isBalanced(Node node) {

        if(node == null) {
            return true;
        }
        return _isBalanced(node.left) && _isBalanced(node.right) && modSub(height(node.left),height(node.right)) <= 1;
    }

    private int modSub(int a, int b) {
        int result = a-b;
        if(result < 0) {
            result = b-a;
        }
        return result;
    }
    private int max(int a, int b) {
        if(a > b) { return a; }
        else { return b; }
    }

    public Node toDoublyLinkedList() {
        Node node = _convertToDoublyLinkedList(root);
        Node result = node;
        while(node!=null) {
            result = node;
            node = node.left;
        }
        return result;
    }

    private Node _convertToDLL(Node node) {
        if(node == null) {
            return null;
        }

        if(node.left != null) {
            // If left subtree exists.

            Node left = _convertToDLL(node.left);
            while(left.right!=null) {
                left = left.right;
            }

            left.right = node;
            node.left = left;
        }

        if(node.right != null) {
            Node right = _convertToDLL(node.right);
            while(right.left!=null) {
                right = right.left;
            }

            right.left = node;
            node.right = right;
        }

        return node;
    }

    public static class Node {
        public int val;
        public Node left;
        public Node right;

        public Node(int value) {
            this.val = value;
            this.left = null;
            this.right = null;
        }
    }

    public interface ForwardIterator {
        Node next();
        boolean hasNext();
    }

    public class BreadthIterator implements ForwardIterator {

        public BreadthIterator(Node root) {
            toBeTraversed = root;
        }

        @Override
        public Node next() {
            Node temp = toBeTraversed;
            if(temp.left!=null) {
                queue.add(temp.left);
            } if(temp.right!=null) {
                queue.add(temp.right);
            }
            toBeTraversed = queue.poll();
            return temp;
        }

        @Override
        public boolean hasNext() {
            return toBeTraversed != null;
        }
    }

    public Node convertToDLL2() {
        Node converted = _convertToDLL2(root);
        while (converted.left!=null) {
            converted = converted.left;
        }
        return converted;
    }

    private Node _convertToDLL2(Node node) {

        if(node == null) {
            return null;
        }
        _convertToDLL2(node.left);

        if(_previous!=null) {
            node.left = _previous;
            _previous.right = node;
        }
        _previous = node;
        _convertToDLL2(node.right);
        return node;
    }

    private Node _convertToDoublyLinkedList(Node node) {

        if(node == null) {
            return null;
        }

        if(node.left != null) {
            // If left subtree exists
            Node left = _convertToDoublyLinkedList(node.left);
            // Connect the current node to the previous recursion's consecutive successor
            while(left.right!=null) {
                left = left.right;
            }
            left.right = node;
            node.left = left;
        }

        if(node.right != null) {
            // If right subtree exists
            Node right = _convertToDoublyLinkedList(node.right);
            while(right.left!=null) {
                right = right.left;
            }

            right.left = node;
            node.right = right;
        }

        return node;
    }
}
