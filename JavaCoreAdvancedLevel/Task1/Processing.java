package JavaCoreAdvancedLevel.Task1;

import JavaCoreAdvancedLevel.Task1.competitor.Competitor;
import JavaCoreAdvancedLevel.Task1.obstacle.Obstacle;

public class Processing {
    public void start (Competitor[] competitors, Obstacle[] obstacles) {
        for (Competitor c : competitors) {
            for (Obstacle o : obstacles) {
                o.doIt(c);
                if (!c.isOnDistance()) break;
            }
            c.info();
        }
    }
}
