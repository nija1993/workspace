/*
 * This class is used for displaying the state of each of the object whichever is necessary
 * The point of view is specified by the arg : 
 * 		0 - w.r.t each counters
 * 		1 - w.r.t the entry queue
 * 		2 - w.r.t the exit queue
 * 		3 - w.r.t the particular request
 */
import java.util.Iterator;

public class Display {
	QueueSim q;
	int arg;
	int CustNo;
	int PrevReqSize;													// To keep track of the size of request queue of the previous iteration
	int CurrReqSize;													// To keep track of the size of request queue of the present iteration
	int arrival = 0;	
	Display(int arg, int CustNo)										// Constructor for storing the argument value and customer number(in case of arg = 3)
	{
		this.arg = arg;
		this.CustNo = CustNo;
	}
	void display(QueueSim q, int time)									// The main display function, displays according to the argument provided
	{
		this.q = q;
		System.out.println("-------------------------------------------------");
		System.out.println("Time : " + time);							// Printing the global time
		if(arg == 0 && time != 0)										// Display w.r.t all the counters except during time = 0
		{
			System.out.println("Displaying the goods at each counter : ");
			for(int i = 0; i < q.no_of_counters; i++)					// For loop to output the state of each counter
			{
				System.out.println("Counter "+i+" : ");					// Invariant : i is a valid counter number
				System.out.println("\tNo of goods : "+q.counters[i].goods);
				if(q.counters[i].free)									// Checking if the counter is free
				{														// Invariant : counter no i is free
					System.out.println("\tRequest no : "+q.counters[i].request.CustNo+" is done");
					if(q.counters[i].closed)							// The only possible case when a counter can be free
																		// even when it is closed is if the counter closes just 
																		// when the request is satisfied
						System.out.println("\tCounter closed");
					else
						System.out.println("\tCounter free");
				}
				else if(q.counters[i].closed)							// Checking if the counter is closed
				{														// Invariant : counter i is closed
					System.out.println("\tRequest no : "+q.counters[i].request.CustNo+" is partially done");
					System.out.println("\tCounter closed");				// Since the counter is closed and it is not free
																		// the request was only partially completed
				}
				else
					System.out.println("\tCounter busy");				// Invariant : counter i is neither closed nor free 
																		// => it is busy with a particular request
			}
		}
		else if(arg == 1)												// Display w.r.t entry queue
		{
			if(time == 0)
			{
				PrevReqSize = q.no_of_requests;							// Initializing at time = 0
				CurrReqSize = q.no_of_requests;							//
			}
			else
			{
				PrevReqSize = CurrReqSize;
				CurrReqSize = q.ReqQue.size();							// Updating the previous size and the current size
				int difference = PrevReqSize - CurrReqSize;
				if(PrevReqSize == 0)									// If previous request is 0 print empty
					System.out.println("Entry queue is empty");
				else if(difference == 0)
					System.out.println("No request left the entry queue");
				else													// Printing out the change in the queue
					System.out.println(difference+" requests left the entry queue");
			}
			if(PrevReqSize != 0)										// Display the contents of the entry queue if size is not zero
			{
				System.out.println("Displaying the contents of entry queue : ");
				Iterator<ReqTime> i = q.ReqQue.iterator();
				while(i.hasNext())
					System.out.println("\t"+i.next().time);
			}
		}
		else if(arg == 2)												// Display w.r.t exit queue
		{
			int elem_added = q.elements_added_exit;
			int elem_removed = q.elements_removed_exit;
			if(q.ExitQue.size() == 0)
				System.out.println("No element in exit queue");
			else
			{
				System.out.println(elem_added+" elements added into exit queue");		// Output the elements added in the exit queue
				System.out.println(elem_removed+" elements removed from exit queue");	// Output the elements removed from the exit queue
				System.out.println("Displaying the contents of exit queue : ");			// Output the contents of the exit queue
				Iterator<ReqTime> i = q.ExitQue.iterator();
				while(i.hasNext())
					System.out.println("\t"+i.next().time);
			}
		}
		else if(arg == 3)												// Display w.r.t a particular request with number CustNo
		{
			if(CustNo > q.no_of_requests)								// In case of invalid request number
			{
				System.out.println("Invalid Customer Number");
			}
			else if(q.ReqQue.peek().CustNo <= CustNo)					// If the first number in the queue is less than the request number
			{															// it is still in the entry queue
				System.out.println("Request still in the entry queue");
			}
			else														
			{															// Invariant : Request number is valid and not in the entry queue
				for(int i = 0; i < q.no_of_counters; i++)				// Checking each counters if the request is present in any counters
				{
					if(q.counters[i].request.CustNo == CustNo)			// If it matches print the state
					{
						System.out.println("Request currently in counter number : "+i);
						return;
					}
				}
				Iterator<ReqTime> i = q.ExitQue.iterator();
				while(i.hasNext())										// Checking in the exit queue
				{
					ReqTime r = i.next();
					if(r.CustNo == CustNo)								// If the request number matches
					{
						if(arrival == 0)								// If it is the first time after arrival at the exit queue
																		// print satisfied or partially satisfied depending on the boolean variable
						{
							arrival = 1;
							if(q.ExitQue.peek().satisfied)
								System.out.println("Request was satisfied");
							else
								System.out.println("Request was partially satisfied");
						}
						System.out.println("Request currently in exit queue");
						return;
					}
				}
				System.out.println("Request out of exit queue");		// If it is not present anywhere
			}
		}
	}
}
