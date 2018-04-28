package View;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class RollDiceBtn extends JButton implements ActionListener {
	private ImageIcon MONOPOLY_ICON;
	private PositionMap positionMap; 
	
	//constructor
	public RollDiceBtn(){
		super("Roll Dice");
		
		this.setBounds(369, 623, 117, 29);
		this.setFont(new Font("Avenir", Font.PLAIN, 13));
		
		positionMap = new PositionMap(); 
		
		MONOPOLY_ICON = new ImageIcon(this.getClass().getResource("/img/monopolyIcon.png"));
		Image image = MONOPOLY_ICON.getImage(); 
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING); 
		MONOPOLY_ICON = new ImageIcon(newimg);
	}
	
	//when button is clicked:
	public void clicked(JLayeredPane contentPanel, Monopoly monopoly, Tok tok1, Tok tok2, Tok tok3, Tok tok4){
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
	}
	
	public void moveToken(Tok tok, int newPosition){
		tok.setLocation(positionMap.getX(newPosition), positionMap.getY(newPosition));
	}

	@Override
	public void actionPerformed(ActionEvent e) {}
}
