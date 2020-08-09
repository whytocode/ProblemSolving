package RevisionIteration.one;

import binarytree.Tree;

import java.util.ArrayDeque;
import java.util.Queue;

public class LeftViewBinaryTree {
    private Tree tree;
    private int threshold = 0;
    private static final Tree.Node NULL =  new Tree.Node(-1);

    public LeftViewBinaryTree(Tree tree) {
        this.tree = tree;
    }

    public void get(String algorithm) {
        if(algorithm.equalsIgnoreCase("dfs")) {
            leftView(tree.getRoot(), 1);
        } else if(algorithm.equalsIgnoreCase("bfs")){
            leftViewBFS(tree.getRoot());
        } else {
            throw new IllegalArgumentException("No such algorithm");
        }
    }

    private void leftView(Tree.Node root,int height) {
        if(root == null) {
            return;
        }

        if(threshold < height) {
            System.out.println(root.val);
            threshold = height;
        }

        leftView(root.left,height+1);
        leftView(root.right,height+1);


    }

    private void leftViewBFS(Tree.Node tree) {

        Tree.Node prev = NULL;
        Queue<Tree.Node> queue = new ArrayDeque<>();
        if(tree == null) {
            return;
        }

        queue.add(tree);
        queue.add(NULL);

        while(!queue.isEmpty()) {
            Tree.Node data = queue.poll();

            if(prev == NULL && data != NULL) {
                System.out.println(data.val);
            }

            if(data.left != null) {
                queue.add(data.left);
            }
            if(data.right !=null) {
                queue.add(data.right);
            }

            if(queue.peek() == NULL) {
                queue.add(NULL);
                prev = queue.poll();
            } else {
                prev = data;
            }
        }
    }
    public static Tree sample() {
        Tree tree = new Tree();
        tree.insert(40);
        tree.insert(20);
        tree.insert(60);
        tree.insert(3);
        tree.insert(12);
        tree.insert(45);
        tree.insert(35);
        tree.insert(25);
        return tree;
    }
}
