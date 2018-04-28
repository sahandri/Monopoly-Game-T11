package View;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class MortgageBtn extends ActionButtons implements ActionListener{
	
	public MortgageBtn(){
		super("Mortgage");
		
		this.setFont(new Font("Avenir", Font.PLAIN, 13));
		this.setBounds(252, 603, 117, 29);
	}
	
	@Override
	protected void performClick(JLayeredPane contentPanel, Monopoly monopoly, String[] nameOfProperties) {
		String mortgageProperty = (String) JOptionPane.showInputDialog(contentPanel, "What property do you want to mortgage?", "select one of your properties", JOptionPane.QUESTION_MESSAGE, MONOPOLY_ICON, nameOfProperties, "Titan");
		int mortgagePosition = monopoly.getPropertyPosition(mortgageProperty);
		
		if(monopoly.mortgage(mortgagePosition)){
			JOptionPane.showMessageDialog(contentPanel, "Property Mortgaged","Hooray!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
		}else{
			JOptionPane.showMessageDialog(contentPanel, "Couldn't Mortgage This Property","Oops!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
		}
		
	}

}
