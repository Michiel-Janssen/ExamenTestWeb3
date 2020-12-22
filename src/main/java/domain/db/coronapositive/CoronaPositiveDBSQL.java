package domain.db.coronapositive;

import domain.db.DbException;
import domain.db.person.PersonDBSQL;
import domain.model.CoronaPositiveModel;
import domain.model.Person;
import domain.model.Role;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoronaPositiveDBSQL implements CoronaPositiveDB {
    private Connection connection;
    private String schema;

    public CoronaPositiveDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println(this.schema);
    }

    @Override
    public void add(CoronaPositiveModel coronaPositiveModel) {
        if(coronaPositiveModel == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = String.format("INSERT INTO \"web3_project_r0789294\".coronapositive (\"id\", \"date\") VALUES (?,?::timestamp without time zone)", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, coronaPositiveModel.getId());
            statementSQL.setObject(2, coronaPositiveModel.getDate());;
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }
/*
    @Override
    public List<CoronaPositiveModel> getAll() {
        List<CoronaPositiveModel> coronaPositiveModels = new ArrayList<CoronaPositiveModel>();
        String sql = String.format("SELECT * from \"web3_project_r0789294\".coronapositive order by date", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String id = result.getString("id");
                Timestamp date = result.getTimestamp("date");
                CoronaPositiveModel coronaPositiveModel = new CoronaPositiveModel(date, id);
                coronaPositiveModels.add(coronaPositiveModel);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return coronaPositiveModels;
    }

 */

    @Override
    public Map<Timestamp, Person> getAll() {
        Map<Timestamp, Person> data = new HashMap<>();
        String sql = String.format("SELECT c.*,p.* from \"web3_project_r0789294\".coronapositive as c inner join \"web3_project_r0789294\".person as p using(id) where c.id = p.id order by date", this.schema, this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                Timestamp date = result.getTimestamp("date");

                String userid = result.getString("id");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String password = result.getString("password");
                String email = result.getString("email");
                String roleAsString = result.getString("role");
                Role role = Role.valueOf(roleAsString.toUpperCase());
                Person person = new Person(userid, firstname, lastname, password, email, role);

                data.put(date, person);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return data;
    }



    @Override
    public Map<Timestamp, Person> getAllFiltered(Timestamp from, Timestamp until) {
        Map<Timestamp, Person> data = new HashMap<>();
        String sql = String.format("SELECT c.*,p.* from \"web3_project_r0789294\".coronapositive as c inner join \"web3_project_r0789294\".person as p using(id) where date BETWEEN ? and ? order by date", this.schema, this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setTimestamp(1, from);
            statementSql.setTimestamp(2, until);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                Timestamp date = result.getTimestamp("date");

                String userid = result.getString("id");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String password = result.getString("password");
                String email = result.getString("email");
                String roleAsString = result.getString("role");
                Role role = Role.valueOf(roleAsString.toUpperCase());
                Person person = new Person(userid, firstname, lastname, password, email, role);

                data.put(date, person);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return data;
    }

    @Override
    public Map<Timestamp, Person> getAllFilteredWithPerson(Timestamp from, Timestamp until, String id) {
        Map<Timestamp, Person> data = new HashMap<>();
        String sql = String.format("SELECT c.*, p.* from \"web3_project_r0789294\".coronapositive as c inner join \"web3_project_r0789294\".person as p using(id) WHERE date BETWEEN ? and ? and p.id = ? order by date", this.schema, this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setTimestamp(1, from);
            statementSql.setTimestamp(2, until);
            statementSql.setString(3, id);

            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userid = result.getString("id");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String password = result.getString("password");
                String email = result.getString("email");
                String roleAsString = result.getString("role");
                Role role = Role.valueOf(roleAsString.toUpperCase());
                Person person = new Person(userid, firstname, lastname, password, email, role);
                Timestamp date = result.getTimestamp("date");
                data.put(date, person);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return data;
    }

}
