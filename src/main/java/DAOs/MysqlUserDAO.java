package DAOs;

import DTOs.User;
import Exceptions.DAOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MysqlUserDAO extends MysqlDAO implements UserDAOInterface
{
    @Override
    public List<User> findAllUsers() throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM USERS";
            ps = con.prepareStatement(query);

            //User the prepared statement to execute SQL
            rs = ps.executeQuery();

            while(rs.next())
            {
                int userID = rs.getInt("ID");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String lastname = rs.getString("LAST_NAME");
                String firstname = rs.getString("FIRST_NAME");

                User u = new User(userID,firstname,lastname,username,password);
                users.add(u);
            }
        }
        catch(SQLException e)
        {
            throw new DAOException("findAllUsers() " + e.getMessage());
        }
        finally {
            try{
                if(rs !=null)
                {
                    rs.close();
                }
                if(ps != null)
                {
                    ps.close();
                }
                if( con != null)
                {
                    this.freeConnection(con);
                }
            }
            catch(SQLException e)
            {
                throw new DAOException("findAllUsers() "+ e.getMessage());
            }
        }

        return users;
    }

    @Override
    public User findUserByUsernamePassword(String uname, String pword) throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM USERS WHERE USERNAME = ? AND PASSWORD = ?";
            ps = con.prepareStatement(query);
            ps.setString(1,uname);
            ps.setString(2,pword);

            //User the prepared statement to execute SQL
            rs = ps.executeQuery();

            while(rs.next())
            {
                int userID = rs.getInt("ID");
                String username = rs.getString("USERNAME");
                String password = rs.getString("PASSWORD");
                String lastname = rs.getString("LAST_NAME");
                String firstname = rs.getString("FIRST_NAME");

                u = new User(userID,firstname,lastname,username,password);
            }
        }
        catch(SQLException e)
        {
            throw new DAOException("findUserByUsernamePassword() " + e.getMessage());
        }
        finally {
            try{
                if(rs !=null)
                {
                    rs.close();
                }
                if(ps != null)
                {
                    ps.close();
                }
                if( con != null)
                {
                    this.freeConnection(con);
                }
            }
            catch(SQLException e)
            {
                throw new DAOException("ffindUserByUsernamePassword() "+ e.getMessage());
            }
        }
        return u;
    }
}
