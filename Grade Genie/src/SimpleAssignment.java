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

import java.util.Random;

/**
 * 
 * This class models a basic assignment with points possible, points earned, and
 * an optional bonus of 5% of possible points.
 * 
 * @author Kai Tsimpidis and Daniel Afrasiabi
 * 
 */

public class SimpleAssignment {

	// Indicates whether a 5% bonus is offered
	// (and has not yet been applied) for this assignment
	private boolean bonusAvailable = false;

	// Indicates whether this assignment has been completed or not
	private boolean isComplete = false;

	// The score the student received on this assignment; only set when the
	// assignment is completed
	private double pointsEarned = 0.0;

	// The number of points possible on this assignment; must be strictly positive
	public final int POINTS_POSSIBLE;

	/**
	 * creates an assignment that has not been completed and does not have a bonus
	 * available.
	 * 
	 * @param points gives the integer value for the max points on the assignment
	 */
	public SimpleAssignment(int points) {
		// The number of points possible on this assignment; must be strictly positive
		if (points < 1) {
			POINTS_POSSIBLE = 1;
		} else {
			POINTS_POSSIBLE = points;
		}

	}

	/**
	 * creates an assignment that has not been completed and could have a bonus
	 * available.
	 * 
	 * @param points gives the integer value for the max points on the assignment
	 * @param bonus  true or false whether or not student is eligible for grade
	 *               bonus
	 */

	public SimpleAssignment(int points, boolean bonus) {
		// The number of points possible on this assignment; must be strictly positive
		if (points < 1) {
			POINTS_POSSIBLE = 1;
		} else {
			POINTS_POSSIBLE = points;
		}

		bonusAvailable = bonus;
	}

	/**
	 * If assignment was completed and there is a bonus available, adds %5 of
	 * possible points to the earned points total, up to a maximum number of
	 * possible points
	 * 
	 */
	public void awardBonus() {
		if (isComplete() && bonusAvailable) {
			if ((pointsEarned + (POINTS_POSSIBLE * 0.05)) >= POINTS_POSSIBLE) {
				pointsEarned = POINTS_POSSIBLE;
				bonusAvailable = false;
			} else {
				pointsEarned = pointsEarned + (POINTS_POSSIBLE * 0.05);
				bonusAvailable = false;
			}
		}

	}

	/**
	 * Completes the assignment and records the provided score
	 * 
	 * @param score score received on assignment
	 */
	public void complete(double score) {
		if ((isComplete == false) && (score <= POINTS_POSSIBLE) && (score > 0.0)) {
			pointsEarned = score;
			isComplete = isComplete();
		}
		if ((isComplete == false) && (score > POINTS_POSSIBLE)) {
			pointsEarned = POINTS_POSSIBLE;
			isComplete = isComplete();
		}
		if ((isComplete == false) && (score < 0.0)) {
			pointsEarned = 0.0;
			isComplete = isComplete();
		}
	}

	/**
	 * Reports whether this assignment has been completed yet
	 *
	 * @return true if this assignment has been completed, false otherwise
	 */
	public boolean isComplete() {
		if (pointsEarned > 0.0) {
			return true;
		} else {
			return false;
		}
	}

	public double getPoints() {
		if (isComplete) {
			return pointsEarned;
		} else {
			return 0.0;
		}

	}

	/**
	 * Creates random assignment scores with a mean of 80% and a standard deviation
	 * of 15%.
	 * 
	 * @param n        the number of assignment scores to create
	 * @param maxScore the maximum score value to create
	 * @return an array of the SimpleAssignments created under those requirements
	 */
	public static SimpleAssignment[] makeRandomAssignments(int n, int maxScore) {
		Random rand = new Random();
		SimpleAssignment[] result = new SimpleAssignment[n];
		double mean = 0.80;
		double std = 0.15;
		for (int i = 0; i < n; i++) {
			double pctScore = rand.nextGaussian(mean, std);
			result[i] = new SimpleAssignment(maxScore);
			result[i].complete(pctScore * maxScore);
		}
		return result;
	}

	/**
	 * Creates and returns a String representation of this SimpleAssignment
	 * 
	 * @return a string representation of SimpleAssignment
	 */
	public String toString() {
		if (isComplete()) {
			return pointsEarned + "/" + POINTS_POSSIBLE;
		}

		else {
			return "*/" + POINTS_POSSIBLE;
		}
	}

}
