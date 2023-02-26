package service;

import domain.Student;
import enums.ClassYear;

import java.util.*;

public class StudentService {

    private List <Student> students;
    private final Scanner scanner = new Scanner(System.in);


    public StudentService(List<Student> students) {
        this.students = students;
    }

    public List<Student> getAllStudents() {
        for(Student student: this.students) {
            System.out.println("Student ID : " + student.getId() +
                    "\t\tlastname : " + student.getLastName() +
                    "\t\tfirstname : " + student.getFirstName());
        }
        return this.students;
    }

    public void askForGeneralOption() {
        int userTries;
        String choice;
        int option = 0;
        boolean keepAsking = true;
        while(keepAsking) {
            System.out.println("=========================================");
            System.out.println("What do you want to do : \n" +
                    "1 - Add a student\n" +
                    "2 - List Students\n" +
                    "3 - List Students (shorthand)\n" +
                    "4 - Delete student\n" +
                    "9 - Exit program\n");
            try {
                option = scanner.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("Invalid option " + option + ".");
                option = 0;
            }
            scanner.nextLine();
            switch (option) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    listAllStudents();
                    break;
                case 3:
                    getAllStudents();
                    break;
                case 4:
                    removeStudent();
                    break;
                case 9:
                    System.out.println("Goodbye");
                    System.exit(0);
                default:
                    boolean continueWorkingFormat;
                    userTries = 4;
                    System.out.println("No valid option passed. Enter a valid option.");
                    do {
                        System.out.println("Do you want to continue working (Tries remaining : " + userTries  +  ") ? (Options : 'Y'/'Yes' | 'N'/'No (Exit)') ==> ");
                        choice = scanner.nextLine();
                        if (choice.equals("y") || choice.equals("Y") || choice.equals("yes") || choice.equals("Yes")) {
                            break;
                        } else if (choice.equals("n") || choice.equals("N") || choice.equals("no") || choice.equals("No")) {
                            continueWorkingFormat = true;
                            keepAsking = false;
                        } else {
                            System.out.println("Wrong input.");
                            --userTries;
                            continueWorkingFormat = false;
                            if (userTries == 0) {
                                System.out.println("No more tries. Circling back to general option menu.");
                                System.out.println();
                            }
                        }
                    }
                    while (!continueWorkingFormat && userTries > 0);
                    break;
            }
        }
        closeAllResources();
    }

    /**
     * Prints a tabular representation of students
     * Each column divider is made of 3 tabulations
     * Ex :
     *  ----------------------------------------------------------------------------------------------------------------------------
     * | StudentLastName1        | StudentFirstName1            | StudentAge1           | StudentGenre1         | StudentClass1     |
     * | StudentLastName2        | StudentFirstName2            | StudentAge2           | StudentGenre2         | StudentClass2     |
     * | StudentLastNameN        | StudentFirstNameN            | StudentAgeN           | StudentGenreN         | StudentClassN     |
     *  ----------------------------------------------------------------------------------------------------------------------------
     */
    public void listAllStudents() {
        // TODO : think of a way of dynamically generating the top and bottom line of the table (adapted to the global length of the Student record)
        System.out.println(" -------------------------------------------------------------------------------------------------------");
        for(Student student: this.students) {
            System.out.format("|%-8s|%-32s|%-32s|%-3d|%-8s|%-16s|%n",
                    student.getId(),
                    student.getFirstName(),
                    student.getLastName(),
                    student.getAge(),
                    student.getGenre(),
                    student.getClassYear());
        }
        System.out.println(" -------------------------------------------------------------------------------------------------------");
    }

    public boolean addStudent() {
        // handle a randomly generated id or auto-increment one
        this.students = askStudentFromUserInput();
        return !students.isEmpty();
    }

    public List<Student> filterByGrade() {
        return null;
    }

    public List<Student> askStudentFromUserInput() {
        int generatedId = 0;
        boolean moreStudentsToAdd = true;
        boolean isGenreValid = false;
        boolean isClassYearValid = false;
        List<Student> students = new ArrayList<>();
        String lastName;
        String firstName;
        int age = 0;
        String genre;
        String grade = "";
        String userChoice;
        ClassYear classYear = null;

        while (moreStudentsToAdd) {
            System.out.println("Add new student ? (Options : Any key for 'Yes' | 'N'/'No') ==> ");
            userChoice = scanner.nextLine();
            if (userChoice.equals("N") || userChoice.equals("No") || userChoice.equals("no") || userChoice.equals("n")) {
                moreStudentsToAdd = false;
            } else {
                Student newStudent = new Student();
                System.out.println("Enter student last name : ");
                lastName = scanner.nextLine();
                System.out.println("Enter student first name : ");
                firstName = scanner.nextLine();

                try {
                    System.out.println("Enter student age : ");
                    age = scanner.nextInt();
                } catch (InputMismatchException ime) {
                    System.out.println(ime.getMessage());
                    System.out.println("Incorrect format : " + age  + ". Please enter a valid age (integer expected) : ");
                    this.scanner.nextLine();
                    age = scanner.nextInt();
                }

                this.scanner.nextLine();
                System.out.println("Enter student genre (FEMALE, MALE) : ");
                genre = scanner.nextLine();
                while (!isGenreValid) {
                    if ((genre.contains("M") || genre.contains("F") || genre.contains("m") || genre.contains("f")
                            || genre.contains("MALE") || genre.contains("FEMALE"))) {
                        System.out.println("Genre " + genre + " successfully added.");
                        isGenreValid = true;
                    } else {
                        System.out.println("Invalid genre :" + genre + ". Please provide either m, f, M, F, Male or Female.");
                        genre = scanner.nextLine();
                        isGenreValid = false;
                    }
                }

                // TODO : handle user input for class year
                // TODO: think about a simpler way to ask user input i.e providing the possibility to enter an int
                // TODO: based on a class year : ex : 6 for 6th grade
                while (!isClassYearValid) {
                    try {
                        System.out.println("Enter student class : ");
                        System.out.println("Available classes are :");
                        displayPossibleClasses();
                        grade = scanner.nextLine();
                        classYear = ClassYear.fromString(grade);
                        for (ClassYear classyear1 : ClassYear.values()) {
                            if (grade.equalsIgnoreCase(classyear1.getGrade())) {
                                isClassYearValid = true;
                                break;
                            }
                        }
                    } catch (IllegalArgumentException iae) {
                        System.out.println(iae.getMessage());
                    }
                }
                newStudent.setLastName(lastName);
                newStudent.setFirstName(firstName);

                if (age != 0) {
                    newStudent.setAge(age);
                }
                newStudent.setGenre(genre);

                if (!(grade.isEmpty() || grade.isBlank())) {
                    newStudent.setClassYear(classYear);
                }

                newStudent.setId(generatedId++);

                students.add(newStudent);
                this.students.addAll(students);

                // TODO : resetting flag to false : in order to prompt for next Student's classYear, to improve
                isClassYearValid = false;
            }
        }
        //scanner.close();
        return students;
    }

    public void removeStudent() {
        // Init to negative value to avoid deleting any existing student (ranging from 0 or 1 to N)
        // TODO: handle case when user enters an ID who does not exist or is already deleted
        int studentId = -1;
        getAllStudents();
        System.out.println("Enter student to remove (Student ID) : ");
        String idToDelete = scanner.nextLine();
        try {
            studentId = Integer.parseInt(idToDelete);
        } catch (NumberFormatException numberFormatException) {
            System.out.println("Invalid ID => " + idToDelete +  ". Student ID must be an integer ranging from below available students");
            getAllStudents();
            // exit current function call in order to avoid passing studentId as -1 at the end (indexOutOfBound)
            // for any case wher user enters something other than an int
            return;
        }
        System.out.println("Student successfully deleted.");
        this.students.remove(studentId);
    }

    private void displayPossibleClasses() {
        for (ClassYear classYear : ClassYear.values()) {
            System.out.println(classYear.getGrade() + ".");
        }
    }

    private void closeAllResources() {
        scanner.close();
    }
}
