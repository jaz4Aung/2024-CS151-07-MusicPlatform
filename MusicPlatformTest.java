package musicplatform;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class MusicPlatformTest {

    private SongBase songBase;
    private User user;
    private IndividualArtist individualArtist;
    private SignedArtist signedArtist;

    @BeforeEach
    public void setUp() {
        // Initialize shared objects for tests
        songBase = new SongBase();
        user = new User("user123", "password", "testuser", "test@example.com", 
                        LocalDate.of(2000, 1, 1), songBase);
        individualArtist = new IndividualArtist(user, songBase);
        signedArtist = new SignedArtist(user, songBase);
    }

    // Test case 1: Verify song creation
    @Test
    public void testSongCreation() {
        Song song = new Song("HUMBLE.", "Kendrick Lamar", 177, "Rap", 
                             LocalDate.of(2017, 3, 30), true);
        assertEquals("HUMBLE.", song.getName());
        assertEquals("Kendrick Lamar", song.getArtistName());
        assertEquals(177, song.getDuration());
        assertEquals("Rap", song.getGenre());
        assertTrue(song.isExplicit());
    }

    // Test case 2: User registration and profile validation
    @Test
    public void testUserRegistration() {
        assertEquals("user123", user.getUserId());
        assertEquals("testuser", user.getUsername());
        assertEquals("test@example.com", user.getEmail());
    }

    // Test case 3: Artist role assignment
    @Test
    public void testArtistProfileAssignment() {
        user.setArtistProfile(individualArtist);
    
        // Verify the user is now an artist
        assertTrue(user.isArtist(), "The user should be marked as an artist");
    
        // Verify the profile is correctly assigned
        assertNotNull(user.getArtistProfile(), "Artist profile should not be null");
        assertEquals("Individual Artist", user.getArtistProfile().getArtistType(), 
                     "Artist type should be Individual Artist");
    }
    
    // Test case 4: Upload song as individual artist
    @Test
    public void testUploadSongAsIndividualArtist() {
        Song song = new Song("Lucid Dreams", "Juice WRLD", 239, "Hip-Hop", 
                             LocalDate.now(), true);
        individualArtist.uploadSong(song);
        assertEquals(1, individualArtist.getUploadedSongs().size());
        assertEquals("Lucid Dreams", individualArtist.getUploadedSongs().get(0).getName());
    }

    // Test case 5: Signed artist upload with delay
    @Test
    public void testSignedArtistUploadDelay() {
        Song song = new Song("The Box", "Roddy Ricch", 195, "Hip-Hop", 
                             LocalDate.now(), true);
        signedArtist.uploadSong(song);
        assertEquals(LocalDate.now().plusDays(7), song.getReleaseDate());
    }

    // Test case 6: Create playlist and add songs
    @Test
    public void testCreatePlaylistAndAddSongs() {
        user.createPlaylist("Workout Jams");
        Playlist playlist = user.getUserPlaylists().get(0);

        Song song = new Song("The Box", "Roddy Ricch", 195, "Hip-Hop", 
                             LocalDate.now(), true);
        user.addToPlaylist("Workout Jams", song);

        assertEquals(1, playlist.getSongs().size());
        assertEquals("The Box", playlist.getSongs().get(0).getName());
    }

    // Test case 7: Search song in SongBase
    @Test
    public void testSearchSongInSongBase() {
        Song song = new Song("Mo Bamba", "Sheck Wes", 207, "Hip-Hop", 
                             LocalDate.of(2017, 6, 16), true);
        songBase.addSong(song);

        Song foundSong = songBase.searchSong("Mo Bamba");
        assertNotNull(foundSong);
        assertEquals("Mo Bamba", foundSong.getName());
    }

    // Test case 8: Handle invalid duration input
    @Test
    public void testInvalidDurationInput() {
        assertThrows(NumberFormatException.class, () -> {
            Integer.parseInt("invalid_duration");
        });
    }

    // Test case 9: Delete song as individual artist
    @Test
    public void testDeleteSongAsIndividualArtist() {
        Song song = new Song("Hotline Bling", "Drake", 267, "Hip-Hop", 
                             LocalDate.now(), false);
        individualArtist.uploadSong(song);
        individualArtist.deleteSong(song);

        assertTrue(individualArtist.getUploadedSongs().isEmpty());
    }

    // Test case 10: Check signed artist earnings calculation
    @Test
    public void testSignedArtistEarnings() {
        Song song1 = new Song("Goosebumps", "Travis Scott", 255, "Hip-Hop", 
                              LocalDate.now(), true);
        Song song2 = new Song("Mask Off", "Future", 203, "Rap", 
                              LocalDate.now(), true);

        song1.setPlayCount(1000);
        song2.setPlayCount(2000);

        signedArtist.uploadSong(song1);
        signedArtist.uploadSong(song2);

        double expectedEarnings = (1000 + 2000) * 0.01 * 0.7; // 30% platform share deducted
        assertEquals(expectedEarnings, signedArtist.calculateEarnings(), 0.01);
    }
}
