package View;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;

public abstract class ActionButtons extends JButton implements ActionListener{
	protected ImageIcon MONOPOLY_ICON;
	
	public ActionButtons(String name){
		super(name);
		
		MONOPOLY_ICON = new ImageIcon(this.getClass().getResource("/img/monopolyIcon.png"));
		Image image = MONOPOLY_ICON.getImage(); 
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING); 
		MONOPOLY_ICON = new ImageIcon(newimg);
	}
	
	public void clicked(JLayeredPane contentPanel, Monopoly monopoly){
		ArrayList<Integer> positionProperties = monopoly.getOwnedStreets();
		String[] nameOfProperties = new String[positionProperties.size()];
		for(int i=0; i<nameOfProperties.length; i++){
			nameOfProperties[i] = monopoly.getStreetName(positionProperties.get(i));
		}
		
		performClick(contentPanel, monopoly, nameOfProperties);
	}
	
	protected abstract void performClick(JLayeredPane contentPanel, Monopoly monopoly, String[] nameOfProperties);
	
	@Override
	public void actionPerformed(ActionEvent e) {}

}
