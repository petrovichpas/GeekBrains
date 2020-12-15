
// нет класса Engine, не переопределен метод open, нет слова "implements"
abstract class Car {
    public Engine engine;
    private String color, name;

    public void start() {
        System.out.println("Car starting");
    }

    abstract void open();

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

class LightWeightCar extends Car implements Moveable {

    @Override
    void open() {
        System.out.println("LightWeightCar is open");
    }

    @Override
    public void move() {
        System.out.println("LightWeightCar is moving");
    }
}

class Lorry extends Car implements Moveable, Stopable{

    @Override
    void open() {
        System.out.println("Lorry is open");
    }

    @Override
    public void move() {
        System.out.println("Lorry is moving");
    }

    @Override
    public void stop() {
        System.out.println("Lorry is stop");
    }
}

class Engine{}

interface Moveable {
    void move();
}

interface Stopable {
    void stop();
}
