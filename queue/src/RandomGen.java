/*
 * This class is to generate uniformly
 * distributed random numbers within 
 * a range [min, max].
 * Can generate different random numbers 
 * depending on the values of min and max
 */
import java.util.Random;

public class RandomGen {
	Random r = new Random();							// Creating a new Random class object
	int minimum, maximum;
	RandomGen(int min, int max){						// Constructor
		minimum = min;
		maximum = max;
	}
	int value;
	int generate(){										// Function to generate the random number
		value = minimum + r.nextInt(maximum-minimum+1);	// Calculating random value
		return value;
	}
}
