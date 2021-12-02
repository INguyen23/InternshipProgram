import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InternshipApplicationTest {
    private InternshipApplication system;
    private UserList users;
    private InternshipList internships;
    private ArrayList<Student> students;
    private ArrayList<Employer> employers;
    private ArrayList<Administrator> admins;
    private ArrayList<InternshipListing> listings;

    @BeforeEach
    public void setup() {
        system = InternshipApplication.getInternshipApplication();
        users = UserList.getUserList();
        users.clear();
        internships = InternshipList.getInstance();
        internships.clear();

        users.addStudent("Aidan", "Christman", "achristman@email.sc.edu", "epsilondk", "703-283-8482");
        users.addEmployer(new Employer("Michael", "Brown", "mbrown@amazon.com", "gamecockFan111", "Amazon", "Good prices, selection, and convenience"));
        users.addAdministrator(new Administrator("Addison", "Ministrator", "aministrator@email.sc.edu", "p@ssword1", "Head of Career Center"));
        internships.addListing(new InternshipListing("Intern", "Google", "remote", "6 weeks", 10, 20, UUID.randomUUID()));

        students = users.getStudents();
        employers = users.getEmployers();
        admins = users.getAdmin();
        listings = internships.getInternshipList();
    }

    @Test
    public void testCheckLoginStudent() {
        Student student = (Student)system.checkLogin("achristman@email.sc.edu", "epsilondk", "s");
        assertEquals(student, students.get(0));
    }

    @Test
    public void testCheckLoginStudentFail() {
        Student student = (Student)system.checkLogin("jfdslakj", "jsdfk", "s");
        assertNull(student);
    }

    @Test
    public void testCheckLoginEmployer() {
        Employer employer = (Employer)system.checkLogin("mbrown@amazon.com", "gamecockFan111", "e");
        assertEquals(employer, employers.get(0));
    }

    @Test
    public void testCheckLoginEmployerFail() {
        Employer employer = (Employer)system.checkLogin("sdf", "djfsk", "e");
        assertNull(employer);
    }

    @Test
    public void testCheckLoginAdministrator() {
        Administrator admin = (Administrator)system.checkLogin("aministrator@email.sc.edu", "p@ssword1", "a");
        assertEquals(admin, admins.get(0));
    }

    @Test
    public void testCheckLoginAdministratorFail() {
        Administrator admin = (Administrator)system.checkLogin("dsfj", "jsdfk", "a");
        assertNull(admin);
    }

    @Test
    public void testLoginStudent() {
        boolean login = system.login("achristman@email.sc.edu", "epsilondk", "s");
        assertEquals(true, login);
    }

    @Test
    public void testLoginEmployer() {
        boolean login = system.login("mbrown@amazon.com", "gamecockFan111", "e");
        assertEquals(true, login);
    }

    @Test
    public void testLoginAdministrator() {
        boolean login = system.login("aministrator@email.sc.edu", "p@ssword1", "a");
        assertEquals(true, login);
    }

    @Test
    public void testSearchStudent() {
        Student student = system.searchStudent("Aidan", "Christman");
        assertEquals(student, students.get(0));
    }

    @Test
    public void testLoadApplicants() {
        students.get(0).apply(listings.get(0));
        ArrayList<Student> applicants = system.loadApplicants(listings.get(0));
        assertEquals(students.get(0).getFirstName(), applicants.get(0).getFirstName());
    }


}
