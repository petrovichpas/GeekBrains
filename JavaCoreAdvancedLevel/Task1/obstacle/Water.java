package JavaCoreAdvancedLevel.Task1.obstacle;

import JavaCoreAdvancedLevel.Task1.competitor.Competitor;

public class Water extends Obstacle {
    int length;

    public Water(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitors) {
        competitors.swim(length);
    }
}