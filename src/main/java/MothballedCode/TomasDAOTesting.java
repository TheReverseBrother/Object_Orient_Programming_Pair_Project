package MothballedCode;

import DAOs.MysqlMovieDAO;
import DAOs.MysqlUserDAO;
import DAOs.WatchedDAO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class TomasDAOTesting
{

    public static MysqlMovieDAO movies = new MysqlMovieDAO();
    public static MysqlUserDAO user = new MysqlUserDAO();
    public static WatchedDAO watched = new WatchedDAO();
    public static void main(String[] args)
    {
        MysqlMovieDAO movies = new MysqlMovieDAO();
        MysqlUserDAO user = new MysqlUserDAO();
        WatchedDAO watched = new WatchedDAO();


//        JSONObject  ja = movies.findMovieByTitle("tammy");
        JSONArray ja = watched.GetAllWatchedMovies("1");
        System.out.println(ja.toString());



    }
}
