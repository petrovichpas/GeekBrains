package JavaCoreAdvancedLevel.Task1;

import JavaCoreAdvancedLevel.Task1.competitor.Competitor;

public class Team {
    private Competitor[] competitors;

    public Team(Competitor... competitor) {
        this.competitors = competitor;
    }

    public Competitor[] getCompetitors() {
        return competitors;
    }

    public void showResults(){
        for (Competitor c : competitors){
            c.info();
        }
    }
}
