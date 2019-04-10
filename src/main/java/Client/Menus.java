package Client;

import CoreDetails.MovieDBDetails;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.regex.Pattern;



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
            String input = keyboard.nextLine();



            // Quit

            if (input.matches("[Qq][uU][iI][tT]"))
            {
                selected = true;
                if(Client.isConnected())
                {
                  Client.ClientServer.fetchString("8££Exiting");
                }
                else {System.out.println("Goodbye hope to see you later");}

            }

            //Login
            else if (input.matches("[lL][oO][gG][iI][nN]"))
            {
                loginMenu();
                selected = true;

            }
            //Register
            else if (input.matches("[rR][eE][gG][iI][sS][tT][eE][rR]"))
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
        Scanner keyboard = new Scanner (System.in);

        String Email;
        String password;
        String confirmEmali;
        String confirmPassword;
        boolean registered = false;

        while (!registered)
        {

            System.out.println("Please enter the E-mail address that you would like to register your account to");
            Email = keyboard.nextLine();
            if (!Email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || Email.matches(".*?[£][£].*"))
            {
                System.out.println("please enter a valid E-mail");
                Email = keyboard.nextLine();
            }
            System.out.println("please confirm that "+Email+" is the correct E-mail address");

        }
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

            String logInMessage = "1££"+Email+"££"+password;

            try
            {

                String response =  Client.ClientServer.fetchString(logInMessage);
                responseArray = response.split(MovieDBDetails.BREAKINGCHARACTERS);


                if(responseArray[0].equals("true"))
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
            catch (Exception e){}


        }

        applicationMenu(responseArray[1]);


    }

    private static void applicationMenu(String userID)
    {
        boolean loggedIn = true;
        int user_ID = Integer.parseInt(userID);
        Scanner keyboard = new Scanner(System.in);


            System.out.println("Search Movie");
            System.out.println("Edit Movies");
            System.out.println("Get Watched Movies");
            System.out.println("Add to Watched Movies");
            System.out.println("Logout");

    }
}
