package JavaCoreAdvancedLevel.Task1.obstacle;
import JavaCoreAdvancedLevel.Task1.competitor.Competitor;

public class Cross extends Obstacle {
    int length;

    public Cross(int length) {
        this.length = length;
    }

    @Override
    public void doIt(Competitor competitors) {
            competitors.run(length);
    }
}