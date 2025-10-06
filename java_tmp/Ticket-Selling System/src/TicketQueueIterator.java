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
 * Iterator for a TicketQueue that keeps the original queue intact. This iterator will return
 * elements in the order of the queue from front to back.
 */
public class TicketQueueIterator implements Iterator<TicketSiteUser> {
  private TicketQueue userQueue; // deep copy of a TicketQueue

  /*
   * Constructor for a TicketQueueIterator that sets the data field to be a deep copy of the given
   * queue.
   * 
   * @param queue - the TicketQueue for this iterator to use
   * 
   * @throws IllegalArgumentException - if the queue is null
   */
  public TicketQueueIterator(TicketQueue queue) throws IllegalArgumentException {
    // creates deep copy of queue
    if (queue != null) {
      userQueue = queue.deepCopy();
    }
    // cannot create a deep copy of null queue
    else {
      throw new IllegalArgumentException("The queue is null!");
    }
  }

  /*
   * Determines whether or not there is another TicketSiteUser in the queue.
   * 
   * @return true if there are more TicketSiteUsers in the queue, false otherwise
   */
  public boolean hasNext() {
    // true if queue is not empty
    return !userQueue.isEmpty();
  }

  /*
   * Returns the next TicketSiteUser in the queue, based on the order from front to back.
   * 
   * @return the next TicketSiteUser in the queue
   * 
   * @throws NoSuchElementException - if there are no more TicketSiteUsers in the queue.
   */
  public TicketSiteUser next() throws NoSuchElementException {
    // check to see if there is a next user in the queue
    if (!this.hasNext()) {
      throw new NoSuchElementException("There are no more TicketSiteUsers in the queue!");
    }
    // remove and return user from front of queue
    return userQueue.dequeue();
  }
}
