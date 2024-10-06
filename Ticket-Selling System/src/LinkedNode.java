/**
 * A generic singly linked node.
 * @author Michelle
 *
 * @param <T> the data type that the node will store
 */
public class LinkedNode <T> {
  /** the data of this LinkedNode*/
  private T data;
  /** the reference to the LinkedNode that comes after this LinkedNode*/
  private LinkedNode<T> next;
  
  /**
   * Constructor that creates a new LinkedNode object containing the given data. This node's next
   * is null.
   * @param data the data to store in this LinkedNode
   */
  public LinkedNode(T data) {this.data = data;}
  
  /** Constructor that creates a new LinkedNode object containing the given data and whose next is 
   * the given LinkedNode.
   * @param data the data to store in this LinkedNode
   * @param next the LinkedNode that comes after this LinkedNode
   */
  public LinkedNode(T data, LinkedNode<T> next) {
    this(data);
    this.next = next;
  }
  
  /**
   * Getter for the LinkedNode's data.
   * @return the data of this LinkedNode
   */
  public T getData() {return this.data;}
  
  /**
   * Getter for the LinkedNode that follows this one.
   * @return the reference to the next LinkedNode
   */
  public LinkedNode<T> getNext() {return this.next;}
  
  /**
   * Sets the LinkedNode that comes next to the given LinkedNode.
   * @param next the reference to the next LinkedNode
   */
  public void setNext(LinkedNode<T> next) {this.next = next;}
  
  /**
   * Returns a string representation of this LinkedNode. The format of String is the data's
   * toString() value  + " ->" if there is a next OR " -> END" if there is no next.
   * 
   * Ex. " nodeData ->" for a node that has a next 
   * 
   * @return the string representation of this LinkedNode
   */
  @Override
  public String toString() {
    return data.toString() + (this.next != null ? " -> " : " -> END");
  }
}
