# <span style="color:red; font-size:24px;">ඞ</span> Among Us Game

This project implements a simplified version of **Among Us** in Java.  
It requires a **JAR file to be built as a library** before the main program can be compiled and executed.  



## File
- **SpaceStation.java** → Main entry point for the program. Contains the `main` method and simulation logic.



## Step 1 — Build the Library JAR

Before compiling the main program, you must create a JAR library.  
This JAR will be referenced on the classpath when compiling and running.

Example (if the library source is in `SpaceStation.java`):

```bash
javac SpaceStation.java
jar cf SpaceStationLib.jar SpaceStation.class
```


## Step 2 — Compile with the Library

Once the JAR library is built, compile the main program while including the library on the classpath:

```bash
javac -cp .:SpaceStationLib.jar SpaceStation.java
```