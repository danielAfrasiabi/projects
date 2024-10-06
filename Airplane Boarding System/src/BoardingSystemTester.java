//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Boarding System
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

import java.util.NoSuchElementException;

/**
 * This is a Utility class which implements tester methods to ensure the correctness of the
 * implementation of the main operations defined in cs300 fall 2023 p10 Airplane Boarding System.
 *
 */
public class BoardingSystemTester {


  /**
   * Ensures the correctness of Passenger.compareTo() method when called to compare two Passenger
   * objects having different boarding groups.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToDifferentGroup() {
    // Create a Passenger object named obj1 with name "Nathan", Group.A, and status true
    Passenger obj1 = new Passenger("Nathan", Group.A, true);

    // Reset the passenger order
    Passenger.resetPassengerOrder();

    // Create another Passenger object named obj2 with name "Jack", Group.B, and status true
    Passenger obj2 = new Passenger("Jack", Group.B, true);

    // Check if obj1 is greater than or equal to obj2
    if (obj1.compareTo(obj2) >= 0) {
      return false;
    }

    // Check if obj2 is less than or equal to obj1
    if (obj2.compareTo(obj1) <= 0) {
      return false;
    }

    // If both conditions are false, return true
    return true;
  }

  /**
   * Ensures the correctness of Passenger.compareTo() method when called to compare two Passenger
   * objects having the same boarding group, and different arrival orders.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToSameGroupDifferentArrival() {
    // Create a Passenger object named obj1 with name "Nathan", Group.A, and status true
    Passenger obj1 = new Passenger("Nathan", Group.A, true);

    // Create another Passenger object named obj2 with name "Jack", Group.A, and status true
    Passenger obj2 = new Passenger("Jack", Group.A, true);

    // Check if obj1 is greater than or equal to obj2
    if (obj1.compareTo(obj2) >= 0) {
      // If true, return false
      return false;
    }

    // Check if obj2 is less than or equal to obj1
    if (obj2.compareTo(obj1) <= 0) {
      // If true, return false
      return false;
    }

    // If both conditions are false, return true
    return true;
  }

  /**
   * Ensures two passengers having the SAME boarding group and with the SAME order of arrival are
   * equal (compareTo should return 0).
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPassengerCompareToSameGroupSameArrival() {
    // Reset the passenger order
    Passenger.resetPassengerOrder();

    // Create a Passenger object named obj1 with name "Nathan", Group.A, and status true
    Passenger obj1 = new Passenger("Nathan", Group.A, true);

    // Reset the passenger order again
    Passenger.resetPassengerOrder();

    // Create another Passenger object named obj2 with name "Jack", Group.A, and status true
    Passenger obj2 = new Passenger("Jack", Group.A, true);

    // Check if obj1 is not equal to obj2
    if (obj1.compareTo(obj2) != 0) {
      // If true, return false
      return false;
    }

    // Check if obj2 is not equal to obj1
    if (obj2.compareTo(obj1) != 0) {
      // If true, return false
      return false;
    }

    // If both conditions are false, return true
    return true;
  }

  /**
   * Ensures the correctness of the constructor for BoardingQueue class.
   * 
   * This tester should implement at least the following test cases:
   *
   * - Calling the constructor of the BoardingQueue class with an invalid capacity should throw an
   * IllegalArgumentException - Calling the constructor or the BoardingQueue class with a valid
   * capacity should not throw any errors, and should result in a new BoardingQueue object which is
   * empty, has size 0, a capacity equal to the capacity that was passed as a parameter, and the
   * heap array contains only null references.
   *
   * @return true if the constructor of the BoardingQueue functions properly, false otherwise
   */
  public static boolean testBoardingQueueConstructor() {
    try {
      // Try to create a BoardingQueue with capacity 0, which should throw an
      // IllegalArgumentException
      BoardingQueue testConstructor = new BoardingQueue(0);
      // If the above line doesn't throw an exception, return false
      return false;
    } catch (IllegalArgumentException e) {
      // If an IllegalArgumentException is caught, proceed with the test
    }

    // Create a BoardingQueue with capacity 10
    BoardingQueue testConstructor = new BoardingQueue(10);

    // Check if the queue is empty
    if (!testConstructor.isEmpty()) {
      // If not empty, return false
      return false;
    }

    // Check if the size of the queue is 0
    if (testConstructor.size() != 0) {
      // If not 0, return false
      return false;
    }

    // Check if the capacity of the queue is 10
    if (testConstructor.capacity() != 10) {
      // If not 10, return false
      return false;
    }

    // Get the array representation of the queue
    Passenger[] testArray = testConstructor.toArray();

    // Check that all elements in the array are null
    for (int i = 0; i < testConstructor.capacity(); i++) {
      if (testArray[i] != null) {
        // If any element is not null, return false
        return false;
      }
    }

    // If all conditions pass, return true
    return true;
  }

