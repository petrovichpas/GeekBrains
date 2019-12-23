package AlgorithmsAndDataStructuresJava.Task3;

import java.util.EmptyStackException;

public class MyQueue<T> {

    private T[] list;
    private int size = 0;
    private int begin = 0;
    private int end = 0;

    public MyQueue(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity: " + capacity);
        list = (T[]) new Object[capacity];
    }

    public MyQueue() {
        list = (T[]) new Object[10];
    }

    public void insert(T value) {
        if (isFull()) throw new StackOverflowError();
        list[end] = value;
        size++;
        end = nextIndex(end);
    }

    public T remove() {
        T temp = peekFront();
        size--;
        list[begin] = null;
        begin = nextIndex(begin);
        return temp;
    }

    public T peekFront() {
        if (isEmpty()) throw new EmptyStackException();
        return list[begin];
    }

    private int nextIndex(int index) {
        return (index + 1) % list.length;
    }

    public boolean isFull() {
        return size == list.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
