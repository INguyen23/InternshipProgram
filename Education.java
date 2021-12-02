import java.util.ArrayList;

public class Education {
    private String school;
    private String major;
    private String minor;
    private double gpa;
    private int graduationYear;
    private String classStanding;
    private ArrayList<String> courseWork;

    public Education(String school, double gpa, int graduationYear, String classStanding) {
        this.school = school;
        this.gpa = gpa;
        this.graduationYear = graduationYear;
        this.classStanding = classStanding;
        courseWork = new ArrayList<String>();
        courseWork.add(0, "n/a");
    }

    //  contructor for JSON
    public Education(String school, String major, String minor, double gpa, int graduationYear, String classStanding, ArrayList<String> courseWork) { 
        this.school = school;
        this.major = major;
        this.minor = minor; 
        this.gpa = gpa; 
        this.graduationYear = graduationYear; 
        this.classStanding = classStanding;
        this.courseWork = courseWork;
    }

    /**
     * gets the school student attends
     * @return the school
     */
    public String getSchool() {
        return school;
    }

    /**
     * sets school student attends
     * @param school the school
     */
    public void setSchool(String school) {
        this.school = school;
    }

    /**
     * gets students graduation year
     * @return year of graduation
     */
    public int getGraduationYear() {
        return graduationYear;
    }

    /**
     * sets the year of graduation for the sutdent
     * @param graduationYear year of graduation
     */
    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    /**
     * gets students current class standing
     * @return students class standing
     */
    public String getClassStanding() {
        return classStanding;
    }

    /**
     * sets students current class standing
     * @param classStanding students class standing
     */
    public void setClassStanding(String classStanding) {
        this.classStanding = classStanding;
    }

    /**
     * gets students major
     * @return the students major
     */
    public String getMajor() {
        return this.major;
    }

    /**
     * sets students major
     * @param major major of student
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * sets students minor
     * @param minor minor of the student
     */
    public void setMinor(String minor) {
        this.minor = minor;
    }

    /**
     * gets students minor
     * @return students minor
     */
    public String getMinor() {
        return this.minor;
    }

    /**
     * gets students gpa
     * @return students gpa
     */
    public double getGpa() {
        return gpa;
    }

    /**
     * sets a students gpa
     * @param gpa students gpa
     */
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    /**
     * gets students relevant coursdework
     * @return students coursework
     */
    public ArrayList<String> getCoursework() {
        return courseWork;
    }

    /**
     * prints the students relevant coursework to add to resume
     */
    public void printCoursework() {
        System.out.print("Relevant Skills/Coursework: ");
        for (int i = 0; i < courseWork.size() - 1; i++)
            {
                System.out.print(courseWork.get(i) + ", ");
            }
        if(courseWork.size() != 0) {
            System.out.print(courseWork.get(courseWork.size() - 1));
        }
    }

    /**
     * sets students relevant coursework
     * @param courseWork students relevant coursework
     */
    public void setCoursework(ArrayList<String> courseWork) {
        this.courseWork = courseWork;
    }

    /**
     * adds coursework to array of coursework to add to resume
     * @param myClass class that is to be added
     */
    public void addCoursework(String myClass) {
        if (courseWork.get(0).equals("n/a")) {
            courseWork.remove(0);
        }
        courseWork.add(myClass);
    }

    /**
     * removes coursework from an array to remove from resume
     * @param relevantCoursework coursework that is removed
     * @return remove coursework
     */
    public boolean removeCoursework(String relevantCoursework) {
        return courseWork.remove(relevantCoursework);
    }

    /**
     * displays the education to the resume page
     */
    public void displayEducation() {
        System.out.println("School: " + school);
        System.out.println("Expected year of graduation: " + graduationYear);
        System.out.println("GPA: " + gpa);
        System.out.println("Class Standing: " + classStanding);
        System.out.println("Major: " + major);
        System.out.println("Minor: " + minor);
        printCoursework();
    }
}
