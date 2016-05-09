import java.util.Random;

/**
 * 
 */

/**
 * @author SJHSStudent
 *
 */
public class D8 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static class DieJava {
		private int sides; // Number of sides
		private int value; // The die's value

		/**
		 * The constructor performs an initial roll of the die.
		 * 
		 * @param numSides
		 *            The number of sides for this die
		 */

		public DieJava(int numSides) {
			sides = numSides;
			roll();
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
			return sides;
		}

		/**
		 * getValue method
		 * 
		 * @return The value of the die
		 */

		public int getValue() {
			return value;
		}

	}

}
