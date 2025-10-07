// Main File:		p3Heap.c
// This File:		p3Heap.c
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
// AI chats:		none
///////////////////////////////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////
//
// Copyright 2020-2025 Deb Deppeler based on work by Jim Skrentny
// Posting or sharing this file is prohibited, including any changes.
// Used with permission, SPRING 2025, Dr. Hina Mahmood
//
/////////////////////////////////////////////////////////////////////////////

#include <unistd.h>
#include <stdio.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <sys/mman.h>
#include <fcntl.h>
#include <string.h>
#include "p3Heap.h"

/*
 * This structure serves as the header for each allocated and free block.
 * It also serves as the footer for each free block.
 */
typedef struct blockHeader {           

    /*
     * 1) The size of each heap block must be a multiple of 8
     * 2) Heap blocks have blockHeaders that contain size and status bits
     * 3) Free heap blocks contain a footer, but we can use the blockHeader 
     *
     * All heap blocks have a blockHeader with size and status
     * Free heap blocks have a blockHeader as its footer with size only
     *
     * Status is stored using the two least significant bits
     *   Bit0 => least significant bit, last bit
     *   Bit0 == 0 => free block
     *   Bit0 == 1 => allocated block
     *
     *   Bit1 => second last bit 
     *   Bit1 == 0 => previous block is free
     *   Bit1 == 1 => previous block is allocated
     * 
     * Start Heap: 
     *  The blockHeader for the first block of heap is after skipping 4 bytes.
     *  This ensures alignment requirements can be met.
     * 
     * End Mark: 
     *  The end of the available memory is indicated using a size_status of 1.
     * 
     * Examples:
     * 
     * 1. Allocated block of size 24 bytes:
     *    Allocated Block Header:
     *      If the previous block is free      p-bit=0 size_status would be 25
     *      If the previous block is allocated p-bit=1 size_status would be 27
     * 
     * 2. Free block of size 24 bytes:
     *    Free Block Header:
     *      If the previous block is free      p-bit=0 size_status would be 24
     *      If the previous block is allocated p-bit=1 size_status would be 26
     *    Free Block Footer:
     *      size_status should be 24
     */
    int size_status;

} blockHeader;         

/* Global variable - DO NOT CHANGE NAME or TYPE. 
 * It must point to the first block in the heap and is set by init_heap()
 * i.e., the block at the lowest address.
 */
blockHeader *heap_start = NULL;     

/* Size of heap allocation padded to round to the nearest page size.
 */
int alloc_size;


/* 
 * Function for allocating 'size' bytes of heap memory.
 * Argument size: requested size for the payload
 * Returns address of allocated block (payload) on success.
 * Returns NULL on failure.
 *
 * This function must:
 * - Check size - Return NULL if size < 1 
 * - Determine block size rounding up to a multiple of 8 
 *   and possibly add padding as a result.
 *
 * - Use BEST-FIT PLACEMENT POLICY to chose a free block
 *
 * - If the BEST-FIT block that is found is exact size match
 *   - 1. Update all heap blocks as needed for any affected blocks
 *   - 2. Return the address of the allocated block payload
 *
 * - If the BEST-FIT block that is found is large enough to split 
 *   - 1. SPLIT the free block into two valid heap blocks:
 *         1. an allocated block
 *         2. a free block
 *         NOTE: both blocks must meet heap block requirements 
 *       - Update all heap block header(s) and footer(s) 
 *              as needed for any affected blocks.
 *   - 2. Return the address of the allocated block payload
 *
 *   Return NULL if unable to find and allocate block of required size
 *
 * Note: payload address that is returned is NOT the address of the
 *       block header. It is the address of the start of the 
 *       available memory for the requester.
 *
 * Tips: Be careful with pointer arithmetic and scale factors.
 */
