package Client;

import CoreDetails.MovieDBDetails;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.Socket;
import java.util.LinkedHashMap;
import java.util.Scanner;

public class ClientServerConnection
{
    private Socket dataSocket =  null;
    private OutputStream out = null;
    private PrintWriter output = null;
    private InputStream in = null;
    private Scanner input = null;
    private LinkedHashMap<String, String> cache = new LinkedHashMap<>(5);

    public ClientServerConnection()
    {
        try
        {
            dataSocket = new Socket("10.102.11.17", MovieDBDetails.SERVER_PORT);

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
        if(cache.containsKey(message))
        {
            String cacheString = cache.get(message);
            JSONArray cached = new JSONArray(cacheString);
            return cached;
        }
        else
        {
            output.println(message);
            output.flush();
            String response = input.nextLine();

            if (response.equals("null"))
            {
                response = null;
            }

            cache.put(message,response);
            JSONArray ja = new JSONArray(response);
            return ja;
        }
    }

    public JSONObject fetchObject(String message)
    {
        if(cache.containsKey(message))
        {
            String cacheString = cache.get(message);
            JSONObject cached = new JSONObject(cacheString);
            return cached;
        }
        else
        {
            output.println(message);
            output.flush();
            String response = input.nextLine();

            if (response.equals("null"))
            {
                response = null;
            }

            cache.put(message,response);
            JSONObject ja = new JSONObject(response);
            return ja;
        }
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

    public void Updated()
    {
        cache.clear();
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
