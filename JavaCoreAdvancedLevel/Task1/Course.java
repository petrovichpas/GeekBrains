package JavaCoreAdvancedLevel.Task1;

import JavaCoreAdvancedLevel.Task1.obstacle.Cross;
import JavaCoreAdvancedLevel.Task1.obstacle.Wall;
import JavaCoreAdvancedLevel.Task1.obstacle.Water;

public class Course {
    private Cross cross;
    private Wall wall;
    private Water water;

    public Course(Cross cross, Wall wall, Water water) {
        this.cross = cross;
        this.wall = wall;
        this.water = water;
    }

    public Cross getCross() {
        return cross;
    }

    public Wall getWall() {
        return wall;
    }

    public Water getWater() {
        return water;
    }

    public void start (Team team) {
        getCross().doIt(team.getHuman());
        getWall().doIt(team.getHuman());
        getWater().doIt(team.getHuman());

        getCross().doIt(team.getCat());
        getWall().doIt(team.getCat());
        getWater().doIt(team.getCat());

        getCross().doIt(team.getDog());
        getWall().doIt(team.getDog());
        getWater().doIt(team.getDog());
        System.out.println("_______________________________________________________");
    }
}
