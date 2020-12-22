package domain.model;

import java.sql.Timestamp;

public class CoronaPositiveModel {
    private Timestamp date;
    private String id;

    public CoronaPositiveModel(Timestamp date, String id) {
        setDate(date);
        setId(id);
    }

    public CoronaPositiveModel() {}

    //Setters

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    //Getters

    public Timestamp getDate() {
        return date;
    }

    public String getId() {
        return id;
    }
}
