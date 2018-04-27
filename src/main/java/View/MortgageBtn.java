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

public class MortgageBtn extends JButton implements ActionListener{
	private ImageIcon MONOPOLY_ICON;
	public MortgageBtn(){
		super("Mortgage");
		
		this.setFont(new Font("Avenir", Font.PLAIN, 13));
		this.setBounds(252, 603, 117, 29);
		
		MONOPOLY_ICON = new ImageIcon(this.getClass().getResource("/img/monopolyIcon.png"));
		Image image = MONOPOLY_ICON.getImage(); 
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING); 
		MONOPOLY_ICON = new ImageIcon(newimg);
		
	}
	
	public void clicked(JLayeredPane contentPanel, Monopoly monopoly){
		ArrayList<Integer> positionProperties = new ArrayList<>();
		String[] nameOfProperties = new String[positionProperties.size()];
		for(int i=0; i<nameOfProperties.length; i++){
			nameOfProperties[i] = monopoly.getStreetName(positionProperties.get(i));
		}
		for(int i=0; i<nameOfProperties.length; i++){
			System.out.println("name: " + nameOfProperties[i]);
		}
		String mortgageProperty = (String) JOptionPane.showInputDialog(contentPanel, "What property do you want to mortgage?", "select one of your properties", JOptionPane.QUESTION_MESSAGE, MONOPOLY_ICON, nameOfProperties, "Titan");
		int mortgagePosition = 0;
		for(int i=0; i<nameOfProperties.length; i++){
			if(mortgageProperty.equals(nameOfProperties[i])){
				mortgagePosition = i; break;
			}
		}
		if(monopoly.mortgage(mortgagePosition)){
			JOptionPane.showMessageDialog(contentPanel, "Property Mortgaged","Hooray!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
		}else{
			JOptionPane.showMessageDialog(contentPanel, "Couldn't Mortgage This Property","Oops!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
		}
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}

}
