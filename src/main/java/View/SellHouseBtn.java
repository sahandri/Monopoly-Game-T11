package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class SellHouseBtn extends ActionButtons implements ActionListener{

	public SellHouseBtn(){
		super("Sell House");
		
		this.setFont(new Font("Avenir", Font.PLAIN, 13));
		this.setBounds(368, 642, 117, 29);
		
	}
	
	@Override
	public void clicked(JLayeredPane contentPanel, Monopoly monopoly){
		ArrayList<Integer> positionProperties = monopoly.getOwnedHouses();
		String[] nameOfProperties = new String[positionProperties.size()];
		for(int i=0; i<nameOfProperties.length; i++){
			nameOfProperties[i] = monopoly.getStreetName(positionProperties.get(i));
		}
		
		performClick(contentPanel, monopoly, nameOfProperties);
	}
	
	@Override
	protected void performClick(JLayeredPane contentPanel, Monopoly monopoly, String[] nameOfProperties) {
		
		String houseBought = (String) JOptionPane.showInputDialog(contentPanel, "What house do you want to sell?", "select one of your houses", JOptionPane.QUESTION_MESSAGE, MONOPOLY_ICON, nameOfProperties, "Titan");
		int housePosition = monopoly.getPropertyPosition(houseBought);
		
		if(monopoly.sellHouse(housePosition)){
			JOptionPane.showMessageDialog(contentPanel, "House Bought!","Hooray!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
		}else{
			JOptionPane.showMessageDialog(contentPanel, "Couldn't sell the house on this property","Oops!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
		}
	}

}
