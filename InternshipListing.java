import java.util.ArrayList;
import java.util.UUID;

public class InternshipListing {
    private UUID listingID;
    private UUID employerID;
    private String title;
    private String company;
    private boolean status;
    private String location;
    private String duration;
    private double lowerPay;
    private double higherPay;
    private ArrayList<String> duties;
    private ArrayList<String> requirements;
    private String CloseDate;
    private String startDate;
    private ArrayList<UUID> applicants;
    private ArrayList<Student> studentApplicants;
    private UUID selectedCandidate;
    private UserList users;

    //constructor for interface
    public InternshipListing(String title, String company, String location, String duration, double lowerPay, double higherPay, UUID employerID) {
        this.listingID = UUID.randomUUID();
        this.title = title;
        this.company = company;
        this.status = true;
        this.location = location;
        this.duration = duration;
        this.lowerPay = lowerPay;
        this.higherPay = higherPay;
        this.duties = new ArrayList<String>();
        requirements = new ArrayList<String>();
        selectedCandidate = null;
        this.employerID = employerID;
        this.applicants = new ArrayList<UUID>();
        this.studentApplicants = new ArrayList<Student>();
    }

    //constructor for JSON File
    public InternshipListing(String title, String company, boolean status, UUID listingID, UUID employerID, String location, String duration, double lowerPay, double higherPay, ArrayList<String> duties, ArrayList<String> requirements, ArrayList<UUID> applicants, UUID studentID) {
        this.title = title;
        this.company = company;
        this.status = status;
        this.listingID = listingID;
        this.employerID = employerID;
        this.location = location;
        this.duration = duration;
        this.lowerPay = lowerPay;
        this.higherPay = higherPay;
        this.duties = duties;
        this.requirements = requirements;
        //this.applicationCloseDate = applicationCloseDate;
        this.startDate = startDate;
        this.applicants = applicants;
        this.selectedCandidate = studentID;
        this.studentApplicants = new ArrayList<Student>();
    }

    /**
     * gets unique id of the listing
     * @return unique id of listing
     */
    public UUID getID() { 
        return listingID;
    }

    /**
     * get unique id of employer who posted listing
     * @return unique employers id
     */
    public UUID getEmployerID() { 
        return employerID;
    }
    
    /**
     * gets title of listing
     * @return title of listing
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets title of the listing
     * @param title title of the listing
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets company posting the listing
     * @return company that posted the listing
     */
    public String getCompany() {
        return company;
    }

    /**
     * sets company posting the listing
     * @param company company posting the listing
     */
    public void setCompany(String company) {
        this.company = company;
    }

    /**
     * location of the job posted
     * @return location of job posting
     */
    public String getLocation() {
        return location;
    }

    /**
     * sets location of job posting
     * @param location location of posted job
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * gets status of job listing (open/closed)
     * @return postings status
     */
    public boolean getStatus() {
        return status;
    }

    /**
     * sets the status of the posting
     * @param status status of the posting
     */
    public void setStatus(boolean status) {
        this.status = status;
    }

    /**
     * gets duration of the internship listing
     * @return duration of the internship listing
     */
    public String getDuration() {
        return duration;
    }

    /**
     * sets duration of the posted listing
     * @param duration duration of the listing
     */
    public void setDuration(String duration) {
        this.duration = duration;
    }

    /**
     * gets the lower pay of the posted listing
     * @return returns the pay of the listing
     */
    public double getLowerPay() {
        return lowerPay;
    }

    /**
     * sets lower pay of the listing
     * @param lowerPay pay of the listing 
     */
    public void setLowerPay(double lowerPay) {
        this.lowerPay = lowerPay;
    }

    /**
     * gets higher pay of the listing
     * @return pay of the listing
     */
    public double getHigherPay() {
        return higherPay;
    }

    /**
     * sets higher pay of the listing
     * @param higherPay pay of the listing
     */
    public void setHigherPay(double higherPay) {
        this.higherPay = higherPay;
    }

    /**
     * gets the duties of the posted listing
     * @return duties of the listing
     */
    public ArrayList<String> getDuties() {
        return duties;
    }

    /**
     * adds duties to the listing
     * @param duty duty to be added to listing
     */
    public void addDuty(String duty) {
        duties.add(duty);
    }

    /**
     * removes duty from the listing
     * @param duty duty to be removed 
     * @return 
     */
    public boolean removeDuty(String duty) {
        if (duties.contains(duty)) {
            duties.remove(duty);
            return true;
        }
        return false;
    }

