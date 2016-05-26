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
		super("Ye Olde Dice Roller");
			DiePanel dP = new DiePanel();
			add(dP);
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setResizable(false); // makes the frame not resizable 
			setSize(1250, 300); // sets the frame to the preferred size 
			setVisible(true); // makes the frame visible
			
	}
	public static void main(String[] args) {
		new FinalProjectDiceMain();
		
		
		/*
		 * *GOALS*
		 * To be able to roll all of the different kinds of dice (D20, D4, D6, D8, D10, D12)- check
		 * Have a text area for modifiers- check
		 * Have a text area for the number of dice rolled- check
		 * Selection for common dice rolls??? (Initiative, attack, saving throws)- prob not gonna happen, the number of dice works just the same honestly
		 * dice roll sound - check
		 * nat 1 sound - check
		 * nat 20 sound - check
		 */
		
		
	}

}
