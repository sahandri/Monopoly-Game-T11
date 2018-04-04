package Model;
import java.util.Random;

public class Dice {
	private int id;			//Dice id (i.e. 1 or 2 depending on which of the 2 Monopoly dices it is)
	
	/*constructor accepting dice id*/
	public Dice(int id){
		this.id = id;
	}
	
	/*constructor without dice id*/
	public Dice(){
		this.id = 0;			//	=0 if Dice id not used/not needed 
	}
	
	/*simulates dice throw
	 * return: random number between 1 and 6*/
	public int roll(){
		Random r = new Random();
		int n = r.nextInt(6) + 1; 	//roll dice to get random number
		return n;
	}
	
}
