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

import java.util.ArrayList;

/**
 * This class models a collection of scores which are worth a given percentage
 * of a student's grade. No adjustments are made to these scores.
 */
public class AssignmentGroup {
	private ArrayList<SimpleAssignment> assignments; // An ArrayList containing the assignments associated with this
														// AssignmentGroup
	public final double PERCENT_OF_TOTAL; // The percent of the total grade this AssignmentGroup comprises

	/**
	 * Basic constructor, initializes an assignment group which is worth the given
	 * percentage of a student's grade. This method also sets up this object's
	 * ArrayList to accept new SimpleAssignments.
	 * 
	 * @param percent - the percent of the total grade that this assignment group
	 *                represents, assumed to be between 0 and 1.
	 */
	public AssignmentGroup(double percent) {
		assignments = new ArrayList<SimpleAssignment>();
		PERCENT_OF_TOTAL = percent;
	}

	/**
	 * Adds a single assignment object to this AssignmentGroup
	 * 
	 * @param assignment - the SimpleAssignment to add
	 */
	public void addAssignment(SimpleAssignment assignment) {
		assignments.add(assignment);

	}

	/**
	 * Adds a batch of assignments to this AssignmentGroup in the order they appear
	 * 
	 * @param assignmentBatch - a perfect-size array of SimpleAssignments to add;
	 *                        you may assume there are no null values present in
	 *                        this array
	 */
	public void addAssignments(SimpleAssignment[] assignmentBatch) {
		for (int i = 0; i < assignmentBatch.length; ++i) {
			assignments.add(assignmentBatch[i]);
		}
	}

	/**
	 * Retrieves an assignment at the given index in the AssignmentGroup
	 * 
	 * @param i - the index of the assignment to access (0-based)
	 * @return the assignment at the given index, or null if the index is out of
	 *         bounds
	 */
	public SimpleAssignment getAssignment(int i) {
		if (i >= assignments.size()) {
			return null;
		}

		return assignments.get(i);
	}

	/**
	 * Accesses the number of assignments currently stored in this AssignmentGroup
	 * 
	 * @return the number of assignments present in this AssignmentGroup
	 */
	public int getNumAssignments() {
		return assignments.size();
	}

	/**
	 * Accesses the total number of earned points across all assignments in this
	 * AssignmentGroup
	 * 
	 * @return the sum of all earned points of all assignments in this
	 *         AssignmentGroup
	 */
	public double getPoints() {
		double totalPoints = 0;
		for (int i = 0; i < assignments.size(); ++i) {
			totalPoints += assignments.get(i).getPoints();
		}

		return totalPoints;
	}

	/**
	 * Accesses the total number of points possible across all assignments in this
	 * AssignmentGroup. Be careful - not all assignments in this group are required
	 * to have the same number of points possible.
	 * 
	 * @return the sum of all possible points of all assignments in this
	 *         AssignmentGroup
	 */
	public int getTotalPossible() {
		int totalPossible = 0;
		for (int i = 0; i < assignments.size(); ++i) {
			totalPossible += assignments.get(i).POINTS_POSSIBLE;
		}

		return totalPossible;
	}

	/**
	 * Determines whether all assignments currently in this AssignmentGroup have
	 * been completed.
	 * 
	 * @return true if ALL assignments in this AssignmentGroup have been completed;
	 *         false otherwise
	 */
	public boolean isComplete() {
		for (int i = 0; i < assignments.size(); ++i) {
			if (!assignments.get(i).isComplete()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Creates a String representation of this AssignmentGroup. Each assignment is
	 * listed by number (1-based) and its String representation.
	 * 
	 * @return a String containing all of the assignments in this AssignmentGroup
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i < assignments.size(); i++) {
			result += assignments.get(i).toString();
		}

		return result;
	}

}
