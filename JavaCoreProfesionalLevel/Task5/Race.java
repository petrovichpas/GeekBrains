package JavaCoreProfessionalLevel.Task5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

public class Race {
    private int cars_count;
    private ArrayList<Stage> stages;
    private Car[] cars;
    private CountDownLatch countDownLatch;
    private CountDownLatch countDownLatch2;
    private Semaphore semaphore;
    private ReentrantLock reentrantLock;
    CyclicBarrier cb = new CyclicBarrier(cars_count);


    public ArrayList<Stage> getStages() {
        return stages;
    }

    public CountDownLatch getCountDownLatch() {
        return countDownLatch;
    }

    public CountDownLatch getCountDownLatch2() {
        return countDownLatch2;
    }

    public Semaphore getSemaphore() {
        return semaphore;
    }

    public ReentrantLock getReentrantLock() {
        return reentrantLock;
    }

    public Race(int cars_count, Stage... stages) {
        this.cars_count = cars_count;
        this.stages = new ArrayList<>(Arrays.asList(stages));
        this.semaphore = new Semaphore(cars_count % 2 == 0 ? cars_count / 2 : (cars_count - 1) / 2);
        this.countDownLatch = new CountDownLatch(cars_count);
        this.countDownLatch2 = new CountDownLatch(cars_count);
        this.reentrantLock = new ReentrantLock();
    }

    public void startRace(){
        cars = new Car[cars_count];
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        for (int i = 0; i < cars_count; i++) {
            cars[i] = new Car(this, 20 + (int) Math.random() * 10);
        }

        try {
            countDownLatch.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        try {
            countDownLatch2.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
