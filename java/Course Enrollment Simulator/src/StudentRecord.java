//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Course Enrollment Simulator
// Course:   CS 300 Fall 2023
//
// Author:   Daniel Afrasiabi
// Email:    dafrasiabi@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Kai Tsimpidis
// Partner Email:   tsimpidis@wisc.edu
// Partner Lecturer's Name: Hobbes LeGault
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   X Write-up states that pair programming is allowed for this assignment.
//   X We have both read and understand the course Pair Programming Policy.
//   X We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.util.zip.DataFormatException;

/**
 * This class models a basic student record with each record containing a
 * campusID, an email, whether their prereq is satisfied, and their name.
 * 
 * @author Daniel Afrasiabi and Kai Tsimpidis
 */
public class StudentRecord {

	// TODO add fields with respect to the details in the javadocs
	// unique id of student
	private String campusID;
	// email of student
	private String email;
	// boolean for student satisfying prerequisities
	private boolean isPreReqSatisfied = false;
	// name of student
	private String name;

	/**
	 * Constructor for a student record object. Assigns values to all fields.
	 * 
	 * @param name     the name of the student
	 * @param email    the email of the student
	 * @param campusID the campusID of the student
	 * @param preReq   the boolean representing if the student satisfies the
	 *                 prerequisites
	 * @throws DataFormatException with message "Bad name, email, or campusID!" if
	 *                             name or email or campusID are NOT valid
	 */
	public StudentRecord(String name, String email, String campusID, boolean preReq) throws DataFormatException {
		boolean validName = false;
		boolean validEmail = false;
		boolean validCampusID = false;
		validName = isValidName(name);
		validEmail = isValidEmail(email);
		validCampusID = isValidCampusID(campusID);
		if (!validName || !validEmail || !validCampusID) {
			throw new DataFormatException("Bad name, email, or campusID!");
		}
		if (validName && validEmail && validCampusID) {
			this.name = name;
			this.email = email;
			isPreReqSatisfied = preReq;
			this.campusID = campusID;
		}
	}

	/**
	 * Validator method for a student's name
	 * 
	 * @param name the student's name
	 * @return true if and only if the name is not null and not blank
	 */
	public static boolean isValidName(String name) throws DataFormatException {

		if ((name != null) && !(name.trim().equals(""))) {
			return true;
		}

		throw new DataFormatException();
	}

	/**
	 * Validator method for a student's email
	 * 
	 * @param email the student's email
	 * @return true if and only if the email is not null, has one @ symbol, ends
	 *         with .edu, is between 0 and 40 characters (EXCLUSIVE, that is, 0 and
	 *         40 are not allowed lengths but 1 and 39 are), and has at least two
	 *         characters before the @ symbol.
	 */
	public static boolean isValidEmail(String email) throws DataFormatException {
		if (email == null) {
			throw new DataFormatException();
		}
		if (!(email.trim().endsWith(".edu"))) {
			throw new DataFormatException();
		}
		if ((email.trim().length() >= 40) || (email.trim().length() <= 0)) {
			throw new DataFormatException();
		}
		int countMatches = email.length() - email.replace("@", "").length();
		if (countMatches != 1) {
			throw new DataFormatException();

		}
		int indexOfAt = email.indexOf('@');
		if (email.substring(0, indexOfAt).length() < 2) {
			throw new DataFormatException();
		}

		return true; // default return statement
	}

	/**
	 * Validator method for a student's id
	 * 
	 * @param campusID the student's campusID
	 * @return true if and only if the campusID is not null and can be parsed to a
	 *         long with 10-digits. Extra leading and trailing whitespace should be
	 *         disregarded.
	 */
	public static boolean isValidCampusID(String campusID) throws DataFormatException {
		// TODO implement this method
		if (campusID == null) {
			return false;
		}

		try {
			long id = Long.parseLong(campusID);
		} catch (NumberFormatException e) {
			return false;
		}

		campusID = campusID.trim();

		if (campusID.length() != 10) {
			return false;
		}

		return true;

	}

	/**
	 * Getter method for a student's name
	 * 
	 * @return the student's name
	 */
	public String getName() {

		return name; // default return statement
	}

	/**
	 * Getter method for a student's email
	 * 
	 * @return the student's email
	 */
	public String getEmail() {
		return email; // default return statement
	}

	/**
	 * Getter method for a student's campusID
	 * 
	 * @return the student's campusID
	 */
	public String getCampusID() {
		return campusID; // default return statement
	}

	/**
	 * Returns true if this student record satisfies the pre-requisites of the
	 * course
	 * 
	 * @return true if this student record satisfies the pre-requisites of the
	 *         course
	 */
	public boolean isPrerequisiteSatisfied() {

		return isPreReqSatisfied; // default return statement
	}

	/**
	 * Compared this StudentRecord to the specified object
	 * 
	 * @return true if anObject is instanceof StudentRecord and has the same
	 *         campusID as this StudentRecord.
	 */
	public boolean equals(Object other) {
		if (other instanceof StudentRecord) {
			StudentRecord record = (StudentRecord) other;
			return this.campusID.equals(record.campusID);
		} else {
			return false;
		} // default return statement
	}

	/**
	 * Returns a string representation of this student record in the following
	 * format (comma followed by a space ", " separated): <BR>
	 * name, email, campusID, preReqValue
	 * 
	 * @return the string representation of a studentRecord as described above
	 */
	@Override
	public String toString() {
		return name + ", " + email + ", " + campusID + ", " + isPreReqSatisfied;
	}
}
