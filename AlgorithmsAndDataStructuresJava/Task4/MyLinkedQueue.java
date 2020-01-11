package AlgorithmsAndDataStructuresJava.Task4;

public class MyLinkedQueue<T> {
    private MyLinkedList<T> queue = new MyLinkedList<>();

    public int size(){
        return queue.size();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }

    public void enqueue(T value){
        queue.insertFirst(value);
    }

    public T dequeue(){
        return queue.removeLast();
    }

    public T peek(){
        return queue.getLast();
    }
}
