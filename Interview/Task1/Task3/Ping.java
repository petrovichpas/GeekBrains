package Task3;

public class Ping {
    private Main main;

    public Ping(Main main) {
        this.main = main;
    }

    Thread threadPing = new Thread(() -> {
        while (true) {
            try {
                Thread.sleep(500);
                main.say("PING");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    });




}
