package Model;

import java.util.HashMap;
import java.util.Map;

public class Board {
	public Square[] squares;				//stores the 40 squares on the board	
	public Map<Player, Token> players;	//stores the players and the token associated with it
	public Map<Integer,Player> deeds;	//map to keep track of deeds: < position_of_property, player >
	public Dice dice;					//dice
	
	/*constructor*/
	public Board(){
		squares = new Square[40]; 	//initialize the array of Squares
		players = new HashMap<>();	//Stores the players and their token
		deeds = new HashMap<>();		/*add to it using deeds.put(position,Player);		retrieve from it using deeds.get(position)*/
		dice = new Dice();
		setUp();						//set up the board at creation.
	}
	
	/*create, initialize and insert (in order) the squares 
	 * into 'squares' array to set up the initial board
	 * 0-39 total squares:
	 * 		22 Streets
	 * 		6 Cards
	 * 		4 Railroads
	 * 		4 Corners
	 * 		2 Taxes
	 * 		2 Utilities
	 * */
	private void setUp(){
		//TODO
	}
	
	/*add a player to the game by creating a new player object 
	 * with ID equal to the variable id passed as argument
	 * and sets the position for the token of the new player to START;
	 * */
	public void addPlayer(int id, Token tok){
		//TODO
	}
	
	// 	MAIN METHOD
	public static void main(String[] args){
		Corner c = new Corner(0,0);
		System.out.println(c.toString());
	}
}
