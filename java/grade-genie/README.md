# 📝 Grade Genie

This project implements an **assignment grading system** in Java.  
It models assignments and groups, applies scaling or dropping rules, and calculates grades.  
It includes driver and tester classes for verifying grading logic.



## Files

- **AssignmentGroup.java** → Represents a collection of assignments and their weights.  
- **DropAssignmentGroup.java** → Extends `AssignmentGroup` with functionality to drop the lowest score(s).  
- **ScalingAssignmentGroup.java** → Extends `AssignmentGroup` with functionality to scale scores.  
- **SimpleAssignment.java** → Represents a single assignment with its score and weight.  
- **CS300Grader.java** → Driver program for running the grading system.  
- **AssignmentGroupTester.java** → Tester for verifying `AssignmentGroup` functionality.  
- **SimpleAssignmentTester.java** → Tester for verifying `SimpleAssignment` functionality.  
