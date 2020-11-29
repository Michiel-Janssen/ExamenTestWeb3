package domain.model;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Person {
	private String userid;
	private String email;
	private String password;
	private String firstName;
	private String lastName;
	private Role role;

	public Person(String userid, String firstName, String lastName, String password, String email) {
		setUserid(userid);
		setFirstName(firstName);
		setLastName(lastName);
		setPassword(password);
		setEmail(email);
		setRole(role);
	}

	public Person() {
	}

	//Setters

	public void setRole(Role role) {
		this.role = role;
	}

	public void setUserid(String userid) {
		if(userid.isEmpty()){
			throw new IllegalArgumentException("No userid given");
		}
		this.userid = userid;
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

	public void setPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		this.password = password;
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

	//Getters

	public Role getRole() {
		return role;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getUserid() {
		return userid;
	}

	// Extra

	public boolean isCorrectPassword(String password) {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		return getPassword().equals(password);
	}

	public boolean isCorrectHashedPassword(String pass) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		return hashPassword(pass).equals(password);
	}

	public void setPasswordHashed(String password)  {
		if(password.isEmpty()){
			throw new IllegalArgumentException("No password given");
		}
		try {
			this.password = hashPassword(password);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	private String hashPassword(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
		MessageDigest crypt = MessageDigest.getInstance("SHA-512");
		crypt.reset();

		// encrypts
		crypt.update(password.getBytes("UTF-8"));

		//16 hexadecimal system the sixteen digits are "0–9" followed by "A–F".
		String hashedPassword = new BigInteger(1, crypt.digest()).toString(16);
		System.out.println(hashedPassword.length());
		return hashedPassword;
	}

	public boolean isPasswordCorrect(String password){
		try {
			return this.password.equals(hashPassword(password));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return false;
	}

	public Person getpersonIfAuthenticated(String password){
		if(this != null && this.isPasswordCorrect(password))
			return this;
		return null;
	}

	@Override
	public String toString(){
		return getFirstName() + " " + getLastName() + ": " + getUserid() + ", " + getEmail();
	}	
}
