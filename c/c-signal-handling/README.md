# 📡 Signal Handling

This project consists of three C programs that demonstrate how to handle and send signals in a Unix/Linux environment. The programs use the `sigaction` system call to override default signal behaviors and handle them gracefully.

## Files Included
- **c-signal-handler.c** – Prints the current process ID and time periodically, handles `SIGUSR1` and `SIGINT`.
- **div0-handler.c** – Handles division operations, catching division-by-zero (`SIGFPE`) and interrupts (`SIGINT`).
- **send-signal.c** – Sends either `SIGUSR1` or `SIGINT` to another process based on user input.

## 1. c-signal-handler.c

### Description
This program demonstrates handling multiple signals:
- **SIGALRM**: Prints the current process ID and system time every 3 seconds.
- **SIGUSR1**: Increments a counter when the signal is received.
- **SIGINT** (`Ctrl+C`): Prints the total number of `SIGUSR1` signals received before exiting.


## 2. div0-handler.c

### Description
This program accepts two integers from the user and performs division.  
It demonstrates handling of:

- **SIGFPE** → Triggered if a division by zero occurs. Prints an error and exits.  
- **SIGINT** (`Ctrl+C`) → Prints the total number of successful operations before exiting.  


## 3. send-signal.c

### Description
This program sends signals to another process based on its PID.

- `-u <pid>`: Sends **SIGUSR1**.  
- `-i <pid>`: Sends **SIGINT**.  
