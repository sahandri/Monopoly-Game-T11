package View;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class StartGameBtn extends JButton implements ActionListener{
			String[] List = new String[] {"2","3","4"};
			private ImageIcon MONOPOLY_ICON;
			private int numberOfPlayers;
			private ArrayList<String> names = new ArrayList<>();
			private JTextField name1 = new JTextField();
			private JTextField name2 = new JTextField();
			private JTextField name3 = new JTextField();
			private JTextField name4 = new JTextField();
			
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
	
	public StartGameBtn(){
		super("start");
		
		MONOPOLY_ICON = new ImageIcon(this.getClass().getResource("/img/monopolyIcon.png"));
		Image image = MONOPOLY_ICON.getImage(); 
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING); 
		MONOPOLY_ICON = new ImageIcon(newimg);
	}

	public String clicked(JLayeredPane contentPanel, Monopoly monopoly){
		int option;
		String numP = (String) JOptionPane.showInputDialog(contentPanel, "How many of you playing?", "select number of players", JOptionPane.QUESTION_MESSAGE, MONOPOLY_ICON, List, "Titan");
		numberOfPlayers = Integer.parseInt(numP);
		switch(Integer.valueOf(numberOfPlayers)){
				case 2: 
					option = JOptionPane.showConfirmDialog(contentPanel, playerNames2Q, "insert players names", JOptionPane.OK_CANCEL_OPTION, JOptionPane.CLOSED_OPTION,MONOPOLY_ICON);
					if (option == JOptionPane.OK_OPTION){
						names.add(name1.getText());
						names.add(name2.getText());
						monopoly.startGame(Integer.valueOf(numberOfPlayers), names);
					}
					break;
				case 3:
					option = JOptionPane.showConfirmDialog(contentPanel, playerNames3Q, "insert players names", JOptionPane.OK_CANCEL_OPTION, JOptionPane.CLOSED_OPTION,MONOPOLY_ICON);
					if (option == JOptionPane.OK_OPTION){
						names.add(name1.getText());
						names.add(name2.getText());
						names.add(name3.getText());
						monopoly.startGame(Integer.valueOf(numberOfPlayers), names);
					}
					break;
				case 4:
					option = JOptionPane.showConfirmDialog(contentPanel, playerNames4Q, "insert players names", JOptionPane.OK_CANCEL_OPTION,JOptionPane.CLOSED_OPTION,MONOPOLY_ICON);
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
		return numP;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
