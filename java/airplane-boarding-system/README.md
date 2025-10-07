# Airline Boarding System (Java)

This project simulates an airline boarding system that uses a **priority queue** to manage passengers and boarding groups.  
It models passengers with different attributes, places them into groups, and ensures boarding follows a priority-based order.

---

## 📂 Files Included

- **Passenger.java**  
  Defines the `Passenger` class with attributes like name, boarding group, and possibly priority (e.g., first class, economy, special needs).  

- **Group.java**  
  Defines a `Group` of passengers, which can be used to organize and manage collections of passengers boarding together.  

- **PriorityQueueADT.java**  
  An interface that specifies the operations of a priority queue (insert, remove, peek, check if empty, etc.).  
  Used to enforce abstraction for the boarding queue system.  

- **BoardingQueue.java**  
  Implements the `PriorityQueueADT` for handling passengers and groups in a priority queue structure.  
  This class determines boarding order based on passenger/group priority.  

- **BoardingSystemDriver.java**  
  The main driver program. Runs the boarding system simulation, reading inputs, inserting passengers/groups, and outputting boarding order.  

- **BoardingSystemTester.java**  
  A test class that verifies the correctness of the boarding system implementation.  
  Includes unit tests for queue operations, passenger ordering, and system rules.  

---

## ⚙️ Compilation

To compile all classes:

```bash
javac *.java
```