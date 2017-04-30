import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.SoftBevelBorder;
import javax.swing.UIManager;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
/**
 * @author Alex Cherniak
 * 
 * this class represents a TicTacToe game against the computer
 * the player will always play with the X symbol
 * the computer will always play with the O symbol
 * 
 * the first player to start the game is chosen randomly
 * 
 * how to play:
 * when its the player's turn , an input dialog will pop out and ask for coordinates
 * player should insert the the coordinates at the following format: row,col 
 * for example 1,2 to place an X on the first row and the second column cell
 * the player can use spaces between the characters - program will get the right coordinates
 * 
 * if the player press 'cancel' on the input dialog , another dialog will pop and ask the player if he wants to
 * quit the game
 * 
 * player can restart the game when he finished by pressing the start game button again.
 *
 */

public class TicTacToe {
	
	/**********************************************************************************************************************
	 * field variables
	 **********************************************************************************************************************/
	private JFrame mainWindow; //the main window
	private XOButton[] buttons = new XOButton[9]; //an array of 9 XOButtons 
	private final int NO_BUTTONS = 9;
	private JTextField screen; //the screen to display info on
	private int turn; //this is a turn flag , will determine who is next to play || 0 - computer's turn || 1- player's turn
	private JButton startButton;
	private final int COMPUTER = 0; //represents the Computer's turn
	private final int PLAYER = 1; //represents the Player's turn
    private int gameState = -1; // -1 will represent a running game state , -1000 will represent game over
    private final int PLAYING = -1;
    private final int OVER = -1000;
    private JLabel col2;
    private JLabel col3;
    private JLabel row1;
    private JLabel row2;
    private JLabel row3;
    private JLabel col1;
    private String input = ""; //will represent the input string
    private char[] inputChar; //will represent the input as a char[] array
    private JPanel panel;
    
    /************************************************************************************************************************
     * **********************************************************************************************************************
     ************************************************************************************************************************/

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					TicTacToe window = new TicTacToe();
					window.mainWindow.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TicTacToe()
	{
		initialize();
	}

