package Client;

import DAOs.MysqlMovieDAO;
import DAOs.MysqlUserDAO;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;

public class Client
{
    private static boolean connection = false;

    public static ClientServerConnection ClientServer = new ClientServerConnection();
    public static int USERID = -1;

    public static void main(String[] args)
    {
        Menus.mainMenu();
    }


    public static void formatJSONMovie(JSONArray array)
    {
        JSONObject obj;
        System.out.println("████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████");

        System.out.format("█"+"%40s%60s%20s%30s%60s%20s", "Title", "Genre", "Rating", "Director", "Starring","█");
        System.out.println();
        System.out.println("████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████");

        for (int i = 0; i < array.length(); i++)
        {

            obj = array.getJSONObject(i);
            System.out.format("█"+"%40s%60s%20s%30s%78s%2s", "\"" + obj.get("title") + "\"", obj.get("genre"), obj.get("user_rating"), obj.get("director"), obj.get("starring"),"█");
            System.out.println();

        }
        System.out.println("████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████████");
    }

    public  static void logout()
    {
        Menus.mainMenu();
    }


    public static void updateMenu()
    {
        boolean picked = false;

        while (!picked)
        {
            Scanner keyboard = new Scanner(System.in);
            System.out.println("Enter the name of the move you would like to rate");
            String title = keyboard.nextLine();
            System.out.println("Enter Rating you would like to give this movie");
            String rating = keyboard.nextLine();

            ClientServer.fetchString("5££"+rating+":"+title+"££"+Client.USERID);


        }



    }


}


