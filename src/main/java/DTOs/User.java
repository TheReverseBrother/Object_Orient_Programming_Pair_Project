package DTOs;

/* This is the DTO, Data Transfer Object
It is used to transfer data between the DAO and the
Business Objects (main application). Here it represents
a row of data from the user table.
The DAO fill a User objects with data retrieved from the resultSet and pass the User object to the main application. Collections of DTOs (e.g ArrayList<User> may be passed between the DAO and the main application
 */

public class User
{
    private int id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;

    public User(int id, String firstName, String lastName, String username, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public User(String firstName, String lastName, String username, String password) {
        this.id = 0;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public User(){

    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
