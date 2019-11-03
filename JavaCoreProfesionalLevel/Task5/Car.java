package JavaCoreProfessionalLevel.Task5;

public class Car {
    private static int id = 0;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.speed = speed;
        id++;
        this.name = "Участник № " + id;

        new Thread(()-> {
            try {
                System.out.println(getName() + " готовится");
                Thread.sleep(500 + (int)(Math.random() * 800));
                System.out.println(getName() + " готов");
                race.getCountDownLatch().countDown();
                race.getCountDownLatch().await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            race.getStages().forEach(rs -> rs.go(this, race));
            race.getCountDownLatch2().countDown();

            if (race.getReentrantLock().tryLock()) {
                race.getReentrantLock().lock();
                try {
                    race.getCountDownLatch2().await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("ПОБЕДИТЕЛЬ ГОНКИ НЕУДЕРЖИМЫЙ: " + getName());
            }
        }).start();
    }
}