
import domain.model.Person;
import util.Credentials;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.Properties;

public class ConnectionTest extends Credentials {
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

        Statement statement = connection.createStatement();
        statement.execute(setSearchPath);
        statement.executeQuery( "SELECT * from \"JanssenMichielWeb3\".person" );
        connection.commit();

        ResultSet result = statement.getResultSet();

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
        statement.close();
        connection.close();

    }
}