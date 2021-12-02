import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataWriterTest {
    private UserList users = UserList.getUserList();
    private InternshipList internships = InternshipList.getInstance();

    @BeforeEach
    public void setup() {
        users.clear();
        internships.clear();
        DataWriter.saveStudents();
        DataWriter.saveEmployers();
        DataWriter.saveAdministrators();
        DataWriter.saveInternshipList();
    }

    @Test
    public void testSaveStudents() {
        users.addStudent("Lucy", "Davis", "ldavis@email.sc.edu", "oreo81618", "403-989-9438");
        DataWriter.saveStudents();
        assertEquals("Lucy", users.getStudents().get(0).getFirstName());
    }

    @Test
    public void testSaveEmployers() {
        users.addEmployer(new Employer("Michael", "Brown", "mbrown@amazon.com", "gamecockFan111", "Amazon", "Good prices, selection, and convenience"));
        DataWriter.saveEmployers();
        assertEquals("Michael", users.getEmployers().get(0).getFirstName());
    }

    @Test
    public void testSaveAdministrators() {
        users.addAdministrator(new Administrator("Addison", "Ministrator", "aministrator@email.sc.edu", "p@ssword1", "Head of Career Center"));
        DataWriter.saveAdministrators();
        assertEquals("Addison", users.getAdmin().get(0).getFirstName());
    }

    @Test
    public void testSaveInternshipList() {
        internships.addListing(new InternshipListing("Intern", "Google", "remote", "6 weeks", 10, 20, UUID.randomUUID()));
        DataWriter.saveInternshipList();
        assertEquals("Google", internships.getInternshipList().get(0).getCompany());
    }

    @Test
    public void testAddZeroUsers() {
        assertEquals(null, users.getStudents().get(0));
    }

    @Test
    public void testAddNullListing() {
        assertEquals(null, internships.getInternshipList().get(0));
    }
}
