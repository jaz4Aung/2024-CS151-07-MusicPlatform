package musicplatform;

import java.util.ArrayList;
import java.util.List;

public class SongBase {
    private List<Song> allSongs;
    private List<Playlist> allPlaylists;

    public SongBase() {
        this.allSongs = new ArrayList<>();
        this.allPlaylists = new ArrayList<>();
    }

    // Add a song to the database
    public void addSong(Song song) {
        if (song == null) {
            System.out.println("Cannot add a null song.");
            return;
    }
        allSongs.add(song);
    }

    // Add a playlist to the database
    public void addPlaylist(Playlist playlist) {
        if (playlist == null) {
        System.out.println("Cannot add a null playlist.");
        return;
    }
        allPlaylists.add(playlist);

    }

    // Removes the song if it exists
    public boolean removeSong(Song song) {
        
        if (song == null || !allSongs.contains(song)) {
        System.out.println("Song not found in the database.");
        return false;
        }
        allSongs.remove(song);
        System.out.println("Song '" + song.getName() + "' removed from the database.");
        return true;
    }

    // Search and display song details by name
    public void searchSongByName(String name) {
        Song song = searchSong(name);
        if (song != null) {
            System.out.println("Song found: " + song.getName() + " by " + song.getArtistName());
            System.out.println("Genre: " + song.getGenre() + ", Duration: " + song.getDuration() + " seconds");
            System.out.println("Explicit: " + song.isExplicit() + ", Play Count: " + song.getPlayCount());
        } else {
            System.out.println("Song not found.");
        }
    }

    // Search for a song by name
    public Song searchSong(String name) {
        if (name == null || name.trim().isEmpty()) {
            System.out.println("Song name cannot be empty.");
            return null;
        }
        for (Song song : allSongs) {
            if (song.getName().equalsIgnoreCase(name.trim())) {
                return song;
            }
        }
        return null;
    }

    // Search and display playlist details by name
    public void searchPlaylistByName(String name) {
        Playlist playlist = searchPlaylist(name);
        if (playlist != null) {
            System.out.println("Playlist: " + playlist.getName());
            System.out.println("Songs in Playlist:");
            playlist.getSongs().forEach(song -> System.out.println("- " + song.getName()));
        } else {
            System.out.println("Playlist not found.");
        }
    }

    // Search for a playlist by name
    public Playlist searchPlaylist(String name) {
        for (Playlist playlist : allPlaylists) {
            if (playlist.getName().equalsIgnoreCase(name.trim())) {
                return playlist;
            }
        }
        return null;
    }
}
