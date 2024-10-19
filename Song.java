package musicplatform;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Song implements Playable, Rateable {
    // Attributes
    private String name;
    private String artistName;
    private int duration; // in seconds
    private String genre;
    private LocalDate releaseDate;
    private int playCount;
    private boolean isExplicit;
    private List<Integer> ratings;

    // Constructor
    public Song(String name, String artistName, int duration, String genre, LocalDate releaseDate, boolean isExplicit) {
        this.name = name;
        this.artistName = artistName;
        this.duration = duration;
        this.genre = genre;
        this.releaseDate = releaseDate;
        this.isExplicit = isExplicit;
        this.playCount = 0;
        this.ratings = new ArrayList<>();
    }

    // Playable Interface Methods
    @Override
    public void play() {
        playCount++;
        System.out.println("Playing '" + name + "' by " + artistName + ". Play count: " + playCount);
    }

    @Override
    public void pause() {
        System.out.println("Paused '" + name + "'");
    }

    @Override
    public void stop() {
        System.out.println("Stopped '" + name + "'");
    }

    @Override
    public void skip() {
        System.out.println("Skipping to the next song...");
    }

    @Override
    public void restart() {
        System.out.println("Restarting '" + name + "'");
    }

    // Rateable Interface Methods
    @Override
    public void rate(int rating) {
        if (rating >= 1 && rating <= 10) {
            ratings.add(rating);
            System.out.println("Rated '" + name + "' with " + rating + " stars.");
        } else {
            System.out.println("Invalid rating. Please provide a rating between 1 and 10.");
        }
    }

    // Static method to get song duration with exception handling
    public static int getValidDuration() {
        Scanner scanner = new Scanner(System.in); // Use local scanner
        try {
            System.out.print("Enter duration (in seconds): ");
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Error: Duration must be a valid number.");
            return getValidDuration(); // Retry until valid input
        }
    }
    
    @Override
    public double getAverageRating() {
        if (ratings.isEmpty()) return 0;
        return ratings.stream().mapToInt(Integer::intValue).average().orElse(0);
    }

    @Override
    public int getNumberOfRatings() {
        return ratings.size();
    }

    @Override
    public boolean isHighlyRated() {
        return getAverageRating() >= 8;
    }

    @Override
    public void resetRatings() {
        ratings.clear();
        System.out.println("All ratings have been reset for '" + name + "'.");
    }

    // Getter Methods
    public String getName() {
        return name;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getDuration() {
        return duration;
    }

    public String getGenre() {
        return genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public int getPlayCount() {
        return playCount;
    }

    public boolean isExplicit() {
        return isExplicit;
    }

    public List<Integer> getRatings() {
        return new ArrayList<>(ratings); // Return a copy to prevent external modification
    }

    // Setter Methods
    public void setName(String name) {
        this.name = name;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public void setExplicit(boolean isExplicit) {
        this.isExplicit = isExplicit;
    }
}
