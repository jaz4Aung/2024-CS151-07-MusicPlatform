package musicplatform;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SongBase songBase = new SongBase();
    private static final HashMap<String, User> users = new HashMap<>();
    private static User loggedInUser;

    public static void main(String[] args) {
        initializeSongs();

        System.out.println("=== Welcome to the Music Platform ===");
        while (loggedInUser == null) {
            System.out.println("1. Log in");
            System.out.println("2. Register");
            System.out.print("Choose an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> logIn();
                case "2" -> registerUser();
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        mainMenu(); // Display main menu after login
    }

    // Initialize 20 rap/hip-hop songs
    private static void initializeSongs() {
        songBase.addSong(new Song("SICKO MODE", "Travis Scott", 312, "Hip-Hop", LocalDate.of(2018, 8, 3), true));
        songBase.addSong(new Song("HUMBLE.", "Kendrick Lamar", 177, "Rap", LocalDate.of(2017, 3, 30), true));
        songBase.addSong(new Song("Bank Account", "21 Savage", 210, "Hip-Hop", LocalDate.of(2017, 7, 7), true));
        songBase.addSong(new Song("God's Plan", "Drake", 198, "Hip-Hop", LocalDate.of(2018, 1, 19), true));
        songBase.addSong(new Song("Rockstar", "Post Malone", 218, "Hip-Hop", LocalDate.of(2017, 9, 15), true));
        songBase.addSong(new Song("Lucid Dreams", "Juice WRLD", 239, "Hip-Hop", LocalDate.of(2018, 5, 11), true));
        songBase.addSong(new Song("The Box", "Roddy Ricch", 195, "Hip-Hop", LocalDate.of(2019, 12, 6), true));
        songBase.addSong(new Song("Goosebumps", "Travis Scott", 255, "Hip-Hop", LocalDate.of(2016, 9, 2), true));
        songBase.addSong(new Song("No Role Modelz", "J. Cole", 292, "Rap", LocalDate.of(2014, 12, 9), false));
        songBase.addSong(new Song("Mask Off", "Future", 203, "Rap", LocalDate.of(2017, 4, 18), true));
        songBase.addSong(new Song("Life Is Good", "Future feat. Drake", 250, "Hip-Hop", LocalDate.of(2020, 1, 10), true));
        songBase.addSong(new Song("Suge", "DaBaby", 191, "Hip-Hop", LocalDate.of(2019, 3, 1), true));
        songBase.addSong(new Song("Laugh Now Cry Later", "Drake feat. Lil Durk", 261, "Hip-Hop", LocalDate.of(2020, 8, 14), true));
        songBase.addSong(new Song("Whats Poppin", "Jack Harlow", 159, "Hip-Hop", LocalDate.of(2020, 1, 21), true));
        songBase.addSong(new Song("Hotline Bling", "Drake", 267, "Hip-Hop", LocalDate.of(2015, 7, 31), false));
        songBase.addSong(new Song("SICK", "Earl Sweatshirt", 174, "Rap", LocalDate.of(2022, 1, 14), true));
        songBase.addSong(new Song("Mo Bamba", "Sheck Wes", 207, "Hip-Hop", LocalDate.of(2017, 6, 16), true));
        songBase.addSong(new Song("Money Trees", "Kendrick Lamar feat. Jay Rock", 394, "Hip-Hop", LocalDate.of(2012, 10, 22), true));
        songBase.addSong(new Song("Nonstop", "Drake", 222, "Hip-Hop", LocalDate.of(2018, 6, 29), true));
        songBase.addSong(new Song("Bodak Yellow", "Cardi B", 235, "Hip-Hop", LocalDate.of(2017, 6, 16), true));
    }
    

    // User registration
    private static void registerUser() {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter birth date (YYYY-MM-DD): ");
        LocalDate birthDate = LocalDate.parse(scanner.nextLine());

        User newUser = new User(userId, password, username, email, birthDate, songBase);
        users.put(userId, newUser);
        System.out.println("Registration successful! You can now log in.");
    }

    // User login
    private static void logIn() {
        System.out.print("Enter user ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = users.get(userId);
        if (user != null && user.getPassword().equals(password)) {
            loggedInUser = user;
            System.out.println("Welcome back, " + user.getUsername() + "!");
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    // Main menu with exception handling
    private static void mainMenu() {
        String choice = "";  

        do {
            try {
                System.out.println("\n=== Main Menu ===");
                System.out.println("1. View/Update Profile");
                System.out.println("2. Play Song");
                System.out.println("3. Manage Playlists");
                System.out.println("4. Apply for Artist Role");
                System.out.println("5. Manage Artist Profile");
                System.out.println("6. Search Songs/Playlists");
                System.out.println("7. Stop Song");
                System.out.println("8. Like a Song");
                System.out.println("9. See Current Song Info");
                System.out.println("10. Log out");
                System.out.println("EXIT - Close the program");
                System.out.print("Enter your choice: ");
                choice = scanner.nextLine().trim().toUpperCase();

                switch (choice) {
                    case "1" -> manageProfile();
                    case "2" -> playSongOptions();
                    case "3" -> managePlaylists();
                    case "4" -> applyForArtist();
                    case "5" -> manageArtistProfile();
                    case "6" -> searchSongsAndPlaylists();
                    case "7" -> loggedInUser.stopSong();
                    case "8" -> likeSong();
                    case "9" -> displayCurrentSongInfo();
                    case "10" -> {
                        loggedInUser = null;
                        System.out.println("Logged out successfully.");
                        return;
                    }
                    case "EXIT" -> System.out.println("Exiting the program...");
                    default -> throw new IllegalArgumentException("Invalid choice. Try again.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (!choice.equals("EXIT"));
    }

    private static void manageArtistProfile() {
        if (loggedInUser.getArtistProfile() == null) {
            System.out.println("You are not an artist.");
            return;
        }
    
        System.out.println("\n=== Manage Artist Profile ===");
        System.out.println("1. Upload Song");
        System.out.println("2. Delete Song");
        System.out.println("3. View Uploaded Songs");
        System.out.println("4. View Earnings");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().trim();
    
        switch (choice) {
            case "1" -> uploadSong();
            case "2" -> deleteSong();
            case "3" -> loggedInUser.getArtistProfile().viewUploadedSongs();
            case "4" -> loggedInUser.getArtistProfile().calculateEarnings();
            case "0" -> mainMenu();
            default -> System.out.println("Invalid choice.");
        }
    }
    

    // Profile management
    private static void manageProfile() {
        System.out.println("\n=== Profile Management ===");
        System.out.println("1. View Profile");
        System.out.println("2. Update Profile");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> loggedInUser.viewProfile();
            case "2" -> updateProfile();
            case "0" -> mainMenu();
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void searchSongsAndPlaylists() {
        System.out.println("\n=== Search ===");
        System.out.println("1. Search Song by Name");
        System.out.println("2. Search Playlist by Name");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().trim();
    
        switch (choice) {
            case "1" -> {
                System.out.print("Enter song name: ");
                String songName = scanner.nextLine().trim();
                songBase.searchSongByName(songName); // Call from SongBase
            }
            case "2" -> {
                System.out.print("Enter playlist name: ");
                String playlistName = scanner.nextLine().trim();
                songBase.searchPlaylistByName(playlistName); // Call from SongBase
            }
            default -> System.out.println("Invalid choice.");
        }
    }
    
    private static void updateProfile() {
        System.out.print("Enter new username: ");
        String newUsername = scanner.nextLine();
        System.out.print("Enter new email: ");
        String newEmail = scanner.nextLine();
        loggedInUser.updateProfile(newUsername, newEmail);
        System.out.println("Profile updated successfully.");
    }

    // Play song options
    private static void playSongOptions() {
        System.out.println("\n=== Play Song ===");
        System.out.println("1. Search and Play Song");
        System.out.println("2. Search and Play from Playlist");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> playSong();
            case "2" -> playFromPlaylist();
            case "0" -> mainMenu();
            default -> System.out.println("Invalid choice.");
        }
    }


    // Like a Song
    private static void likeSong() {
        System.out.print("Enter song name to like: ");
        String songName = scanner.nextLine();
        Song song = songBase.searchSong(songName);

        if (song != null) {
            loggedInUser.likeSong(song);
        } else {
            System.out.println("Song not found.");
        }
    }

    private static void playSong() {
        System.out.print("Enter song name: ");
        String songName = scanner.nextLine();
        Song song = songBase.searchSong(songName);

        if (song != null) {
            loggedInUser.playSong(song);
        } else {
            System.out.println("Song not found.");
        }
    }

    private static void playFromPlaylist() {
        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine();
        loggedInUser.searchAndPlayPlaylist(playlistName);
    }

    // Manage playlists
    private static void managePlaylists() {
        System.out.println("\n=== Playlist Management ===");
        System.out.println("1. Create Playlist");
        System.out.println("2. Add Song to Playlist");
        System.out.println("3. Delete Playlist");
        System.out.println("0. Back to Main Menu");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine().trim();

        switch (choice) {
            case "1" -> createPlaylist();
            case "2" -> addSongToPlaylist();
            case "3" -> deletePlaylist();
            case "0" -> mainMenu();
            default -> System.out.println("Invalid choice.");
        }
    }

    private static void createPlaylist() {
        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();
        loggedInUser.createPlaylist(name);
        System.out.println("Playlist '" + name + "' created.");
    }

    private static void addSongToPlaylist() {
        System.out.print("Enter playlist name: ");
        String playlistName = scanner.nextLine().trim(); // Trim whitespace
    
        System.out.print("Enter song name: ");
        String songName = scanner.nextLine().trim(); // Trim whitespace
    
        // Search for the song in the SongBase
        Song song = songBase.searchSong(songName);
        if (song == null) {
            System.out.println("Song not found.");
            return; // Stop if the song isn't found
        }
    
        // Delegate the task to the User's method
        loggedInUser.addToPlaylist(playlistName, song);
    }
    
    private static void uploadSong() {
        if (!loggedInUser.isArtist()) {
            System.out.println("You must be an artist to upload songs.");
            return;
        }
    
        System.out.print("Enter song name: ");
        String songName = scanner.nextLine();
        System.out.print("Enter artist name: ");
        String artistName = loggedInUser.getUsername(); // Use the logged-in user's name
    
        // Use the static method from the Song class to get a valid duration
        int duration = Song.getValidDuration();
    
        System.out.print("Enter genre: ");
        String genre = scanner.nextLine();
        System.out.print("Is this song explicit? (true/false): ");
        boolean isExplicit = Boolean.parseBoolean(scanner.nextLine());
    
        Song newSong = new Song(songName, artistName, duration, genre, LocalDate.now(), isExplicit);
        loggedInUser.getArtistProfile().uploadSong(newSong);
        System.out.println("Added song: " + songName);
    }
    

    // Display current song information
    private static void displayCurrentSongInfo() {
        Song currentSong = loggedInUser.getOnPlayingSong();
        if (currentSong != null) {
            System.out.println("\n=== Current Song Info ===");
            System.out.println("Title: " + currentSong.getName());
            System.out.println("Artist: " + currentSong.getArtistName());
            System.out.println("Genre: " + currentSong.getGenre());
            System.out.println("Duration: " + currentSong.getDuration() + " seconds");
            System.out.println("Release Date: " + currentSong.getReleaseDate());
            System.out.println("Play Count: " + currentSong.getPlayCount());
            System.out.println("Explicit: " + (currentSong.isExplicit() ? "Yes" : "No"));
        } else {
            System.out.println("No song is currently playing.");
        }
    }
    
    private static void deleteSong() {
        if (!loggedInUser.isArtist()) {
            System.out.println("You must be an artist to delete songs.");
            return;
        }
    
        System.out.print("Enter song name to delete: ");
        String songName = scanner.nextLine();
    
        List<Song> uploadedSongs = loggedInUser.getArtistProfile().getUploadedSongs();

        Song songToDelete = uploadedSongs.stream()
                                         .filter(song -> song.getName().equalsIgnoreCase(songName))
                                         .findFirst()
                                         .orElse(null);
    
        if (songToDelete != null) {
            loggedInUser.getArtistProfile().deleteSong(songToDelete);
        } else {
            System.out.println("Song not found.");
        }
    }
    

    private static void deletePlaylist() {
        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();
        loggedInUser.deletePlaylist(name);
    }

    public static void applyForArtist() {
        if (loggedInUser.isArtist()) {
            System.out.println(loggedInUser.getUsername() + " is already an artist.");
            return;
        }
    
        // Display artist type policies
        System.out.println("\n=== Artist Policy Information ===");
        System.out.println("1. Individual Artist:");
        System.out.println("   - Immediate song uploads.");
        System.out.println("   - Songs can be deleted at any time.");
        System.out.println("   - Keep 100% of your streaming revenue.");
        System.out.println();
        System.out.println("2. Signed Artist:");
        System.out.println("   - Songs are uploaded with a 1-week delay.");
        System.out.println("   - Songs can only be deleted 1 year after upload.");
        System.out.println("   - Platform takes 30% of your revenue.");
        System.out.println("   - Contract needs to be renewed yearly.");
        System.out.println();
    
        System.out.print("Apply as (individual/signed): ");
        String type = scanner.nextLine().trim().toLowerCase();
    
        switch (type) {
            case "individual" -> loggedInUser.setArtistProfile(new IndividualArtist(loggedInUser, songBase));
            case "signed" -> loggedInUser.setArtistProfile(new SignedArtist(loggedInUser, songBase));
            default -> {
                System.out.println("Invalid artist type. Please try again.");
                return;
            }
        }
    
        loggedInUser.setArtist(true);
        System.out.println(loggedInUser.getUsername() + " is now a " + type + " artist.");
    }
    


}
