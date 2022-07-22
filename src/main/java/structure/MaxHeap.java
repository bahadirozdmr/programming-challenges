package structure;

public class MaxHeap {
    private int[] Heap;
    private int size;

    public MaxHeap(int maxsize) {
        this.size=0;
        this.Heap=new int[maxsize];
    }

    public void insert(int value) {
        Heap[size]=value;
        int current=size;

    }
}



