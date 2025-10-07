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

import java.io.IOException;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;
import java.util.Scanner;

/**
 * This class manages student records between waitlist and officail roster using
 * methods to evaluate each list.
 * 
 * @author Daniel Afrasiabi and Kai Tsimpidis
 *
 */

public class ExceptionalCourseEnrollment {
	/** Course name */
	private String courseName;
	/** Arraylist storing the records of students enrolled in this course */
	private ArrayList<StudentRecord> roster;
	/** enrollment capacity of this course enrollment */
	private int enrollmentCapacity;
	/**
	 * Arraylist storing records of students in the waitlist (not yet enrolled in
	 * the course)
	 */
	private ArrayList<StudentRecord> waitlist;
	/** waitlist capacity */
	private int waitlistCapacity;

	/**
	 * Constructor for ExceptionalCourseEnrollment. Initializes all the fields with
	 * the corresponding inputs. The roster and waitlist arraylists must be empty.
	 * 
	 * @param courseName         the name of the course
	 * @param enrollmentCapacity the capacity of the course roster (INCLUSIVE,
	 *                           between 15 and 250. That is, 15 and 250 are allowed
	 *                           but 14 and 251 arent)
	 * @param waitlistCapacity   the capacity of the waitlist (must be GREATER than
	 *                           0 and LESS OR EQUAL TO than the enrollmentCapacity)
	 * @throws IllegalArgumentException with message "Course name must not be blank
	 *                                  or empty" if course name is blank or empty
	 * @throws IllegalArgumentException with message "Enrollment capacity must be
	 *                                  between 15 and 250!" if enrollment capacity
	 *                                  is not between 15 and 250, inclusive
	 * @throws IllegalArgumentException with message "Waitlist capacity must be
	 *                                  between 0 and enrollment capacity!" if
	 *                                  waitlistCapacity is larger than
	 *                                  enrollmentCapacity or less than zero
	 */
	public ExceptionalCourseEnrollment(String courseName, int enrollmentCapacity, int waitlistCapacity) {

		if (courseName.length() == 0 || courseName == null) {
			throw new IllegalArgumentException("Course name must not be blank or empty");
		}

		this.courseName = courseName;

		if (enrollmentCapacity <= 0 || enrollmentCapacity >= 250) {
			throw new IllegalArgumentException("Enrollment capacity must be between 0 and 250!");
		}

		this.enrollmentCapacity = enrollmentCapacity;

		if (waitlistCapacity <= 0 || waitlistCapacity > enrollmentCapacity) {
			throw new IllegalArgumentException("Waitlist capacity must be between 0 and enrollment capacity!");
		}

		this.waitlistCapacity = waitlistCapacity;

		roster = new ArrayList<StudentRecord>();

		waitlist = new ArrayList<StudentRecord>();

	}

	/**
	 * Checks if the roster is full.
	 *
	 * @return true if the size of the roster is equal to the enrollment capacity,
	 *         false otherwise.
	 */
	public boolean isRosterFull() {
		if (roster == null) {
			return false;
		}

		if (roster.size() == enrollmentCapacity) {
			return true;
		}
		return false; // default return
	}

	/**
	 * Checks if the waitlist is full.
	 *
	 * @return true if the size of the waitlist is equal to the waitlist capacity,
	 *         false otherwise.
	 */
	public boolean isWaitlistFull() {
		if (waitlist == null) {
			return false;
		}

		if (waitlist.size() == waitlistCapacity) {
			return true;
		}

		return false; // default return
	}

	/**
	 * Checks if the course enrollment is closed. A course enrollment is considered
	 * closed if both the roster and the waitlist are full.
	 *
	 * @return true if both the roster and the waitlist are full, false otherwise.
	 */
	public boolean isCourseEnrollmentClosed() {
		if (isRosterFull() && isWaitlistFull()) {
			return true;
		}
		return false; // default return
	}

