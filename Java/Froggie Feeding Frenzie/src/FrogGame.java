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

import java.io.File;
import java.util.ArrayList;
import processing.core.PApplet;
import processing.core.PImage;
import java.util.Random;

/*
 * Runs and operates the game where a moveable frog character with a certain health level
 * attacks a variety of different bugs and accumulates score while trying to avoid running
 * into them and losing health.
 */
public class FrogGame extends PApplet {
  // array list of the gameActors in the game
  private ArrayList<GameActor> gameActors;
  // the player's current score
  private int score;
  // the image to use for the background
  private PImage backgroundImg;
  // keeps track if the game is over, is true if the game is over
  private boolean isGameOver;
  // random number generator
  private Random randGen;
  // how many bugs should be on the screen at all times
  private static final int BUG_COUNT = 5;

  public static void main(String[] args) {
    PApplet.main("FrogGame");
  }

  /*
   * Sets screen size to 800 by 600
   */
  @Override
  public void settings() {
    this.size(800, 600);
  }

  /*
   * Customizes window by creating title and managing other features of PApplet
   * class
   */
  @Override
  public void setup() {
    this.getSurface().setTitle("Froggie Feeding Frenzie"); // set title of window
    this.imageMode(PApplet.CENTER); // images when drawn will use x,y as their center
    this.rectMode(PApplet.CENTER); // rectangles when drawn will use x,y as their center
    this.focused = true; // window is "active" upon start up
    this.textAlign(PApplet.CENTER); // text written to screen will have center alignment
    this.textSize(30); // text is 30 pt

    // initialize randGen
    randGen = new Random();

    // loads in and saves the backgroundImg
    backgroundImg = this.loadImage("images/background.jpg");

    // initialize gameActors to an empty ArrayList
    gameActors = new ArrayList<>();

    // sets the processing variable for all classes where necessary
    Hitbox.setProcessing(this);
    GameActor.setProcessing(this);
    Tongue.setProcessing(this);

    // initializes game
    initGame();

  }

  /*
   * Draws background image and all characters to screen, initializes movement of
   * characters
   */
  @Override
  public void draw() {
    if (isGameOver) {
      this.text("GAME OVER", 400, 300);
    } else {
      // draws background
      this.image(backgroundImg, width / 2, height / 2);

      for (int i = 0; i < gameActors.size(); ++i) {
        // draws every GameActor in the ArrayList to the screen
        gameActors.get(i).draw();

        // moves all StrongBug characters
        if (gameActors.get(i) instanceof StrongBug) {
          ((StrongBug) gameActors.get(i)).move();
        }
        // moves all BouncingBug characters
        if (gameActors.get(i) instanceof BouncingBug) {
          ((BouncingBug) gameActors.get(i)).move();
        }

        // moves all CirclingBug characters
        if (gameActors.get(i) instanceof CirclingBug) {
          ((CirclingBug) gameActors.get(i)).move();
        }

        // moves all FrogCharacters
        if (gameActors.get(i) instanceof Frog) {
          ((Frog) gameActors.get(i)).move();
        }

      }

      // runs all game logic checks
      runGameLogicChecks();

      for (int i = 0; i < gameActors.size(); ++i) {
        if (gameActors.get(i) instanceof Frog) {
          Frog frog = (Frog) gameActors.get(i);
          this.text("Health: " + frog.getHealth(), 80, 40);
          this.text("Score: " + score, 240, 40);
        }

      }
    }
  }

  /*
   * Adds randomizes Bug character to gameActors arrayList
   */
  private void addNewBug() {
    // generates a random number in the range [0,4) to determine what bug will be
    // added
    int bugNumber = randGen.nextInt(4);

    // generate a random value in the range [0, windowWidth) for the bug's x
    // coordinate
    float x = randGen.nextFloat(800);

    // (generate a random value in the range [0, windowHeight - 150) for the bug's y
    // coordinate
    float y = randGen.nextFloat(600);

    // adds non-moving Bug to arrayList
    if (bugNumber == 0) {
      gameActors.add(new Bug(x, y, 25));
    }

    // adds Bouncing Bug to arrayList
    else if (bugNumber == 1) {
      gameActors.add(new BouncingBug(x, y, 2, 5));
    }

    // adds Circling Bug to arrayList
    else if (bugNumber == 2) {
      int[] color = new int[] { randGen.nextInt(256), randGen.nextInt(256), randGen.nextInt(256) };
      gameActors.add(new CirclingBug(x, y, 25, color));
    }

    // adds Strong Bug to arraylist
    else if (bugNumber == 3) {
      gameActors.add(new StrongBug(x, y, 3));
    }

  }

