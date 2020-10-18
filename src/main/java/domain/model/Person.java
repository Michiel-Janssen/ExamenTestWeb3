package domain.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String userid;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private String fitness;
	private int hour;
	private int date;
	private int gsm;

	public Person(String userid, String email, String password, String firstName, String lastName, String fitness) throws IllegalAccessException {
		setUserid(userid);
		setEmail(email);
		setPassword(password);
		setFirstName(firstName);
		setLastName(lastName);
		setFitness(fitness);

	}

	public Person(String firstName, String lastName, int hour, int date, int gsm, String fitness) throws IllegalAccessException {
		setFirstName(firstName);
		setLastName(lastName);
		setFitness(fitness);
		setHour(hour);
		setDate(date);
		setGsm(gsm);
	}

	public void setGsm(int gsm) {
		if(gsm < 0) {
			throw new IllegalArgumentException("False gsm number");
		}
		this.gsm = gsm;
	}

	public void setDate(int date) {
		if(date < 0) {
			throw new IllegalArgumentException("False date");
		}
		this.date = date;
	}

	public void setHour(int hour) {
		if(hour < 0 || hour > 24) {
			throw new IllegalArgumentException("Hour is not possible");
		}
		this.hour = hour;
	}

	public Person() {
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		if(userid.isEmpty()){
			throw new IllegalArgumentException("No userid given");
		}
		this.userid = userid;
	}

	public void setEmail(String email) {
		if(email.isEmpty()){
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

	
	
	public String getEmail() {
		return email;
	}
	
	private String getPassword() {
		return password;
	}
	
	public boolean isCorrectPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		return getPassword().equals(password);
	}

	public void setPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		if(firstName.isEmpty()){
			throw new IllegalArgumentException("No firstname given");
		}
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		if(lastName.isEmpty()){
			throw new IllegalArgumentException("No last name given");
		}
		this.lastName = lastName;
	}

	public void setFitness(String fitness) throws IllegalAccessException {
		if(fitness == null) {
			throw new IllegalAccessException("No fitness given");
		}
		this.fitness = fitness;
	}

	public String getFitness() {
		return fitness;
	}

	@Override
	public String toString(){
		return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
	}	
}
