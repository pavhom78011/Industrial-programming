import java.util.ArrayList;
import java.util.Arrays;

public class Team implements Developer{
    private ArrayList<Developer> developers = new ArrayList<>();
    public Team () {}
    public Team (Team developerss) {
        for (Developer developer : developerss.developers) {
            developers.add(developer);
        }
    }
    public void addDeveloper(Developer developer) {
        developers.add(developer);
    }
    public void removeDeveloper(Developer developer) {
        developers.remove(developer);
    }
    @Override
    public void writeCode() {
        System.out.println("Team creates project...");
        for (Developer developer : developers) {
            developer.writeCode();
        }
    }
}