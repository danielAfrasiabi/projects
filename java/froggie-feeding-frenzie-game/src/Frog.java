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
 * An instantiable class maintains data about a Frog in the Froggie Feeding
 * Frenzie game. They an be drawn to the screen, dragged around by the mouse,
 * and attack Bugs with its Tongue.
 */
public class Frog extends GameActor implements Moveable {
  // how much health the frog has
  private int health;
  // path to the image to use for the frog
  private static final String IMG_PATH = "images/frog.png";
  // keeps track of if the Frog is being dragged by the mouse
  private boolean isDragging;
  // the previous x-coordinate of the mouse
  private float oldMouseX;
  // the previous y-coordinate of the mouse
  private float oldMouseY;
  // the tongue belonging to this frog
  private Tongue tongue;

  /**
   * Constructor for a new Frog object using the provided parameters. The Frog is
   * NOT dragging by default.
   * 
   * @param x      the x-coordinate for the center of the Frog and starting point
   *               of the tongue
   * @param y      the y-coordinate for the center of the Frog and starting point
   *               of the tongue
   * @param health the initial health of this Frog
   * @throws IllegalArgumentException thrown if health is less than 1
   */
  public Frog(float x, float y, int health) throws IllegalArgumentException {
    super(x, y, IMG_PATH);
    if (health < 1) {
      throw new IllegalArgumentException("Health cannot be less than 1");
    }
    this.health = health;
    this.isDragging = false;

    this.oldMouseX = x;
    this.oldMouseY = y;
    tongue = new Tongue(x, y);
    Tongue.setProcessing(processing);

  }

  /**
   * Draws the image of the Frog to the screen. If the Frog's tongue is active:
   * (1)draw the tongue and (2) extend the tongue by moving it's x-coordinate to
   * the Frog's x-coordinate and up 2 pixels.
   */
  @Override
  public void draw() {
    // draw frog tongue
    if (tongue.isActive()) {
      tongue.draw();
      tongue.extend(this.getX(), -2);
    }
    // draw frog body
    processing.image(this.image, this.getX(), this.getY());

  }

  /**
   * Moves the Frog by dragging it by the mouse, if it should be dragging. (See
   * write-up for more details on implementing the dragging functionality.) The
   * starting point of the tongue and the Hitbox need to move along with the Frog.
   * If the Frog's tongue is NOT active, move the tongue's endpoint along with the
   * Frog as well. The Frog only moves if it should move.
   */
  public void move() {
    // sets the frogs new location to the location of mouse
    if (shouldMove()) {
      this.setX(processing.mouseX);
      this.setY(processing.mouseY);
    }

    float newX = this.getX();
    float newY = this.getY();
    this.moveHitbox(newX, newY);

    // moves tongue
    tongue.updateStartPoint(newX, newY);
    if (!(tongue.isActive())) {
      tongue.updateEndPoint(newX, newY);
    }

    this.oldMouseX = processing.mouseX;
    this.oldMouseY = processing.mouseY;
  }

  /**
   * Determines whether or not this Frog has run into a Bug.
   * 
   * @param b the Bug to check that if it collides with the Frog
   * @return true if the Bug's Hitbox and Frog's Hitbox overlap, false otherwise
   * 
   */
  public boolean isHitBy(Bug b) {
    if (this.getHitbox().doesCollide(b.getHitbox())) {
      return true;
    }

    return false;
  }

  /**
   * Gets the Hitbox for this Frog's tongue.
   * 
   * @return this Frog's tongue's hitbox
   * 
   * @throws IllegalStateException if the tongue is currently inactive
   */
  public Hitbox getTongueHitbox() throws IllegalStateException {
    if (!tongue.isActive()) {
      throw new IllegalStateException();
    }
    return tongue.getHitbox();
  }

  /**
   * Decreases the health of this Frog by 1.
   */
  public void loseHealth() {
    this.health = this.health - 1;
  }

  /**
   * Gets the current health of the Frog
   * 
   * @return the current health of this Frog
   */
  public int getHealth() {
    return this.health;
  }

  /**
   * Reports if the Frog needs to move on the screen.
   * 
   * @return true if the Frog is being dragged, false otherwise
   */
  public boolean shouldMove() {
    return isDragging;
  }

  /**
   * Determines if this frog is dead.
   * 
   * @return true if this Frog's health is 0 or lower, false otherwise
   */
  public boolean isDead() {
    if (this.health <= 0) {
      return true;
    }

    return false;
  }

  /**
   * Changes this Frog so it is now being dragged. This method should only be
   * called externally when the mouse is over this frog and has been clicked.
   */
  public void mousePressed() {
    isDragging = true;
  }

  /**
   * Changes this Frog so it is no longer being dragged. This method should only
   * be called externally when the mouse has been released.
   */
  public void mouseReleased() {
    isDragging = false;
  }

  /**
   * Determines if the mouse is over the Frog's image
   * 
   * @return true, if the mouse is inside the Frog's bounding box of the image,
   *         false otherwise
   */
  public boolean isMouseOver() {
    int xPosMouse = processing.mouseX;
    int yPosMouse = processing.mouseY;
    float xFrogPos = this.getX();
    float yFrogPos = this.getY();
    int picWidth = (this.image.width) / 2;
    int picHeight = (this.image.height) / 2;
    boolean insidePicX = false;
    boolean insidePicY = false;
    // checks to see if mouse x position is in line with frog
    if (xPosMouse < (xFrogPos + picWidth) && xPosMouse > (xFrogPos - picWidth)) {
      insidePicX = true;
    }
    // checks to see if mouse y position is in line with frog
    if (yPosMouse < (yFrogPos + picHeight) && yPosMouse > (yFrogPos - picHeight)) {
      insidePicY = true;
    }

    // checks if mouse is on frog
    if (insidePicX && insidePicY) {
      return true;
    } else {

      return false;
    }
  }

  /**
   * Starts an attack by resetting the tongue to it's default state and activating
   * the tongue.
   */
  public void startAttack() {
    tongue.reset();
    tongue.activate();
  }

  /**
   * Stops the attack by deactivating the tongue.
   */
  public void stopAttack() {
    tongue.deactivate();
  }

  /**
   * Reports if this Frog's tongue has hit the top of the screen.
   * 
   * @return true if the tongue has hit the top of the screen, false otherwise
   */
  public boolean tongueHitBoundary() {
    if (tongue.hitScreenBoundary()) {
      return true;
    }
    return false;
  }
}
