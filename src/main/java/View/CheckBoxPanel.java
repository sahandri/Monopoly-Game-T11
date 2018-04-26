package View;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JCheckBox;
import javax.swing.JLayeredPane;

public class CheckBoxPanel {
	private JLayeredPane contentPanel;
	private Monopoly monopoly;
	private ArrayList<JCheckBox> c = new ArrayList<JCheckBox>();
	private JCheckBox checkBox0 = new JCheckBox("");
	private JCheckBox checkBox1 = new JCheckBox("");
	private JCheckBox checkBox2 = new JCheckBox("");
	private JCheckBox checkBox3 = new JCheckBox("");
	private JCheckBox checkBox4 = new JCheckBox("");
	private JCheckBox checkBox5 = new JCheckBox("");
	private JCheckBox checkBox6 = new JCheckBox("");
	private JCheckBox checkBox7 = new JCheckBox("");
	private JCheckBox checkBox8 = new JCheckBox("");
	private JCheckBox checkBox9 = new JCheckBox("");
	private JCheckBox checkBox10 = new JCheckBox("");
	private JCheckBox checkBox11 = new JCheckBox("");
	private JCheckBox checkBox12 = new JCheckBox("");
	private JCheckBox checkBox13 = new JCheckBox("");
	private JCheckBox checkBox14 = new JCheckBox("");
	private JCheckBox checkBox15 = new JCheckBox("");
	private JCheckBox checkBox16 = new JCheckBox("");
	private JCheckBox checkBox17 = new JCheckBox("");
	private JCheckBox checkBox18 = new JCheckBox("");
	private JCheckBox checkBox19 = new JCheckBox("");
	private JCheckBox checkBox20 = new JCheckBox("");
	private JCheckBox checkBox21 = new JCheckBox("");
	private JCheckBox checkBox22 = new JCheckBox("");
	private JCheckBox checkBox23 = new JCheckBox("");
	private JCheckBox checkBox24 = new JCheckBox("");
	private JCheckBox checkBox25 = new JCheckBox("");
	private JCheckBox checkBox26 = new JCheckBox("");
	private JCheckBox checkBox27 = new JCheckBox("");
	
	public CheckBoxPanel(JLayeredPane contentPanel, Monopoly monopoly){
		this.contentPanel = contentPanel;
		this.monopoly = monopoly;
	}
	
	public void setCheckBoxes(){
		c.add(checkBox0);
		c.add(checkBox1);
		c.add(checkBox2);
		c.add(checkBox3);
		c.add(checkBox4);
		c.add(checkBox5);
		c.add(checkBox6);
		c.add(checkBox7);
		c.add(checkBox8);
		c.add(checkBox9);
		c.add(checkBox10);
		c.add(checkBox11);
		c.add(checkBox12);
		c.add(checkBox13);
		c.add(checkBox14);
		c.add(checkBox15);
		c.add(checkBox16);
		c.add(checkBox17);
		c.add(checkBox18);
		c.add(checkBox19);
		c.add(checkBox20);
		c.add(checkBox21);
		c.add(checkBox22);
		c.add(checkBox23);
		c.add(checkBox24);
		c.add(checkBox25);
		c.add(checkBox26);
		c.add(checkBox27);
		for(int i=0; i<28; i++) {
			contentPanel.add(c.get(i));
		}
	}
	
	public void playerStatus(){
		int R, G, B;
		int column = 599;
		int row = 75;
		for(int i=0; i<28; i++) {
			if(i%8==0) {
				column = 599;
				row += 42;
			}
			column+= 40;
			switch(i) {
				case 0:
	            case 1:
	            	R= 25;
	            	G = 25;
	            	B = 112;		
	                break;
	            case 2:
	            case 3:
	            case 4:
	            	R= 128;
	            	G = 0;
	            	B = 128;	
	                break;
	            case 5:
	            case 6:
	            case 7:
	            	R= 255;
	            	G = 105;
	            	B = 180;	
	                break;
	            case 8:
	            case 9:
	            case 10:
	            	R= 255;
	            	G = 165;
	            	B = 0;	
	                break;
	            case 11:
	            case 12:
	            case 13:
	            	R= 255;
	            	G = 0;
	            	B = 0;	
	                break;
	            case 14:
	            case 15:
	            case 16:
	            	R= 255;
	            	G = 255;
	            	B = 0;	
	                break;
	            case 17:
	            case 18:
	            case 19:
	            	R= 0;
	            	G = 255;
	            	B = 0;	
	                break;
	            case 20:
	            case 21:
	            	R= 132;
	            	G = 112;
	            	B = 255;	
	            	break;
	            case 22:
	            case 23:
	            case 24:
	            case 25:
	            	R= 0;
	            	G = 0;
	            	B = 0;	
	            	break;
	            case 26:
	            case 27:	
	            	R= 100;
	            	G = 100;
	            	B = 100;	
	                break;
	            default:
	            	R= 0;
	            	G = 0;
	            	B = 0;	
			}
			if (monopoly.checkOwner(i) == -1) {//no one owns property
				c.get(i).setBackground(new Color(R, G, B));
				c.get(i).setForeground(new Color(0, 0, 0));
				c.get(i).setBounds(column, row, 29, 29);
				c.get(i).setEnabled(false);
			}else if(monopoly.checkOwner(i) == 0) {//player ownes property
				c.get(i).setBackground(new Color(R, G, B));
				c.get(i).setForeground(new Color(0, 0, 0));
				c.get(i).setBounds(column, row, 29, 29);
				c.get(i).setEnabled(false);
				c.get(i).setSelected(true);
			}else {//other player owns the property
				c.get(i).setBackground(new Color(250, 250, 250));
				c.get(i).setForeground(new Color(0, 0, 0));
				c.get(i).setBounds(column, row, 29, 29);
				c.get(i).setEnabled(false);
			}
		}
	}
}
