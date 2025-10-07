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

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * An iterator that only returns buses from another iterator that go to a particular destination.
 */
public class BusFilteredIterator implements Iterator<Bus> {
  // The iterator we are filtering.
  private Iterator<Bus> baseIterator;

  // The destination BusStop we are filtering by.
  private BusStop destination;

  // The next Bus to be returned, or null if there aren't any more.
  private Bus next;


  /*
   * Construct a new BusFilteredIterator that filters the given iterator to return only Bus-es that
   * stop at the given destination.
   * 
   * @param iterator - the iterator we are filtering.
   * 
   * @param destination - the desired destination.
   */
  public BusFilteredIterator(Iterator<Bus> iterator, BusStop destination) {
    baseIterator = iterator;
    this.destination = destination;
    // Initialize variable next
    advanceToNext();
  }

  /*
   * Private helper method that advances this iterator. It will iterate over `this.iterator` until
   * it reaches a Bus that stops at destination. Then, it will store that Bus in `next`.
   */
  private void advanceToNext() {
    // Loop as long as iterator has next
    while (baseIterator.hasNext()) {
      Bus iteratedBus = baseIterator.next();
      // Only accounts for busses which go to specified destination
      if (iteratedBus.goesTo(destination)) {
        next = iteratedBus;
        return;
      }
    }
    // Next not found, set to null
    next = null;
  }

  /*
   * Returns true if there is another Bus (that goes to the destination) in this iterator, or false
   * otherwise. This method should not change any of the fields of the iterator.
   * 
   * @return true if a call to next() will return another Bus; false otherwise.
   */
  public boolean hasNext() {
    // True if next is not null
    return next != null;
  }

  /*
   * Returns the `next` bus and advances the iterator until the next bus it will return.
   * 
   * @return Buses from the iterator baseIterator that go to the destination stop.
   * 
   * @throws NoSuchElementException - if called when there is no next Bus.
   */
  public Bus next() throws NoSuchElementException {
    // Throw exception if iterator is empty
    if (!hasNext()) {
      throw new NoSuchElementException("There is no next bus!");
    }

    // Advance iterator and return bus
    Bus nextBus = next;
    advanceToNext();
    return nextBus;

  }


}
