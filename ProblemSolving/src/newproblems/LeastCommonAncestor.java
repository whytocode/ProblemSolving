package newproblems;

import binarytree.Tree;

import java.util.Objects;

public class LeastCommonAncestor {

    private Tree tree;

    public LeastCommonAncestor() {
       tree = new Tree();
    }

    public Tree.Node lca(int x,int y) {
        return findLCA(tree.getRoot(),x,y);
    }

    private Tree.Node findLCA(Tree.Node node, int x, int y) {
        if(node == null) {
            return null;
        }

        if(node.val == x || node.val == y) {
            return node;
        }

        Tree.Node left = findLCA(node.left,x,y);
        Tree.Node right = findLCA(node.right,x,y);

        if(left !=null && right!=null) {
            return node;
        }

        if(left == null && right == null) {
            return null;
        }

        if(left != null) {
            return left;
        } else return right;
    }

    public void createSample() {
        tree.insert(60);
        tree.insert(40);
        tree.insert(80);
        tree.insert(50);
        tree.insert(70);
        tree.insert(30);
        tree.insert(20);
        tree.insert(10);

    }
}
