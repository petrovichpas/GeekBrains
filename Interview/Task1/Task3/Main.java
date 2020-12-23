package Task3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private int count = 0;
    private Lock lock = new ReentrantLock();

    // Task1
    public synchronized void say(String s) throws InterruptedException {
        System.out.println(s);
        notify();
        wait();
    }

    //Task2
    public int increment() {
        lock.lock();
        try {
            return ++count;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        // Task1
        Main main = new Main();
        new Ping(main).threadPing.start();
        new Pong(main).threadPong.start();

        //Task2
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                main.increment();
            }
        });
        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 10000; i++) {
                main.increment();
            }
        });

        t1.start();
        t2.start();
        t1.join();
        t2.join();

        System.out.println(main.count);
    }
}
