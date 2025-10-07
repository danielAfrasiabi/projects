// Main File:		my_magic_square.c
// This File:		my_magic_square.c
// Other Files:		n/a
// Semester:		CS 354 Lecture 002 Spring 2025
// Grade Group:		gg13
// Instructor:		Mahmood
//
// Author:		Daniel Afrasiabi
// Email:		dafrasiabi@wisc.edu
// CS Login:		afrasiabi
//
////////////////////////////OTHER SOURCES OF HELP///////////////////////////
// Persons:		none
//
//
// Online sources:	none
//
//
//
// AI chats:		none
///////////////////////////////////////////////////////////////////////////////
// Copyright 2020 Jim Skrentny
// Posting or sharing this file is prohibited, including any changes/additions.
// Used by permission, CS 354 Spring 2025, Hina Mahmood
////////////////////////////////////////////////////////////////////////////////

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Structure that represents a magic square
typedef struct {
	int size;           // dimension of the square
	int **magic_square; // ptr to 2D heap array that stores magic square values
} MagicSquare;

/* TODO:
 * Prompts the user for magic square's size, read size, and
 * check if it is an odd number >= 3 
 * If not valid size, display the required error message and exit
 *
 * return the valid number
 */
int getSize() {
	int size;
	
	//Prompts user to provide integer value for magic square size
	printf("Enter magic square's size (odd integer >=3)\n");
	
	//Stores integer value at address of variable - size
    	scanf("%i", &size);
	
	//Checks if inputted size meets constraints
	if (size%2 != 1) {
			printf("Magic square size must be odd.\n");
			exit(1);
		}	   
	
	if (size < 3) {
		printf("Magic square size must be >= 3.\n");
		exit(1);
	}
	
	return size;
} 

/* TODO:
 * Creates a magic square of size n on the heap
 *
 * May use the Siamese magic square algorithm or alternative
 * algorithm that produces a valid magic square 
 *
 * n - the number of rows and columns
 *
 * returns a pointer to the completed MagicSquare struct
 */
MagicSquare *generateMagicSquare(int n) {
	//Creates an instance of MagicSquare * and allocates memory on heap
	MagicSquare *magicSquare = (MagicSquare *)malloc(sizeof(MagicSquare));
	
	//Check if allocation was successful
	if (magicSquare == NULL) {
		printf("Failed to allocate memory for magic square.\n");
		exit(1);
	}
	
	//Set size to parameter n
	magicSquare->size = n;
	
	//Allocate memory on heap for rows of magicSquare->magic_square
	(magicSquare->magic_square) = (int **)malloc( (magicSquare->size) * sizeof(int *));
	
	//Check if allocation for int** was successful
	if( (magicSquare->magic_square) == NULL) {
		printf("Failed to allocate memory for magic square.\n");
		exit(1);
	}

	//Allocate memory on heap for columns of magicSquare->magic_square
	for (int i = 0; i < (magicSquare->size); i++) {
		*( (magicSquare->magic_square) + i) = (int *)malloc( (magicSquare->size) * sizeof(int));
	
		//Check if each respective allocation for int* was successful
		if ( *( (magicSquare->magic_square) + i) == NULL ) {
			printf("Failed to allocate memory for magic square.\n");
			exit(1);
		}
	}
	
	//Initialize all magicSquare elements to 0
	for (int i = 0; i < (magicSquare->size); i++) {
		for (int j = 0; j < (magicSquare->size); j++) {
			*( *( (magicSquare->magic_square) + i ) + j) = 0;
		}
	}

	//Define variables to assist with magic square creatoin
	int i = 0; //current row position for magic square
	int j = ( (magicSquare->size) / 2); //current column position for magic square
	int x = 1; //current number of magic square to be inserted
	int limit = (magicSquare->size) * (magicSquare->size); //maximum number for magic square
	
	int temp_i = 0; //temp variable holder for i to check if new i position is available
	int temp_j = (magicSquare->size) / 2;  //temp variable holder to to check if new j position is available


	//Loop until all numbers from x = 1 to limit are placed in the magic square
	while (x <= limit) { 
		*( *( (magicSquare->magic_square) + i) + j) = x++;  //Assigns x to current position then increments x
		
		//Situation 1: Move up and to the right	
		//If currently in the top row of the square, move to bottom row
		if (i == 0) { 
			temp_i = (magicSquare->size) - 1;
		}
		//Otherwise move up one row
		else {
			temp_i = i - 1;			
		}
		
		//If currently in rightmost column of square, move to leftmost column
		if (j == ( (magicSquare->size) - 1)) {
			temp_j = 0;
		}
		//Otherwise move to the right one column
		else {
			temp_j = j + 1;
		}
	
		//If situation 1 presents an empty square then confirm temp_i and temp_j to i and j
		if (*( *( (magicSquare->magic_square) + temp_i) + temp_j)  == 0) {
			i = temp_i;
			j = temp_j;
			
		}
		
		//If situation 1 fails, look down one square
		else {
			temp_i = i;
			temp_j = j;
			
			//If currently in bottom row, move to the top row
			if (i == ( (magicSquare->size) - 1) ) {
				temp_i = 0;
			}
			//Otherwise move down one row
			else {
				temp_i = i + 1;
			}
			
			//If situation 2 presents an empty square then confirm temp_i and temp_j to i and j; 
			//if both situations fail then it can be assumed that the square is full.
			if ( *( *( (magicSquare->magic_square) + temp_i) + temp_j) == 0) {
				i = temp_i;
				j = temp_j;
			}
		}

	}

	return magicSquare;	
} 

