package DAOs;

import org.json.JSONArray;
import org.json.JSONObject;

public interface UserDAOInterface
{
    public JSONArray findAllUsers() throws Exceptions.DAOException;
    public JSONObject findUserByUsernamePassword(String username, String Password) throws Exceptions.DAOException;
}
