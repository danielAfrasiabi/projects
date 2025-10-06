//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title: Ticket Rush
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

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * A series of static tester methods to check the correctness of the TicketQueue and the
 * TicketQueueIterator.
 */
public class TicketQueueTester {
  /*
   * Checks the correctness of the TicketQueue's peek() method, including case(s) where it should
   * throw exceptions.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testPeek() {
    // set up test queue
    TicketQueue testQueue = new TicketQueue(5);
    TicketSiteUser testUser = new TicketSiteUser("Daniel", "banana", "1111222233334444");

    // enqueue user to queue
    testUser.login("Daniel", "banana");
    testQueue.enqueue(testUser);

    testQueue.peek();

    // test case for code which dequeues user when peeking
    try {
      testQueue.peek();
    } catch (NoSuchElementException e) {
      return false;
    }

    // test case for code which peeks incorrect user
    try {
      if (!testQueue.peek().equals(testUser)) {
        return false;
      }
    } catch (NullPointerException e) {
      return false;
    }

    return true;

  }

  /*
   * Checks the correctness of the TicketQueue's enqueue() method, including case(s) where it
   * should throw exceptions.
   * 
   * @return true if all test cases pass, false otherwise
   * 
   */
  public static boolean testEnqueue() {
    // set up test queue
    TicketQueue testQueue = new TicketQueue(2);
    TicketSiteUser testUser1 = new TicketSiteUser("Daniel", "banana", "1111222233334444");
    TicketSiteUser testUser2 = new TicketSiteUser("Adam", "apple", "5555666677778888");
    TicketSiteUser testUser3 = new TicketSiteUser("Abigail", "pear", "1234567887654321");

    // test case for enqueuing user who has not logged in yet
    try {
      testQueue.enqueue(testUser1);
      return false;
    } catch (IllegalArgumentException e) {
    }

    // enqueue users to queue
    testUser1.login("Daniel", "banana");
    testUser2.login("Adam", "apple");
    testUser3.login("Abigail", "pear");

    testQueue.enqueue(testUser1);
    testQueue.enqueue(testUser2);

    // test case for queue size
    if (testQueue.size() != 2) {
      return false;
    }

    // test case for enqueuing user to an already full queue
    try {
      testQueue.enqueue(testUser3);
      return false;
    } catch (IllegalStateException e) {
    }

    // test case to ensure users are enqueued to back of queue
    if (testQueue.dequeue() != testUser1) {
      return false;
    }

    return true;

  }

  /*
   * Checks the correctness of the TicketQueue's dequeue() method, including case(s) where it 
   * should throw exceptions.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testDequeue() {
    // set up test queue
    TicketQueue testQueue = new TicketQueue(3);

    TicketSiteUser testUser1 = new TicketSiteUser("Daniel", "banana", "1111222233334444");
    TicketSiteUser testUser2 = new TicketSiteUser("Adam", "apple", "5555666677778888");
    TicketSiteUser testUser3 = new TicketSiteUser("Abigail", "pear", "1234567887654321");

    // enqueue users to queue
    testUser1.login("Daniel", "banana");
    testUser2.login("Adam", "apple");
    testUser3.login("Abigail", "pear");
    testQueue.enqueue(testUser1);
    testQueue.enqueue(testUser2);
    testQueue.enqueue(testUser3);


    // test case to ensure users are dequeued from front of list
    if (testQueue.dequeue() != testUser1) {
      return false;
    }

    // test case to ensure user1 was properly removed from list
    if (testQueue.dequeue() != testUser2) {
      return false;
    }

    // test case for queue size
    if (testQueue.size() != 1) {
      return false;
    }

    if (testQueue.dequeue() != testUser3) {
      return false;
    }

    // test case for dequeuing user from an empty list
    try {
      testQueue.dequeue();
      return false;
    } catch (NoSuchElementException e) {
    } catch (NullPointerException e) {
      return false;
    }

    return true;

  }

  /*
   * Checks the correctness of the TicketQueue's constructor, including case(s) where it should
   * throw exceptions. Also checks the correctness of isEmpty(), isFull(), size(), capacity(), and
   * toString() on a newly created TicketQueue.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testConstructor() {
    boolean result = false;

    boolean result1 = false;
    boolean result2 = false;
    boolean result3 = false;
    boolean result4 = false;
    boolean result5 = false;

    // test case for constructing queue with illegal capacity
    try {
      TicketQueue testQueue = new TicketQueue(0);
    } catch (IllegalArgumentException e) {
      result1 = true;
    }

    // set up queue with allowed capacity
    TicketQueue testQueue = new TicketQueue(5);


    // test case for ensuring isEmpty method works properly
    if (testQueue.isEmpty()) {
      result2 = true;
    }

    // test case for ensuring isFull method works properly
    if (!testQueue.isFull()) {
      result3 = true;
    }

    // test case for ensuring capacity() works properly
    if (testQueue.capacity() == 5) {
      result4 = true;
    }

    String expected = "";

    // test case for ensuring toString() works properly
    if (testQueue.toString() == expected) {
      result5 = true;
    }

    // return value is true if all test cases returned true
    if (result1 && result2 && result3 && result4 && result5) {
      result = true;
    }

    return result;

  }

  /*
   * Checks the correctness of the TicketQueueIterator method(s) and iterating through a
   * TicketQueue.
   * 
   * @return true if all test cases pass, false otherwise
   */
  public static boolean testIterator() {
    // set up test queue
    TicketQueue testQueue = new TicketQueue(4);

    // enqueue users to queue
    TicketSiteUser testUser1 = new TicketSiteUser("Daniel", "banana", "1111222233334444");
    TicketSiteUser testUser2 = new TicketSiteUser("Adam", "apple", "5555666677778888");
    TicketSiteUser testUser3 = new TicketSiteUser("Abigail", "pear", "1234567887654321");
    TicketSiteUser testUser4 = new TicketSiteUser("Claire", "orange", "1234123412341234");
    testUser1.login("Daniel", "banana");
    testUser2.login("Adam", "apple");
    testUser3.login("Abigail", "pear");
    testUser4.login("Claire", "orange");
    testQueue.enqueue(testUser1);
    testQueue.enqueue(testUser2);
    testQueue.enqueue(testUser3);
    testQueue.enqueue(testUser4);

    String expected = testQueue.toString();

    // set up test iterator
    Iterator<TicketSiteUser> testIterator = testQueue.iterator();
    // set up array for expected outcome from iterators next() method
    TicketSiteUser[] expectedOrder =
        new TicketSiteUser[] {testUser1, testUser2, testUser3, testUser4};


    // test case to ensure iterator's next() method produces expected outcomes
    for (int i = 0; i < 4; i++) {
      try {
        if (expectedOrder[i] != testIterator.next()) {
          return false;
        }
      } catch (NoSuchElementException e) {
        return false;
      }

    }

    // test case to ensure iterator used a deep copy and did not affect queue's size
    if (testQueue.size() != 4) {
      return false;
    }

    // test case to ensure test queue has not been affected by iterator and still produces same
    // string
    if (!testQueue.toString().equals(expected)) {
      return false;
    }

    return true;

  }

  /*
   * Runs all test methods
   * 
   * @return true if all methods pass and false otherwise
   */
  private static boolean runAllTests() {
    // true if all methods pass
    return (testPeek() && testEnqueue() && testDequeue() && testConstructor() && testIterator());
  }

  /*
   * Prints if tests passed or failed
   */
  public static void main(String args[]) {
    if (runAllTests()) {
      System.out.println("All tests passed.");
    } else {
      System.out.println("Tests failed.");
    }
  }

}
