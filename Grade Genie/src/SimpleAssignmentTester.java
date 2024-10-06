//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Course Grader Simulator
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

/**
 * This class includes many testers to verify function of SimpleAssignment class
 * 
 */
public class SimpleAssignmentTester {

	/**
	 * Call the basic SimpleAssignment constructor and test the accessor methods
	 * before and after // completing the assignment.
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	public static boolean testAccessors() {
		SimpleAssignment assignment = new SimpleAssignment(50);
		boolean testConstant = (50 == assignment.POINTS_POSSIBLE);
		assignment.complete(45.0);
		boolean testComplete = assignment.isComplete();

		boolean test = (testConstant && testComplete);

		SimpleAssignment assignment1 = new SimpleAssignment(100);
		boolean testConstant1 = (100 == assignment1.POINTS_POSSIBLE);
		assignment1.complete(75.0);
		boolean testComplete1 = assignment1.isComplete();

		boolean test1 = (testConstant1 && testComplete1);

		return (test && test1);
	}

	/**
	 * This method calls a number of shorter helper methods, which test various
	 * cases for a // SimpleAssignment with the bonus option available
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	public static boolean testSimpleBonus() {
		boolean result = testAwardBonus();
		result &= testBonusTwice();
		result &= testNoBonus();
		result &= testBonus105();

		return result;
	}

	/**
	 * Test that a completed assignment that scores less than 95% has the correct
	 * bonus value added to it when awardBonus() is called
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	private static boolean testAwardBonus() {
		double epsilon = 0.07;
		SimpleAssignment assignment = new SimpleAssignment(100, true);
		assignment.complete(90.0);
		assignment.awardBonus();
		double score = assignment.getPoints();
		boolean testCorrectBonus = (Math.abs(95.0 - score) < epsilon);

		return testCorrectBonus;
	}

	/**
	 * Verify that calling the awardBonus() method a second time does not modify the
	 * earned points result
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	private static boolean testBonusTwice() {

		double epsilon = 0.07;
		SimpleAssignment assignment = new SimpleAssignment(100, true);
		assignment.complete(90.0);
		assignment.awardBonus();
		assignment.awardBonus();
		double score = assignment.getPoints();
		boolean testCorrectBonus = (Math.abs(95.0 - score) < epsilon);

		return testCorrectBonus;
	}

	/**
	 * Verify that calling the awardBonus() method on an assignment with NO bonus
	 * available does NOT result in a bonus being applied to the earned points
	 * result
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	private static boolean testNoBonus() {
		double epsilon = 0.07;
		SimpleAssignment assignment = new SimpleAssignment(100, false);
		assignment.complete(90.0);
		assignment.awardBonus();
		double score = assignment.getPoints();
		boolean testCorrectBonus = (Math.abs(90.0 - score) < epsilon);

		return testCorrectBonus;
	}

	/**
	 * Verify that calling the awardBonus() method on an assignment whose earned
	 * points are > 95% of possible points does NOT result in a score that exceeds
	 * the total possible points
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	private static boolean testBonus105() {
		double epsilon = 0.07;
		SimpleAssignment assignment = new SimpleAssignment(100, true);
		assignment.complete(97.0);
		assignment.awardBonus();
		double score = assignment.getPoints();
		boolean testCorrectBonus = (Math.abs(100.0 - score) < epsilon);

		return testCorrectBonus;
	}

	/**
	 * This method calls a number of shorter helper methods, all of which test error
	 * cases in the SimpleAssignment class
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	public static boolean testSimpleErrorCases() {
		boolean result = testBadConstructorInput();
		result &= testBonusIncomplete();
		result &= testBadPointsEarned();
		result &= testCompleteAssignmentTwice();

		return result;
	}

	/**
	 * Test the SimpleAssignment constructor with bad input
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	private static boolean testBadConstructorInput() {
		SimpleAssignment assignment = new SimpleAssignment(0);

		boolean testPoints = (1 == assignment.POINTS_POSSIBLE);

		return testPoints;
	}

	/**
	 * Test the awardBonus() method on an assignment that has bonus available but is
	 * not yet completed
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	private static boolean testBonusIncomplete() {
		SimpleAssignment assignment = new SimpleAssignment(100, true);
		double epsilon = 0.07;
		assignment.awardBonus();

		assignment.complete(80.0);

		double score = assignment.getPoints();
		boolean testCorrectBonus = (Math.abs(80.0 - score) < epsilon);

		return testCorrectBonus;

	}

	/**
	 * Test the complete() method with input values outside of the allowed range and
	 * make sure that the points returned are what you expect for the given error
	 * case
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	private static boolean testBadPointsEarned() {

		SimpleAssignment assignment = new SimpleAssignment(100, true);
		SimpleAssignment assignment1 = new SimpleAssignment(100, true);

		double epsilon = 0.07;

		assignment.complete(101.0);
		assignment.complete(-1.0);

		double score = assignment.getPoints();
		double score1 = assignment1.getPoints();

		boolean testCorrectEarned = (Math.abs(100.0 - score) < epsilon);
		boolean testCorrectEarned1 = (Math.abs(0.0 - score1) < epsilon);

		return (testCorrectEarned && testCorrectEarned1);
	}

	/**
	 * Test calling complete() twice with different values, and make sure that the
	 * earned points value is NOT updated after the assignment has been completed
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	private static boolean testCompleteAssignmentTwice() {
		SimpleAssignment assignment = new SimpleAssignment(100, true);
		double epsilon = 0.07;

		assignment.complete(95.0);
		assignment.complete(70.0);
		double score = assignment.getPoints();

		boolean testCorrectBonus = (Math.abs(95.0 - score) < epsilon);

		return testCorrectBonus;
	}

	/**
	 * This method reports whether all provided SimpleAssignmentTester methods have
	 * passed
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	public static boolean runAllTests() {
		return testAccessors() && testSimpleBonus() && testSimpleErrorCases();
	}

	/**
	 * Main method to execute tests for both tester classes. Prints out PASS or FAIL
	 * for each tester method executed
	 * 
	 * @param args basic main method input
	 */
	public static void main(String[] args) {
		System.out.println("basic: " + (testAccessors() ? "PASS" : "fail"));
		System.out.println("bonus: " + (testSimpleBonus() ? "PASS" : "fail"));
		System.out.println("edge cases: " + (testSimpleErrorCases() ? "PASS" : "fail"));

		if (runAllTests()) {
			System.out.println("add one: " + (AssignmentGroupTester.testAddOneAssignment() ? "PASS" : "fail"));
			System.out.println("add many: " + (AssignmentGroupTester.testAddManyAssignments() ? "PASS" : "fail"));
			System.out.println("get total: " + (AssignmentGroupTester.testGetTotal() ? "PASS" : "fail"));
			System.out.println("get points: " + (AssignmentGroupTester.testGetPoints() ? "PASS" : "fail"));
			System.out.println("complete: " + (AssignmentGroupTester.testComplete() ? "PASS" : "fail"));
		} else {
			System.out.println("Your SimpleAssignment implementation does NOT pass its tests!\n"
					+ "Do NOT continue with AssignmentGroup until you have passed all SimpleAssignment tests.");
		}

	}
}
