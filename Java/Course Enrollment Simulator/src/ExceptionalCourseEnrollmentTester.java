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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.zip.DataFormatException;

/**
 * This utility class implements unit tests to check the correctness of methods
 * defined in the ExceptionalCourseEnrollment class of the Exceptional Course
 * Enrollment System program.
 *
 * @author Kai Tsimpidis and Daniel Afrasiabi
 */
public class ExceptionalCourseEnrollmentTester {

	/**
	 * Ensures the correctness of the StudentRecord.equals() method.
	 * 
	 * Defines at least two StudentRecord objects and checks for the following test
	 * cases:<BR>
	 * (1) StudentRecord.equals() is expected to return true when passed a
	 * StudentRecord with the same campusID as the current one. You can compare a
	 * student record to itself.<BR>
	 * (2) StudentRecord.equals() is expected to return false when passed a
	 * StudentRecord with campusID different from the campusID of the current
	 * student record. (3) StudentRecord.equals() is expected to return false when
	 * passed a String as input (4) StudentRecord.equals() is expected to return
	 * false when passed the reference null as input
	 * 
	 * 
	 * @return true if and only if the tester verifies a correct functionality and
	 *         false if at least one bug is detected
	 */
	public static boolean studentRecordEqualsTester() {
		try {
			StudentRecord student = new StudentRecord("Billy", "Billy@wisc.edu", "1010101010", true);
			StudentRecord student1 = new StudentRecord("Bill", "Bill@wisc.edu", "1010101010", true);
			boolean testTrue = student.equals(student1); // Should be true
			StudentRecord student2 = new StudentRecord("Billy", "Billy@wisc.edu", "1010101010", true);
			StudentRecord student3 = new StudentRecord("Bill", "Bill@wisc.edu", "1919191919", true);
			boolean testFalse = student2.equals(student3); // Should be false
			boolean testString = student2.equals("String"); // Should be false
			boolean testNull = student2.equals(null);
			return (testTrue && !testFalse && !testString && !testNull); // default return statement

		} catch (DataFormatException e) {
			return false;
		}

	}

	/**
	 * Ensures the correctness of the constructor of the StudentRecord class when
	 * called with VALID input
	 * 
	 * @return true if and only if the tester verifies a correct functionality and
	 *         false if at least one bug is detected
	 */
	public static boolean studentRecordConstructorSuccessful() {
		try {
			StudentRecord student = new StudentRecord("Billy", "Billy@wisc.edu", "1010101010", true);
			boolean testName = student.getName().equals("Billy");
			boolean testEmail = student.getEmail().equals("Billy@wisc.edu");
			boolean testCampusID = student.getCampusID().equals("1010101010");
			boolean testPreReq = student.isPrerequisiteSatisfied();

			return (testName && testEmail && testCampusID && testPreReq);
		} catch (DataFormatException e) {
			return false;
		}
	}

	/**
	 * Ensures the correctness of the constructor of the StudentRecord class when
	 * called with one INVALID input
	 * 
	 * @return true if and only if the tester verifies a correct functionality and
	 *         false if at least one bug is detected
	 */
	public static boolean studentRecordConstructorUnSuccessful() {
		String expectedOutput = "Bad name, email, or campusID!";
		String actualOutput = null;

		try {
			StudentRecord student = new StudentRecord("", "Billy@wisc.edu", "1010101010", true);

		} catch (IllegalArgumentException e) {
			actualOutput = "Not correct";
		} catch (Exception e) {
			actualOutput = "Bad name, email, or campusID!";
		}
		boolean testName = expectedOutput.equals(actualOutput);
		actualOutput = null;

		try {
			StudentRecord student1 = new StudentRecord(null, "Billy@wisc.edu", "1010101010", true);
		} catch (IllegalArgumentException e) {
			actualOutput = "Not correct";
		} catch (Exception e) {
			actualOutput = "Bad name, email, or campusID!";
		}
		boolean testName1 = expectedOutput.equals(actualOutput);
		actualOutput = null;

		try {
			StudentRecord student2 = new StudentRecord("Billy", null, "1010101010", true);
		} catch (IllegalArgumentException e) {
			actualOutput = "Not correct";
		} catch (Exception e) {
			actualOutput = "Bad name, email, or campusID!";
		}
		boolean testEmail = expectedOutput.equals(actualOutput);
		actualOutput = null;

		try {
			StudentRecord student3 = new StudentRecord("Billy", "Billy@@wisc.edu", "1010101010", true);
		} catch (IllegalArgumentException e) {
			actualOutput = "Not correct";
		} catch (Exception e) {
			actualOutput = "Bad name, email, or campusID!";
		}
		boolean testEmail2 = expectedOutput.equals(actualOutput);
		actualOutput = null;

		try {
			StudentRecord student4 = new StudentRecord("Billy", "Billy@wisc.com", "1010101010", true);
		} catch (IllegalArgumentException e) {
			actualOutput = "Not correct";
		} catch (Exception e) {
			actualOutput = "Bad name, email, or campusID!";
		}
		boolean testEmail3 = expectedOutput.equals(actualOutput);
		actualOutput = null;

		try {
			StudentRecord student5 = new StudentRecord("Billy",
					"Billy@wwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwwisc.edu", "1010101010", true);
		} catch (IllegalArgumentException e) {
			actualOutput = "Not correct";
		} catch (Exception e) {
			actualOutput = "Bad name, email, or campusID!";
		}
		boolean testEmail4 = expectedOutput.equals(actualOutput);
		actualOutput = null;

		try {
			StudentRecord student6 = new StudentRecord("Billy", "B@wisc.edu", "1010101010", true);
		} catch (IllegalArgumentException e) {
			actualOutput = "Not correct";
		} catch (Exception e) {
			actualOutput = "Bad name, email, or campusID!";
		}
		boolean testEmail5 = expectedOutput.equals(actualOutput);
		actualOutput = null;

		try {
			StudentRecord student7 = new StudentRecord("Billy", "Billy@wisc.edu", "1010101", true);
		} catch (IllegalArgumentException e) {
			actualOutput = "Not correct";
		} catch (Exception e) {
			actualOutput = "Bad name, email, or campusID!";
		}
		boolean testID = expectedOutput.equals(actualOutput);
		actualOutput = null;

		try {
			StudentRecord student8 = new StudentRecord("Billy", "Billy@wisc.edu", "1010101010000", true);
		} catch (IllegalArgumentException e) {
			actualOutput = "Not correct";
		} catch (Exception e) {
			actualOutput = "Bad name, email, or campusID!";
		}
		boolean testID1 = expectedOutput.equals(actualOutput);
		actualOutput = null;

		try {
			StudentRecord student9 = new StudentRecord("Billy", "Billy@wisc.edu", "101010gg10", true);
		} catch (IllegalArgumentException e) {
			actualOutput = "Not correct";
		} catch (Exception e) {
			actualOutput = "Bad name, email, or campusID!";
		}
		boolean testID2 = expectedOutput.equals(actualOutput);

		return (testName && testName1 && testEmail && testEmail2 && testEmail3 && testEmail4 && testEmail5 && testID
				&& testID1 && testID2);
	}

