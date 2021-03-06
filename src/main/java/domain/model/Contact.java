package domain.model;

import java.sql.Timestamp;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Contact {
    private String firstName;
    private String lastName;
    private String email;
    private String fitness;
    private Timestamp date;
    private String gsm;
    private String personId;

    public Contact(String personId, String firstName, String lastName, String email, Timestamp date, String gsm, String fitness) {
        setPersonId(personId);
        setFirstName(firstName);
        setLastName(lastName);
        setEmail(email);
        setDate(date);
        setGsm(gsm);
        setFitness(fitness);
    }

    public void setPersonId(String personId) {
        if(personId == null) {
            throw new IllegalArgumentException("Person id is not valid");
        }
        this.personId = personId;
    }

    public Contact() {}

    public void setGsm(String gsm) {
        if(gsm.isEmpty()) {
            throw new IllegalArgumentException("Phone number isn't valid");
        }
        this.gsm = gsm;
    }

    public void setDate(Timestamp date) {
        if(date == null) {
            throw new IllegalArgumentException("No valid date or time");
        }
        this.date = date;
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
            throw new IllegalArgumentException("No first name given");
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

    public String getPersonId() {
        return personId;
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

    public Timestamp getDate() {
        return date;
    }

    public String getGsm() {
        return gsm;
    }
}
