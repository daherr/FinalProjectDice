import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.util.Random;

/**
 * 
 */

/**
 * @author SJHSStudent
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
	private GridBagLayout layout; // variable for the GridBagLayout.
	private JTextField myTextField; // variable for text box
	private JTextField modTextField; // text box for modifiers
	public static int buttonSelected = 1; // variable for the type of die that is selected
	private JTextArea dieValue; // variable for the value of the dice rolls
	public int rollValue; // int for the value of the dice rolls
	public int numDie; //
	public int modDie;
	public Dimension d;
	private JOptionPane justInCase;
	//private Color background;
	private BufferedImage image;
	
	D6 d6;
	D4 d4;
	D8 d8;
	D10 d10;
	D12 d12;
	D20 d20;
	D100 d100;
	
	public DiePanel(){
		super();
		
		try {
			image = ImageIO.read(new File("BackgroundImage.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//background = Color.BLACK;
		//getContentPane().
		rollValue = 0;
		layout = new GridBagLayout();
		 // variable for the gridbag layout
		 JButton genButton; // creates a new button for creating all the other buttons
		 GridBagConstraints c = new GridBagConstraints(); // creates a new GridBagRestraint
		 c.fill = GridBagConstraints.BOTH; // sets c to resize components BOTH vertically and horizontally
		 //c.gridwidth = GridBagConstraints.RELATIVE;
		 setLayout(layout); // sets the layout of the screen to a gridbag layout
		 justInCase = new JOptionPane();
		 
		    genButton = new JButton("Roll Dice"); // creates the button to roll the dice
			genButton.setFont(new Font("GothicE", Font.BOLD, 48)); // sets font, makes font italicized and font size
			genButton.setActionCommand("Roll Dice"); // adds ActionListener to button to roll dice
			c.ipadx = 5;
			c.gridx = 3;
			c.gridy = 1;
			layout.setConstraints(genButton, c); // more button constraints
			genButton.setVisible(true);
		
			add(genButton); // adds button to frame
			
			myTextField = new JTextField("Please enter the number of dice"); // creates new text field and sets text in text field
			myTextField.setFont(new Font("GothicE", Font.PLAIN, 16));
			myTextField.setEditable(true); // makes the text field not editable 
			d = myTextField.getPreferredSize();
			myTextField.setPreferredSize(d);
			add(myTextField);
			myTextField.addActionListener(this);
			c.gridx = 1;
			c.gridy = 1;
			//myTextField.
			layout.setConstraints(myTextField, c); // text field constraints
			
			modTextField = new JTextField("Please enter the value of modifier");
			modTextField.setFont(new Font("GothicE", Font.PLAIN, 16));
			modTextField.setEditable(true);
			d = modTextField.getPreferredSize();
			modTextField.setPreferredSize(d);
			add(modTextField);
			modTextField.addActionListener(this);
			c.gridx = 2;
			c.gridy = 1;
			layout.setConstraints(modTextField, c); // text field constraints
			
			//c.anchor = GridBagConstraints.FIRST_LINE_START;
			JRadioButton D20 = new JRadioButton("D20");
	        D20.setMnemonic(KeyEvent.VK_B);
	        D20.setActionCommand("D20");
	        D20.setOpaque(false);
	        c.ipadx = 0;
	        c.ipady = 40;
	        c.gridx = 2;
	        c.gridy = 3;
	       
	        layout.setConstraints(D20, c); // more button constraints
			
	         JRadioButton D10  = new JRadioButton("D10");
	        D10.setMnemonic(KeyEvent.VK_B);
	        D10.setActionCommand("D10");
	        D10.setOpaque(false);
	        c.gridy = 2;
	        c.gridx = 4;
	        layout.setConstraints(D10, c); // more button constraints
	        
	        JRadioButton D6 = new JRadioButton("D6");
	        D6.setMnemonic(KeyEvent.VK_B);
	        D6.setActionCommand("D6");
	        D6.setOpaque(false);
	        c.gridy = 2;
	        c.gridx = 2;
	        layout.setConstraints(D6, c); // more button constraints
	        
	        JRadioButton D4 = new JRadioButton("D4");
	        D4.setMnemonic(KeyEvent.VK_B);
	        D4.setActionCommand("D4");
	        D4.setOpaque(false);
	        c.gridy = 2;
	        c.gridx = 1;
	        layout.setConstraints(D4, c); // more button constraints
	        
	        JRadioButton D8 = new JRadioButton("D8");
	        D8.setMnemonic(KeyEvent.VK_B);
	        D8.setActionCommand("D8");
	        D8.setOpaque(false);
	        c.gridy = 2;
	        c.gridx = 3;
	        layout.setConstraints(D8, c); // more button constraints
	        
	        JRadioButton D12 = new JRadioButton("D12");
	        D12.setMnemonic(KeyEvent.VK_B);
	        D12.setActionCommand("D12");
	        D12.setOpaque(false);
	        c.gridy = 3;
	        c.gridx = 1;
	        layout.setConstraints(D12, c); // more button constraints
	        
	        JRadioButton D100 = new JRadioButton("Percentage");
	        D100.setMnemonic(KeyEvent.VK_B);
	        D100.setActionCommand("D100");
	        D100.setOpaque(false);
	        c.gridy = 3;
	        c.gridx = 3;
	        layout.setConstraints(D100, c); // more button constraints
	        
	        dieValue = new JTextArea("0");
			dieValue.setFont(new Font("GothicE", Font.PLAIN, 100));
			dieValue.setEditable(false);
			d = dieValue.getPreferredSize();
			dieValue.setPreferredSize(new Dimension((d.width * 3), d.height));
			add(dieValue);
			//dieValue.setSize( 300, 50);
			//dieValue.setAlignmentY(1);
			//dieValue.setAlignmentX(1);
			c.ipady = 0;
			c.ipadx = 0;
			c.gridx = 4;
			c.gridy = 1;
			c.anchor = GridBagConstraints.LAST_LINE_END;
			
			layout.setConstraints(dieValue, c);
	        
	        ButtonGroup dice = new ButtonGroup();
	        dice.add(D20);
	        dice.add(D4);
	        dice.add(D6);
	        dice.add(D8);
	        dice.add(D10);
	        dice.add(D12);
	        dice.add(D100);
	        
	        //add(background);
	        add(D20);
	        add(D4);
	        add(D6);
	        add(D8);
	        add(D10);
	        add(D12);
	        add(D100);
	        
	        //setComponentZOrder(background, 1);
	        //setContentPane(new JLabel(new ImageIcon("C:\\Users\\SJHSStudent\\Documents\\DHerr_Java\\FinalProjectDice\\rust-orange-leather-close-up-texture.jpg")));
	        D4.addActionListener(this);
	        D6.addActionListener(this);
	        D8.addActionListener(this);
	        D10.addActionListener(this);
	        D12.addActionListener(this);
	        D20.addActionListener(this);
	        D100.addActionListener(this);
	        genButton.addActionListener(this);
	        D6.setSelected(true);
			this.setSize(1250, 300); // sets the frame to the preferred size 
			this.setVisible(true); // makes the frame visible
			
	}
	
		
		
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
	
	@Override
	public void actionPerformed(ActionEvent e) {
		D6 d6 = new D6(6);
		D4 d4 = new D4(4);
		D8 d8 = new D8(8); 
		D10 d10 = new D10(10);
		D12 d12 = new D12(12);
		D20 d20 = new D20(20);
		D100 d100 = new D100(100);
		switch(e.getActionCommand()){
		
		case "Roll Dice":
			rollValue = 0;
			numDie = 0;
			modDie= 0;
			numberDice();
			modifyDice();
			for(int ctr = 0; ctr < numDie; ctr++){
			if(buttonSelected == 1){
				playDiceSound();
				d6.roll();
				rollValue += d6.getValue() + modDie;
			}else if(buttonSelected == 2){
				playDiceSound();
				d4.roll();
				rollValue += d4.getValue() + modDie;
			}else if(buttonSelected == 3){
				playDiceSound();
				d8.roll();
				rollValue += d8.getValue() + modDie;
			}else if(buttonSelected == 4){
				playDiceSound();
				d10.roll();
				rollValue += d10.getValue() + modDie;
			}else if(buttonSelected == 5){
				playDiceSound();
				d12.roll();
				rollValue += d12.getValue() + modDie;
			}else if(buttonSelected == 6){
				d20.roll();
				rollValue += d20.getValue();
				if(d20.getValue() == 20){
					try{
					File clap = new File("Applause Light 2-SoundBible.com-356111200.wav"); // creates the file to reference for sound one
					AudioInputStream clapStream = AudioSystem.getAudioInputStream(clap);
					AudioFormat clapFormat = clapStream.getFormat(); // creates a new audio format
					DataLine.Info clapInfo = new DataLine.Info( Clip.class, clapFormat); // creates a variable for additional information about the file
					Clip clapClip = (Clip) AudioSystem.getLine(clapInfo);
					clapClip.open(clapStream);
					clapClip.start(); // starts to play the clip
					} catch(Exception e1){
						e1.printStackTrace();
					}
				}else if(d20.getValue() == 1){
					try{
					File sad = new File("Sad_Trombone-Joe_Lamb-665429450.wav"); // creates the file to reference for sound one
					AudioInputStream sadStream = AudioSystem.getAudioInputStream(sad); // creates a new audio input stream
					AudioFormat sadFormat = sadStream.getFormat(); // creates a new audio format
					DataLine.Info sadInfo = new DataLine.Info( Clip.class, sadFormat); // creates a variable for additional information about the file
					Clip sadClip = (Clip) AudioSystem.getLine(sadInfo); // creates the clip that will be played 
					sadClip.open(sadStream); // opens the clip so that it can be played
					sadClip.start(); // starts to play the clip
					} catch(Exception e1){
						e1.printStackTrace();
					}
					}else if( d20.getValue() != 20 || d20.getValue() != 1){
						
						playDiceSound();
					}
				
				rollValue += modDie;
			}else if(buttonSelected == 7){
					d100.roll();
					playDiceSound();
					rollValue += d100.getValue() + modDie;
				}
			displayRandom();
			String valueString = Integer.toString(rollValue);
			dieValue.setText(valueString);
			}
			if(rollValue > 1000){
				JOptionPane.showMessageDialog(justInCase, "Number larger than 1000: " + rollValue);
			}
			System.out.println(rollValue);
			break;
		case "D4":
			buttonSelected = 2;
			break;
		case "D6":
			buttonSelected = 1;
			break;
		case "D8":
			buttonSelected = 3;
			break;
		case "D10":
			buttonSelected = 4;
			break;
		case "D12":
			buttonSelected = 5;
			break;	
		case "D20":
			buttonSelected = 6;
			break;
		case "D100":
			buttonSelected = 7;
			break;
		case "default":
			//the default sets the dice back to D6, as the D6 is the most common die
			buttonSelected = 1;
			break;
		}
	}
		
	public int numberDice(){
		if(myTextField.getText().matches("^[0-9]+$")){
		numDie = Integer.parseInt(myTextField.getText());
		}else{
			myTextField.setText("Invalid number, value set to one");
			numDie = 1;
		}
		return numDie;
	}
	
	public int modifyDice(){
		if(modTextField.getText().matches("^[+|-][0-9]+$")){
			modDie = Integer.parseInt(modTextField.getText());
		}else{
			modDie = 0;
		}
		return modDie;
	}
	public void playDiceSound(){
		try{
		File dice = new File("Shake And Roll Dice-SoundBible.com-591494296.wav"); // creates the file to reference for sound one
		AudioInputStream diceStream = AudioSystem.getAudioInputStream(dice); // creates a new audio input stream
		AudioFormat diceFormat = diceStream.getFormat(); // creates a new audio format
		DataLine.Info diceInfo = new DataLine.Info( Clip.class, diceFormat); // creates a variable for additional information about the file
		Clip diceClip = (Clip) AudioSystem.getLine(diceInfo); // creates the clip that will be played 
		diceClip.open(diceStream); // opens the clip so that it can be played
		diceClip.start(); // starts to play the clip
		}catch( Exception e1){
			e1.printStackTrace();
		}
	}
	
	public void displayRandom(){
		Random rnd = new Random(); // creates random object
		int ranNum = 0;
		int ctr = 7;
		for( int i = 0; i < ctr; i++){
			String numString = Integer.toString(ranNum);
			switch(buttonSelected){
			
			case 1:
				ranNum = rnd.nextInt(6);
				break;
			case 2:
				 ranNum = rnd.nextInt(4);
				 break;
			case 3:
				 ranNum = rnd.nextInt(8);
				 break;
			case 4:
				ranNum = rnd.nextInt(10);
				break;
			case 5:
				ranNum = rnd.nextInt(12);
				break;
			case 6:
				ranNum = rnd.nextInt(20);
				break;
			case 7:
				ranNum = rnd.nextInt(100);
				break;
			}
			try {
				Thread.sleep(100);
				dieValue.setText(numString);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
			System.out.println(numString);
		}
	}
	
	 protected void paintComponent(Graphics g) {
	      super.paintComponent(g);
	      if (image != null) {
	         g.drawImage(image, 0, 0, null);
	      }
	   }

}

