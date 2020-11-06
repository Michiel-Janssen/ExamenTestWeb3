package domain.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String firstName;
    private String lastName;
    private String email;
    private String fitness;
    private String hour;
    private String date;
    private int gsm;

    public Contact(String firstName, String lastName, String email, String hour, String date, int gsm, String fitness) {
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setHour(hour);
        setDate(date);
        setGsm(gsm);
        setFitness(fitness);
    }

    public Contact() {}

    public void setGsm(int gsm) {
        if(gsm < 0) {
            throw new IllegalArgumentException("False gsm number");
        }
        this.gsm = gsm;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setEmail(String email) {
        if (email.isEmpty()) {
            throw new IllegalArgumentException("No email given");
        }
        String USERID_PATTERN =
                "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        Pattern p = Pattern.compile(USERID_PATTERN);
        Matcher m = p.matcher(email);
        if (!m.matches()) {
            throw new IllegalArgumentException("Email not valid");
        }


        this.email = email;
    }

    public void setFirstName(String firstName) {
        if(firstName.isEmpty()){
            throw new IllegalArgumentException("No firstname given");
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if(lastName.isEmpty()){
            throw new IllegalArgumentException("No last name given");
        }
        this.lastName = lastName;
    }

    public void setFitness(String fitness) {
        if(fitness == null) {
            throw new IllegalArgumentException("No fitness given");
        }
        this.fitness = fitness;
    }

    public String getFitness() {
        return fitness;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getHour() {
        return hour;
    }

    public String getDate() {
        return date;
    }

    public int getGsm() {
        return gsm;
    }
}