	/**
	 * Initialize the contents of the main window.
	 */
	private void initialize()
	{
		mainWindow = new JFrame();
		mainWindow.setIconImage(Toolkit.getDefaultToolkit().getImage("D:\\\u05DE\u05D3\u05E2\u05D9 \u05D4\u05DE\u05D7\u05E9\u05D1 - \u05EA\u05D5\u05D0\u05E8 \u05E8\u05D0\u05E9\u05D5\u05DF\\Advanced Java\\TicTacToe - Maman12\\src\\tictactoe Icon.png"));
		mainWindow.getContentPane().setBackground(new Color(0, 102, 153));
		mainWindow.getContentPane().setForeground(Color.WHITE);
		mainWindow.setTitle("TicTacToe");
		mainWindow.setBounds(100, 100, 752, 524);
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.getContentPane().setLayout(null);
		
		//create the JPanel
		panel = new JPanel();
		panel.setBounds(213, 35, 300, 278);
		mainWindow.getContentPane().add(panel);
		panel.setLayout(new GridLayout(3, 3, 0, 0));
		
		//create the Screen
		screen = new JTextField();
		screen.setBackground(new Color(255, 255, 204));
		//add mouse click listener to allow the screen to be emptied once clicked up on
		screen.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) 
			{
				screen.setText(""); //empty the screen
			}
		});
		//add listener to be able to read the text from the screen
		screen.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent screenEvent) 
			{
				input = screen.getText();
			}
		});
		screen.setFont(new Font("Segoe Print", Font.BOLD, 15));
		screen.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		screen.setBounds(67, 326, 592, 101);
		screen.setHorizontalAlignment(SwingConstants.CENTER);
		mainWindow.getContentPane().add(screen);
		screen.setText("Welcome to Tic-Tac-Toe!");
		
		//create start button
		startButton = new JButton("Start Game");
		startButton.setBorder(new SoftBevelBorder(BevelBorder.RAISED, new Color(255, 250, 205), new Color(0, 191, 255), null, null));
		startButton.setBackground(UIManager.getColor("Button.darkShadow"));
		startButton.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				startGame();//calling the start game method
			}
		});
		startButton.setFont(new Font("Stencil", Font.PLAIN, 26));
		startButton.setBounds(525, 34, 197, 101);
		mainWindow.getContentPane().add(startButton);
		
		/*****************************************************************************************************************
		 * labels for rows + cols
		 ****************************************************************************************************************/
		col1 = new JLabel("1");
		col1.setForeground(new Color(255, 255, 51));
		col1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		col1.setHorizontalAlignment(SwingConstants.CENTER);
		col1.setBounds(240, 13, 56, 16);
		mainWindow.getContentPane().add(col1);
		
		col2 = new JLabel("2");
		col2.setForeground(new Color(255, 255, 51));
		col2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		col2.setBounds(333, 13, 56, 16);
		col2.setHorizontalAlignment(SwingConstants.CENTER);
		mainWindow.getContentPane().add(col2);
		
		col3 = new JLabel("3");
		col3.setForeground(new Color(255, 255, 51));
		col3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		col3.setBounds(435, 13, 56, 16);
		col3.setHorizontalAlignment(SwingConstants.CENTER);
		mainWindow.getContentPane().add(col3);
		
		row1 = new JLabel("1");
		row1.setForeground(new Color(255, 255, 51));
		row1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		row1.setBounds(145, 76, 56, 16);
		row1.setHorizontalAlignment(SwingConstants.RIGHT);
		mainWindow.getContentPane().add(row1);
		
		row2 = new JLabel("2");
		row2.setForeground(new Color(255, 255, 51));
		row2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		row2.setBounds(145, 168, 56, 16);
		row2.setHorizontalAlignment(SwingConstants.RIGHT);
		mainWindow.getContentPane().add(row2);
		
		row3 = new JLabel("3");
		row3.setForeground(new Color(255, 255, 51));
		row3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		row3.setBounds(145, 262, 56, 16);
		row3.setHorizontalAlignment(SwingConstants.RIGHT);
		mainWindow.getContentPane().add(row3);
		
		JLabel lblCreatedByAlex = new JLabel("Created by: Alex Cherniak");
		lblCreatedByAlex.setFont(new Font("Segoe Print", Font.BOLD, 13));
		lblCreatedByAlex.setForeground(Color.YELLOW);
		lblCreatedByAlex.setBounds(525, 440, 197, 24);
		mainWindow.getContentPane().add(lblCreatedByAlex);
		
		JLabel logo = new JLabel("");
		logo.setIcon(new ImageIcon("D:\\\u05DE\u05D3\u05E2\u05D9 \u05D4\u05DE\u05D7\u05E9\u05D1 - \u05EA\u05D5\u05D0\u05E8 \u05E8\u05D0\u05E9\u05D5\u05DF\\Advanced Java\\TicTacToe - Maman12\\src\\tictactoe logo.png"));
		logo.setBounds(12, 13, 168, 300);
		mainWindow.getContentPane().add(logo);
		
		/*****************************************************************************************************************
		 * ***************************************************************************************************************
		 *****************************************************************************************************************/
		
		//add XOButtons to the panel
		for(int i=0;i<NO_BUTTONS;i++)
		{
			buttons[i] = new XOButton();
			panel.add(buttons[i]);
		}
		
	}
	
	/*
	 * method to select who is first to start the game || 0 - the computer will start || 1 - the player will start
	 */
	private int generateTurn()
	{
		int min = 0; //minimum value
		int max = 1; //maximum value
		Random rn = new Random();
		int random = rn.nextInt(max - min + 1)+ min;
		return random; //return 1 or 0 randomly
	}
	
	/*-------------------------------------------------------------------------------------------------------------------------
	 * Start Game Method  ---------------------------------------------------------------------------------------------------
	 *///----------------------------------------------------------------------------------------------------------------------
	public void startGame()
	{	
		/*
		 * need to clean the board before the game starts - when starting over
		 */
		for(int i=0;i<buttons.length;i++)
		{
			buttons[i].setValue(0);
		}
		panel.revalidate(); //re-validate the panel after setting values
		gameState = PLAYING;
		int checkState=-1;
		int defValidation = 0;
		turn = this.generateTurn(); //turn gets the info regarding the starting player
		screen.setText(""); //clear the screen
		while(gameState == PLAYING)
		{
			if(turn == COMPUTER) //computer's turn
			{
				defValidation = tryToWin(); //will try to look for a sequence to end the game
				if(defValidation == 0) //couldnt win
				{
					defValidation = defensePlan();
					if(defValidation != 100) //didnt defended
					{
						attackPlan(); //attack
					}
				}
				panel.revalidate();				
				turn = PLAYER; //switch turn to player
			}
			else//palyer's turn
			{
				int checkInput = 0;
				int row;
				int col;
				while(checkInput == 0)
				{
					input = JOptionPane.showInputDialog("please insert Coordinates to place the X. example:1,2");
					if(input == null)
					{
						String question = JOptionPane.showInputDialog("Do you want to quit playing? type: 'yes' to quit or: 'no' to keep playing");
						if(question.compareTo("yes") == 0)
						{
							System.exit(0);//exit the program
						}
						else //probably not a yes :)
						{
							input = ""; //set input as empty which will trigger an incorrect input state
						}
					}
					input = input.replaceAll("\\s+", ""); //removes white spaces from the input
					inputChar = input.toCharArray();
					if(inputChar.length != 3)//inputChar array must have 3 characters for valid input
					{
						screen.setText("incorrect input on first argument - try again");
					}
					else
					{
						if(inputChar[0] != '1' && inputChar[0] != '2' && inputChar[0] != '3')
						{
							screen.setText("incorrect input on first argument - try again");
						}
						//check second argument
						else //passed the first argument check
						{
							if(inputChar[1] != ',')
							{
								screen.setText("incorrect input on first argument - try again");
							}
							else
								if(inputChar[2] != '1' && inputChar[2] != '2' && inputChar[2] != '3')
								{
									screen.setText("incorrect input on first argument - try again");
								}
								else//passed the second argument check
								{
									row = Integer.parseInt(Character.toString(inputChar[0]));
									col = Integer.parseInt(Character.toString(inputChar[2]));
									/*
									 * convert table
									 * 1,1 - cell 0 || 2,1 - cell 3 || 3,1 - cell 6
									 * 1,2 - cell 1 || 2,2 - cell 4 || 3,2 - cell 7
									 * 1,3 - cell 2 || 2,3 - cell 5 || 3,3 - cell 8
									 */
									switch(row)
									{
									case 1: //first row
										switch(col)
										{
										case 1:
											if(buttons[0].getValue() == 0) //empty cell
											{
												buttons[0].setValue(1);
												checkInput =1;
											}
											else
											{
												screen.setText("cell is not empty - try again");
											}
											break;
										case 2:
											if(buttons[1].getValue() == 0)
											{
												buttons[1].setValue(1);
												checkInput =1;
											}
											else
											{
												screen.setText("cell is not empty - try again");
											}
											break;
										case 3:
											if(buttons[2].getValue() == 0)
											{
												buttons[2].setValue(1);
												checkInput =1;
											}
											else
											{
												screen.setText("cell is not empty - try again");
											}
											break;
										}
										break;
									case 2: //second row
										switch(col)
										{
										case 1:
											if(buttons[3].getValue() == 0)
											{
												buttons[3].setValue(1);
												checkInput =1;
											}
											else
											{
												screen.setText("cell is not empty - try again");
											}
											break;
										case 2:
											if(buttons[4].getValue() == 0)
											{
												buttons[4].setValue(1);
												checkInput =1;
											}
											else
											{
												screen.setText("cell is not empty - try again");
											}
											break;
										case 3:
											if(buttons[5].getValue() == 0)
											{
												buttons[5].setValue(1);
												checkInput =1;
											}
											else
											{
												screen.setText("cell is not empty - try again");
											}
											break;
										}
										break;
									case 3: //third row
										switch(col)
										{
										case 1:
											if(buttons[6].getValue() == 0)
											{
												buttons[6].setValue(1);
												checkInput =1;
											}
											else
											{
												screen.setText("cell is not empty - try again");
											}
											break;
										case 2:
											if(buttons[7].getValue() == 0)
											{
												buttons[7].setValue(1);
												checkInput =1;
											}
											else
											{
												screen.setText("cell is not empty - try again");
											}
											break;
										case 3:
											if(buttons[8].getValue() == 0)
											{
												buttons[8].setValue(1);
												checkInput =1;
											}
											else
											{
												screen.setText("cell is not empty - try again");
											}
											break;
										}
										break;
									}
							}
						}
					}
					}
					
				turn = COMPUTER; //switch turn to the computer
			}
			checkState = checkGameState();
//		    return 0 - computer wins
//		    return 1 - player wins
//			return 2 - draw
//		    return 3 - game should continue
			switch(checkState)
			{
			case 0:
				gameState = OVER; //set game state to OVER to indicate that the game is over
				screen.setText("the Computer WINS!");
				break;
			case 1:
				gameState = OVER;
				screen.setText("the Player WINS!");
				break;
			case 2:
				gameState = OVER;
				screen.setText("DRAW!!");
				break;
			case 3:
				break; //game should continue , so it does nothing		
			}	
		}
	}
	/*---------------------------------------------------------------------------------------------------------------------
	 * --------------------------------------------------------------------------------------------------------------------
	 --------------------------------------------------------------------------------------------------------------------*/
	
	/*
	 * the defense plan method will scan 8 possibilities for the player to win
	 * 
	 * 1. X** 2. *X* 3. **X 4. XXX 5. *** 6. *** 7. X** 8. **X
	 *    X**    *X*    **X    ***    XXX    ***    *X*    *X*
	 *    X**    *X*    **X    ***    ***    XXX    **X    X**
	 *    
	 *    return 100 if defended , return -100 if didnt defend
	 *    
	 *    buttons array built:
	 *    012
	 *    345
	 *    678
	 */
	private int defensePlan()
	{
		/*
		 * scan the first 3 options - 1,2,3
		 */
		for(int i=0;i<3;i++)
		{
			if((buttons[i].getValue()==1) && (buttons[i+3].getValue()==1)) //defend on lower row
			{
				if(buttons[i+6].getValue()==0) //need to defend 
				{
					buttons[i+6].setValue(2); //set O on the cell it needs to defend
					return 100; //return defended
				}
			}
			if((buttons[i].getValue()==1) && (buttons[i+6].getValue()==1)) //defend on middle row
			{
				if(buttons[i+3].getValue()==0) //need to defend 
				{
					buttons[i+3].setValue(2); //set O on the cell it needs to defend
					return 100; //return defended
				}
			}
			if((buttons[i+3].getValue()==1) && (buttons[i+6].getValue()==1)) //defend on first row
			{
				if(buttons[i].getValue()==0) //need to defend 
				{
					buttons[i].setValue(2); //set O on the cell it needs to defend
					return 100; //return defended
				}
			}
		}
		/*
		 * scan options 4,5,6
		 */
		for(int i=0;i<7;i+=3)
		{
			if((buttons[i].getValue()==1) && (buttons[i+1].getValue()==1)) //defend on third column
			{
				if(buttons[i+2].getValue()==0) //need to defend 
				{
					buttons[i+2].setValue(2); //set O on the cell it needs to defend
					return 100; //return defended
				}
			}
			if((buttons[i].getValue()==1) && (buttons[i+2].getValue()==1)) //defend on middle column
			{
				if(buttons[i+1].getValue()==0) //need to defend 
				{
					buttons[i+1].setValue(2); //set O on the cell it needs to defend
					return 100; //return defended
				}
			}
			if((buttons[i+1].getValue()==1) && (buttons[i+2].getValue()==1)) //defend on first column
			{
				if(buttons[i].getValue()==0) //need to defend 
				{
					buttons[i].setValue(2); //set O on the cell it needs to defend
					return 100; //return defended
				}
			}
		}
		/*
		 * scan option 7
		 */
		if((buttons[0].getValue()==1) && (buttons[4].getValue()==1)) //defend on third row
		{
			if(buttons[8].getValue()==0) //need to defend 
			{
				buttons[8].setValue(2); //set O on the cell it needs to defend
				return 100; //return defended
			}
		}
		if((buttons[0].getValue()==1) && (buttons[8].getValue()==1)) //defend on middle row
		{
			if(buttons[4].getValue()==0) //need to defend 
			{
				buttons[4].setValue(2); //set O on the cell it needs to defend
				return 100; //return defended
			}
		}
		if((buttons[4].getValue()==1) && (buttons[8].getValue()==1)) //defend on first row
		{
			if(buttons[0].getValue()==0) //need to defend 
			{
				buttons[0].setValue(2); //set O on the cell it needs to defend
				return 100; //return defended
			}
		}
		/*
		 * scan option 8
		 */
		if((buttons[2].getValue()==1) && (buttons[4].getValue()==1)) //defend on third row
		{
			if(buttons[6].getValue()==0) //need to defend 
			{
				buttons[6].setValue(2); //set O on the cell it needs to defend
				return 100; //return defended
			}
		}
		if((buttons[2].getValue()==1) && (buttons[6].getValue()==1)) //defend on middle row
		{
			if(buttons[4].getValue()==0) //need to defend 
			{
				buttons[4].setValue(2); //set O on the cell it needs to defend
				return 100; //return defended
			}
		}
		if((buttons[4].getValue()==1) && (buttons[6].getValue()==1)) //defend on first row
		{
			if(buttons[2].getValue()==0) //need to defend 
			{
				buttons[2].setValue(2); //set O on the cell it needs to defend
				return 100; //return defended
			}
		}
		return -100; //didnt defend
	}
	
	/*
	 * the attack plan method will scan 8 possibilities for the computer to win
	 * 
	 * 1. X** 2. *X* 3. **X 4. XXX 5. *** 6. *** 7. X** 8. **X
	 *    X**    *X*    **X    ***    XXX    ***    *X*    *X*
	 *    X**    *X*    **X    ***    ***    XXX    **X    X**
	 *    
	 *    
	 *    buttons array built:
	 *    012
	 *    345
	 *    678
	 */
	private void attackPlan()
	{
		int attackCell; //will be used to attack in case the computer cant win the game at the next move
		/*
		 * need to look for a place to place the O in a way to create 2 in a row + a blank cell
		 */
		/*
		 * scan the first 3 options - 1,2,3
		 */
		for(int i=0;i<3;i++)
		{
			if((buttons[i].getValue()==2) && (buttons[i+3].getValue()==0)) //attack on lower row
			{
				if(buttons[i+6].getValue()==0) //need to attack 
				{
					buttons[i+6].setValue(2); //set O on the cell it needs to attack
					return;
				}
			}
			if((buttons[i].getValue()==0) && (buttons[i+6].getValue()==2)) //attack on middle row
			{
				if(buttons[i+3].getValue()==0) //need to attack 
				{
					buttons[i+3].setValue(2); //set O on the cell it needs to attack
					return;
				}
			}
			if((buttons[i+3].getValue()==2) && (buttons[i+6].getValue()==0)) //attack on first row
			{
				if(buttons[i].getValue()==0) //need to attack 
				{
					buttons[i].setValue(2); //set O on the cell it needs to attack
					return; 
				}
			}
		}
		/*
		 * scan options 4,5,6
		 */
		for(int i=0;i<7;i+=3)
		{
			if((buttons[i].getValue()==2) && (buttons[i+1].getValue()==0)) //attack on third column
			{
				if(buttons[i+2].getValue()==0) //need to attack 
				{
					buttons[i+2].setValue(2); //set O on the cell it needs to attack
					return;
				}
			}
			if((buttons[i].getValue()==0) && (buttons[i+2].getValue()==2)) //attack on middle column
			{
				if(buttons[i+1].getValue()==0) //need to attack
				{
					buttons[i+1].setValue(2); //set O on the cell it needs to attack
					return; 
				}
			}
			if((buttons[i+1].getValue()==2) && (buttons[i+2].getValue()==0)) //attack on first column
			{
				if(buttons[i].getValue()==0) //need to attack 
				{
					buttons[i].setValue(2); //set O on the cell it needs to attack
					return;
				}
			}
		}
		/*
		 * scan option 7
		 */
		if((buttons[0].getValue()==2) && (buttons[4].getValue()==0)) //attack on third row
		{
			if(buttons[8].getValue()==0) //need to attack 
			{
				buttons[8].setValue(2); //set O on the cell it needs to attack
				return; 
			}
		}
		if((buttons[0].getValue()==0) && (buttons[8].getValue()==2)) //attack on middle row
		{
			if(buttons[4].getValue()==0) //need to attack 
			{
				buttons[4].setValue(2); //set O on the cell it needs to attack
				return; 
			}
		}
		if((buttons[4].getValue()==2) && (buttons[8].getValue()==0)) //attack on first row
		{
			if(buttons[0].getValue()==0) //need to attack 
			{
				buttons[0].setValue(2); //set O on the cell it needs to attack
				return; 
			}
		}
		/*
		 * scan option 8
		 */
		if((buttons[2].getValue()==2) && (buttons[4].getValue()==0)) //attack on third row
		{
			if(buttons[6].getValue()==0) //need to defend 
			{
				buttons[6].setValue(2); //set O on the cell it needs to defend
				return;
			}
		}
		if((buttons[2].getValue()==0) && (buttons[6].getValue()==2)) //attack on middle row
		{
			if(buttons[4].getValue()==0) //need to attack 
			{
				buttons[4].setValue(2); //set O on the cell it needs to attack
				return; 
			}
		}
		if((buttons[4].getValue()==2) && (buttons[6].getValue()==0)) //attack on first row
		{
			if(buttons[2].getValue()==0) //need to attack 
			{
				buttons[2].setValue(2); //set O on the cell it needs to attack
				return; 
			}
		}
		
		
		/*
		 * if we got here , it means we couldnt attack before(unlikely) , will place a random X
		 * to allow the game to continue
		 */
		attackCell = generateRandomCell();
		while(buttons[attackCell].getValue()!= 0) //verify that cell is empty
		{
			attackCell = generateRandomCell();
		}
		buttons[attackCell].setValue(2); //set O to the selected cell
	}
	
	/*
	 * generates a random cell in the buttons array
	 * return the index of the cell
	 */
	private int generateRandomCell()
	{
		int min = 0; //minimum value
		int max = 8; //maximum value
		Random rn = new Random();
		int random = rn.nextInt(max - min + 1)+ min;
		return random; //return 1 or 0 randomly
	}
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/*
	 * check game state - will return an int that will indicate if the game has a winner /game ended as draw / game should continue
	 * 
	 * return 0 - computer won
	 * return 1 - player won
	 * return 2 - draw
	 * return 3 - game should continue
	 */
	private int checkGameState()
	{
		/*
		 * check winning possibilities
		 */
		
		//check for winning on possibilities 1,2,3
		for(int i=0;i<3;i++)
		{
			//
			if((buttons[i].getValue() == 1)&& (buttons[i+3].getValue() == 1) && (buttons[i+6].getValue() == 1)) //the player wins
			{
				return 1; //return value to indicate that the player wins
			}
			if((buttons[i].getValue() == 2)&& (buttons[i+3].getValue() == 2) && (buttons[i+6].getValue() == 2))//the computer wins
			{
				return 0; //return value to indicate that the computer wins
			}
		}
		//check for winning possibilities 4,5,6
		for(int i=0;i<7;i+=3)
		{
			if((buttons[i].getValue() == 1)&& (buttons[i+1].getValue() == 1) && (buttons[i+2].getValue() == 1)) //the player wins
			{
				return 1;
			}
			if((buttons[i].getValue() == 2)&& (buttons[i+1].getValue() == 2) && (buttons[i+2].getValue() == 2)) //the computer wins
			{
				return 0;
			}
		}
		//check for winning possibility 7
		if((buttons[0].getValue() == 1)&& (buttons[4].getValue() == 1) && (buttons[8].getValue() == 1))//the player wins
		{
			return 1;
		}
		if((buttons[0].getValue() == 2)&& (buttons[4].getValue() == 2) && (buttons[8].getValue() == 2))//the computer wins
		{
			return 0;
		}
		//check for winning possibility 8
		if((buttons[2].getValue() == 1)&& (buttons[4].getValue() == 1) && (buttons[6].getValue() == 1))//the player wins
		{
			return 1;
		}
		if((buttons[2].getValue() == 2)&& (buttons[4].getValue() == 2) && (buttons[6].getValue() == 2))//the computer wins
		{
			return 0;
		}
		/*
		 * if none of the above exits the method , need to check for draw or let the game continue
		 */
		
		//check for empty cells
		for(int i=0;i<NO_BUTTONS;i++)
		{
			if(buttons[i].getValue()==0) //empty cell
			{
				return 3; //return a value to indicate that there is still an empty cell and game can continue
			}
		}
		/*
		 * if we got this far it means that there are no empty cells and nobody was able to win so - draw
		 */
		return 2; //return a value to indicate a draw
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	/* try to win method - the computer will try to win the game on the current turn if he can
	 * will return 1 if computer placed his O
	 * will return 0 if computer didnt play on this method
	 */
	private int tryToWin()
	{
		/*
		 * scan the first 3 options - 1,2,3
		 */
		for(int i=0;i<3;i++)
		{
			if((buttons[i].getValue()==2) && (buttons[i+3].getValue()==2)) //attack on lower row
			{
				if(buttons[i+6].getValue()==0) //need to attack 
				{
					buttons[i+6].setValue(2); //set O on the cell it needs to attack
					return 1;
				}
			}
			if((buttons[i].getValue()==2) && (buttons[i+6].getValue()==2)) //attack on middle row
			{
				if(buttons[i+3].getValue()==0) //need to attack 
				{
					buttons[i+3].setValue(2); //set O on the cell it needs to attack
					return 1;
				}
			}
			if((buttons[i+3].getValue()==2) && (buttons[i+6].getValue()==2)) //attack on first row
			{
				if(buttons[i].getValue()==0) //need to attack 
				{
					buttons[i].setValue(2); //set O on the cell it needs to attack
					return 1; 
				}
			}
		}
		/*
		 * scan options 4,5,6
		 */
		for(int i=0;i<7;i+=3)
		{
			if((buttons[i].getValue()==2) && (buttons[i+1].getValue()==2)) //attack on third column
			{
				if(buttons[i+2].getValue()==0) //need to attack 
				{
					buttons[i+2].setValue(2); //set O on the cell it needs to attack
					return 1;
				}
			}
			if((buttons[i].getValue()==2) && (buttons[i+2].getValue()==2)) //attack on middle column
			{
				if(buttons[i+1].getValue()==0) //need to attack
				{
					buttons[i+1].setValue(2); //set O on the cell it needs to attack
					return 1; 
				}
			}
			if((buttons[i+1].getValue()==2) && (buttons[i+2].getValue()==2)) //attack on first column
			{
				if(buttons[i].getValue()==0) //need to attack 
				{
					buttons[i].setValue(2); //set O on the cell it needs to attack
					return 1;
				}
			}
		}
		/*
		 * scan option 7
		 */
		if((buttons[0].getValue()==2) && (buttons[4].getValue()==2)) //attack on third row
		{
			if(buttons[8].getValue()==0) //need to attack 
			{
				buttons[8].setValue(2); //set O on the cell it needs to attack
				return 1; 
			}
		}
		if((buttons[0].getValue()==2) && (buttons[8].getValue()==2)) //attack on middle row
		{
			if(buttons[4].getValue()==0) //need to attack 
			{
				buttons[4].setValue(2); //set O on the cell it needs to attack
				return 1; 
			}
		}
		if((buttons[4].getValue()==2) && (buttons[8].getValue()==2)) //attack on first row
		{
			if(buttons[0].getValue()==0) //need to attack 
			{
				buttons[0].setValue(2); //set O on the cell it needs to attack
				return 1; 
			}
		}
		/*
		 * scan option 8
		 */
		if((buttons[2].getValue()==2) && (buttons[4].getValue()==2)) //attack on third row
		{
			if(buttons[6].getValue()==0) //need to defend 
			{
				buttons[6].setValue(2); //set O on the cell it needs to defend
				return 1;
			}
		}
		if((buttons[2].getValue()==2) && (buttons[6].getValue()==2)) //attack on middle row
		{
			if(buttons[4].getValue()==0) //need to attack 
			{
				buttons[4].setValue(2); //set O on the cell it needs to attack
				return 1; 
			}
		}
		if((buttons[4].getValue()==2) && (buttons[6].getValue()==2)) //attack on first row
		{
			if(buttons[2].getValue()==0) //need to attack 
			{
				buttons[2].setValue(2); //set O on the cell it needs to attack
				return 1; 
			}
		}
		return 0;
	}
}
