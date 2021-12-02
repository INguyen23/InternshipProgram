import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The only method to test in this class is adding the listing, which tests the datawriter and dataloader
 */
public class InternshipListTest {
    private InternshipList internships;

    @BeforeEach
    public void setup() {
        internships = InternshipList.getInstance();
        internships.clear();
    }

    @AfterEach
    public void tearDown() {
        internships.clear();
    }

    @Test
    public void addListingTest() {
        InternshipListing inListing = new InternshipListing("CS Intern", "Google", "NY", "3m", 10, 15, UUID.randomUUID());
        internships.addListing(inListing);
        assertEquals(internships.getInternshipList().get(0).getTitle(), "CS Intern");
    }

    @Test
    public void addListingMultipleTest() {
        InternshipListing inListing1 = new InternshipListing("CS Intern1", "Google1", "NY1", "3m1", 101, 151, UUID.randomUUID());
        InternshipListing inListing2 = new InternshipListing("CS Intern2", "Google2", "NY2", "3m2", 102, 152, UUID.randomUUID());
        InternshipListing inListing3 = new InternshipListing("CS Intern3", "Google3", "NY3", "3m3", 103, 153, UUID.randomUUID());
        internships.addListing(inListing1);
        internships.addListing(inListing2);
        internships.addListing(inListing3);
        ArrayList<InternshipListing> inList = internships.getInternshipList();
        assertTrue(inList.get(0).getTitle().equals("CS Intern1") && inList.get(1).getCompany().equals("Google2") && inList.get(2).getLowerPay() == 103);
    }
}