	/**
	 * Getter for course name
	 * 
	 * @return string the name of the course
	 */
	public String getName() {
		// TOOD: Implement this method with respect to the javadocs
		return courseName; // default return
	}

	/**
	 * Returns a deep copy (NOT the deepest) of this course enrollment's roster
	 * 
	 * @return a deep copy of the roster, and null if roster is null
	 */
	public ArrayList<StudentRecord> deepCopyRoster() {
		if (roster == null) {
			return null;
		}

		ArrayList<StudentRecord> deepRecord = new ArrayList<StudentRecord>();

		for (int i = 0; i < roster.size(); ++i) {
			deepRecord.add(roster.get(i));
		}
		return deepRecord;
	}

	/**
	 * Returns a deep copy (NOT the deepest) of this course enrollment's waitlist
	 * 
	 * @return a deep copy of the waitlist, and null if waitlist is null
	 */
	public ArrayList<StudentRecord> deepCopyWaitlist() {
		if (waitlist == null) {
			return null;
		}

		ArrayList<StudentRecord> deepWaitlist = new ArrayList<StudentRecord>();

		for (int i = 0; i < waitlist.size(); ++i) {
			deepWaitlist.add(waitlist.get(i));
		}
		return deepWaitlist;
	}

	/**
	 * Expands the enrollment capacity of the course by the increase amount. Does
	 * not affect the waitlist at all.
	 * 
	 * @param increase the non-negative amount to increase the capacity by
	 * @throws IllegalArgumentException with message "Increase amount must be
	 *                                  greater than zero!" if increase is not
	 *                                  larger than zero
	 */
	public void expandEnrollmentCapacity(int increase) throws IllegalArgumentException {
		if (increase <= 0) {
			throw new IllegalArgumentException("Increase amount must be greater than zero!");
		}

		enrollmentCapacity += increase;
	}

	/**
	 * Prints the list of all the students in the waitlist of the course, with
	 * respect to the following format.
	 */
	public void printWaitlist() {

		System.out.println("Waitlist capacity: " + this.waitlistCapacity);
		if (this.waitlist.isEmpty()) {
			System.out.println("The waitlist is empty.");
		} else {
			for (int i = 0; i < this.waitlist.size(); i++) {
				String waitlistString = (i + 1) + ". " + this.waitlist.get(i) + "\n";
				System.out.println(waitlistString);
			}
		}

	}

	/**
	 * Returns the student record object that has an exact match with campusID in
	 * the list passed as input. We assume that campusID values are unique.
	 * 
	 * @param campusID a string representing the campusID of a student.
	 * @param list     an ArrayList of StudentRecords
	 * @return StudentRecord record in list with an exact match with campusID.
	 * @throws NoSuchElementException with message "No student record found!" if no
	 *                                match found in the input list or if campusID
	 *                                is NOT valid.
	 * 
	 */
	public static StudentRecord searchById(String campusID, ArrayList<StudentRecord> list) {
		int index = -1;

		for (int i = 0; i < list.size(); ++i) {
			if (list.get(i).getCampusID() == campusID) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			throw new NoSuchElementException("No student record found!");
		}

		return list.get(index);
	}

