package cs250.hw4;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import cs250.hw4.TreeStructure;
import cs250.hw4.BTreeNode;

public class BTree implements TreeStructure {
    BTreeNode root;
    int degree;
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File file = new File(args[0]);
        FileReader fReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fReader);
        TreeStructure tree = new BTree();
        Random rng = new Random(42);
        ArrayList<Integer> nodesToRemove = new ArrayList<>();
        ArrayList<Integer> nodesToGet = new ArrayList<>();
        String line = bufferedReader.readLine();

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
        if (root == null) {
            //System.out.println(degree);
            root = new BTreeNode(degree, true);
            root.keys[0] = num;
            long[] arr = new long[1];
            arr[0] = System.nanoTime();
            root.timeStamps = arr;

        } else {
            if (root.n == 2 * degree - 1) {
                BTreeNode s = new BTreeNode(degree, false);
                s.children[0] = root;
                s.timeStamps[0] = System.nanoTime();
                s.splitChild(0, root);
                int i = 0;
                if (s.keys[0] < num) {
                    i++;
                }
                s.children[i].insertNonFull(num);
                root = s;
            } else {
                root.insertNonFull(num);
            }
        }
    }

    

    public Boolean remove(Integer num){
        return false;
    };
    public Long get(Integer num){
        return -1L;
    };
    public Integer findMaxDepth(){
        return -1;
    };
    public Integer findMinDepth(){
        return -1;
    };
}