	/**
	 * Ensures the correctness of the searchById() method
	 * 
	 * Creates an ArrayList which contains at least 2 student records, and defines
	 * at least two cases:
	 * 
	 * (1) successful search<BR>
	 * (2) unsuccessful search<BR>
	 * 
	 * 
	 * @throws NoSuchElementException if the search result is not found
	 * @return true if and only if the tester verifies a correct functionality and
	 *         false if at least one bug is detected
	 */
	public static boolean searchByIdTester() {
		String errMsg = "Bug detected: search did not return the expected result.";
		try {
			// Create an arraylist which contains 3 student records
			ArrayList<StudentRecord> records = new ArrayList<StudentRecord>();
			StudentRecord s1 = new StudentRecord("Rob", "rob@wisc.edu", "1234567890", true);
			StudentRecord s2 = new StudentRecord("Joey", "joey@wisc.edu", "1233367890", true);
			StudentRecord s3 = new StudentRecord("NotHere", "no@wisc.edu", "1111167890", true);
			records.add(s1);
			records.add(s2);

			// Finds a student in the arraylist

			StudentRecord r1 = ExceptionalCourseEnrollment.searchById(s1.getCampusID(), records);
			if (r1 != s1) {
				return false;
			}
			// Does'nt find a student not in the array
			try {
				ExceptionalCourseEnrollment.searchById(s3.getCampusID(), records);
				return false; // a NoSuchElementException was not thrown as expected
			} catch (NoSuchElementException e) {
				// check for the error message
				String expectedErrorMessage = "No student record found!";
				if (!e.getMessage().equals(expectedErrorMessage)) {
					System.out.println("The NoSuchElementException did not contain the expected error message!");
					return false;
				}
			}

		} catch (Exception e) {
			System.out.println(errMsg);
			return false;
		}

		try {

		} catch (Exception e) {
			return true;
		}
		return true;

	}

	/**
	 * Runs all the tester methods defined in this class.
	 * 
	 * @return true if no bugs are detected.
	 */
	public static boolean runAllTests() {
		boolean searchTesterOutput = searchByIdTester();
		System.out.println("searchTester: " + (searchTesterOutput ? "Pass" : "Failed!"));

		System.out.println("-----------------------------------------------");
		boolean studentRecordEqualsTesterOutput = studentRecordEqualsTester();
		System.out.println("studentRecordEqualsTester: " + (studentRecordEqualsTesterOutput ? "Pass" : "Failed!"));

		System.out.println("-----------------------------------------------");
		boolean studentRecordConstructorSuccessfulOutput = studentRecordConstructorSuccessful();
		System.out.println("studentRecordConstructorSuccessful: "
				+ (studentRecordConstructorSuccessfulOutput ? "Pass" : "Failed!"));

		System.out.println("-----------------------------------------------");
		boolean studentRecordConstructorUnSuccessfulOutput = studentRecordConstructorUnSuccessful();
		System.out.println("studentRecordConstructorUnSuccessful: "
				+ (studentRecordConstructorUnSuccessfulOutput ? "Pass" : "Failed!"));
		System.out.println("-----------------------------------------------");
		return searchTesterOutput && studentRecordEqualsTesterOutput && studentRecordConstructorSuccessfulOutput;
	}

	/**
	 * Main method to run this tester class.
	 * 
	 * @param args list of input arguments if any
	 */
	public static void main(String[] args) {
		System.out.println("-----------------------------------------------");
		System.out.println("runAllTests: " + (runAllTests() ? "Pass" : "Failed!"));
	}

}
