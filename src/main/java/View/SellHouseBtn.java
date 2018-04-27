package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class SellHouseBtn extends JButton implements ActionListener{

	public SellHouseBtn(){
		super("Sell House");
		
		this.setFont(new Font("Avenir", Font.PLAIN, 13));
		this.setBounds(368, 642, 117, 29);
		
	}
	
	public void clicked(){
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}

}
