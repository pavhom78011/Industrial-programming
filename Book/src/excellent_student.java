import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import java.io.IOException;

public class excellent_student {
    String surname = "";
    String name = "";
    String patronymic = "";
    int course = 0;
    int group = 0;
    int semestr = 0;
    String subject = "";
    int mark = 0;

    excellent_student () {

    }

    excellent_student (String surname, String name, String patronymic, int course, int group, int semestr, String subject, int mark) {
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.course = course;
        this.group = group;
        this.semestr = semestr;
        this.subject = subject;
        this.mark = mark;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getGroup() {
        return group;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public int getSemestr() {
        return semestr;
    }

    public void setSemestr(int semestr) {
        this.semestr = semestr;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        try { ObjectMapper objectMapper = new ObjectMapper();
            ObjectWriter writer = objectMapper.writerWithDefaultPrettyPrinter();
            return writer.writeValueAsString(this);
        }
        catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}