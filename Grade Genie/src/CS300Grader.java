import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

/**
 * This is an interactive utility class which maintains a set of various assignment groups
 * which correspond to the CS 300 grade policies for Fall 2023. Most of the methods in this
 * class are provided in full; you may modify them as you see fit. The last method before
 * the main method is incomplete, and you may complete it however you wish if you want to
 * use this assignment to help project your grade in CS 300.
 * 
 * This class should NOT be submitted with your code.
 */
public class CS300Grader {

  private static AssignmentGroup exams;
  private static DropAssignmentGroup programs;
  private static DropAssignmentGroup[] weeklyQuizzes;
  private static ScalingAssignmentGroup zybooks;
  private static ScalingAssignmentGroup participation;
  
  /**
   * Displays the menu for the interactive loop
   */
  private static void printMenu() {
    System.out.println("1. Generate random CS 300 grade report");
    System.out.println("2. Get CS 300 grade projection");
    System.out.println("3. Exit");
    System.out.print("> ");
  }
  
  /**
   * Sets up the CS 300 assignment groups according to the number of units covered so far
   * in the semester.
   * 
   * @param numUnits the number of units, a value from 1-5 (not validated, use with care)
   */
  private static void initializeAssignmentGroups(int numUnits) {
    exams = new AssignmentGroup(.35);
    programs = new DropAssignmentGroup(.45, 1);
    weeklyQuizzes = new DropAssignmentGroup[numUnits];
    for (int i=0; i<weeklyQuizzes.length; i++) {
      weeklyQuizzes[i] = new DropAssignmentGroup(.02, 1);
    }
    zybooks = new ScalingAssignmentGroup(.05, .8);
    participation = new ScalingAssignmentGroup(.05, .8);
  }
  
  /**
   * Calculates the total percent earned by the current assignment group setup 
   * 
   * @return the percent of total points earned with group weights accounted for,
   * as a double between 0 and 1
   */
  private static double getTotalPercent() {
    double total = 0;
    total += exams.getPoints()/exams.getTotalPossible()*exams.PERCENT_OF_TOTAL;
    total += programs.getPoints()/programs.getTotalPossible()*programs.PERCENT_OF_TOTAL;
    total += zybooks.getPoints()/zybooks.getTotalPossible()*zybooks.PERCENT_OF_TOTAL;
    total += participation.getPoints()/participation.getTotalPossible()*participation.PERCENT_OF_TOTAL;
    for (int i=0; i<weeklyQuizzes.length; i++) {
      total += weeklyQuizzes[i].getPoints()/weeklyQuizzes[i].getTotalPossible()*weeklyQuizzes[i].PERCENT_OF_TOTAL;
    }
    double scale = Math.pow(10, 3);
    double points = Math.round(total*scale)/scale;
    return points;
  }
  
  /**
   * Displays the total percent and letter grade earned by the current assignment group setup
   */
  private static void printTotalPercent() {
    double totalPct = getTotalPercent();
    System.out.print("Total percent: "+totalPct*100+"%, which is a(n) ");
    if (totalPct >= .93) System.out.println("A");
    else if (totalPct >= .88) System.out.println("AB");
    else if (totalPct >= .8) System.out.println("B");
    else if (totalPct >= .75) System.out.println("BC");
    else if (totalPct >= .7) System.out.println("C");
    else if (totalPct >= .6) System.out.println("D");
    else System.out.println("F");
    System.out.println();
  }
  
  /**
   * Displays the grades stored in the current assignment group setup
   */
  private static void printCS300Grade() {
    System.out.println("Unit Quizzes: "+exams);
    System.out.println("Programs: "+programs);
    System.out.println("Weekly Quizzes:");
    for (int i=0; i<weeklyQuizzes.length; i++) {
      System.out.println("  Unit "+(i+1)+": "+weeklyQuizzes[i]);
    }
    System.out.println("Zybooks: "+zybooks);
    System.out.println("Participation: "+participation);
  }
  
  /**
   * Generates a random student score report for CS 300
   */
  private static void generateRandomReport() {
    // initialize assignment groups for the whole semester
    initializeAssignmentGroups(5);
    
    // populate the assignment groups with scores
    exams.addAssignments(SimpleAssignment.makeRandomAssignments(5, 100));
    programs.addAssignments(SimpleAssignment.makeRandomAssignments(10, 50));
    zybooks.addAssignments(SimpleAssignment.makeRandomAssignments(15,100));
    participation.addAssignments(SimpleAssignment.makeRandomAssignments(15,100));
    for (DropAssignmentGroup d : weeklyQuizzes) d.addAssignments(SimpleAssignment.makeRandomAssignments(2,15));

    // display the results
    printCS300Grade();
    printTotalPercent(); 
  }
  
  /**
   * TODO: If you'd like to make up a projection for your own grade, you can complete this
   * method! You can hard-code, you can practice user input with the Scanner object, however
   * you want to complete it. This file should NOT be submitted and will therefore NOT be graded.
   * 
   * @param in a Scanner object hooked up to keyboard input
   */
  private static void getGradeProjection(Scanner in) {
    // initialize assignment groups for as far as we have come in the semester
    initializeAssignmentGroups(2); // it's unit 2 as of when P03 is happening
    
    // TODO: populate the assignment groups with scores (however you want to)
    
    // display the results
    printCS300Grade();
    printTotalPercent();
  }
  
  /**
   * This method runs the interactive grade projection menu.
   * @param args unused
   */
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int input = -1;
    while (input != 3) {
      printMenu();
      try {
        input = in.nextInt();
      } catch (InputMismatchException e) {
        System.out.println("I don't understand that input");
        in.nextLine();
        input = -1;
      }
      
      switch(input) {
        case 1:
          generateRandomReport();
          break;
        case 2:
          getGradeProjection(in);
          break;
        default:
          break;
      }
    }
    System.out.println("Goodbye!");
    in.close();
  }

}
