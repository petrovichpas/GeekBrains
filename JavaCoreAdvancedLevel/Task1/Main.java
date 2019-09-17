package JavaCoreAdvancedLevel.Task1;

import JavaCoreAdvancedLevel.Task1.competitor.Cat;
import JavaCoreAdvancedLevel.Task1.competitor.Competitor;
import JavaCoreAdvancedLevel.Task1.competitor.Dog;
import JavaCoreAdvancedLevel.Task1.competitor.Human;
import JavaCoreAdvancedLevel.Task1.obstacle.Cross;
import JavaCoreAdvancedLevel.Task1.obstacle.Obstacle;
import JavaCoreAdvancedLevel.Task1.obstacle.Wall;
import JavaCoreAdvancedLevel.Task1.obstacle.Water;

public class Main {
    public static void main(String[] args) {
        Competitor[] competitors = {new Human("Боб"), new Cat("Барсик"), new Dog("Бобик")};
        Obstacle[] obstacles = {new Cross(80), new Wall(2), new Water(120)};
        new Processing().start(competitors, obstacles);
        //dsgterd
    }
}
