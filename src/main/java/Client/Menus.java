package Client;

import CoreDetails.MovieDBDetails;
import Exceptions.DAOException;
import org.json.JSONArray;

import java.util.Scanner;


public class Menus
{


    public static void mainMenu()
    {
        boolean selected = false;

        while (!selected)
        {

            Scanner keyboard = new Scanner(System.in);
            System.out.println("type in the command you wish to execute");
            System.out.println("Login");
            System.out.println("Register");
            System.out.println("Quit");
            String selectionInput = keyboard.nextLine();


            // Quit

            if (selectionInput.matches("[Qq][uU][iI][tT]"))
            {
                selected = true;
                if (Client.isConnected())
                {
                    Client.ClientServer.fetchString("8££Exiting");
                }
                else
                {
                    System.out.println("Goodbye hope to see you later");
                }

            }

            //Login
            else if (selectionInput.matches("[lL][oO][gG][iI][nN]"))
            {
                loginMenu();
                selected = true;

            }
            //Register
            else if (selectionInput.matches("[rR][eE][gG][iI][sS][tT][eE][rR]"))
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
        int user_ID = Integer.parseInt(userID);
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
                searchMenu(user_ID);

            }
            else if (selectionInput.matches("^[Ee][Dd][Ii][Tt]?[ ]*\\w*"))
            {
                selected = true;
                Client.editMovie(user_ID);

            }
            else if (selectionInput.matches("^[Gg][Ee][Tt][Tt]?[ ]*\\w*") || selectionInput.matches("^[Ww][Aa][Tt][Cc][Hh][Ee][Dd]?[ ]*\\w*"))
            {
                selected = true;
                Client.getWatchedMovies(user_ID);

            }
            else if (selectionInput.matches("^[Aa][Dd][Dd]?[ ]*\\w*"))
            {
                selected = true;
                Client.addMovieToWatched(user_ID);
            }
            else if (selectionInput.matches("^[Ll][Oo][Gg][Oo][Uu][Tt]"))
            {
                selected = true;
                mainMenu();
            }
            else
            {
                System.out.println("Unrecognized Command");
            }

        }
    }

    private static void searchMenu(int user_ID)
    {
    }
}
