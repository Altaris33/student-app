package domain;

import enums.ClassYear;

public class Student {

    private int id;
    private String lastName;
    private String firstName;
    private int age;
    private ClassYear classYear;
    private String genre;

    public Student() {
    }

    public Student(String lastName, String firstName, int age, ClassYear classYear, String genre) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
        this.classYear = classYear;
        this.genre = genre;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public ClassYear getClassYear() {
        return classYear;
    }

    public void setClassYear(ClassYear classYear) {
        this.classYear = classYear;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
