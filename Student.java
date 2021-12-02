import java.util.UUID;
import java.util.ArrayList;

public class Student extends User {
    protected Resume resume;
    private String major;
    private String minor;
    private String phoneNumber;
    private String classStanding;
    private ArrayList<UUID> appliedListings;
    
    /**
     * initializes the student user
     * @param firstName students first name
     * @param lastName students last name
     * @param email students email
     * @param password students password
     * @param phoneNumber student phone number 
     */
    public Student(String firstName, String lastName, String email, String password, String phoneNumber) {
        super(firstName, lastName, email, password);
        this.phoneNumber = phoneNumber;
        this.setID(UUID.randomUUID());
        this.resume = new Resume(this);
        appliedListings = new ArrayList<UUID>();
    }

    //JSON CONSTRUCTOR
    public Student(String firstName, String lastName, String email, String password, String phoneNumber, UUID id, String major, String minor, String classStanding, ArrayList<UUID> appliedListings, Resume resume) {
        super(firstName, lastName, email, password);
        this.major = major;
        this.minor = minor;
        this.phoneNumber = phoneNumber;
        this.setID(id);
        this.classStanding = classStanding;
        this.appliedListings = appliedListings;
        this.resume = resume;
    }

    /**
     * displays the students resume
     */
    public void displayResume() {
        System.out.println("-- " + this.getFirstName() + " " + this.getLastName() + " Resume --"); 
        this.resume.display();
    }

    /**
     * allows the student to apply for internship
     * @param internship internship to be applied for
     */
    public void apply(InternshipListing internship) {
        appliedListings.add(internship.getID());
        internship.addApplicant(this);
        System.out.println("Successfully applied.");
        DataWriter.saveStudents();
        DataWriter.saveInternshipList();
        return;
    }

    /**
     * gets students resuem
     * @return the students resume
     */
    public Resume getResume() {
        return this.resume;
    }

    /**
     * sets the students resume
     * @param resume students resume
     */
    public void setResume(Resume resume) { 
        this.resume = resume;
    }

    /**
     * sets the students major
     * @param major students major
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * sets the students minor
     * @param minor students minor
     */
    public void setMinor(String minor) {
        this.minor = minor;
    }

    /**
     * gets students class standing
     * @return students class standing
     */
    public String getClassStanding() { 
        return this.classStanding;
    }

    /**
     * set students phone number
     * @param phoneNumber students phone number
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * gets the students phone number
     * @return students phone number
     */
    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    /**
     * gets the listings student has applied for
     * @return the applied listings
     */
    public ArrayList<InternshipListing> getAppliedListings() {
        ArrayList<InternshipListing> applications = new ArrayList<InternshipListing>();
        for (int i = 0; i < appliedListings.size(); i++) {
            UUID id = appliedListings.get(i);
            //search the internship listing database and add each matching UUID to applications
        }
        return applications;
    }

    /**
     * gets listings from json
     * @return applied listings
     */
    public ArrayList<String> getAppliedListingsJSON() {
        ArrayList<String> applications = new ArrayList<String>();
        for (int i = 0; i < appliedListings.size(); i++) {
            applications.add(appliedListings.get(i).toString());
        }
        return applications;
    }

    public void viewEmployerProfile(Employer employer) {
        //employer.displayProfile();
    }

    /**
     * displays the students profile
     */
    public void displayProfile() {
        System.out.println("\n--- " + this.firstName + " " + this.lastName + " ---");
        System.out.println("Phone number: " + this.phoneNumber);
        System.out.println("Hometown: " + resume.getHometown());
        System.out.println("Expected year of graduation: " + resume.getEducation().getGraduationYear());
        System.out.println("Education: " + resume.getEducation().getSchool());
        System.out.println("Major: " + resume.getEducation().getMajor());
        System.out.println("Minor: " + resume.getEducation().getMinor());
        System.out.println();
        System.out.println("Reviews: ");
        displayReviews();
    }
}