package AlgorithmsAndDataStructuresJava.Task2;

import java.util.Arrays;

public class Main {
    static int[] a = new int[100000];
    static int[] b = new int[a.length];
    static int[] c = new int[a.length];
    static int[] d = new int[a.length];

    public static void main(String[] args) {
        for (int i = 0; i < a.length; i++) {
            a[i] = (int) (Math.random() * 10);
            b[i] = (int) (Math.random() * 10);
            c[i] = (int) (Math.random() * 10);
            d[i] = (int) (Math.random() * 10);
        }

        bubbleSorter(a);
        insertSorter(b);
        selectSorter(c);

        long t = System.currentTimeMillis();
        Arrays.sort(d);
        long t2 = System.currentTimeMillis();
        System.out.println("Сортировка java.util выполнена за " + (t2 - t) + " миллисекунд.");
    }

    static void swap(int[] arr, int idxA, int indB) {
        int tmp = arr[idxA];
        arr[idxA] = arr[indB];
        arr[indB] = tmp;
    }

    static void bubbleSorter(int[] arr) {
        new Thread(() -> {
            long t = System.currentTimeMillis();
            for (int out = arr.length - 1; out >= 1; out--) {
                for (int in = 0; in < out; in++) {
                    if (arr[in] > arr[in + 1])
                        swap(arr, in, in + 1);
                }
            }
            long t2 = System.currentTimeMillis();
            System.out.println("Сортировка пузырьком выполнена за " + (t2 - t) / 1000 + " секунд.");
        }).start();
    }


    static void insertSorter(int[] arr) {
        new Thread(() -> {
            long t = System.currentTimeMillis();
            for (int i = 1; i < arr.length; i++) {
                int current = arr[i];
                int j = i - 1;
                while (j >= 0 && current < arr[j]) {
                    arr[j + 1] = arr[j];
                    j--;
                }
                arr[j + 1] = current;
            }
            long t2 = System.currentTimeMillis();
            System.out.println("Сортировка вставкой выполнена за " + (t2 - t) / 1000 + " секунд.");
        }).start();
    }

    static void selectSorter(int[] arr) {
        new Thread(() -> {
            long t = System.currentTimeMillis();
            for (int i = 0; i < arr.length; i++) {
                int min = arr[i];
                int minId = i;
                for (int j = i + 1; j < arr.length; j++) {
                    if (arr[j] < min) {
                        min = arr[j];
                        minId = j;
                    }
                }
                swap(arr, arr[i], min);
            }
            long t2 = System.currentTimeMillis();
            System.out.println("Сортировка выбором выполнена за " + (t2 - t) / 1000 + " секунд.");
        }).start();
    }
}