  /**
   * Tests the functionality of BoardingQueue.peekBest() method by calling peekBest on an empty
   * queue and verifying it throws a NoSuchElementException.
   * 
   * @return true if BoardingQueue.peekBest() verifies a correct functionality, false otherwise
   */
  public static boolean testPeekBestEmptyQueue() {
    // Create a BoardingQueue with capacity 20
    BoardingQueue testQueue = new BoardingQueue(20);

    try {
      // Try to peek the best passenger, which should throw a NoSuchElementException
      testQueue.peekBest();
      // If the above line doesn't throw an exception, return false
      return false;
    } catch (NoSuchElementException e) {
      // If a NoSuchElementException is caught, proceed with the test
    }

    // If the exception was caught, return true
    return true;
  }

  /**
   * Tests the functionality of BoardingQueue.peekBest() method by calling peekBest on a non-empty
   * queue and ensures it
   * 
   * 1) returns the Passenger having the highest priority (the minimum), and 2) does not remove that
   * Passenger from the boarding queue.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testPeekBestNonEmptyQueue() {
    // Create a BoardingQueue with capacity 20
    BoardingQueue testQueue = new BoardingQueue(20);

    // Create Passenger objects with different characteristics
    Passenger p1 = new Passenger("Daniel", Group.A, true);
    Passenger p2 = new Passenger("Jack", Group.B, true);
    Passenger p3 = new Passenger("Ashley", Group.C, true);
    Passenger p4 = new Passenger("Samantha", Group.A, true);
    Passenger p5 = new Passenger("Elijah", Group.B, true);

    // Enqueue the passengers into the testQueue
    testQueue.enqueue(p1);
    testQueue.enqueue(p2);
    testQueue.enqueue(p3);
    testQueue.enqueue(p4);
    testQueue.enqueue(p5);

    // Check if the best passenger according to the boarding order is p1
    if (testQueue.peekBest() != p1) {
      // If not, return false
      return false;
    }

    // Check if the first passenger in the array representation of the queue is p1
    if ((testQueue.toArray())[0] != p1) {
      // If not, return false
      return false;
    }

    // If both conditions pass, return true
    return true;
  }

  /**
   * Tests the functionality of the BoardingQueue.enqueue() method by calling enqueue() on an empty
   * queue and ensuring the method 1) adds the Passenger and 2) increments the size.
   * 
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEnqueueToEmptyQueue() {
    // Create a BoardingQueue with capacity 20
    BoardingQueue testQueue = new BoardingQueue(20);

    // Create a Passenger object with specific characteristics
    Passenger p1 = new Passenger("Daniel", Group.A, true);

    // Enqueue the passenger into the testQueue
    testQueue.enqueue(p1);

    // Check if the first passenger in the array representation of the queue is p1
    if ((testQueue.toArray())[0] != p1) {
      // If not, return false
      return false;
    }

    // Check if the size of the queue is 1 after enqueuing p1
    if (testQueue.size() != 1) {
      // If not, return false
      return false;
    }

    // If both conditions pass, return true
    return true;
  }

  /**
   * Tests the functionality of the BoardingQueue.enqueue() method by calling enqueue() on a
   * non-empty queue and ensuring it
   * 
   * 1) inserts the Passenger at the proper position of the heap, increments the size by one, and
   * returns true, if the queue was not already full.
   * 
   * 2) returns false, without making any changes to the size of the queue or the array heap, if the
   * method is called on a full queue.
   * 
   * Try adding at least 5 Passengers.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testEnqueueToNonEmptyQueue() {
    // Create a BoardingQueue with capacity 5
    BoardingQueue testQueue = new BoardingQueue(5);

    // Create Passenger objects with different characteristics
    Passenger p1 = new Passenger("Daniel", Group.A, true);
    Passenger p2 = new Passenger("Jack", Group.B, true);
    Passenger p3 = new Passenger("Ashley", Group.B, true);
    Passenger p4 = new Passenger("Samantha", Group.B, true);

    // Enqueue the first set of passengers into the testQueue
    testQueue.enqueue(p1);
    testQueue.enqueue(p2);
    testQueue.enqueue(p3);
    testQueue.enqueue(p4);

    // Create another Passenger object
    Passenger p5 = new Passenger("Elijah", Group.A, true);

    // Check if enqueuing p5 is successful
    if (testQueue.enqueue(p5) != true) {
      // If not, return false
      return false;
    }

    // Check if the size of the queue is 5 after enqueuing p5
    if (testQueue.size() != 5) {
      // If not, return false
      return false;
    }

    // Check if the second passenger in the array representation of the queue is p5
    if ((testQueue.toArray())[1] != p5) {
      // If not, return false
      return false;
    }

    // Create another Passenger object
    Passenger p6 = new Passenger("Alicia", Group.C, true);

    // Check if enqueuing p6 is unsuccessful (since the queue is full)
    if (testQueue.enqueue(p6) != false) {
      // If not, return false
      return false;
    }

    // If all conditions pass, return true
    return true;
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() method by calling dequeue() on an empty
   * queue and ensures a NoSuchElementException is thrown in that case.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testDequeueEmpty() {
    // Create a BoardingQueue with capacity 20
    BoardingQueue testQueue = new BoardingQueue(20);

    try {
      // Try to dequeue from an empty queue, which should throw a NoSuchElementException
      testQueue.dequeue();
      // If the above line doesn't throw an exception, return false
      return false;
    } catch (NoSuchElementException e) {
      // If a NoSuchElementException is caught, proceed with the test
    }

    // If the exception was caught, return true
    return true;
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() method by calling dequeue() on a queue of
   * size one and ensures the method call returns the correct Passenger, size is zero, and the array
   * heap contains null references, only.
   * 
   * @return true if tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testDequeueBoardingQueueSizeOne() {
    // Create a BoardingQueue with capacity 20
    BoardingQueue testQueue = new BoardingQueue(20);

    // Create a Passenger object with specific characteristics
    Passenger p1 = new Passenger("Daniel", Group.A, true);

    // Enqueue the passenger into the testQueue
    testQueue.enqueue(p1);

    // Check if the dequeued passenger is equal to p1
    if (testQueue.dequeue() != p1) {
      // If not, return false
      return false;
    }

    // Check if the size of the queue is 0 after dequeuing
    if (testQueue.size() != 0) {
      // If not, return false
      return false;
    }

    // Get the array representation of the queue
    Passenger[] testHeap = testQueue.toArray();

    // Check that all elements in the array are null
    for (int i = 0; i < testQueue.capacity(); i++) {
      if (testHeap[i] != null) {
        // If any element is not null, return false
        return false;
      }
    }

    // If all conditions pass, return true
    return true;
  }

  /**
   * Tests the functionality of BoardingQueue.dequeue() when called on a non-empty boarding queue.
   * 
   * This tests ensures the dequeue() method removes, and returns the passenger with the highest
   * boarding priority in the queue, the size of the queue must be decremented by one, and the
   * contents of the heap array is as expected.
   * 
   * @return true if PriorityCareAdmissions.dequeue() returns the correct Passenger each time it is
   *         called and size is appropriately decremented, false otherwise
   */
  public static boolean testDequeueBoardingQueueNonEmpty() {
    // Create a BoardingQueue with capacity 10
    BoardingQueue testQueue = new BoardingQueue(10);

    // Create Passenger objects with different characteristics
    Passenger p1 = new Passenger("Daniel", Group.C, true);
    Passenger p2 = new Passenger("Jack", Group.B, true);
    Passenger p3 = new Passenger("Ashley", Group.B, true);
    Passenger p4 = new Passenger("Samantha", Group.B, true);
    Passenger p5 = new Passenger("Elijah", Group.A, true);
    Passenger p6 = new Passenger("Alicia", Group.C, true);

    // Enqueue the passengers into the testQueue
    testQueue.enqueue(p1);
    testQueue.enqueue(p2);
    testQueue.enqueue(p3);
    testQueue.enqueue(p4);
    testQueue.enqueue(p5);
    testQueue.enqueue(p6);

    // Check if the dequeued passenger is equal to p5
    if (testQueue.dequeue() != p5) {
      // If not, return false
      return false;
    }

    // Check if the size of the queue is 5 after dequeuing p5
    if (testQueue.size() != 5) {
      // If not, return false
      return false;
    }

    // Define the expected and actual arrays after the first dequeue operation
    Passenger[] expectedArray1 = new Passenger[] {p2, p4, p3, p1, p6, null, null, null, null, null};
    Passenger[] actualArray1 = testQueue.toArray();

    // Check if the expected and actual arrays are equal
    for (int i = 0; i < expectedArray1.length; i++) {
      if (expectedArray1[i] != actualArray1[i]) {
        // If not, return false
        return false;
      }
    }

    // Check if the dequeued passenger is equal to p2
    if (testQueue.dequeue() != p2) {
      // If not, return false
      return false;
    }

    // Check if the size of the queue is 4 after dequeuing p2
    if (testQueue.size() != 4) {
      // If not, return false
      return false;
    }

    // Define the expected and actual arrays after the second dequeue operation
    Passenger[] expectedArray2 =
        new Passenger[] {p3, p4, p6, p1, null, null, null, null, null, null};
    Passenger[] actualArray2 = testQueue.toArray();

    // Check if the expected and actual arrays are equal
    for (int i = 0; i < expectedArray2.length; i++) {
      if (expectedArray2[i] != actualArray2[i]) {
        // If not, return false
        return false;
      }
    }

    // If all conditions pass, return true
    return true;
  }

