package musicplatform;

import java.util.ArrayList;
import java.util.List;

public class Playlist implements Playable, Rateable {
    private String name;
    private List<Song> songs;
    private List<Integer> ratings; // Stores ratings for the playlist

    // Constructor
    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
        this.ratings = new ArrayList<>();
    }

    // Playable Interface Methods
    @Override
    public void play() {
        for (Song song : songs) {
            song.play();
        }
    }

    @Override
    public void pause() {
        // Placeholder for pause logic
    }

    @Override
    public void stop() {
        // Placeholder for stop logic
    }

    @Override
    public void skip() {
        // Placeholder: Skip to the next song (could be implemented as needed)
    }

    @Override
    public void restart() {
        // Placeholder: Restart playlist from the beginning
    }

    // Rateable Interface Methods
    @Override
    public void rate(int rating) {
        if (rating >= 1 && rating <= 10) {
            ratings.add(rating);
        } else {
            throw new IllegalArgumentException("Rating must be between 1 and 10.");
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
    }

    // Getter Methods
    public String getName() {
        return name;
    }

    public List<Song> getSongs() {
        return new ArrayList<>(songs); // Return a copy to prevent modification
    }

    public List<Integer> getRatings() {
        return new ArrayList<>(ratings); // Return a copy of ratings list
    }

    // Setter Methods
    public void setName(String name) {
        this.name = name;
    }

    // Add a song to the playlist
    public void addSong(Song song) {
        songs.add(song);
    }

    // Remove a song from the playlist
    public boolean removeSong(Song song) {
        return songs.remove(song);
    }

    // Check if the playlist contains a specific song
    public boolean containsSong(Song song) {
        return songs.contains(song);
    }
}
