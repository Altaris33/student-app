package service;

import domain.Student;
import enums.ClassYear;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StudentServiceTest {

    private List<Student> students = createStudents();
    private StudentService studentService;

    @BeforeEach
    public void init() {
        studentService =  new StudentService(students);
    }

    @Test
    @DisplayName("When listing all students, should return all students")
    public void whenListingAllStudents_shouldReturnAllStudents() {
        // GIVEN

        // WHEN
        studentService.listAllStudents();

        // THEN
        assertEquals(4, students.size());
    }

    @Test
    @DisplayName("When no students found, should return no student")
    public void whenNoStudentsFound_studentsShouldReturnNoStudent() {
        // GIVEN
        this.students.clear();

        // WHEN
        studentService.listAllStudents();

        // THEN
        assertEquals(0, students.size());
        assertTrue(students.isEmpty());
    }

    @Test
    @DisplayName("When adding student, should add student and increase students size by 1")
    public void whenAddingStudent_shouldAddStudent() {
        // GIVEN
        // WHEN
        boolean isStudentAdded = studentService.addStudent();

        // THEN
        assertTrue(isStudentAdded);
    }

    @Test
    @DisplayName("When listing only 8th grade students, should filter students")
    public void whenFilteringStudentsByGrade_shouldReturnFilteredStudents() {
        // GIVEN
        // WHEN
        List<Student> students = studentService.filterByGrade();

        // THEN
        assertEquals(2, students.size());
    }

    private List<Student> createStudents() {
        Student captainFalcon = new Student("Falcon", "Captain", 16, ClassYear.TENTH_GRADE, "Male");
        Student yoshi = new Student("", "Yoshi", 14, ClassYear.EIGHTH_GRADE, "Male");
        Student ganondorf = new Student("", "Ganondorf", 14, ClassYear.EIGHTH_GRADE, "Male");
        Student drMario = new Student("Dr", "Mario", 12, ClassYear.SIXTH_GRADE, "Male");
        return new ArrayList<>(Arrays.asList(captainFalcon, yoshi, ganondorf, drMario));
    }
}
