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

public class UnMortgageBtn extends ActionButtons implements ActionListener{
	
	public UnMortgageBtn(){
		super("Un-Mortgage");
		
		this.setFont(new Font("Avenir", Font.PLAIN, 13));
		this.setBounds(252, 642, 117, 29);
	}
	
	@Override
	protected void performClick(JLayeredPane contentPanel, Monopoly monopoly, String[] nameOfProperties) {
		String UnmortgageProperty = (String) JOptionPane.showInputDialog(contentPanel, "What property do you want to Un-mortgage?", "select one of your properties", JOptionPane.QUESTION_MESSAGE, MONOPOLY_ICON, nameOfProperties, "Titan");
		int mortgagePosition = monopoly.getPropertyPosition(UnmortgageProperty);
		
		if(monopoly.unmortgage(mortgagePosition)){
			JOptionPane.showMessageDialog(contentPanel, "Property Un-mortgaged","Hooray!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
		}else{
			JOptionPane.showMessageDialog(contentPanel, "Couldn't Un-mortgage This Property","Oops!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
		}
	}

}
