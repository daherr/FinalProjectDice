import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;

/**
 * 
 */

/**
 * @author David Herr
 *
 */
public class DiePanel extends JPanel implements ActionListener {

	/**
	 * @param args
	 */
	private static final long serialVersionUID = 2212909403301242061L;
	/**
	 * This program is a Dungeons and Dragons Dice Roll Helper otherwise known as the Ye Old Dice Roller! (Copyright pending)
	 * The goal is to help Dungeon masters and players with their dice rolls, however, it can be used in all sorts of board games to replace dice
	 * if they are ever lost, etc..
	 * @param args
	 * @return 
	 */
	private JButton genButton; // creates a new button for creating all the other buttons
	private GridBagLayout layout; // variable for the GridBagLayout.
	private JTextField myTextField; // variable for text box
	private JTextField modTextField; // text box for modifiers
	public static int buttonSelected = 1; // variable for the type of die that is selected
	private JTextArea dieValue; // variable for the value of the dice rolls
	public long rollValue; // int for the value of the dice rolls
	public int numDie; // int for the number of dice being rolled
	public int modDie; // int for the value of the modifier of the dice roll
	public Dimension d; // the dimension for setting the size of the roll value text box
	private JOptionPane justInCase; // This JOptionPane is for if the value of the dice roll goes over 1000
	private BufferedImage image; // variable for the background image
	private Clip clapClip; // clip for applause (natural 20 on D20)
	private Clip sadClip; // clip for the sad trombone (natural 1 for D20)
	private Clip diceClip; // clip for rolling the dice
	public DiePanel(){
		super(); // calls the super method to build the basic panek
		
		try {
			image = ImageIO.read(new File("BackgroundImageG.jpg")); //sets the background image
		} catch (IOException e) {
			e.printStackTrace(); // just in case the background image did not load properly
		}
		
		try{
		File clap = new File("Applause Light 2-SoundBible.com-356111200.wav"); // creates the file to reference for sound one
		AudioInputStream clapStream = AudioSystem.getAudioInputStream(clap); // creates a new AudioInputStream to load the sound
		AudioFormat clapFormat = clapStream.getFormat(); // creates a new audio format
		DataLine.Info clapInfo = new DataLine.Info( Clip.class, clapFormat); // creates a variable for additional information about the file
		clapClip = (Clip) AudioSystem.getLine(clapInfo); //creates a new clip to be played
		clapClip.open(clapStream); // opens the clip to be able to be played
		
		File sad = new File("Sad_Trombone-Joe_Lamb-665429450.wav"); // creates the file to reference for sound one
		AudioInputStream sadStream = AudioSystem.getAudioInputStream(sad); // creates a new audio input stream
		AudioFormat sadFormat = sadStream.getFormat(); // creates a new audio format
		DataLine.Info sadInfo = new DataLine.Info( Clip.class, sadFormat); // creates a variable for additional information about the file
		sadClip = (Clip) AudioSystem.getLine(sadInfo); // creates the clip that will be played 
		sadClip.open(sadStream); // opens the clip so that it can be played
		
		File dice = new File("Shake And Roll Dice-SoundBible.com-591494296.wav"); // creates the file to reference for sound one
		AudioInputStream diceStream = AudioSystem.getAudioInputStream(dice); // creates a new audio input stream
		AudioFormat diceFormat = diceStream.getFormat(); // creates a new audio format
		DataLine.Info diceInfo = new DataLine.Info( Clip.class, diceFormat); // creates a variable for additional information about the file
		diceClip = (Clip) AudioSystem.getLine(diceInfo); // creates the clip that will be played 
		diceClip.open(diceStream); // opens the clip so that it can be played
		}catch(Exception e1){
			e1.printStackTrace(); // prints the stack trace just in case an error pops up
		}
		
		
		rollValue = 0; // sets rollValue to zero just in case
		layout = new GridBagLayout(); // variable for the gridbag layout
		 GridBagConstraints c = new GridBagConstraints(); // creates a new GridBagRestraint
		 c.fill = GridBagConstraints.BOTH; // sets c to resize components BOTH vertically and horizontally
		 setLayout(layout); // sets the layout of the screen to a gridbag layout
		 justInCase = new JOptionPane(); //creates a new JOptionPane for displaying values over 1000
		 
		    genButton = new JButton("Roll Dice"); // creates the button to roll the dice
			genButton.setFont(new Font("GothicE", Font.BOLD, 48)); // sets font, makes font italicized and font size
			genButton.setActionCommand("Roll Dice"); // adds ActionListener to button to roll dice 
			c.gridx = 3; // sets the column for the Roll Dice button to be in
			c.gridy = 1; // sets the row for the Roll Dice button to be in
			layout.setConstraints(genButton, c); // more button constraints
			genButton.setVisible(true); // sets the Roll Dice button to be true
		
			add(genButton); // adds button to frame
			
			myTextField = new JTextField("Please enter the number of dice"); // creates new text field and sets text in text field
			myTextField.setFont(new Font("GothicE", Font.PLAIN, 16)); // sets the font and seixe of the Text Field
			myTextField.setEditable(true); // makes the text field not editable 
			d = myTextField.getPreferredSize(); // sets the dimension of the text field to be the first preferred size
			myTextField.setPreferredSize(d); // sets the text field to be the first preferred size
			add(myTextField); // the the text field to the panel
			myTextField.addActionListener(this); // gives the text field an action listener
			c.gridx = 1; // sets the text field to be in the first column
			c.gridy = 1; // sets the text field to be in the first row
			layout.setConstraints(myTextField, c); // text field constraints
			
			modTextField = new JTextField("Please enter the value of modifier"); // creates new text field for the modifier of the dice roll
			modTextField.setFont(new Font("GothicE", Font.PLAIN, 16)); // sets the font and size of the text for the text field
			modTextField.setEditable(true); // sets the text field to be editable
			d = modTextField.getPreferredSize(); // sets the dimension of the text field to be the first preferred size
			modTextField.setPreferredSize(d); // sets the size of the modTextField to the preferred size
			add(modTextField); // adds the modTextField to the Panel
			modTextField.addActionListener(this); // adds an actionListener to the modTextField
			c.gridx = 2; //sets the text field to be in the second row
			c.gridy = 1; // sets the text field to be in the first column
			layout.setConstraints(modTextField, c); // text field constraints
			
			JRadioButton D20 = new JRadioButton("D20"); // creates a new JRadioButton called D20
	        D20.setMnemonic(KeyEvent.VK_B); // sets the mnemonic for the button
	        D20.setActionCommand("D20"); // sets the action command of the button to D20
	        D20.setOpaque(false); // sets the background of the button to be opaque
	        c.ipadx = 0; // sets the internal padding for the x axis of the buttons to zero
	        c.ipady = 40; // sets the interal padding of the y axis of the buttons to forty
	        c.gridx = 2; // sets the button to be in the second column
	        c.gridy = 3; // sets the button to be in the third row
	       
	        layout.setConstraints(D20, c); // more button constraints
			
	         JRadioButton D10  = new JRadioButton("D10"); // creates a new JRadioButton called D10
	        D10.setMnemonic(KeyEvent.VK_B); // sets the mnemonic for the button
	        D10.setActionCommand("D10"); // sets the action command for the button to D10
	        D10.setOpaque(false); // sets the background of the button to be opaque
	        c.gridy = 2; // sets the button to be in the second row
	        c.gridx = 4;// sets the button to be in the fourth column
	        layout.setConstraints(D10, c); // more button constraints
	        
	        JRadioButton D6 = new JRadioButton("D6"); // creates a new JRadioButton called D6
	        D6.setMnemonic(KeyEvent.VK_B); // sets mnemonic of the button 
	        D6.setActionCommand("D6"); // sets the action command of the button to D6
	        D6.setOpaque(false); // sets the background of the button to be opaque
	        c.gridy = 2; // sets the button to be in row two
	        c.gridx = 2; // sets the button to be in column two
	        layout.setConstraints(D6, c); // more button constraints
	        
	        JRadioButton D4 = new JRadioButton("D4"); // creates a new JRadioButton called D4
	        D4.setMnemonic(KeyEvent.VK_B); // sets the mnemonic for D4
	        D4.setActionCommand("D4"); // sets the action command of D4 to D4
	        D4.setOpaque(false); // sets the button to be opaque
	        c.gridy = 2; // sets the button to be in the second row
	        c.gridx = 1; // sets the button to be in the first column
	        layout.setConstraints(D4, c); // more button constraints
	        
	        JRadioButton D8 = new JRadioButton("D8"); //  creates a new JRadioButton called D8
	        D8.setMnemonic(KeyEvent.VK_B); // sets the mnemonic
	        D8.setActionCommand("D8"); // sets the action command of D8 to D8
	        D8.setOpaque(false); // sets the button to be opaque
	        c.gridy = 2; // sets the button to be in the second row
	        c.gridx = 3; // sets the button to be in the third column
	        layout.setConstraints(D8, c); // more button constraints
	        
	        JRadioButton D12 = new JRadioButton("D12"); // creates a new JRadioButton called D12
	        D12.setMnemonic(KeyEvent.VK_B); // sets the mnemonic
	        D12.setActionCommand("D12"); // sets the action command of D12 to D12
	        D12.setOpaque(false); // sets the button to be opaque
	        c.gridy = 3; // sets the button to be in the third row
	        c.gridx = 1; // sets the button to be in the first column
	        layout.setConstraints(D12, c); // more button constraints
	        
	        JRadioButton D100 = new JRadioButton("Percentage"); // creates a new JRadioButton called Percentage
	        D100.setMnemonic(KeyEvent.VK_B); // sets the mnemonic
	        D100.setActionCommand("D100"); // sets the action command of D100 to D100
	        D100.setOpaque(false); // sets the button to be opaque 
	        c.gridy = 3; // sets the button to be in the third row
	        c.gridx = 3; // sets the button to be in the third column
	        layout.setConstraints(D100, c); // more button constraints
	        
	        dieValue = new JTextArea("0"); // creates the JTextArea with the text 0 in it
			dieValue.setFont(new Font("GothicE", Font.PLAIN, 100)); // sets the font of the text area to be GothicE, not bold or italic and size 100
			dieValue.setEditable(false); // makes the JTextArea non-editable
			d = dieValue.getPreferredSize(); // sets the dimension of the text field to be the first preferred size
			dieValue.setPreferredSize(new Dimension((d.width * 3), d.height)); // sets the dimension of the text field to be the first preferred size times three so for three digits
			c.ipady = 0; // makes sure there's no internal padding for the x
			c.ipadx = 0; // makes sure there's no internal padding for the y
			c.gridx = 4; // sets the JTextArea to be in the fourth row
			c.gridy = 1; // sets the JTextArea to be in the first column
			
			layout.setConstraints(dieValue, c); // more constraints
	        
	        ButtonGroup dice = new ButtonGroup(); // creates the button group for the dice
	        dice.add(D20); // adds D20 to dice
	        dice.add(D4); // adds D4 to dice
	        dice.add(D6); // adds D6 to dice
	        dice.add(D8); // adds D8 to dice
	        dice.add(D10); // adds D10 to dice
	        dice.add(D12); // adds D12 to dice
	        dice.add(D100); // adds D100 to dice
	        
	        add(D20); // adds D20 to the JPanel
	        add(D4); // adds D4 to the JPanel
	        add(D6); // adds D6 to the JPanel
	        add(D8); // adds D8 to the JPanel
	        add(D10); // adds D10 to the JPanel
	        add(D12); // adds D12 to the JPanel
	        add(D100); // adds D100 to the JPanel
	        add(dieValue); // adds dieValue to JPanel
	        
	        D4.addActionListener(this); // adds ActionListener to D4
	        D6.addActionListener(this); // adds ActionListener to D6
	        D8.addActionListener(this); // adds ActionListener to D8
	        D10.addActionListener(this); // adds ActionListener to D10
	        D12.addActionListener(this); // adds ActionListener to D12
	        D20.addActionListener(this); // adds ActionListener to D20
	        D100.addActionListener(this); // adds ActionListener to D100
	        genButton.addActionListener(this); // adds ActionListener to roll dice button
	        D6.setSelected(true); // sets D6 to the selected at runtime 
			this.setSize(1250, 300); // sets the frame to the preferred size 
			this.setVisible(true); // makes the frame visible
			
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		D6 d6 = new D6(6); // creates a new variable for six-sided die
		D4 d4 = new D4(4); // creates a new variable for four-sided die
		D8 d8 = new D8(8); // creates a new variable for eight-sided die
		D10 d10 = new D10(10); // creates a new variable for ten-sided die
		D12 d12 = new D12(12); // creates a new variable for twelve-sided die
		D20 d20 = new D20(20); // creates a new variable for twenty-sided die
		D100 d100 = new D100(100); // creates a new variable for one hundred-sided die
		switch(e.getActionCommand()){
		
		case "Roll Dice":
			rollValue = 0; // sets rollValue
			numDie = 0; // sets numDie to zero
			modDie= 0; // sets the value of the modifier to zero
			numberDice(); // gets the number of dice rolled
			modifyDice(); // gets the value of the modifier
			for(int ctr = 0; ctr < numDie; ctr++){
			if(buttonSelected == 1){
				diceClip.start(); // starts to play the clip
				d6.roll(); // gets the value of the roll
				rollValue += d6.getValue() + modDie; // sets rollValue to the value of the roll plus the modifier 
			}else if(buttonSelected == 2){
				diceClip.start(); // starts to play the clip
				d4.roll(); // gets the value of the roll
				rollValue += d4.getValue() + modDie; // sets rollValue to the value of the roll plus the modifier
			}else if(buttonSelected == 3){
				diceClip.start(); // starts to play the clip
				d8.roll(); // gets the value of the roll
				rollValue += d8.getValue() + modDie; // sets rollValue to the value of the roll plus the modifier
			}else if(buttonSelected == 4){
				diceClip.start(); // starts to play the clip
				d10.roll(); // gets the value of the roll
				rollValue += d10.getValue() + modDie; // sets rollValue to the value of the roll plus the modifier
			}else if(buttonSelected == 5){
				diceClip.start(); // starts to play the clip
				d12.roll(); // gets the value of the roll
				rollValue += d12.getValue() + modDie; // sets rollValue to the value of the roll plus the modifier
			}else if(buttonSelected == 6){
				d20.roll(); // gets the value of the roll
				rollValue += d20.getValue() + modDie; // sets rollValue to the value of the roll plus the modifier
				if(d20.getValue() == 20){
					try{
					clapClip.start(); // starts to play the clip
					} catch(Exception e1){
						e1.printStackTrace();
					}
				}else if(d20.getValue() == 1){
					try{
					sadClip.start(); // starts to play the clip
					} catch(Exception e1){
						e1.printStackTrace();
					}
					}else if( d20.getValue() != 20 || d20.getValue() != 1){
						
						diceClip.start(); // starts to play the clip
					}
				
				rollValue += modDie; // sets rollValue to the value of the roll plus the modifier
			}else if(buttonSelected == 7){
					d100.roll(); // gets the value of the roll
					diceClip.start(); // starts to play the clip
					rollValue += d100.getValue() + modDie; // sets rollValue to the value of the roll plus the modifier
				}
			String valueString = Long.toString(rollValue); // creates a string valueString to store the value of the die roll
			dieValue.setText(valueString); // sets the value of the of the text field to the value of the roll
			}
			if(rollValue > 1000){
				//In case the value of the roll is over 1000, a separate screen will appear with the value
				JOptionPane.showMessageDialog(justInCase, "Number larger than 1000: " + rollValue);
			}
			System.out.println(rollValue); // prints the value of the roll to the console
			break;
		case "D4":
			buttonSelected = 2; // if D4 is selected buttonSelected = 2
			break;
		case "D6":
			buttonSelected = 1; // if D6 is selected buttonSelected = 1
			break;
		case "D8":
			buttonSelected = 3; // if D8 is selected buttonSelected = 3
			break;
		case "D10":
			buttonSelected = 4; // if D10 is selected buttonSelected  = 4
			break;
		case "D12":
			buttonSelected = 5; // if D12 is selected buttonSelected = 5
			break;	
		case "D20":
			buttonSelected = 6; // if D20 is selected buttonSelected  = 6
			break;
		case "D100":
			buttonSelected = 7; // if D100, or Percentage, is selected buttonSelected = 7
			break;
		case "default":
			//the default sets the dice back to D6, as the D6 is the most common die
			buttonSelected = 1;
			break;
		}
	}
		/**
		 * This method gets the value of the number of dice and returns it to be used 
		 * @return numDie
		 */
	public int numberDice(){
		if(myTextField.getText().matches("^[0-9]+$")){
		numDie = Integer.parseInt(myTextField.getText()); // if the value is a number, sets numDie to value
		}else{
			//otherwise the numDie = 1
			myTextField.setText("Value set to one");
			numDie = 1;
		}
		return numDie;
	}
	/**
	 * Gets the value of any modifier and returns it to be used
	 * @return modDie
	 */
	public int modifyDice(){
		if(modTextField.getText().matches("^[+|-][0-9]+$")){
			// if the value passes the regex, modDie is set to the ints in that value
			modDie = Integer.parseInt(modTextField.getText()); 
		}else{
			//otherwise modDie = 0
			modDie = 0;
		}
		return modDie;
	}
	/**
	 * This method creates the background image so it can be used
	 */
	
	 protected void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      if (image != null) {
	         g.drawImage(image, 0, 0, null); // draws the background picture
	      }
	   }

}

