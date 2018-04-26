package View;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class BuyPropertyBtn extends JButton implements ActionListener{
	private ImageIcon MONOPOLY_ICON;
	private JTextArea display;
	
	public BuyPropertyBtn(){
		super("Buy Property");
		
		this.setBounds(136, 623, 117, 29);
		this.setFont(new Font("Avenir", Font.PLAIN, 13));
		
		MONOPOLY_ICON = new ImageIcon(this.getClass().getResource("/img/monopolyIcon.png"));
		Image image = MONOPOLY_ICON.getImage(); 
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING); 
		MONOPOLY_ICON = new ImageIcon(newimg);
	}
	
	public JTextArea clicked(JLayeredPane contentPanel, Monopoly monopoly, JTextArea display){
		if(monopoly.getOwnerID(monopoly.getPlayer()) != monopoly.getPlayer().getID()
				&& monopoly.getOwnerID(monopoly.getPlayer()) != -1) {
			if(JOptionPane.showConfirmDialog(contentPanel,
					monopoly.getOwnerName(monopoly.getPlayer())+" do you want to sell?", "",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.CANCEL_OPTION, MONOPOLY_ICON)
					==JOptionPane.YES_OPTION) {
				boolean success = monopoly.buyProperty(monopoly.getPlayer());
				if(success) {
					Object[] messageObj = {"Property purchased succesfully"};
					JOptionPane.showMessageDialog(contentPanel, messageObj,"Hooray!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
					display.append(monopoly.getName(monopoly.getPlayer()) + " Purchased Property " + monopoly.getSquareName(monopoly.getCurrentPlayerPosition()));
				}else {
					Object[] messageObj = {"Can not purchase this property"};
					JOptionPane.showMessageDialog(contentPanel,messageObj ,"Oops!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
				}
			}
		}else {
			boolean success = monopoly.buyProperty(monopoly.getPlayer());
			if(success) {
				Object[] messageObj = {"Property purchased succesfully"};
				JOptionPane.showMessageDialog(contentPanel, messageObj,"Hooray!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
				display.append(monopoly.getName(monopoly.getPlayer()) + " Purchased Property " + monopoly.getSquareName(monopoly.getCurrentPlayerPosition()));
			}else {
				Object[] messageObj = {"Can not purchase this property"};
				JOptionPane.showMessageDialog(contentPanel,messageObj ,"Oops!", JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
			}
		}
		return display;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}

}
