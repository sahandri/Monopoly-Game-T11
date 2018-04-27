package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class SellHotelBtn extends JButton implements ActionListener{

	public SellHotelBtn(){
		super("Sell Hotel");
		
		this.setFont(new Font("Avenir", Font.PLAIN, 13));
		this.setBounds(481, 641, 117, 29);
	}
	
	public void clicked(){
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}

}