void* alloc(int size) { 
	//Check if allocation size is valid    
	if (size < 1) {
		return NULL;
	}
	
	int blockSize = size + sizeof(blockHeader);	//increase blockSize by 4 bytes - designated for header
	
	//Ensure blockSize is a multiple of 8 bytes
	if (blockSize % (2*sizeof(blockHeader)) == 0) {
	}
	else {
		blockSize += (2*sizeof(blockHeader)) - (blockSize % (2*sizeof(blockHeader)) ) ;
	}
	
	blockSize /= sizeof(blockHeader);	  //Divide by 4 because each increment of type blockHeader* stores 4 bytes
	
	blockHeader *newBlock = NULL;	//newBlock to be allocated
	blockHeader *heapPointer = heap_start;	//Copy of heap_start designated to traverse heap
	
	//Best-fit placement policy allocation for newBlock
	while ( (heapPointer->size_status) != 1) {	
		//Remove p-bit and a-bit values from size_status, divide by 4 to determine size in terms of blockHeaders
		int currentBlockSize = ( (heapPointer->size_status) - (heapPointer->size_status % (2 * sizeof(blockHeader)) ) ) / sizeof(blockHeader);
		
		//Must be divisible by 2 for block to be free
		if ( ( (heapPointer->size_status) % 2 == 0 ) && (currentBlockSize >= blockSize) ) {
			
			//Best-fit block found, break from loop
			if (currentBlockSize == blockSize) {
				newBlock = heapPointer;
				break;			
			}
			
			//In case newBlock is still NULL, apply first-fit policy and continue traversing heap
			else if (newBlock == NULL) {
				newBlock = heapPointer;
			}

			//In case newBlock has been allocated, check if currentBlock is a better fit
			else if ( (currentBlockSize - blockSize) > 0)  {
				int newBlockSize =  ( (newBlock->size_status) - (newBlock->size_status % (2 * sizeof(blockHeader)) ) ) / sizeof(blockHeader);
	
				if ( (currentBlockSize - blockSize) < (newBlockSize - blockSize) ) {
					newBlock = heapPointer;
				}
			}
		}
		
		//Move forward in heap by currentBlockSize blockHeaders, or 4*currentBlockSize bytes
		heapPointer += currentBlockSize;
	}
	
	//If no space on heap for allocation, return NULL	
	if (newBlock == NULL) {
		return NULL;
	}		
	
	//Calculation for newBlockSize in blockHeaders
	int newBlockSize = ( (newBlock->size_status) - (newBlock->size_status % (2*sizeof(blockHeader)) ) ) / sizeof(blockHeader);
	
	//Check if splitting policy is met
	if ( (newBlockSize - blockSize)  >= 2)  {
		int splitSize = newBlockSize - blockSize;
		
		splitSize -= splitSize % 2;

		newBlock->size_status -= (splitSize*sizeof(blockHeader));
		newBlockSize = ( (newBlock->size_status) - (newBlock->size_status % (2*sizeof(blockHeader)) ) ) / sizeof(blockHeader);

		//Create splitBlock Header
		blockHeader *splitBlock = newBlock + newBlockSize;
		splitBlock->size_status = (splitSize*sizeof(blockHeader)) + 2;
		
		//Create splitBlock Footer
		blockHeader *splitBlockFooter = splitBlock + splitSize - 1;
		splitBlockFooter->size_status = (splitSize*sizeof(blockHeader));
	}
	
	//Add 1 to newBlock size status for a-bit
	newBlock->size_status++;
	//Point to payload
	newBlock += 1;

	//Increment p-bit of next block if not already active
	if ( ( (newBlock+newBlockSize)->size_status % (2*sizeof(blockHeader))  ) <= 1) {
		(newBlock+newBlockSize)->size_status += 2;
	}
	
	return newBlock;
} 

/* 
 * Function for freeing up a previously allocated block.
 * Argument ptr: address of the block to be freed up.
 * Returns 0 on success.
 * Returns -1 on failure.
 * This function should:
 * - Return -1 if ptr is NULL.
 * - Return -1 if ptr is not a multiple of 8.
 * - Return -1 if ptr is outside of the heap space.
 * - Return -1 if ptr block is already freed.
 * - Update header(s) and footer as needed.
 *
 * If free results in two or more adjacent free blocks,
 * they will be immediately coalesced into one larger free block.
 * so free blocks require a footer (blockHeader works) to store the size
 *
 * TIP: work on getting immediate coalescing to work after your code 
 *      can pass the tests in partA and partB of tests/ directory.
 *      Submit code that passes partA and partB to Canvas before continuing.
 */                    
