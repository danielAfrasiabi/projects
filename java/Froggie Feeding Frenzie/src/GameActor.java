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

/**
 * An instantiable class for all game actors in the Froggie Feeding Frenzie
 * game. Game actors are images that are drawn the screen that also have
 * hitboxes.
 */
public class GameActor {
  // PApplet to use to draw GameActors to the screen
  protected static processing.core.PApplet processing;
  // the x,y-coordinates of the center stored as [x,y]
  private float[] coordinates;
  // the image associated with this GameActor
  protected processing.core.PImage image;
  // the hitbox associated with this GameActor
  private Hitbox hitbox;

  /**
   * Constructor for a new GameActor object by setting the coordinates, loading
   * the image, and creating the hitbox.
   * 
   * @param x       the x-coordinate for the center of this object and its hitbox
   * @param y       the y-coordinate for the center of this object and its hitbox
   * @param imgPath the path to the image that will be loaded in
   * @throws IllegalStateException with a descriptive message if processing is
   *                               null
   */
  public GameActor(float x, float y, String imgPath) throws IllegalStateException {
    if (processing == null) {
      throw new IllegalStateException(
          "Processing is null. setProcessing() must be called before " + "creating any Hitbox objects.");
    }
    coordinates = new float[] { x, y };
    image = processing.loadImage(imgPath);
    hitbox = new Hitbox(x, y, image.width, image.height);

  }

  /**
   * Sets the processing for all GameActors
   * 
   * @param processing the instance of a PApplet to draw onto
   */
  public static void setProcessing(processing.core.PApplet processing) {
    GameActor.processing = processing;
  }

  /**
   * Getter for the x-coordinate
   * 
   * @return the x-coordinate of center of this GameActor
   */
  public float getX() {
    return coordinates[0];
  }

  /**
   * Getter for the y-coordinate
   * 
   * @return the y-coordinate of center of this GameActor
   */
  public float getY() {
    return coordinates[1];
  }

  /**
   * Setter for the x-coordinate
   * 
   * @param newX the new x-coordinate for the center of this GameActor
   */
  public void setX(float newX) {
    coordinates[0] = newX;
  }

  /**
   * Setter for the y-coordinate
   * 
   * @param newY the new y-coordinate for the center of this GameActor
   */
  public void setY(float newY) {
    coordinates[1] = newY;
  }

  /**
   * Getter for the Hitbox.
   * 
   * @return the Hitbox of this GameActor
   */
  public Hitbox getHitbox() {
    return hitbox;
  }

  public void draw() {
    processing.image(image, getX(), getY());

  }

  /**
   * Moves this GameActors Hitbox to the provided x,y-coordinates
   * 
   * @param x the new x-coordinate for the center of the GameActor's hitbox
   * @param y the new y-coordinate for the center of the GameActor's hitbox
   */
  public void moveHitbox(float x, float y) {
    hitbox.setPosition(x, y);
  }
}
