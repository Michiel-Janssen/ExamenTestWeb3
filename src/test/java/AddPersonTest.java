import domain.db.DbException;
import domain.model.DomainException;
import domain.model.Person;
import util.Credentials;

import javax.swing.*;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Properties;

public class AddPersonTest extends Credentials {

    public static void main(String[] args) throws SQLException {
        String url = "jdbc:postgresql://databanken.ucll.be:62021/2TX33";
        Properties properties = new Properties();

        try {
            Class.forName("util.Credentials");
            setPass(properties);
        } catch (ClassNotFoundException e) {
            System.out.println("Class ui.view.Secret with credentials not found!");
        }

        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode","prefer");
        properties.setProperty("allowMultiQueries","true");
        properties.setProperty("prepareThreshold","0");

        String setSearchPath = "SET search_path = JanssenMichielWeb3;";
        Connection connection = DriverManager.getConnection(url,properties);
        connection.setAutoCommit(false);

        String userID1 = JOptionPane.showInputDialog(null, "User ID");
        String firstName1 = JOptionPane.showInputDialog(null, "First Name?");
        String lastName1 = JOptionPane.showInputDialog(null, "Lastname");
        String password1 = JOptionPane.showInputDialog(null, "Password");
        String fitness1 = JOptionPane.showInputDialog(null, "Fitness");
        String email1 = JOptionPane.showInputDialog(null, "Email");

        if (userID1.trim().isEmpty() || userID1 == null || email1.trim().isEmpty() || email1 == null || password1.trim().isEmpty() || password1 == null || firstName1.trim().isEmpty() ||
                firstName1 == null || lastName1.trim().isEmpty() || lastName1 == null || fitness1.trim().isEmpty() || fitness1 == null) {
            throw new DomainException("userID, email, fitness, password, first name or last name can not be empty!");
        }

        Statement statement = connection.createStatement();
        statement.execute(setSearchPath);
        try {
            PreparedStatement statementSQL = connection.prepareStatement("INSERT INTO \"JanssenMichielWeb3\".person (\"id\", \"firstName\", \"lastName\", \"password\", \"email\") VALUES (?,?,?,?,?)");
            statementSQL.setString(1, userID1);
            statementSQL.setString(2, firstName1);
            statementSQL.setString(3, lastName1);
            statementSQL.setString(4, password1);
            statementSQL.setString(5, email1);
            statementSQL.executeUpdate();
            statementSQL.close();
        } catch (SQLException e) {
            throw new DbException((e));
        }

        Statement statement2 = connection.createStatement();
        statement2.execute(setSearchPath);
        statement2.executeQuery( "SELECT * from \"JanssenMichielWeb3\".person" );
        connection.commit();

        ResultSet result = statement2.getResultSet();

        while(result.next()){
            String id = result.getString("id");
            String firstName = result.getString("firstName");
            String lastName = result.getString("lastName");
            String password = result.getString("password");
            String email = result.getString("email");
            try {
                Person person = new Person(id, firstName, lastName, password, email);
                System.out.println(person.toString());
            }
            catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        statement2.close();
        connection.close();
    }
}