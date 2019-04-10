package DAOs;

import org.json.JSONArray;
import org.json.JSONObject;

public interface UserDAOInterface
{
    public String findUserByUsernamePassword(String username, String Password) throws Exceptions.DAOException;
    public boolean registerUser(String Username, String Password);
}
