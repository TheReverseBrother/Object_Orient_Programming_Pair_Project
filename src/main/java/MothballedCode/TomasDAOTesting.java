package MothballedCode;

import DAOs.MysqlMovieDAO;
import DAOs.WatchedDAO;
import org.json.JSONArray;
import org.json.JSONObject;

public class TomasDAOTesting
{
    public static void main(String[] args)
    {
        MysqlMovieDAO movie = new MysqlMovieDAO();
        WatchedDAO watched = new WatchedDAO();
        JSONArray watch = watched.GetAllWatchedMovies("2");
        System.out.println("Watch"+watch);
        String genre = movie.getGenres("tammy");
        System.out.println(genre);
        try
        {

            JSONObject ja = movie.findMovieByTitle("tammy");
            System.out.println(ja.toString());
        }catch(Exception e)
        {

        }

    }
}
