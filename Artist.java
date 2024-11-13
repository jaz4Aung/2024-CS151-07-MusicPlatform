package musicplatform;

import java.util.ArrayList;
import java.util.List;

public abstract class Artist {
    protected String userId;  // Associated with the user
    protected String name;  // Artist's name or alias
    protected List<Song> uploadedSongs;  // List of uploaded songs
    protected List<User> followers;  // List of followers
    protected double earnings;  // Artist's earnings
    protected SongBase songBase;  // Reference to SongBase

    // Constructor
    public Artist(String userId, String name, SongBase songBase) {
        this.userId = userId;
        this.name = name;
        this.songBase = songBase;
        this.uploadedSongs = new ArrayList<>();
        this.followers = new ArrayList<>();
        this.earnings = 0.0;
    }

    // Abstract method to get the artist type (to be implemented by subclasses)
    public abstract String getArtistType();

    // Upload a new song
    public void uploadSong(Song song) {
        uploadedSongs.add(song);
        songBase.addSong(song);  // Add song to global database
        System.out.println("Song '" + song.getName() + "' uploaded by " + name);
    }

    // Delete a song
    public void deleteSong(Song song) {
        if (uploadedSongs.remove(song)) {
            songBase.removeSong(song);  // Remove song from SongBase
            System.out.println("Song '" + song.getName() + "' deleted by " + name);
        } else {
            System.out.println("Song not found in your uploads.");
        }
    }

    // View uploaded songs
    public void viewUploadedSongs() {
        if (uploadedSongs.isEmpty()) {
            System.out.println(name + " has not uploaded any songs.");
        } else {
            System.out.println("Uploaded songs by " + name + ":");
            uploadedSongs.forEach(song ->
                System.out.println("- " + song.getName() + " (" + song.getPlayCount() + " streams)")
            );
        }
    }

    // Calculate total streams from all uploaded songs
    public int getTotalStreams() {
        return uploadedSongs.stream().mapToInt(Song::getPlayCount).sum();
    }

    // Add a follower
    public void addFollower(User user) {
        if (user == null) {
            System.out.println("Cannot add a null follower.");
            return;
        }
        if (!followers.contains(user)) {
            followers.add(user);
            System.out.println(user.getUsername() + " is now following " + name);
        } else {
        System.out.println(user.getUsername() + " is already following " + name);
        }
    }


    // Remove a follower
    public void removeFollower(User user) {
        if (followers.remove(user)) {
            System.out.println(user.getUsername() + " is no longer following " + name);
        } else {
            System.out.println(user.getUsername() + " is not a follower of " + name);
        }
    }

    // Calculate earnings based on streams
    public double calculateEarnings() {
        earnings = getTotalStreams() * 0.01;  // Example: $0.01 per stream
        return earnings;
    }

    // === Getters and Setters ===

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Song> getUploadedSongs() {
        return new ArrayList<>(uploadedSongs);  // Return a copy to avoid modification
    }

    public void setUploadedSongs(List<Song> uploadedSongs) {
        this.uploadedSongs = new ArrayList<>(uploadedSongs);  // Set a copy to avoid reference issues
    }

    public List<User> getFollowers() {
        return new ArrayList<>(followers);  // Return a copy
    }

    public void setFollowers(List<User> followers) {
        this.followers = new ArrayList<>(followers);  // Set a copy to avoid reference issues
    }

    public double getEarnings() {
        return earnings;
    }

    public void setEarnings(double earnings) {
        this.earnings = earnings;
    }

    public SongBase getSongBase() {
        return songBase;
    }

    public void setSongBase(SongBase songBase) {
        this.songBase = songBase;
    }
}
