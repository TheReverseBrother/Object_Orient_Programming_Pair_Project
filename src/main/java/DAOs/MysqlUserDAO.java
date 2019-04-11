package DAOs;

import Client.Client;
import Exceptions.DAOException;
import com.mysql.cj.jdbc.result.ResultSetFactory;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.*;

public class MysqlUserDAO extends MysqlDAO implements UserDAOInterface
{
    @Override
    public String findUserByUsernamePassword(String username, String Password) throws DAOException
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = null;
        int userID = 0;
        String dbPassword = "";
        try
        {
            System.out.println("HOOO" + username);
            con = this.getConnection();
            String query = "SELECT * FROM USERS WHERE EMAIL=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();

            while (rs.next())
            {
                userID = rs.getInt("UserID");
                System.out.println("In RS" + userID);
                dbPassword = rs.getString("Password");
                System.out.println(dbPassword);
                System.out.println("Passed: " + Password);
            }


            if (dbPassword.equals(Password))
            {
                result = "true££" + userID;
            }
            else
            {
                result = "false££Invalid Username And Password";
            }
        }
        catch (SQLException e)
        {
            throw new DAOException("findAllUsers() " + e.getMessage());
        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    this.freeConnection(con);
                }
            }
            catch (SQLException e)
            {
                throw new DAOException("findAllUsers() " + e.getMessage());
            }
        }

        return result;
    }

    @Override
    public boolean registerUser(String Username, String Password)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String result = "";
        int userID = 0;
        String dbPassword = null;
        boolean check = checkIfUserExists(Username);

        if (check)
        {
            System.out.println("User already registered with this email please try another");
            return false;
        }
        else if (!check)
        {

            try
            {
                con = this.getConnection();
                String query = "INSERT INTO USERS(EMAIL,PASSWORD) VALUES(?,?)";
                ps = con.prepareStatement(query);
                ps.setString(1, Username);
                ps.setString(2, Password);
                ps.executeUpdate();
                System.out.println("Registered Congratulations Login to Enjoy Features");
                return true;

            }
            catch (SQLException e)
            {

                System.out.println("User already registered with this email please try another test");
                return false;
            }
            finally
            {
                try
                {
                    if (rs != null)
                    {
                        rs.close();
                    }
                    if (ps != null)
                    {
                        ps.close();
                    }
                    if (con != null)
                    {
                        this.freeConnection(con);
                    }
                }
                catch (SQLException e)
                {

                }
            }

        }
        return false;
    }

    public Boolean checkIfUserExists(String username)
    {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean check = false;
        int rowCount = 0;
        try
        {
            con = this.getConnection();
            String query = "SELECT * FROM USERS WHERE EMAIL=?";
            ps = con.prepareStatement(query);
            ps.setString(1, username);
            rs = ps.executeQuery();


            while(rs.next())
            {
                rowCount++; //or rs.getString("column name");
            }


            if(rs.next()){rowCount =1 ;}


        }
        catch (SQLException e)
        {

        }
        finally
        {
            try
            {
                if (rs != null)
                {
                    rs.close();
                }
                if (ps != null)
                {
                    ps.close();
                }
                if (con != null)
                {
                    this.freeConnection(con);
                }
            }
            catch (SQLException e)
            {
                System.out.println("test");
            }
        }
        if(rowCount == 1){check = true;}
        return check;
    }
}