    /**
     * gets requirements for the listing
     * @return listing requirements
     */
    public ArrayList<String> getRequirements() {
        return requirements;
    }

    /**
     * adds requirements to the listing
     */
    public void addRequirement(String requirement) {
        requirements.add(requirement);
    }

    /**
     * removes requirements from the listing
     */
    public boolean removeRequirement(String requirement) {
        if (requirements.contains(requirement)) {
            requirements.remove(requirement);
            return true;
        }
        return false;
    }

    /**
     * gets number of applicants for listing
     * @return number of applicants
     */
    public int getNumApplicants() {
        return applicants.size();
    }

    /**
     * gets the applicants and their ids
     * @return applicants
     */
    public ArrayList<UUID> getApplicants() {
        return this.applicants;
    }

    /**
     * gets the student applications
     * @return student applications
     */
    public ArrayList<Student> getStudentApplications() {
        return studentApplicants;
    }

    /**
     * adds a student object to the listing's list of applicants
     * @param student student who applies
     */
    public void addApplicant(Student student) {
        UUID id = student.getID();
        boolean alreadyThere = false;
        for (int i = 0; i < applicants.size(); i++) {
            if (id.compareTo(applicants.get(i)) == 0) {
                alreadyThere = true;
            }
        }
        if (!alreadyThere) {
            this.applicants.add(id);
            // for (int i = 0; i < applicants.size(); i++) {
            //     System.out.println(applicants.get(i));
            // }
            DataWriter.saveInternshipList();
        }
    }

    /**
     * sets student applicants
     * @param students students who apply
     */
    public void setStudentApplicants(ArrayList<Student> students) {
        this.studentApplicants = students;
    }

    /**
     * displays the applicants of the listing
     */
    public void displayApplicants() {
        System.out.println("\nApplicants: ");
        for (int i = 1; i <= studentApplicants.size(); i++) {
            System.out.println(+ i + ".  " + studentApplicants.get(i-1).getFirstName() + studentApplicants.get(i-1).getLastName());
        }
        System.out.println();
    }

    /**
     * sets the selected candidate
     * @param student student who is selected for listing
     */
    public void setSelectedCandidate(Student student) {
        this.selectedCandidate = student.getID();
    }

    /**
     * gets the selected candidate for the listing
     * @return the selected student
     */
    public Student getSelectedCandidate() {
        for (int i = 0; i < studentApplicants.size(); i++) {
            if (selectedCandidate.compareTo(studentApplicants.get(i).getID()) == 0)
                return studentApplicants.get(i);
        }
        return null;
    }

    public UUID getSelectedCandidateUsingUUID() {
        if (selectedCandidate == null) {
            return null;
        }
        for (int i = 0; i < applicants.size(); i++) {
            if (selectedCandidate.compareTo(applicants.get(i)) == 0)
                return applicants.get(i);
        }
        return null;
    }

    /**
     * gets selected student
     * @return selected student
     */
    public String getSelectedCandidateString() {
        return selectedCandidate.toString();
    }

    /**
     * gets the applicants
     * @return applicants
     */
    public ArrayList<String> getApplicantsString() {
        ArrayList<String> applicantsString = new ArrayList<String>();
        for (int i = 0; i < applicants.size(); i++) {
            applicantsString.add(applicants.get(i).toString());
        }
        return applicantsString;
    }
    
    /**
     * displays the internship listing
     */
    public void display() {
        System.out.println();
        System.out.println(this.company + " " + this.title);
        System.out.println("Duration: " + this.duration);
        System.out.println("Pay range: " + this.lowerPay + "/hr - " + this.higherPay + "/hr");
        System.out.print("Duties: ");
        for (int i = 0; i < duties.size() - 1; i++) {
            System.out.print(duties.get(i) + ", ");
        }
        if (duties.size() != 0) {
            System.out.print(duties.get(duties.size() - 1));
        }
        System.out.print("\nRequirements: ");
        for (int i = 0; i < requirements.size() - 1; i++) {
            System.out.print(requirements.get(i) + ", ");
        }
        if (requirements.size() != 0) {
            System.out.print(requirements.get(requirements.size() - 1));
        }
        users = UserList.getUserList();
        if (selectedCandidate != null) {
            System.out.print("\nSelected Candidate: ");
            for (int i = 0; i < users.getStudents().size(); i++) {
                if (selectedCandidate.compareTo(users.getStudents().get(i).getID()) == 0) {
                    System.out.println(users.getStudents().get(i).getName());
                }
            }
        }
    }
}
