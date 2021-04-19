

public interface PlaylistDAO {
    void savePlaylist(Playlist playlist);
    void deletePlaylist(Playlist playlist);
    Playlist getPlaylistByUser(String username);
    Playlist getPlaylistById(int id);
}
