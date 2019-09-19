package JavaCoreAdvancedLevel.Task1.obstacle;

import JavaCoreAdvancedLevel.Task1.competitor.Competitor;

public class Wall extends Obstacle {
    int height;

    public Wall(int height) {
        this.height = height;
    }

    @Override
    public void doIt(Competitor competitors) {
        competitors.jump(height);
    }
}