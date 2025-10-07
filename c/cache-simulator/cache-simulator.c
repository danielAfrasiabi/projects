// Main File:		csim.c
// This File:		csim.c
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
// AI chats:		none
/////////////////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////////////////////
// Copyright 2013,2019-2025
// Posting or sharing this file is prohibited, including any changes/additions.
// Used with permission for Spring 2025
////////////////////////////////////////////////////////////////////////////////

/**
 * csim.c:  
 * Simulate the contents of a cache with given configuration and 
 * count the number of hits, misses, and evictions for a given 
 * sequence of memory accesses for a program.
 *
 * If you want to create your own memory access traces, you can 
 * use valgrind to output traces that this simulator can analyze.
 *
 * Which replacement policy is implemented? Least Recently Used Replacement Policy
 *
 * Implementation and assumptions:
 *  1. (L) load or (S) store cause at most one cache miss and a possible eviction.
 *  2. (I) Instruction loads are ignored.
 *  3. (M) Data modify is treated as a load followed by a store to the same
 *     address. Hence, an (M) operation can result in two cache hits, 
 *     or a miss and a hit plus a possible eviction.
 *
 */  

#include <getopt.h>
#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <assert.h>
#include <math.h>
#include <limits.h>
#include <string.h>
#include <errno.h>
#include <stdbool.h>

/******************************************************************************/
/* DO NOT MODIFY THESE VARIABLE NAMES and TYPES                               */
/* DO UPDATE THEIR VALUES AS NEEDED BY YOUR CSIM                              */

//Globals set by command line args.
int b = 0; //number of (b) bits
int s = 0; //number of (s) bits
int E = 0; //number of lines per set

//Globals derived from command line args.
int B; //block size in bytes: B = 2^b
int S; //number of sets: S = 2^s

//Global counters to track cache statistics in access_data().
int hit_cnt = 0;
int miss_cnt = 0;
int evict_cnt = 0;

//Global to control trace output
int verbosity = 0; //print trace if set
/******************************************************************************/


// Type mem_addr_t: stores addresses or address masks.
typedef unsigned long long int mem_addr_t;

// Type cache_line_t: stores "overhead" (v-bit and tag) for a cache line
// The cache block's data is not needed or stored for this simulator.
typedef struct cache_line {                    
    char valid;
    mem_addr_t tag;
	int counter;
} cache_line_t;

// Type cache_set_t: Stores a pointer to the first cache line in a set.
// Note: Each set is a pointer to a heap array of one or more cache lines.
typedef cache_line_t* cache_set_t;

// Type cache_t: Stores a pointer to the first set in a cache
// Note: The cache is a pointer to a heap array of one or more sets.
typedef cache_set_t* cache_t;

// Create the cache we are simulating. 
// Note: The cache is a pointer to a heap array of one or more sets.
cache_t cache;  

/* 
 * init_cache:
 * Allocates the data structure for a cache with S sets and E lines per set.
 * Initializes all valid bits and tags with 0s.
 */                    
void init_cache() {
	B = (int) pow(2, b);	//Correctly initialize byte offset
	S = (int) pow(2, s);	//Correctly initialize # of cache sets

	//Allocate memory for S cache sets	
	cache = (cache_t) malloc(S * sizeof(cache_set_t) ); 
	
	//Check if memory for cache successfully allocated
	if (cache == NULL) {
		exit(1);
	}
	
	//Allocate E cache lines of memory for each cache set dereferenced by *(cache + i)
	for (int i = 0; i < S; i++) {
		*(cache + i) = (cache_set_t) malloc(E * sizeof(cache_line_t));

		//Check if memory for cache set successfully allocated
		if ( *(cache + i) == NULL) {
			exit(1);
		}

		//Initialize all valid bits and tags with 0s
		for (int j = 0; j < E; j++) {
			(*(cache + i) + j)->valid = 0;
			(*(cache + i) + j)->tag = 0; 
			(*(cache + i) + j)->counter = 0;
		}
	} 
}


/*
 * free_cache:
 * Frees all heap allocated memory used by the cache.
 */                    
void free_cache() {
	//Free memory allocated to each cache set
	for (int i = 0; i < S; i++) {
		free( *(cache + i) );
	}

	//Free cache
	free(cache);
	
	//Set cache to NULL to prevent future errors
	cache = NULL;            
}


/*  
 * access_data:
 * Simulates data access at given "addr" memory address in the cache.
 *
 * If block containing addr is already in cache, increment hit_cnt
 * If block containing addr is not in cache, 
 *    cache it (set tag and valid), increment miss_cnt
 * If a block in a cache line is evicted, increment evict_cnt
 * Implements least recently used replacement policy
 */                    
