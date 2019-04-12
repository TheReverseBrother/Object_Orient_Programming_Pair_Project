package DAOs;

import Exceptions.DAOException;
import org.json.JSONArray;
import org.json.JSONObject;

public interface MovieDAOInterface
{
    public JSONArray findAllMovies() throws DAOException;
    public JSONObject findMovieByTitle(String title) throws DAOException;
    public JSONArray findMovieByDirector(String Director) throws DAOException;
    public JSONArray findMovieByActor(String Actor) throws DAOException;
    public String updateMovieByID(JSONObject movie);
    public String getGenres(String title);
    public JSONArray getMoviesByGenres(String GenreOne,String GenreTwo);
}
