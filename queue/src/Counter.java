/*
 * This class is used for storing the
 * details about each counter which 
 * are :
 * 		goods 		- Current number of goods at that counter
 * 		prev_goods 	- The number of goods at the counter when the previous request was completed
 * 		free		- True if the counter is free
 * 		closed		- True if the goods reaches zero and it is closed
 * 		request		- If the counter is busy(not free and not closed), it has the object for which
 * 					  the request is being taken care of.
 */
public class Counter {
	int goods;
	int prev_goods;
	boolean free;
	boolean closed;
	ReqTime request;
	Counter()					// Constructor
	{
		free = true;
		closed = false;
	}
}
