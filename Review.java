public class Review {
    private int rating;
    private String date;
    private String comment;
    private String author;

    /**
     * initializes the review
     * @param rating rating given to user
     * @param date date review was given
     * @param comment comment with review on the user
     * @param author who wrote the review
     */
    public Review(int rating, String date, String comment, String author) {
        this.rating = rating;
        this.date = date;
        this.comment = comment;
        this.author = author;
    }

    /**
     * gets users given rating
     * @return the rating
     */
    public int getRating() {
        return this.rating;
    }

    /**
     * gets the author of the current review
     * @return author's first and last name
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * gets the date the review was written
     * @return the date
     */
    public String getDate() {
        return this.date;
    }

    /**
     * gets the comment written by the reviewer
     * @return the comment
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * displays the review of the user
     */
    public void displayReview() {
        System.out.println("Rating: " + rating);
        System.out.println("Author: " + author);
        System.out.println("Date: " + date);
        System.out.println(comment);
    }
}
