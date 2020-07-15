package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
    private ArrayList<Node> data;
    private HashMap<T, Integer> dataMap;
    private int size;

    private class Node {
        double priority;
        T item;

        private Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }
    }

    public ArrayHeapMinPQ () {
        data = new ArrayList<>();
        dataMap = new HashMap<>();
        size = 0;
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) throw new IllegalArgumentException("The item is already exist.");
        size ++;
        dataMap.put(item, data.size() - 1);
        data.add(new Node(item, priority));
        switchUp(data.size() - 1);
    }

    private void switchUp(int index) {
        if (index < 1)  return;
        int parIndex = (index - 1)/2;
        Node thisNode = data.get(index);
        Node parNode = data.get(parIndex);
        if (parNode.priority <= thisNode.priority) return;
        switchNode(index, parIndex);
        switchUp(parIndex);
    }

    @Override
    public boolean contains(T item) {
        return dataMap.containsKey(item);
    }

    @Override
    public T getSmallest() {
        return data.get(0).item;
    }

    @Override
    public T removeSmallest() {
        if (size == 0) return null;

        T smallest = data.get(0).item;
        data.set(0, data.get(data.size() - 1));
        data.remove(data.size() - 1);
        dataMap.remove(smallest);
        size --;
        switchDown(0);
        return smallest;
    }

    private void switchDown(int index) {
        int chilIndex1 = index * 2 + 1;
        int chilIndex2 = index * 2 + 2;

        //no child
        if (chilIndex1 >= data.size()) {
            return;
        }

        //only one child
        if (chilIndex2 >= data.size()) {
            Node chil1 = data.get(chilIndex1);
            if (chil1.priority < data.get(index).priority) {
                switchNode(index, chilIndex1);
                switchDown(chilIndex1);
                return;
            }
            return;
        }

        //two child
        Node chil1 = data.get(chilIndex1);
        Node chil2= data.get(chilIndex2);
        if (chil1.priority < chil2.priority) {
            if (chil1.priority < data.get(index).priority) {
                switchNode(index, chilIndex1);
                switchDown(chilIndex1);
            }
        }
        if (chil1.priority > chil2.priority) {
            if (chil2.priority < data.get(index).priority) {
                switchNode(index, chilIndex2);
                switchDown(chilIndex2);
            }
        }
        return;
    }

    private void switchNode(int index1, int index2) {
        Node node1 = data.get(index1);
        Node node2 = data.get(index2);

        data.set(index2, node1);
        data.set(index1, node2);

        dataMap.put(node1.item, index2);
        dataMap.put(node2.item, index1);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void changePriority(T item, double priority) {
        if (!dataMap.containsKey(item)) return;
        int index = dataMap.get(item);
        data.get(index).priority = priority;

        //parent
        switchUp(index);

        //children
        switchDown(index);
    }

    public void printNode() {
        T[] heap = (T[]) new Object[data.size() + 1];
        for (int i = 0; i < data.size(); i++) {
            heap[i + 1] = data.get(i).item;
        }

        int depth = ((int) (Math.log(heap.length) / Math.log(2)));
        int level = 0;
        int itemsUntilNext = (int) Math.pow(2, level);
        for (int j = 0; j < depth; j++) {
            System.out.print(" ");
        }

        for (int i = 1; i < heap.length; i++) {
            System.out.printf("%d ", heap[i]);
            if (i == itemsUntilNext) {
                System.out.println();
                level++;
                itemsUntilNext += Math.pow(2, level);
                depth--;
                for (int j = 0; j < depth; j++) {
                    System.out.print(" ");
                }
            }
        }
        System.out.println();
    }
}
