package Task4;

public class ABC {
    private static ABC abc = new ABC();
    private static char charABC = 'A';

    private static void printABC(char ch) {
        synchronized (abc) {
            try {
                for (int i = 0; i < 5; i++) {
                    while (charABC != ch) {
                        abc.wait();
                    }

                    switch (ch) {
                        case ('A') : System.out.print(ch);
                            charABC = 'B';
                            break;
                        case ('B') : System.out.print(ch);
                            charABC = 'C';
                            break;
                        default : System.out.print(ch + " ");
                        charABC = 'A';
                    }
                    abc.notifyAll();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Thread(() -> printABC('A')).start();
        new Thread(() -> printABC('B')).start();
        new Thread(() -> printABC('C')).start();

    }
}
