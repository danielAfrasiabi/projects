# 🐸 Frog Game with Bugs (Java)

This project implements a **Frog Game simulation** in Java, featuring multiple bug types and interactions with a frog character.  
Like the Bus and SpaceStation projects, it requires a **JAR to be built as a library first** before compiling and running.

---

## 📂 Files

- **Frog.java** → Defines the Frog character and its movement/interaction logic.  
- **FrogGame.java** → Main driver of the game. Entry point (`main` method).  
- **Bug.java** → Base class for bugs in the game.  
- **BouncingBug.java** → Bug subclass that bounces around the environment.  
- **CirclingBug.java** → Bug subclass that moves in circular patterns.  
- **StrongBug.java** → Bug subclass with special strength attributes.  
- **GameActor.java** → Abstract/interface defining shared behavior for all actors in the game.  
- **Moveable.java** → Interface for objects that can move.  
- **Hitbox.java** → Class handling collisions and interaction areas.  
- **Tongue.java** → Represents the frog’s tongue attack.  

---

## ⚙️ Step 1 — Build the Library JAR

Before compiling or running, build the JAR library from all `.class` files:

```bash
javac *.java
jar cf FrogGameLib.jar *.class
```
---

## ⚙️ Step 2 — Compile with the Library

After `FrogGameLib.jar` is built, compile your sources **against** the library:

```bash
javac -cp .:FrogGameLib.jar *.java
```