package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class BuyHotelBtn extends JButton implements ActionListener{

	public BuyHotelBtn(){
		super("Buy Hotel");
		
		this.setFont(new Font("Avenir", Font.PLAIN, 13));
		this.setBounds(481, 603, 117, 29);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}

}
