# 🚌 Bus Finder

This project implements a **bus system simulation** in Java.  
It models buses, bus stops, and iterators for navigating bus routes and passengers.  
This system requires a **JAR to be built as a library first** before compiling and running the program.



## Files

- **Bus.java** → Defines the Bus object and its properties.  
- **BusDriver.java** → Main entry point of the program. Runs the bus system simulation.  
- **BusFilteredIterator.java** → Iterator that filters buses or stops based on specific conditions.  
- **BusForwardIterator.java** → Iterator that traverses buses or stops in forward order.  
- **BusStopTree.java** → Tree structure to represent bus stops and their organization.  
- **BusStopTreeTester.java** → Test suite for verifying the correctness of the BusStopTree and iterators.  



## Step 1 — Build the Library JAR

Before compiling the main program, you must create a JAR library.  
This JAR will be referenced on the classpath when compiling and running.

Example:

```bash
javac *.java
jar cf BusSystemLib.jar *.class
```



## Step 2 — Compile with the Library

After `BusSystemLib.jar` is built, compile (or recompile) your sources while including the library on the classpath:

```bash
javac -cp .:BusSystemLib.jar *.java
```