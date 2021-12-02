import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class InternshipApplication {

    private static InternshipApplication program;
    private Scanner in;
    private UserList users;
    private InternshipList internships;
    private ArrayList<InternshipListing> listings;
    private User currentUser;
    private ArrayList<String> employerOptions;
    private ArrayList<String> studentOptions;
    private ArrayList<String> adminOptions;

    /**
     * constructor for an Internship Application
     */
    private InternshipApplication() {
        program = this;
        in = new Scanner(System.in);
        users = UserList.getUserList();
        internships = InternshipList.getInstance();
        listings = internships.getInternshipList();
    }

    public static InternshipApplication getInternshipApplication() {
        if (program == null)
            return new InternshipApplication();
        return program;
    }

    /**
     * gets the current user
     * @return the current user
     */
    public User getCurrentUser() {
        return this.currentUser;
    }

    /**
     * displays welcome messafe
     */
    public void displayWelcome() {
        String[] welcome = {"\n1. Log in", "2. Create account", "3. Quit"};
        for (String s : welcome)
            System.out.println(s);
    }

    /**
     * if user is student displays the student menu
     */
    public void displayStudentMenu() {
        String[] menu = { "\n--- Welcome USC Student " + currentUser.getFirstName() + " " + currentUser.getLastName() + " ---",
        "What can we help you with?", "\n1. View profile", "2. Update resume","3. Create new resume", "4. View all listings",
        "5. Search for a listing", "6. Log out\n" };
        for (String s : menu)
            System.out.println(s);
    }

    /**
     * if user is an employer displays employer menu
     */
    public void displayEmployerMenu() {
        String[] menu = { "\n--- Welcome " + currentUser.getFirstName() + " " + currentUser.getLastName() + " ---",
        "What can we help you with?", "\n1. Edit/view company profile", "2. View posted listings", "3. Create new listing",
        "4. Search students", "5. Log out \n" };
        for (String s : menu)
            System.out.println(s);
    }

    /**
     * if user is an admin displays admin menu
     */
    public void displayAdministratorMenu() {
        String[] menu = { "\n--- Welcome Admin " + currentUser.getFirstName() + " " + currentUser.getLastName() + " ---",
        "What can we help you with?", "\n1. View posted listings", "2. View registered students", "3. View registered employers", 
        "4. Log out"};
        for (String s : menu)
            System.out.println(s);
    }

    /**
     * create an account, either student, employer, admin
     * @param firstName first name of user
     * @param lastName last name of user
     * @param email users email
     * @param password users password
     * @param userType type of account (student, employer, admin)
     */
    public void createAccount(String firstName, String lastName, String email, String password, String userType) {
        //create account based on user input
        switch (userType) {
            case "s":
                System.out.println("Enter your phone number in the format 123-456-7890: ");
                String phoneNumber = in.nextLine();
                Student s = new Student(firstName, lastName, email, password, phoneNumber);
                currentUser = s;
                users.addStudent(firstName, lastName, email, password, phoneNumber);
                //add new student to studentList
                DataWriter.saveStudents();
                break;
            case "e":
                System.out.println("Enter the name of your company: ");
                String company = in.nextLine();
                System.out.println("Enter your company's mission: ");
                String companyMission = in.nextLine();
                Employer e = new Employer(firstName, lastName, email, password, company, companyMission);
                currentUser = e;
                users.addEmployer(e);
                DataWriter.saveEmployers();
                break;
            case "a":
                System.out.println("Enter your job title at the UofSC Career Center: ");
                String jobTitle = in.nextLine();
                Administrator a = new Administrator(firstName, lastName, email, password, jobTitle);
                currentUser = a;
                users.addAdministrator(a);
                DataWriter.saveAdministrators();
                break;
        }
    }

    /**
     * allows login
     * @param email account email
     * @param password account password
     * @param userType type of account (student, employer, admin)
     * @return
     */
    public boolean login(String email, String password, String userType) {
        currentUser = checkLogin(email, password, userType);
        if (currentUser != null) {
            System.out.println("User " + currentUser.getFirstName() + " " + currentUser.getLastName() + " successfully logged in.\n");
            switch (userType) {
                case "s":
                    this.currentUser = (Student)currentUser;
                    break;
                case "e":
                    this.currentUser = (Employer)currentUser;
                    break;
                case "a":
                    this.currentUser = (Administrator)currentUser;
                    break;
            }
            return true;
        }
        return false;
    }

    /**
     * check to make sure user login is valid
     * @param email user email
     * @param password user password
     * @param userType type of user account (student, employer, admin)
     * @return
     */
    public User checkLogin(String email, String password, String userType) {
        ArrayList<Student> students = users.getStudents();
        ArrayList<Employer> employers = users.getEmployers();
        ArrayList<Administrator> administrators = users.getAdmin();
        if (userType.equalsIgnoreCase("s")) {
            for (int i = 0; i < students.size(); i++) {
                if (students.get(i).getEmail().equals(email))
                    if (students.get(i).getPassword().equals(password))
                        return students.get(i);
            }
        }
        if (userType.equalsIgnoreCase("e")) {
            for (int i = 0; i < employers.size(); i++) {
                if (employers.get(i).getEmail().equals(email))
                    if (employers.get(i).getPassword().equals(password))
                        return employers.get(i);
            }
        }
        if (userType.equalsIgnoreCase("a")) {
            for (int i = 0; i < administrators.size(); i++) {
                if (administrators.get(i).getEmail().equals(email))
                    if (administrators.get(i).getPassword().equals(password))
                        return administrators.get(i);
            }
        }
        return null;
    }

    /**
     * searches for students
     * @param firstName students first name
     * @param lastName students last name
     * @return
     */
    public Student searchStudent(String firstName, String lastName) {
        ArrayList<Student> students = users.getStudents();
        System.out.println("size: " + students.size());
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getFirstName().equalsIgnoreCase(firstName) &&
                students.get(i).getLastName().equalsIgnoreCase(lastName))
                    return students.get(i);
        }
        return null;
    }

    public Employer searchEmployer(String firstname, String lastName) {
        //TODO: search list of employers for the target user
        //return the employer if found and null if not in user list
        return null;
    }

    /**
     * calls employers display profile
     * @param employer employer whos profile is to be viewed/displayed
     */
    public void viewEmployerProfile(Employer employer) {
        employer.displayProfile();
    }

    /**
     * calls students display profile
     * @param student student whos profile is to be viewed/displayed
     */
    public void viewStudentProfile(Student student) {
        student.displayProfile();
    }

    /**
     * allows user to write a review
     * @param reviewer who is writing the review
     * @param reviewee who is being reviewed
     */
    public void writeReview(User reviewer, User reviewee) {
        System.out.println("Enter the date in the format (MM/DD/YYYY): ");
        String date = in.nextLine();
        int rating = 0;
        while (true) {
            System.out.println("Enter a rating 1-5 for this user: ");
            String input = in.nextLine();
            try {
                rating = Integer.parseInt(input);
                if (rating < 1 || rating > 5) {
                    System.out.println("Invalid input.");
                    continue;
                }
                break;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                continue;
            }
        }
        System.out.println("Enter a comment on your experience: ");
        String comment = in.nextLine();
        String author = reviewer.getFirstName() + " " + reviewer.getLastName();
        Review review = new Review(rating, date, comment, author);
        reviewee.addReview(review);
        System.out.println("Review successfully written.");
    }

    /**
     * allows student to create a resume
     * @param student student whos resume is to be created
     */
    public void createResume(Student student) { 
        Resume resume = student.getResume();
        Education education = resume.getEducation();
        Experience experience = resume.getExperience();
        while (true) { 
            System.out.print("Creating " + student.getFirstName() + " " + student.getLastName() + "'s Resume:");
            System.out.println("\nEnter Hometown:");
            String hometown = in.nextLine();
            resume.setHometown(hometown);
            System.out.println("Add Education:\nEnter School:");
            String school = in.nextLine();
            education.setSchool(school);
            System.out.println("Enter Major:"); 
            String major = in.nextLine();
            education.setMajor(major);
            System.out.println("Enter Minor:");
            String minor = in.nextLine();
            education.setMinor(minor);
            System.out.println("Enter GPA:");
            double gpa = Double.parseDouble(in.nextLine());
            education.setGpa(gpa);
            System.out.println("Enter Graduation Year:");
            int graduationYear = Integer.parseInt(in.nextLine());
            education.setGraduationYear(graduationYear);
            System.out.println("Enter Class Standing:");
            String classStanding = in.nextLine();
            education.setClassStanding(classStanding);
            System.out.println("Enter Skills/Relevant Coursework:");
            boolean coursework = true;
            ArrayList<String> courseArray = new ArrayList<String>();
            while(coursework = true) { 
                System.out.println("Add skill/relevant coursework? (y/n)");
                        String addCoursework = in.nextLine();
                        
                        if (addCoursework.equalsIgnoreCase("y")) {
                            System.out.println("Enter name of skill/course: ");
                            String myClass = in.nextLine();
                            courseArray.add(myClass);
                            education.setCoursework(courseArray);
                            continue;
                        }
                        else if (addCoursework.equals("n")) {
                            break;
                        }
                        else {
                            System.out.println("Invalid input.");
                            continue;
                        }
            }
            resume.setEducation(education);
            System.out.println("Enter Awards:");
            boolean awards = true;
            while(coursework = true) { 
                System.out.println("Add awards? (y/n)");
                        String addAward = in.nextLine();
                        ArrayList<String> awardArray = new ArrayList<String>();
                        if (addAward.equalsIgnoreCase("y")) {
                            System.out.println("Enter name of Awards: ");
                            String award = in.nextLine();
                            awardArray.add(award);
                            resume.setAwards(awardArray);
                            continue;
                        }
                        else if (addAward.equals("n")) {
                            break;
                        }
                        else {
                            System.out.println("Invalid input.");
                            continue;
                        }
            }
            System.out.println("Enter Scholarships:");
            boolean scholarship = true;
            while(scholarship = true) { 
                System.out.println("Add scholarship? (y/n)");
                        String addScholarship = in.nextLine();
                        ArrayList<String> scholarshipArray = new ArrayList<String>();
                        if (addScholarship.equalsIgnoreCase("y")) {
                            System.out.println("Enter name of scholarship: ");
                            String scholarships = in.nextLine();
                            scholarshipArray.add(scholarships);
                            resume.setScholarships(scholarshipArray);
                            continue;
                        }
                        else if (addScholarship.equals("n")) {
                            scholarship = false;
                            break;
                        }
                        else {
                            System.out.println("Invalid input.");
                            continue;
                        }
            }
            System.out.println("Enter Experiences:");
            boolean experiences = true;
            ArrayList<Experience> experienceArray = new ArrayList<Experience>();
            while(experiences = true) { 
                System.out.println("Add experience? (y/n)");
                        String addExperience = in.nextLine();
                        if (addExperience.equalsIgnoreCase("y")) {
                            experience = new Experience("n/a", "n/a", "n/a", "n/a", "n/a");
                            System.out.println("Enter job title: ");
                            String title = in.nextLine();
                            experience.setTitle(title);
                            System.out.println("Enter Employer:");
                            String employer = in.nextLine();
                            experience.setEmployer(employer);
                            System.out.println("Enter location:");
                            String location = in.nextLine();
                            experience.setLocation(location);
                            System.out.println("Enter Start Date:");
                            String startDate = in.nextLine();
                            experience.setStartDate(startDate);
                            System.out.println("Enter End Date:");
                            String endDate = in.nextLine();
                            experience.setEndDate(endDate);
                            System.out.println("Enter Responsibilities:");
                            boolean responsibilities = true;
                            ArrayList<String> responsibilityArray = new ArrayList<String>();
                            while(responsibilities = true) { 
                                System.out.println("Add a responsibility? (y/n)");
                                        String addResponsibility = in.nextLine();
                                        if (addResponsibility.equalsIgnoreCase("y")) {
                                            System.out.println("Enter responsibility: ");
                                            String responsibility = in.nextLine();
                                            responsibilityArray.add(responsibility);
                                            experience.setResponsibilites(responsibilityArray);
                                            continue;
                                        }
                                        else if (addResponsibility.equals("n")) {
                                            responsibilities = false;
                                            break;
                                        }
                                        else {
                                            System.out.println("Invalid input.");
                                            continue;
                                        }
                            }
                            experienceArray.add(experience);
                            resume.setExperience(experienceArray);
                            continue;
                        }
                        else if (addExperience.equals("n")) {
                            experiences = false;
                            break;
                        }
                        else {
                            System.out.println("Invalid input.");
                            continue;
                        }
            }
            student.setResume(resume);
            break;
        }
        
    }

    /**
     * allows student to edit their resume
     * @param student student who wants to edit their resume
     */
    public void editResume(Student student) {
        while (true) {
            student.displayResume();
            System.out.println("\n(1) to edit hometown" + "\n(2) to edit education" + "\n(3) to add a new scholarship" 
                    + "\n(4) to add a new award" + "\n(5) to add a new work experience" + "\n(6) to write resume to a file" + "\n(0) to exit and save changes");
            System.out.print("Enter an option: ");
            String input = in.nextLine();
            System.out.println();
            switch (input) {
                case "0":   //returning to previous screen
                    return;
                case "1":
                    System.out.println("Enter hometown: ");
                    String hometown = in.nextLine();
                    student.resume.setHometown(hometown);
                    break;
                case "2":
                    System.out.println("Enter name of school: ");
                    String school = in.nextLine();
                    System.out.println("Enter your expected year of graduation: ");
                    int gradYear = in.nextInt();
                    in.nextLine();
                    System.out.println("Enter major: ");
                    String major = in.nextLine();
                    System.out.println("Enter minor: ");
                    String minor = in.nextLine();
                    System.out.println("Enter class standing (freshman/sophomore/junior/senior/graduate student): ");
                    String standing = in.nextLine();
                    System.out.println("Enter GPA (0.0 - 4.0): ");
                    double gpa = in.nextDouble();
                    in.nextLine();
                    ArrayList<String> coursework = student.resume.getEducation().getCoursework();
                    while (true) {
                        System.out.println("Add a skill/relevant coursework? (y/n)");
                        String addCoursework = in.nextLine();
                        if (addCoursework.equalsIgnoreCase("y")) {
                            System.out.println("Enter name of skill/course: ");
                            String course = in.nextLine();
                            student.getResume().getEducation().addCoursework(course);
                            continue;
                        }
                        else if (addCoursework.equalsIgnoreCase("n")) {
                            break;
                        }
                        else {
                            System.out.println("Invalid input.");
                            continue;
                        }
                    }
                    student.resume.editEducation(school, major, minor, gpa, gradYear, standing, coursework);
                    break;
                case "3":
                    System.out.println("Enter name of scholarship to add: ");
                    String scholarship = in.nextLine();
                    student.getResume().addScholarship(scholarship);
                    break;
                case "4":
                    System.out.println("Enter name of award to add: ");
                    String award = in.nextLine();
                    student.getResume().addAward(award);
                    break;
                case "5":
                    System.out.println("Enter title of experience: ");
                    String title = in.nextLine();
                    System.out.println("Enter name of employer: ");
                    String employer = in.nextLine();
                    System.out.println("Enter location of experience: ");
                    String location = in.nextLine();
                    System.out.println("Enter the start date: ");
                    String startDate = in.nextLine();
                    System.out.println("Enter the end date: ");
                    String endDate = in.nextLine();
                    ArrayList<String> responsibilities = new ArrayList<String>();
                    System.out.println("Enter a responsibility that you had during this experience: ");
                    String r = in.nextLine();
                    responsibilities.add(r);
                    while (true) {
                        System.out.println("Add another responsibility? (y/n)");
                        if (in.nextLine().equalsIgnoreCase("y")) {
                            System.out.println("Enter responsibility: ");
                            r = in.nextLine();
                            responsibilities.add(r);
                        }
                        break;
                    }
                    student.getResume().addExperience(title, employer, location, startDate, endDate, responsibilities);
                    break;
                case "6": 
                    System.out.println("Enter a name for your new file: ");
                    String fileName = in.nextLine();
                    student.getResume().createFile(fileName);
                    break;
                default:
                    System.out.println("Invalid input. \n");
            }

        }
    }

    /**
     * allows the user to view available internship listings
     * @param s student who wants to view the listing
     */
    public void viewInternshipListings(Student s) {
        while (true) {
            System.out.println();
            System.out.println("INTERNSHIP LISTINGS:\n");
            internships.displayListings();
            System.out.println("\nEnter the number of the internship you would like to view or 0 to go back: ");
            String input = in.nextLine();
            try {
                int option = Integer.parseInt(input);
                if (option == 0)
                    return;
                if (option-1 < listings.size()) {
                    System.out.println();
                    listings.get(option-1).display();
                    System.out.println("\n\nWould you like to apply for this internship? (Y/N)");
                    input = in.nextLine();
                    if (input.equalsIgnoreCase("y")) {
                        s.apply(listings.get(option-1));
                        DataWriter.saveInternshipList();
                        continue;
                    }
                    if (input.equalsIgnoreCase("n"))
                        continue;
                    System.out.println("Invalid input.");
                 }
                else {
                    System.out.println("Invalid input.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }

    }

    //overridden for administrators
    public void viewInternshipListings(Administrator administrator) {
        while (true) {
            internships.displayListings();
            System.out.println("\nEnter the number of the listing you would like to view or 0 to go back: ");
            String input = in.nextLine();
            try {
                int option = Integer.parseInt(input);
                if (option == 0)
                    return;
                if (option-1 < listings.size()) {
                    System.out.println();
                    InternshipListing listing = listings.get(option-1);
                    listing.display();
                    System.out.println("\n\n(1) to remove a duty");
                    System.out.println("(2) to remove a requirement");
                    System.out.println("(0) to go back");
                    System.out.println("Enter the number of the action you would like to take: ");
                    input = in.nextLine();
                    if (input.equalsIgnoreCase("1")) {
                        System.out.println("Enter the duty you would like to remove EXACTLY as it is written, omitting any commas: ");
                        String duty = in.nextLine();
                        if (listing.removeDuty(duty))
                            System.out.println("Duty removed.");
                        else    
                            System.out.println("Invalid input.");
                        continue;
                    }
                    if (input.equalsIgnoreCase("2")) {
                        System.out.println("Enter the requirement you would like to remove EXACTLY as it is written, omitting any commas: ");
                        String requirement = in.nextLine();
                        if (listing.removeRequirement(requirement))
                            System.out.println("Requirement removed.");
                        else    
                            System.out.println("Invalid input.");
                        continue;
                    }
                    if (input.equalsIgnoreCase("0"))
                        return;
                    System.out.println("Invalid input.");
                 }
                else {
                    System.out.println("Invalid input.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    /**
     * allows employer to edit their company mission
     * @param employer employer who wants to edit their company mission
     */
    public void editCompanyMission(Employer employer) {
        System.out.println("Enter the new company mission: ");
        String companyMission = in.nextLine();
        employer.setMission(companyMission);
        System.out.println("Mission successfully updated.\n");
        DataWriter.saveEmployers();
        return;
    }

    /**
     * allows employer to create a listing
     * @param employer employer who wants to create a listing
     */
    public void createListing(Employer employer) {
        System.out.println("Enter the title of the position: ");
        String title = in.nextLine();
        String company = employer.getCompany();
        System.out.println("Enter the location of this internship: ");
        String location = in.nextLine();
        System.out.println("Enter the duration of this internship:");
        String duration = in.nextLine();
        System.out.println("Enter the lowest potential pay of this position as a decimal: ");
        double lowerPay = Double.parseDouble(in.nextLine());
        System.out.println("Enter the highest potential pay of this position as a decimal: ");
        double higherPay = Double.parseDouble(in.nextLine());
        UUID employerID = employer.getID();
        InternshipListing listing = new InternshipListing(title, company, location, duration, lowerPay, higherPay, employerID);
        employer.addListing(listing);
        boolean addDuties = true;
        while (addDuties) {
            System.out.println("Enter a duty that this position shall require: ");
            String duty = in.nextLine();
            listing.addDuty(duty);
            while (true) {
                System.out.println("Add another duty? (Y/N)");
                String input = in.nextLine();
                if (input.equalsIgnoreCase("y"))
                    break;
                if (input.equalsIgnoreCase("n")) {
                    addDuties = false;
                    break;
                }
                System.out.println("Invalid input.");
            }
        }
        boolean addRequirement = true;
        while (addRequirement) {
            System.out.println("Enter a requirement that the applicant should have: ");
            String requirement = in.nextLine();
            listing.addRequirement(requirement);
            while (true) {
                System.out.println("Add another requirement? (Y/N)");
                String input = in.nextLine();
                if (input.equalsIgnoreCase("y"))
                    break;
                if (input.equalsIgnoreCase("n")) {
                    addRequirement = false;
                    break;
                }
                System.out.println("Invalid input.");
            }
        }
        DataWriter.saveInternshipList();
        DataWriter.saveEmployers();
        return;
    }

    /**
     * allows employer to edit a posted listing
     * @param employer employer who wants to edit a listing
     * @param numListing number of the displayed listings which they want to edit
     */
    public void editListing(Employer employer, int numListing) {

        while (true) {    
            InternshipListing listing = employer.getListings().get(numListing);
            listing.display();
            System.out.println("\n\n(1) to edit the duration");
            System.out.println("(2) to edit the pay range");
            System.out.println("(3) to add a new duty");
            System.out.println("(4) to add a new requirement");
            System.out.println("(5) to view list of applicants");
            System.out.println("(0) to go back");
            String input = in.nextLine();
            int command = 0;
            try {
                command = Integer.parseInt(input);
                switch (command) {
                    case 0: //return
                        return;
                    case 1: 
                        System.out.println("Enter the updated duration: ");
                        String duration = in.nextLine();
                        listing.setDuration(duration);
                        break;
                    case 2: //edit pay range
                        System.out.println("Enter the lowest possible pay (in decimal format): ");
                        double lowerPay = Double.parseDouble(in.nextLine());
                        System.out.println("Enter the highest possible pay (in decimal format): ");
                        double higherPay = Double.parseDouble(in.nextLine());
                        listing.setLowerPay(lowerPay);
                        listing.setHigherPay(higherPay);
                        break;
                    case 3: //add new duty
                        System.out.println("Enter a new duty that the intern will have: ");
                        String duty = in.nextLine();
                        listing.addDuty(duty);
                        break;
                    case 4: //add new requirement
                        System.out.println("Enter a new requirement that the applicant should have: ");
                        String requirement = in.nextLine();
                        listing.addRequirement(requirement);
                        break;
                    case 5: //display applicants
                        ArrayList<Student> applicants = loadApplicants(listing);
                        listing.setStudentApplicants(applicants);
                        viewStudent(employer, applicants, listing);
                        break;
                    default:
                        System.out.println("Invalid input.");
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                continue;
            }
        }

    }

    /**
     * allows user to edit the employers profile
     * @param employer employer whos profile is to be edited
     */
    public void editEmployerProfile(Employer employer) {
        while (true) {
            System.out.println("\nWould you like to edit your mission statement? (Y/N)");
            String input = in.nextLine();
            if (input.equalsIgnoreCase("Y")) {
                editCompanyMission(employer);
                return;
            }
            if (input.equalsIgnoreCase("n"))
                return;
            else {
                System.out.println("Invalid input.");
                continue;
            }
        }
    }

    /**
     * allows user to logot of the system
     */
    public void logout() {
        System.out.println("Logging out...");
        System.out.println("Goodbye!");
        DataWriter.saveStudents();
    }

    /**
     * allows the user to search by the name of company
     * @param searchTerm name of the company to search by
     */
    public void searchByCompany(String searchTerm) {
        ArrayList<InternshipListing> matches = new ArrayList<InternshipListing>();
        //TODO: fill matches with listings whose company contains the search term
        for (int i = 0; i < internships.getInternshipList().size(); i++) {
            if (internships.getInternshipList().get(i).getCompany().toLowerCase().contains(searchTerm.toLowerCase())) {
                matches.add(internships.getInternshipList().get(i));
            }
        }
        //print out matches
        displayMatches(matches);
    }

    /**
     * allows user to search by a keyword
     * @param searchTerm keyword that is used to search by
     */
    public void searchByKeyword(String searchTerm) {
        ArrayList<InternshipListing> matches = new ArrayList<InternshipListing>();
        for (int i = 0; i < internships.getInternshipList().size(); i++) {
            for(int j = 0; j < internships.getInternshipList().get(i).getDuties().size(); j++) {
                if (internships.getInternshipList().get(i).getDuties().get(j).toLowerCase().contains(searchTerm.toLowerCase()) && !matches.contains(internships.getInternshipList().get(i))) {
                    matches.add(internships.getInternshipList().get(i));
                }
            }
            for (int j = 0; j < internships.getInternshipList().get(i).getRequirements().size(); j++) {
                if (internships.getInternshipList().get(i).getRequirements().get(j).toLowerCase().contains(searchTerm.toLowerCase()) && !matches.contains(internships.getInternshipList().get(i))) {
                    matches.add(internships.getInternshipList().get(i));
                }
            }
        }

        //print out matches
        displayMatches(matches);
    }

    /**
     * displays the matches to the search terms
     * @param matches objects that match the search terms
     */
    public void displayMatches(ArrayList<InternshipListing> matches) {
        System.out.println("Your matches are below.");
        for (int i = 0; i < matches.size(); i++) {
            System.out.println("(" + (i + 1) + ") " + matches.get(i).getCompany() + " " + matches.get(i).getTitle());
        }
        while (true) {
            System.out.println("\nEnter the number of the internship you would like to view or 0 to go back: ");
                String input = in.nextLine();
                try {
                    int option = Integer.parseInt(input);
                    if (option == 0)
                        return;
                    if (option-1 < matches.size()) {
                        System.out.println();
                        matches.get(option-1).display();
                        System.out.println("\n\nWould you like to apply for this internship? (Y/N)");
                        input = in.nextLine();

                        if (input.equalsIgnoreCase("y")) {
                            ((Student)currentUser).apply(matches.get(option-1));
                            DataWriter.saveInternshipList();
                            return;
                        }

                        //returns back to student ui
                        if (input.equalsIgnoreCase("n")) 
                            return;

                        System.out.println("Invalid input.");
                    }
                    else {
                        System.out.println("Invalid input.");
                    }
                }
                catch (NumberFormatException e) {
                    System.out.println("Invalid input.");
            }
        }
    }

    /**
     * loads list of applicants
     * @param listing listing to load the list of applicants from
     * @return list of applicants
     */
    public ArrayList<Student> loadApplicants(InternshipListing listing) {
        ArrayList<Student> students = users.getStudents(); //megalist of students
        ArrayList<UUID> applicants = listing.getApplicants(); //applicants
        ArrayList<Student> ret = new ArrayList<Student>();
        for (int i = 0; i < applicants.size(); i++) {
            UUID applicantID = applicants.get(i);
            for (int j = 0; j < students.size(); j++) {
                UUID studentID = students.get(j).getID();
                if (applicantID.compareTo(studentID) == 0) {
                    ret.add((students.get(j)));
                }
            }
        }
        return ret;
    }

    /**
     * allows employer to view applicants and select student for internship
     * @param employer employer whos listing is posted and selects candidate
     * @param applicants applicants who applied to the internship
     * @param listing listing that is applied to and viewed by employer
     */
    public void viewStudent(Employer employer, ArrayList<Student> applicants, InternshipListing listing) {
        while (true) {
            listing.displayApplicants();
            System.out.println("Enter the number of the student whose resume you would like to view or 0 to go back.");
            String input = in.nextLine();
            try {
                int command = Integer.parseInt(input);
                if (command > applicants.size()) {
                    System.out.println("Invalid input.");
                    continue;
                }
                if (command == 0)
                    return;
                Student student = applicants.get(command-1);
                student.displayResume();
                System.out.println();
                student.displayReviews();
                if (listing.getSelectedCandidateUsingUUID() == null) {
                    System.out.println("\nWould you like to select this candidate for this position? (Y/N)");
                    input = in.nextLine();
                    if (input.equalsIgnoreCase("y")) {
                        listing.setSelectedCandidate(student);
                        System.out.println(student.getFirstName() + " " + student.getLastName() + " chosen for the position.");
                        DataWriter.saveInternshipList();
                        return;
                    }
                    if (input.equalsIgnoreCase("n"))
                        continue;
                    System.out.println("Invalid input.");
                }
                else {
                    System.out.println("\nStudent " + listing.getSelectedCandidate().getFirstName() + " " + listing.getSelectedCandidate().getLastName() + " has already been selected for this position.");
                    System.out.println("Press 'enter' to go back.");
                    in.nextLine();
                    return;
                }
                
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }

    /**
     * allows admin to view list of students
     */
    public void viewStudentList() {
        ArrayList<Student> students = users.getStudents();
        int num = 1;
        System.out.println("\nRegistered Students\n");
        for (int i = 0; i < students.size(); i++) {
            System.out.println("(" + num + ") " + students.get(i).getFirstName() + " " + students.get(i).getLastName());
            num++;
        }
        while (true) {
            System.out.println("Enter the number of the student you would like to view or 0 to go back: ");
            String input = in.nextLine();
            try {
                int option = Integer.parseInt(input);
                if (option ==0) 
                    return;
                if (option > students.size()) {
                    System.out.println("Invalid input.");
                    continue;
                }
                students.get(option-1).displayProfile();
                System.out.println();
                while (true) {
                    System.out.println("\nEnter 1 to write a review on this student or 0 to go back: ");
                    input = in.nextLine();
                    if (input.equals("1")) {
                        writeReview(currentUser, students.get(option-1));
                        break;
                    }
                    if (input.equals("0"))
                        break;
                    else {
                        System.out.println("Invalid input.");
                        continue;
                    }
                }
                continue;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                continue;
            }
        }
    }

    /**
     * allows admin to view list of employers
     */
    public void viewEmployerList() {
        ArrayList<Employer> employers = users.getEmployers();
        int num = 1;
        System.out.println("\nRegistered Employers\n");
        for (int i = 0; i < employers.size(); i++) {
            System.out.println("(" + num + ") " + employers.get(i).getFirstName() + " " + employers.get(i).getLastName() + ": " + employers.get(i).getCompany());
            num++;
        }
        while (true) {
            System.out.println("\nEnter the number of the employer you would like to view or 0 to go back: ");
            String input = in.nextLine();
            try {
                int option = Integer.parseInt(input);
                if (option ==0) 
                    return;
                if (option > employers.size()) {
                    System.out.println("Invalid input.");
                    continue;
                }
                editEmployerAdmin(employers.get(option-1));
                System.out.println();
                continue;
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input.");
                continue;
            }
        }
    }

    /**
     * allows the admin to edit the employer
     * @param employer employer the admin wants to edit
     */
    public void editEmployerAdmin(Employer employer) {
        while (true) {
            employer.displayProfile();
            System.out.println("Enter 1 to edit the company mission statement, 2 to edit a listing, or 0 to go back: ");
            String input = in.nextLine();
            try {
                int option = Integer.parseInt(input);
                switch (option) {
                    case 0:
                        return;
                    case 1:
                        editCompanyMission(employer);
                        break;
                    case 2:
                        while (true) {
                            employer.displayListings();
                            System.out.println("Enter the number of the listing you would like to view or 0 to go back: ");
                            input = in.nextLine();
                            try {
                                int command = Integer.parseInt(input);
                                if (command == 0)
                                    return;
                                if (command > employer.getNumListings()) {
                                    System.out.println("Invalid input.");
                                    continue;
                                }
                                editListing(employer, command);
                                break;
                            }
                            catch (NumberFormatException e) {
                                System.out.println("Invalid input.");
                                continue;
                            }
                        }
                        break;
                    default: 
                        System.out.println("Invalid input.");
                        continue;
                }
            }
            catch (NumberFormatException e) {
                System.out.println("Invalid input.");
            }
        }
    }


}

