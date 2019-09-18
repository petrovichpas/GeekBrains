package JavaCoreAdvancedLevel.Task1;

import JavaCoreAdvancedLevel.Task1.competitor.Cat;
import JavaCoreAdvancedLevel.Task1.competitor.Dog;
import JavaCoreAdvancedLevel.Task1.competitor.Human;
import JavaCoreAdvancedLevel.Task1.obstacle.Cross;
import JavaCoreAdvancedLevel.Task1.obstacle.Wall;
import JavaCoreAdvancedLevel.Task1.obstacle.Water;

public class Main {
    public static void main(String[] args) {
        Course course = new Course(new Cross(80), new Wall(2), new Water(120));
        Team team = new Team(new Human("Боб"), new Cat("Барсик"), new Dog("Бобик"), new Dog("GAV"));
        course.doIt(team);
        team.showResults();
    }
}
