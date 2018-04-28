package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class SellHotelBtn extends ActionButtons implements ActionListener{

	public SellHotelBtn(){
		super("Sell Hotel");
		
		this.setFont(new Font("Avenir", Font.PLAIN, 13));
		this.setBounds(481, 641, 117, 29);
	}
	
	@Override
	public void clicked(JLayeredPane contentPanel, Monopoly monopoly){
		ArrayList<Integer> positionProperties = monopoly.getOwnedHotels();
		String[] nameOfProperties = new String[positionProperties.size()];
		for(int i=0; i<nameOfProperties.length; i++){
			nameOfProperties[i] = monopoly.getStreetName(positionProperties.get(i));
		}
		
		performClick(contentPanel, monopoly, nameOfProperties);
	}

	@Override
	protected void performClick(JLayeredPane contentPanel, Monopoly monopoly, String[] nameOfProperties) {
		String hotelProperty = (String) JOptionPane.showInputDialog(contentPanel, "What hotel do you want to sell", "select one of your hotel", JOptionPane.QUESTION_MESSAGE, MONOPOLY_ICON, nameOfProperties, "Titan");
		int hotelPosition = monopoly.getPropertyPosition(hotelProperty);
		
		if(monopoly.buyHotel(hotelPosition)){
			JOptionPane.showMessageDialog(contentPanel, "Hotel Sold!","Hooray!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
		}else{
			JOptionPane.showMessageDialog(contentPanel, "Couldn't sell the hotel","Oops!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
		}
		
	}

}
