import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnionFind {
    private int[] data;
    // TODO - Add instance variables?

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        data = new int[n];
        Arrays.fill(data, -1);
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex > data.length || vertex < 0) {
            throw new  RuntimeException("vertex is not a valid index.");
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        validate(v1);
        int root = find(v1);
        int size = -data[root];
        return size;
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        validate(v1);
        return data[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        int set1 = find(v1);
        int set2 = find(v2);
        if (set1 == set2) {
            return true;
        } else {
            return false;
        }
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        if (connected(v1, v2)) {
            return;
        }
        int setSize1 = sizeOf(v1);
        int setSize2 = sizeOf(v2);
        int rootVertex1 = find(v1);
        int rootVertex2 = find(v2);
        if (setSize1 < setSize2) {
            data[rootVertex2] = -setSize1 + -setSize2;
            data[rootVertex1] = rootVertex2;
            return;
        } else {
            data[rootVertex1] = -setSize1 + -setSize2;
            data[rootVertex2] = rootVertex1;
            return;
        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        validate(vertex);
        List<Integer> children = new ArrayList<Integer>();
        while (parent(vertex) >= 0) {
            children.add(vertex);
            vertex = parent(vertex);
        }
        for (int item: children) {
            data[item] = vertex;
        }
        return vertex;
    }

}