void access_data(mem_addr_t addr) {
	//Determine set number
	int setAnder = 0;
	for (int i = 0; i < s; i++) {
		setAnder += (int) pow(2, (i+b) );
	}

	int setIndex = addr & setAnder;
	int setNumber = setIndex >> b;	//Shift right b bits to isolate set number

	//Determine tag number
	int tagNumber = addr >> (b+s);

	//Determine maximum counter value for given set
	int	counterMax = 0;
	for (int i = 0; i < E; i++) {
		cache_line_t currentLine = *( *(cache + setNumber) + i);
			
		if (currentLine.counter > counterMax) {
			counterMax = currentLine.counter;
		}	
	}

	//Search cache to see if block is already in cache
	for (int i = 0; i < E; i++) {
		cache_line_t currentLine = *( *(cache + setNumber) + i);
		
		if (currentLine.valid == 1 && currentLine.tag == tagNumber) {
			hit_cnt++;	//Block found in cache, increment hit_cnt		
			
			//Update counter value in accordance with LRU policy
			(*(cache + setNumber) + i)->counter = counterMax + 1;
			printf("hit ");
			
			return;
		}
	}	

	//Block not found in cache, increment miss_cnt
	miss_cnt++;
	printf("miss ");

	//Search cache set for empty line to place block
	for (int i = 0; i < E; i++) {	
		if ( (*(cache + setNumber) + i)->valid == 0) {
			//Empty line found, update all struct members
			(*(cache + setNumber) + i)->tag = tagNumber;
			(*(cache + setNumber) + i)->valid = 1;

			//Update counter value in accordance with LRU policy
			(*(cache + setNumber) + i)->counter = counterMax + 1;
			
			return;
		} 
	}

	//All cache lines full, find block to evict
	int evictJ = 0;
	for (int j = 0;	j < E; j++) {
		cache_line_t currentLine = *( *(cache + setNumber) + j);
		cache_line_t evictLine = *( *(cache + setNumber) + evictJ);
			
		//Smaller counter means that given line was less recently used
		if (currentLine.counter < evictLine.counter) {
			evictJ = j;
		}
	}

	//Evict block, increment evict_cnt
	(*(cache + setNumber) + evictJ)->tag = tagNumber;
	evict_cnt++;
	printf("eviction ");
	
	//Update counter value in accordance with LRU policy
	(*(cache + setNumber) + evictJ)->counter = counterMax + 1;

}		


/* 
 * replay_trace:
 * Replays the given trace file against the cache.

 Example: subset of trace, shows type of access, address, size of access

 L 7ff0005b8,4
 S 7feff03ac,4
 M 7fefe059c,4
 
 * Reads the input trace file line by line.
 * Extracts the type of each memory access : L/S/M
 * TRANSLATE "L" as a Load i.e. one memory access
 * TRANSLATE "S" as a Store i.e. one memory access
 * TRANSLATE "M" as a Modify which is a load followed by a store, 2 mem accesses 
 */                    
void replay_trace(char* trace_fn) {           
    char buf[1000];  
    mem_addr_t addr = 0;
    unsigned int len = 0;
    FILE* trace_fp = fopen(trace_fn, "r"); 

    if (!trace_fp) { 
        fprintf(stderr, "%s: %s\n", trace_fn, strerror(errno));
        exit(1);   
    }

    while (fgets(buf, 1000, trace_fp) != NULL) { 
        if (buf[1] == 'S' || buf[1] == 'L' || buf[1] == 'M') {
            sscanf(buf+3, "%llx,%u", &addr, &len);

            if (verbosity)
                printf("%c %llx,%u ", buf[1], addr, len);

				//Load and store operatios each require only one memory access
				if (buf[1] == 'L'|| buf[1] == 'S') {
					access_data(addr);
				}

				//Modify operation requires two memory accesses, a load followed by a store
				else {
					access_data(addr);
					access_data(addr);
				}
					
            if (verbosity)
                printf("\n");
        }
    }

    fclose(trace_fp);
}  


/*
 * print_usage:
 * Print information on how to use csim to standard output.
 */                    
void print_usage(char* argv[]) {                 
    printf("Usage: %s [-hv] -s <num> -E <num> -b <num> -t <file>\n", argv[0]);
    printf("Options:\n");
    printf("  -h         Print this help message.\n");
    printf("  -v         Verbose flag.\n");
    printf("  -s <num>   Number of s bits for set index.\n");
    printf("  -E <num>   Number of lines per set.\n");
    printf("  -b <num>   Number of b bits for word and byte offsets.\n");
    printf("  -t <file>  Trace file.\n");
    printf("\nExamples:\n");
    printf("  linux>  %s -s 4 -E 1 -b 4 -t traces/yi.trace\n", argv[0]);
    printf("  linux>  %s -v -s 8 -E 2 -b 4 -t traces/yi.trace\n", argv[0]);
    exit(0);
}  


/*
 * print_summary:
 * Prints a summary of the cache simulation statistics to a file.
 */                    
void print_summary(int hits, int misses, int evictions) {                
    printf("hits:%d misses:%d evictions:%d\n", hits, misses, evictions);
    FILE* output_fp = fopen(".csim_results", "w");
    assert(output_fp);
    fprintf(output_fp, "%d %d %d\n", hits, misses, evictions);
    fclose(output_fp);
}  


/*
 * main:
 * parses command line args, 
 * makes the cache, 
 * replays the memory accesses, 
 * frees the cache and 
 * prints the summary statistics.  
 */                    
int main(int argc, char* argv[]) {                      
    char* trace_file = NULL;
    char c;

    // Parse the command line arguments: -h, -v, -s, -E, -b, -t 
    while ((c = getopt(argc, argv, "s:E:b:t:vh")) != -1) {
        switch (c) {
            case 'b':
                b = atoi(optarg);
                break;
            case 'E':
                E = atoi(optarg);
                break;
            case 'h':
                print_usage(argv);
                exit(0);
            case 's':
                s = atoi(optarg);
                break;
            case 't':
                trace_file = optarg;
                break;
            case 'v':
                verbosity = 1;
                break;
            default:
                print_usage(argv);
                exit(1);
        }
    }

    // Make sure that all required command line args were specified.
    if (s == 0 || E == 0 || b == 0 || trace_file == NULL) {
        printf("%s: Missing required command line argument\n", argv[0]);
        print_usage(argv);
        exit(1);
    }

    // Initialize cache.
    init_cache();

    // Replay the memory access trace.
    replay_trace(trace_file);

    // Free memory allocated for cache.
    free_cache();

    // Print statistics to a file.
    // DO NOT REMOVE: This function must be called for test_csim to work.
    print_summary(hit_cnt, miss_cnt, evict_cnt);
    return 0;   
}  

//			END OF CODE (SPRING 2025)                                     

