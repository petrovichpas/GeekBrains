package AlgorithmsAndDataStructuresJava.Task3;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        reverseString();
    }

    static void reverseString(){
        MyQueue myQueue = new MyQueue();
        MyStack myStack = new MyStack();

        String s = new Scanner(System.in).next();

        // используя очередь
//        for (int i = s.length()-1; i >= 0; i--) {
//            myQueue.insert(s.charAt(i));
//        }
//        for (int i = 0; i < s.length(); i++) {
//            System.out.print(myQueue.remove());
//        }

        // используя стэк
        for (int i = 0; i < s.length(); i++) {
            myStack.push(s.charAt(i));
        }
        for (int i = 0; i < s.length(); i++) {
            System.out.print(myStack.pop());
        }
    }
}
