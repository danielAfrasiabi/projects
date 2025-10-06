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

import java.lang.Math;

/*
 * Creates a new CirclingBug object using the provided parameters.
 */
public class CirclingBug extends Bug implements Moveable {
  // keeps track of how long the bug has been moving
  private double ticks = 0.0;

  // the radius of the circle path this bug moves on
  private double radius;

  // the x,y-coordinates for the center of the circle path
  private float[] circleCenter;

  // color used to tint the image when being drawn [Red,Green,Blue]
  private int[] tintColor = new int[3];

  // constant, number of points ALL CirclingBugs are worth 200
  private static final int POINTS = 200;

  /*
   * Creates a new CirclingBug object using the provided parameters. By default
   * the number of ticks for a new CirclingBug should be 0.0. The x,y-coordinates
   * for the initial position of the Bug must be calculated by using the equations
   * given in the write-up for ticks = 0.0.
   * 
   * @param circleX the x-coordinate for the center of the circle path
   * 
   * @param circleY the y-coordinate for the center of the circle path
   * 
   * @param radius the radius of this CirclingBug's circle path
   * 
   * @param tintColor an array containing the Red,Green, and Blue values for the
   * tint color
   * 
   */
  public CirclingBug(float circleX, float circleY, double radius, int[] tintColor) {
    super(circleX, circleY, POINTS);

    circleCenter = new float[] { circleX, circleY };

    this.radius = radius;

    this.tintColor = tintColor;

  }

  /*
   * Draws the image to the screen, tinting it with the tintColor before drawing
   * it. After the image is drawn to the screen the tinting effect will need to
   * done undone by tinting it again with white. (255, 255, 255)
   * 
   */
  @Override
  public void draw() {
    processing.tint(tintColor[0], tintColor[1], tintColor[2]);
    processing.image(this.image, getX(), getY());
    processing.tint(255, 255, 255);

  }

  /*
   * Moves this CirclingBug to the next position on its circular path. (See
   * write-up for more details on how to calculate this path.) The Hitbox should
   * move with the CirclingBug. The bug only changes its xy-coordinates if it
   * should move. Ticks will advance by 0.05 whenever this method is called.
   * 
   */
  @Override
  public void move() {
    if (!shouldMove()) {
      return;
    }

    ticks += 0.05;

    // assigning new x and y values due to formula for circular motion
    float x = circleCenter[0] + (float) (radius * Math.cos(ticks));
    float y = circleCenter[1] + (float) (radius * Math.sin(ticks));

    setX(x);
    setY(y);

    // move hitbox to new position of bug
    getHitbox().setPosition(getX(), getY());

  }

  /*
   * Reports if the CirclingBug needs to move.
   * 
   * @return true, this bug ALWAYS moves
   */
  @Override
  public boolean shouldMove() {
    return true;
  }

}
