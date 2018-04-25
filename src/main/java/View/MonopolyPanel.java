package View;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;


import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.Timer;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;

public class MonopolyPanel extends JFrame implements ActionListener {
	private final String boardImagePath = "/img/board.jpg";

	private Tok tok1;
	private Tok tok2;
	private Tok tok3;
	private Tok tok4;
	private JLabel lblPlayer;
	private JLabel lblPrice;
	private JLabel timer;
	private CheckBoxPanel checkBoxPanel;
	private PositionMap positionMap;
	
	private JButton buyBtn, btnEndTurn;
	private int startTimeMin, endTimeMin;
	private String numberOfPlayers;
	private ImageIcon MONOPOLY_ICON;
	private JTextArea display;
	
	private static Monopoly monopoly;
			
	private JLayeredPane contentPanel;

	private final JButton rollDiceBtn = new JButton("Roll Dice");

	public MonopolyPanel(Monopoly monopoly) {
		positionMap = new PositionMap();
		this.monopoly = monopoly;
		setUpGUI();
		
		MONOPOLY_ICON = new ImageIcon(this.getClass().getResource("/img/monopolyIcon.png"));
		Image image = MONOPOLY_ICON.getImage(); 
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING); 
		MONOPOLY_ICON = new ImageIcon(newimg);
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
        
        checkBoxPanel = new CheckBoxPanel(contentPanel, monopoly);
        