  /*
   * Checks to see if the mouse is currently pressed while over the frog
   */
  @Override
  public void mousePressed() {
    for (int i = 0; i < gameActors.size(); ++i) {
      if (gameActors.get(i) instanceof Frog) {
        Frog frog = (Frog) gameActors.get(i);
        if (frog.isMouseOver()) {
          frog.mousePressed();
        }
      }
    }

  }

  /*
   * Checks to see if mouse has been released from frog
   */
  @Override
  public void mouseReleased() {
    for (int i = 0; i < gameActors.size(); ++i) {
      if (gameActors.get(i) instanceof Frog) {
        ((Frog) gameActors.get(i)).mouseReleased();
      }
    }
  }

  /*
   * Creates keys for ' ' to prompt a frog attack and 'r' to reset the game
   */
  @Override
  public void keyPressed() {
    int index = -1;
    Frog frog;

    for (int i = 0; i < gameActors.size(); ++i) {
      if (gameActors.get(i) instanceof Frog) {
        index = i;
      }
    }

    if (index >= 0) {
      frog = (Frog) gameActors.get(index);
    } else {
      return;
    }

    // if the key is a space, have the frog starts attacking
    if (this.key == ' ') {
      frog.startAttack();
    }
    // if the key is a lowercase 'r', reset the game to its initial state
    if (this.key == 'r') {
      initGame();
    }
  }

  /*
   * Initializes presets for game and begins adding bugs to arrayList
   */
  public void initGame() {
    // score begins at 0
    score = 0;
    // the game is not over
    isGameOver = false;
    // arraylist is initialized and emptied
    gameActors = new ArrayList<GameActor>();
    // adds a frog character to arrayList
    gameActors.add(new Frog(400, 500, 100));
    // add new bugs (of random varieties) to the list UP TO the BUG_COUNT
    for (int i = 0; i < BUG_COUNT; ++i) {
      addNewBug();
    }
  }

  /*
   * Creates logic for game to run on such as being hit takes away health and
   * eliminating a character increases the frog's score
   */
  private void runGameLogicChecks() {
    try {
      Frog frog;
      int index = -1;
      for (int i = 0; i < gameActors.size(); ++i) {
        if (gameActors.get(i) instanceof Frog) {
          index = i;
        }
      }

      if (index >= 0) {
        // creates copy of Frog character to use for comparison
        frog = (Frog) gameActors.get(index);
      } else {
        return;
      }

      // if the Frog's tongue hits the edge of the screen, then it stops attacking
      if (frog.tongueHitBoundary()) {
        frog.stopAttack();
      }

      // check if the frog hits any of the bugs
      for (int i = 0; i < gameActors.size(); ++i) {
        if (gameActors.get(i) instanceof Bug) {
          if (frog.isHitBy((Bug) gameActors.get(i))) {
            // if it hit any of the bugs it takes damage and loses health

            frog.loseHealth();
            // if the frog is dead then the game is over
            if (frog.isDead()) {
              isGameOver = true;
            }
          }
        }
      }

      for (int i = 0; i < gameActors.size(); ++i) {

        // check every bug to see if it has been hit by the Frog
        if (gameActors.get(i) instanceof Bug) {
          if (gameActors.get(i) instanceof StrongBug) {
            // if a StrongBug is hit
            if (gameActors.get(i).getHitbox().doesCollide(frog.getTongueHitbox())) {
              // stop the frog's attack
              frog.stopAttack();
              // the StrongBug takes damage and loses health
              ((StrongBug) gameActors.get(i)).loseHealth();
              // if the StrongBug is dead
              if (((StrongBug) gameActors.get(i)).isDead()) {
                // update score
                score += ((Bug) gameActors.get(i)).getPoints();
                // remove character
                gameActors.remove(i);
                // add new character
                addNewBug();
              }
            }
          }

          else {
            // if a non-StrongBug is hit do the following
            if (gameActors.get(i).getHitbox().doesCollide(frog.getTongueHitbox())) {
              // stop the frog's attack
              frog.stopAttack();
              // update score
              score += ((Bug) gameActors.get(i)).getPoints();
              // remove it from the game
              gameActors.remove(i);
              // add a new bug (of a random variety) to the game
              addNewBug();
            }
          }

        }
      }

    } catch (IllegalStateException e) {
    }
  }
}