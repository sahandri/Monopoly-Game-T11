package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
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
	private final String boardImagePath = "/board.jpg";
	private final String tokenImagePath = "/boot.png";

	private JLayeredPane contentPanel;
	private final JButton RollDiceButton = new JButton("Roll Dice");
	//private Monopoly monopoly;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MonopolyPanel frame = new MonopolyPanel();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MonopolyPanel() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1000 , 700);
		contentPanel = new JLayeredPane();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
        //create label that holds board image 
		JLabel boardImage = new JLabel("");
		boardImage.setBounds(6, 6, 594, 585);
		Image img = new ImageIcon(this.getClass().getResource(boardImagePath)).getImage().getScaledInstance(600, 600, Image.SCALE_AREA_AVERAGING);    //import board.png file as an ImageIcon object
		boardImage.setIcon(new ImageIcon(img));         //set the image of the board to be in the label 
		contentPanel.add(boardImage, new Integer(1));                   //add the label to the board
		
		//create label that holds token image 
				JLabel tokenImage = new JLabel("");
				tokenImage.setBounds(6, 6, 594, 585);
				Image tokenImg = new ImageIcon(this.getClass().getResource(tokenImagePath)).getImage().getScaledInstance(600, 600, Image.SCALE_AREA_AVERAGING);    //import board.png file as an ImageIcon object
				tokenImage.setIcon(new ImageIcon(tokenImg));         //set the image of the board to be in the label 
				contentPanel.add(tokenImage, new Integer(2));                   //add the label to the board
		
		String[] List = new String[] {"1","2","3","4"};
        //create button to start game
		JButton startGameBtn = new JButton("Start");
		startGameBtn.addActionListener(new ActionListener() {
				//TODO: implement here what should happen when "start" button is pressed.
				public void actionPerformed(ActionEvent arg0) {
					String numberOfPlayers = (String) JOptionPane.showInputDialog(contentPanel, "Enter Number Of Players", "Input", JOptionPane.QUESTION_MESSAGE,
					        null, List, "Titan");
					JOptionPane.showMessageDialog(contentPanel.getComponent(0),
							"number of players: "+numberOfPlayers+" player1 starts the game");
					//calls monopoly.startGame(Integer.valueOf(numberOfPlayers))
	            }

		});
		startGameBtn.setFont(new Font("Avenir", Font.PLAIN, 13));
		startGameBtn.setBounds(19, 623, 117, 29);
		contentPanel.add(startGameBtn);
		
		
		//Roll dice and move player
		RollDiceButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//int roll = monopoly.rollDice();
				//JOptionPane.showMessageDialog(contentPanel.getComponent(0),
				//"Dice: "+Integer.valueOf(roll));
			}
		});
		RollDiceButton.setBounds(374, 623, 165, 29);
		contentPanel.add(RollDiceButton);
		
		
		
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
		
		JLabel lblPlayer = new JLabel("Player");
		lblPlayer.setBounds(615, 30, 69, 20);
		contentPanel.add(lblPlayer);
		
		JLabel lblPrice = new JLabel("Money");
		lblPrice.setBounds(751, 30, 69, 20);
		contentPanel.add(lblPrice);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("");
		chckbxNewCheckBox.setBackground(new Color(139, 69, 19));
		chckbxNewCheckBox.setForeground(new Color(0, 0, 0));
		chckbxNewCheckBox.setBounds(611, 75, 29, 29);
		contentPanel.add(chckbxNewCheckBox);
		
		JCheckBox checkBox = new JCheckBox("");
		checkBox.setForeground(Color.BLACK);
		checkBox.setBackground(new Color(139, 69, 19));
		checkBox.setBounds(647, 75, 29, 29);
		contentPanel.add(checkBox);
		
		JCheckBox checkBox_1 = new JCheckBox("");
		checkBox_1.setForeground(Color.BLACK);
		checkBox_1.setBackground(new Color(0, 255, 255));
		checkBox_1.setBounds(703, 75, 29, 29);
		contentPanel.add(checkBox_1);
		
		JCheckBox checkBox_2 = new JCheckBox("");
		checkBox_2.setForeground(Color.BLACK);
		checkBox_2.setBackground(new Color(0, 255, 255));
		checkBox_2.setBounds(739, 75, 29, 29);
		contentPanel.add(checkBox_2);
		
		JCheckBox checkBox_3 = new JCheckBox("");
		checkBox_3.setForeground(Color.BLACK);
		checkBox_3.setBackground(new Color(0, 255, 255));
		checkBox_3.setBounds(777, 75, 29, 29);
		contentPanel.add(checkBox_3);
		
		JCheckBox checkBox_4 = new JCheckBox("");
		checkBox_4.setForeground(Color.BLACK);
		checkBox_4.setBackground(new Color(255, 0, 255));
		checkBox_4.setBounds(840, 75, 29, 29);
		contentPanel.add(checkBox_4);
		
		JCheckBox checkBox_5 = new JCheckBox("");
		checkBox_5.setForeground(Color.BLACK);
		checkBox_5.setBackground(new Color(255, 0, 255));
		checkBox_5.setBounds(876, 75, 29, 29);
		contentPanel.add(checkBox_5);
		
		JCheckBox checkBox_6 = new JCheckBox("");
		checkBox_6.setForeground(Color.BLACK);
		checkBox_6.setBackground(new Color(255, 0, 255));
		checkBox_6.setBounds(913, 75, 29, 29);
		contentPanel.add(checkBox_6);
		
		JCheckBox checkBox_7 = new JCheckBox("");
		checkBox_7.setForeground(Color.BLACK);
		checkBox_7.setBackground(Color.ORANGE);
		checkBox_7.setBounds(611, 117, 29, 29);
		contentPanel.add(checkBox_7);
		
		JCheckBox checkBox_8 = new JCheckBox("");
		checkBox_8.setForeground(Color.BLACK);
		checkBox_8.setBackground(Color.ORANGE);
		checkBox_8.setBounds(647, 117, 29, 29);
		contentPanel.add(checkBox_8);
		
		JCheckBox checkBox_9 = new JCheckBox("");
		checkBox_9.setForeground(Color.BLACK);
		checkBox_9.setBackground(Color.ORANGE);
		checkBox_9.setBounds(683, 117, 29, 29);
		contentPanel.add(checkBox_9);
		
		JCheckBox checkBox_10 = new JCheckBox("");
		checkBox_10.setForeground(Color.BLACK);
		checkBox_10.setBackground(Color.RED);
		checkBox_10.setBounds(739, 117, 29, 29);
		contentPanel.add(checkBox_10);
		
		JCheckBox checkBox_11 = new JCheckBox("");
		checkBox_11.setForeground(Color.BLACK);
		checkBox_11.setBackground(Color.RED);
		checkBox_11.setBounds(777, 117, 29, 29);
		contentPanel.add(checkBox_11);
		
		JCheckBox checkBox_12 = new JCheckBox("");
		checkBox_12.setForeground(Color.BLACK);
		checkBox_12.setBackground(Color.RED);
		checkBox_12.setBounds(813, 117, 29, 29);
		contentPanel.add(checkBox_12);
		
		JCheckBox checkBox_13 = new JCheckBox("");
		checkBox_13.setForeground(Color.BLACK);
		checkBox_13.setBackground(Color.YELLOW);
		checkBox_13.setBounds(865, 117, 29, 29);
		contentPanel.add(checkBox_13);
		
		JCheckBox checkBox_14 = new JCheckBox("");
		checkBox_14.setForeground(Color.BLACK);
		checkBox_14.setBackground(Color.YELLOW);
		checkBox_14.setBounds(901, 117, 29, 29);
		contentPanel.add(checkBox_14);
		
		JCheckBox checkBox_15 = new JCheckBox("");
		checkBox_15.setForeground(Color.BLACK);
		checkBox_15.setBackground(Color.YELLOW);
		checkBox_15.setBounds(938, 117, 29, 29);
		contentPanel.add(checkBox_15);
		
		JCheckBox checkBox_16 = new JCheckBox("");
		checkBox_16.setForeground(Color.BLACK);
		checkBox_16.setBackground(Color.GREEN);
		checkBox_16.setBounds(611, 154, 29, 29);
		contentPanel.add(checkBox_16);
		
		JCheckBox checkBox_17 = new JCheckBox("");
		checkBox_17.setForeground(Color.BLACK);
		checkBox_17.setBackground(Color.GREEN);
		checkBox_17.setBounds(647, 154, 29, 29);
		contentPanel.add(checkBox_17);
		
		JCheckBox checkBox_18 = new JCheckBox("");
		checkBox_18.setForeground(Color.BLACK);
		checkBox_18.setBackground(Color.GREEN);
		checkBox_18.setBounds(683, 154, 29, 29);
		contentPanel.add(checkBox_18);
		
		JCheckBox checkBox_19 = new JCheckBox("");
		checkBox_19.setForeground(Color.BLACK);
		checkBox_19.setBackground(Color.BLUE);
		checkBox_19.setBounds(739, 154, 29, 29);
		contentPanel.add(checkBox_19);
		
		JCheckBox checkBox_20 = new JCheckBox("");
		checkBox_20.setForeground(Color.BLACK);
		checkBox_20.setBackground(Color.BLUE);
		checkBox_20.setBounds(777, 154, 29, 29);
		contentPanel.add(checkBox_20);
		
		JCheckBox checkBox_21 = new JCheckBox("");
		checkBox_21.setForeground(Color.BLACK);
		checkBox_21.setBackground(Color.BLACK);
		checkBox_21.setBounds(611, 206, 29, 29);
		contentPanel.add(checkBox_21);
		
		JCheckBox checkBox_22 = new JCheckBox("");
		checkBox_22.setForeground(Color.BLACK);
		checkBox_22.setBackground(Color.BLACK);
		checkBox_22.setBounds(647, 206, 29, 29);
		contentPanel.add(checkBox_22);
		
		JCheckBox checkBox_23 = new JCheckBox("");
		checkBox_23.setForeground(Color.BLACK);
		checkBox_23.setBackground(Color.BLACK);
		checkBox_23.setBounds(683, 206, 29, 29);
		contentPanel.add(checkBox_23);
		
		JCheckBox checkBox_24 = new JCheckBox("");
		checkBox_24.setForeground(Color.BLACK);
		checkBox_24.setBackground(Color.BLACK);
		checkBox_24.setBounds(719, 206, 29, 29);
		contentPanel.add(checkBox_24);
		
		JCheckBox checkBox_25 = new JCheckBox("");
		checkBox_25.setForeground(Color.BLACK);
		checkBox_25.setBackground(Color.GRAY);
		checkBox_25.setBounds(813, 206, 29, 29);
		contentPanel.add(checkBox_25);
		
		JCheckBox checkBox_26 = new JCheckBox("");
		checkBox_26.setForeground(Color.BLACK);
		checkBox_26.setBackground(Color.GRAY);
		checkBox_26.setBounds(850, 206, 29, 29);
		contentPanel.add(checkBox_26);

		/** History scroll area*/
		JTextArea display = new JTextArea ( 16, 30 );
		display.setText("This will hold previous actions.");
		display.setEditable ( false );
		JScrollPane scrollPane = new JScrollPane(display);
		scrollPane.setBounds(610, 370, 364, 212);
		contentPanel.add(scrollPane);
		scrollPane.setVerticalScrollBarPolicy ( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
		
		
		JLabel lblHistory = new JLabel("History");
		lblHistory.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblHistory.setBounds(611, 330, 75, 29);
		contentPanel.add(lblHistory);
		
		JLabel Token1 = new JLabel("");
		Token1.setBounds(526, 529, 69, 20);
		contentPanel.add(Token1);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        /*
         * Method of ActionListener.
         * This class (MonopolyPanel) implements ActionListener.
        */
		
	}
}
