import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
/***
 * @author Alex Cherniak
 * 
 * this class represent an XO button - the board in the TicTacToe game is built from 9 buttons that will
 * create the experience of a playing board
 */


public class XOButton extends JButton
{
	private ImageIcon X,O; //images for X and O
	int value; //0 is nothing || 1 is X || 2 is O
	
	
	/*
	 * create constructor
	 */
	public XOButton()
	{
		value =0; //starting value - empty
		X = new ImageIcon(this.getClass().getResource("XButton.png")); //set the X image
		O = new ImageIcon(this.getClass().getResource("OButton.png")); //set the Y image
		this.setBackground(Color.WHITE); //set button's color to white
	}
	

	
	/*
	 * set value method , the "computer" will use this method to set the O icon when playing against
	 * the player
	 */
	public void setValue(int value)
	{
		this.value = value; //sets the value
		switch(value) //sets the buttons icon according to the value
		{
			case 0:
				setIcon(null);
				break;
			case 1: 
				setIcon(X);
				break;
			case 2:
				setIcon(O); //inaccessible to the user - will be used by the computer
				break;
		}
		
	}
	/*
	 * get value method
	 */
	public int getValue()
	{
		return value; //return the value
	}
	
}
