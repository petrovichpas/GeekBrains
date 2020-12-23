package Task3;

public class Pong{
    private Main main;

    public Pong(Main main) {
        this.main = main;
    }

    Thread threadPong = new Thread(() -> {
        while (true) {
        try {
            Thread.sleep(500);
            main.say("PONG");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        }
    });

}
