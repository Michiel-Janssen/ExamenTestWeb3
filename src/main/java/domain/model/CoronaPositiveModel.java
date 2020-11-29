package domain.model;

public class CoronaPositiveModel {
    private String date;
    private String id;

    public CoronaPositiveModel(String date, String id) {
        setDate(date);
        setId(id);
    }

    public CoronaPositiveModel() {}

    //Setters

    public void setDate(String date) {
        this.date = date;
    }

    public void setId(String id) {
        this.id = id;
    }

    //Getters

    public String getDate() {
        return date;
    }

    public String getId() {
        return id;
    }
}
