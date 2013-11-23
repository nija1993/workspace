/*
 * This class stores a field time 
 * which represents the request time 
 * of the particular object,
 * the Customer Number and a boolean
 * variable to check whether he was 
 * satisfied or not.
 */
public class ReqTime {
	int time;
	int CustNo;
	boolean satisfied;
	ReqTime(int t, int cust){			// Constructor
		time = t;
		CustNo = cust;
	}
}
