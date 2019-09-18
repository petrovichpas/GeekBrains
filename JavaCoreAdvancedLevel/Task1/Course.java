package JavaCoreAdvancedLevel.Task1;

import JavaCoreAdvancedLevel.Task1.competitor.Competitor;
import JavaCoreAdvancedLevel.Task1.obstacle.Obstacle;

public class Course {
    private Obstacle[] obstacles;

    public Course(Obstacle ... obstacles) {
        this.obstacles = obstacles;
    }

    public Obstacle[] getObstacles() {
        return obstacles;
    }

    public void doIt(Team team) {
        for (Obstacle o : obstacles){
            for (Competitor c : team.getCompetitors()){
                o.doIt(c);
            }
        }
        System.out.println("_______________________________________________________");
    }
}
