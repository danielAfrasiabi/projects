// Main File:		check_sudoku_board.c
// This File:		check_sudoku_board.c
// Other Files:		n/a
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
//
// AI chats:		none
///////////////////////////////////////////////////////////////////////////////
// Copyright 2021-25 Deb Deppeler
// Posting or sharing this file is prohibited, including any changes/additions.
// Shared with permission: Hina Mahmood (Spring 2025)
// We have provided comments and structure for this program to help you get 
// started. Later programs will not provide the same level of commenting,
// rather you will be expected to add same level of comments to your work.
////////////////////////////////////////////////////////////////////////////////

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

char *DELIM = ",";  // commas ',' are a common delimiter character for data strings

/* TODO: implement this function
 * Returns 1 if and only if the 2D array of ints in board 
 * is in a valid Sudoku board state. Otherwise returns 0.
 *
 * DOES NOT PRODUCES ANY PRINTED OUTPUT
 * 
 * A valid row or column contains only blanks or the digits 1-size, 
 * with no duplicate digits, where size is the value 1 to 9.
 * 
 * Note: This function requires only that each row and each column are valid.
 * 
 * board: heap allocated 2D array of integers 
 *  size: number of rows and columns in the board
 */
int valid_sudoku_board(int **board, int size) {
	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			int test_rows = *(*(board+i) + j);	//accesses board[i][j] element
			if ( (test_rows > size) || (test_rows < 0) ) {
				return 0;
			}
			for (int x = 0; x < j; x++) {
			int temp = *(*(board+i) + x);		//accesses board[i][x] to assure no duplicates
				if( (test_rows == temp) && (test_rows != 0) ) {
					return 0;
				}
			}
		}
	}

	for (int i = 0; i < size; i++) {
		for (int j = 0; j < size; j++) {
			int test_columns = *(*(board+j) + i); //accesses board[j][i] element
			if ( (test_columns > size) || (test_columns < 0) ) {
				return 0;
			}
			for (int x = 0; x < j; x++) {
				int temp = *(*(board+x)+ i);	//accesses board[x][i] to assure no duplicates
				if ( (test_columns == temp) && (test_columns != 0) ) {
					return 0;
				}
			}
		}
	}

	return 1;   //all tests passed, board is valid
	}	  

/* COMPLETED (DO NOT EDIT)       
 * Read the first line of file to get the size of the board.
 * 
 * PRE-CONDITION #1: file exists
 * PRE-CONDITION #2: first line of file contains valid non-zero integer value
 *
 * fptr: file pointer for the board's input file
 * size: a pointer to an int to store the size
 *
 * POST-CONDITION: the integer whose address is passed in as size (int *) 
 * will now have the size (number of rows and cols) of the board being checked.
 */
void get_board_size(FILE *fptr, int *size) {      
	char *line = NULL;
	size_t len = 0;

	// 'man getline' to learn about <stdio.h> getline
	if ( getline(&line, &len, fptr) == -1 ) {
		printf("Error reading the input file.\n");
		free(line);
		exit(1);
	}

	char *size_chars = NULL;
	size_chars = strtok(line, DELIM); // 'man strtok' string tokenizer
	*size = atoi(size_chars);         // 'man atoi' alpha to integer
	free(line);                       // free memory allocated for line 
}


/* TODO: COMPLETE THE MAIN FUNCTION
 * This program prints "valid" (without quotes) if the input file contains
 * a valid state of a Sudoku puzzle board wrt to rows and columns only.
 * It prints "invalid" (without quotes) if the input file is not valid.
 *
 * Usage: A single CLA that is the name of a file that contains data.
 *
 * argc: the number of command line args (CLAs)
 * argv: the CLA strings, includes the program name
 *
 * Returns 0 if file exists and is readable.
 * Exit with any non-zero result if unable to open and read a given file.
 */
int main( int argc, char **argv ) {              

	// TODO: Check if number of command-line arguments is correct.
	if (argc != 2) {
		printf("Usage: ./check_sudoku_board <input_filename>\n");
		exit(1);
	}

	// Open the file 
	FILE *fp = fopen(*(argv + 1), "r");
	if (fp == NULL) {
		printf("Can't open file for reading.\n");
		exit(1);
	}

	// will store the board's size, number of rows and columns
	int size;

	// TODO: Call get_board_size to read first line of file as the board size.
	get_board_size(fp,&size);

	// TODO: Dynamically allocate a 2D array for given board size.
	// You must dyamically create an array of pointers to other arrays of ints
	int **board = (int **)calloc(size, sizeof(int*) );	//allocating size memory locations of size in														t*pointer bytes to board
	if (board == NULL) {	//check if allocation worked
		printf("Failed to allocate memory for board.\n");
		exit(1);
	}

	for (int i = 0; i < size; i++) {
		*(board + i) = (int *)calloc(size, sizeof(int) ); //allocating size memory locations of size															int bytes to size different *board pointer															s all successively addressed
		if ( (*(board + i) == NULL) ) {		//check if allocation worked
			printf("Failed to allocate memory for board.\n");
			exit(1);
		}
	}

		
	
	// Read the remaining lines of the board data file.
	// Tokenize each line and store the values in your 2D array.
	char *line = NULL;
	size_t len = 0;
	char *token = NULL;
	for (int i = 0; i < size; i++) {

		// read the line
		if (getline(&line, &len, fp) == -1) {
			printf("Error while reading line %i of the file.\n", i+2);
			exit(1);
		}

		token = strtok(line, DELIM);
		for (int j = 0; j < size; j++) {
			// TODO: Complete the line of code below
			// to initialize elements of your 2D array.
			/* ADD ARRAY ACCESS CODE HERE */
			*(*(board+i) + j)  = atoi(token); 
			token = strtok(NULL, DELIM);
		}
	}

	// TODO: Call valid_sudoku_board and print the appropriate
	//       output depending on the function's return value.
	if (valid_sudoku_board(board,size) == 0) {
		printf("invalid\n"); //invalid board
	}
	else {
		printf("valid\n");  //valid board
	}

	// TODO: Free dynamically allocated memory.
	for (int i = 0; i < size; i++) {
		free(*(board + i));  //free all memory allocations desginated to each *board pointer 
	}
	free(board); //free memory allocations designated to board

	//Close the file.
	if (fclose(fp) != 0) {
		printf("Error while closing the file.\n");
		exit(1);
	} 

	return 0;       
}       

