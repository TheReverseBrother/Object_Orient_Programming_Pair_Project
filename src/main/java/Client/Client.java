package Client;

public class Client
{
    private static boolean connection = false;
    public static boolean isConnected(){return connection;}
    public static void setConnectionToTrue(){connection = true;}
    public static  void setConnectionToFalse(){connection =  false;}
    public static  ClientServerConnection ClientServer = new ClientServerConnection();
    public static int USERID = -1;

    public static void main(String[] args)
    {
        Menus.mainMenu();
    }

}
