package Task5;

public class Main {

    static final int size = 10000000;
    static final int h = size / 2;
    float[] arr = new float[size];

    public void oneThread() {
        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();
        for (int i = 0; i < size; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        System.out.println("Время выполнения 1 потока " + (System.currentTimeMillis() - a));
    }

    public void twoThreads() throws InterruptedException {

        float[] a1 = new float[h];
        float[] a2 = new float[h];

        for (int i = 0; i < size; i++) {
            arr[i] = 1;
        }

        long a = System.currentTimeMillis();

        System.arraycopy(arr, 0, a1, 0, h);
        System.arraycopy(arr, h, a2, 0, h);

        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                System.arraycopy(a1, 0, arr, 0, h);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < h; i++) {
                    a2[i] = (float) (a2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
                }
                System.arraycopy(a2, 0, arr, h, h);
            }
        });

        t1.start();
        t2.start();
        t2.join();
        t1.join();
        System.out.println("Время выполнения 2 потоков " + (System.currentTimeMillis() - a));
    }

    public void manyThreads(int numThreads) throws InterruptedException {
        
        if (size % numThreads == 0) {
            int len = size / numThreads;
            Thread[] threads = new Thread[numThreads];

            for (int i = 0; i < size; i++) {
                arr[i] = 1;
            }

            long a = System.currentTimeMillis();

            for (int i = 0; i < numThreads; i++) {
                int q = i;
                threads[i] = new Thread(new Runnable() {
                    @Override
                    public void run() {
                            float[] mas = new float[len];
                            System.arraycopy(arr, q > 0 ? q * len : q, mas, 0, len);

                            for (int j = 0; j < len; j++) {
                                mas[j] = (float) (mas[j] * Math.sin(0.2f + j / 5) * Math.cos(0.2f + j / 5) * Math.cos(0.4f + j / 2));
                            }
                            System.arraycopy(mas, 0, arr, q > 0 ? q * len : q, len);
                    }
                }); threads[i].start();
            }

            for (Thread th : threads) {
                th.join();
            }
            System.out.println("Время выполнения " + numThreads + " потоков " + (System.currentTimeMillis() - a));
        }
        else System.out.println("Введите другое количество потоков");
    }

    public static void main(String[] args) throws InterruptedException {
        Main main = new Main();
        main.oneThread();
        main.twoThreads();
        main.manyThreads(100);
    }
}
