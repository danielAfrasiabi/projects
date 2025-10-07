# Siamese Magic Square Generator

This program generates a magic square of a given odd size (≥ 3) using the **Siamese method** and writes it to an output file.

---

## Input
- The program prompts the user for the size of the magic square:
  - Must be an **odd integer** ≥ 3.
  - If the size is invalid, the program prints an error message and exits.

---

## Output
- The program writes the magic square to a specified output file (provided as a command-line argument).
- File format:
  - First line: the size of the square `N`
  - Next `N` lines: each row of the magic square, with values separated by commas `,`

### Example (for size = 7)
Output file `output1.txt`
