import java.util.Scanner;

public class ApplicationUI {
    private Scanner scan;
    private InternshipApplication system;
    private String userType;
    
    public static void main(String[] args) {
        ApplicationUI application = new ApplicationUI();
        application.run();
    }

    /**
     * constructor for the application interface
     */
    public ApplicationUI() {
        scan = new Scanner(System.in);
        system = InternshipApplication.getInternshipApplication();
    }

    /**
     * runs the program by allowing user to log in or create account; also allows for user to exit the system
     */
    public void run() {
        while (true) {
            System.out.println("-- Welcome to the University of South Carolina Internship Program ---");
            // LOGGING IN/CREATING ACCOUNT
            while (true) {
                system.displayWelcome();
                int option = getUserOptions(3);
                if (option == -1) {
                    System.out.println("Invalid input.");
                    continue;
                }

                switch (option) {
                    case(0): //prompt log in
                        if (!promptLogIn()) {
                            System.out.println("Account with this username/password does not exist");
                            continue;
                        }
                        break;
                    case(1):    //create account
                        createAccount();
                        break;
                    case(2):    //quit
                        System.out.println("Goodbye.");
                        return;
                }
                break;
            }

            switch (userType) {
                case "s":
                    studentUI();
                    break;
                case "e":
                    employerUI();
                    break;
                case "a":
                    administratorUI();
                    break;
            }
        }
    }

    /**
     * helper method that prompts the user to enter an option and parse it as an integer representing the index of an option
     * @param numOptions number of different options the user can enter
     * @return the parsed integer
     */
    private int getUserOptions(int numOptions) {
        System.out.println("\nEnter an option: ");
        String input = scan.nextLine();
        int option;
        try {
            option = Integer.parseInt(input) - 1;
        }
        catch(NumberFormatException nfe) {
            option = -1;
        }
        if (option >= 0 && option < numOptions)
            return option;
        else
            return -1;
    }

    /**
     * prompts the user to log in as a specific usertype based off of an email and password
     * @return whether login was successful or not
     */
    private boolean promptLogIn() {
        while (true) {
            System.out.println("Enter (s) to log in as a student, (e), to log in as employer, or (a) to log in as admin.");
            userType = scan.nextLine();
            if (userType.equalsIgnoreCase("s")) {
                userType = "s";
                break;
            }
            if (userType.equalsIgnoreCase("e")) {
                userType = "e";
                break;
            }
            if (userType.equalsIgnoreCase("a")) {
                userType = "a";
                break;
            }
            System.out.println("Invalid account type.");
            continue;
        }
        System.out.println("Enter email: ");
        String email = scan.nextLine();
        System.out.println("Enter password: ");
        String password = scan.nextLine();
        return system.login(email, password, userType);
    }

    /**
     * creates a new account in the system by getting type of account, email, and password, desired information based on type of user, and logs user in
     */
    private void createAccount() {
        //getting user type
        String input;
        while (true) {
            System.out.println("Please enter an account type: (S)tudent, (E)mployer, or (A)dministrator");
            input = scan.nextLine();
            if (checkAccType(input).equals("false")) {
                System.out.println("Invalid account type.");
                continue;
            }
            break;
        }
        String accType = input;
        //getting user email
        System.out.println("Enter email: ");
        String email = scan.nextLine();
        if (accType.equals("s") || accType.equals("a")) {
            while (!checkValidEmail(email)) {
                System.out.println("Invalid email. Please enter a valid email associated with the University of South Carolina.");
                email = scan.nextLine();
            }
        }
        //get password
        System.out.println("Enter a password between 8-20 characters: ");
        String password = scan.nextLine();
        while (!checkValidPassword(password)) {
            System.out.println("Invalid password. Not within character limit.");
            System.out.println("Enter a password between 8-20 characters: ");
            password = scan.nextLine();
        }

        //get user first name
        System.out.println("Enter your first name: ");
        String firstName = scan.nextLine();

        //get user last name
        System.out.println("Enter your last name: ");
        String lastName = scan.nextLine();

        //creating account
        system.createAccount(firstName, lastName, email, password, accType);
        
        system.login(email, password, accType);
    }

    /**
     * checks to see if an email is registered with UofSC
     * @param email the email
     * @return whether email is valid or not
     */
    public boolean checkValidEmail(String email) {
        return email.contains("email.sc.edu");
    }

    /**
     * checks to see if a password is within length requirements
     * @param password the password
     * @return whether password is valid or not
     */
    public boolean checkValidPassword(String password) {
        return password.length() >= 8 && password.length() <= 20;
    }