	/**
	 * Appends (adds to the end) the student record to the waitlist if the waitlist
	 * has space, the student isn't already on the waitlist, isn't already enrolled
	 * in the course, and they meet the preReqs. Prints student.getName() + " was
	 * successfully added to the waitlist." if successful Throws exceptions
	 * described below.
	 * 
	 * @param student valid StudentRecord of student to be added
	 * @throws IllegalArgumentException if the student is already on the waitlist
	 *                                  with message "That student is already on the
	 *                                  waitlist!"
	 * @throws IllegalArgumentException if the student is already enrolled in the
	 *                                  course with message "That student is already
	 *                                  enrolled!"
	 * @throws IllegalStateException    if the waitlist is full with the message
	 *                                  "The waitlist is full!"
	 * @throws IllegalStateException    if the student does not have satisfactory
	 *                                  prerequisites with message "The
	 *                                  prerequisities are not satisfied for that
	 *                                  course!"
	 */
	public void addWaitlist(StudentRecord student) {
		if (waitlist.contains(student)) {
			throw new IllegalArgumentException("That student is already on the waitlist!");
		}

		if (roster.contains(student)) {
			throw new IllegalArgumentException("That student is already enrolled!");
		}

		if (isWaitlistFull()) {
			throw new IllegalStateException("The waitlist is full!");
		}

		if (!student.isPrerequisiteSatisfied()) {
			throw new IllegalStateException("The prerequisites are not satisfied for that course!");
		}

		waitlist.add(student);

		System.out.println(student.getName() + " was successfully added to the waitlist.");
		// Add implementation-level comments to highlight the major algorithmic steps to
		// resolve this
		// method FIRST!
		// You can use ArrayList.contains() method to check whether there is a match
		// with student in
		// roster or waitlist
	}

	/**
	 * Enrolls one student given their StudentRecord. Only enrolls the student if
	 * the following<br>
	 * conditions are met, otherwise throws an appropriate error described below:
	 * <br>
	 * - student is not already enrolled in the course <br>
	 * - the course has space <br>
	 * - the student has satisfied the prerequisities<br>
	 * Prints student.getName() + " was successfully enrolled in this class." if the
	 * enrollment was successful. <br>
	 * Removes the student from the waitlist if they were on it.<br>
	 * 
	 * @param student StudentRecord for the student to add
	 * @throws IllegalStateException with message "That student is already
	 *                               enrolled!" is the student is already enrolled
	 * @throws IllegalStateException with message "The course is full." if the
	 *                               course is full. The course is considered full
	 *                               when the roster's size equals the enrollment
	 *                               capacity.
	 * @throws IllegalStateException with message "That student has not satisfied
	 *                               the prerequisites!" if student does not have
	 *                               the appropriate prerequisities
	 */
	public void enrollOneStudent(StudentRecord student) {
		if (roster == null || waitlist == null) {
			return;
		}

		for (int i = 0; i < roster.size(); ++i) {
			if (roster.get(i).getCampusID() == student.getCampusID()) {
				throw new IllegalStateException("That student is already enrolled!");
			}
		}

		if (isRosterFull()) {
			throw new IllegalStateException("The course is full.");
		}

		if (!student.isPrerequisiteSatisfied()) {
			throw new IllegalStateException("That student has not satisfied the prerequisites!");
		}

		roster.add(student);

		for (int i = 0; i < waitlist.size(); ++i) {
			if (waitlist.get(i).getCampusID() == student.getCampusID()) {
				waitlist.remove(i);
				break;
			}
		}

		System.out.println(student.getName() + " was successfully enrolled in this class.");

	}

	/**
	 * Removes a student from the roster based on a matching campusID
	 * 
	 * @param student the student's StudentRecord
	 * @throws NoSuchElementException with message "There is no matching student in
	 *                                the roster!" if the student is not in the
	 *                                roster
	 */
	public void dropCourse(StudentRecord student) {
		String campusID = student.getCampusID();

		int index = -1;

		for (int i = 0; i < roster.size(); ++i) {
			if (roster.get(i).getCampusID() == campusID) {
				index = i;
				break;
			}
		}

		if (index == -1) {
			throw new NoSuchElementException("There is no matching student in the roster!");
		}

		roster.remove(index);
		// TOOD: Implement this method with respect to the javadocs
	}

	/**
	 * Returns a String representation of this exceptional course enrollment The
	 * string should be of the form: <BR>
	 */
	@Override
	public String toString() {
		// Provided to students
		String rosterString = "";
		rosterString = rosterString + "Course Name: " + this.courseName + "\n";
		rosterString = rosterString + "Number of enrolled students: " + this.roster.size() + "\n";
		for (int i = 0; i < this.roster.size(); i++) {
			String studentString = this.roster.get(i).toString();
			rosterString = rosterString + (i + 1) + ". " + studentString + "\n";
		}
		return rosterString.trim();
	}

