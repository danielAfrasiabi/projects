
//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Catch the Bug, Froggy!
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

/*
 * A subclass of Bug that is Movable. These bugs bounce around the screen like a DVD player logo.
 */
public class BouncingBug extends Bug implements Moveable {

  // keeps track if bug is moving up or down
  private boolean goDown;

  // keeps track if bug is moving left or right
  private boolean goLeft;

  // a constant, number of points ALL BouncingBugs are worth, 100
  private static final int POINTS = 100;

  // a random generator to determine the initial directions
  private Random randGen = new Random();

  // the number of pixels to move horizontally and vertically, formatted [dx,dy]
  private int[] speedNums;

  /*
   * Creates a new Bouncing Bug object using the given parameters.
   */
  public BouncingBug(float x, float y, int dx, int dy) {
    super(x, y, POINTS);

    speedNums = new int[] { dx, dy };

    goDown = randGen.nextBoolean();
    goLeft = randGen.nextBoolean();

  }

  /*
   * Moves this BouncingBug dx pixels left or right (depending on the current
   * horizontal direction) and dy pixels up or down (depending on the current
   * vertical direction).
   */
  @Override
  public void move() {
    if (!shouldMove()) {
      return;
    }

    // hit left boundary, go right
    if (goLeft && getX() <= 0) {
      goLeft = false;
      setX(getX() + speedNums[0]);
      getHitbox().setPosition(getX(), getY());
    }

    // hit right boundary, go left
    if (!goLeft && getX() >= 800) {
      goLeft = true;
      setX(getX() - speedNums[0]);
      getHitbox().setPosition(getX(), getY());
    }

    // hit upper boundary, go down
    if (!goDown && getY() <= 0) {
      goDown = true;
      setY(getY() + speedNums[1]);
      getHitbox().setPosition(getX(), getY());
    }

    // hit lower boundary, go up
    if (goDown && getY() >= 600) {
      goDown = false;
      setY(getY() - speedNums[1]);
      getHitbox().setPosition(getX(), getY());
    }

    // no boundaries hit, continue going left
    if (goLeft && getX() > 0 && getX() < 800) {
      setX(getX() - speedNums[0]);
      getHitbox().setPosition(getX(), getY());
    }

    // no boundaries hit, continue going right
    if (!goLeft && getX() > 0 && getX() < 800) {
      setX(getX() + speedNums[0]);
      getHitbox().setPosition(getX(), getY());
    }

    // no boundaries hit, continue going down
    if (goDown && getY() > 0 && getY() < 600) {
      setY(getY() + speedNums[1]);
      getHitbox().setPosition(getX(), getY());
    }

    // no boundaries hit, continue going up
    if (!goDown && getY() > 0 && getY() < 600) {
      setY(getY() - speedNums[1]);
      getHitbox().setPosition(getX(), getY());
    }

  }

  /*
   * Reports if the BouncingBug needs to move.
   * 
   * @return true, this Bug ALWAYS moves
   */
  @Override
  public boolean shouldMove() {
    return true;
  }

}