/* TODO:  
 * Open a new file (or overwrite the existing file)
 * and write magic square values to the file
 * in a format specified in the assignment.
 *
 * See assignment for required file format.
 *
 * magic_square - the magic square to write to a file
 * filename - the name of the output file
 */
void fileOutputMagicSquare(MagicSquare *magic_square, char *filename) {
	//Open file for writing
	FILE *fp = fopen(filename, "w");
	
	//Check if file was successfully opened
	if (fp == NULL) {
		printf("Can't open file for writing.\n");
		exit(1);
	}
	
	//Write magic square contents to file
	fprintf(fp, "%i\n", magic_square->size);
	for (int i = 0; i < magic_square->size; i++) {
		if (i != 0) {
			fprintf(fp, "\n");
		}

		for (int j = 0; j < magic_square->size; j++) {
			fprintf(fp, "%i", *( *( (magic_square->magic_square) + i) + j) );
			
			if (j != ( (magic_square->size) - 1 )) {
				fprintf(fp, ",");
			}
		}
	}
	
	fprintf(fp, "\n");  
	
	//Close file and check for errors
	if ( fclose(fp) == EOF ) {
		printf("fclose error\n");
		exit(1);
	}
}



/* TODO:
 * Calls other functions to generate a magic square 
 * of the user-specified size and outputs the
 * created square to the output filename.
 * 
 * Checks for proper argument count, expects  
 * file name to be written to.
 */
int main(int argc, char **argv) {
	// TODO: Check input arguments to get output filename
	if (argc != 2) {
		printf("Usage: ./my_magic_square <output_filename>\n");
		exit(1);
	}
	
	//Extracts filename passed in from CLA
	char *filename = *(argv + 1);
	
	// TODO: Get magic square's size from user
	int size = getSize();	

	// TODO: Generate a magic square by correctly interpreting 
	//       the algorithm(s) in the write-up or by writing on your own.  
	//       You must confirm that your program produces a 
	//       valid Magic Square. See the provided Wikipedia page link for
    	//       description.
	
	//Initializes a MagicSquare pointer to return value of function
	MagicSquare *magicSquare = generateMagicSquare(size);

	// TODO: Output the magic square
	fileOutputMagicSquare(magicSquare, filename);

	//Frees memory on heap for all int *magic_square 
	for (int i = 0; i < (magicSquare->size); i++) {
		free( *( (magicSquare->magic_square) + i) );
	}
	
	//Frees memory on heap for int **magic_square
	free(magicSquare->magic_square);

	//Frees memory on heap for MagicSquare *magicSquare
	free(magicSquare);

	return 0;
} 

// 202501


