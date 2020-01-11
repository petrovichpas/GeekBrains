package AlgorithmsAndDataStructuresJava.Task4;

import java.util.ListIterator;

public class Main {
    public static void main(String[] args) {
        MyLinkedStack<String> stack = new MyLinkedStack<>();
        stack.push("Katia");
        stack.push("Luba");
        stack.push("Luba2");
        while (!stack.isEmpty()) System.out.println(stack.pop());

        MyLinkedList<String> mll = new MyLinkedList<>();
        ListIterator<String> iterator = mll.iterator();
        mll.insertFirst("1");
        mll.insertFirst("2");
        mll.insertFirst("3");
        mll.insertLast("4");
        while(!mll.isEmpty()) iterator.remove();
        System.out.println(mll);
    }
}
