package DAOs;

import DTOs.User;
import java.util.List;

public interface UserDAOInterface
{
    public List<User> findAllUsers() throws Exceptions.DAOException;
    public User findUserByUsernamePassword(String username, String Password) throws Exceptions.DAOException;
}
