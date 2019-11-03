package JavaCoreProfessionalLevel.Task5;

public class Tunnel extends Stage {
    public Tunnel() {
        super.length = 80;
        super.description = "Тоннель " + length + " метров";
    }

    @Override
    public void go(Car car, Race race) {
            try {
                System.out.println(car.getName() + " приближается к этапу " + description);
                race.getSemaphore().acquire();
                System.out.println(car.getName() + " движется по этапу: " + description);
                Thread.sleep(length / car.getSpeed() * 1000);
                System.out.println(car.getName() + " закончил этап: " + description);
                race.getSemaphore().release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
    }
}