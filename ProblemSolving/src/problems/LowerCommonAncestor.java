package problems;

import binarytree.Tree;

public class LowerCommonAncestor {
    private final Tree tree = new Tree();

//    public Tree.Node findLCA(Tree.Node root,int a, int b) {
//
//    }
    private void plantTree() {
        for(int i =1 ; i<=7 ; i++) {
            tree.insert(i);
        }

    }
}
