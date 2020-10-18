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
        String sql = String.format("INSERT INTO %s.person (email, firstname, lastname, fitness) VALUES (?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, person.getEmail());
            statementSQL.setString(2, person.getFirstName());
            statementSQL.setString(3, person.getLastName());
            statementSQL.setString(3, person.getFitness());
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
        String sql = String.format("SELECT * FROM %s.person", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userid = result.getString("userid");
                String email = result.getString("email");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String password = result.getString("password");
                String fitness = result.getString("fitness");
                Person person = new Person(userid, email, firstname, lastname, password, fitness);
                persons.add(person);
            }
        } catch (SQLException | IllegalAccessException e) {
            throw new DbException(e.getMessage(), e);
        }
        return persons;
    }
}