import java.util.*;

public class record_book {
    int stud = 0;
    String surname = "";
    String name = "";
    String patronymic = "";
    int group = 0;
    int course = 0;
    sessions a = new sessions();
    record_book(int stud, String surname, String name, String patronymic, int group, int course) {
        this.stud = stud;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.group = group;
        this.course = course;
    }
    public class sessions {
        Map<Integer, List<Map<String, Integer>>> sessions = new HashMap<>();
        void SetSessionAndMark(int numofsession, String subject, int mark) {
            List<Map<String, Integer>> list = sessions.getOrDefault(numofsession, new ArrayList<>());
            Map<String, Integer> map = new HashMap<>();
            map.put(subject, mark);
            list.add(map);
            sessions.put(numofsession, list);
        }
    }
}