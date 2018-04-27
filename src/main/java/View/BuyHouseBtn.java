package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class BuyHouseBtn extends JButton implements ActionListener{

	public BuyHouseBtn(){
		super("Buy House");
		
		this.setFont(new Font("Avenir", Font.PLAIN, 13));
		this.setBounds(368, 603, 117, 29);
		
	}
	
	public void clicked(){
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}

}
