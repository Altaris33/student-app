import domain.Student;
import service.StudentService;

import java.util.ArrayList;
import java.util.List;

public class Application {

    /**
     * Application that returns data regarding Students (no specific establishment for now)
     * Data can be input by :
     * 1. Keyboard
     * 2. Files (CSV files, HTML files, XML files)
     * 3. Interchange formats (JSON)
     * <p>
     * Students can be
     * 1. listed
     * 2. Filtered based on criteria (grade, genre, age)
     * <p>
     * TODO : UPDATE idea : use students to perform statistics (ELK, Kafka...)
     *
     * @param args arguments passed to the program as an array of strings
     */

    public static void main(String[] args) {
        System.out.println("Student Application.");

        List<Student> students = new ArrayList<>();
        StudentService studentService = new StudentService(students);
        studentService.askForGeneralOption();
        students = studentService.getAllStudents();
        System.out.println(students);

        System.out.println("Exiting program");
    }
}
