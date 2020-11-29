package domain.db.contact;

import domain.db.DbException;
import domain.model.Contact;
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
            PreparedStatement statementSQL = connection.prepareStatement("INSERT INTO \"web3_project_r0789294\".contact (\"firstname\", \"lastname\", \"email\", \"date\", \"gsm\", \"fitness\") VALUES (?,?,?,?,?,?,?)");
            statementSQL.setString(1, contact.getFirstName());
            statementSQL.setString(2, contact.getLastName());
            statementSQL.setString(3, contact.getEmail());
            statementSQL.setObject(5, contact.getDate());
            statementSQL.setString(6, contact.getGsm());
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
        String sql = String.format("SELECT * from \"web3_project_r0789294\".contact", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String email = result.getString("email");
                Timestamp date = result.getObject("date", Timestamp.class);
                String gsm = result.getString("gsm");
                String fitness = result.getString("fitness");
                Contact contact = new Contact(firstName, lastName, email, date, gsm, fitness);
                contacten.add(contact);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return contacten;
    }

    //Extra story, geef populairste fitness terug

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
            }
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

    //SQL Van mensen die in contact zijn gekomen met een corona patiënt
    @Override
    public List<Contact> getPossibleCorona(String Date) {
        List<Contact> contacts = new ArrayList<Contact>();
        String sql = String.format("SELECT * from \"web3_project_r0789294\".\"contact\" WHERE date = ?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, Date);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                String email = result.getString("email");
                Timestamp date = result.getObject("date", Timestamp.class);
                String gsm = result.getString("gsm");
                String fitness = result.getString("fitness");
                Contact c = new Contact(firstName, lastName, email, date, gsm, fitness);
                contacts.add(c);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return contacts;
    }
}
