package musicplatform;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class SignedArtist extends Artist {
    private LocalDate contractEndDate; // Contract expiration date
    private final double platformShare = 0.3; // Platform takes 30% of earnings

    // Constructor
    public SignedArtist(User user, SongBase songBase) {
        super(user.getUserId(), user.getUsername(), songBase); // Pass SongBase to superclass
        this.contractEndDate = LocalDate.now().plusYears(1); // Default 1-year contract
    }

    @Override
    public String getArtistType() {
        return "Signed Artist";
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

    // Upload a song with a 1-week delay and add it to the song base
    @Override
    public void uploadSong(Song song) {

        if (song == null) {
            System.out.println("Cannot upload a null song.");
            return;
        }
        // Validate if the entered release date is in the future
        if (song.getReleaseDate().isAfter(LocalDate.now())) {
            System.out.println("Future release date detected. Uploading on: " + song.getReleaseDate());
        } else {
            // If the release date is not in the future, set the delay to 1 week ahead
            LocalDate releaseDate = LocalDate.now().plus(7, ChronoUnit.DAYS);
            song.setReleaseDate(releaseDate); // Override with delayed release date
            System.out.println("Song '" + song.getName() + "' will be uploaded on: " + releaseDate);
        }
    
        uploadedSongs.add(song); // Add to artist's uploads
        songBase.addSong(song); // Add to SongBase
        System.out.println("Added song: " + song.getName());
    }
    

    // Delete a song from the uploaded list and SongBase if eligible
    @Override
    public void deleteSong(Song song) {
        if (song == null) {
        System.out.println("Cannot delete a null song.");
        return;
        }
        LocalDate uploadDate = song.getReleaseDate();
        LocalDate eligibleDate = uploadDate.plus(1, ChronoUnit.YEARS);
    
        if (LocalDate.now().isAfter(eligibleDate)) {
            uploadedSongs.remove(song); // Remove from the artist's uploaded list
            songBase.removeSong(song); // Remove from the global song base
            System.out.println("Deleted song: " + song.getName());
        } else {
            System.out.println("Cannot delete '" + song.getName() + "' until: " + eligibleDate);
        }
    }

    // Renew the contract by extending the expiration date by 1 year
    public void renewContract() {
        contractEndDate = contractEndDate.plusYears(1);
        System.out.println("Contract renewed until: " + contractEndDate);
    }

    // View contract details (expiration date)
    public void viewContractDetails() {
        System.out.println(name + "'s contract expires on: " + contractEndDate);
    }

    // Calculate artist earnings after deducting platform share
    @Override
    public double calculateEarnings() {
        double grossEarnings = getTotalStreams() * 0.01; // Assume $0.01 per stream
        double netEarnings = grossEarnings * (1 - platformShare); // Deduct 30% platform share
        System.out.println(name + " has earned $" + netEarnings + " after platform's share.");
        return netEarnings;
    }

    // Calculate the total number of streams for all uploaded songs
    @Override
    public int getTotalStreams() {
        return uploadedSongs.stream().mapToInt(Song::getPlayCount).sum();
    }

    // === Getters and Setters ===

    public LocalDate getContractEndDate() {
        return contractEndDate;
    }

    public void setContractEndDate(LocalDate contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    public double getPlatformShare() {
        return platformShare;
    }
}
