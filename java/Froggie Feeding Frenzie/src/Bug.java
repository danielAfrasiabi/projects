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
 * Creates a new Bug object with the provided information.
 */
public class Bug extends GameActor {
  // how many points this bug gives for being eaten
  private int points;

  // path to the image used for bugs, all bugs use the same image
  private static final String IMG_PATH = "images/bug.png";

  /*
   * Creates a new Bug object with the provided information.
   */
  public Bug(float x, float y, int points) {
    super(x, y, IMG_PATH);

    this.points = points;
  }

  /*
   * Gets how many points this Bug is worth
   * 
   * @return the number of points this Bug is worth
   */
  public int getPoints() {
    return points;
  }

  /*
   * Determines whether or not this bug has been eaten by the Frog.
   * 
   * @param f the frog that has possibly eaten this bug
   * 
   * @return true if this Bug's Hitbox overlaps that Frog's Tongue's Hitbox, false
   * otherwise note this method should also return false if the tongue is inactive
   */
  public boolean isEatenBy(Frog f) {
    if (this.getHitbox().doesCollide(f.getTongueHitbox())) {
      return true;
    }
    return false;
  }
}
