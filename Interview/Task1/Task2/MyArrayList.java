package Task2;

public class MyArrayList<T extends Comparable> {
    private int size = 0;
    private T[] tempArr;

    public MyArrayList() {
        tempArr = (T[]) new Comparable[100];
    }

    public void add(T value) {
        tempArr[size] = value;
        size++;
    }

    public boolean remove(int index) {
        for (int i = index; i < size - 1; i++) {
            tempArr[i] = tempArr[i + 1];
        }

        tempArr[size] = null;
        size--;
        return true;
    }

    public boolean remove(T value) {
        for (int i = 0; i < size; i++) {
            if (tempArr[i].equals(value)) {
                remove(i);
            }
        }
        return true;
    }

    public T get(int index) {
        return tempArr[index];
    }

    public void set(int index, T item) {
        tempArr[index] = item;
    }

    public int indexOf(T value) {
        for (int i = 0; i < size; i++) {
            if (tempArr[i].equals(value)) return i;
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
