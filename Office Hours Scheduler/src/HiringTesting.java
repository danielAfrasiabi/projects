//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: TA Hiring Program
// Course: CS 300 Fall 2023
//
// Author: Kai Tsimpidis
// Email: tsimpidis@wisc.edu
// Lecturer: Hobbes LeGault
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: Daniel Afrasaibi
// Partner Email: dafrasiabi@wisc.edu
// Partner Lecturer's Name: Mouna Kacem
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// __X_ Write-up states that pair programming is allowed for this assignment.
// __X_ We have both read and understand the course Pair Programming Policy.
// __X_ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: Used no outside help from any persons
// Online Sources: Used no online sources for help
//
///////////////////////////////////////////////////////////////////////////////

import java.util.ArrayList;
import java.util.Random;

/**
 * Tester class for the implementation of different hiring types Hiring class
 */
public class HiringTesting {
  /**
   * Random instance for tests that randomly generate inputs. We set the random
   * seed so that we still get deterministic results that we can reproduce.
   */
  public static Random randGen = new Random(205);

  /**
   * Main Method that runs all the testers
   * 
   * @param args
   */
  public static void main(String[] args) {
    System.out.println(greedyHiringBaseTest());
    System.out.println(greedyHiringRecursiveTest());
    System.out.println(optimalHiringBaseTest());
    System.out.println(optimalHiringRecursiveTest());
    System.out.println(optimalHiringFuzzTest());
    System.out.println(minCoverageHiringBaseTest());
    System.out.println(minCoverageHiringRecursiveTest());
    System.out.println(minCoverageHiringFuzzTest());

  }

  /**
   * Ensures base case works in greedyHiring
   * 
   * @return true if all tests pass
   */
  public static boolean greedyHiringBaseTest() {
    boolean result = false;

    CandidateList candidatesTest = new CandidateList();
    CandidateList hiredTest = new CandidateList();
    int hiresLeftTest = 0;

    CandidateList greedyTestResult = Hiring.greedyHiring(candidatesTest, hiredTest, hiresLeftTest);

    if (HiringTestingUtilities.compareCandidateLists(hiredTest, greedyTestResult)) {
      result = true;
    }

    return result;
  }

  /**
   * Tests the recursive implementation of greedyHiring
   * 
   * @return true if tests pass false if not
   */
  public static boolean greedyHiringRecursiveTest() {
    boolean result1 = false;

    boolean[][] testAvailabilities = new boolean[][] { { true, false, true, false, true },
        { true, false, false, false, true }, { false, true, true, false, false },
        { true, false, false, false, false } };

    CandidateList candidatesTest = HiringTestingUtilities.makeCandidateList(testAvailabilities);

    CandidateList hiredTest = new CandidateList();

    int hiresLeftTest = 2;

    CandidateList greedyTestResult = Hiring.greedyHiring(candidatesTest, hiredTest, hiresLeftTest);

    CandidateList expectedHired = new CandidateList();
    expectedHired.add(candidatesTest.get(0));
    expectedHired.add(candidatesTest.get(2));

    boolean[][] testAvailabilities1 = new boolean[][] { { false, false, false, false, false },
        { false, false, true, false, true }, { false, false, true, true, true },
        { true, true, false, false, false } };

    CandidateList candidatesTest1 = HiringTestingUtilities.makeCandidateList(testAvailabilities1);

    CandidateList hiredTest1 = new CandidateList();

    CandidateList greedyTestResult1 = Hiring.greedyHiring(candidatesTest1, hiredTest1,
        hiresLeftTest);

    CandidateList expectedHired1 = new CandidateList();
    expectedHired1.add(candidatesTest1.get(2));
    expectedHired1.add(candidatesTest1.get(3));

    if (HiringTestingUtilities.compareCandidateLists(greedyTestResult, expectedHired)
        && HiringTestingUtilities.compareCandidateLists(greedyTestResult1, expectedHired1)) {
      result1 = true;
    }

    return result1;
  }

  /**
   * Ensures base case works in optimalHiring
   * 
   * @return true if tests pass false if not
   */
  public static boolean optimalHiringBaseTest() {

    boolean result2 = false;

    CandidateList candidatesTest = new CandidateList();
    CandidateList hiredTest = new CandidateList();
    int hiresLeftTest = 0;

    CandidateList greedyTestResult = Hiring.optimalHiring(candidatesTest, hiredTest,
        hiresLeftTest);

    if (HiringTestingUtilities.compareCandidateLists(hiredTest, greedyTestResult)) {
      result2 = true;
    }

    return result2;
  }

  /**
   * Tests the recursive implementation of optimalHiring
   * 
   * @return true if tests pass false if not
   */
  public static boolean optimalHiringRecursiveTest() {

    boolean optimalRecursiveTest = false;

    boolean[][] testAvailabilities = new boolean[][] { { true, false, true, false, true },
        { false, true, false, true, false }, { false, false, true, false, true },
        { true, false, false, false, false } };

    CandidateList candidatesTest = HiringTestingUtilities.makeCandidateList(testAvailabilities);

    CandidateList hiredTest = new CandidateList();

    int hiresLeftTest = 2;

    CandidateList actualHires = Hiring.optimalHiring(candidatesTest, hiredTest, hiresLeftTest);

    ArrayList<CandidateList> expectedHires = HiringTestingUtilities
        .allOptimalSolutions(candidatesTest, hiresLeftTest);

    boolean[][] testAvailabilities1 = new boolean[][] { { false, false, false, false, false },
        { false, false, true, false, true }, { false, false, true, true, true },
        { true, true, false, false, false } };

    CandidateList candidatesTest1 = HiringTestingUtilities.makeCandidateList(testAvailabilities1);
    CandidateList hiredTest1 = new CandidateList();

    CandidateList actualHires1 = Hiring.optimalHiring(candidatesTest1, hiredTest1, hiresLeftTest);
    ArrayList<CandidateList> expectedHires1 = HiringTestingUtilities
        .allOptimalSolutions(candidatesTest1, hiresLeftTest);

    if (expectedHires.contains(actualHires) && expectedHires1.contains(actualHires1)) {
      optimalRecursiveTest = true;
    } else {
      optimalRecursiveTest = false;
    }

    return optimalRecursiveTest;
  }

