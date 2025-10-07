// Main File:		n/a
// This File:		send_signal.c
// Other Files:		my_c_signal_handler.c, my_div0_handler.c
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

int main(int argc, char* argv[]) {             
	//Ensure correct number of arguments were provided
	if (argc != 3) {
		printf("Usage: send_signal -u <pid> to send SIGUSR1\n");
		printf("       send_signal -i <pid> to send SIGINT\n");
		exit(1);         
	}

	int pid = atoi(argv[2]); //Convert string to int

	//Checks argv[1] to determine which signal to send to specified process
	if (strcmp(argv[1],"-u") == 0) {
		
		//Send SIGUSR1 to given pid and check if operated successfully
		if (kill(pid, SIGUSR1) == -1) {
			exit(1);
		}
	}
	
	if (strcmp(argv[1],"-i") == 0) {

		//Send SIGINT to given pid and check if operated successfully
		if (kill(pid, SIGINT) == -1) { 
			exit(1);
		}
	} 

}
