package domain.db.contact;

import domain.db.DbException;
import domain.model.Contact;

import java.util.List;

public interface ContactDB {
    /**
     * Stores the given person
     * @param contact The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    void add(Contact contact);


    /**
     * Returns a list with all persons stored in the database
     * @return An arraylist with all persons stored in the database
     * @throws DbException if something went wrong
     */

    List<Contact> getAll();

    String popularFitness();

    List<Contact> getPossibleCorona(String Date);


}