int free_block(void *ptr) {    
	//Checks if ptr is NULL		
	if (ptr == NULL) {
		return -1;
	}

	//Checks if ptr is addressed correctly
	if ( ( ((long)ptr) % (2*sizeof(blockHeader)) ) != 0) {
		return -1;
	}

	//Assigns new blockHeader to header address of ptr payload
	blockHeader *ptrBlock = ( (blockHeader *)ptr ) - 1;

	//Determine p-bit and a-bit sum of ptrBlock
	int paBits = (ptrBlock->size_status) % (2*sizeof(blockHeader));
	
	//Search for address of end of available memory
	blockHeader *end_mark = heap_start;
	while (1 == 1) {
		if (end_mark->size_status == 1) {
			break;
		}
		int currentBlockSize = ( (end_mark->size_status) - (end_mark->size_status % (2*sizeof(blockHeader)) ) ) / sizeof(blockHeader);
		end_mark += currentBlockSize;
	}

	//Checks if ptrBlock is within heap space
	if ( ptrBlock < heap_start || ptrBlock > end_mark) {
		return -1;
	}

	//Checks if ptrBlock is already free
	if ( ptrBlock->size_status % 2 == 0) {
		return -1;
	}
	
	//Assign two blockHeaders to current ptrBlock address and traverse forwards and 
	//backwards to find maximum free block length
	blockHeader *freeStart = ptrBlock;
	blockHeader *freeEnd = ptrBlock;
	
	//Size of current ptrBlock in units of blockHeaders (4 bytes each)
	int freeSize = ( (ptrBlock->size_status) - paBits) / sizeof(blockHeader);
	
	//Infinite loop breaks when allocated block or beginning of heap found via backwards traversal
	while (1 == 1) {
		//Check p-bit of current block, break if p-bit is 1
		if ( ( ( (freeStart->size_status) % (2*sizeof(blockHeader)) ) >= 2 ) || (freeStart == heap_start) ) {
			break;
		}
		
		//P-bit of current block = 0; Increment freeSize then move freeStart backwards
		freeSize += ( (freeStart-1)->size_status ) / sizeof(blockHeader);
		freeStart -= ( (freeStart-1)->size_status ) / sizeof(blockHeader);
	}

	//Infinite loop breaks when allocated block or end of heap found via forwards traversal
	while (1 == 1) {
		//Calculate current block size in units of blockHeaders
		int currentBlockSize = ( (freeEnd->size_status) - (freeEnd->size_status % (2*sizeof(blockHeader)) ) ) / sizeof(blockHeader);
		
		//Check if next block is free, break if allocated
		if ( ( (freeEnd + currentBlockSize)->size_status % 2 ) != 0) {
			break;
		}

		//Increment freeEnd and freeSize in event next block is free
		freeEnd += currentBlockSize;
		int nextBlockSize = ( (freeEnd->size_status) - (freeEnd->size_status % (2*sizeof(blockHeader)) ) ) / sizeof(blockHeader);
		freeSize += nextBlockSize;
	}

	//Assign new block size status; add 2 for p-bit unless block starts at beginning of heap
	if (freeStart == heap_start) {
		freeStart->size_status = freeSize*4;
	}
	else {
		freeStart->size_status = (freeSize*4) + 2;
	}

	//Create footer for new freeBlock and assign size status
	blockHeader *freeFooter = freeStart + (freeSize - 1);
	freeFooter->size_status =  freeSize*4;

	//Decrement p-bit for next block unless next block is the end of the heap
	if ( (freeFooter+1)->size_status == 1) {
	}
	else if ( ( ((freeFooter+1)->size_status) % 8) >= 2 ) {
		(freeFooter+1)->size_status -= 2;
	}
	
	//Successfully freed block, return 0
	return 0;
} 


/* 
 * Initializes the memory allocator.
 * Called ONLY once by a program.
 * Argument sizeOfRegion: the size of the heap space to be allocated.
 * Returns 0 on success.
 * Returns -1 on failure.
 */                    
