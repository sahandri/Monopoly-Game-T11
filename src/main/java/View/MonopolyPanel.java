package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Model.Street.colors;


import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;

public class MonopolyPanel extends JFrame implements ActionListener {
	private Map<Integer,ArrayList<Integer>> GUIposition = new HashMap<Integer,ArrayList<Integer>>();
	private final String boardImagePath = "/img/board.jpg";
	private final String tokenImagePath = "/img/token/boot.png";

	private JLabel tok1 = new JLabel();
	private JLabel tok2 = new JLabel();
	private JLabel tok3 = new JLabel();
	private JLabel tok4 = new JLabel();
	private JLabel lblPlayer;
	private JLabel lblPrice;
	private String numberOfPlayers;
	
	private static Monopoly monopoly;
			
	private JLayeredPane contentPanel;
	private final JButton RollDiceButton = new JButton("Roll Dice");


	public MonopolyPanel(Monopoly monopoly) {
		this.monopoly = monopoly;
		createPositionMap();
		setUpGUI();
	}

	public void setUpGUI(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000 , 700);
		contentPanel = new JLayeredPane();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		this.setVisible(true);
        this.setExtendedState(Frame.NORMAL);
        
        setUpImages();
        setUpButtons();
        setLabels();
	}
	
	private void setUpImages(){
		//create LABEL that holds board IMAGE 
		JLabel boardImage = new JLabel("");
		boardImage.setBounds(6, 6, 594, 585);
		Image img = new ImageIcon(this.getClass().getResource(boardImagePath)).getImage().getScaledInstance(600, 600, Image.SCALE_AREA_AVERAGING);    //import board.png file as an ImageIcon object
		boardImage.setIcon(new ImageIcon(img));         //set the image of the board to be in the label 
		contentPanel.add(boardImage, new Integer(1));                   //add the label to the board

		//create LABEL that holds token1 IMAGE : boot
		tok1.setBounds(89, 491, 100, 100);
		Image tokenImg = new ImageIcon(this.getClass().getResource(tokenImagePath)).getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);    //import boot.png file as an ImageIcon object
		/*tokenImage.setIcon(new ImageIcon(tokenImg));         //set the image of the token to be in the label 
		* This is a scaling of the token image. */
		tok1.setIcon(new ImageIcon(new ImageIcon(tokenImg).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
		contentPanel.add(tok1, new Integer(2));                   //add the label to the board
		
	}
	
	private void setUpButtons(){
		startButton();
		rollDiceButton();
		buyPropertyButton();
		sellPropertyButton();
		historyWindow();
		//playerStatus();
		EndTurnButton();
	}

	private void historyWindow() {
		/** History scroll area*/
		JTextArea display = new JTextArea ( 16, 30 );
		display.setFont(new Font("Avenir", Font.PLAIN, 13));
		display.setText("This will hold previous actions.");
		display.setEditable ( false );
		JScrollPane scrollPane = new JScrollPane(display);
		scrollPane.setBounds(610, 370, 364, 212);
		contentPanel.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		
		
		JLabel lblHistory = new JLabel("History");
		lblHistory.setFont(new Font("Avenir", Font.BOLD, 18));
		lblHistory.setBounds(611, 330, 75, 29);
		contentPanel.add(lblHistory);
	}
	
	public void EndTurnButton() {
		JButton btnEndTurn = new JButton("Next Player");
		btnEndTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//switch player to the next one
				monopoly.changePlayer();
				playerStatus();
			}
		});
		btnEndTurn.setBounds(539, 622, 115, 29);
		contentPanel.add(btnEndTurn);
	}

	private void sellPropertyButton() {
		//create Sell Property
		JButton SellPropertyBtn = new JButton("Sell Property");
		SellPropertyBtn.setFont(new Font("Avenir", Font.PLAIN, 13));
		SellPropertyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                //TODO: implement here what should happen when "End Game" button is pressed.
			}
		});
		SellPropertyBtn.setBounds(252, 623, 117, 29);
		contentPanel.add(SellPropertyBtn);
	}

	private void buyPropertyButton() {
		//create button to buy property
		JButton BuyBtn = new JButton("Buy Property");
		BuyBtn.setFont(new Font("Avenir", Font.PLAIN, 13));
		BuyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                //TODO: implement here what should happen when "Buy Property" button is pressed.
			}
		});
		BuyBtn.setBounds(136, 623, 117, 29);
		contentPanel.add(BuyBtn);
	}

	private void rollDiceButton() {
		//Roll dice and move player
		RollDiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//int roll = monopoly.rollDice();
				//JOptionPane.showMessageDialog(contentPanel.getComponent(0),
				//"Dice: "+Integer.valueOf(roll));
			}
		});
		RollDiceButton.setBounds(369, 623, 117, 29);
		contentPanel.add(RollDiceButton);
	}

	private void startButton(){
		//START BUTTON:
		String[] List = new String[] {"2","3","4"};
		ArrayList<String> names = new ArrayList<>();
		JTextField name1 = new JTextField();
		JTextField name2 = new JTextField();
		JTextField name3 = new JTextField();
		JTextField name4 = new JTextField();
		Object[] playerNames2Q = {
				"Player 1 name: ", name1,
				"Player 2 name: ", name2
		};
		Object[] playerNames3Q = {
				"Player 1 name: ", name1,
				"Player 2 name: ", name2,
				"Player 3 name: ", name3
		};
		Object[] playerNames4Q = {
				"Player 1 name: ", name1,
				"Player 2 name: ", name2,
				"Player 3 name: ", name3,
				"Player 4 name: ", name4
		};
				
		//create button to start game
		JButton startGameBtn = new JButton("Start");
		startGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					numberOfPlayers = (String) JOptionPane.showInputDialog(contentPanel, "Enter Number Of Players", "Input", JOptionPane.QUESTION_MESSAGE, null, List, "Titan");
					int option;
					switch(Integer.valueOf(numberOfPlayers)){
							case 2: 
								option = JOptionPane.showConfirmDialog(null, playerNames2Q, "Insert Names", JOptionPane.OK_CANCEL_OPTION);
								if (option == JOptionPane.OK_OPTION){
									names.add(name1.getText());
									names.add(name2.getText());
									monopoly.startGame(Integer.valueOf(numberOfPlayers), names);
								}
								break;
							case 3:
								option = JOptionPane.showConfirmDialog(null, playerNames3Q, "Insert Names", JOptionPane.OK_CANCEL_OPTION);
								if (option == JOptionPane.OK_OPTION){
									names.add(name1.getText());
									names.add(name2.getText());
									names.add(name3.getText());
									monopoly.startGame(Integer.valueOf(numberOfPlayers), names);
								}
								break;
							case 4:
								option = JOptionPane.showConfirmDialog(null, playerNames4Q, "Insert Names", JOptionPane.OK_CANCEL_OPTION);
								if (option == JOptionPane.OK_OPTION){
									names.add(name1.getText());
									names.add(name2.getText());
									names.add(name3.getText());
									names.add(name4.getText());
									monopoly.startGame(Integer.valueOf(numberOfPlayers), names);
								}
								break;
							default: option = 0;
					}
						
				JOptionPane.showMessageDialog(contentPanel.getComponent(0), "number of players: "+numberOfPlayers+"\nPlayer1 starts the game");
			}

		});
		startGameBtn.setFont(new Font("Avenir", Font.PLAIN, 13));
		startGameBtn.setBounds(19, 623, 117, 29);
		contentPanel.add(startGameBtn);
		RollDiceButton.setFont(new Font("Avenir", Font.PLAIN, 13));
	}
	
	public void setLabels() {
		lblPlayer = new JLabel("Player");
		lblPlayer.setFont(new Font("Avenir", Font.PLAIN, 13));
		lblPlayer.setBounds(615, 30, 69, 20);
		contentPanel.add(lblPlayer);	
		lblPrice = new JLabel("Money");
		lblPrice.setFont(new Font("Avenir", Font.PLAIN, 13));
		lblPrice.setBounds(751, 30, 69, 20);
		contentPanel.add(lblPrice);	
	}
	
	
	public void playerStatus() {
		lblPlayer.setText(monopoly.getPlayer().getName());
		lblPrice.setText("$"+Integer.toString(monopoly.getPlayer().getMoney().getMoney()));
		
		int R, G, B;
		int column = 599;
		int row = 75;
		for(int i=0; i<28; i++) {
			if(i%8==0) {
				column = 599;
				row += 42;
			}
			column+= 40;
			switch(i) {
				case 0:
	            case 1:
	            	R= 139;
	            	G = 69;
	            	B = 19;		
	                break;
	            case 2:
	            case 3:
	            case 4:
	            	R= 0;
	            	G = 255;
	            	B = 255;	
	                break;
	            case 5:
	            case 6:
	            case 7:
	            	R= 255;
	            	G = 0;
	            	B = 255;	
	                break;
	            case 8:
	            case 9:
	            case 10:
	            	R= 255;
	            	G = 99;
	            	B = 71;	
	                break;
	            case 11:
	            case 12:
	            case 13:
	            	R= 255;
	            	G = 0;
	            	B = 0;	
	                break;
	            case 14:
	            case 15:
	            case 16:
	            	R= 0;
	            	G = 255;
	            	B = 0;	
	                break;
	            case 17:
	            case 18:
	            case 19:
	            	R= 25;
	            	G = 25;
	            	B = 112;	
	                break;
	            case 20:
	            case 21:
	            	R= 100;
	            	G = 100;
	            	B = 19;	
	            	break;
	            case 22:
	            case 23:
	            case 24:
	            case 25:
	            	R= 0;
	            	G = 0;
	            	B = 0;	
	            	break;
	            case 26:
	            case 27:	
	            	R= 100;
	            	G = 100;
	            	B = 100;	
	                break;
	            default:
	            	R= 0;
	            	G = 0;
	            	B = 0;	
			}
			StringBuilder name = new StringBuilder();
			name.append(i).append(" ");
			//String name = Integer.toString(x);
			if (monopoly.checkOwner(i) == -1) {//no one owns property
				//if(true) {
				JCheckBox checkBox = new JCheckBox(name.toString().trim());
				checkBox.setName("CheckBox" + i);
				checkBox.setBackground(new Color(R, G, B));
				checkBox.setForeground(new Color(0, 0, 0));
				checkBox.setBounds(column, row, 29, 29);
				checkBox.setEnabled(false);
				contentPanel.add(checkBox);
			}else if(monopoly.checkOwner(i) == 0) {//player ownes property
				JCheckBox checkBox = new JCheckBox(name.toString().trim());
				checkBox.setName("CheckBox" + i);
				checkBox.setBackground(new Color(R, G, B));
				checkBox.setForeground(new Color(0, 0, 0));
				checkBox.setBounds(611, 75, 29, 29);
				checkBox.setEnabled(false);
				checkBox.setSelected(true);
				contentPanel.add(checkBox);
			}else {//other player owns the property
				JCheckBox checkBox = new JCheckBox(name.toString().trim());
				checkBox.setName("CheckBox" + i);
				checkBox.setBackground(new Color(250, 250, 250));
				checkBox.setForeground(new Color(0, 0, 0));
				checkBox.setBounds(611, 75, 29, 29);
				checkBox.setEnabled(false);
				contentPanel.add(checkBox);
			}
		}
	}
	
	public void moveToken(JLabel tok, int newPosition){
		tok.setLocation(GUIposition.get(newPosition).get(0), GUIposition.get(newPosition).get(1));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        /*
         * Method of ActionListener.
         * This class (MonopolyPanel) implements ActionListener.
        */
		
	}
	
	private Map<Integer, ArrayList<Integer>> createPositionMap() {
		ArrayList<Integer> coordinates = new ArrayList<Integer>();
		
		coordinates.add(0, 530);
		coordinates.add(1, 490);
		GUIposition.put(0, coordinates);
		
		coordinates.add(0, 470);
		coordinates.add(1, 490);
		GUIposition.put(1, coordinates);
		
		coordinates.add(0, 424);
		coordinates.add(1, 490);
		GUIposition.put(2, coordinates);
		
		coordinates.add(0, 374);
		coordinates.add(1, 490);
		GUIposition.put(3, coordinates);
		
		coordinates.add(0, 328);
		coordinates.add(1, 490);
		GUIposition.put(4, coordinates);
		
		coordinates.add(0, 280);
		coordinates.add(1, 490);
		GUIposition.put(5, coordinates);
		
		coordinates.add(0, 232);
		coordinates.add(1, 490);
		GUIposition.put(6, coordinates);
		
		coordinates.add(0, 185);
		coordinates.add(1, 490);
		GUIposition.put(7, coordinates);
		
		coordinates.add(0, 138);
		coordinates.add(1, 490);
		GUIposition.put(8, coordinates);
		
		coordinates.add(0, 89);
		coordinates.add(1, 490);
		GUIposition.put(9, coordinates);
		
		
		
		return null;
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonopolyPanel frame = new MonopolyPanel(monopoly);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
