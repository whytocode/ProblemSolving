package AVLTree;

public class AVLTree {
    private Node root;
    public static class Node {
        public int val;
        public Node left;
        public Node right;
        public int height;

        Node(int value) {
            this.val = value;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
    }

    public AVLTree() {

    }

    private void createTree(int val) {
        root = new Node(val);
    }

    public void insert(int val) {
        root= _internalInsert(root, val);
    }

    private Node _internalInsert(Node node, int key) {
        if(node == null) {
            return new Node(key);
        }

        if(key < node.val) {
            node.left = _internalInsert(node.left,key);
        } else if(key > node.val) {
            node.right = _internalInsert(node.right,key);
        } else {
            return node;        // Return the existing node. Do not tryInsert.
        }

        // Now, we need to update the height
        node.height = 1 + Math.max(height(node.left),height(node.right));

        int balance = balance(node);
        System.out.println("Balance for: "+node.val + " is : "+balance);
        if(balance > 1 && key < node.left.val) {
            // Its a left - left
            return rotateRight(node);

        } if(balance > 1 && key > node.left.val) {
            // Left - Right
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        } if(balance < -1 && key < node.right.val) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        } if(balance < -1 && key > node.right.val) {
            return rotateLeft(node);
        }
        return node;
    }

    private int height(Node node) {
        if(node == null) {
            return -1;
        }
        return node.height;
    }

    private int balance(Node node) {
            if(node == null) return 0;

            return height(node.left) - height(node.right);
    }


    private Node rotateRight(Node y) {
        Node x = y.left;
        Node temp = x.right;

        x.right = y;
        y.left = temp;

        y.height = 1 + Math.max(height(y.left),height(y.right));
        x.height = 1 + Math.max(height(x.left),height(x.right));
        return x;
    }


    private Node rotateLeft(Node node) {
        Node newNode = node.right;
        Node temp = newNode.left;

        newNode.left = node;
        node.right = temp;

        node.height = 1 + Math.max(height(node.left),height(node.right));
        newNode.height = 1 + Math.max(height(newNode.left),height(newNode.right));
        return newNode;
    }

    public void test() {
        AVLTree avlTree = new AVLTree();
        avlTree.insert(10);
        avlTree.insert(8);
        avlTree.insert(6);
        avlTree.insert(5);
        avlTree.insert(4);
        avlTree.insert(3);
        avlTree.insert(2);
        avlTree.insert(1);

    }
}
