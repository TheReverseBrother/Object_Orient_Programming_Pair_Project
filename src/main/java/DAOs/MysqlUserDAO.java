package DAOs;

import Exceptions.DAOException;
import org.json.JSONArray;
import org.json.JSONObject;

public class MysqlUserDAO implements UserDAOInterface
{
    @Override
    public JSONArray findAllUsers() throws DAOException
    {
        return null;
    }

    @Override
    public JSONObject findUserByUsernamePassword(String username, String Password) throws DAOException
    {
        return null;
    }
}
