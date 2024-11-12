import java.util.ArrayList;
import java.util.List;

public class StringStruct {
    String name;
    String competition;
    int reward;
    StringStruct (String name, String competition, String reward) {
        this.name = name;
        this.competition = competition;
        this.reward = Integer.parseInt(reward);
    }
    StringStruct () {

    }
    public
    String toString() {
        return name + " " + competition + " " + reward;
    }
    String GetName () {
        return name;
    }
    String GetCompetition () {
        return competition;
    }
    int GetReward () {
        return reward;
    }
}