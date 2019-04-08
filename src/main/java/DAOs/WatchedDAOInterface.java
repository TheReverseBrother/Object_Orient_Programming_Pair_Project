package DAOs;

import org.json.JSONArray;

public interface WatchedDAOInterface
{
    public JSONArray GetAllWatchedMovies(String userID);
    public String AddtoWatched(String userID, String movieID, String title);
    public String CheckIfWatched();
}
