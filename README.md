#Music Platform Project - Bowen Ni
#Overview
The MusicPlatform project is a simulation of a real-world music streaming platform developed in Java. The platform allows users to register, follow artists, create playlists, and stream songs. Users can also apply to become artists with two types available: Individual Artist and Signed Artist, each offering unique capabilities and restrictions.

This project applies key Object-Oriented Programming (OOP) principles, including inheritance, abstraction, polymorphism, and interfaces, to build a scalable and realistic system.

#Design
###Class Structure
-User: Represents individual users who can manage playlists, rate songs, and apply to become artists.

-Artist (Abstract Class): Parent class for both Individual Artist and Signed Artist roles.

-Individual Artist: Can upload and delete songs instantly and keep 100% of earnings.
Signed Artist: Requires 1-week verification for uploads and can delete songs only after 1 year. Signed artists share revenue with the platform (70% to the artist, 30% to the platform).
Song: Represents individual songs with attributes such as name, artist, genre, and play count.

-Playlist: Manages collections of songs, allowing users to play and rate them.

-SongBase: Stores all songs and playlists available on the platform.

###Interfaces
-Playable: Defines methods to control playback, such as play, pause, stop, and restart.
-Rateable: Enables rating functionality, providing methods to rate songs and playlists and calculate average ratings.

###Design Considerations
-Separation of Concerns: Each class is designed with specific responsibilities, ensuring clear division of tasks.
-Encapsulation: All private attributes are accessed and modified through appropriate getters and setters.
-Polymorphism,Inheritance: Abstract classes and interfaces allow for flexible and extendable code.
-Scalability: The system is designed with interfaces that allow easy future expansion.

#Usage
###Key Functionalities
1.User Management
-Apply for Artist Role: Users can apply to become either Individual Artists or Signed Artists.
-Follow Artists: Users can follow artists to receive updates.

2.Song and Playlist Management
-Upload Songs:
    -Individual Artists can upload songs instantly.
    -Signed Artists require a 1-week verification period.
-Manage Playlists: Users can create custom playlists, add songs, and rate them.

3.Contract and Revenue Management
-Contract Renewal: Signed Artists can renew their contracts yearly.
-Song Deletion: Individual Artists can delete songs anytime, while Signed Artists must wait a year after upload to delete songs.

4.Streaming and Rating
-Play Songs and Playlists: Users can play, pause, and stop songs and playlists.
-Rate Songs and Playlists: Users can rate songs and playlists with scores between 1 and 10.

#Installation Instructions
1.Clone the Repository

git clone https://github.com/bowen-ni/2024-CS151-07-MusicPlatform.git
cd 2024-CS151-07-MusicPlatform

2.Compile the Java Files
Run the following command inside the project directory:
javac -d bin -sourcepath src src/musicplatform/*.java

3.Run the Program
java -cp bin musicplatform.Main