package edu.iit.itmd515;

import java.io.Serializable;
import javax.validation.constraints.*;

/**
 * Java Bean class that holds and models the information entered in a form
 * Validation Bean constraints cheat sheet: Constraints cheatsheet: https://docs.oracle.com/javaee/6/tutorial/doc/gircz.html 
 * @author Raquel, Francisco
 *
 */
public class FormDataBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	// frontend validation
	private String name; 
	// frontend validation
	private String ssn;
	@Size(min=1, max=5)
	@Pattern(regexp = "[0-9]*")
	//@Pattern(regexp = "^\d{5}(?:[-\s]\d{4})?$") //https://stackoverflow.com/questions/2577236/regex-for-zip-code
	private int zipCode; // bean validation
	@Pattern(regexp = "(?:[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-zA-Z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\\.)+[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-zA-Z0-9-]*[a-zA-Z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
	private String email;  //https://stackoverflow.com/a/46445787/6325681 
	// frontend validation
	private String address;
	// frontend validation
	private String city;
	// frontend validation
	private String state;

	/**
	 * Constructor
	 * 
	 * @param name
	 * @param ssn
	 * @param zipCode
	 * @param email
	 * @param address
	 * @param city
	 * @param state
	 */
	public FormDataBean(String name, String ssn, int zipCode, String email, String address, String city, String state) {
		super();
		this.name = name;
		this.ssn = ssn;
		this.zipCode = zipCode;
		this.email = email;
		this.address = address;
		this.city = city;
		this.state = state;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ssn
	 */
	public String getSsn() {
		return ssn;
	}

	/**
	 * @param ssn the ssn to set
	 */
	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	/**
	 * @return the zipCode
	 */
	public int getZipCode() {
		return zipCode;
	}

	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

}
