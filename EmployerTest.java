import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class EmployerTest {
    private Employer employer = new Employer("Hawkins", "John", "jh@email.com", "p@ssword", "Company", "To do things");
    private InternshipList internships;

    @BeforeEach
    public void setup() {
        employer.getListings().clear();
        employer.getUUIDListings().clear();
        internships = InternshipList.getInstance();
        internships.clear();
    }

    @AfterEach
    public void tearDown() {
        employer.getListings().clear();
        employer.getUUIDListings().clear();
        internships.clear();
    }

    @Test
    public void removeListingTest() {
        InternshipListing inListing = new InternshipListing("CS Intern", "Google", "NY", "3m", 10, 15, UUID.randomUUID());
        employer.addListing(inListing);
        employer.removeListing(inListing);
        try {
            assertFalse(employer.getListings().get(0).getTitle().equals("CS Intern"));
        }
        catch (NullPointerException e) {
            assertTrue(true);
        }
    }

    @Test
    public void loadListingsOneListingTest() {
        InternshipListing inListing = new InternshipListing("CS Intern", "Google", "NY", "3m", 10, 15, UUID.randomUUID());
        employer.addListing(inListing);
        employer.loadListings();
        assertEquals(employer.getListings().get(0).getTitle(), "CS Intern");
    }

    @Test
    public void loadListingsMultipleListingsTest() {
        InternshipListing inListing1 = new InternshipListing("CS Intern1", "Google1", "NY1", "3m1", 101, 151, UUID.randomUUID());
        employer.addListing(inListing1);
        InternshipListing inListing2 = new InternshipListing("CS Intern2", "Google2", "NY2", "3m2", 102, 152, UUID.randomUUID());
        employer.addListing(inListing2);
        InternshipListing inListing3 = new InternshipListing("CS Intern3", "Google3", "NY3", "3m3", 103, 153, UUID.randomUUID());
        employer.addListing(inListing3);
        employer.loadListings();
        assertTrue(employer.getListings().get(0).getTitle().equals("CS Intern1") && employer.getListings().get(1).getTitle().equals("CS Intern2") && employer.getListings().get(2).getTitle().equals("CS Intern3"));
    }

    @Test
    public void writeReviewOneTest() {
        Student student = new Student("Anne", "Lowry", "al@email.com", "p@ssword", "845-234-5829");
        employer.writeReview(student, 4, "Aug 1", "This is a comment.");
        Review review = student.getReviews().get(0);
        assertTrue(review.getRating() == 4 && review.getDate().equals("Aug 1") && review.getComment().equals("This is a comment."));
    }

    @Test
    public void writeReviewMultipleTest() {
        Student student = new Student("Anne", "Lowry", "al@email.com", "p@ssword", "845-234-5829");
        employer.writeReview(student, 4, "Aug 1", "This is a comment1.");
        employer.writeReview(student, 3, "Aug 2", "This is a comment2.");
        employer.writeReview(student, 5, "Aug 3", "This is a comment3.");
        ArrayList<Review> revs = student.getReviews();
        assertTrue(revs.get(0).getRating() == 4 && revs.get(1).getDate().equals("Aug 2") && revs.get(2).getComment().equals("This is a comment3."));
    }

    @Test   // No argument to choose the listing you're choosing the student for
    public void chooseCandidateTest() {
        Student student = new Student("Anne", "Lowry", "al@email.com", "p@ssword", "845-234-5829");
        InternshipListing inListing1 = new InternshipListing("CS Intern", "Google", "NY", "3m", 10, 15, UUID.randomUUID());
        try {
            employer.chooseCandidate(student);
        }
        catch (NullPointerException exception) {
            fail();
        }
    }
}
