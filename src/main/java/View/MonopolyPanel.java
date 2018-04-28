package View;

import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.Timer;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;

public class MonopolyPanel extends JFrame implements ActionListener {
	private final String boardImagePath = "/img/board.jpg";
	private final String iconImagePath = "/img/monopolyIcon.png";
	private ImageIcon MONOPOLY_ICON;
	private int startTimeMin, endTimeMin;
	private String numberOfPlayers;
	
	private Tok tok1;
	private Tok tok2;
	private Tok tok3;
	private Tok tok4;
	private JLabel lblPlayer;
	private JLabel lblPrice;
	private GameTimer timer;
	private CheckBoxPanel checkBoxPanel;
	private RollDiceBtn rollDiceBtn;
	private BuyPropertyBtn buyBtn;
	private JTextArea display;
	private JButton btnEndTurn;
	
	private ActionButtons btnMortgage;
	private ActionButtons btnUnmortgage;
	private ActionButtons btnBuyHouse;
	private ActionButtons btnSellHouse;
	private ActionButtons btnBuyHotel;
	private ActionButtons btnSellHotel;
	
	private static Monopoly monopoly;		
	private JLayeredPane contentPanel;
	
	/*constructor of MonopolyPanel*/
	public MonopolyPanel(Monopoly monopoly) {
		this.monopoly = monopoly;
		
		setUpGUI();
		
		MONOPOLY_ICON = new ImageIcon(this.getClass().getResource(iconImagePath));
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
        
        setUpBoardAndButtons();
        setLabels();
        checkBoxPanel.setCheckBoxes();
	}
	
	private void setUpBoardAndButtons(){
		//setup buttons:
		buyPropertyButton();
		startButton();
		rollDiceBtn();
		historyWindow();
		EndTurnButton();
		MortgageButton();
		UnMortgageButton();
		BuyHouseButton();
		SellHouseButton();
		BuyHotelButton();
		//create LABEL that holds board IMAGE:
		JLabel boardImage = new JLabel("");
		boardImage.setBounds(6, 6, 594, 585);
		Image img = new ImageIcon(this.getClass().getResource(boardImagePath)).getImage().getScaledInstance(600, 600, Image.SCALE_AREA_AVERAGING);
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
	
	private void EndTurnButton() {
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
		btnEndTurn.setBounds(726, 622, 115, 29);
		contentPanel.add(btnEndTurn);
		btnEndTurn.setEnabled(false);
	}

	private void buyPropertyButton() {
		//create button to buy property
		buyBtn = new BuyPropertyBtn();
		buyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				display = buyBtn.clicked(contentPanel, monopoly, display);
				playerStatus();
			}
		});
		contentPanel.add(buyBtn);
		buyBtn.setEnabled(false);
	}

	private void rollDiceBtn() {
		//Roll dice and move player
		rollDiceBtn = new RollDiceBtn();
		rollDiceBtn.setLocation(610, 623);
		rollDiceBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				rollDiceBtn.clicked(contentPanel, monopoly, tok1, tok2, tok3, tok4);
				display.append(monopoly.move());
				buyBtn.setEnabled(true);
				btnEndTurn.setEnabled(true);
				rollDiceBtn.setEnabled(false);
			}
		});
		contentPanel.add(rollDiceBtn);
		rollDiceBtn.setEnabled(false);
	}

	private void startButton(){
		//create button to start game
		StartGameBtn strtgmbtnStart = new StartGameBtn();
		strtgmbtnStart.setText("Start");
		strtgmbtnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					numberOfPlayers = strtgmbtnStart.clicked(contentPanel, monopoly);
					
					setUpTokenImg();
					playerStatus();
					startTimer();
					strtgmbtnStart.setEnabled(false);
					buyBtn.setEnabled(false);
					rollDiceBtn.setEnabled(true);
			}
		});
		contentPanel.add(strtgmbtnStart);
	}
	
	private void MortgageButton(){
		btnMortgage = new MortgageBtn();
		btnMortgage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnMortgage.clicked(contentPanel, monopoly);
				playerStatus();	//update players money lbl
			}
		});
		contentPanel.add(btnMortgage);
	}
	
	private void UnMortgageButton(){
		btnUnmortgage = new UnMortgageBtn();
		btnUnmortgage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnUnmortgage.clicked(contentPanel, monopoly);
				playerStatus();
			}
		});
		contentPanel.add(btnUnmortgage);
	}
	
	private void BuyHouseButton(){
		btnBuyHouse = new BuyHouseBtn();
		btnBuyHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnBuyHouse.clicked(contentPanel, monopoly);
				playerStatus();
			}
		});
		contentPanel.add(btnBuyHouse);
	}
	
	private void SellHouseButton() {
		btnSellHouse = new SellHouseBtn();
		btnSellHouse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnSellHouse.clicked(contentPanel, monopoly);
				playerStatus();
			}
		});
		contentPanel.add(btnSellHouse);
	}
	
	private void BuyHotelButton(){
		btnBuyHotel = new BuyHotelBtn();
		btnBuyHotel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnBuyHotel.clicked(contentPanel, monopoly);
				playerStatus();
			}
		});
		contentPanel.add(btnBuyHotel);
	}
	
	private void startTimer(){
		Calendar calendar = new GregorianCalendar();
		startTimeMin = calendar.get(Calendar.MINUTE);
		endTimeMin = startTimeMin + 5;
		Timer SimpleTimer = new Timer(1000, new ActionListener(){
			@Override
		    public void actionPerformed(ActionEvent e) {
				timer.startTimer(startTimeMin, endTimeMin);
			}
		});
		SimpleTimer.start();
	}
	
	private void setLabels() {
		//label to display current player name:
		lblPlayer = new JLabel("Player");
		lblPlayer.setFont(new Font("Avenir", Font.PLAIN, 13));
		lblPlayer.setBounds(615, 30, 69, 20);
		contentPanel.add(lblPlayer);
		//label to display current player money:
		lblPrice = new JLabel("Money");
		lblPrice.setFont(new Font("Avenir", Font.PLAIN, 13));
		lblPrice.setBounds(751, 30, 69, 20);
		contentPanel.add(lblPrice);	
		//timer:
		timer = new GameTimer(contentPanel, monopoly);
		timer.setBounds(866, 622, 108, 29);
		contentPanel.add(timer);
		/*
		
		btnSellHotel = new SellHotelBtn();
		contentPanel.add(btnSellHotel);*/
	}
	
	private void playerStatus() {
		lblPlayer.setText(monopoly.getPlayer().getName());
		lblPrice.setText("$"+Integer.toString(monopoly.getPlayer().getMoney().getMoney()));
		checkBoxPanel.playerStatus();
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {/*ignore this -> only present cause class implements ActionListener*/}
	
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
