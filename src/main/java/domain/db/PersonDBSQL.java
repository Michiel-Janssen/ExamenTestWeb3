package domain.db;

import domain.model.Person;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDBSQL implements PersonDB {
    private Connection connection;
    private String schema;

    public PersonDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println(this.schema);
    }

    /**
     * Stores the given person in the database
     *
     * @param person The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    @Override
    public void add(Person person) {
        if (person == null) {
            throw new DbException("Nothing to add.");
        }
        try {
            PreparedStatement statementSQL = connection.prepareStatement("INSERT INTO \"JanssenMichielWeb3\".person (\"id\", \"firstName\", \"lastName\", \"password\", \"email\") VALUES (?,?,?,?,?)");
            statementSQL.setString(1, person.getUserid());
            statementSQL.setString(2, person.getFirstName());
            statementSQL.setString(3, person.getLastName());
            statementSQL.setString(4, person.getPassword());
            statementSQL.setString(5, person.getEmail());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    /**
     * Returns a list with all persons stored in the database
     * @return An arraylist with all persons stored in the database
     * @throws DbException when there are problems with the connection to the database
     */
    public List<Person> getAll() {
        List<Person> persons = new ArrayList<Person>();
        String sql = String.format("SELECT * from \"JanssenMichielWeb3\".person", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userid = result.getString("id");
                String firstname = result.getString("firstName");
                String lastname = result.getString("lastName");
                String password = result.getString("password");
                String email = result.getString("email");
                Person person = new Person(userid, firstname, lastname, password, email);
                persons.add(person);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return persons;
    }

}