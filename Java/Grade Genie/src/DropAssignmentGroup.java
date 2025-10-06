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
 * of a student's grade. When calculating the point values for this type of
 * assignment group, the lowest N scores are dropped, which is facilitated using
 * a pair of static utility methods.
 */
public class DropAssignmentGroup {
	private ArrayList<SimpleAssignment> assignments; // An ArrayList containing the assignments associated with this
														// DropAssignmentGroup
	public final double PERCENT_OF_TOTAL; // The percent of the total grade this DropAssignmentGroup comprises
	private int numDropped; // The number of assignments to be dropped from this group; must be strictly
							// positive

	/**
	 * Basic constructor, initializes an assignment group which is worth the given
	 * percentage of a student's grade and which allows a given number of dropped
	 * scores. This method also sets up this object's ArrayList to accept new
	 * SimpleAssignments.
	 * 
	 * @param percent - the percent of the total grade that this assignment group
	 *                represents, assumed to be between 0 and 1.
	 * @param drops   - the number of assignments to be dropped; if the given number
	 *                is less than 1, the constructor will set it to 1.
	 */
	public DropAssignmentGroup(double percent, int drops) {
		assignments = new ArrayList<SimpleAssignment>();
		PERCENT_OF_TOTAL = percent;
		numDropped = drops;
	}

	/**
	 * Adds a single assignment object to this DropAssignmentGroup
	 * 
	 * @param assignment - the SimpleAssignment to add
	 */
	public void addAssignment(SimpleAssignment assignment) {
		assignments.add(assignment);
	}

	/**
	 * Adds a batch of assignments to this DropAssignmentGroup in the order they
	 * appear
	 * 
	 * @param assignmentBatch assignmentBatch - a perfect-size array of
	 *                        SimpleAssignments to add; you may assume there are no
	 *                        null values present in this array
	 */
	public void addAssignments(SimpleAssignment[] assignmentBatch) {
		for (int i = 0; i < assignmentBatch.length; ++i) {
			assignments.add(assignmentBatch[i]);
		}
	}

	/**
	 * Without modifying the input ArrayList, creates a NEW ArrayList which contains
	 * all but the lowest- scoring N (numDropped) assignments from the input
	 * ArrayList. If the input ArrayList contains N (numDropped) or fewer
	 * assignments, the returned ArrayList will be empty.
	 * 
	 * @param assignments - an ArrayList containing all assignments
	 * @param n           - the number of assignments to drop
	 * @return a COPY of the input ArrayList which contains all but the
	 *         lowest-scoring n (NOT numDropped) assignments
	 */
	public static ArrayList<SimpleAssignment> dropLowestN(ArrayList<SimpleAssignment> assignments, int n) {
		ArrayList<SimpleAssignment> copyOfAssignments = new ArrayList<SimpleAssignment>();
		ArrayList<SimpleAssignment> newAssignments = new ArrayList<SimpleAssignment>();

		if (assignments.size() <= n) {
			return newAssignments;
		}

		for (int i = 0; i < assignments.size(); ++i) {
			copyOfAssignments.add(assignments.get(i));
		}

		for (int i = 0; i < assignments.size(); ++i) {
			int lowestScoringIndex = getLowestScoreIndex(copyOfAssignments);
			newAssignments.add(copyOfAssignments.get(lowestScoringIndex));
			copyOfAssignments.remove(lowestScoringIndex);
		}

		for (int i = 0; i < n; ++i) {
			newAssignments.remove(0);
		}

		return newAssignments;
	}

	/**
	 * Retrieves an assignment at the given index in the DropAssignmentGroup, NOT
	 * accounting for drops
	 * 
	 * @param i - the index of the assignment to access (0-based)
	 * @return the assignment at the given index, or null if the index is out of
	 *         bounds
	 */
	public SimpleAssignment getAssignment(int i) {
		return assignments.get(i);

	}

	/**
	 * Finds the index of the lowest scoring assignment in the given ArrayList. In
	 * the case of ties, this method should prefer the assignment with the lower
	 * index. No other form of tie-breaking (e.g. points possible, completeness,
	 * etc) should be implemented.
	 * 
	 * @param assignments - an ArrayList containing the assignments to analyze
	 * @return the index (0-based) of the lowest scoring assignment
	 */
	public static int getLowestScoreIndex(ArrayList<SimpleAssignment> assignments) {
		int lowestScoringIndex = 0;
		for (int i = 0; i < assignments.size(); ++i) {
			if (assignments.get(i).getPoints() < assignments.get(lowestScoringIndex).getPoints()) {
				lowestScoringIndex = i;
			}
		}

		return lowestScoringIndex;
	}

	/**
	 * Accesses the number of assignments currently stored in this
	 * DropAssignmentGroup, NOT accounting for drops
	 * 
	 * @return the number of assignments present in this DropAssignmentGroup
	 */
	public int getNumAssignments() {
		return assignments.size();
	}

	/**
	 * Accesses the total number of earned points across all assignments in this
	 * DropAssignmentGroup, after dropping the lowest N (numDropped)
	 * 
	 * @return the sum of all earned points of all non-dropped assignments in this
	 *         DropAssignmentGroup
	 */
	public double getPoints() {
		ArrayList<SimpleAssignment> newAssignments = dropLowestN(assignments, numDropped);

		double totalPoints = 0;

		for (int i = 0; i < newAssignments.size(); ++i) {
			totalPoints += newAssignments.get(i).getPoints();
		}

		return totalPoints;
	}

	/**
	 * Accesses the total number of points possible across all assignments in this
	 * DropAssignmentGroup, after dropping the lowest N (numDropped).
	 * 
	 * @return the sum of all possible points of all non-dropped assignments in this
	 *         DropAssignmentGroup
	 */
	public int getTotalPossible() {
		ArrayList<SimpleAssignment> newAssignments = dropLowestN(assignments, numDropped);

		int totalPossible = 0;

		for (int i = 0; i < newAssignments.size(); ++i) {
			totalPossible += newAssignments.get(i).POINTS_POSSIBLE;
		}

		return totalPossible;
	}

	/**
	 * Determines whether all assignments currently in this DropAssignmentGroup have
	 * been completed, after dropping the lowest N (numDropped).
	 * 
	 * @return true if ALL non-dropped assignments in this DropAssignmentGroup have
	 *         been completed; false otherwise
	 */
	public boolean isComplete() {
		ArrayList<SimpleAssignment> newAssignments = dropLowestN(assignments, numDropped);

		for (int i = 0; i < newAssignments.size(); ++i) {
			if (!newAssignments.get(i).isComplete()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Creates a String representation of this DropAssignmentGroup. Each assignment
	 * is listed by number (1-based) and its String representation.
	 * 
	 * @return a String containing all of the non-dropped assignments in this
	 *         DropAssignmentGroup
	 */
	public String toString() {
		String result = "";
		for (int i = 0; i < assignments.size(); i++) {
			result += assignments.get(i).toString();
		}

		return result;
	}

}
