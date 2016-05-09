import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;

/**
 * 
 */

/**
 * @author David Herr
 *
 */
public class FinalProjectDiceMain extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2212909403301242061L;
	/**
	 * This program is a Dungeons and Dragons Dice Roll Helper (name is in the works) 
	 * The goal is to help Dungeon masters ( or players? Not sure yet) with their dice rolls
	 * @param args
	 * @return 
	 */
	private GridBagLayout layout;
	private JTextField myTextField; // variable for text box
	private JTextField modTextField; // text box for modifiers
	public static int buttonSelected = 1;
	
	public FinalProjectDiceMain(){
		super();
		
		layout = new GridBagLayout();
		 // variable for the gridbag layout
		 JButton genButton;
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 GridBagConstraints c = new GridBagConstraints(); // creates a new GridBagRestraint
		 c.fill = GridBagConstraints.BOTH; // sets c to resize components BOTH vertically and horizontally
		 c.gridwidth = GridBagConstraints.RELATIVE;
		 setLayout(layout);
		 
		    genButton = new JButton("Roll Dice");
			genButton.setFont(new Font("TimesRoman", Font.ITALIC, 12)); // sets font, makes font italicized and font size
			genButton.setActionCommand("RollDice"); // adds ActionListener to button to roll dice
			c.gridx = 10;
			c.gridy = 3;
			layout.setConstraints(genButton, c); // more button constraints
			genButton.setVisible(true);
			add(genButton); // adds button to frame
			
			myTextField = new JTextField("Please enter the number of dice you need to roll"); // creates new text field and sets text in text field
			myTextField.setEditable(true); // makes the text field not editable 
			add(myTextField);
			myTextField.addActionListener(this);
			c.gridx = 10;
			c.gridy = 1;
			layout.setConstraints(myTextField, c); // text field constraints
			
			modTextField = new JTextField("Please enter the value of your modifier for this roll");
			modTextField.setEditable(true);
			add(modTextField);
			modTextField.addActionListener(this);
			c.gridx = 10;
			c.gridy = 2;
			layout.setConstraints(modTextField, c); // text field constraints
			
			//c.anchor = GridBagConstraints.FIRST_LINE_START;
			JRadioButton D20 = new JRadioButton("D20");
	        D20.setMnemonic(KeyEvent.VK_B);
	        D20.setActionCommand("D20");
	        c.gridx = 1;
	        c.gridy = 6;
	        layout.setConstraints(D20, c); // more button constraints
			
	         JRadioButton D10  = new JRadioButton("D10");
	        D10.setMnemonic(KeyEvent.VK_B);
	        D10.setActionCommand("D10");
	        c.gridy = 4;
	        layout.setConstraints(D10, c); // more button constraints
	        
	        JRadioButton D6 = new JRadioButton("D6");
	        D6.setMnemonic(KeyEvent.VK_B);
	        D6.setActionCommand("D6");
	        c.gridy = 2;
	        layout.setConstraints(D6, c); // more button constraints
	        
	        JRadioButton D4 = new JRadioButton("D4");
	        D4.setMnemonic(KeyEvent.VK_B);
	        D4.setActionCommand("D4");
	        c.gridy = 1;
	        layout.setConstraints(D4, c); // more button constraints
	        
	        JRadioButton D8 = new JRadioButton("D8");
	        D8.setMnemonic(KeyEvent.VK_B);
	        D8.setActionCommand("D8");
	        c.gridy = 3;
	        layout.setConstraints(D8, c); // more button constraints
	        
	        JRadioButton D12 = new JRadioButton("D12");
	        D12.setMnemonic(KeyEvent.VK_B);
	        D12.setActionCommand("D12");
	        c.gridy = 5;
	        layout.setConstraints(D12, c); // more button constraints
	        
	        ButtonGroup dice = new ButtonGroup();
	        dice.add(D20);
	        dice.add(D4);
	        dice.add(D6);
	        dice.add(D8);
	        dice.add(D10);
	        dice.add(D12);
	        
	        add(D20);
	        add(D4);
	        add(D6);
	        add(D8);
	        add(D10);
	        add(D12);
	        
	        D4.addActionListener(this);
	        D6.addActionListener(this);
	        D8.addActionListener(this);
	        D10.addActionListener(this);
	        D12.addActionListener(this);
	        D20.addActionListener(this);
	        
	        D6.setSelected(true);
	        this.setResizable(false); // makes the frame resizable 
			this.setSize(500, 500); // sets the frame to the preferred size 
			this.setVisible(true); // makes the frame visible
			
	}
	public static void main(String[] args) {
		new FinalProjectDiceMain();
		
		
		/*
		 * *GOALS*
		 * To be able to roll all of the different kinds of dice (D20, D4, D6, D8, D10, D12) 
		 * Have an animation for each of the different kinds of dice
		 * Have a text area for modifiers
		 * Have a text area for the number of dice rolled
		 * Selection for common dice rolls??? (Initiative, attack, saving throws) 
		 * 
		 * Store images in array
		 * store in buffered image
		 * 
		 */
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()){
		
		case "RollDice":
			
			break;
		case "D4":
			
			break;
		case "D6":
			
			break;
		case "D8":
			
			break;
		case "D10":
			
			break;
		case "D12":
			
			break;	
		case "D20":
			
			break;
		case "default":
			
			break;
		
		
		
		
		
		}
		
	}
}
