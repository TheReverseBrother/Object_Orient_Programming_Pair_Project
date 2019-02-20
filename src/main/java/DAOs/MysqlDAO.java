package DAOs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MysqlDAO
{
    public Connection getConnection()throws Exceptions.DAOException
    {
        String url = "jdbc:mysql://localhost/";
        String dbName = "gd2OOPCA4";
        String driver = "com.mysql.cj.jdbc.Driver";
        String username = "root";
        String password = "";
        Connection con = null;

        try
        {
            con = DriverManager.getConnection(url,username,password);
        }
        catch(SQLException ex)
        {
            System.out.println("Connection Failed "+ ex.getMessage());
            System.exit(1 );
        }
        return con;
    }

    public void freeConnection(Connection con) throws Exceptions.DAOException
    {
        try
        {
            if(con != null)
            {
                con.close();
                con = null;
            }
        }
        catch(SQLException e)
        {
            System.out.println("Failed To Free the connection: " + e.getMessage());
            System.exit(1);
        }
    }
}
