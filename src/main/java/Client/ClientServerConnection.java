package Client;

import CoreDetails.MovieDBDetails;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientServerConnection
{
    private Socket dataSocket =  null;
    private OutputStream out = null;
    private PrintWriter output = null;
    private InputStream in = null;
    private Scanner input = null;
    public ClientServerConnection()
    {
        try
        {
            dataSocket = new Socket("127.0.0.1", MovieDBDetails.SERVER_PORT);

            out = dataSocket.getOutputStream();
            output = new PrintWriter(new OutputStreamWriter(out));

            in = dataSocket.getInputStream();
            input = new Scanner(new InputStreamReader(in));
        }
        catch(Exception e)
        {
            System.out.println("Well fuck");
        }
    }

    public JSONArray FetchingArray(String message)
    {
        output.println(message);
        output.flush();
        String respone = input.nextLine();
        if (respone.equals("null"))
        {
            return null;
        }

        JSONArray ja = new JSONArray(respone);
        return ja;

    }

    public JSONObject fetchObject(String message)
    {
        output.println(message);
        output.flush();
        String respone = input.nextLine();
        if (respone.equals("null"))
        {
            return null;
        }
        JSONObject returnMessage = new JSONObject(respone);
        return returnMessage;
    }

    public String fetchString(String message)
    {
        output.println(message);
        output.flush();

        String returnMessage = input.nextLine();
        if (returnMessage.equals("null"))
        {
            return null;
        }
        return returnMessage;
    }

    public void closeConnection(String message)
    {
        output.println(message);
        output.flush();
        try {
            output.close();
            dataSocket.close();
        }catch(IOException e)
        {
            e.printStackTrace();
        }
    }
}