int init_heap(int sizeOfRegion) {    

    static int allocated_once = 0; //prevent multiple myInit calls

    int   pagesize; // page size
    int   padsize;  // size of padding when heap size is not a multiple of page size
    void* mmap_ptr; // pointer to memory mapped area
    int   fd;

    blockHeader* end_mark;

    if (0 != allocated_once) {
        fprintf(stderr, 
                "Error:mem.c: InitHeap has allocated space during a previous call\n");
        return -1;
    }

    if (sizeOfRegion <= 0) {
        fprintf(stderr, "Error:mem.c: Requested block size is not positive\n");
        return -1;
    }


    // Get the pagesize from O.S. 
    pagesize = getpagesize();

    // Calculate padsize, as padding is required to round up sizeOfRegion 
    // to a multiple of pagesize
    padsize = sizeOfRegion % pagesize;
    padsize = (pagesize - padsize) % pagesize;

    alloc_size = sizeOfRegion + padsize;

    // Using mmap to allocate memory
    fd = open("/dev/zero", O_RDWR);
    if (-1 == fd) {
        fprintf(stderr, "Error:mem.c: Cannot open /dev/zero\n");
        return -1;
    }
    mmap_ptr = mmap(NULL, alloc_size, PROT_READ | PROT_WRITE, MAP_PRIVATE, fd, 0);
    if (MAP_FAILED == mmap_ptr) {
        fprintf(stderr, "Error:mem.c: mmap cannot allocate space\n");
        allocated_once = 0;
        return -1;
    }

    allocated_once = 1;

    // for double word alignment and end mark
    alloc_size -= 8;

    // Initially there is only one big free block in the heap.
    // Skip first 4 bytes for double word alignment requirement.
    heap_start = (blockHeader*) mmap_ptr + 1;

    // Set the end mark
    end_mark = (blockHeader*)((void*)heap_start + alloc_size);
    end_mark->size_status = 1;

    // Set size in header
    heap_start->size_status = alloc_size;

    // Set p-bit as allocated in header
    // Note a-bit left at 0 for free
    heap_start->size_status += 2;

    // Set the footer
    blockHeader *footer = (blockHeader*) ((void*)heap_start + alloc_size - 4);
    footer->size_status = alloc_size;

    return 0;
} 

/* STUDENTS MAY EDIT THIS FUNCTION, but do not change function header.
 * TIP: Review this implementation to see one way to traverse through
 *      the blocks in the heap.
 *
 * Can be used for DEBUGGING to help you visualize your heap structure.
 * It traverses heap blocks and prints info about each block found.
 * 
 * Prints out a list of all the blocks including this information:
 * No.      : serial number of the block 
 * Status   : free/used (allocated)
 * Prev     : status of previous block free/used (allocated)
 * t_Begin  : address of the first byte in the block (where the header starts) 
 * t_End    : address of the last byte in the block 
 * t_Size   : size of the block as stored in the block header
 */                     
void disp_heap() {     

    int    counter;
    char   status[6];
    char   p_status[6];
    char * t_begin = NULL;
    char * t_end   = NULL;
    int    t_size;

    blockHeader *current = heap_start;
    counter = 1;

    int used_size =  0;
    int free_size =  0;
    int is_used   = -1;

    fprintf(stdout, 
            "********************************** HEAP: Block List ****************************\n");
    fprintf(stdout, "No.\tStatus\tPrev\tt_Begin\t\tt_End\t\tt_Size\n");
    fprintf(stdout, 
            "--------------------------------------------------------------------------------\n");

    while (current->size_status != 1) {
        t_begin = (char*)current;
        t_size = current->size_status;

        if (t_size & 1) {
            // LSB = 1 => used block
            strcpy(status, "alloc");
            is_used = 1;
            t_size = t_size - 1;
        } else {
            strcpy(status, "FREE ");
            is_used = 0;
        }

        if (t_size & 2) {
            strcpy(p_status, "alloc");
            t_size = t_size - 2;
        } else {
            strcpy(p_status, "FREE ");
        }

        if (is_used) 
            used_size += t_size;
        else 
            free_size += t_size;

        t_end = t_begin + t_size - 1;

        fprintf(stdout, "%d\t%s\t%s\t0x%08lx\t0x%08lx\t%4i\n", counter, status, 
                p_status, (unsigned long int)t_begin, (unsigned long int)t_end, t_size);

        current = (blockHeader*)((char*)current + t_size);
        counter = counter + 1;
    }

    fprintf(stdout, 
            "--------------------------------------------------------------------------------\n");
    fprintf(stdout, 
            "********************************************************************************\n");
    fprintf(stdout, "Total used size = %4d\n", used_size);
    fprintf(stdout, "Total free size = %4d\n", free_size);
    fprintf(stdout, "Total size      = %4d\n", used_size + free_size);
    fprintf(stdout, 
            "********************************************************************************\n");
    fflush(stdout);

    return;  
} 


//		p3Heap.c		END OF FILE                    
                                       
