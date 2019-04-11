package MothballedCode;

import DAOs.MysqlMovieDAO;
import org.json.JSONObject;

public class TomasDAOTesting
{
    public static void main(String[] args)
    {
        MysqlMovieDAO movie = new MysqlMovieDAO();

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
