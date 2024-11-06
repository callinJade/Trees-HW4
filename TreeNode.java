package cs250.hw4;

public class TreeNode {
    private int value;
    private long timeInsertedAt;
    private TreeNode left;
    private TreeNode right;

    public TreeNode(int value, long time){
        this.value = value;
        this.timeInsertedAt = time;
        this.left = null;
        this.right = null;
    }

    public TreeNode getLeft() {
        return left;
    }

    public TreeNode getRight() {
        return right;
    }

    public long getTimeInsertedAt() {
        return timeInsertedAt;
    }

    public int getValue() {
        return value;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public void setTimeInsertedAt(long timeInsertedAt) {
        this.timeInsertedAt = timeInsertedAt;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isLeaf(){
        if (left == null && right == null){
            return true;
        }
        return false;
    }

    public void removeLeaf(TreeNode node, TreeNode parent){
        int value = node.getValue();
        if (parent.getLeft() != null){
            if (value == parent.getLeft().getValue()){
                parent.setLeft(null);
            }
        }
        else if (parent.getRight() != null){
            if (value == parent.getRight().getValue()){
                parent.setRight(null);
            }
        }
    }

    public void removeOneChild(TreeNode node, TreeNode parent){
        TreeNode left = node.getLeft();
        TreeNode right = node.getRight();
        int value = node.getValue();
        if (left != null){
            if (parent.getLeft() != null){
                if (value == parent.getLeft().getValue()){
                    parent.setLeft(left);
                    //parent.getLeft().setTimeInsertedAt(left.getTimeInsertedAt());
                }
            }
            else if (parent.getRight() != null){
                if (value == parent.getRight().getValue()){
                    parent.setRight(left);
                    //parent.getRight().setTimeInsertedAt(left.getTimeInsertedAt());
                }
            }            
        }
        else if (right != null){
            if (parent.getLeft() != null){
                if (value == parent.getLeft().getValue()){
                    parent.setLeft(right);
                    //parent.getLeft().setTimeInsertedAt(right.getTimeInsertedAt());
                }
            }
            else if (parent.getRight() != null){
                if (value == parent.getRight().getValue()){
                    parent.setRight(right);
                }
            }
        }
    }

    public void removeTwoChildren(TreeNode node, TreeNode parent){
        TreeNode successor = findSuccessor(node);
        if (parent.getLeft() != null){
            if (node.getValue() == parent.getLeft().getValue()){
                parent.setLeft(successor);
            }
        }
        if (parent.getRight() != null){
            if (node.getValue() == parent.getRight().getValue()){
                parent.setRight(successor);
            }
        }
        
        if (node.getLeft() != null){
            successor.setLeft(node.getLeft());
        }
        if (node.getRight() != null){
            successor.setRight(node.getRight());
        }
        
        //successor.getLeft().setTimeInsertedAt(node.getLeft().getTimeInsertedAt());
        //successor.getLeft().setValue(node.getLeft().getValue());

        
        //successor.getRight().setTimeInsertedAt(node.getRight().getTimeInsertedAt());
        //successor.getRight().setValue(node.getRight().getValue());
        
    }

    public TreeNode removeRoot(TreeNode node){
        TreeNode successor = findSuccessor(node);
        if (node.getLeft() != null){
            successor.setLeft(node.getLeft());
        }
        if (node.getRight() != null){
            successor.setRight(node.getRight());
        }

        return successor;
    }

    public static TreeNode findSuccessor(TreeNode node) {
        int minValue = node.getValue();
        TreeNode parent = node;
        node = node.getRight();
        while (node.getLeft() != null) {
            minValue = node.getLeft().getValue();
            parent = node;
            node = node.getLeft();
        }
        TreeNode successor = node;
        node.removeNode(node, parent, node.getValue());
        return successor;
    }

    public Boolean removeNode(TreeNode node, TreeNode parent, int num){
        /*if (node.getValue() == num){
            node = null;
        }*/

        if (num < node.getValue() && node.getLeft() != null){
            removeNode(node.getLeft(), node, num);
        }
        else if (num > node.getValue() && node.getRight() != null){
            removeNode(node.getRight(), node, num);
        }
        //this is the node to be deleted
        else{
            if (node.getLeft() == null && node.getRight() == null){
                removeLeaf(node, parent);
                return true;
            }
            else if (node.getLeft() == null || node.getRight() == null){
                //System.out.println(node.getValue());
                //System.out.println(parent.getValue());
                removeOneChild(node, parent);
                return true;
            }
            else {
                removeTwoChildren(node, parent);
                return true;
            }
        }
        return false;
    }

    public TreeNode findParent(TreeNode node){
        TreeNode prevNode = node;
        return prevNode;
    }

    public int leftTree(){
        if (left != null){
            left.leftTree();
        }
        return value;
    }

    public int rightTree(){
        if (right != null){
            right.rightTree();
        }
        return value;
    }

    public int minDepth(TreeNode node){
        // Corner case. Should never be hit unless the code is
        // called on root = NULL
        if (node == null)
            return 0;
 
        // Base case : Leaf Node. This accounts for height = 1.
        if (node.getLeft() == null && node.getRight() == null)
            return 1;
 
        // If left subtree is NULL, recur for right subtree
        if (node.getLeft() == null)
            return minDepth(node.getRight()) + 1;
 
        // If right subtree is NULL, recur for left subtree
        if (node.getRight() == null)
            return minDepth(node.getLeft()) + 1;
 
        return Math.min(minDepth(node.getLeft()),
                        minDepth(node.getRight())) + 1;
    }

    public int maxDepth(TreeNode node)
    {
        if (node == null)
            return 0;
        else {
            /* compute the depth of each subtree */
            int lDepth = maxDepth(node.getLeft());
            int rDepth = maxDepth(node.getRight());
 
            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }
    
}