    /**
     * determines what kind of user is currently logged onto the system so that the current UI/options can be displayed
     * @param input a letter representing the current account type
     * @return the lowercase letter representing the current account type
     */
    private String checkAccType(String input) {
        if (input.equalsIgnoreCase("s")) {
            this.userType = "s";
            return "s";
        }
        if (input.equalsIgnoreCase("e")) {
            this.userType = "e";
            return "e";
        }
        if (input.equalsIgnoreCase("a")) {
            this.userType = "a";
            return "a";
        }
        else
            return "false";
    }

    /**
     * main menu with student options; calls appropriate functions of InternshipApplication based on user input
     */
    private void studentUI() {
        Student s = (Student)system.getCurrentUser();
        while (true) {
            system.displayStudentMenu();
            int option = getUserOptions(6);
            switch (option) {
                case 0: //View profile
                    System.out.println();
                    system.viewStudentProfile(s);
                    System.out.println();
                    break;
                case 1: //View/update resume
                    system.editResume(s);
                    break;
                case 2: //create resume
                    system.createResume(s);
                    break;
                case 3: //view listings and apply
                    system.viewInternshipListings(s);
                    break;
                case 4: //search for listings by keyword
                    String command = "";
                while (true) {
                    System.out.println("(1) Search by company");
                    System.out.println("(2) Search by keyword");
                    System.out.println("(0) Exit");
                    command = scan.nextLine();
                    switch (command) {
                        case "0": //return
                            break;
                        case "1": //search by employer
                            System.out.println("Enter company to search for: ");
                            String searchTerm = scan.nextLine();
                            system.searchByCompany(searchTerm);
                            break;
                        case "2": //search by keyword
                            System.out.println("Enter keyword to search for: ");
                            String searchTermKey = scan.nextLine();
                            system.searchByKeyword(searchTermKey);
                            break;
                        default:
                            System.out.println("Invalid option.\n");
                            continue;
                    }
                    break;
                }
                    break;
                case 5: //logout
                    system.logout();
                    return;
            }
            
        }
    }

    /**
     * main menu with employer options; calls appropriate functions of InternshipApplication based on user input
     */
    private void employerUI() {
        Employer employer = (Employer)system.getCurrentUser();
        while (true) {
            system.displayEmployerMenu();
            int option = getUserOptions(5);
            switch (option) {
                case 0: //EDIT/VIEW COMPANY PROFILE
                    system.viewEmployerProfile(employer);
                    system.editEmployerProfile(employer);
                        break;
                case 1: //VIEW POSTED LISTINGS
                    while (true) {
                        employer.displayListings();
                        System.out.println("\nEnter the number of the listing you want to edit or 0 to go back");
                        String input = scan.nextLine();
                        int command;
                        try {
                            command = Integer.parseInt(input);
                            if (command == 0)
                                break;
                            if (command > employer.getNumListings()) {
                                System.out.println("Invalid entry.");
                                continue;
                            }
                            system.editListing(employer, command-1);
                        }
                        catch (NumberFormatException e) {
                            System.out.println("Invalid input. Enter a number to view a listing or 0 to go back");
                        }
                    }
                    break;
                case 2: //CREATE NEW LISTING
                    system.createListing(employer);
                    break;
                case 3: //SEARCH STUDENTS
                    Student student;
                    System.out.println("Enter first name of student to search: ");
                    String firstName = scan.nextLine();
                    System.out.println("Enter last name of student to search: ");
                    String lastName = scan.nextLine();
                    student = system.searchStudent(firstName, lastName);
                    if (student == null) {
                        System.out.println("\nUser not found.");
                        break;
                    }
                    else {
                        System.out.println("\nUser found:\n");
                        system.viewStudentProfile(student);
                        while (true) {
                            System.out.println("\nEnter 1 to write a review on this student or 0 to go back: ");
                            String input = scan.nextLine();
                            if (input.equals("1")) {
                                system.writeReview(system.getCurrentUser(), student);
                                break;
                            }
                            if (input.equals("0"))
                                break;
                            else {
                                System.out.println("Invalid input.");
                                continue;
                            }
                        }
                    }
                    break;
                case 4: //LOG OUT
                    system.logout();
                    return;
            }
        }
    }

    /**
     * main menu with administrator options; calls appropriate functions of InternshipApplication based on user input
     */
    private void administratorUI() {
        Administrator a = (Administrator)system.getCurrentUser();
        while (true) {
            system.displayAdministratorMenu();
            int option = getUserOptions(5);
            switch (option) {
                case 0:
                     system.viewInternshipListings(a);
                    break;
                case 1:
                    system.viewStudentList();
                    break;
                case 2: 
                    system.viewEmployerList();
                    break;
                case 3:
                    system.logout();
                    return;
            }
        }
    }
}
