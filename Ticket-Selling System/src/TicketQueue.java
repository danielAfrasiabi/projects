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
 * A capacity based linked-list queue for TicketSiteUsers
 */
public class TicketQueue implements QueueADT<TicketSiteUser>, Iterable<TicketSiteUser> {
  private LinkedNode<TicketSiteUser> front; // the linked node at the front of the queue
  private LinkedNode<TicketSiteUser> back; // the linked node at the back of the queue
  private int size; // the number of TicketSiteUsers in the queue
  private int capacity; // the MAXIMUM number of TicketSiteUsers that the queue can hold

  /*
   * Creates an empty queue of TicketSiteUsers with the given capacity.
   * 
   * @param capacity - the capacity of this queue
   * 
   * @throws IllegalArgumentException - if the capacity is less than 1
   */
  public TicketQueue(int capacity) throws IllegalArgumentException {
    // checking that capacity is allowed
    if (capacity < 1) {
      throw new IllegalArgumentException("Your capacity is too low!");
    }
    this.capacity = capacity;
    // size initialized to 0
    size = 0;
  }

  /*
   * Reports whether or not this queue is full.
   * 
   * @return true is the number of TicketSiteUsers is the same or more of the capacity, false
   * otherwise
   * 
   */
  public boolean isFull() {
    // full if size equals capacity
    return size == capacity;

  }

  /*
   * Removes and returns the TicketSiteUser from the front of the queue.
   * 
   * @return the TicketSiteUser at the front of the queue
   * 
   * @throws NoSuchElementException - if the queue is empty
   */
  public TicketSiteUser dequeue() throws NoSuchElementException {
    // cannot dequeue user if queue is empty
    if (isEmpty()) {
      throw new NoSuchElementException("Cant dequeue any items because the queue is empty!");
    }

    // special case for size = 1
    if (size == 1) {
      TicketSiteUser dequeuedItem = front.getData();
      // front and back are now null
      front = null;
      back = null;
      // decrement size after dequeuing user
      size--;
      return dequeuedItem;
    }

    // special case for size = 2
    else if (size == 2) {
      TicketSiteUser dequeuedItem = front.getData();
      // front and back are now pointing to same user
      front = back;
      // decrement size after dequeuing user
      size--;
      return dequeuedItem;
    }

    // all other size values
    else {
      TicketSiteUser dequeuedItem = front.getData();
      // front is now pointing to the second user in queue
      front = front.getNext();
      // decrement size after dequeuing user
      size--;
      return dequeuedItem;
    }

  }

  /*
   * Returns the TicketSiteUser from the front of the queue without removing it.
   * 
   * @return the element at the front of the queue
   * 
   * @throws NoSuchElementException - if the queue is empty
   */
  public TicketSiteUser peek() throws NoSuchElementException {
    // cannot peek at queue if queue is empty
    if (isEmpty()) {
      throw new NoSuchElementException("Cant dequeue any items because the queue is empty!");
    }

    return front.getData();
  }

  /*
   * Reports if this queue is empty.
   * 
   * @return true if the queue has no TicketSiteUsers in it, false otherwise
   */
  public boolean isEmpty() {
    // queue is empty if size = 0
    return size == 0;

  }

  /*
   * Reports the current size of the queue.
   * 
   * @return the number of TicketSiteUsers in the queue
   */
  public int size() {
    return size;

  }

  /*
   * Reports the capacity of the queue.
   * 
   * @return the MAXIMUM number of TicketSiteUsers this queue can hold
   * 
   */
  public int capacity() {
    return capacity;

  }

  /*
   * Changes the capacity of the queue to the new capacity.
   * 
   * @param newCapacity - the new MAXIMUM number of TicketSiteUsers this queue can hold
   * 
   * @throws IllegalArgumentException - if the newCapacity is less than 1
   */
  public void setCapacity(int newCapacity) throws IllegalArgumentException {
    // capacity must be a positive integer
    if (newCapacity < 1) {
      throw new IllegalArgumentException("Your capacity is too low!");
    }

    capacity = newCapacity;
  }

  /*
   * Creates and returns an instance of a TicketQueueIterator for this queue.
   */
  public Iterator<TicketSiteUser> iterator() {
    return new TicketQueueIterator(this);
  }

  /*
   * Creates and returns a deep copy (not the deepest copy) of this TicketQueue.
   * 
   * @return a deep copy (not the deepest copy) of this TicketQueue
   */
  public TicketQueue deepCopy() {
    // new ticket queue which users from original queue will be copied to
    TicketQueue copyOfTicketQueue = new TicketQueue(capacity);
    // special case for capacity = 0
    if (capacity == 0) {
      return copyOfTicketQueue;
    }
    // special case for capacity = 1
    else if (capacity == 1) {
      copyOfTicketQueue.enqueue(front.getData());
      return copyOfTicketQueue;
    }
    // all other capacity values
    else {
      LinkedNode<TicketSiteUser> currentItem = front;

      // enqueue all users to copy of queue until last user in queue is reached
      while (currentItem.getNext() != null) {
        copyOfTicketQueue.enqueue(currentItem.getData());
        currentItem = currentItem.getNext();
      }
      // enqueue final user to copy of queue
      copyOfTicketQueue.enqueue(currentItem.getData());
      return copyOfTicketQueue;
    }

  }

  /*
   * Produces a string representing all contents of ticket queue
   * 
   * @return a string organizing all data of queue from front to back
   */
  @Override
  public String toString() {
    String s = "";
    LinkedNode<TicketSiteUser> runner = this.front;
    while (runner != null) {
      s += runner.getData() + "\n";
      runner = runner.getNext();
    }
    return s;
  }

  /*
   * Adds the given TicketSiteUser to the back of the queue.
   * 
   * @param newObject - element to add at the back (end) of the queue
   */
  public void enqueue(TicketSiteUser newObject)
      throws IllegalStateException, IllegalArgumentException {
    // cannot enqueue user if queue is already full
    if (isFull()) {
      throw new IllegalStateException("Sorry, the queue is full!");
    }
    // cannot enqueue user if user is not eligible to buy a ticket
    if (!newObject.canBuyTicket()) {
      throw new IllegalArgumentException("Sorry, but you are not deemed"
          + " eligible to buy a ticket!");
    }

    // special case for size = 0
    if (size == 0) {
      // front and back equal new user
      front = new LinkedNode<TicketSiteUser>(newObject);
      back = front;
      // increment size
      size++;
    }
    // special case for size = 1
    else if (size == 1) {
      // user is enqueued to back of queue
      back = new LinkedNode<TicketSiteUser>(newObject);
      front.setNext(back);
      // increment size
      size++;
    }
    // special case for all other size values
    else {
      // user is set to next item of current back pointer
      back.setNext(new LinkedNode<TicketSiteUser>(newObject));
      // user is set to back of queue
      back = back.getNext();
      // increment size
      size++;
    }
  }

}
