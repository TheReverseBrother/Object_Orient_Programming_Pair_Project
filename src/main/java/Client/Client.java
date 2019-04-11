package Client;

import DAOs.MysqlMovieDAO;
import DAOs.MysqlUserDAO;
import org.json.JSONArray;
import org.json.JSONObject;

public class Client
{
    private static boolean connection = false;

    public static boolean isConnected()
    {
        return connection;
    }

    public static void setConnectionToTrue()
    {
        connection = true;
    }

    public static void setConnectionToFalse()
    {
        connection = false;
    }

    public static ClientServerConnection ClientServer = new ClientServerConnection();
    public static int USERID = -1;
    public static MysqlUserDAO userDAO = new MysqlUserDAO();
    public static MysqlMovieDAO movieDAO = new MysqlMovieDAO();

    public static void main(String[] args)
    {
        Menus.mainMenu();
    }

    public static void editMovie(int user_ID)
    {
    }

    public static void getWatchedMovies(int user_ID)
    {
    }

    public static void addMovieToWatched(int user_ID)
    {
    }

    public static void formatJSONMovie(JSONArray array)
    {
        JSONObject test;
        System.out.println("████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████");

        System.out.format("█"+"%40s%60s%20s%30s%60s%20s", "Title", "Genre", "Rating", "Director", "Starring","█");
        System.out.println();
        System.out.println("████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████");

        for (int i = 0; i < array.length(); i++)
        {

            test = array.getJSONObject(i);

            System.out.format("█"+"%40s%60s%20s%30s%78s%2s", "\"" + test.get("title") + "\"", test.get("genre"), test.get("user_rating"), test.get("director"), test.get("starring"),"█");
            System.out.println();

        }
        System.out.println("████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████");



    }
}