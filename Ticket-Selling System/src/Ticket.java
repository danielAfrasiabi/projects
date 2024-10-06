/**
 * A object for a concert Ticket information. Each ticket represents one available seat for
 * an event.
 * 
 * @author Michelle
 *
 */
public class Ticket {
  private String eventName; //the name of the event this ticket is for 
  private String venue; //the venue (location) where the ticket is for
  private String section; //the section name for the seat the ticket is for
  private String seatNumber; //the number for the seat the ticket is for
  private double price; //the cost of the ticket
  
  /**
   * Constructor for a new Ticket object. Assigns the given values to their respective data fields.
   * @param eventName the event that this ticket is for
   * @param venue the venue where the event is for this ticket
   * @param section the section name for where the seat is
   * @param seatNumber the number for the ticket's seat
   * @param price the price of the ticket
   */
  public Ticket(String eventName, String venue, String section, String seatNumber, double price) {
    this.eventName = eventName;
    this.venue = venue;
    this.section = section;
    this.seatNumber = seatNumber;
    this.price = price;
  }
  
  /**
   * Returns a string representation of this Ticket. The format of String is 
   * [eventName] + " @" + [venue] + " " + [section] + ":" + [seatNumber] + " - $" + [price]
   * 
   * Ex. "Taylor Swift Eras Tour @Madison Square Garden A:5 - $425.46"
   * 
   * @return the string representation of this Ticket
   */
  @Override
  public String toString() {
    return eventName + " @" + venue + " " + section +":" + seatNumber + " - $" + price;
  }
  

}
