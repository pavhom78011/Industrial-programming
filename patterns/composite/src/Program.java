
public class Program {
    public static void main(String[] args) {
        Team team = new Team();
        Developer javaDeveloper1 = new JavaDeveloper();
        Developer javaDeveloper2 = new JavaDeveloper();
        Developer cppDeveloper1 = new cppDeveloper();
        team.addDeveloper(javaDeveloper1);
        team.addDeveloper(javaDeveloper2);
        team.addDeveloper(cppDeveloper1);
        team.writeCode();
    }
}