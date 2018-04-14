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
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JScrollPane;

public class MonopolyPanel extends JFrame implements ActionListener {
	private final String boardImagePath = "/board.jpg";

	private JPanel contentPanel;

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
		contentPanel = new JPanel();
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPanel);
		contentPanel.setLayout(null);
		
        //create label that holds board image 
		JLabel boardImage = new JLabel("");
		boardImage.setBounds(6, 6, 594, 585);
		Image img = new ImageIcon(this.getClass().getResource(boardImagePath)).getImage().getScaledInstance(600, 600, Image.SCALE_AREA_AVERAGING);    //import board.png file as an ImageIcon object
		boardImage.setIcon(new ImageIcon(img));         //set the image of the board to be in the label 
		contentPanel.add(boardImage);                   //add the label to the board
		
        //create button to start game
		JButton startGameBtn = new JButton("Start");
		startGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO: implement here what should happen when "start" button is pressed.
			}
		});
		startGameBtn.setFont(new Font("Avenir", Font.PLAIN, 13));
		startGameBtn.setBounds(19, 623, 117, 29);
		contentPanel.add(startGameBtn);
		
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
		
        //create button to end the game
		JButton EndGameBtn = new JButton("End Game");
		EndGameBtn.setFont(new Font("Avenir", Font.PLAIN, 13));
		EndGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
                //TODO: implement here what should happen when "End Game" button is pressed.
			}
		});
		EndGameBtn.setBounds(252, 623, 117, 29);
		contentPanel.add(EndGameBtn);
		
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
