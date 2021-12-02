import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataLoaderTest {
    private UserList users = UserList.getUserList();
    private InternshipList internships = InternshipList.getInstance();

    @BeforeEach
    public void setup() {
        users.clear();
        users.addStudent("Lucy", "Davis", "ldavis@email.sc.edu", "oreo81618", "403-989-9438");
        users.addEmployer(new Employer("Michael", "Brown", "mbrown@amazon.com", "gamecockFan111", "Amazon", "Good prices, selection, and convenience"));
        users.addAdministrator(new Administrator("Addison", "Ministrator", "aministrator@email.sc.edu", "p@ssword1", "Head of Career Center"));
        internships.clear();
        internships.addListing(new InternshipListing("Intern", "Google", "remote", "6 weeks", 10, 20, UUID.randomUUID()));
    }

    @Test
    public void testLoadOneStudent() {
        ArrayList<Student> students = DataLoader.loadStudents();
        assertEquals(1, students.size());
    }

    @Test
    public void testLoadNullStudent() {
        users.clear();
        ArrayList<Student> students = DataLoader.loadStudents();
        assertEquals(0, students.size());
    }

    @Test
    public void testLoadThreeStudents() {
        users.addStudent("Patrick", "Madden", "pmadden@email.sc.edu", "soccerFan123", "803-493-2848");
        users.addStudent("Evan", "Park", "epark@email.sc.edu", "password1234", "571-292-3948");
        ArrayList<Student> students = DataLoader.loadStudents();
        assertEquals(3, students.size());
    }

    @Test
    public void testLoadOneEmployer() {
        ArrayList<Employer> employers = DataLoader.loadEmployers();
        assertEquals(1, employers.size());
    }

    @Test
    public void testLoadNullEmployer() {
        users.clear();
        ArrayList<Employer> employers = DataLoader.loadEmployers();
        assertEquals(0, employers.size());
    }

    @Test
    public void testLoadThreeEmployers() {
        users.addEmployer(new Employer("Patrick", "Madden", "pmadden@amazon.com", "soccerFan123", "Amazon", "Deliver goods to people everywhere"));
        users.addEmployer(new Employer("Evan", "Park", "epark@jpmorgan.com", "password1234", "JP Morgan", "Make money"));
        ArrayList<Employer> employers = DataLoader.loadEmployers();
        assertEquals(3, employers.size());
    }

    @Test
    public void testLoadOneAdministrator() {
        ArrayList<Administrator> admins = DataLoader.loadAdministrators();
        assertEquals(1, admins.size());
    }

    @Test
    public void testLoadNullAdministrator() {
        users.clear();
        ArrayList<Administrator> admins = DataLoader.loadAdministrators();
        assertEquals(0, admins.size());
    }

    @Test
    public void testLoadThreeAdministrators() {
        users.addAdministrator(new Administrator("Patrick", "Madden", "pmadden@email.sc.edu", "soccerFan123", "Career Center Employee"));
        users.addAdministrator(new Administrator("Evan", "Park", "epark@email.sc.edu", "password1234", "SSC Advisor"));
        ArrayList<Administrator> admins = DataLoader.loadAdministrators();
        assertEquals(3, admins.size());
    }

    @Test
    public void testLoadOneListing() {
        ArrayList<InternshipListing> listings = DataLoader.loadInternshipList();
        assertEquals(1, listings.size());
    }

    @Test
    public void testLoadNullListings() {
        internships.clear();
        ArrayList<InternshipListing> listings = DataLoader.loadInternshipList();
        assertEquals(0, listings.size());
    }

    @Test
    public void testLoadThreeListings() {
        internships.addListing(new InternshipListing("Title", "Company", "Location", "Duration", 5, 10, UUID.randomUUID()));
        internships.addListing(new InternshipListing("Engineer", "Nasa", "Houston", "3 months", 3, 10, UUID.randomUUID()));
        ArrayList<InternshipListing> listings = DataLoader.loadInternshipList();
        assertEquals(3, listings.size());
    }

    @Test
    public void testLoadStudentResume() {
        ArrayList<Student> students = DataLoader.loadStudents();
        Resume resume = students.get(0).getResume();
        assertEquals(resume.getName(), "Lucy Davis");
    }
}


