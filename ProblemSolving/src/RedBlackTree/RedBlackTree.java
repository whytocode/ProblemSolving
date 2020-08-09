package RedBlackTree;

public class RedBlackTree {
    private Node root;

    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private Node fresherNode;

    public static final class Node {
        int val;
        Node left,right,parent;
        boolean color;

        Node(int val) {
            this.val = val;
        }
    }


    public void insert(int val) {
        root = _insert(root,val);
        root = fixInsertViolation(fresherNode);
    }

    /**
     * This does a basic BST insertion in the tree.
     * @param node Node in which we need to insert the value
     * @param val value that needs to be inserted.
     * @return Node after insertion
     */
    private Node _insert(Node node, int val) {
        if(node == null) {
            Node newNode = new Node(val);
            if(root == null) {
                newNode.color = BLACK;
            } else {
                newNode.color = RED;
            }
            fresherNode = newNode;
            return newNode;
        }

        if(val < node.val) {
            node.left = _insert(node.left,val);
            node.left.parent = node;
        } else if(val > node.val) {
            node.right = _insert(node.right,val);
            node.right.parent = node;
        } else {
            return node;
        }


        return node;
    }


    private Node fixInsertViolation(Node newInsert) {

        if(newInsert.parent!=null && newInsert.parent.color == RED) {
            // If parent color is RED, then break the chain as there can be no RED-RED relationships.
            Node temp = newInsert;

            while(temp!=null && temp.parent!=root && temp!=root) {
                Node sibling = findSibling(temp.parent);
                Node grandPa = temp.parent.parent;

                    if (sibling != null && sibling.color == RED) {
                        // We do the recolor when sibling is present and it is red.
                        temp.parent.color = BLACK;
                        sibling.color = BLACK;
                        grandPa.color = RED;
                    }

                    else if(sibling == null || ( sibling!=null && sibling.color == BLACK) ) {
                        // When sibling is absent or it is black then we do the rotations

                        if(temp.parent.left == temp && grandPa!=null && grandPa.left == temp.parent) {
                            // It is the left-left config, we do a right rotation on the grandpa.
                            Boolean isLeft = isLeftChild(grandPa);
                            if(isLeft == null) {
                                root = rotateRight(grandPa,true);
                            }
                            else if(isLeft) {
                                grandPa.parent.left = rotateRight(grandPa,true);
                            } else {
                                grandPa.parent.right = rotateRight(grandPa,true);
                            }
                        } else if(temp.parent.right == temp && grandPa!=null && grandPa.left == temp.parent) {
                            // It is a left-right configuration, we do a left rotation on the temp without fixing colors and
                            // then we do a right rotate on the grand pa with color fix.
                            grandPa.left = rotateLeft(temp.parent,false);
                            Boolean isLeft = isLeftChild(grandPa);
                            if(isLeft == null) {
                                // When grandpa is the root. It will have no parents then the root needs to shift.
                                root = rotateRight(grandPa,true);
                            }
                            else if(isLeft) {
                                grandPa.parent.left = rotateRight(grandPa,true);
                            } else {
                                grandPa.parent.right = rotateRight(grandPa,true);
                            }
                        } else if(temp.parent.right == temp && grandPa!=null && grandPa.right == temp.parent) {
                            // If becomes a right-right case
                            Boolean isLeft = isLeftChild(grandPa);
                            if(isLeft == null) {
                                root = rotateLeft(grandPa,true);
                            }
                            else if(isLeft) {
                                grandPa.parent.left = rotateLeft(grandPa,true);
                            } else {
                                grandPa.parent.right = rotateLeft(grandPa,true);
                            }
                        } else if(temp.parent.left == temp && grandPa!=null && grandPa.right == temp.parent) {
                            // Right-Left case.
                            grandPa.right = rotateRight(temp.parent,false);
                            Boolean isLeft = isLeftChild(grandPa);
                            if(isLeft == null) {
                                root = rotateLeft(grandPa,true);
                            }
                            else if(isLeft) {
                                grandPa.parent.left = rotateLeft(grandPa,true);
                            } else {
                                grandPa.parent.right = rotateLeft(grandPa,true);
                            }
                        }
                    }
                    temp = temp.parent.parent;
                }
        }
        root.color = BLACK;
        return root;
    }

    private Node findSibling(Node node) {
        Node sibling = null;
        if(node.parent.left != node) {
            sibling = node.parent.left;
        }
        if(node.parent.right != node) {
            sibling = node.parent.right;
        }

        return sibling;
    }

    public Node getRoot() {
        return root;
    }

    private Node rotateRight(Node node,boolean shouldFixColors) {
        Node pOld = node.parent;
        Node newRoot = node.left;
        Node temp = newRoot.right;

        if(temp!=null) {
            temp.parent = node;
        }
        newRoot.right = node;
        newRoot.parent = pOld;  // newRoot parent points to old root parent.

        node.parent = newRoot; // Old root's parent is new root.
        node.left = temp;

        if(shouldFixColors) {
            swapColor(newRoot,node);
        }
        return newRoot;
    }

    private Node rotateLeft(Node node,boolean shouldFixColors) {
        Node pOld = node.parent;
        Node newRoot = node.right;
        Node temp = newRoot.left;

        if(temp!=null) {
            temp.parent = node;
        }
        newRoot.left = node;
        newRoot.parent = pOld;

        node.parent = newRoot;
        node.right = temp;

        if(shouldFixColors) {
            swapColor(node,newRoot);
        }
        return newRoot;
    }

    private void swapColor(Node node, Node newRoot) {
        boolean temp = node.color;
        node.color = newRoot.color;
        newRoot.color = temp;
    }

    private Boolean isLeftChild(Node node) {
        if(node.parent == null) {
            return null;
        }
        return node.parent.left == node;
    }
    
    public void test() {
        insert(5);
        insert(4);
        insert(7);
        insert(2);
        insert(3);
        insert(6);
        insert(9);
        insert(10);     // proper
        insert(13);
        insert(12);
        insert(14);
        insert(11);
    }
}
