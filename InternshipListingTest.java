import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InternshipListingTest {
    InternshipListing inListing = new InternshipListing("CS Intern", "Google", "NY", "3m", 10, 15, UUID.randomUUID());

    @BeforeEach
    public void setup() {
        inListing.getApplicants().clear();
        inListing.getDuties().clear();
        inListing.getRequirements().clear();
        inListing.getStudentApplications().clear();
    }

    @AfterEach
    public void tearDown() {
        inListing.getApplicants().clear();
        inListing.getDuties().clear();
        inListing.getRequirements().clear();
        inListing.getStudentApplications().clear();
    }

    @Test
    public void addApplicantTest() {
        Student student = new Student("Anne", "Lowry", "al@email.com", "p@ssword", "845-234-5829");
        inListing.addApplicant(student);
        assertEquals(inListing.getApplicants().get(0), student.getID());
    }

    @Test
    public void addApplicantMultipleTest() {
        Student student1 = new Student("Anne1", "Lowry1", "al@email.com1", "p@ssword1", "845-234-5821");
        Student student2 = new Student("Anne2", "Lowry2", "al@email.com2", "p@ssword2", "845-234-5822");
        Student student3 = new Student("Anne3", "Lowry3", "al@email.com3", "p@ssword3", "845-234-5823");
        inListing.addApplicant(student1);
        inListing.addApplicant(student2);
        inListing.addApplicant(student3);
        ArrayList<UUID> appl = inListing.getApplicants();
        assertTrue(appl.get(0).compareTo(student1.getID()) == 0 && appl.get(1).compareTo(student2.getID()) == 0 && appl.get(2).compareTo(student3.getID()) == 0);
    }

    @Test
    public void addApplicantRepeatTest() {
        Student student = new Student("Anne", "Lowry", "al@email.com", "p@ssword", "845-234-5829");
        inListing.addApplicant(student);
        inListing.addApplicant(student);
        assertTrue(inListing.getApplicants().size() == 1);
    }

    @Test   // Fails as student is never added to student applicants
    public void getSelectedCandidateTest() {
        Student student = new Student("Anne", "Lowry", "al@email.com", "p@ssword", "845-234-5829");
        inListing.addApplicant(student);
        inListing.setSelectedCandidate(student);
        Student testStudent = inListing.getSelectedCandidate();
        assertEquals(student.getID(), testStudent.getID());
    }

    @Test
    public void getSelectedCandidateNullTest() {
        assertNull(inListing.getSelectedCandidate());
    }

    @Test
    public void getSelectedCandidateUsingUUIDTest() {
        Student student = new Student("Anne", "Lowry", "al@email.com", "p@ssword", "845-234-5829");
        inListing.addApplicant(student);
        inListing.setSelectedCandidate(student);
        UUID testStudentID = inListing.getSelectedCandidateUsingUUID();
        assertEquals(student.getID(), testStudentID);
    }

    @Test
    public void getSelectedCandidateUsingUUIDMultipleTest() {
        Student student1 = new Student("Anne1", "Lowry1", "al@email.com1", "p@ssword1", "845-234-5821");
        Student student2 = new Student("Anne2", "Lowry2", "al@email.com2", "p@ssword2", "845-234-5822");
        Student student3 = new Student("Anne3", "Lowry3", "al@email.com3", "p@ssword3", "845-234-5823");
        inListing.addApplicant(student1);
        inListing.addApplicant(student2);
        inListing.addApplicant(student3);
        inListing.setSelectedCandidate(student2);
        UUID testStudentID = inListing.getSelectedCandidateUsingUUID();
        assertEquals(student2.getID(), testStudentID);
    }

    @Test
    public void getSelectedCandidateUsingUUIDNullTest() {
        assertNull(inListing.getSelectedCandidateUsingUUID());
    }
}
