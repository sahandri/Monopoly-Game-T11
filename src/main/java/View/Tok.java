package View;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Tok extends JLabel{
	private final String tokenImagePath = "/img/token/";
	private String path;
	
	public Tok(String name){
		path = tokenImagePath + name + ".png";
		setUpToken();
	}
	
	private void setUpToken(){
		this.setBounds(525, 491, 100, 100);
		Image tokenImg = new ImageIcon(this.getClass().getResource(path)).getImage().getScaledInstance(100, 100, Image.SCALE_AREA_AVERAGING);    //import png file as an ImageIcon object
		/*tokenImage.setIcon(new ImageIcon(tokenImg));         //set the image of the token to be in the label 
		* This is a scaling of the token image. */
		this.setIcon(new ImageIcon(new ImageIcon(tokenImg).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT)));
	}
	
	
}
