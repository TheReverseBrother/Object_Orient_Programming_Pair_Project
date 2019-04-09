package Client;

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


            if (input.matches("[Qq][uU][iI][tT]"))
            {
                selected = true;
                if(Client.isConnected())
                {
                    System.out.println("TODO Send sever disconnect request");
                    Client.setConnectionToFalse();
                }
                else {System.out.println("Goodbye hope to see you later");}

            }
            else if (input.matches("[lL][oO][gG][iI][nN]"))
            {
                System.out.println("test");
                selected = true;

            }
            //Register
            else if (input.matches("[rR][eE][gG][iI][sS][tT][eE][rR]"))
            {
                System.out.println("test");
                selected = true;

            }

            else
                {
                    System.out.println("invalid input please type a recognised command");
                    System.out.println();
                }

        }
    }
}
