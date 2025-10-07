# 🎟️ Ticket Queue System (Java)

This project implements a **ticket queue management system** in Java.  
It models tickets, queues, and users, with iterators for queue traversal and testers for validation.

---

## 📂 Files

- **LinkedNode.java** → A node in a linked data structure (used for the queue).  
- **QueueADT.java** → Interface defining queue operations (enqueue, dequeue, peek, isEmpty, etc.).  
- **Ticket.java** → Represents a ticket object (with attributes such as ticket ID, details, etc.).  
- **TicketQueue.java** → Implements the ticket queue using linked nodes and `QueueADT`.  
- **TicketQueueIterator.java** → Iterator for traversing tickets in the queue.  
- **TicketQueueTester.java** → Automated tester for validating the queue implementation.  
- **TicketSiteUser.java** → Represents a user who interacts with the ticket system (creates, manages tickets).  

---

## ⚙️ Compilation

Compile all Java files:

```bash
javac *.java
```