	/**
	 * Returns a string of the roster of the course, with the string representation
	 * of each StudentRecord stored in the ArrayList roster in a separate line.
	 * 
	 * @return String representing the roster to the above specifications
	 */
	public String rosterToString() {
		String rosterString = "";
		for (int i = 0; i < this.roster.size(); i++) {
			rosterString += this.roster.get(i) + "\n";
		}
		return rosterString.trim();

	}

	/**
	 * Saves the string representation of the roster to a file passed as input. Does
	 * this by calling the rosterToString() method and writing the string to a file.
	 * 
	 * You can use a PrintWriter or a FileWriter to do this.
	 * 
	 * Catches and prints the message associated with any IOException that might be
	 * thrown.
	 * 
	 * @param file the path of the output file
	 */
	public void saveRoster(File file) {
		try {
			PrintWriter writer = new PrintWriter(file);
			writer.write(rosterToString());
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Helper method to parse a line from a loaded roster and convert it to a
	 * StudentRecord object. The line represents a String representation of a
	 * student. Extra whitespace at the beginning and end of the line should be
	 * disregarded.
	 * 
	 * A String representation of a StudentRecord should be at the following format:
	 * <BR>
	 * name, email, campusID, preReqValue
	 * 
	 * Where name represents the name of a student,<BR>
	 * email represents the email address of a student,<BR>
	 * campusID represents the campus ID of a student,<BR>
	 * preReqValue should be parsable to a boolean telling whether the
	 * pre-requisites of the course are satisfied.
	 * 
	 * 
	 * @param line a string representing a student from a saved roster
	 * @return StudentRecord the StudentRecord generated from that line
	 * 
	 * @throws DataFormatException if the line is not formatted correctly. A line is
	 *                             not correctly formatted if it is not at the above
	 *                             format where name, email, campusID, preReqValue
	 *                             are valid and separated by ", ".
	 */
	private StudentRecord lineToRecord(String line) throws DataFormatException {
		line = line.trim();

		String[] organizer = line.split(", ");

		String name = organizer[0];
		String email = organizer[1];
		String campusID = organizer[2];
		boolean preReqValue = Boolean.parseBoolean(organizer[3]);

		StudentRecord newStudent = new StudentRecord(name, email, campusID, preReqValue);

		boolean validName = StudentRecord.isValidName(name);
		boolean validEmail = StudentRecord.isValidEmail(email);
		boolean validCampusId = StudentRecord.isValidCampusID(campusID);

		if (!validName || !validEmail || !validCampusId) {
			throw new DataFormatException();
		}

		return newStudent;

	}

	/**
	 * Loads a roster in from a file. The file contains string representations of
	 * StudentRecords each in a separate file.
	 * 
	 * Enrolls each student until the end of the file or the capacity of the roster
	 * is reached.
	 * 
	 * Catches FileNotFoundException and prints the message "Could not find that
	 * file!"
	 * 
	 * @throws IllegalStateException with message "The course capacity would be
	 *                               exceeded by loading that student!" if the
	 *                               roster size would be exceeded after adding that
	 *                               student.
	 * @param rosterFile file object to read
	 */
	public void loadRoster(File rosterFile) throws DataFormatException {

		try {
			Scanner input = new Scanner(rosterFile);

			int enrolled = 0;

			for (int i = 0; i < enrollmentCapacity; ++i) {
				enrollOneStudent(lineToRecord(input.nextLine()));
				enrolled++;
				if (!input.hasNextLine()) {
					break;
				}
			}

			if (enrolled == enrollmentCapacity && input.hasNextLine()) {
				input.close();
				throw new IllegalStateException("The course capacity would be exceeded by loading that student!");
			}

			input.close();

		} catch (FileNotFoundException e) {
			System.out.println("Could not find that file!");
		} catch (Exception f) {
		}

	}

}
