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

        JSONArray ja = new JSONArray(recommendMovie("1"));
        System.out.println(ja.length());

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

    public static String recommendMovie(String user)
    {
        JSONArray WatchedList = watched.GetAllWatchedMovies(user);
        Random rand = new Random();
        int i = rand.nextInt(WatchedList.length());
        JSONObject movie = WatchedList.getJSONObject(i);
        String title = movie.get("title").toString();
        JSONObject movie2 = movies.findMovieByTitle(title);
        String genre = movie2.getString("genre");

        String[] genres = genre.split(",");
        if(genres.length > 1)
        {
            int j = rand.nextInt(genres.length);
            JSONArray MoviesWithSimilarGenre = movies.findMovieByGenre(genres[j]);

            for(int x = 0 ; x < MoviesWithSimilarGenre.length(); ++x)
            {
                for (int y = 0; y < WatchedList.length(); ++y)
                {
                    if(MoviesWithSimilarGenre.getJSONObject(x).get("title")== WatchedList.getJSONObject(y).get("title"))
                    {
                        MoviesWithSimilarGenre.remove(x);
                    }
                }
            }
            return MoviesWithSimilarGenre.toString();
        }
        else
        {
            JSONArray MoviesWithSimilarGenre = movies.findMovieByGenre(genres[0]);

            for(int x = 0 ; x < MoviesWithSimilarGenre.length(); ++x)
            {
                for (int y = 0; y < WatchedList.length(); ++y)
                {
                    if(MoviesWithSimilarGenre.getJSONObject(x).get("movieID")== WatchedList.getJSONObject(y).get("movieID"))
                    {
                        MoviesWithSimilarGenre.remove(x);
                    }
                }
            }
            return MoviesWithSimilarGenre.toString();
        }
    }

}
