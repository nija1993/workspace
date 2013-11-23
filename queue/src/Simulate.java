/*
 * This class is our main simulating class. It uses the QueueSim class
 * and its member functions to simulate the running of the queue. This 
 * ends when either all the counters are closed(which happens when it 
 * runs out of goods) or if all the counters are free but there is no
 * request.
 */
public class Simulate {
	public static void main(String args[])
	{
		QueueSim q = new QueueSim();
		q.createRequestQueue();							//Initializes the entry queue with a particular number of requests
		int time = 0;
		int ReqID = 10;
		Display d = new Display(0,ReqID);				//Creating Display class for displaying contents w.r.t f.o.v of
														//	0 - Each counter
														// 	1 - Entry queue
														// 	2 - Exit queue
														// 	3 - Particular request
		boolean AddCounter = true,GetGood = true;
		d.display(q,time);
		while(GetGood)
		{
			time++;										//Invariant here is that the value of GetGood is true
			q.exitSim();
			if(AddCounter)								
				AddCounter = q.addToCounters();			//AddCounter becomes false if the Entry Queue becomes empty
			if(GetGood)
			{
				if(!AddCounter && q.free())				//If the entryQueue is empty and all counters are free proceed
					break;								//to emptying exit queue. Even if one counter is busy, finish
														//the request
				
				GetGood = q.getGoods();					//GetGood becomes false if all the counters have closed
			}
			d.display(q,time);
		}
														//Reaches this point only if GetGood becomes false or
														//if all the counters are free and the EntryQueue is empty
		if(!GetGood)
			System.out.println("All counters closed..Emptying exit queue..");
		else
			System.out.println("All requests done..Emptying exit queue..");
		q.emptyExit();									//Empties the exit queue
	}
}
