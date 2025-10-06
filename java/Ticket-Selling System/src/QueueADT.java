/**
 * Generic interface for the Queue abstract data type.
 * @author Michelle
 *
 * @param <T> the data type of the elements the queue contains
 */
public interface QueueADT<T> {
  /**
   * Inserts an element at the back of the queue
   *
   * @param newObject element to add at the back (end) of the queue
   */
  public void enqueue(T newObject);

  /**
   * Removes and returns element at the front of the queue.
   *
   * @return the element at the front of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  public T dequeue();

  /**
   * Returns without removing element at the front of the queue.
   *
   * @return the element at the front of the queue
   * @throws NoSuchElementException if the queue is empty
   */
  public T peek();

  /**
   * Checks whether or not the queue is empty.
   *
   * @return true if the queue is empty, false otherwise
   */
  public boolean isEmpty();
  
  /**
   * Reports the current size of the queue.
   *
   * @return the number of elements in the queue
   */
  
  public int size();
}

