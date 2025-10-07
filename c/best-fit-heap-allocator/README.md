# Best-Fit Heap Allocator

This project implements a simple dynamic memory allocator with **best-fit placement** and **immediate coalescing**.  
It provides functions similar to `malloc` and `free` but operates on a custom heap.

---

## Compilation
```bash
gcc -Wall -Wextra -o heap-allocator heap-allocator.c
