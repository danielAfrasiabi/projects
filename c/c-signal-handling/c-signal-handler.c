// Main File:		n/a
// This File:		my_c_signal_handler.c
// Other Files:		send_signal.c, my_div0_handler.c
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
#include <unistd.h>
#include <time.h>
#include <string.h>

int seconds = 3; //Seconds between alarm iterations
int counter = 0; //Count of SIGUSR1 signals received

//Signal handler for SIGALRM
void handler_SIGALRM() {
	time_t now = time(NULL);
    
	//Check time() function operated successfully
	if (now == (time_t) - 1) {
		exit(1);
	}

	char *time_str = ctime(&now);

	//Check ctime() function operated successfully	
	if (time_str == NULL) {
		exit(1);
	}

	printf("PID: %d CURRENT TIME: %s", getpid(), time_str);
	
	alarm(seconds); //Retrigger alarm
}

//Signal handler for SIGUSR1
void handler_SIGUSR1() {
	printf("Received SIGUSR1, user signal 1 counted.\n");
	counter++; //Increment counter
}

//Signal handler for SIGINT
void handler_SIGINT() {
	printf("\nSIGINT handled.\nSIGUSR1 was handled %d times. Exiting now.\n", counter);
	exit(0); //Exit program
}

int main() {
	struct sigaction sa1, sa2, sa3; //Used to override pre-defined signal actions

	memset(&sa1, 0, sizeof(sa1)); //Clear memory to 0s for sa1
	sa1.sa_handler = handler_SIGALRM; //Define signal handler corresponding to signal action
	sa1.sa_flags = 0; //No flags used in signal action
	
	//Check to ensure signal was successfully bound to sa1
	if(sigaction(SIGALRM, &sa1, NULL) != 0) {
		printf("Error binding SIGALRM");
		exit(1);
	}

	memset(&sa2, 0, sizeof(sa2));
	sa2.sa_handler = handler_SIGUSR1;
	sa2.sa_flags = 0;
	if (sigaction(SIGUSR1, &sa2, NULL) != 0) {
		printf("Error binding SIGUSR1");
		exit(1);
	}

	memset(&sa3, 0, sizeof(sa3));
	sa3.sa_handler = handler_SIGINT;
	sa3.sa_flags = 0;
	if (sigaction(SIGINT, &sa3, NULL) != 0) {
		printf("Error binding SIGINT");
		exit(1);
	}
	
	printf("PID and current time: prints every 3 seconds.\nType Ctrl-C to end the program.\n");

	alarm(seconds); //Trigger alarm
	
	while (1) {
		//Infinite loop
	}

}
	
