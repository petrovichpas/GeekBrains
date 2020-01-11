package AlgorithmsAndDataStructuresJava.Task4;

import java.util.NoSuchElementException;

public class MyOneLinkedList<T> {
    private Node first;
    private int size = 0;

    private class Node<T> {
        T value;
        Node next;

        public Node(T value, Node next) {
            this.value = value;
            this.next = next;
        }

        public Node(T value) {
            this.value = value;
        }
    }

    public void insertFirst(T value) {
        Node newNode = new Node(value, first);
        first = newNode;
        size++;
    }

    public T removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node oldFirst = first;
        first = first.next;
        size--;
        return (T) oldFirst.value;
    }

    public void insert(int index, T value) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        if (index == 0) {
            insertFirst(value);
            return;
        }
        Node current = first;
        int i = 0;
        while (i < index - 1) {
            current = current.next;
            i++;
        }
        Node newNode = new Node(value);
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    public boolean remove(T value){
        if(isEmpty()){
            return false;
        }
        if(first.value.equals(value)){
            removeFirst();
            return true;
        }
        Node current = first;
        while(current.next != null && !current.next.value.equals(value)){
            current = current.next;
        }
        if(current.next == null){
            return false;
        }
        current.next = current.next.next;
        size--;
        return true;
    }

    public int indexOf(T value) {
        return index(value);
    }

    private int index(T value) {
        Node current = first;
        int index = 0;
        while (current != null) {
            if (current.value.equals(value)) {
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    public boolean contains(T value) {
        return index(value) > -1;
    }

    public T getFirst() {
        return (T) first.value;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return size;
    }

    @Override
    public String toString() {
        if (isEmpty()) {
            return "[ ]";
        }
        Node current = first;
        StringBuilder sb = new StringBuilder("[ ");

        while (current != null) {
            sb.append(current.value.toString() + ", ");
            current = current.next;
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append(" ]");
        return sb.toString();
    }
}
