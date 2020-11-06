package domain.db;

import domain.model.Contact;
import domain.model.Person;
import util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ContactDBSQL implements ContactDB {
    private Connection connection;
    private String schema;

    public ContactDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println(this.schema);
    }

    /**
     * Stores the given person in the database
     *
     * @param contact The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    @Override
    public void add(Contact contact) {
        if (contact == null) {
            throw new DbException("Nothing to add.");
        }
        try {
            PreparedStatement statementSQL = connection.prepareStatement("INSERT INTO \"JanssenMichielWeb3\".contact (\"firstName\", \"lastName\", \"email\", \"hour\", \"date\", \"gsm\", \"fitness\") VALUES (?,?,?,?,?,?,?)");
            statementSQL.setString(1, contact.getFirstName());
            statementSQL.setString(2, contact.getLastName());
            statementSQL.setString(3, contact.getEmail());
            statementSQL.setString(4, contact.getHour());
            statementSQL.setString(5, contact.getDate());
            statementSQL.setInt(6, contact.getGsm());
            statementSQL.setString(7, contact.getFitness());
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
    public List<Contact> getAll() {
        List<Contact> contacten = new ArrayList<Contact>();
        String sql = String.format("SELECT * from \"JanssenMichielWeb3\".contact", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String firstName = result.getString("firstName");
                String lastName = result.getString("lastName");
                String email = result.getString("email");
                String hour = result.getString("hour");
                String date = result.getString("date");
                int gsm = result.getInt("gsm");
                String fitness = result.getString("fitness");
                Contact contact = new Contact(firstName, lastName, email, hour, date, gsm, fitness);
                contacten.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return contacten;
    }

    @Override
    public String popularFitness() {
        ArrayList fitnessen = new ArrayList<String>();
        String sql = String.format("SELECT * from \"JanssenMichielWeb3\".contact", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String fitness = result.getString("fitness");
                fitnessen.add(fitness);
            } //return fitnessen;
            int v = 0;
            int h = 0;
            int c = 0;
            int r = 0;
            for (int i = 0; i < fitnessen.size(); i++) {
                if (fitnessen.get(i).equals("Vaart")) {
                    v++;
                }
                if (fitnessen.get(i).equals("Ring")) {
                    r++;
                }
                if (fitnessen.get(i).equals("Heverlee")) {
                    h++;
                }
                if (fitnessen.get(i).equals("Centrum")) {
                    c++;
                }
            }
            if(v > c && v > h && v > r) {
                return "Vaart";
            }
            if(c > v && c > h && c > r) {
                return "Centrum";
            }
            if(h > c && h > v && h > r) {
                return "Heverlee";
            }
            if(r > c && r > h && r > v) {
                return "Ring";
            } else return "Onbekend";
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
    }
}
