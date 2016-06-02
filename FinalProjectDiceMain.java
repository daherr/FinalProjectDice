import javax.swing.*;


/**
 * 
 */

/**
 * @author David Herr
 *
 */
public class FinalProjectDiceMain extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2212909403301242061L;
	/**
	 * This program is a Dungeons and Dragons Dice Roll Helper otherwise known as the Ye Old Dice Roller! (Copyright pending)
	 * The goal is to help Dungeon masters and players with their dice rolls, however, it can be used in all sorts of board games to replace dice
	 * if they are ever lost, etc..
	 * @param args
	 * @return 
	 */
	public FinalProjectDiceMain(){
		super("Ye Olde Dice Roller"); //Calls the JFrame methods to create the JFrame with the title: Ye Olde Dice Roller
			DiePanel dP = new DiePanel(); // creates a new DiePanel
			add(dP); //adds the die panel to the JFrame
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // sets the screen to close on default
	        setResizable(false); // makes the frame not resizable 
			setSize(1250, 300); // sets the frame to the preferred size 
			setVisible(true); // makes the frame visible
			
	}
	public static void main(String[] args) {
		new FinalProjectDiceMain(); // creates a new JFrame for the program to run in
				
	}

}
