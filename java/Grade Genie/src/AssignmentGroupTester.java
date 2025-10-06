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
 * Tester class for the AssignmentGroup, DropAssignmentGroup, and
 * ScalingAssignmentGroup classes
 */
public class AssignmentGroupTester {

	/**
	 * Using the getNumAssignments() and getAssignment(i) accessor methods, verify
	 * that the addAssignment() method works properly in AssignmentGroup
	 * 
	 * @return true if tests pass, and false otherwise
	 */

	public static boolean testAddOneAssignment() {
		AssignmentGroup assignmentsGroup = new AssignmentGroup(50.0);
		SimpleAssignment assignment = new SimpleAssignment(100);
		assignment.complete(90.0);
		assignmentsGroup.addAssignment(assignment);

		boolean testNumAssign = (1 == assignmentsGroup.getNumAssignments());
		boolean testGetAssign = (assignment == assignmentsGroup.getAssignment(0));

		return (testNumAssign && testGetAssign);
	}

	/**
	 * Using the getNumAssignments() and getAssignment(i) accessor methods, verify
	 * that the addAssignments() method works properly in AssignmentGroup
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	public static boolean testAddManyAssignments() {
		AssignmentGroup assignmentsGroup = new AssignmentGroup(50.0);
		SimpleAssignment assignment = new SimpleAssignment(100);
		SimpleAssignment assignment1 = new SimpleAssignment(100);
		SimpleAssignment assignment2 = new SimpleAssignment(100);

		assignment.complete(90.0);
		assignment1.complete(80.0);
		assignment2.complete(70.0);

		assignmentsGroup.addAssignment(assignment);
		assignmentsGroup.addAssignment(assignment1);
		assignmentsGroup.addAssignment(assignment2);

		boolean testNumAssign = (3 == assignmentsGroup.getNumAssignments());
		boolean testGetAssign = (assignment2 == assignmentsGroup.getAssignment(2));

		return (testNumAssign && testGetAssign);
	}

	/**
	 * Verify that the getTotalPossible() method returns the expected value in EACH
	 * of the classes which implements the method
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	public static boolean testGetTotal() {
		boolean result = testGroupTotal();
		result &= testDropTotal();
		result &= testScaledTotal();

		return result;
	}

	/**
	 * Verify that getTotalPossible() works as expected in AssignmentGroup
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	private static boolean testGroupTotal() {
		AssignmentGroup assignmentsGroup = new AssignmentGroup(50.0);
		SimpleAssignment assignment = new SimpleAssignment(100);
		SimpleAssignment assignment1 = new SimpleAssignment(100);
		SimpleAssignment assignment2 = new SimpleAssignment(100);

		assignment.complete(90.0);
		assignment1.complete(80.0);
		assignment2.complete(70.0);

		assignmentsGroup.addAssignment(assignment);
		assignmentsGroup.addAssignment(assignment1);
		assignmentsGroup.addAssignment(assignment2);

		boolean testTotalPossible = (300.0 == assignmentsGroup.getTotalPossible());
		return testTotalPossible;
	}

	// Verify that getTotalPossible() works as expected in DropAssignmentGroup
	private static boolean testDropTotal() {

		DropAssignmentGroup assignmentsGroup = new DropAssignmentGroup(50.0, 1);
		SimpleAssignment assignment = new SimpleAssignment(100);
		SimpleAssignment assignment1 = new SimpleAssignment(100);
		SimpleAssignment assignment2 = new SimpleAssignment(90);

		assignment.complete(90.0);
		assignment1.complete(80.0);
		assignment2.complete(70.0);

		assignmentsGroup.addAssignment(assignment);
		assignmentsGroup.addAssignment(assignment1);
		assignmentsGroup.addAssignment(assignment2);

		boolean testTotalPossible = (200.0 == assignmentsGroup.getTotalPossible());
		return testTotalPossible;
	}

	/**
	 * Verify that getTotalPossible() works as expected in ScalingAssignmentGroup
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	private static boolean testScaledTotal() {

		ScalingAssignmentGroup assignmentsGroup = new ScalingAssignmentGroup(50.0, 1);
		SimpleAssignment assignment = new SimpleAssignment(100);
		SimpleAssignment assignment1 = new SimpleAssignment(100);
		SimpleAssignment assignment2 = new SimpleAssignment(100);

		assignment.complete(90.0);
		assignment1.complete(80.0);
		assignment2.complete(70.0);

		assignmentsGroup.addAssignment(assignment);
		assignmentsGroup.addAssignment(assignment1);
		assignmentsGroup.addAssignment(assignment2);

		boolean testTotalPossible = (300.0 == assignmentsGroup.getTotalPossible());
		return testTotalPossible;
	}

	/**
	 * Verify that the getPoints() method returns the expected value in EACH of the
	 * classes which implements the method
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	public static boolean testGetPoints() {
		boolean result = testGroupPoints();
		result &= testDropPoints();
		result &= testScaledPoints();

		return result;
	}

	/**
	 * Verify that getPoints() works as expected in AssignmentGroup
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	private static boolean testGroupPoints() {
		AssignmentGroup assignmentsGroup = new AssignmentGroup(50.0);
		SimpleAssignment assignment = new SimpleAssignment(100);
		SimpleAssignment assignment1 = new SimpleAssignment(100);
		SimpleAssignment assignment2 = new SimpleAssignment(100);

		assignment.complete(90.0);
		assignment1.complete(80.0);
		assignment2.complete(70.0);

		assignmentsGroup.addAssignment(assignment);
		assignmentsGroup.addAssignment(assignment1);
		assignmentsGroup.addAssignment(assignment2);

		boolean testTotalPossible = (240.0 == assignmentsGroup.getPoints());
		return testTotalPossible;
	}

	/**
	 * Verify that getPoints() works as expected in DropAssignmentGroup
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	private static boolean testDropPoints() {
		DropAssignmentGroup assignmentsGroup = new DropAssignmentGroup(50.0, 2);
		SimpleAssignment assignment = new SimpleAssignment(100);
		SimpleAssignment assignment1 = new SimpleAssignment(100);
		SimpleAssignment assignment2 = new SimpleAssignment(100);

		assignment.complete(90.0);
		assignment1.complete(80.0);
		assignment2.complete(70.0);

		assignmentsGroup.addAssignment(assignment);
		assignmentsGroup.addAssignment(assignment1);
		assignmentsGroup.addAssignment(assignment2);

		boolean testTotalPossible = (90.0 == assignmentsGroup.getPoints());
		return testTotalPossible;
	}

	/**
	 * Verify that getPoints() works as expected in ScalingAssignmentGroup
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	private static boolean testScaledPoints() {
		ScalingAssignmentGroup assignmentsGroup = new ScalingAssignmentGroup(50.0, 1);
		SimpleAssignment assignment = new SimpleAssignment(100);
		SimpleAssignment assignment1 = new SimpleAssignment(100);
		SimpleAssignment assignment2 = new SimpleAssignment(100);

		assignment.complete(90.0);
		assignment1.complete(80.0);
		assignment2.complete(70.0);

		assignmentsGroup.addAssignment(assignment);
		assignmentsGroup.addAssignment(assignment1);
		assignmentsGroup.addAssignment(assignment2);

		boolean testTotalPossible = (240.0 == assignmentsGroup.getPoints());
		return testTotalPossible;
	}

	/**
	 * Verify that the isComplete() accessor method works as expected in
	 * AssignmentGroup
	 * 
	 * @return true if tests pass, and false otherwise
	 */
	public static boolean testComplete() {
		AssignmentGroup assignmentsGroup = new AssignmentGroup(50.0);
		SimpleAssignment assignment = new SimpleAssignment(100);
		SimpleAssignment assignment1 = new SimpleAssignment(100);
		SimpleAssignment assignment2 = new SimpleAssignment(100);

		assignment.complete(90.0);
		assignment1.complete(80.0);
		assignment2.complete(70.0);

		assignmentsGroup.addAssignment(assignment);
		assignmentsGroup.addAssignment(assignment1);
		assignmentsGroup.addAssignment(assignment2);

		boolean testComplete = assignmentsGroup.isComplete();
		return testComplete;
	}

}
