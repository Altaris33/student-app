package enums;

public enum ClassYear {

    // TODO : check classes in the US school system
    FIRST_GRADE("1st grade"),
    SECOND_GRADE("2nd grade"),
    THIRD_GRADE("3rd grade"),
    FOURTH_GRADE("4th grade"),
    FIFTH_GRADE("5th grade"),
    SIXTH_GRADE("6th grade"),
    SEVENTH_GRADE("7th grade"),
    EIGHTH_GRADE("8th grade"),
    NINTH_GRADE("9th grade"),
    TENTH_GRADE("10th grade"),
    ELEVENTH_GRADE("11th grade"),
    TWELFTH_GRADE("12th grade");

    private final String grade;

    ClassYear(String grade) {
        this.grade = grade;
    }

    public String getGrade() {
        return this.grade;
    }

    public static ClassYear fromString(String grade) {
        for(ClassYear classYear: ClassYear.values()) {
            if (classYear.grade.equalsIgnoreCase(grade)) {
                return classYear;
            }
        }
        throw new IllegalArgumentException("No constant class year : " + grade + " found.");
    }

    public static String[] getAll() {
        return new String[ClassYear.values().length];
    }

}
