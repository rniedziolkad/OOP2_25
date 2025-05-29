package pl.umcs.oop.music;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlaylistTest {
    @Test
    public void testNewPlaylistIsEmpty() {
        Playlist playlist = new Playlist();
        assertTrue(playlist.isEmpty());
    }
    @Test
    public void testAddOneSizeOne() {
        Playlist playlist = new Playlist();

        playlist.add(new Song("art1", "tit1", 100));

        assertEquals(1, playlist.size());
    }
    @Test
    public void testAddSongPlaylistContainsExactInstance() {
        Playlist playlist = new Playlist();
        Song song = new Song("a1", "t1", 100);

        playlist.add(song);

        assertEquals(song, playlist.getFirst());
    }

    @Test
    public void testAddSongPlaylistContainsEquivalentSong() {
        Playlist playlist = new Playlist();
        Song song = new Song("a1", "t1", 100);

        playlist.add(song);

        assertEquals(new Song("a1", "t1", 100), playlist.getFirst());
    }

    @Test
    public void testAtSecondReturnsCorrectSong() {
        Playlist playlist = new Playlist();
        Song s1 = new Song("A", "tit1", 100);
        Song s2 = new Song("B", "tit2", 100);
        Song s3 = new Song("C", "tit3", 100);
        playlist.add(s1);
        playlist.add(s2);
        playlist.add(s3);

        assertEquals(s1, playlist.atSecond(0));
        assertEquals(s1, playlist.atSecond(99));

        assertEquals(s2, playlist.atSecond(100));
        assertEquals(s2, playlist.atSecond(199));

        assertEquals(s3, playlist.atSecond(200));
        assertEquals(s3, playlist.atSecond(299));
    }

    @Test
    public void testAtSecondThrowsExceptionForNegativeTime() {
        Playlist playlist = new Playlist();
        playlist.add(new Song("A", "tit1", 100));

        IndexOutOfBoundsException e = assertThrows(
                IndexOutOfBoundsException.class,
                () -> playlist.atSecond(-1)
        );

        assertEquals("Czas nie może być ujemny: -1", e.getMessage());

    }


}
