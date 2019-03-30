package Client;

import java.util.Scanner;

public class Menus
{

    public static void InitialMenu()
    {
        System.out.println("1. Register");
        System.out.println("2. Login");
        System.out.println("3. Exit");
    }

    public static void RegisterMenu()
    {
        System.out.println("Please Enter Email");
        System.out.println("Please Enter Username");
        System.out.println("Please Enter Password");
    }

    public static void LoginMenu()
    {
        System.out.println("Please Enter Username");
        System.out.println("Please Enter Password");
    }

    public static void MainMenu()
    {
        System.out.println("1. Search Movies");
        System.out.println("2. Update Movies");
        System.out.println("3. Add Movie to Database");
        System.out.println("4. Add Movie to Watched");
        System.out.println("5. Display Watched Movies");
        System.out.println("4. Get Recommendation");
    }

    public static void SearchMenu()
    {
        System.out.println("Search Movies By: ");
        System.out.println("1. Title");
        System.out.println("2. Director");
        System.out.println("3. Actor/Actress");
    }


    public static int integerChecker()
    {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        int number = 0;
        while (check)
        {
            try
            {
                number = Integer.parseInt(sc.next());
                check = false;
            }
            catch(NumberFormatException ignore)
            {
                System.out.println("Please Enter Valid Number");
            }
        }
        return number;
    }
}
