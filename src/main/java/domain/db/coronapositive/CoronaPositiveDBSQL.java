package domain.db.coronapositive;

import domain.db.DbException;
import domain.model.CoronaPositiveModel;
import util.DbConnectionService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CoronaPositiveDBSQL implements CoronaPositiveDB {
    private Connection connection;
    private String schema;

    public CoronaPositiveDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSchema();
        System.out.println(this.schema);
    }

    /**
     * Stores the given person in the database
     *
     * @param coronaPositiveModel The person to be added
     * @throws DbException if the given person is null
     * @throws DbException if the given person can not be added
     */
    @Override
    public void add(CoronaPositiveModel coronaPositiveModel) {
        if (coronaPositiveModel == null) {
            throw new DbException("Nothing to add.");
        }
        try {
            PreparedStatement statementSQL = connection.prepareStatement("INSERT INTO \"web3_project_r0789294\".coronapositive (\"id\", \"date\") VALUES (?,?::timestamp without time zone)");
            statementSQL.setString(1, coronaPositiveModel.getId());
            statementSQL.setString(2, coronaPositiveModel.getDate());
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
    public List<CoronaPositiveModel> getAll() {
        List<CoronaPositiveModel> coronaPositiveModels = new ArrayList<CoronaPositiveModel>();
        String sql = String.format("SELECT * from \"web3_project_r0789294\".coronapositive", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String id = result.getString("id");
                String date = result.getString("date");
                CoronaPositiveModel coronaPositiveModel = new CoronaPositiveModel(id, date);
                coronaPositiveModels.add(coronaPositiveModel);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return coronaPositiveModels;
    }
}
