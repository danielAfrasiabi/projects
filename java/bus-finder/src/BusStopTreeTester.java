//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Bus Stop Tree
// Course: CS 300 Fall 2023
//
// Author: Daniel Afrasiabi
// Email: dafrasiabi@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name: NONE
// Partner Email: NONE
// Partner Lecturer's Name: NONE
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
// ___ Write-up states that pair programming is allowed for this assignment.
// ___ We have both read and understand the course Pair Programming Policy.
// ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons: NONE
// Online Sources: NONE
//
///////////////////////////////////////////////////////////////////////////////
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class BusStopTreeTester {

  /**
   * Tests that compareTo returns the correct value when comparing a bus with a different arrival.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToDifferentArrivalTime() {
    // Create an array of stop IDs and stop times for a dummy route
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes = {"05:00", "07:00", "09:00", "11:00", "13:00"};

    // Create a dummy bus route with the given stop IDs and stop times
    BusRoute testRoute =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);

    // Create three buses with different arrival times at different stops
    Bus testBus1 = new Bus(BusStop.getStop(1), testRoute);
    Bus testBus2 = new Bus(BusStop.getStop(2), testRoute);
    Bus testBus3 = new Bus(BusStop.getStop(3), testRoute);

    // Test if the compareTo method correctly identifies the order of buses based on arrival times
    if (testBus1.compareTo(testBus2) != -1) {
      return false; // Bus 1 should arrive earlier than Bus 2
    }

    if (testBus3.compareTo(testBus1) != 1) {
      return false; // Bus 3 should arrive later than Bus 1
    }

    // If the test passes, return true
    return true;
  }


  /**
   * For two buses with the same arrival time but different routes, test that compareTo returns the
   * correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeDifferentRoute() {
    // Create an array of stop IDs and stop times for dummy routes
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes = {"05:00", "07:00", "09:00", "11:00", "13:00"};

    // Create three dummy bus routes with different route IDs
    BusRoute testRoute1 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute testRoute2 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute testRoute3 =
        BusRoute.dummyRoute("C4", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);

    // Create three buses with the same arrival time but different routes
    Bus testBus1 = new Bus(BusStop.getStop(1), testRoute1);
    Bus testBus2 = new Bus(BusStop.getStop(1), testRoute2);
    Bus testBus3 = new Bus(BusStop.getStop(1), testRoute3);

    // Test if the compareTo method correctly identifies the order of buses based on routes
    if (testBus1.compareTo(testBus3) != 1) {
      return false; // Bus 1 should have a higher route ID than Bus 3
    }

    if (testBus2.compareTo(testBus1) != 1) {
      return false; // Bus 2 should have a higher route ID than Bus 1
    }

    if (testBus3.compareTo(testBus2) != -1) {
      return false; // Bus 3 should have a lower route ID than Bus 2
    }

    // If the test passes, return true
    return true;
  }


  /**
   * For two buses with the same arrival time and route name, but different directions, test that
   * compareTo returns the correct value.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testBusCompareToSameArrivalTimeSameRouteDifferentDirection() {
    // Create an array of stop IDs and stop times for a dummy route
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes = {"05:00", "07:00", "09:00", "11:00", "13:00"};

    // Create two dummy bus routes with the same route ID but different directions
    BusRoute testRoute1 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute testRoute2 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);

    // Create two buses with the same arrival time and route but different directions
    Bus testBus1 = new Bus(BusStop.getStop(1), testRoute1);
    Bus testBus2 = new Bus(BusStop.getStop(1), testRoute2);

    // Test if the compareTo method correctly identifies the order of buses based on directions
    if (testBus1.compareTo(testBus2) != 1) {
      return false; // Bus 1 should have a higher direction than Bus 2
    }

    // If the test passes, return true
    return true;
  }


  /**
   * Tests that compareTo returns the correct value (0) when comparing a bus with the same arrival
   * time, route name, and direction.
   * 
   * @return true if the test passes, false otherwise.
   */
  private static boolean testBusCompareToSameBus() {
    int[] stopIds1 = {1, 2, 3, 4, 5};
    String[] stopTimes1 = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    // routes are different objects, but otherwise identical
    BusRoute route1 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    BusRoute route2 =
        BusRoute.dummyRoute("ROUTE 1", BusRoute.BusDirection.OUTGOING, stopIds1, stopTimes1);
    Bus bus1 = new Bus(BusStop.getStop(2), route1);
    Bus bus2 = new Bus(BusStop.getStop(2), route2);

    // compare bus1 to bus2 and vice versa
    boolean correctComparison1 = bus1.compareTo(bus2) == 0; // should return 0
    boolean correctComparison2 = bus2.compareTo(bus1) == 0; // should return 0

    // test passes if both comparisons return 0
    return correctComparison1 && correctComparison2;
  }

  /**
   * Tests that isValidBST returns true for an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTEmpty() {
    // Check if an empty tree is considered a valid BST
    if (!BusStopTree.isValidBST(null)) {
      return false;
    }
    // If the tree is empty, the test passes
    return true;
  }



  /**
   * Tests that isValidBST returns false for an invalid BST.
   * 
   * Should use a tree with depth > 2. Make sure to include a case where the left subtree contains a
   * node that is greater than the right subtree. (See the example in the spec for more details.)
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTInvalid() {
    // Create dummy bus routes with the same stop IDs and stop times
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes = {"05:00", "07:00", "09:00", "11:00", "13:00"};

    // Create five buses with different routes
    BusRoute testRoute1 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute2 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute testRoute3 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute testRoute4 =
        BusRoute.dummyRoute("C2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute testRoute5 =
        BusRoute.dummyRoute("L4", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);

    // Create nodes for each bus
    Bus testBus1 = new Bus(BusStop.getStop(2), testRoute1);
    Bus testBus2 = new Bus(BusStop.getStop(1), testRoute2);
    Bus testBus3 = new Bus(BusStop.getStop(1), testRoute3);
    Bus testBus4 = new Bus(BusStop.getStop(3), testRoute4);
    Bus testBus5 = new Bus(BusStop.getStop(2), testRoute5);

    Node<Bus> testNode1 = new Node<Bus>(testBus1);
    Node<Bus> testNode2 = new Node<Bus>(testBus2);
    Node<Bus> testNode3 = new Node<Bus>(testBus3);
    Node<Bus> testNode4 = new Node<Bus>(testBus4);
    Node<Bus> testNode5 = new Node<Bus>(testBus5);

    // Build an invalid binary search tree
    testNode1.setLeft(testNode2);
    testNode1.setRight(testNode3);
    testNode3.setRight(testNode4);
    testNode4.setRight(testNode5);

    // Check if the invalid tree is detected as not a valid BST
    if (BusStopTree.isValidBST(testNode1)) {
      return false;
    }
    // If the tree is invalid, the test passes
    return true;
  }


  /**
   * Tests that isValidBST returns true for a valid BST.
   * 
   * Should use a tree with depth > 2.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testIsValidBSTValid() {
    // Create dummy bus routes with the same stop IDs and stop times
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes = {"05:00", "07:00", "09:00", "11:00", "13:00"};

    // Create five buses with the same route
    BusRoute testRoute1 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute2 =
        BusRoute.dummyRoute("D1", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute testRoute3 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute testRoute4 =
        BusRoute.dummyRoute("C2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute testRoute5 =
        BusRoute.dummyRoute("L4", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);

    // Create nodes for each bus
    Bus testBus1 = new Bus(BusStop.getStop(1), testRoute1);
    Bus testBus2 = new Bus(BusStop.getStop(1), testRoute2);
    Bus testBus3 = new Bus(BusStop.getStop(1), testRoute3);
    Bus testBus4 = new Bus(BusStop.getStop(3), testRoute4);
    Bus testBus5 = new Bus(BusStop.getStop(4), testRoute5);

    Node<Bus> testNode1 = new Node<Bus>(testBus1);
    Node<Bus> testNode2 = new Node<Bus>(testBus2);
    Node<Bus> testNode3 = new Node<Bus>(testBus3);
    Node<Bus> testNode4 = new Node<Bus>(testBus4);
    Node<Bus> testNode5 = new Node<Bus>(testBus5);

    // Build a valid binary search tree
    testNode1.setLeft(testNode2);
    testNode1.setRight(testNode3);
    testNode3.setRight(testNode4);
    testNode4.setRight(testNode5);

    // Check if the valid tree is detected as a valid BST
    if (!BusStopTree.isValidBST(testNode1)) {
      return false;
    }
    // If the tree is valid, the test passes
    return true;
  }


  /**
   * Tests that addBus correctly adds a bus to an empty BST.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusEmpty() {
    // Create an empty BusStopTree with a user-defined stop ID
    BusStopTree testBST = new BusStopTree(1);

    // Create dummy bus route and bus
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    BusRoute testRoute1 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    Bus testBus = new Bus(BusStop.getStop(1), testRoute1);

    // Add the bus to the empty tree
    testBST.addBus(testBus);

    // Check if the first bus in the tree is the added bus
    if (testBST.getFirstBus() != testBus) {
      return false;
    }

    // If the test passes, return true
    return true;
  }

  /**
   * Tests that addBus correctly adds a bus to a non-empty BST.
   * 
   * Each time you add a bus, make sure that 1) addBus() returns true, 2) the BST is still valid, 3)
   * the BST size has been incremented.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBus() {
    // Create an empty BusStopTree with a user-defined stop ID
    BusStopTree testBST = new BusStopTree(1);

    // Create multiple dummy bus routes with the same stop IDs and stop times
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    BusRoute testRoute1 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute2 =
        BusRoute.dummyRoute("D3", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute3 =
        BusRoute.dummyRoute("C5", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute4 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute testRoute5 =
        BusRoute.dummyRoute("B4", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute6 =
        BusRoute.dummyRoute("A7", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);

    // Create multiple buses with the same stop and different routes
    Bus testBus1 = new Bus(BusStop.getStop(1), testRoute1);
    Bus testBus2 = new Bus(BusStop.getStop(1), testRoute2);
    Bus testBus3 = new Bus(BusStop.getStop(1), testRoute3);
    Bus testBus4 = new Bus(BusStop.getStop(1), testRoute4);
    Bus testBus5 = new Bus(BusStop.getStop(1), testRoute5);
    Bus testBus6 = new Bus(BusStop.getStop(1), testRoute6);

    // Add buses to the tree and check the validity and size of the tree after each addition
    boolean result1 = testBST.addBus(testBus1);
    Node<Bus> root = testBST.getRoot();

    if (!result1 || !BusStopTree.isValidBST(null) || testBST.size() != 1) {
      System.out.println("RESULT: 1");
      return false;
    }

    boolean result2 = testBST.addBus(testBus2);

    if (!result2 || !BusStopTree.isValidBST(root) || testBST.size() != 2) {
      System.out.println("RESULT: 2");
      return false;
    }

    boolean result3 = testBST.addBus(testBus3);

    if (!result3 || !BusStopTree.isValidBST(root) || testBST.size() != 3) {
      System.out.println("RESULT: 3");
      return false;
    }

    boolean result4 = testBST.addBus(testBus4);

    if (!result4 || !BusStopTree.isValidBST(root) || testBST.size() != 4) {
      System.out.println("RESULT: 4");
      return false;
    }

    boolean result5 = testBST.addBus(testBus5);

    if (!result5 || !BusStopTree.isValidBST(root) || testBST.size() != 5) {
      System.out.println("RESULT: 5");
      return false;
    }

    boolean result6 = testBST.addBus(testBus6);

    if (!result6 || !BusStopTree.isValidBST(root) || testBST.size() != 6) {
      System.out.println("RESULT: 6");
      return false;
    }

    // If the test passes, return true
    return true;
  }

  /**
   * Tests that addBus returns false when adding a duplicate bus. The BST should not be modified
   * (same size).
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testAddBusDuplicate() {
    // Create an empty BusStopTree with a user-defined stop ID
    BusStopTree testBST = new BusStopTree(1);

    // Create dummy bus routes and buses
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    BusRoute testRoute1 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute2 =
        BusRoute.dummyRoute("D3", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute3 =
        BusRoute.dummyRoute("C5", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute4 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute testRoute5 =
        BusRoute.dummyRoute("B4", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);

    Bus testBus1 = new Bus(BusStop.getStop(1), testRoute1);
    Bus testBus2 = new Bus(BusStop.getStop(1), testRoute2);
    Bus testBus3 = new Bus(BusStop.getStop(1), testRoute3);
    Bus testBus4 = new Bus(BusStop.getStop(1), testRoute4);
    Bus testBus5 = new Bus(BusStop.getStop(1), testRoute5);
    Bus testBus6 = new Bus(BusStop.getStop(1), testRoute5); // Duplicate of testBus5

    // Add buses to the tree
    testBST.addBus(testBus1);
    testBST.addBus(testBus2);
    testBST.addBus(testBus3);
    testBST.addBus(testBus4);
    testBST.addBus(testBus5);

    // Attempt to add a duplicate bus, and check that the size remains the same
    if (testBST.addBus(testBus6) || testBST.size() != 5) {
      return false;
    }

    // If the test passes, return true
    return true;
  }


  /**
   * Tests that contains returns true when the BST contains the Bus, and false otherwise.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testContains() {
    // Create an empty BusStopTree with a user-defined stop ID
    BusStopTree testBST = new BusStopTree(1);

    // Create dummy bus routes and buses
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes = {"05:00", "07:00", "09:00", "11:00", "13:00"};
    BusRoute testRoute1 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute2 =
        BusRoute.dummyRoute("D3", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute3 =
        BusRoute.dummyRoute("C5", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute4 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute testRoute5 =
        BusRoute.dummyRoute("B4", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute6 =
        BusRoute.dummyRoute("A7", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);

    Bus testBus1 = new Bus(BusStop.getStop(1), testRoute1);
    Bus testBus2 = new Bus(BusStop.getStop(1), testRoute2);
    Bus testBus3 = new Bus(BusStop.getStop(1), testRoute3);
    Bus testBus4 = new Bus(BusStop.getStop(1), testRoute4);
    Bus testBus5 = new Bus(BusStop.getStop(1), testRoute5);
    Bus testBus6 = new Bus(BusStop.getStop(1), testRoute6);

    // Add buses to the tree
    testBST.addBus(testBus1);
    testBST.addBus(testBus2);
    testBST.addBus(testBus3);
    testBST.addBus(testBus4);
    testBST.addBus(testBus5);

    // Check if the tree contains a specific bus and doesn't contain a duplicate
    if (!testBST.contains(testBus5) || testBST.contains(testBus6)) {
      return false;
    }

    // If the test passes, return true
    return true;
  }



  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is the node passed in as the root node parameter.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRoot() {
    // Create an empty BusStopTree with a user-defined stop ID
    BusStopTree testBST = new BusStopTree(1);

    // Dummy data for bus routes and stop times
    int[] stopIds = {1, 2, 3, 4, 5};

    String[] stopTimes1 = {"09:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"06:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"03:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"12:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"07:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes6 = {"17:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes7 = {"20:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes8 = {"01:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes9 = {"14:00", "07:00", "09:00", "11:00", "13:00"};



    // Create dummy bus routes
    BusRoute testRoute1 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    BusRoute testRoute2 =
        BusRoute.dummyRoute("D3", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    BusRoute testRoute3 =
        BusRoute.dummyRoute("C5", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3);
    BusRoute testRoute4 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes4);
    BusRoute testRoute5 =
        BusRoute.dummyRoute("B4", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes5);
    BusRoute testRoute6 =
        BusRoute.dummyRoute("A7", BusRoute.BusDirection.INCOMING, stopIds, stopTimes6);
    BusRoute testRoute7 =
        BusRoute.dummyRoute("A4", BusRoute.BusDirection.INCOMING, stopIds, stopTimes7);
    BusRoute testRoute8 =
        BusRoute.dummyRoute("A3", BusRoute.BusDirection.INCOMING, stopIds, stopTimes8);
    BusRoute testRoute9 =
        BusRoute.dummyRoute("A1", BusRoute.BusDirection.INCOMING, stopIds, stopTimes9);


    // Create dummy buses
    Bus testBus1 = new Bus(BusStop.getStop(1), testRoute1);
    Bus testBus2 = new Bus(BusStop.getStop(1), testRoute2);
    Bus testBus3 = new Bus(BusStop.getStop(1), testRoute3);
    Bus testBus4 = new Bus(BusStop.getStop(1), testRoute4);
    Bus testBus5 = new Bus(BusStop.getStop(1), testRoute5);
    Bus testBus6 = new Bus(BusStop.getStop(1), testRoute6);
    Bus testBus7 = new Bus(BusStop.getStop(1), testRoute7);
    Bus testBus8 = new Bus(BusStop.getStop(1), testRoute8);
    Bus testBus9 = new Bus(BusStop.getStop(1), testRoute9);

    // Add buses to the BST
    testBST.addBus(testBus1);
    testBST.addBus(testBus2);
    testBST.addBus(testBus3);
    testBST.addBus(testBus4);
    testBST.addBus(testBus5);
    testBST.addBus(testBus6);
    testBST.addBus(testBus7);
    testBST.addBus(testBus8);
    testBST.addBus(testBus9);

    // Get the root node of the BST
    Node<Bus> root = testBST.getRoot();

    // Get the expected node (first node after root)
    Node<Bus> expected = root.getRight().getRight().getRight();

    // Define a time for comparison
    LocalTime time = LocalTime.parse("18:30");

    // Check if the expected node is returned by the getFirstNodeAfter method
    if (testBST.getFirstNodeAfter(time, root) != expected) {
      return false;
    }

    // If the test passes, return true
    return true;
  }

  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the left subtree.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterLeftSubtree() {
    // Create an empty BusStopTree with a user-defined stop ID
    BusStopTree testBST = new BusStopTree(1);

    // Dummy data for bus routes and stop times
    int[] stopIds = {1, 2, 3, 4, 5};

    String[] stopTimes1 = {"09:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"06:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"03:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"12:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"07:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes6 = {"17:00", "07:00", "09:00", "11:00", "13:00"};


    // Create dummy bus routes
    BusRoute testRoute1 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    BusRoute testRoute2 =
        BusRoute.dummyRoute("D3", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    BusRoute testRoute3 =
        BusRoute.dummyRoute("C5", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3);
    BusRoute testRoute4 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes4);
    BusRoute testRoute5 =
        BusRoute.dummyRoute("B4", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes5);
    BusRoute testRoute6 =
        BusRoute.dummyRoute("A7", BusRoute.BusDirection.INCOMING, stopIds, stopTimes6);


    // Create dummy buses
    Bus testBus1 = new Bus(BusStop.getStop(1), testRoute1);
    Bus testBus2 = new Bus(BusStop.getStop(1), testRoute2);
    Bus testBus3 = new Bus(BusStop.getStop(1), testRoute3);
    Bus testBus4 = new Bus(BusStop.getStop(1), testRoute4);
    Bus testBus5 = new Bus(BusStop.getStop(1), testRoute5);
    Bus testBus6 = new Bus(BusStop.getStop(1), testRoute6);

    // Add buses to the BST
    testBST.addBus(testBus1);
    testBST.addBus(testBus2);
    testBST.addBus(testBus3);
    testBST.addBus(testBus4);
    testBST.addBus(testBus5);
    testBST.addBus(testBus6);

    // Get the left subtree of the root
    Node<Bus> root = testBST.getRoot().getLeft();

    // Get the expected node in the left subtree
    LocalTime time = LocalTime.parse("02:30");
    Node<Bus> expected = root.getLeft();

    // Check if the expected node is returned by the getFirstNodeAfter method
    if (testBST.getFirstNodeAfter(time, root) != expected) {
      return false;
    }

    // If the test passes, return true
    return true;
  }



  /**
   * Tests that getFirstNodeAfter returns the correct <code>Node<Bus></code> when the correct
   * <code>Node<Bus></code> is in the right subtree.
   * 
   * @return
   */
  public static boolean testGetFirstNodeAfterRightSubtree() {
    // Create an empty BusStopTree with a user-defined stop ID
    BusStopTree testBST = new BusStopTree(1);

    // Dummy data for bus routes and stop times
    int[] stopIds = {1, 2, 3, 4, 5};

    String[] stopTimes1 = {"09:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes2 = {"06:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes3 = {"03:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes4 = {"12:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes5 = {"07:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes6 = {"17:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes7 = {"20:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes8 = {"01:00", "07:00", "09:00", "11:00", "13:00"};
    String[] stopTimes9 = {"14:00", "07:00", "09:00", "11:00", "13:00"};


    // Create dummy bus routes

    BusRoute testRoute1 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes1);
    BusRoute testRoute2 =
        BusRoute.dummyRoute("D3", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes2);
    BusRoute testRoute3 =
        BusRoute.dummyRoute("C5", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes3);
    BusRoute testRoute4 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes4);
    BusRoute testRoute5 =
        BusRoute.dummyRoute("B4", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes5);
    BusRoute testRoute6 =
        BusRoute.dummyRoute("A7", BusRoute.BusDirection.INCOMING, stopIds, stopTimes6);
    BusRoute testRoute7 =
        BusRoute.dummyRoute("A4", BusRoute.BusDirection.INCOMING, stopIds, stopTimes7);
    BusRoute testRoute8 =
        BusRoute.dummyRoute("A3", BusRoute.BusDirection.INCOMING, stopIds, stopTimes8);
    BusRoute testRoute9 =
        BusRoute.dummyRoute("A1", BusRoute.BusDirection.INCOMING, stopIds, stopTimes9);


    // Create dummy buses

    Bus testBus1 = new Bus(BusStop.getStop(1), testRoute1);
    Bus testBus2 = new Bus(BusStop.getStop(1), testRoute2);
    Bus testBus3 = new Bus(BusStop.getStop(1), testRoute3);
    Bus testBus4 = new Bus(BusStop.getStop(1), testRoute4);
    Bus testBus5 = new Bus(BusStop.getStop(1), testRoute5);
    Bus testBus6 = new Bus(BusStop.getStop(1), testRoute6);
    Bus testBus7 = new Bus(BusStop.getStop(1), testRoute7);
    Bus testBus8 = new Bus(BusStop.getStop(1), testRoute8);
    Bus testBus9 = new Bus(BusStop.getStop(1), testRoute9);

    // Add buses to the BST
    testBST.addBus(testBus1);
    testBST.addBus(testBus2);
    testBST.addBus(testBus3);
    testBST.addBus(testBus4);
    testBST.addBus(testBus5);
    testBST.addBus(testBus6);
    testBST.addBus(testBus7);
    testBST.addBus(testBus8);
    testBST.addBus(testBus9);

    // Get the right subtree of the root
    Node<Bus> root = testBST.getRoot().getRight();

    // Get the expected node in the right subtree
    LocalTime time = LocalTime.parse("16:30");
    Node<Bus> expected = root.getRight();

    // Check if the expected node is returned by the getFirstNodeAfter method
    if (testBST.getFirstNodeAfter(time, root) != expected) {
      return false;
    }

    // If the test passes, return true
    return true;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a LEAF NODE. Make sure that 1) removeBus
   * returns the removed Bus, 2) the BST is still valid, 3) the BST size has been decremented.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusLeaf() {
    // TODO: OPTIONAL (but highly encouraged)
    return false;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with ONE child. Make sure
   * that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has been
   * decremented.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeOneChild() {
    // TODO: OPTIONAL (but highly encouraged)
    return false;
  }

  /**
   * Tests that removeBus correctly removes a Bus that is a non-leaf node with TWO children. Make
   * sure that 1) removeBus returns the removed Bus, 2) the BST is still valid, 3) the BST size has
   * been decremented.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeTwoChildren() {
    // TODO: OPTIONAL (but highly encouraged)
    return false;
  }


  /**
   * Tests that removeBus returns false when removing a Bus that is not in the BST. The BST should
   * not be modified.
   * 
   * Note: this test is optional and you will not be graded on it. However, it is highly encouraged.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testRemoveBusNodeNotInBST() {
    // TODO: OPTIONAL (but highly encouraged)
    return false;
  }

  /**
   * Tests the creation of an BusFilteredIterator where NONE of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToEmpty() {
    // Dummy data for bus routes and stop times
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes = {"09:00", "07:00", "09:00", "11:00", "13:00"};

    // Create dummy bus routes
    BusRoute testRoute1 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute2 =
        BusRoute.dummyRoute("D3", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute3 =
        BusRoute.dummyRoute("C5", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute4 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute testRoute5 =
        BusRoute.dummyRoute("B4", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute6 =
        BusRoute.dummyRoute("A7", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);

    // Create dummy buses
    Bus testBus1 = new Bus(BusStop.getStop(1), testRoute1);
    Bus testBus2 = new Bus(BusStop.getStop(1), testRoute2);
    Bus testBus3 = new Bus(BusStop.getStop(1), testRoute3);
    Bus testBus4 = new Bus(BusStop.getStop(1), testRoute4);
    Bus testBus5 = new Bus(BusStop.getStop(1), testRoute5);
    Bus testBus6 = new Bus(BusStop.getStop(1), testRoute6);

    // Create an ArrayList to hold the test buses
    ArrayList<Bus> testList = new ArrayList<Bus>();
    testList.add(testBus1);
    testList.add(testBus2);
    testList.add(testBus3);
    testList.add(testBus4);
    testList.add(testBus5);
    testList.add(testBus6);

    // Create an iterator from the ArrayList
    Iterator<Bus> testIterator = testList.iterator();

    // Create a BusFilteredIterator with a specified stop
    BusFilteredIterator testBFT = new BusFilteredIterator(testIterator, BusStop.getStop(2));

    try {
      // Try to get the next bus (should throw NoSuchElementException)
      testBFT.next();
    } catch (NoSuchElementException e) {
      // If an exception is caught, return true (test passes)
      return true;
    }

    // If no exception is caught, return false (test fails)
    return false;
  }


  /**
   * Tests the creation of an BusFilteredIterator where SOME of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToSome() {
    // Dummy data for bus routes and stop times
    int[] stopIds = {1, 2, 3, 4, 5};
    String[] stopTimes = {"09:00", "07:00", "09:00", "11:00", "13:00"};

    // Create dummy bus routes
    BusRoute testRoute1 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute2 =
        BusRoute.dummyRoute("D3", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute3 =
        BusRoute.dummyRoute("C5", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute4 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute testRoute5 =
        BusRoute.dummyRoute("B4", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute6 =
        BusRoute.dummyRoute("A7", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);

    // Create dummy buses
    Bus testBus1 = new Bus(BusStop.getStop(1), testRoute1);
    Bus testBus2 = new Bus(BusStop.getStop(2), testRoute2);
    Bus testBus3 = new Bus(BusStop.getStop(2), testRoute3);
    Bus testBus4 = new Bus(BusStop.getStop(1), testRoute4);
    Bus testBus5 = new Bus(BusStop.getStop(2), testRoute5);
    Bus testBus6 = new Bus(BusStop.getStop(1), testRoute6);

    // Create an ArrayList to hold the test buses
    ArrayList<Bus> testList = new ArrayList<Bus>();
    testList.add(testBus1);
    testList.add(testBus2);
    testList.add(testBus3);
    testList.add(testBus4);
    testList.add(testBus5);
    testList.add(testBus6);

    // Create an iterator from the ArrayList
    Iterator<Bus> testIterator = testList.iterator();

    // Create a BusFilteredIterator with a specified stop
    BusFilteredIterator testBFT = new BusFilteredIterator(testIterator, BusStop.getStop(2));

    // Get the next buses and compare with expected results
    Bus resultBus1 = testBFT.next();
    Bus resultBus2 = testBFT.next();
    Bus resultBus3 = testBFT.next();

    // Check if the results match the expected buses
    if (resultBus1.compareTo(testBus1) == 0 || resultBus2.compareTo(testBus1) == 0
        || resultBus3.compareTo(testBus1) == 0) {
      return false;
    }

    try {
      // Try to get the next bus (should throw NoSuchElementException)
      testBFT.next();
    } catch (NoSuchElementException e) {
      // If an exception is caught, return true (test passes)
      return true;
    }

    // If no exception is caught, return false (test fails)
    return false;
  }

  /**
   * Tests the creation of an BusFilteredIterator where ALL of the buses go to the destination.
   * 
   * @return true if the test passes, false otherwise.
   */
  public static boolean testGetNextBusesToAll() {
    // Dummy data for bus stop IDs
    int[] stopIds = {1, 2, 3, 4, 5};

    // Dummy data for bus stop times
    String[] stopTimes = {"09:00", "07:00", "09:00", "11:00", "13:00"};

    // Create dummy bus routes
    BusRoute testRoute1 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute2 =
        BusRoute.dummyRoute("D3", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute3 =
        BusRoute.dummyRoute("C5", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute4 =
        BusRoute.dummyRoute("D2", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);
    BusRoute testRoute5 =
        BusRoute.dummyRoute("B4", BusRoute.BusDirection.OUTGOING, stopIds, stopTimes);
    BusRoute testRoute6 =
        BusRoute.dummyRoute("A7", BusRoute.BusDirection.INCOMING, stopIds, stopTimes);

    // Create dummy buses with the same stop
    Bus testBus1 = new Bus(BusStop.getStop(2), testRoute1);
    Bus testBus2 = new Bus(BusStop.getStop(2), testRoute2);
    Bus testBus3 = new Bus(BusStop.getStop(2), testRoute3);
    Bus testBus4 = new Bus(BusStop.getStop(2), testRoute4);
    Bus testBus5 = new Bus(BusStop.getStop(2), testRoute5);
    Bus testBus6 = new Bus(BusStop.getStop(2), testRoute6);

    // Create an ArrayList to hold the test buses
    ArrayList<Bus> testList = new ArrayList<Bus>();
    testList.add(testBus1);
    testList.add(testBus2);
    testList.add(testBus3);
    testList.add(testBus4);
    testList.add(testBus5);
    testList.add(testBus6);

    // Create an iterator from the ArrayList
    Iterator<Bus> testIterator = testList.iterator();

    // Create a BusFilteredIterator with a specified stop
    BusFilteredIterator testBFT = new BusFilteredIterator(testIterator, BusStop.getStop(2));

    // Move through the iterator to simulate getting next buses
    testBFT.next();
    testBFT.next();
    testBFT.next();
    testBFT.next();
    testBFT.next();
    testBFT.next();

    try {
      // Try to get the next bus (should throw NoSuchElementException)
      testBFT.next();
    } catch (NoSuchElementException e) {
      // If an exception is caught, return true (test passes)
      return true;
    }

    // If no exception is caught, return false (test fails)
    return false;
  }

  /*
   * Runs all tests in class and prints true or false for each one
   */
  public static void main(String[] args) {
    // Populate BusStop with dummy data. This only has to be done once.
    BusStop.createDummyStops();

    System.out
        .println("testBusCompareToDifferentArrivalTime: " + testBusCompareToDifferentArrivalTime());
    System.out.println("testBusCompareToSameArrivalTimeDifferentRoute: "
        + testBusCompareToSameArrivalTimeDifferentRoute());
    System.out.println("testBusCompareToSameArrivalTimeSameRouteDifferentDirection: "
        + testBusCompareToSameArrivalTimeSameRouteDifferentDirection());
    System.out.println("testBusCompareToSameBus: " + testBusCompareToSameBus());
    System.out.println("testIsValidBSTEmpty: " + testIsValidBSTEmpty());
    System.out.println("testIsValidBSTInvalid: " + testIsValidBSTInvalid());
    System.out.println("testIsValidBSTValid: " + testIsValidBSTValid());
    System.out.println("testAddBusEmpty: " + testAddBusEmpty());
    System.out.println("testAddBus: " + testAddBus());
    System.out.println("testAddBusDuplicate: " + testAddBusDuplicate());
    System.out.println("testRemoveBusLeaf: " + testRemoveBusLeaf());
    System.out.println("testRemoveBusNodeOneChild: " + testRemoveBusNodeOneChild());
    System.out.println("testRemoveBusNodeTwoChildren: " + testRemoveBusNodeTwoChildren());
    System.out.println("testRemoveBusNodeNotInBST: " + testRemoveBusNodeNotInBST());
    System.out.println("testContains: " + testContains());
    System.out.println("testGetFirstNodeAfterRoot: " + testGetFirstNodeAfterRoot());
    System.out.println("testGetFirstNodeAfterLeftSubtree: " + testGetFirstNodeAfterLeftSubtree());
    System.out.println("testGetFirstNodeAfterRightSubtree: " + testGetFirstNodeAfterRightSubtree());
    System.out.println("testGetNextBusesToEmpty: " + testGetNextBusesToEmpty());
    System.out.println("testGetNextBusesToSome: " + testGetNextBusesToSome());
    System.out.println("testGetNextBusesToAll: " + testGetNextBusesToAll());
  }

}
