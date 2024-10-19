package musicplatform;

public interface Rateable {
    // Rate the item with a score from 1 to 10
    void rate(int rating);

    // Get the average rating of the item
    double getAverageRating();

    // Get the number of ratings the item has received
    int getNumberOfRatings();

    // Check if the item is highly rated (average >= 8)
    boolean isHighlyRated();

    // Reset all ratings to start fresh
    void resetRatings();
}
