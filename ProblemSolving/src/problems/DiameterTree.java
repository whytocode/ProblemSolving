package problems;

import binarytree.Tree;

public class DiameterTree {
    private Tree tree;

    public DiameterTree() {
        initTree();
    }

    private void initTree() {
        tree = new Tree();
        tree.insert(60);
        tree.insert(50);
        tree.insert(80);
        tree.insert(30);
        tree.insert(40);
        tree.insert(20);
        tree.insert(90);
    }


    public int getDiameter() {
        return _getDiameter(tree.getRoot());
    }


    private int _getDiameter(Tree.Node root) {

        if(root == null) {
            return 0;
        }

        return Math.max( 1+ height(root.left) + height(root.right),
                        Math.max(_getDiameter(root.left),
                        _getDiameter(root.right)) );
    }


    private int height(Tree.Node node) {
        if(node == null) {
            return 0;
        }

        return 1+ Math.max(height(node.left),height(node.right));
    }
}
