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

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Array-based min-heap implementation of a priority boarding queue storing elements of type
 * Passenger. This class guarantees the min-heap invariant, so that:
 * 
 * The Passenger at the root should be the smallest Passenger in the queue, which corresponds to the
 * passenger having the highest priority.
 * 
 * Children always are greater than their parent.
 * 
 * The Passenger at the root of this min-heap priority queue must be dequeued (board the airplane)
 * next, meaning removed and returned by the dequeue method.
 * 
 * The BoardingQueue.peekBest() must return without removing the Passenger at the root of this
 * min-heap queue, if the queue is not empty.
 * 
 * We rely on the Passenger.compareTo() method to compare Passengers.
 * 
 * The root of a non-empty queue is always at index 0 of this array-heap.
 */
public class BoardingQueue implements PriorityQueueADT<Passenger> {
  private Passenger[] heap;// array min-heap of Passengers representing this priority queue
  private int size; // size of this priority queue

  /**
   * Constructs an empty BoardingQueue with the given capacity
   * 
   * @param capacity Capacity of this boarding queue
   * @throws IllegalArgumentException with a descriptive error message if the capacity is not a
   *                                  positive integer (greater than zero)
   */
  public BoardingQueue(int capacity) throws IllegalArgumentException {
    // capacity must be a positive integer
    if (capacity < 1) {
      throw new IllegalArgumentException("Invalid capacity! Must be a postiive integer.");
    }
    heap = new Passenger[capacity];
    size = 0;
  }

  /**
   * Returns the capacity of this BoardingQueue
   * 
   * @return the capacity of this BoardingQueue
   */
  public int capacity() {
    return heap.length;
  }

  /**
   * Removes all the elements from this Boarding Queue
   */
  public void clear() {
    // create new array-based heap of passengers to clear heap
    heap = new Passenger[capacity()];
    size = 0;
  }

  /**
   * Checks whether this BoardingQueue is full
   * 
   * @return true if this boarding queue is full
   */
  public boolean isFull() {
    // heap is only full when element at final index is not null
    if (heap[capacity() - 1] != null) {
      // size equals capacity when heap is full
      if (size == capacity()) {
        return true;
      }
    }
    // is not full otherwise
    return false;
  }

  /**
   * Returns a deep copy of the array-heap of this BoardingQueue. This method can be used for
   * testing purposes.
   * 
   * This method can be used for testing purposes.
   * 
   * @return a deep copy of the array-heap storing the Passengers in this queue
   * @throws a NullPointerException if the heap array of this BoardingQueue is null
   */
  public Passenger[] toArray() throws NullPointerException {
    // cannot execute toArray() on null heap
    if (heap == null) {
      throw new NullPointerException("Heap is null.");
    }
    return Arrays.copyOf(this.heap, this.heap.length);
  }

  /**
   * Returns a deep copy of this BoardingQueue containing all of its elements in the same order.
   * This method does not return the deepest copy, meaning that you do not need to duplicate
   * Passengers. Only the instance of the heap (including the array and its size) will be
   * duplicated.
   * 
   * @return a deep copy of this BoardingQueue. The returned new boarding queue (the deep copy) has
   *         the same length and size as this queue.
   */
  public BoardingQueue deepCopy() {
    // construct new boarding queue with same capacity of this queue
    BoardingQueue deepCopy = new BoardingQueue(capacity());
    // copy contents from this heap to deepCopy's heap
    for (int i = 0; i < capacity(); ++i) {
      deepCopy.heap[i] = this.heap[i];
    }

    return deepCopy;
  }

  /**
   * Returns a String representing this boarding queue, where each Passenger in the queue is listed
   * on a separate line, in order from smallest to greatest, meaning in their boarding order.
   * 
   * @return a String representing this boarding queue, and an empty String "" if this queue is
   *         empty.
   */
  @Override
  public String toString() {
    String s = "";
    BoardingQueue deepCopy = this.deepCopy();
    while (!deepCopy.isEmpty()) {
      // concatenate dequeued string value to string
      s += deepCopy.dequeue().toString() + "\n";
    }
    return s.trim();
  }

