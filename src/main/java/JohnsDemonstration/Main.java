package JohnsDemonstration;

import java.sql.*;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Connecting to a MySQL database called gd2OOPCA4 using mysql JDBC driver");
        Connection conn = null;
        String url = "jdbc:mysql://localhost/";
        String dbName = "gd2OOPCA4";
        String driver = "com.mysql.cj.jdbc.Driver";
        String userName = "root";
        String password = "";
        Statement statement = null;
        ResultSet resultSet = null;

        String title = "DogTooth";
        String genre = "Action psychological thriller";
        String director = "Yorgos Lanthimos";

        PreparedStatement preparedStatement = null;

        try{
//            Class.forName(driver).newInstance();
            conn = DriverManager.getConnection(url+dbName,userName,password);
            System.out.println("We have successfully connected");


            // Execute and embedded select query
            //statements allow us to issue SQL queries to the database
            statement =conn.createStatement();
            resultSet = statement.executeQuery("select * from movies");
            //itterate through result set
            while(resultSet.next())
            {
                int MovieId = resultSet.getInt("id");
                String MovieTitle = resultSet.getString("title");
                String movieGenre = resultSet.getString("genre");
                String MovieDirector = resultSet.getString("director");

                System.out.println(MovieId +",");
                System.out.println(MovieTitle +",");
                System.out.println(movieGenre +",");
                System.out.println(MovieDirector +",");
            }
            /*
             * Execute a prepared statement to insert into the database
             */
            // Insert a new row for dogtooth
            preparedStatement = conn.prepareStatement("Insert into movies(title,genre,director) values(?,?,?)");
            //parameters are numbered starting at 1
            preparedStatement.setString(1,title);
            preparedStatement.setString(2,genre);
            preparedStatement.setString(3,director);

            preparedStatement.executeUpdate();




            conn.close();
            System.out.println("Disconnected nigga");
        }catch(Exception e)
        {
            System.out.println(" Got ya bitch");
            e.printStackTrace();
        }

    }
}
