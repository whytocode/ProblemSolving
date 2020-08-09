package RevisionIteration.one;

public class ParentedBinaryTree {

    public static final class Node {
        int value;
        Node left, right, parent;

        private Node(int val) {
            this.value = val;
            this.parent = null;
        }
    }

    private Node root;

    /**
     * Return root for the inserted tree
     */
    private Node insert(Node node, int val) {
        if(root == null) {
            root = new Node(val);
            return root;
        }

        if(node == null) {
            return new Node(val);
        }

        if(val >= node.value) {
            node.right = insert(node.right,val);
            node.right.parent = node;
        } else {
            node.left = insert(node.left,val);
            node.left.parent = node;
        }
        return node;
    }

    public void insert(int val) {
         insert(root,val);
    }

    public Node rotateRight() {
        if(root.left == null) {
            System.out.println("Cannot be rotated Right");
        } else {
            Node newRoot = root.left;
            Node orphaned = newRoot.right;

            newRoot.parent = root.parent;
            root.parent = newRoot;

            newRoot.right = root;
            root.left = orphaned;
            if (orphaned != null) {
                orphaned.parent = root;
            }
            root = newRoot;
        }
        return root;
    }

}
