/*
 * This class contains all the necessary fields and member
 * functions required to simulate the rudimentary queue
 * simulator. 
 * This class is used by the main class which is in Simulate
 * class. All the operations are done in this class. The 
 * operations include : 
 * 		1. Initializing the entry queue
 * 		2. Adding the requests to the counter
 * 		3. Getting the goods from the counter
 * 		4. Adding to the exit queue after completing the transaction
 * 		5. Removing from the exit queue
 * Some values can be changed depending on the situation
 * are : 
 * 		1. Number of counters
 * 		2. Goods Per counter
 * 		3. Number of requests
 * The minimum and maximum value for generating the random number can be 
 * changed to any other value required.
 */
import java.util.LinkedList;
import java.util.Queue;

public class QueueSim {
	Queue<ReqTime> ReqQue;								// Entry Queue
	Queue<ReqTime> ExitQue;								// Exit Queue
	int no_of_counters;									// Initialization can be different
	Counter[] counters;									// Array for keeping track of each counter
	int GoodsPerCounter;								// Initialization can be different
	int no_of_requests;									// ,,
	int elements_added_exit;							// To keep track of elements added to exit queue per time interval
	int elements_removed_exit;							// To keep track of elements removed from exit queue per time interval(can be 0 or 1 only)
	QueueSim()											// Constructor for initializing the required variables
	{
		no_of_counters = 10;							// Initializing value		
		GoodsPerCounter = 30;							// ,,
		counters = new Counter[no_of_counters];
		ReqQue = new LinkedList<ReqTime>();				// Storing of Queue is done in the form of Linked List
		ExitQue = new LinkedList<ReqTime>();			// ,,
		for(int i = 0; i < no_of_counters; i++)			// Initializing each counter with a particular number of goods
		{
			counters[i] = new Counter();
			counters[i].goods = GoodsPerCounter;
			counters[i].prev_goods = GoodsPerCounter;
		}
	}
	void createRequestQueue()							// Function to initialize the entry queue
	{
		no_of_requests = 50;
		int min = 5;
		int max = 10;
		RandomGen random = new RandomGen(min,max);		// RandomGen class is used for generating random numbers
														// within a range [min,max]. Can be replaced by any other
														// user-defined module.
		for(int i = 0; i < no_of_requests; i++)
		{
			int GenNo = random.generate();				// The function which generates the random number
			ReqTime t = new ReqTime(GenNo,i+1);			// An object for storing the request time
			ReqQue.offer(t);							// Add the object into the queue
		}
	}
	boolean addToCounters()								// Function to add the objects from the entry queue
	{													// to the counter if the counter is free.
		int i = 0;										// i represents the counter number
		for(; i < no_of_counters; i++)
		{												// Gets inside the loop only if it is a valid counter
														// i.e if i is less than the number of counters
			if(ReqQue.peek() == null)
			{											// Invariant : The entry queue is empty
//				System.out.println("No more requests..");
				return false;
			}
			if(counters[i].free)
			{											// Invariant : The counter is free
				ReqTime req = ReqQue.remove();			// Remove the request from the queue and give it to the counter
				counters[i].request = req;
				counters[i].free = false;
				counters[i].prev_goods = counters[i].goods;
			}
		}
		return true;
	}
	boolean getGoods()									// Function to get the goods from the respective counters
	{
		boolean flag = false;							// flag becomes true if any one counter is not closed
		int i = 0;										// i - counter number
		elements_added_exit = 0;
		for(; i < no_of_counters; i++)
		{												// Gets inside the loop only if it is a valid counter
														// i.e if i is less than the number of counters
			if(!counters[i].closed)
			{											// Invariant : one of the counter is not closed
				flag = true;
			}
			if(counters[i].free == false && counters[i].closed == false)
			{											// Invariant : The counter is busy (neither free nor closed)
				counters[i].goods--;
				counters[i].request.time--;
				if(counters[i].request.time == 0)
				{										// Invariant : The request has been satisfied
					counters[i].free = true;
					if(counters[i].goods == 0)
						counters[i].closed = true;		// Invariant : The request has been satisfied and the goods in the counter gets exhausted
					counters[i].request.time = counters[i].prev_goods - counters[i].goods;
					elements_added_exit++;				// Keeping track of number of elements added to exit queue
					counters[i].request.satisfied = true;
					ExitQue.offer(counters[i].request);	// Feed it to the exit queue
				}
				else if(counters[i].goods == 0)
				{										// Invariant : Goods of a particular counter gets exhausted
					counters[i].closed = true;
					counters[i].free = false;
					counters[i].request.time = counters[i].prev_goods;
					elements_added_exit++;
					counters[i].request.satisfied = false;
					ExitQue.offer(counters[i].request);	// Feed it to the exit queue :P
				} 
			}
		}
		return flag;
	}
	void exitSim()										// Function to simulate the exit queue
	{
		elements_removed_exit = 0;
		if(ExitQue.peek() != null)	
		{												// Invariant : Exit queue is not empty
			ExitQue.peek().time--;
			if(ExitQue.peek().time == 0)
			{											// Invariant : The request has spent his time at the queue
				elements_removed_exit = 1;
				ExitQue.remove();						// Remove from the exit queue
//				System.out.println("element moving out of exit queue..");
			}
		}
	}
	void emptyExit()									// Function to empty the exit queue
	{
		while(ExitQue.peek() != null)
		{												// Invariant : Exit queue is not empty
			ExitQue.remove();
		}
	}
	boolean free()										// Function to check if all the counters are free
	{
		boolean f = true;
		for(int i = 0; i < no_of_counters; i++)
			if(!counters[i].free)
				f = false;								// f is made false even if one of the counters is not free
		return f;
	}
}
