package Test;

import DAOs.MysqlMovieDAO;
import DAOs.WatchedDAO;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WatchedDAOTest {

    WatchedDAO watched = null;
    JSONObject expectedObject = null;
    JSONArray expectedArray = null;
    JSONObject resultObject = null;
    JSONArray resultArray = null;
    String expected = null;
    String result = null;

    @Before
    public void before()
    {
        watched = new WatchedDAO();
    }

    @Test
    public void getAllWatchedMovies()
    {

    }

    @Test
    public void addtoWatched() {
    }

    @Test
    public void checkIfWatched() {
    }

    @Test
    public void getAllWatchedMoviesForClient() {
    }
}