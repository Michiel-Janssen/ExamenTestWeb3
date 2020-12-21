package domain.db.coronapositive;

import domain.db.DbException;
import domain.model.CoronaPositiveModel;


import java.sql.Timestamp;
import java.util.List;

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

    List<CoronaPositiveModel> getAll();

    List<CoronaPositiveModel> getAllFiltered(Timestamp from, Timestamp until);
}