  /**
   * Generates a large number of random inputs for your program and checking that
   * the program behaves as expected. Using many random inputs allows you to
   * exercise a bunch of the different code paths in your implementation
   * 
   * @return true if tests pass false if not
   */
  public static boolean optimalHiringFuzzTest() {
    boolean optimalFuzzTest = true;

    for (int i = 0; i < 100; i++) {

      int numHours = randGen.nextInt(5) + 1;
      int numCandidates = randGen.nextInt(10) + 1;
      CandidateList candidates = HiringTestingUtilities.generateRandomInput(numHours,
          numCandidates);
      int numHires = randGen.nextInt(numCandidates) + 1;
      CandidateList hiredFuzz = new CandidateList();

      CandidateList actualHires = Hiring.optimalHiring(candidates, hiredFuzz, numHires);

      ArrayList<CandidateList> expectedHires = HiringTestingUtilities
          .allOptimalSolutions(candidates, numHires);

      if (!expectedHires.contains(actualHires)) {
        optimalFuzzTest = false;
      }
    }
    return optimalFuzzTest;
  }

  /**
   * Ensures base case works in optimalHiring
   * 
   * @return true if tests pass false if not
   */
  public static boolean minCoverageHiringBaseTest() {

    boolean minBaseTest = false;

    CandidateList candidatesTest = new CandidateList();
    CandidateList hiredTest = new CandidateList();
    int minHours = 0;

    CandidateList minTestResult = Hiring.minCoverageHiring(candidatesTest, hiredTest, minHours);

    if (HiringTestingUtilities.compareCandidateLists(hiredTest, minTestResult)) {
      minBaseTest = true;
    }

    return minBaseTest;
  }

  /**
   * Tests the recursive implementation of minCoverageHiring
   * 
   * @return true if tests pass false if not
   */
  public static boolean minCoverageHiringRecursiveTest() {

    boolean minCoverageRecursiveTest = false;

    boolean[][] testAvailabilities = new boolean[][] { { true, false, true, false, true },
        { false, true, false, true, false }, { false, false, true, false, true },
        { true, false, false, false, false } };
    int[] payRates = new int[] { 2, 4, 3, 5 };

    CandidateList candidatesTest = HiringTestingUtilities.makeCandidateList(testAvailabilities,
        payRates);

    CandidateList hiredTest = new CandidateList();

    int minHours = 4;

    CandidateList actualHires = Hiring.minCoverageHiring(candidatesTest, hiredTest, minHours);

    ArrayList<CandidateList> expectedHires = HiringTestingUtilities
        .allMinCoverageSolutions(candidatesTest, minHours);

    boolean[][] testAvailabilities1 = new boolean[][] { { false, false, false, false, false },
        { false, false, true, false, true }, { false, false, true, true, true },
        { true, true, false, false, false } };
    int[] payRates1 = new int[] { 1, 3, 3, 4 };

    CandidateList candidatesTest1 = HiringTestingUtilities.makeCandidateList(testAvailabilities1,
        payRates1);
    CandidateList hiredTest1 = new CandidateList();

    CandidateList actualHires1 = Hiring.minCoverageHiring(candidatesTest1, hiredTest1, minHours);
    ArrayList<CandidateList> expectedHires1 = HiringTestingUtilities
        .allMinCoverageSolutions(candidatesTest1, minHours);

    if (expectedHires.contains(actualHires) && expectedHires1.contains(actualHires1)) {
      minCoverageRecursiveTest = true;
    } else {
      minCoverageRecursiveTest = false;
    }

    return minCoverageRecursiveTest;
  }

  /**
   * Generates a large number of random inputs for your program and checking that
   * the program behaves as expected. Using many random inputs allows you to
   * exercise a bunch of the different code paths in your implementation
   * 
   * @return true if tests pass false if not
   */
  public static boolean minCoverageHiringFuzzTest() {
    boolean minFuzzTest = true;

    for (int i = 0; i < 100; i++) {

      int numHours = randGen.nextInt(5) + 1;
      int numCandidates = randGen.nextInt(10) + 1;
      CandidateList candidates = HiringTestingUtilities.generateRandomInput(numHours,
          numCandidates, 5);
      int minHours = randGen.nextInt(numHours) + 1;
      CandidateList hiredFuzz = new CandidateList();

      CandidateList actualHires = Hiring.minCoverageHiring(candidates, hiredFuzz, minHours);

      ArrayList<CandidateList> expectedHires = HiringTestingUtilities
          .allMinCoverageSolutions(candidates, minHours);

      if (!expectedHires.contains(actualHires)) {
        minFuzzTest = false;
      }
    }
    return minFuzzTest;
  }
}
