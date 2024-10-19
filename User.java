package musicplatform;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {
    // Basic User Information
    private String userId;
    private String password;
    private String username;
    private String email;
    private LocalDate birthDate;
    private boolean isArtist;
    private boolean isSubscribed;

    // Artist Profile (if applicable)
    private Artist artistProfile;

    // Playback and Playlist Management
    private Song onPlayingSong;
    private Playlist ongoingPlaylist;
    private List<Playlist> userPlaylists;
    private List<Song> listeningHistory;

    // Dependency for Searching Songs and Playlists
    private SongBase songBase;

    // Constructor
    public User(String userId, String password, String username, String email, 
                LocalDate birthDate, SongBase songBase) {
        this.userId = userId;
        this.password = password;
        this.username = username;
        this.email = email;
        this.birthDate = birthDate;
        this.isArtist = false;
        this.isSubscribed = false;
        this.songBase = songBase;
        this.userPlaylists = new ArrayList<>();
        this.listeningHistory = new ArrayList<>();
    }

    public void viewProfile() {
        System.out.println("\n=== Profile Information ===");
        System.out.println("User ID: " + userId);
        System.out.println("Username: " + username);
        System.out.println("Email: " + email);
        System.out.println("Birth Date: " + birthDate);
        System.out.println("Subscription Status: " + (isSubscribed ? "Subscribed" : "Not Subscribed"));
        System.out.println("Artist Status: " + (isArtist ? "Artist" : "Regular User"));
    
        if (isArtist && artistProfile != null) {
            System.out.println("Artist Type: " + artistProfile.getArtistType());
        }
    
        System.out.println("Playlists: " + (userPlaylists.isEmpty() ? "No playlists" : ""));
        for (Playlist playlist : userPlaylists) {
            System.out.println("- " + playlist.getName());
        }
    
        System.out.println("Listening History:");
        if (listeningHistory.isEmpty()) {
            System.out.println("No songs in listening history.");
        } else {
            for (Song song : listeningHistory) {
                System.out.println("- " + song.getName() + " by " + song.getArtistName());
            }
        }
    }
    
    // Toggle Subscription
    public void toggleSubscription() {
        isSubscribed = !isSubscribed;
        String status = isSubscribed ? "subscribed" : "unsubscribed";
        System.out.println(username + " is now " + status);
    }

    // Apply to Become an Artist
    public void applyForArtist(String type) {
        if (isArtist) {
            System.out.println(username + " is already an artist.");
            return;
        }
        
        if (type.equalsIgnoreCase("individual")) {
            artistProfile = new IndividualArtist(this, songBase);  // Pass User and SongBase
        } else if (type.equalsIgnoreCase("signed")) {
            artistProfile = new SignedArtist(this, songBase);  // Pass User and SongBase
        } else {
            System.out.println("Invalid artist type.");
            return;
        }

        isArtist = true;
        System.out.println(username + " is now a " + type + " artist.");
    }


    // Update Profile Information
    public void updateProfile(String newUsername, String newEmail) {
        this.username = newUsername;
        this.email = newEmail;
        System.out.println("Profile updated successfully.");
    }

    // Play a Song
    public void playSong(Song song) {
        onPlayingSong = song;
        ongoingPlaylist = null; // Single song mode
        song.play();
        listeningHistory.add(song);
    }

    // Play a Playlist
    public void playFromPlaylist(Playlist playlist) {
        ongoingPlaylist = playlist;
        playlist.play();
    }

    // Stop the Current Song
    public void stopSong() {
        if (onPlayingSong != null) {
            onPlayingSong.stop();
            onPlayingSong = null;
        } else {
            System.out.println("No song is currently playing.");
        }
    }

    // Like a Song
    public void likeSong(Song song) {
        song.rate(10); // Automatically gives it the highest rating
        System.out.println(username + " liked the song: " + song.getName());
    }

    // Create a New Playlist
    public void createPlaylist(String name) {
        Playlist newPlaylist = new Playlist(name);
        userPlaylists.add(newPlaylist);
        songBase.addPlaylist(newPlaylist);
        System.out.println("Playlist '" + name + "' created successfully.");
    }

    // Add a Song to a Playlist
    public void addToPlaylist(String playlistName, Song song) {
        Playlist playlist = searchUserPlaylist(playlistName);
        
        if (playlist == null) { 
            System.out.println("Playlist not found.");
        } else {
            playlist.addSong(song);
            System.out.println("Song added to playlist: " + playlistName);
        }
    }

    // Search User's Playlists
    private Playlist searchUserPlaylist(String name) {
        for (Playlist playlist : userPlaylists) {
            if (playlist.getName().equalsIgnoreCase(name.trim())) { // Handle case sensitivity and whitespace
                return playlist;
            }
        }
        return null; // Return null if not found
    }

    // Delete an Existing Playlist
    public void deletePlaylist(String playlistName) {
        boolean removed = userPlaylists.removeIf(p -> p.getName().equalsIgnoreCase(playlistName));
        if (removed) {
            System.out.println("Deleted playlist: " + playlistName);
        } else {
            System.out.println("Playlist not found.");
        }
    }

    // Search and Play a Song from the SongBase
    public void searchAndPlaySong(String songName) {
        Song song = songBase.searchSong(songName);
        if (song != null) {
            playSong(song);
        } else {
            System.out.println("Song not found.");
        }
    }

    // Search and Play a Playlist from the SongBase
    public void searchAndPlayPlaylist(String playlistName) {
        Playlist playlist = songBase.searchPlaylist(playlistName);
        if (playlist != null) {
            playFromPlaylist(playlist);
        } else {
            System.out.println("Playlist not found.");
        }
    }

    // View Listening History
    public void viewListeningHistory() {
        System.out.println("=== Listening History ===");
        if (listeningHistory.isEmpty()) {
            System.out.println("No songs listened to yet.");
        } else {
            for (Song song : listeningHistory) {
                System.out.println("- " + song.getName() + " by " + song.getArtistName());
            }
        }
    }


    // Getter Methods
    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public boolean isArtist() {
        return isArtist;
    }

    public boolean isSubscribed() {
        return isSubscribed;
    }

    public Artist getArtistProfile() {
        return artistProfile;
    }

    public Song getOnPlayingSong() {
        return onPlayingSong;
    }

    public Playlist getOngoingPlaylist() {
        return ongoingPlaylist;
    }

    public List<Playlist> getUserPlaylists() {
        return userPlaylists;
    }

    public List<Song> getListeningHistory() {
        return listeningHistory;
    }

    public SongBase getSongBase() {
        return songBase;
    }

    // Setter Methods
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setArtist(boolean isArtist) {
        this.isArtist = isArtist;
    }

    public void setSubscribed(boolean isSubscribed) {
        this.isSubscribed = isSubscribed;
    }

    public void setArtistProfile(Artist artistProfile) {
        this.artistProfile = artistProfile;
        this.isArtist = true;
    }

    public void setOnPlayingSong(Song onPlayingSong) {
        this.onPlayingSong = onPlayingSong;
    }

    public void setOngoingPlaylist(Playlist ongoingPlaylist) {
        this.ongoingPlaylist = ongoingPlaylist;
    }

    public void setUserPlaylists(List<Playlist> userPlaylists) {
        this.userPlaylists = userPlaylists;
    }

    public void setListeningHistory(List<Song> listeningHistory) {
        this.listeningHistory = listeningHistory;
    }

    public void setSongBase(SongBase songBase) {
        this.songBase = songBase;
    }
}
