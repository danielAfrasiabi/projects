// Main File:		n/a
// This File:		my_div0_handler.c
// Other Files:		my_c_signal_handler.c, send_signal.c
// Semester:		CS 354 Lecture 002 Spring 2025
// Grade Group:		gg13
// Instructor:		Mahmood
//
// Author:			Daniel Afrasiabi
// Email:			dafrasiabi@wisc.edu
// CS Login:		afrasiabi
//
////////////////////////////OTHER SOURCES OF HELP///////////////////////////
// Persons:			none
//
//
// Online sources:	none
//
//
// AI chats:		none
/////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
// Copyright 2013,2019-2025
// Posting or sharing this file is prohibited, including any changes/additions.
// Used with permission for Spring 2025
////////////////////////////////////////////////////////////////////////////////

#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <string.h>

int counter = 0; //Number of division operations successfully completed

//Signal handler for SIGFPE
void handler_SIGFPE() {
	printf("Error: a division by 0 operation was attempted.\n");
	printf("Total number of operations completed succesfully: %i\nThe program will be terminated.\n", counter);
	exit(0);
}

//Signal handler for SIGINT
void handler_SIGINT() {
	printf("\nTotal number of operations completed succesfully: %i\nThe program will be terminated.\n", counter);
	exit(0);
}

int main() {
	struct sigaction sa1, sa2; //Signal actions to replace pre-defined signal actions
	
	memset(&sa1, 0, sizeof(sa1)); //Clear memory to 0s for sa1
	sa1.sa_handler = handler_SIGFPE; //Define signal handler corresponding to respective signal action
	sa1.sa_flags = 0; //No preset flags
	
	//Check to ensure signal was successfully bound to sa1 
	if(sigaction(SIGFPE, &sa1, NULL) != 0) {
		printf("Error binding SIGFPE");
		exit(1);
	}

	memset(&sa2, 0, sizeof(sa2));
	sa2.sa_handler = handler_SIGINT;
	sa2.sa_flags = 0;
	if (sigaction(SIGINT, &sa2, NULL) != 0) {
		printf("Error binding SIGINT");
		exit(1);
	}

	//Enter infinite loop
	while (1) {
		char buffer1[100], buffer2[100];

		printf("Enter first integer: ");	
		if (fgets(buffer1, 100, stdin) == NULL) { //Check if fgets() method properly stores input into buffer1
			exit(1);
		}
		int int1 = atoi(buffer1); //Use atoi() to convert string to int
		
		printf("Enter second integer: ");	
		if (fgets(buffer2, 100, stdin) == NULL) {
			exit(1);
		}
		int int2 = atoi(buffer2);

		int quotient = int1 / int2; 
		int remainder = int1 % int2;

		printf("%i / %i is %i with a remainder of %i\n", int1, int2, quotient, remainder); //Print result of division and remainder in formatted output

		counter++; //Increment counter after division operation successfully completed
	}
}
		
