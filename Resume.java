import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Resume {
    private Student student;
    private String name;
    private String hometown;
    private String phoneNumber;
    private Education education;
    private ArrayList<String> scholarships;
    private ArrayList<String> awards;
    private ArrayList<Experience> experiences;
    private Experience experience;

    /**
     * initialzes the resume
     * @param student student who has resume
     */
    public Resume(Student student) {
        this.student = student;
        this.name = student.getFirstName();
        this.name += " ";
        this.name += student.getLastName();
        this.phoneNumber = student.getPhoneNumber();
        hometown = "n/a";
        education = new Education("n/a", 0, 0, "n/a");
        scholarships = new ArrayList<String>();
        scholarships.add("n/a");
        awards = new ArrayList<String>();
        awards.add("n/a");
        experiences = new ArrayList<Experience>();
        experience = new Experience("n/a", "n/a", "n/a", "n/a", "n/a", null);
        experiences.add(experience);
    }

    //  resume constructor for JSON
    public Resume(String name, String phoneNumber, String hometown, Education education, ArrayList<String> awards, ArrayList<String> scholarships, ArrayList<Experience> experiences) { 
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.hometown = hometown;
        this.education = education;
        this.awards = awards;
        this.scholarships = scholarships;
        this.experiences = experiences;
        if (experiences == null) {
            this.experiences = new ArrayList<Experience>();
            this.experiences.add(new Experience ("n/a", "n/a", "n/a", "n/a", "n/a", null));
        }
    }

    /**
     * allows editing of the education
     * @param school school student attended
     * @param major students major
     * @param minor students minor
     * @param gpa students gpa
     * @param graduationYear students graduation year
     * @param classStanding students class standing
     * @param coursework students relevant coursework
     */
    public void editEducation(String school, String major, String minor, double gpa, int graduationYear, String classStanding, ArrayList<String> coursework) {
        education.setSchool(school);
        education.setGpa(gpa);
        education.setGraduationYear(graduationYear);
        education.setClassStanding(classStanding);
        education.setMajor(major);
        education.setMinor(minor);
        education.setCoursework(coursework);
    }

    /**
     * gets students name
     * @return name of student
     */
    public String getName() { 
        return name;
    }

    /**
     * gets students hometown
     * @return the students hometown
     */
    public String getHometown() {
        return hometown;
    }

    /**
     * sets the students hometwon
     * @param hometown students hometown
     */
    public void setHometown(String hometown) {
        this.hometown = hometown;
    }

    /**
     * gets the students education
     * @return students education
     */
    public Education getEducation() {
        return education;
    }

    /**
     * sets the students education
     * @param education students education
     */
    public void setEducation(Education education) {
        this.education = education;
    }

    /**
     * adds a scholarship to the students resume
     */
    public void addScholarship(String scholarship) {
        if (scholarships.size() == 0) {
            scholarships.add(scholarship);
        }
        if (scholarships.get(0).equals("n/a")) {
            scholarships.remove(0);
        }
        scholarships.add(scholarship);
    }

    /**
     * sets scholarships
     * @param scholarships scholarships for resume
     */
    public void setScholarships(ArrayList<String> scholarships) { 
        this.scholarships = scholarships;
    }

    /**
     * gets the scholarships for resume
     * @return scholarships for resume
     */
    public ArrayList<String> getScholarships() { 
        return scholarships;
    }

    /**
     * gets the awards for resume
     * @return awards for resume
     */
    public ArrayList<String> getAwards() { 
        return awards;
    }

    /**
     * removes a scholarship from the resume
     * @param scholarship scholarship to be removed
     * @return
     */
    public boolean removeScholarship(String scholarship) {
        return scholarships.remove(scholarship);
    }

    /**
     * add an award to the students resume
     * @param award award to be added to resume
     */
    public void addAward(String award) {
        if (awards.size() == 0) {
            awards.add(award);
        }
        if (awards.get(0).equals("n/a")) {
            awards.remove(0);
        }
        awards.add(award);
    }

    /**
     * sets the awards for the resume
     * @param awards awards for the resume
     */
    public void setAwards(ArrayList<String> awards) {
        this.awards = awards;
    }

    /**
     * adds an expereince to the resume
     * @param title title of the experience
     * @param employer the employer of the experience
     * @param location the location of the experience
     * @param startDate the jobs start date
     * @param endDate the jobs end date
     * @param responsibilities the jobs responsibilities
     */
    public void addExperience(String title, String employer, String location, String startDate, String endDate, ArrayList<String> responsibilities) {
        if (experiences.size() != 0 && experiences.get(0).getTitle().equals("n/a")) {
            experiences.remove(0);
        }
        Experience experience = new Experience(title, employer, location, startDate, endDate, responsibilities);
        experiences.add(experience);
    }

    /**
     * gets the experiences for the resume
     * @return experiences for the resume 
     */
    public ArrayList<Experience> getExperiences() { 
        return experiences;
    }

    /**
     * gets the experience
     * @return returns the experience
     */
    public Experience getExperience() { 
        return experience;
    }

    /**
     * set the experience
     * @param experiences job experience
     */
    public void setExperience(ArrayList<Experience> experiences) { 
        this.experiences = experiences;
    }

    /**
     * removes an experience from the resume
     * @param title title of the past job experience
     * @return
     */
    public boolean removeExperience(String title) {
        for (Experience exp : experiences) {
            if (exp.getTitle().equals(title)) {
                return experiences.remove(exp);
            }
        }
        return false;
    }

    /**
     * prints the scholarships to the resume
     */
    public void printScholarships() {
        System.out.print("\nScholarships: ");
        for (int i = 0; i < scholarships.size() - 1; i++)
            {
                System.out.print(scholarships.get(i) + ", ");
            }
        if (scholarships.size() != 0) {
            System.out.print(scholarships.get(scholarships.size() - 1));
        }
    }

    /**
     * prints the awards to the resume
     */
    public void printAwards() {
        System.out.print("\nAwards: ");
        for (int i = 0; i < awards.size() - 1; i++)
            {
                System.out.print(awards.get(i) + ", ");
            }
        if(awards.size() != 0) {
            System.out.println(awards.get(awards.size() - 1));
        }
    }

    /**
     * prints the experiences to the resume
     */
    public void printExperience() {
        for (int i = 0; i < experiences.size(); i++)
            {
                experiences.get(i).displayExperience();
            }
    }

    /**
     * displays the resume
     */
    public void display() {
        System.out.println("Phone number: " + phoneNumber);
        System.out.println("Hometown: " + hometown);
        education.displayEducation();
        printScholarships();
        printAwards();
        printExperience();
    }

    /**
     * creates a file for the resume to be printed to
     */
    public void createFile(String fileName) {
        try {
            File resumeFile = new File(fileName);
            if (resumeFile.createNewFile()) {
                writeToFile(fileName, resumeFile);
            }
            else {
                System.out.println("Error: file already exists.");
            }
        }
        catch (IOException e) {
            System.out.println("An error occured while creating this file.");
            e.printStackTrace();
        }
    }

    /**
     * writes the resume to the created file
     * @param fileName the name of the file
     * @param resumeFile file for the resume
     */
    public void writeToFile(String fileName, File resumeFile) {
        try {
            FileWriter writer = new FileWriter(fileName);
            writer.write("--" + this.name + "--");
            writer.write("\nPhone number: " + this.phoneNumber);
            writer.write("\nHometown: " + this.hometown);
            writer.write("\nSchool: " + this.education.getSchool());
            writer.write("\nExpected year of graduation: " + this.education.getGraduationYear());
            writer.write("\nGPA: " + this.education.getGpa());
            writer.write("\nClass Standing: " + this.education.getClassStanding());
            writer.write("\nMajor: " + this.education.getMajor());
            writer.write("\nMinor: " + this.education.getMinor());
            writer.write("\nRelevant coursework: ");
            for (int i = 0; i < this.education.getCoursework().size(); i++) {
                writer.write(this.education.getCoursework().get(i) + ", ");
            }
            writer.write("\nScholarships: ");
            for (int i = 0; i < this.scholarships.size(); i++) {
                writer.write(this.scholarships.get(i) + ", ");
            }
            writer.write("\nAwards: ");
            for (int i = 0; i < this.awards.size(); i++) {
                writer.write(this.awards.get(i) + ", ");
            }
            for (int i = 0; i < this.experiences.size(); i++) {
                Experience e = this.experiences.get(i);
                writer.write("\nExperience: " + e.getTitle() + " at " + e.getEmployer() + "; " + e.getLocation() + " | " + e.getStartDate() + " - " + e.getEndDate());
                writer.write("\nResponsibilities: ");
                for (int j = 0; j < e.getResponsibilities().size() - 1; j++)
                    writer.write(e.getResponsibilities().get(j) + ", ");
                if (e.getResponsibilities().size() > 0)
                    writer.write(e.getResponsibilities().get(e.getResponsibilities().size()-1));
            }

            writer.close();
            System.out.println("Resume successfully written to file " + fileName + ".");
        }
        catch (IOException e) {
            System.out.println("An error occurred while writing to this file.");
            e.printStackTrace();
        }
    }
}