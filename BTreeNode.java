package cs250.hw4;

public class BTreeNode {
    int[] keys;
    BTreeNode[] children;
    long[] timeStamps;
    int degree;
    int n;
    boolean leaf;

    public BTreeNode(int degree, boolean leaf) {
        this.keys = new int[2 * degree - 1];
        this.degree = degree;
        this.children = new BTreeNode[2 * degree];
        this.n = 0;
        timeStamps[0] = 0;
        this.leaf = leaf;
    }

    public BTreeNode[] getChildren() {
        return children;
    }

    public long[] getTimeStamps() {
        return timeStamps;
    }

    public int[] getKeys() {
        return keys;
    }

    public int getDegree() {
        return degree;
    }

    public void setChildren(BTreeNode[] children) {
        this.children = children;
    }

    public void setTimeStamps(long[] timeStamps) {
        this.timeStamps = timeStamps;
    }

    public void setKeys(int[] keys) {
        this.keys = keys;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }

    public void insertNode(int num){
        
    }

    void insertNonFull(int num) {
        int i = n - 1;
        if (leaf) {
            while (i >= 0 && keys[i] > num) {
                keys[i + 1] = keys[i];
                i--;
            }
            keys[i + 1] = num;
            n++;
        } else {
            while (i >= 0 && keys[i] > num) {
                i--;
            }
            if (children[i + 1].n == 2 * degree - 1) {
                splitChild(i + 1, children[i + 1]);
                if (keys[i + 1] < num) {
                    i++;
                }
            }
            children[i + 1].insertNonFull(num);
        }
    }

    void splitChild(int i, BTreeNode y) {
        BTreeNode z = new BTreeNode(y.degree, y.leaf);
        z.timeStamps[0] = System.nanoTime();
        z.n = degree - 1;
        for (int j = 0; j < degree - 1; j++) {
            z.keys[j] = y.keys[j + degree];
        }
        if (!y.leaf) {
            for (int j = 0; j < degree; j++) {
                z.children[j] = y.children[j + degree];
            }
        }
        y.n = degree - 1;
        for (int j = n; j > i; j--) {
            children[j + 1] = children[j];
        }
        children[i + 1] = z;
        for (int j = n - 1; j >= i; j--) {
            keys[j + 1] = keys[j];
        }
        keys[i] = y.keys[degree - 1];
        n++;
    }
}
