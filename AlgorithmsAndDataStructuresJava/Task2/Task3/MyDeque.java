package AlgorithmsAndDataStructuresJava.Task3;

import java.util.EmptyStackException;

public class MyDeque<T> {
    private T[] list;
    private int size = 0;
    private int begin;
    private int end = 0;

    public MyDeque(int capacity) {
        if (capacity <= 0) throw new IllegalArgumentException("capacity " + capacity);
        list = (T[]) new Comparable[capacity];
        begin = list.length;
    }

    public MyDeque() {
        list = (T[]) new Comparable[10];
        begin = list.length;
    }

    public void insertLeft(T item) {
        if (isFull()) throw new StackOverflowError();
        list[end] = item;
        size++;
        end = (end + 1) % list.length;
    }

    public void insertRight(T item) {
        if (isFull()) throw new StackOverflowError();
        begin--;
        list[begin] = item;
        size++;
    }


    public T removeLeft() {
        T temp = peekRight();
        size--;
        list[end-1] = null;
        end--;
        return temp;
    }

    public T removeRight() {
        T temp = peekLeft();
        size--;
        list[begin] = null;
        begin = (begin + 1) % list.length;
        return temp;
    }

    public T peekRight() {
        if (isEmpty()) throw new EmptyStackException();
        if (end - 1 < 0) end = list.length;
        return list[end-1];
    }

    public T peekLeft() {
        if (isEmpty()) throw new EmptyStackException();
        return list[begin];
    }

    public boolean isFull() {
        return size == list.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
