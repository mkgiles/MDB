package model;

import java.time.*;

/**
 * The Class Actor.
 */
public class Actor {
	
	/** The name. */
	private String name;
	
	/** The dob. */
	private LocalDate dob;
	
	/** The gender. */
	private Boolean gender;
	
	/** The nationality. */
	private String nationality;

	/**
	 * Instantiates a new actor.
	 *
	 * @param name
	 *            the name
	 * @param gender
	 *            the gender
	 * @param nationality
	 *            the nationality
	 * @param yearNum
	 *            the year num
	 * @param monthNum
	 *            the month num
	 * @param dayNum
	 *            the day num
	 */
	public Actor(String name, Boolean gender, String nationality, int yearNum, int monthNum, int dayNum) {
		setName(name);
		setGender(gender);
		setNationality(nationality);
		setDob(yearNum, monthNum, dayNum);
	}

	/**
	 * View actor.
	 */
	public void viewActor() {
		System.out.print("Showing actors");
	}

	/**
	 * Sets the name.
	 *
	 * @param name
	 *            the new name
	 */
	// SETTERS//
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the gender.
	 *
	 * @param gender
	 *            the new gender
	 */
	public void setGender(Boolean gender) {
		this.gender = gender;
	}

	/**
	 * Sets the nationality.
	 *
	 * @param nationality
	 *            the new nationality
	 */
	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	/**
	 * Sets the dob.
	 *
	 * @param yearNum
	 *            the year num
	 * @param monthNum
	 *            the month num
	 * @param dayNum
	 *            the day num
	 */
	public void setDob(int yearNum, int monthNum, int dayNum) {
		this.dob = LocalDate.of(yearNum, monthNum, dayNum);
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	// GETTERS//
	public String getName() {
		return name;
	}

	/**
	 * Gets the gender.
	 *
	 * @return the gender
	 */
	public Boolean getGender() {
		return gender;
	}

	/**
	 * Gets the nationality.
	 *
	 * @return the nationality
	 */
	public String getNationality() {
		return nationality;
	}

	/**
	 * Gets the dob.
	 *
	 * @return the dob
	 */
	public LocalDate getDob() {
		return dob;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return name;
	}
}
