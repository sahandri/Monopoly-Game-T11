package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLayeredPane;

public class UnMortgageBtn extends JButton implements ActionListener{

	public UnMortgageBtn(){
		super("Un-Mortgage");
		
		this.setFont(new Font("Avenir", Font.PLAIN, 13));
		this.setBounds(252, 642, 117, 29);
	}
	
	public void clicked(JLayeredPane contentPanel, Monopoly monopoly){
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}

}
