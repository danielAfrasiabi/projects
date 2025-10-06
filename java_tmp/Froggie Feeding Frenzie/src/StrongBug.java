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

/*
 * A subclass of Bug that is Movable. These bugs only move when they are not at max health. 
 * When hit they become smaller and start moving horizontally across the screen.
 */
public class StrongBug extends Bug implements Moveable {
  // the max health of this StrongBug
  private final int MAX_HEALTH;

  // the number of points ALL StrongBugs are worth, 500
  private static final int POINTS = 500;

  // the current health of this StringBug, updates when Bug takes damage
  private int currentHealth;

  /*
   * Creates a new StrongBug object at full health using the provided parameters.
   * 
   * @param x the x-coordinate for the center of this StrongBug
   * 
   * @param y the y-coordinate for the center of this StrongBug
   * 
   * @param health the max health for this StrongBug
   */
  public StrongBug(float x, float y, int health) throws IllegalArgumentException {
    super(x, y, POINTS);
    MAX_HEALTH = health;
    currentHealth = MAX_HEALTH;

    if (health < 1) {
      throw new IllegalArgumentException("Oh no! Looks like you don't have" + "enough health to play the game.");
    }

  }

  /*
   * Moves this StrongBug 3 units to the right, wrapping around the screen when
   * the center hits the edge of the window. The Hitbox should move with the
   * StrongBug. The x,y-coordinate of only changes if the StrongBug should move.
   * 
   */
  @Override
  public void move() {
    if (!shouldMove()) {
      return;
    }

    // sets x-position to 0 if bug moves out of screen
    if (getX() > 800) {
      setX(0);
      getHitbox().setPosition(getX(), getY());
    }

    // continuously moves to the right otherwise
    else {
      setX(getX() + 3);
      getHitbox().setPosition(getX(), getY());
    }
  }

  /*
   * Reports if this StrongBug is dead.
   * 
   * @return true if its currentHealth is 0 or less, false otherwise
   */
  public boolean isDead() {
    if (currentHealth <= 0) {
      return true;
    }

    return false;
  }

  /*
   * Decreases the health of this StrongBug by 1.
   */
  public void loseHealth() {
    currentHealth -= 1;
  }

  /*
   * Reports if the StrongBug needs to move.
   * 
   * @return true if the bug is NOT at full health, false otherwise
   */
  @Override
  public boolean shouldMove() {
    if (currentHealth == MAX_HEALTH) {
      return false;
    }

    return true;
  }

  /*
   * Determines whether or not this bug has been eaten by the Frog.
   * 
   * @param f the frog that has possibly eaten this bug
   * 
   * @return true if this Bug's Hitbox overlaps that Frog's Tongue's Hitbox, false
   * otherwise
   */
  @Override
  public boolean isEatenBy(Frog f) {
    if (getHitbox().doesCollide(f.getTongueHitbox())) {
      loseHealth();

      // makes bug image smaller after being hit by frog's tongue
      double newWidth = ((double) image.width) * 0.75;
      double newHeight = ((double) image.height) * 0.75;

      // resizes image
      image.resize((int) newWidth, (int) newHeight);

      // resizes hitbox according to bug image
      getHitbox().changeDimensions((int) newWidth, (int) newHeight);

      return true;
    }

    return false;

  }

}
