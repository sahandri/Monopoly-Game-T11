package View;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

public class GameTimer extends JLabel implements ActionListener{
	private JLayeredPane contentPanel;
	private Monopoly monopoly;

	public GameTimer(JLayeredPane contentPanel, Monopoly monopoly){
		super("Timer");
		this.contentPanel = contentPanel;
		this.monopoly = monopoly;
		this.setFont(new Font("Avenir", Font.PLAIN, 13));
		this.setBounds(834, 621, 139, 29);
	}
	
	public void startTimer(int startTimeMin, int endTimeMin){
		Calendar calendar = new GregorianCalendar();
       if((calendar.get(Calendar.MINUTE) < endTimeMin) ){
    	   this.setText((endTimeMin - calendar.get(Calendar.MINUTE)) +" min left");
       }else{
    	   this.setText("Game Over");
    	   JOptionPane.showMessageDialog(contentPanel,"Game is over!\nWinner is: " + monopoly.selectWinner().getName());
       }
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {}

}
