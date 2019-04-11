package Client;

import CoreDetails.MovieDBDetails;
import Exceptions.DAOException;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.Scanner;


public class Menus
{


    public static void mainMenu()
    {
        boolean selected = false;
        Client.USERID = -1;


        while (!selected)
        {

            Scanner keyboard = new Scanner(System.in);
            System.out.println("type in the command you wish to execute");
            System.out.println("Login");
            System.out.println("Register");
            System.out.println("Quit");
            String selectionInput = keyboard.nextLine();


            // Quit

            if (selectionInput.matches("^[Qq][uU][iI][tT]"))
            {
                selected = true;
                Client.ClientServer.closeConnection("8££Exiting");
                System.out.println("Goodbye Thank you for coming");


            }

            //Login
            else if (selectionInput.matches("^[lL][oO][gG][iI][nN]"))
            {
                loginMenu();
                selected = true;

            }
            //Register
            else if (selectionInput.matches("^[rR][eE][gG][iI][sS][tT][eE][rR]"))
            {
                selected = true;
                registerMenu();

            }
            //default
            else
            {
                System.out.println("invalid input please type a recognised command");
                System.out.println();
            }

        }
    }

    private static void registerMenu()
    {
        Scanner keyboard = new Scanner(System.in);

        String Email = "";
        String password = "";
        String confirmEmail;
        String confirmPassword;
        boolean registered = false;
        boolean hasEmail = false;
        boolean hasPassword = false;
        String responseArray[]=null;

        while (!registered)
        {
            while (!hasEmail)
            {
                System.out.println("Please enter the E-mail address that you would like to register your account to");
                Email = keyboard.nextLine();

                if (!Email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || Email.matches(".*?[£][£].*"))
                {
                    System.out.println("please enter a valid E-mail");
                    Email = keyboard.nextLine();
                }

                System.out.println("please confirm that " + Email + " is the correct E-mail address Y for yes and any other key for no");
                confirmEmail = keyboard.nextLine();

                if (confirmEmail.equals("Y") || confirmEmail.equals("y"))
                {
                    hasEmail = true;
                }
            }


            while (hasEmail && !hasPassword)
            {
                System.out.println("Please Create a Password");
                password = keyboard.nextLine();

                if (password.matches(".*[£][£].*"))
                {
                    System.out.println("please enter a valid password");
                    password = keyboard.nextLine();
                }

                System.out.println("please confirm your password by typing it in again");
                confirmPassword = keyboard.nextLine();

                if (!password.equals(confirmPassword))
                {
                    System.out.println("password did not match");

                }
                else
                {
                    hasPassword = true;
                }

            }

            responseArray = Client.ClientServer.fetchString("0££" + Email + "££" + password).split(MovieDBDetails.BREAKINGCHARACTERS);

            if (responseArray[0].equals("true")) {System.out.println(responseArray[1]); registered = true;}
            else
            {
                System.out.println("Account already ");
                System.out.println("Press y to continue to register or any other key to return to the main menu");
                String selected = keyboard.nextLine();

                if (!selected.equals("Y") && !selected.equals("y"))
                {
                    mainMenu();
                }

                hasEmail = false;
                hasPassword = false;
            }
        }

        mainMenu();
    }


    private static void loginMenu()
    {
        Scanner keyboard = new Scanner(System.in);
        boolean loggedIn = false;
        String Email = "";
        String password = "";
        String[] responseArray = null;

        System.out.println("Please enter your E-mail");
        Email = keyboard.nextLine();

        System.out.println("please enter Password");
        password = keyboard.nextLine();

        while (!loggedIn)
        {

            // found regex online for email addresses @ http://www.regexlib.com/Search.aspx?k=email&c=-1&m=-1&ps=20 author Steven Smith the regex for the breaking characters is my own
            if (!Email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || Email.matches(".*?[£][£].*"))
            {
                System.out.println("invalid E-mail format please enter a valid email");
                loginMenu();
            }

            String logInMessage = "1££" + Email + "££" + password;

            try
            {

                String response = Client.ClientServer.fetchString(logInMessage);
                responseArray = response.split(MovieDBDetails.BREAKINGCHARACTERS);


                if (responseArray[0].equals("true"))
                {
                    loggedIn = true;
                }
                else
                {
                    System.out.println("Invalid E-mail or password");
                    System.out.println("Please enter your E-mail");
                    Email = keyboard.nextLine();
                    System.out.println("please enter Password");
                    password = keyboard.nextLine();
                }
            }
            catch (Exception e)
            {
            }


        }

        applicationMenu(responseArray[1]);


    }

