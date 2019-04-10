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
                    try{
                    Socket dataSocket = new Socket("localhost", MovieDBDetails.SERVER_PORT);

                    OutputStream out = dataSocket.getOutputStream();

                    PrintWriter output = new PrintWriter(new OutputStreamWriter(out));

                    output.println("8££Exiting");
                    output.flush();
                }
                    catch(Exception e){}
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
                System.out.println("test");
                selected = true;

            }
            //default
            else
                {
                    System.out.println("invalid input please type a recognised command");
                    System.out.println();
                }

        }
    }

    private static void loginMenu()
    {
        Scanner keyboard = new Scanner(System.in);
        boolean loggedIn = false;
        String Email = "";
        String password = "";



        while (!loggedIn)
        {



                System.out.println("Please enter your E-mail");
                Email = keyboard.nextLine();

            // found regex online for email addresses @ http://www.regexlib.com/Search.aspx?k=email&c=-1&m=-1&ps=20 author Steven Smith the regex for the breaking characters is my own
            if (!Email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$") || Email.matches(".*?[£][£].*"))
            {
                System.out.println("invalid E-mail format please enter a valid email");
                loginMenu();
            }

                System.out.println("please enter Password");
                password = keyboard.nextLine();


            try
            {
                String logInMessage = "1££"+Email+"££"+password;
                Socket dataSocket = new Socket("localhost", MovieDBDetails.SERVER_PORT);

                OutputStream out = dataSocket.getOutputStream();
                InputStream in = dataSocket.getInputStream();

                PrintWriter output = new PrintWriter(new OutputStreamWriter(out));
                Scanner input = new Scanner(new InputStreamReader(in));

                output.println(logInMessage);
                output.flush();
                String response = input.nextLine();


                if(response.equals("true££1"))
                {
                    loggedIn = true;
                    applicationMenu();
                }
                else if(response.equals("false££Invalid Username And Password")){

                    System.out.println("E-mail or password incorrect");
                    loginMenu();
                }

            }
            catch (Exception e)
            {


            }






        }

    }

    private static void applicationMenu()
    {
        try{
            Socket dataSocket = new Socket("localhost", MovieDBDetails.SERVER_PORT);

            OutputStream out = dataSocket.getOutputStream();

            PrintWriter output = new PrintWriter(new OutputStreamWriter(out));

            output.println("8££Exiting");
            output.flush();
        }
        catch(Exception e){}

    }
}
