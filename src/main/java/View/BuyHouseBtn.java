package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class BuyHouseBtn extends ActionButtons implements ActionListener{

	public BuyHouseBtn(){
		super("Buy House");
		
		this.setFont(new Font("Avenir", Font.PLAIN, 13));
		this.setBounds(368, 603, 117, 29);
	}
	
	@Override
	protected void performClick(JLayeredPane contentPanel, Monopoly monopoly, String[] nameOfProperties) {
		String houseBought = (String) JOptionPane.showInputDialog(contentPanel, "What property do you want to buy a house on?", "select one of your properties", JOptionPane.QUESTION_MESSAGE, MONOPOLY_ICON, nameOfProperties, "Titan");
		int housePosition = monopoly.getPropertyPosition(houseBought);
		
		if(monopoly.buyHouse(housePosition)){
			JOptionPane.showMessageDialog(contentPanel, "House Bought!","Hooray!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
		}else{
			JOptionPane.showMessageDialog(contentPanel, "Couldn't buy an house on this property. All same-color properties must be purchased first!","Oops!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
		}
		
	}

}
