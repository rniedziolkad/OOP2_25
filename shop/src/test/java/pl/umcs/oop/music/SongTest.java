package pl.umcs.oop.music;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.umcs.oop.database.DatabaseConnection;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class SongTest {
    @BeforeAll
    public static void connectToDatabase() {
        URL resource = DatabaseConnection.class
                .getClassLoader()
                .getResource("songs.db");

        assertNotNull(resource);

        DatabaseConnection.connect(resource.getFile());
    }
    @Test
    public void testReadValidIndexReturnsSong() throws SQLException {
        Optional<Song> result = Song.Persistance.read(7);

        assertTrue(result.isPresent());

        Song song = result.get();
        assertEquals("The Doors", song.artist());
        assertEquals("Light My Fire", song.title());
        assertEquals(426, song.duration());
    }
    @Test
    public void testReadInvalidIndexReturnsEmpty() throws SQLException {
        Optional<Song> result = Song.Persistance.read(50);

        assertTrue(result.isEmpty());
    }
    @AfterAll
    public static void disconnectFromDatabase() {
        DatabaseConnection.disconnect();
    }
}