    private static void applicationMenu(String userID)
    {


        boolean selected = false;
        Client.USERID= Integer.parseInt(userID);
        Scanner keyboard = new Scanner(System.in);

        while (!selected)
        {

            System.out.println("Search for Movie");
            System.out.println("Edit Movies");
            System.out.println("Get Watched Movies");
            System.out.println("Add to Watched Movies");
            System.out.println("Logout");

            String selectionInput = keyboard.nextLine();

            if (selectionInput.matches("^[Ss][eE][aA][rR][Cc][Hh]?[ ]*\\w*"))
            {
                selected = true;
                searchMenu();

            }
            else if (selectionInput.matches("^[Ee][Dd][Ii][Tt]?[ ]*\\w*"))
            {
                selected = true;


            }
            else if (selectionInput.matches("^[Gg][Ee][Tt][Tt]?[ ]*\\w*") || selectionInput.matches("^[Ww][Aa][Tt][Cc][Hh][Ee][Dd]?[ ]*\\w*"))
            {
                selected = true;


            }
            else if (selectionInput.matches("^[Aa][Dd][Dd]?[ ]*\\w*"))
            {
                selected = true;

            }
            else if (selectionInput.matches("^[Ll][Oo][Gg][Oo][Uu][Tt]"))
            {
                selected = true;
                Client.logout();
            }
            else
            {
                System.out.println("Unrecognized Command");
            }

        }
    }

    private static void searchMenu()
    {
        boolean selected = false;
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Search By");
        System.out.println("Actor");
        System.out.println("Director");
        System.out.println("Title");
        System.out.println("Type 'Back' to return to the main menu");
        
        while (!selected)
        {
            String selectionInput = keyboard.nextLine();
            
            if(selectionInput.matches("^[Aa][Cc][Tt][Oo][Rr]"))
            {
                selected = true;
                searchByActor();
            }
            else if(selectionInput.matches("^[Dd][Ii][Rr][Ee][Cc][Tt][Oo][Rr]"))
            {
                selected = true;
                searchByDirector();
            }
            else if(selectionInput.matches("^[Tt][Ii][Tt][Ll][Ee]"))
            {
                selected = true;
                searchByTitle();

            }

            if(selectionInput.matches("^[Bb][Aa][Cc][Kk]$"))
            {
                selected = true;
                String userID = ""+Client.USERID;
                applicationMenu(userID);
            }
            else
            {
                System.out.println("Invalid command Please input valid command");
            }
        }
    }

    private static void searchByTitle()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Movie Title");
        String title = keyboard.nextLine();

        JSONObject movie = Client.ClientServer.fetchObject("3££"+title);
        JSONArray mArray = new JSONArray("[]");
        mArray.put(movie);
        if (movie == null){
            System.out.println("Movie not found");
            searchMenu();
        }
        else
        {
            Client.formatJSONMovie(mArray);
            searchMenu();
        }

    }

    private static void searchByDirector()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Director name");
        String director = keyboard.nextLine();

        JSONArray mArray = Client.ClientServer.FetchingArray("4££"+director);
        if (mArray == null){
            System.out.println("Movie not found");
            searchMenu();
        }
        else
        {
            Client.formatJSONMovie(mArray);
            searchMenu();
        }
    }

    private static void searchByActor()
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Enter Actor name");
        String actor = keyboard.nextLine();

        JSONArray mArray = Client.ClientServer.FetchingArray("2££"+actor);
        if (mArray == null){
            System.out.println("Movie not found");
            searchMenu();
        }
        else
        {
            Client.formatJSONMovie(mArray);
            searchMenu();
        }
    }
}
