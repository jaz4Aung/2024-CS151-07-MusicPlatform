package musicplatform;

public interface Playable {
    // Play the item (song or playlist)
    void play();

    // Pause the item
    void pause();

    // Stop the item
    void stop();

    // Skip to the next item (in playlists)
    void skip();

    // Restart the item (song or playlist)
    void restart();
}
