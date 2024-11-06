package cs250.hw4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import cs250.hw4.TreeStructure;
import cs250.hw4.TreeNode;

public class BinaryTree implements TreeStructure {
    private static TreeNode root;
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File(args[0]);
        FileReader fReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fReader);
        TreeStructure tree = new BinaryTree();
        Random rng = new Random(42);
        ArrayList<Integer> nodesToRemove = new ArrayList<>();
        ArrayList<Integer> nodesToGet = new ArrayList<>();
        String line = bufferedReader.readLine();

        /*
        tree.insert(6);
        tree.insert(5);
        tree.insert(4);
        tree.insert(7);
        tree.insert(10);
        tree.insert(9);
        tree.insert(20);
        tree.insert(15);
        tree.insert(12);

        
        /* System.out.println(root.getValue()); //6
        System.out.println(root.getLeft().getValue()); //5
        //System.out.println(root.getLeft().getRight().getValue()); //null
        System.out.println(root.getLeft().getLeft().getValue()); //4
        System.out.println(root.getRight().getValue()); //7
        System.out.println(root.getRight().getRight().getValue()); //10
        //System.out.println(root.getRight().getLeft().getValue()); //null
        */
         
        

        //tree.remove(15);
        //System.out.println(root.getRight().getRight().getValue());
        //System.out.println(root.getRight().getRight().getRight().getLeft().getValue()); //15
        //System.out.println(root.getRight().getRight().getRight().getLeft().getLeft().getValue()); //null
        
        
         
        while (line != null) {
            Integer lineInt = Integer.parseInt(line);
            tree.insert(lineInt);
            Integer rand = rng.nextInt(10);
            if (rand < 5) nodesToRemove.add(lineInt);
            else if (rand >= 5) nodesToGet.add(lineInt);
            line = bufferedReader.readLine();
        }

        bufferedReader.close();
        
        for (int i = 0; i < 10; i++) {
            System.out.println(nodesToGet.get(i) + " inserted at " + tree.get(nodesToGet.get(i)));
        }

        System.out.println("Max depth: " + tree.findMaxDepth());
        System.out.println("Min depth: " + tree.findMinDepth());

        for (Integer num : nodesToRemove) {
            tree.remove(num);
        }

        for (int i = 0; i < 10; i++) {
            System.out.println(nodesToGet.get(i) + " inserted at " + tree.get(nodesToGet.get(i)));
        }

            System.out.println("Max depth: " + tree.findMaxDepth());
            System.out.println("Min depth: " + tree.findMinDepth());
            
        
    }

    public void insert(Integer num){
        int nodeValue;
        if (root == null){
            TreeNode newNode = new TreeNode(num, System.nanoTime());
            root = newNode;
            return;
        }
        TreeNode currNode = root;
        while (!currNode.isLeaf()){
            if (currNode.getLeft() == null && num < currNode.getValue()){
                TreeNode newNode = new TreeNode(num, System.nanoTime());
                currNode.setLeft(newNode);
                ///System.out.println(currNode.getLeft().getValue());
                return;
            }
            else if (currNode.getRight() == null && num > currNode.getValue()){
                TreeNode newNode = new TreeNode(num, System.nanoTime());
                currNode.setRight(newNode);
                //System.out.println(currNode.getRight().getValue());
                return;
            }
            else{
                nodeValue = currNode.getValue();
                if (num < nodeValue){
                    currNode = currNode.getLeft();
                }
                else if (num > nodeValue){
                    currNode = currNode.getRight();
                }
            }
        }
        if (num < currNode.getValue()){
            TreeNode newNode = new TreeNode(num, System.nanoTime());
            currNode.setLeft(newNode);
            //System.out.println(currNode.getLeft().getValue());
        }
        else if (num > currNode.getValue()){
            TreeNode newNode = new TreeNode(num, System.nanoTime());
            currNode.setRight(newNode);
            //System.out.println(currNode.getRight().getValue());
        } 
    }

    public Boolean remove(Integer num){
        int nodeValue;
        if (root == null){
            return false;
        }
        TreeNode currNode = root;
        TreeNode prevNode = null;
        nodeValue = root.getValue();
        if (num == currNode.getValue()){
            root = currNode.removeRoot(currNode);
            return true;
        }
        if (num < currNode.getValue() && currNode.getLeft() != null){
            return currNode.removeNode(currNode.getLeft(), currNode, num);
        }
        else if (num > currNode.getValue() && currNode.getRight() != null){
            return currNode.removeNode(currNode.getRight(), currNode, num);
        }
        return false;
    }

    public Long get(Integer num){
        TreeNode node = findNode(num);
        return node.getTimeInsertedAt();
    }

    public Integer findMaxDepth(){
        int maxDepth = -1;
        if (root == null) {
            return 0;
        } 
        TreeNode currNode = root;
        maxDepth = currNode.maxDepth(currNode);
        return maxDepth;
    }

    public Integer findMinDepth(){
        if (root == null) {
            return 0;
        }
        if (root.getLeft() == null && root.getRight() == null) {
            return 1;
        }
        TreeNode currNode = root;
        return currNode.minDepth(currNode);
    }

    public TreeNode findNode(int num){
        int nodeValue;
        if (root == null){
            return null;
        }
        TreeNode currNode = root;
        nodeValue = root.getValue();
        while (!currNode.isLeaf()){
            nodeValue = currNode.getValue();
            if (num < nodeValue){
                currNode = currNode.getLeft();
            }
            else if (num > nodeValue){
                currNode = currNode.getRight();
            }
            else if (num == nodeValue){
                return currNode;
            }
        }
        if (num == nodeValue){
            return currNode;
        }
        else{
            return null;
        }

    }


}