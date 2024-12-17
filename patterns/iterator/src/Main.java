
public class Main {
    public static void main(String[] args) {
        String[] skills = {"Java", "Spring", "Hibernate", "Maven", "SQL"};
        String[] projects = {"Project A", "Project B", "Project C"};
        JavaDeveloper javaDeveloper = new JavaDeveloper("Steve", skills, projects);
        Iterator iterator = javaDeveloper.getIterator();
        System.out.println("Developer:" + javaDeveloper.getName());
        System.out.println("Skills and Projects:");
        while (iterator.hasNext()) {
            System.out.print(iterator.next().toString() + '\n');
        }
    }
}