  /**
   * Tests the functionality of the clear() method. Should implement at least the following
   * scenarios:
   * 
   * - clear can be called on an empty queue with no errors.
   * 
   * - clear can be called on a non-empty queue with no errors.
   * 
   * After calling clear(), this tester ensures that the queue is empty, meaning its size is zero
   * and its heap array contains only null references.
   *
   * @return true if the tester verifies a correct functionality and false if at least one bug is
   *         detected
   */
  public static boolean testBoardingQueueClear() {
    // Create a BoardingQueue with capacity 10
    BoardingQueue testQueue = new BoardingQueue(10);

    // Clear the queue
    testQueue.clear();

    // Check if the size of the queue is 0 after clearing
    if (testQueue.size() != 0) {
      // If not, return false
      return false;
    }

    // Get the array representation of the cleared queue
    Passenger[] testArray1 = testQueue.toArray();

    // Check that all elements in the array are null
    for (int i = 0; i < testArray1.length; i++) {
      if (testArray1[i] != null) {
        // If any element is not null, return false
        return false;
      }
    }

    // Create Passenger objects with different characteristics
    Passenger p1 = new Passenger("Daniel", Group.C, true);
    Passenger p2 = new Passenger("Jack", Group.B, true);
    Passenger p3 = new Passenger("Ashley", Group.B, true);
    Passenger p4 = new Passenger("Samantha", Group.B, true);
    Passenger p5 = new Passenger("Elijah", Group.A, true);
    Passenger p6 = new Passenger("Alicia", Group.C, true);

    // Enqueue the passengers into the testQueue
    testQueue.enqueue(p1);
    testQueue.enqueue(p2);
    testQueue.enqueue(p3);
    testQueue.enqueue(p4);
    testQueue.enqueue(p5);
    testQueue.enqueue(p6);

    // Clear the queue again
    testQueue.clear();

    // Check if the size of the queue is 0 after clearing
    if (testQueue.size() != 0) {
      // If not, return false
      return false;
    }

    // Get the array representation of the cleared queue
    Passenger[] testArray2 = testQueue.toArray();

    // Check that all elements in the array are null
    for (int i = 0; i < testArray2.length; i++) {
      if (testArray2[i] != null) {
        // If any element is not null, return false
        return false;
      }
    }

    // If all conditions pass, return true
    return true;
  }

