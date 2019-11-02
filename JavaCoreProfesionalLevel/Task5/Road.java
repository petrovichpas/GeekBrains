package JavaCoreProfessionalLevel.Task5;

public class Road extends Stage {
    public Road(int length) {
        super.length = length;
        super.description = "Дорога " + length + " метров";
    }

    @Override
    public void go(Car c, Race race) {
        try {
            System.out.println(c.getName() + " приближается к этапу: " + description);
            Thread.sleep(length / c.getSpeed() * 1000);
            System.out.println(c.getName() + " закончил этап: " + description);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
