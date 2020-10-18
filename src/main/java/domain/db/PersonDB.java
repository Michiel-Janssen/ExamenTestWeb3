package domain.db;

import domain.model.Person;

import java.util.List;

public interface PersonDB {
    /**
     * Stores the given person
     * @param person The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    void add(Person person);


    /**
     * Returns a list with all persons stored in the database
     * @return An arraylist with all persons stored in the database
     * @throws DbException if something went wrong
     */

    List<Person> getAll();
}