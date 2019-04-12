package DAOs;

import Exceptions.DAOException;
import org.json.JSONArray;
import org.json.JSONObject;

public interface MovieDAOInterface
{
    public JSONArray findAllMovies();
    public JSONObject findMovieByTitle(String title);
    public JSONArray findMovieByDirector(String Director);
    public JSONArray findMovieByActor(String Actor);
    public String getGenres(String title);
    public String updateMovieByID(JSONObject movie);
    public JSONObject findWatchedMovie(String userID);
    public String updateMovieByTitle(String movie);
    public JSONArray findMovieByGenre(String GENRE);
}
