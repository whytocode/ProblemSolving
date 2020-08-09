package problems;

import binarytree.Tree;

import java.util.HashMap;
import java.util.Map;

public class VerticalPrintingTree {
    private Tree tree;
    Map<Tree.Node,Integer> data = new HashMap<>();

    public VerticalPrintingTree() {
        init();
    }

    private void init() {
        tree = new Tree(new Tree.Node(50));
        tree.insert(30);
        tree.insert(70);
        tree.insert(10);
        tree.insert(20);
        tree.insert(60);
        tree.insert(80);
    }

    public void assignWeights() {
        Tree.Node root = tree.getRoot();
        traverseTree(root,0);
     }

    private void traverseTree(Tree.Node root, int parentWeight) {

        if(root == null) {
            return;
        }
        traverseTree(root.left,parentWeight-1);
        System.out.println("Node: "+root.val+": "+parentWeight);
        traverseTree(root.right,parentWeight+1);
    }

}
