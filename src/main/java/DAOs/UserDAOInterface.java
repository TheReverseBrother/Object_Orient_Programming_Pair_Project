package DAOs;

import org.json.JSONArray;
import org.json.JSONObject;

public interface UserDAOInterface
{
    public String findUserByUsernamePassword(String username, String Password) throws Exceptions.DAOException;
    public String registerUser(String Username, String Password);
    public String deleteUser(String user);
}
