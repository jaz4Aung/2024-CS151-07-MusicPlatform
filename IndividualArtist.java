package musicplatform;

public class IndividualArtist extends Artist {
    private String bio; // Artist bio or description

    // Constructor
    public IndividualArtist(User user, SongBase songBase) {
        super(user.getUserId(), user.getUsername(), songBase); // Pass user and songBase to superclass
        this.bio = ""; // Default bio is empty
    }

    @Override
    public String getArtistType() {
        return "Individual Artist";
    }

    // Upload a song instantly and add it to the platform database
    @Override
    public void uploadSong(Song song) {
        uploadedSongs.add(song); // Add to artist's personal list
        songBase.addSong(song); // Add song to global SongBase
        System.out.println("Instantly uploaded song: " + song.getName());
    }

    // Delete a song from the artist's uploads and the platform database
    @Override
    public void deleteSong(Song song) {
        if (uploadedSongs.remove(song)) { // Remove from artist's personal list
            songBase.removeSong(song); // Remove from the platform's SongBase
            System.out.println("Deleted song: " + song.getName());
        } else {
            System.out.println("Song not found in your uploads.");
        }
    }

    // View the list of uploaded songs
    @Override
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

    // Customize the artist profile with a bio
    public void customizeProfile(String bio) {
        this.bio = bio;
        System.out.println(name + "'s profile updated with bio: " + bio);
    }

    // View the artist's profile with their bio and uploaded songs
    public void viewProfile() {
        System.out.println("Artist: " + name);
        System.out.println("Bio: " + (bio.isEmpty() ? "No bio available." : bio));
        viewUploadedSongs(); // Display the list of uploaded songs
    }

    // Calculate total earnings from streams (Individual artists get 100% revenue)
    @Override
    public double calculateEarnings() {
        double totalEarnings = getTotalStreams() * 0.01; // Assume $0.01 per stream
        System.out.println(name + " has earned $" + totalEarnings + " from streams.");
        return totalEarnings;
    }

    // Calculate total streams from all uploaded songs
    @Override
    public int getTotalStreams() {
        return uploadedSongs.stream().mapToInt(Song::getPlayCount).sum();
    }

    // === Getters and Setters ===

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
