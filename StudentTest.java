import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StudentTest {
    private InternshipApplication system;
    private UserList users;
    private InternshipList internships;
    private ArrayList<Student> students;
    private ArrayList<Employer> employers;
    private ArrayList<InternshipListing> listings;
    private Student s;
    private InternshipListing internship;

    @BeforeEach
    public void setup() {
        system = InternshipApplication.getInternshipApplication();
        users = UserList.getUserList();
        users.clear();
        internships = InternshipList.getInstance();
        internships.clear();

        users.addStudent("Aidan", "Christman", "achristman@email.sc.edu", "epsilondk", "703-283-8482");
        users.addEmployer(new Employer("Michael", "Brown", "mbrown@amazon.com", "gamecockFan111", "Amazon", "Good prices, selection, and convenience"));
        internships.addListing(new InternshipListing("Intern", "Google", "remote", "6 weeks", 10, 20, UUID.randomUUID()));

        students = users.getStudents();
        employers = users.getEmployers();

        listings = internships.getInternshipList();
        s = students.get(0);
        internship = listings.get(0);
    }

    @Test
    public void testApply() {
        s.apply(internship);
        assertEquals(internship.getApplicants().get(0), s.getID());
    }

    @Test
    public void testGetAppliedListings() {
        s.apply(internship);
        DataWriter.saveInternshipList();
        ArrayList<InternshipListing> applications = s.getAppliedListings();
        assertEquals(applications.get(0).getID(), s.getID());
    }

    @Test
    public void testGetZeroAppliedListings() {
        ArrayList<InternshipListing> applications = s.getAppliedListings();
        assertEquals(0, applications.size());
    }
}
