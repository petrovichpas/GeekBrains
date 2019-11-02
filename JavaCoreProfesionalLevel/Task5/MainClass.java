package JavaCoreProfessionalLevel.Task5;

public class MainClass {
    public static void main(String[] args) {
        Race race = new Race(4, new Road(60), new Tunnel(), new Road(40));
        race.startRace();
    }
}