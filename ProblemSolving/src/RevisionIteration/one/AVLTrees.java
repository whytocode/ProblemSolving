package RevisionIteration.one;

import datastructures.Utils;

public class AVLTrees {

    private Node root;

    public static class Node {
        int data;
        Node left, right;
        int height;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.height = 0;
        }
    }

    public void insert(int data) {
        if (this.root == null) {
            root = new Node(data);
        } else {
            root = internalInsert(root, data);
        }
    }

    public void delete(int data) {
        if (this.root == null) {
            return;
        }
        root = internalDelete(root, data);
    }

    private Node internalDelete(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (key < node.data) {
            node.left = internalDelete(node.left, key);
        }
        if (key > node.data) {
            node.right = internalDelete(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                // We take the next inorder successor
                node.data = inOrderSuccessor(node.right);
                node.right = internalDelete(node.right, node.data);
            }
        }

        node.height = 1 + Math.max(height(node.right), height(node.left));

        if (balance(node) > 1 && balance(root.left) >= 0) {
            // left - left.
            // rotate right once.
            return rotateRight(node);
        } else if (balance(node) > 1 && balance(root.left) < 0) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        } else if (balance(node) < -1 && balance(root.right) > 0) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        } else if (balance(node) < -1 && balance(root.right) <= 0) {
            return rotateLeft(node);
        }

        return node;
    }

    private Node internalInsert(Node node, int key) {

        if (node == null) {
            return new Node(key);
        }

        if (key <= node.data) {
            node.left = internalInsert(node.left, key);
        } else {
            node.right = internalInsert(node.right, key);
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        // calculate the balance factor.

        if (balance(node) > 1 && key < node.left.data) {
            // left - left.
            // rotate right once.
            return rotateRight(node);
        } else if (balance(node) > 1 && key > node.left.data) {
            node.left = rotateLeft(node.left);
            return rotateRight(node);
        } else if (balance(node) < -1 && key < node.right.data) {
            node.right = rotateRight(node.right);
            return rotateLeft(node);
        } else if (balance(node) < -1 && key > node.right.data) {
            return rotateLeft(node);
        }

        return node;
    }

    private int inOrderSuccessor(Node it) {
        int val = it.data;
        while(it.left!=null) {
            val = it.left.data;
            it = it.left;
        }
        return val;
    }

    private int height(Node node) {
        if (node == null) {
            return -1;
        } else return node.height;
    }

    private int balance(Node node) {
        if (node == null) return 0;

        return height(node.left) - height(node.right);
    }

    private Node rotateRight(Node node) {
        Node newRoot = node.left;
        Node temp = newRoot.right;
        newRoot.right = node;
        node.left = temp;

        // update heights.
        node.height = 1 + Math.max(height(node.left), height(node.right));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));

        return newRoot;
    }

    private Node rotateLeft(Node node) {
        Node newRoot = node.right;
        Node temp = newRoot.left;
        newRoot.left = node;
        node.right = temp;
        // update heights.
        node.height = 1 + Math.max(height(node.left), height(node.right));
        newRoot.height = 1 + Math.max(height(newRoot.left), height(newRoot.right));
        return newRoot;
    }

    public void test() {
        AVLTrees avlTrees = new AVLTrees();
        avlTrees.insert(10);
        avlTrees.insert(8);
        avlTrees.insert(6);
        avlTrees.insert(5);
        avlTrees.insert(4);
        avlTrees.insert(3);
        avlTrees.insert(2);
        avlTrees.insert(1);
        avlTrees.delete(4);

    }
}
