import java.util.ArrayList;

public class UserList {
    private static UserList userList;
    private ArrayList<Student> studentList;
    private ArrayList<Employer> employerList;
    private ArrayList<Administrator> administratorList;

    /**
     * initialize the user list
     */
    private UserList() {
        studentList = new ArrayList<Student>();
        employerList = new ArrayList<Employer>();
        administratorList = new ArrayList<Administrator>();
        studentList = DataLoader.loadStudents();
        employerList = DataLoader.loadEmployers();
        administratorList = DataLoader.loadAdministrators();
    }

    //returns an instance of a user list; singleton design pattern
    public static UserList getUserList() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    /**
     * gets a list of students
     * @return list of students
     */
    public ArrayList<Student> getStudents() {
        return studentList;
    }

    /**
     * gets a list of employers
     * @return list of employers
     */
    public ArrayList<Employer> getEmployers() {
        return employerList;
    }

    /**
     * gets a list of administrators
     * @return list of administrators
     */
    public ArrayList<Administrator> getAdmin() {
        return administratorList;
    }

    /**
     * adds a student to the student list
     * @param firstName students first name
     * @param lastName students last name
     * @param email students email
     * @param password students passwowrd
     * @param phoneNumber students phone number
     */
    public void addStudent(String firstName, String lastName, String email, String password, String phoneNumber) {
        studentList.add(new Student(firstName, lastName, email, password, phoneNumber));
        DataWriter.saveStudents();
    }

    /**
     * adds an employer to the employer list
     * @param employer employer to add to the emoployer list
     */
    public void addEmployer(Employer employer) {
        employerList.add(employer);
        DataWriter.saveEmployers();
    }

    /**
     * add an administrator to the administrator list
     * @param administrator administrator to be added to the admin list
     */
    public void addAdministrator(Administrator administrator) {
        administratorList.add(administrator);
        DataWriter.saveAdministrators();
    }

    /**
     * clears the database by removing all users
     */
    public void clear() {
        studentList = new ArrayList<Student>();
        employerList = new ArrayList<Employer>();
        administratorList = new ArrayList<Administrator>();
        DataWriter.saveStudents();
        DataWriter.saveEmployers();
        DataWriter.saveAdministrators();
    }
}
