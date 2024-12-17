import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Vector;

public class Main {
    public static void main(String[] args) throws IOException {
        Vector<record_book> record_books = new Vector<>();
        ReadStudents("students.txt", record_books);
        ReadSubject("subject1.txt", record_books);
        ReadSubject("subject2.txt", record_books);
        ReadSubject("subject3.txt", record_books);
        ReadSubject("subject4.txt", record_books);
        ReadSubject("subject5.txt", record_books);
        ReadSubject("subject6.txt", record_books);
        ReadSubject("subject7.txt", record_books);
        WriteInfo("output.txt", record_books);
        WriteJson("output.txt");
    }

    public static void ReadSubject(String s, Vector<record_book> record_books) throws IOException {
        FileReader fileReader1 = new FileReader(s);
        BufferedReader reader1 = new BufferedReader(fileReader1);
        String str = reader1.readLine();
        StringTokenizer tokenizer = new StringTokenizer(str, " ");
        String subject = tokenizer.nextToken();
        int numofsession = Integer.parseInt(tokenizer.nextToken());
        while (reader1.ready()) {
            String str1 = reader1.readLine();
            StringTokenizer tokenizer1 = new StringTokenizer(str1, " ");
            int stud = Integer.parseInt(tokenizer1.nextToken());
            int mark = Integer.parseInt(tokenizer1.nextToken());
            for (record_book book : record_books) {
                if (book.stud == stud) {
                    book.a.SetSessionAndMark(numofsession, subject, mark);
                }
            }
        }
    }

    public static void ReadStudents(String s, Vector<record_book> record_books) throws IOException {
        FileReader fileReader = new FileReader(s);
        BufferedReader reader = new BufferedReader(fileReader);
        while (reader.ready()) {
            String str = reader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(str, " ");
            record_book a = new record_book(Integer.parseInt(tokenizer.nextToken()), tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()));
            record_books.addElement(a);
        }
    }

    public static void WriteInfo(String file, Vector<record_book> recordBooks) throws IOException {
        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter writer = new BufferedWriter(fileWriter);
        for (record_book record_book : recordBooks) {
            for (Map.Entry<Integer, List<Map<String, Integer>>> sessionEntry : record_book.a.sessions.entrySet()) {
                int sessionNumber = sessionEntry.getKey();
                List<Map<String, Integer>> marksList = sessionEntry.getValue();
                boolean excellent = true;
                for (Map<String, Integer> map : marksList) {
                    for (int i : map.values()) {
                        if (i < 9) {
                            excellent = false;
                        }
                    }
                }
                if (excellent) {
                    for (Map<String, Integer> map : marksList) {
                        for (Map.Entry<String, Integer> entry : map.entrySet()) {
                            String subject = entry.getKey();
                            int mark = entry.getValue();
                            writer.write(record_book.surname + " " + record_book.name + " " + record_book.patronymic + " " + record_book.course + " " + record_book.group + " " + sessionNumber + " " + subject + " " + mark + "\n");
                        }
                    }
                    writer.flush();
                }
            }
        }
    }
    
    public static void WriteJson(String s) throws IOException {
        FileReader fileReader = new FileReader(s);
        BufferedReader reader = new BufferedReader(fileReader);
        FileWriter fileWriter = new FileWriter("output.json");
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        while (reader.ready()) {
            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
            excellent_student a = new excellent_student(tokenizer.nextToken(), tokenizer.nextToken(), tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()), Integer.parseInt(tokenizer.nextToken()), tokenizer.nextToken(), Integer.parseInt(tokenizer.nextToken()));
            bufferedWriter.write(a.toString() + '\n');
            bufferedWriter.flush();
        }
    }
}