        setUpBoardImg();
        setUpButtons();
        setLabels();
        checkBoxPanel.setCheckBoxes();
	}
	
	private void setUpBoardImg(){
		//create LABEL that holds board IMAGE 
		JLabel boardImage = new JLabel("");
		boardImage.setBounds(6, 6, 594, 585);
		Image img = new ImageIcon(this.getClass().getResource(boardImagePath)).getImage().getScaledInstance(600, 600, Image.SCALE_AREA_AVERAGING);    //import board.png file as an ImageIcon object
		boardImage.setIcon(new ImageIcon(img));         //set the image of the board to be in the label 
		contentPanel.add(boardImage, new Integer(1));
	}
	
	
	private void setUpTokenImg(){
		if(Integer.parseInt(numberOfPlayers) == 2){
			//setUp tok1 and add it to panel:
			tok1 = new Tok("boot");
			contentPanel.add(tok1, new Integer(2));
			//setUp tok2 and add it to panel:
			tok2 = new Tok("car");
			contentPanel.add(tok2, new Integer(2));
		}else if(Integer.parseInt(numberOfPlayers) == 3){
			//setUp tok1 and add it to panel:
			tok1 = new Tok("boot");
			contentPanel.add(tok1, new Integer(2));
			//setUp tok2 and add it to panel:
			tok2 = new Tok("car");
			contentPanel.add(tok2, new Integer(2));
			//setUp tok3 and add it to panel:
			tok3 = new Tok("dog");
			contentPanel.add(tok3, new Integer(2));
		}else if(Integer.parseInt(numberOfPlayers) == 4){
			//setUp tok1 and add it to panel:
			tok1 = new Tok("boot");
			contentPanel.add(tok1, new Integer(2));
			//setUp tok2 and add it to panel:
			tok2 = new Tok("car");
			contentPanel.add(tok2, new Integer(2));
			//setUp tok3 and add it to panel:
			tok3 = new Tok("dog");
			contentPanel.add(tok3, new Integer(2));
			//setUp tok3 and add it to panel:
			tok4 = new Tok("penguin");
			contentPanel.add(tok4, new Integer(2));
		}
	}
	
	private void setUpButtons(){
		buyPropertyButton();
		startButton();
		rollDiceBtn();
		historyWindow();
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
		btnEndTurn = new JButton("Next Player");
		btnEndTurn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//switch player to the next one
				display.append(monopoly.getName(monopoly.getPlayer()) + " ended turn. \n");
				
				monopoly.changePlayer();
				playerStatus();
				buyBtn.setEnabled(false);
				rollDiceBtn.setEnabled(true);
				btnEndTurn.setEnabled(false);;
			}
		});
		btnEndTurn.setBounds(539, 622, 115, 29);
		contentPanel.add(btnEndTurn);
		btnEndTurn.setEnabled(false);
	}

	private void buyPropertyButton() {
		//create button to buy property
		buyBtn = new JButton("Buy Property");
		buyBtn.setFont(new Font("Avenir", Font.PLAIN, 13));
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                //TODO: implement here what should happen when "Buy Property" button is pressed.
				if(monopoly.getOwnerID(monopoly.getPlayer()) != monopoly.getPlayer().getID()
						&& monopoly.getOwnerID(monopoly.getPlayer()) != -1) {
					if(JOptionPane.showConfirmDialog(contentPanel,
							monopoly.getOwnerName(monopoly.getPlayer())+" do you want to sell?", "",
							JOptionPane.OK_CANCEL_OPTION, JOptionPane.CANCEL_OPTION, MONOPOLY_ICON)
							==JOptionPane.YES_OPTION) {
						boolean success = monopoly.buyProperty(monopoly.getPlayer());
						if(success) {
							Object[] messageObj = {"Property purchased succesfully"};
							JOptionPane.showMessageDialog(contentPanel, messageObj,"Hooray!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
							display.append(monopoly.getName(monopoly.getPlayer()) + " Purchased Property " + monopoly.getSquareName(monopoly.getCurrentPlayerPosition()));
						}else {
							Object[] messageObj = {"Can not purchase this property"};
							JOptionPane.showMessageDialog(contentPanel,messageObj ,"Oops!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
						}
					}
				}else {
					boolean success = monopoly.buyProperty(monopoly.getPlayer());
					if(success) {
						Object[] messageObj = {"Property purchased succesfully"};
						JOptionPane.showMessageDialog(contentPanel, messageObj,"Hooray!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
						display.append(monopoly.getName(monopoly.getPlayer()) + " Purchased Property " + monopoly.getSquareName(monopoly.getCurrentPlayerPosition()));
					}else {
						Object[] messageObj = {"Can not purchase this property"};
						JOptionPane.showMessageDialog(contentPanel,messageObj ,"Oops!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
					}
				}
				playerStatus();
			}
		});
		buyBtn.setBounds(136, 623, 117, 29);
		contentPanel.add(buyBtn);
		buyBtn.setEnabled(false);
	}

	private void rollDiceBtn() {
		//Roll dice and move player
		rollDiceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int roll = monopoly.getDiceRoll();
				JOptionPane.showMessageDialog(contentPanel,"You got: "+Integer.valueOf(roll), "Dice Rolled..", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
				int newPosition = (monopoly.getCurrentPlayerPosition() + roll)%40;
				switch(monopoly.getPlayer().getID()){
					case 1: moveToken(tok1, newPosition);
						break;
					case 2: moveToken(tok2, newPosition);
						break;
					case 3:	moveToken(tok3,newPosition);
						break;
					case 4: moveToken(tok4, newPosition);
						break;
				}
				display.append(monopoly.move());
				buyBtn.setEnabled(true);
				btnEndTurn.setEnabled(true);
				rollDiceBtn.setEnabled(false);
			}
		});
		rollDiceBtn.setBounds(369, 623, 117, 29);
		contentPanel.add(rollDiceBtn);
		rollDiceBtn.setEnabled(false);
	}
	
	public void moveToken(JLabel tok, int newPosition){
		tok.setLocation(positionMap.getX(newPosition), positionMap.getY(newPosition));
	}


	private void startButton(){
		//create button to start game
		StartGameBtn startGameBtn = new StartGameBtn();
		startGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					numberOfPlayers = startGameBtn.clicked(contentPanel, monopoly);		//Returns the number of players after asking for their names and after starting the game

					Object[] messageObj = {"Number of players: "+numberOfPlayers+"\n"+monopoly.getPlayer().getName()+" starts the game"};
					JOptionPane.showMessageDialog(contentPanel, messageObj, "IT'S GAME TIME",JOptionPane.OK_CANCEL_OPTION,MONOPOLY_ICON);
					//do the set up necessary for the start of the game 
					setUpTokenImg();
					playerStatus();
					startTimer();
					startGameBtn.setEnabled(false);
					buyBtn.setEnabled(false);
					rollDiceBtn.setEnabled(true);
			}

		});
		startGameBtn.setFont(new Font("Avenir", Font.PLAIN, 13));
		startGameBtn.setBounds(19, 623, 117, 29);
		contentPanel.add(startGameBtn);
		rollDiceBtn.setFont(new Font("Avenir", Font.PLAIN, 13));
	}
	
	private void startTimer(){
		Calendar calendar = new GregorianCalendar();
		startTimeMin = calendar.get(Calendar.MINUTE);
		endTimeMin = startTimeMin + 5;
		Timer SimpleTimer = new Timer(1000, new ActionListener(){
			@Override
		    public void actionPerformed(ActionEvent e) {
				Calendar calendar1 = new GregorianCalendar();
		       if((calendar1.get(Calendar.MINUTE) < endTimeMin) ){
		    	   timer.setText((endTimeMin - calendar1.get(Calendar.MINUTE)) +" min left");
		       }else{
		    	   timer.setText("Game Over");
		    	   JOptionPane.showMessageDialog(contentPanel,"Game is over!\nWinner is: " + monopoly.selectWinner().getName());
		       }
		    }
		});
		SimpleTimer.start();
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
		
		timer = new JLabel("Timer");
		timer.setFont(new Font("Avenir", Font.PLAIN, 13));
		timer.setBounds(834, 621, 139, 29);
		contentPanel.add(timer);
		
	}
	
	public void playerStatus() {
		lblPlayer.setText(monopoly.getPlayer().getName());
		lblPrice.setText("$"+Integer.toString(monopoly.getPlayer().getMoney().getMoney()));
		checkBoxPanel.playerStatus();
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
        /*
         * Method of ActionListener.
         * This class (MonopolyPanel) implements ActionListener.
        */
		
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