  /**
   * Main method to run this tester class.
   * 
   * @param args list of input arguments if any
   */
  public static void main(String[] args) {
    System.out.println("testPassengerCompareToDifferentGroup: "
        + (testPassengerCompareToDifferentGroup() ? "Pass" : "Failed!"));
    System.out.println("testPassengerCompareToSameGroupDifferentArrival: "
        + (testPassengerCompareToSameGroupDifferentArrival() ? "Pass" : "Failed!"));
    System.out.println("testPassengerCompareToSameGroupSameArrival: "
        + (testPassengerCompareToSameGroupSameArrival() ? "Pass" : "Failed!"));
    System.out.println(
        "testBoardingQueueConstructor: " + (testBoardingQueueConstructor() ? "Pass" : "Failed!"));
    System.out
        .println("testPeekBestEmptyQueue: " + (testPeekBestEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println(
        "testPeekBestNonEmptyQueue: " + (testPeekBestNonEmptyQueue() ? "Pass" : "Failed!"));
    System.out
        .println("testEnqueueToEmptyQueue: " + (testEnqueueToEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println(
        "testEnqueueToNonEmptyQueue: " + (testEnqueueToNonEmptyQueue() ? "Pass" : "Failed!"));
    System.out.println("testDequeueEmpty: " + (testDequeueEmpty() ? "Pass" : "Failed!"));
    System.out.println("testDequeueBoardingQueueSizeOne: "
        + (testDequeueBoardingQueueSizeOne() ? "Pass" : "Failed!"));
    System.out.println("testDequeueBoardingQueueNonEmpty: "
        + (testDequeueBoardingQueueNonEmpty() ? "Pass" : "Failed!"));
    System.out
        .println("testBoardingQueueClear: " + (testBoardingQueueClear() ? "Pass" : "Failed!"));
  }
}
