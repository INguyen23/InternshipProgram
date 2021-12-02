import java.util.UUID;
import java.util.ArrayList;
public class User {
    protected UUID id;
    private String email;
    private String password;
    protected String firstName;
    protected String lastName;
    private double averageRating;
    private ArrayList<Review> reviews;

    /**
     * initalizes the user
     * @param firstName users first name
     * @param lastName users last name
     * @param email users email
     * @param password users password
     */
    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        reviews = new ArrayList<>();
    }

    /**
     * allows user to create account
     * @param email users email
     * @param password users password
     */
    public void createAccount(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * sets users unique id
     * @param id users id
     */
    public void setID(UUID id) {
        this.id = id;
    }
    
    /**
     * gets users id
     * @return the users id
     */
    public UUID getID() { 
        return id;
    }

    /**
     * gets the users email
     * @return users email
     */
    public String getEmail() {
        return email;
    }

    /**
     * sets the users email
     * @param email users email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * gets the users password
     * @return users password
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * sets users password
     * @param password users password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * gets users first name
     * @return the users first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * sets the users first name
     * @param firstName users first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * gets the users last name
     * @return the suers last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * sets the users last name
     * @param lastName users last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    } 

    /**
     * gets the users name
     * @return users full name (first + last)
     */
    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setReviews(ArrayList<Review> reviews) {
        for (int i = 0; i < reviews.size(); i++) {
            this.addReview(reviews.get(i));
        }
    }

    /**
     * gets a list of the users reviews
     * @return the reviews given to the user
     */
    public ArrayList<Review> getReviews() {
        return reviews;
    }

    /**
     * adds a review to the user
     * @param review review given to the user
     */
    public void addReview(Review review) {
        reviews.add(review);
    }

    /**
     * displays a users review and their average rating
     */
    public void displayReviews() {
        for (Review review: reviews) {
            review.displayReview();
            System.out.println();
        }
        calculateAverageRating();
        System.out.println("Average Rating: " + getAverageRating());
    }

    /**
     * gets the users average rating
     * @return the average rating
     */
    public double getAverageRating() {
        return averageRating;
    }

    /**
     * calculates the users average rating of reviews
     */
    public void calculateAverageRating() {
        averageRating = 0;
        for (Review review: reviews) {
            averageRating += review.getRating();
        }
        if (reviews.size() == 0) {
            averageRating = 0;
        } else {
            averageRating = averageRating/reviews.size();
        }
    }
}
