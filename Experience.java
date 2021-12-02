import java.util.ArrayList;

public class Experience {
    private String title = "n/a";
    private String employer = "n/a";
    private String location = "n/a";
    private String startDate = "n/a";
    private String endDate = "n/a";
    private ArrayList<String> responsibilities;

    /**
     * initializes the experience/past job
     * @param title title of past experience
     * @param employer who was the employer of the past experience
     * @param location where the last job took place
     * @param startDate when it began
     * @param endDate when it ended
     */
    public Experience(String title, String employer, String location, String startDate, String endDate) {
        this.title = title;
        this.employer = employer;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        responsibilities = new ArrayList<String>();
        responsibilities.add(0, "n/a");
    }

    //  constructor for JSON
    public Experience(String title, String employer, String location, String startDate, String endDate, ArrayList<String> responsibilities) {
        this.title = title;
        this.employer = employer;
        this.location = location;
        this.startDate = startDate;
        this.endDate = endDate;
        this.responsibilities = responsibilities;
        if (responsibilities == null) {
            this.responsibilities = new ArrayList<String>();
            this.responsibilities.add("n/a");
        }
    }

    /**
     * gets title of the past experience
     * @return the past experiences title
     */
    public String getTitle() {
        return title;
    }

    /**
     * sets the title of the past expereince
     * @param title the title of the past experience
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * gets employer of the past experience
     * @return the emloyer of past the experience
     */
    public String getEmployer() {
        return employer;
    }

    /**
     * set the employer of the past experience
     * @param employer employer of the past experience
     */
    public void setEmployer(String employer) {
        this.employer = employer;
    }

    /**
     * gets location of the past experience
     * @return location of past experience
     */
    public String getLocation() {
        return location;
    }

    /**
     * sets the location of the past experience
     * @param location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * gets the start date of the past experience
     * @return start date of past experience
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * sets the start date of the past experience
     * @param startDate
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * gets the end date of the past experience
     * @return end date of past experience
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * sets the end date of the past experience
     * @param endDate end date of the past experience
     */
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    /**
     * gets responsibilities from the past experience
     * @return responsibilities from past experience
     */
    public ArrayList<String> getResponsibilities() {
        return responsibilities;
    }

    /**
     * set the responsibilities from past experience
     * @param responsibilities what was done during past experience
     */
    public void setResponsibilites(ArrayList<String> responsibilities) { 
        this.responsibilities = responsibilities;
    }

    /**
     * allows responsibilities to be add to the experience of the resume
     * @param responsibility responsibility/duty that is to be added
     */
    public void addResponsibility(String responsibility) {
        if (responsibilities.get(0).equals("n/a")) {
            responsibilities.remove(0);
        }
        responsibilities.add(responsibility);
    }

    /**
     * allows responsibilities to be removed from the exprience of the resume
     * @param index
     */
    public void deleteResponsibility(int index) {
        if(index < responsibilities.size()) {
            responsibilities.remove(index);
        }
    }

    /**
     * displays the past experience
     */
    public void displayExperience() {
        System.out.print("Experience: " + title + " at " + employer + "; " + location + " | " + startDate + " - " + endDate);
        System.out.print("\n   Responsibilities: ");
        for (int i = 0; i < responsibilities.size() - 1; i++)
            {
                System.out.print(responsibilities.get(i) + ", ");
            }
        if (responsibilities.size() != 0) {
            System.out.println(responsibilities.get(responsibilities.size() - 1));
        }
    }
}
