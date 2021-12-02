import java.util.ArrayList;
import java.util.UUID;

public class InternshipList {
    private static InternshipList internshipList;
    private ArrayList<InternshipListing> internships;

    /**
     * private constructor for the list of posted internships; can only be accessed through getInstance
     */
    private InternshipList() {
        internships = DataLoader.loadInternshipList();
    }

    /**
     * ensures that only one list of internships is created in the application so as to keep the program efficient
     * @return either a new instance of an internship list if one does not exist, or the existing list
     */
    public static InternshipList getInstance() {
        if (internshipList == null) {
            internshipList = new InternshipList();
        }
        return internshipList;
    }

    /**
     * getter method for the arraylist of internship listings
     * @return an arraylist of internship listings
     */
    public ArrayList<InternshipListing> getInternshipList() { 
        return internships;
    }

    /**
     * adds a new listing to the current masterlist of internships
     * @param listing the listing to be added
     */
    public void addListing(InternshipListing listing) {
        this.internships.add(listing);
        DataWriter.saveInternshipList();
    }

    /**
     * displays all of the listings within the masterlist of internships
     */
    public void displayListings() {
        for (int i = 1; i <= internships.size(); i++) {
            int index = i-1;
            InternshipListing temp = internships.get(index);
            System.out.println("\t" + i + ". " + temp.getCompany() + " " + temp.getTitle());
        }
    }

    public void clear() {
        internships = new ArrayList<InternshipListing>();
        DataWriter.saveInternshipList();
    }
}