  /**
   * Restores the min-heap of the priority queue by percolating its root down the tree. If the
   * element at the given index does not violate the min-heap ordering property (it is smaller than
   * its smallest child), then this method does not modify the heap. Otherwise, if there is a heap
   * violation, then swap the element with the correct child and continue percolating the element
   * down the heap.
   * 
   * We assume that index is in bounds (greater or equal to zero and less than size).
   * 
   * @param index index of the element in the heap to percolate downwards
   */
  protected void percolateDown(int index) {
    // No children indices
    if (2 * index + 1 > size - 1) {
      return;
    }
    // At least one child index exists
    else {
      // Index of first child
      int swapIndex = 2 * index + 1;

      // Only has 1 child index
      if (2 * index + 2 > size - 1) {
        // Parent index is greater than child index
        if (heap[index].compareTo(heap[swapIndex]) == 1) {
          // Swapping algorithm
          Passenger swapPassenger = heap[swapIndex];
          heap[swapIndex] = heap[index];
          heap[index] = swapPassenger;
          // Recursive call to method with new index
          percolateDown(swapIndex);
        }
      }

      // Has 2 children indices
      else {
        // Parent index is greater than one of its children index
        if (heap[index].compareTo(heap[swapIndex]) == 1
            || heap[index].compareTo(heap[swapIndex + 1]) == 1) {

          // Determine which child is smaller
          if (heap[swapIndex].compareTo(heap[swapIndex + 1]) == 1) {
            swapIndex++;
          }

          // Swapping algorithm
          Passenger swapPassenger = heap[swapIndex];
          heap[swapIndex] = heap[index];
          heap[index] = swapPassenger;

          // Recursive call to method with new index
          percolateDown(swapIndex);
        }

      }
    }
  }

  /**
   * Restores the min-heap invariant of this priority queue by percolating a leaf up the heap. If
   * the element at the given index does not violate the min-heap invariant (it is greater than its
   * parent), then this method does not modify the heap. Otherwise, if there is a heap violation,
   * swap the element with its parent and continue percolating the element up the heap. We assume
   * that index is in bounds (greater or equal to zero and less than size).
   * 
   * @param index index of the element in the heap to percolate upwards
   */
  protected void percolateUp(int index) {
    // No parent
    if ((index - 1) / 2 < 0) {
      return;
    }
    // Has a parent
    else {
      // Index of parent
      int parentIndex = (index - 1) / 2;

      // Item is greater than or equal to parent
      if (heap[index].compareTo(heap[parentIndex]) >= 0) {
        // No percolation needed
        return;
      }

      // Item is less than parent
      else {
        // Swapping algorithm
        Passenger swapParent = heap[parentIndex];
        heap[parentIndex] = heap[index];
        heap[index] = swapParent;
        percolateUp(parentIndex);
      }

    }
  }

  /*
   * Determines if heap is empty.
   * 
   * @return true if heap is empty and false otherwise
   */
  @Override
  public boolean isEmpty() {
    for (int i = 0; i < capacity(); i++) {
      // heap must be null at every index
      if (heap[i] != null) {
        return false;
      }
    }

    // size must equal 0
    if (size != 0) {
      return false;
    }
    return true;
  }

  /*
   * Determines size of heap.
   * 
   * @return the size of the heap.
   */
  @Override
  public int size() {
    return size;
  }

  /*
   * Peeks and returns the first element of the heap without altering the heap.
   * 
   * @return the passenger at the front of the heap
   * @throws a NoSuchElementException if the heap is empty
   */
  @Override
  public Passenger peekBest() throws NoSuchElementException {
    // no item to peek if heap is empty
    if (isEmpty()) {
      throw new NoSuchElementException("There are no passengers to peek at.");
    }

    // first element in heap
    return heap[0];
  }

  /*
   * Adds a passenger to the correct position in the heap.
   * 
   * @param passenger - the item to be added to the heap
   * @return true if the passenger was successfully added and false otherwise
   */
  @Override
  public boolean enqueue(Passenger passenger) {
    // cannot enqueue to already full heap
    if (!isFull()) {
      // add new passenger to first null index of heap
      heap[size] = passenger;
      // percolate passenger up the heap until it finds correct position
      percolateUp(size);
      // increment size
      size++;
      // passenger successfully enqueued
      return true;
    }
    // no passenger enqueued
    return false;
  }


  /*
   * Removes and returns the item from the front of the heap, resorts the heap to maintain its
   * proper logic.
   * 
   * @return the item from the front of the heap
   * @throws a NoSuchElementException if the heap is empty
   */
  @Override
  public Passenger dequeue() throws NoSuchElementException {
    // cannot dequeue from an empty heap
    if (isEmpty()) {
      throw new NoSuchElementException("There are no passengers available to dequeue.");
    }
    // remove item from front of heap
    Passenger dequeuedItem = heap[0];
    // move item from last non-null index of heap to front
    heap[0] = heap[size - 1];
    // percolate passenger at front of heap down until it finds correct position
    percolateDown(0);
    // set dequeued item which is now at the last non-null index of heap to null
    heap[size - 1] = null;
    // decrement size
    size--;
    return dequeuedItem;
  }


}
