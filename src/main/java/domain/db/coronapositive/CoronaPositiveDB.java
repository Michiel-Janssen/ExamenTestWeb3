package domain.db.coronapositive;

import domain.db.DbException;
import domain.model.CoronaPositiveModel;
import domain.model.Person;


import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface CoronaPositiveDB {
    /**
     * Stores the given coronapositive
     * @param coronaPositiveModel The coronapositvie to be added
     * @throws DbException if the given coronapositive is null
     * @throws DbException if the given coronapositive can not be added
     */
    void add(CoronaPositiveModel coronaPositiveModel);


    /**
     * Returns a list with all persons stored in the database
     * @return An arraylist with all persons stored in the database
     * @throws DbException if something went wrong
     */

    //List<CoronaPositiveModel> getAll();

    Map<Timestamp, Person> getAllFiltered(Timestamp from, Timestamp until);

    Map<Timestamp, Person> getAllFilteredWithPerson(Timestamp from, Timestamp until, String id);

    Map<Timestamp, Person> getAll();
}
