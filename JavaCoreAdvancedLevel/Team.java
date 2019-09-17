package JavaCoreAdvancedLevel.Task1;

import JavaCoreAdvancedLevel.Task1.competitor.Cat;
import JavaCoreAdvancedLevel.Task1.competitor.Dog;
import JavaCoreAdvancedLevel.Task1.competitor.Human;

public class Team {
    private Human human;
    private Cat cat;
    private Dog dog;

    public Team(Human h, Cat c, Dog d) {
        this.human = h;
        this.cat = c;
        this.dog = d;
    }

    public Human getHuman() {
        return human;
    }

    public Cat getCat() {
        return cat;
    }

    public Dog getDog() {
        return dog;
    }

    public void showResults(){
        getHuman().info();
        getCat().info();
        getDog().info();
    }
}
