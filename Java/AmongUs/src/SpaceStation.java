//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Among Us Game Simulator
// Course:   CS 300 Fall 2023
//
// Author:   Daniel Afrasiabi
// Email:    dafrasiabi@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    NONE
// Partner Email:   NONE
// Partner Lecturer's Name: NONE
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

import java.io.File;
import processing.core.PImage;

/**
 * This class models an Among Us simulation in which there is one impostor
 * competing against all other characters.
 */
public class SpaceStation {
	private static PImage background;
	private static Amogus[] players;
	private static final int NUM_PLAYERS = 8;
	private static int impostorIndex;

	/**
	 * This method executes the other methods through utilization of the Utility
	 * class.
	 * 
	 * @param args is the base parameter for the main method
	 */
	public static void main(String args[]) {
		Utility.runApplication();
	}

	/**
	 * This method initiates key variables which are utilized throughout the rest of
	 * the class.
	 */
	public static void setup() {
		background = Utility.loadImage("images" + File.separator + "background.jpeg");

		players = new Amogus[NUM_PLAYERS];
		players[0] = new Amogus(Utility.randGen.nextInt(3) + 1);

		impostorIndex = Utility.randGen.nextInt(NUM_PLAYERS - 1) + 1;
		System.out.println("Impostor index: " + impostorIndex);
	}

	/**
	 * This method draws the background and characters.
	 */
	public static void draw() {
		Utility.image(background, 600, 500);

		for (int i = 0; i < NUM_PLAYERS; i++) {
			for (int j = 0; j < NUM_PLAYERS; j++) {
				if (overlap(players[i], players[j]) && i != j) {
					if (players[i].isImpostor()) {
						players[j].unalive();
					} else if (players[j].isImpostor()) {
						players[i].unalive();
					}
				}
			}
		}

		for (int i = 0; i < NUM_PLAYERS; i++) {
			if (players[i] != null) {
				players[i].draw();
			}
		}

	}

	/**
	 * This method is automatically called whenever a key is pressed. If the key is
	 * 'a' and the maximum amount of players hasn't been reached yet, then this
	 * method generates a random-colored character and assigns the character a
	 * random location to be drawn in the draw() method above.
	 */
	public static void keyPressed() {
		if ((Utility.key() == 'a') && players[NUM_PLAYERS - 1] == null) {
			int color = Utility.randGen.nextInt(3) + 1;
			int width = Utility.randGen.nextInt(1201);
			int height = Utility.randGen.nextInt(801);

			for (int i = 0; i < NUM_PLAYERS; ++i) {
				if (players[i] == null) {
					if (i == impostorIndex) {
						players[i] = new Amogus(color, width, height, true);
						break;
					} else {
						players[i] = new Amogus(color, width, height, false);
						break;
					}
				}
			}
		}
	}

	/**
	 * This method detects if the mouse is over one of the characters.
	 * 
	 * @param amogus is the character which the method detects if the mouse is over
	 *               for
	 * @return true if the mouse is over a character and false otherwise
	 */
	public static boolean isMouseOver(Amogus amogus) {
		boolean answer = false;

		float amogusX = amogus.getX();
		float amogusY = amogus.getY();

		int mouseX = Utility.mouseX();
		int mouseY = Utility.mouseY();

		int amogusWidth = amogus.image().width;
		int amogusHeight = amogus.image().height;

		if ((Math.abs(mouseX - amogusX) < (amogusWidth / 2.0))) {
			if ((Math.abs(mouseY - amogusY) < (amogusHeight / 2.0))) {
				answer = true;
			}
		}

		return answer;
	}

	/**
	 * This method detects which character the mouse is over and calls the start
	 * dragging method to initiate movement of the character.
	 */
	public static void mousePressed() {
		int lowestIndex = 7;
		for (int i = 0; i < NUM_PLAYERS; i++) {
			if ((players[i] != null) && (isMouseOver(players[i]) == true)) {
				if (i < lowestIndex) {
					lowestIndex = i;
				}
			}
		}
		if (players[lowestIndex] != null) {
			players[lowestIndex].startDragging();
		}
	}

	/**
	 * This method stops movement for all characters.
	 */
	public static void mouseReleased() {
		for (int i = 0; i < NUM_PLAYERS; i++) {
			if (players[i] != null) {
				players[i].stopDragging();
			}
		}
	}

	/**
	 * This method identifies whether two specified characters are overlapping on
	 * the screen.
	 * 
	 * @param civilian is a character which is being tested for overlap with the
	 *                 other character
	 * @param impostor is the other character which is being tested for overlap with
	 *                 the first character
	 * @return true if the two characters are overlapping and false otherwise
	 */
	public static boolean overlap(Amogus civilian, Amogus impostor) {
		boolean answer = false;

		if (civilian != null && impostor != null) {
			float civilianX = civilian.getX();
			float civilianY = civilian.getY();

			float impostorX = impostor.getX();
			float impostorY = impostor.getY();

			int width = civilian.image().width;
			int height = civilian.image().height;

			if (Math.abs(impostorX - civilianX) < (width / 2.0)) {
				if (Math.abs(impostorY - civilianY) < (height / 2.0)) {
					answer = true;
				}
			}
		}

		return answer;
	}

}
