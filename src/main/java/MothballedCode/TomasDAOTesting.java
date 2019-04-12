package MothballedCode;

import DAOs.MysqlMovieDAO;
import DAOs.MysqlUserDAO;
import DAOs.WatchedDAO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Random;

public class TomasDAOTesting
{
    public static void main(String[] args)
    {
        MysqlMovieDAO movie = new MysqlMovieDAO();
        MysqlUserDAO user = new MysqlUserDAO();
        WatchedDAO watched = new WatchedDAO();

        watched.removeUserWatched("2");

//        try
//        {
//
//            JSONObject ja = movie.findMovieByTitle("tammy");
//            System.out.println(ja.toString());
//        }catch(Exception e)
//        {
//
//        }

    }

}
