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
	private Map<Integer,int[]> GUIposition = new HashMap<Integer,int[]>();
	private final String boardImagePath = "/img/board.jpg";
	private final String tokenImagePath = "/img/token/boot.png";

	private JLabel tok1 = new JLabel();
	private JLabel tok2 = new JLabel();
	private JLabel tok3 = new JLabel();
	private JLabel tok4 = new JLabel();
	private JLabel lblPlayer;
	private JLabel lblPrice;
	private String numberOfPlayers;
	
	private JTextArea display;
	
	private static Monopoly monopoly;
			
	private JLayeredPane contentPanel;
	private final JButton RollDiceButton = new JButton("Roll Dice");


	public MonopolyPanel(Monopoly monopoly) {
		createPositionMap();
		this.monopoly = monopoly;
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
		contentPanel.add(boardImage, new Integer(1));

		//create LABEL that holds token1 IMAGE : boot
		tok1.setBounds(525, 491, 100, 100);
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
		historyWindow();
		//playerStatus();
		EndTurnButton();
	}

	private void historyWindow() {
		/** History scroll area*/
		display = new JTextArea ( 16, 30 );
		display.setFont(new Font("Avenir", Font.PLAIN, 13));
		display.setText("Press Start to begin the game\n");
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
				display.append(monopoly.move());
				display.append(monopoly.getName(monopoly.getPlayer()) + " ended turn. \n");


				monopoly.changePlayer();
				playerStatus();

			}
		});
		btnEndTurn.setBounds(539, 622, 115, 29);
		contentPanel.add(btnEndTurn);
	}

	private void buyPropertyButton() {
		//create button to buy property
		JButton BuyBtn = new JButton("Buy Property");
		BuyBtn.setFont(new Font("Avenir", Font.PLAIN, 13));
		BuyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                //TODO: implement here what should happen when "Buy Property" button is pressed.
				if(monopoly.getOwnerID(monopoly.getPlayer()) != monopoly.getPlayer().getID()
						&& monopoly.getOwnerID(monopoly.getPlayer()) != -1) {
					if(JOptionPane.showConfirmDialog(contentPanel.getComponent(0),"Player " +
							Integer.toString(monopoly.getOwnerID(monopoly.getPlayer()))+" do you want to sell?")
							==JOptionPane.YES_OPTION) {
						boolean success = monopoly.buyProperty(monopoly.getPlayer());
						if(success) {
							JOptionPane.showMessageDialog(contentPanel.getComponent(0), "Property Purchased Succesfully");
						}else {
							JOptionPane.showMessageDialog(contentPanel.getComponent(0), "can not purchase this property");
						}
					}
				}else {
					boolean success = monopoly.buyProperty(monopoly.getPlayer());
					if(success) {
						JOptionPane.showMessageDialog(contentPanel.getComponent(0), "Property Purchased Succesfully");
					}else {
						JOptionPane.showMessageDialog(contentPanel.getComponent(0), "can not purchase this property");
					}
				}
			}
		});
		BuyBtn.setBounds(136, 623, 117, 29);
		contentPanel.add(BuyBtn);
	}

	private void rollDiceButton() {
		//Roll dice and move player
		RollDiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int roll = monopoly.getDiceRoll();
				JOptionPane.showMessageDialog(contentPanel.getComponent(0),"Dice: "+Integer.valueOf(roll));
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
				playerStatus();
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
		tok.setLocation(GUIposition.get(newPosition)[0], GUIposition.get(newPosition)[1]);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        /*
         * Method of ActionListener.
         * This class (MonopolyPanel) implements ActionListener.
        */
		
	}
	
	private void createPositionMap() {
		
		GUIposition.put(0, new int[]{530,490});
		
		GUIposition.put(1, new int[]{470,490});
		
		GUIposition.put(2, new int[]{424,490});
		
		GUIposition.put(3, new int[] {374,490});
		
		GUIposition.put(4, new int[]{328,490});
		
		GUIposition.put(5, new int[]{ 280, 490 });
		
		GUIposition.put(6, new int[]{232 ,490 });
		
		GUIposition.put(7, new int[]{ 185, 490});
		
		GUIposition.put(8, new int[]{ 138,490 });
		
		GUIposition.put(9, new int[]{ 89, 490});
		
		GUIposition.put(10, new int[]{ 89, 490});
		
		GUIposition.put(11, new int[]{ 89, 438});
		
		GUIposition.put(12, new int[]{ 89, 397});
		
		GUIposition.put(13, new int[]{ 89, 343});
		
		GUIposition.put(14, new int[]{ 89, 299});
		
		GUIposition.put(15, new int[]{ 89, 251});
		
		GUIposition.put(16, new int[]{ 89, 203});
		
		GUIposition.put(17, new int[]{89 ,155 });
		
		GUIposition.put(18, new int[]{ 89, 110});
		
		GUIposition.put(19, new int[]{ 89, 57});
		
		GUIposition.put(20, new int[]{ 89, 6});
		
		GUIposition.put(21, new int[]{ 92, 6});
		
		GUIposition.put(22, new int[]{ 136, 6});
		
		GUIposition.put(23, new int[]{ 184, 6});
		
		GUIposition.put(24, new int[]{ 232, 6});
		
		GUIposition.put(25, new int[]{ 279, 6});
		
		GUIposition.put(26, new int[]{ 325, 6});
		
		GUIposition.put(27, new int[]{ 375, 6});
		
		GUIposition.put(28, new int[]{ 421, 6});
		
		GUIposition.put(29, new int[]{ 468, 6});
		
		GUIposition.put(30, new int[]{ 519, 6});
		
		GUIposition.put(31, new int[]{ 519, 66});
		
		GUIposition.put(32, new int[]{ 519, 110});
		
		GUIposition.put(33, new int[]{ 519, 155});
		
		GUIposition.put(34, new int[]{ 519, 205});
		
		GUIposition.put(35, new int[]{519 , 249});
		
		GUIposition.put(36, new int[]{ 519, 300});
		
		GUIposition.put(37, new int[]{ 519, 345});
		
		GUIposition.put(38, new int[]{ 519, 392});
		
		GUIposition.put(39, new int[]{ 519, 440});
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
