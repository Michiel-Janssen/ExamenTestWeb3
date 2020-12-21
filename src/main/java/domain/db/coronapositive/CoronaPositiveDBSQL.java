package domain.db.coronapositive;

import domain.db.DbException;
import domain.model.CoronaPositiveModel;
import util.DbConnectionService;

import java.sql.*;
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

    @Override
    public List<CoronaPositiveModel> getAll() {
        List<CoronaPositiveModel> coronaPositiveModels = new ArrayList<CoronaPositiveModel>();
        String sql = String.format("SELECT * from \"web3_project_r0789294\".coronapositive order by date", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String id = result.getString("id");
                String date = result.getString("date");
                CoronaPositiveModel coronaPositiveModel = new CoronaPositiveModel(date, id);
                coronaPositiveModels.add(coronaPositiveModel);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return coronaPositiveModels;
    }

    @Override
    public List<CoronaPositiveModel> getAllFiltered(Timestamp from, Timestamp until) {
        List<CoronaPositiveModel> coronaPositiveModels = new ArrayList<CoronaPositiveModel>();
        String sql = String.format("SELECT * from \"web3_project_r0789294\".coronapositive WHERE date BETWEEN ? and ? order by date", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setTimestamp(1, from);
            statementSql.setTimestamp(2, until);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String id = result.getString("id");
                String date = result.getString("date");
                CoronaPositiveModel coronaPositiveModel = new CoronaPositiveModel(date, id);
                coronaPositiveModels.add(coronaPositiveModel);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage(), e);
        }
        return coronaPositiveModels;
    }
}
