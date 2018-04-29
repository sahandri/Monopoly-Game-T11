package View;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class GameTimer extends JLabel implements ActionListener{
	private JLayeredPane contentPanel;
	private Monopoly monopoly;
	private ImageIcon MONOPOLY_ICON;

	public GameTimer(JLayeredPane contentPanel, Monopoly monopoly){
		super("Timer");
		this.contentPanel = contentPanel;
		this.monopoly = monopoly;
		this.setFont(new Font("Avenir", Font.PLAIN, 13));
		this.setBounds(834, 621, 139, 29);
		
		MONOPOLY_ICON = new ImageIcon(this.getClass().getResource("/img/monopolyIcon.png"));
		Image image = MONOPOLY_ICON.getImage(); 
		Image newimg = image.getScaledInstance(50, 50, java.awt.Image.SCALE_AREA_AVERAGING); 
		MONOPOLY_ICON = new ImageIcon(newimg);
	}
	
	public boolean startTimer(int startTimeMin, int endTimeMin){
		Calendar calendar = new GregorianCalendar();
       if((calendar.get(Calendar.MINUTE) < endTimeMin) ){
    	   this.setText((endTimeMin - calendar.get(Calendar.MINUTE)) +" min left");
    	   return true;
       }else{
    	   this.setText("Game Over");
    	   Object[] messageObj = {"Game is over!\nWinner is: " + monopoly.selectWinner().getName()};
    	   JOptionPane.showMessageDialog(contentPanel, messageObj, "",JOptionPane.OK_CANCEL_OPTION, MONOPOLY_ICON);
    	   return false; 
       }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}

}
