import java.util.Random;

/**
 * @author David Herr
 *
 */
public class D12 {

	/**
	 * @param args
	 */
	
		private int sides; // Number of sides
		private int value; // The die's value

		/**
		 * The constructor performs an initial roll of the die.
		 * 
		 * @param numSides
		 *            The number of sides for this die
		 */

		public D12(int numSides) {
			sides = numSides; // sets the number of sides
			roll(); // calls the roll method to get the value of the roll
		}

		/**
		 * The roll method simulates the rolling of the die
		 */

		public void roll() {
			Random rnd = new Random(); // creates random object

			// Get a random value for the die
			value = rnd.nextInt(sides) + 1;
		}

		/**
		 * getSides method
		 * 
		 * @return The number of sides for for this dice
		 */

		public int getSides() {
			return sides; // returns the number of sides
		}

		/**
		 * getValue method
		 * 
		 * @return The value of the die
		 */

		public int getValue() {
			return value; // return the value of the dice roll
		}

	}


