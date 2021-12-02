import java.util.ArrayList;
import java.util.UUID;
public class Employer extends User{

    private String company;
    private String companyMission;
    private ArrayList<UUID> listings;
    private ArrayList<InternshipListing> postedListings; //ignore in json parser; will implement this in the interface/application
    private InternshipListing listing;
    private InternshipList internships;

    /**
     * initializes the employer
     * @param firstName employers first name
     * @param lastName employers last name
     * @param email employers email
     * @param password employers password
     * @param company employers company 
     * @param companyMission employers company mission
     */
    public Employer(String firstName, String lastName, String email, String password, String company, String companyMission) {
        super(firstName, lastName, email, password);
        this.setID(UUID.randomUUID());
        this.company = company;
        this.companyMission = companyMission;
        this.listings = new ArrayList<UUID>();
        this.postedListings = new ArrayList<InternshipListing>();
        this.internships = InternshipList.getInstance();
        loadListings();
    }

    //  constructor for JSON
    public Employer(String firstName, String lastName, UUID id, String company, String companyMission, String password, String email, ArrayList<UUID> listings) { 
        super(firstName, lastName, email, password);
        this.company = company;
        this.companyMission = companyMission; 
        this.setID(id);
        this.listings = listings;
        this.postedListings = new ArrayList<InternshipListing>();
        this.internships = InternshipList.getInstance();
        loadListings();
    }

    /**
     * gets employers company
     * @return company of employer 
     */
    public String getCompany() {
        return company;
    }

    /**
     * gets employers company mission
     * @return companys mission
     */
    public String getMission() {
        return this.companyMission;
    }

    /**
     * sets the employers company mission
     * @param mission the companys mission
     */
    public void setMission(String mission) {
        this.companyMission = mission;
    }

    /**
     * allows employer to remove a listing
     * @param listing listing to be removed
     */
    public void removeListing(InternshipListing listing) {
        this.listings.remove(this.getID());
        this.postedListings.remove(this);
    }

    /**
     * will load the listings that are posted
     */
    public void loadListings() {
        postedListings.clear();
        for (int i = 0; i < listings.size(); i++) {
            for (int j = 0; j < internships.getInternshipList().size(); j++) {
                if (internships.getInternshipList().get(j).getID().compareTo(listings.get(i)) == 0) {
                    postedListings.add(internships.getInternshipList().get(j));
                }
            }
        }
    }

    /**
     * gets the listings that are posted
     * @return the posted job listings
     */
    public ArrayList<InternshipListing> getListings() {
        return this.postedListings;
    }

    public ArrayList<UUID> getUUIDListings() {
        return this.listings;
    }

    public ArrayList<String> getListingsJSON() {
        ArrayList<String> listingsUUIDString = new ArrayList<String>();
        for (int i = 0; i < listings.size(); i++) {
            listingsUUIDString.add(listings.get(i).toString());
        }
        return listingsUUIDString;
    }

    /**
     * gets the amount of listings that are psted
     * @return number of posted listings
     */
    public int getNumListings() {
        return this.postedListings.size();
    }

    /**
     * allows employer to add a listing
     * @param listing listing to be added
     */
    public void addListing(InternshipListing listing) {
        this.listings.add(listing.getID());
        internships.addListing(listing);;
        loadListings();
    }

    /**
     * allows employer to set application close date
     * @param date date of closing
     */
    public void setApplicationCloseDate(String date) {
        //listing.setApplicationCloseDate(date);
    }

    /**
     * allows employer to close listing
     * @param listing listing to be closed
     */
    public void closeListing(InternshipListing listing) {
        listing.setStatus(false);
    }

    /**
     * allows employer to open listing
     * @param listing listing to be opened
     */
    public void openListing(InternshipListing listing) {
        listing.setStatus(true);
    }

    /**
     * allows employer to choose student for job
     * @param student student chosen for job
     */
    public void chooseCandidate(Student student) {
        listing.setSelectedCandidate(student);
    }

    /**
     * alows employer to write a review about a student
     * @param student student to be reviewed
     * @param rating rating student is given by the employer
     * @param date date of when review was given
     * @param comment comment given with students review
     */
    public void writeReview(Student student, int rating, String date, String comment) {
        String name = firstName + " " + lastName;
        Review review = new Review(rating, date, comment, name);
        student.addReview(review);
    }

    /**
     * displays the posted listings
     */
    public void displayListings() {
        loadListings();
        System.out.println("\nPOSTED LISTINGS:");
        for (int i = 1; i <= listings.size(); i++) {
            int index = i - 1;
            InternshipListing listing = postedListings.get(index);
            System.out.println("(" + i + ") " + listing.getCompany() + " " + listing.getTitle());
        }
    }

    /**
     * displays listings
     * @param index
     */
    public void displayListing(int index) {
        loadListings();
        postedListings.get(index).display();
    }

    /**
     * displays employers profile
     */
    public void displayProfile() {
        /**
         * display employer profile in the format:
         * Company: <COMPANY>
         * Company Mission: <COMPANY MISSION>
         * Employer: <FIRSTNAME> <LASTNAME>
         * Posted Listings:
         * 1. <Listing 1>
         * 2. <Listing 2>
         * ....
         */
        System.out.println("\nCompany: " + company);
        System.out.println("Company Mission: " + companyMission);
        System.out.println("Employer: " + firstName + " " + lastName);
        this.displayListings();
        System.out.println();
        
    }
}
