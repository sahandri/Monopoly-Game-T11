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
		Corner start = new Corner(0,0);
		squares[0] = start;
		Street mediterranean_ave = new Street(60,1);
		squares[1] = mediterranean_ave;
		CommunityChest com_chest_1 = new CommunityChest(0,2);
		squares[2] = com_chest_1;
		Street baltic_ave = new Street(60,3);
		squares[3] = baltic_ave;
		Tax income_tax = new Tax(200,4);
		squares[4] = income_tax;
		Railroad reading_railroad = new Railroad(200,5);
		squares[5] = reading_railroad;
		Street central_ave = new Street(100,6);
		squares[6] = central_ave;
		ChanceCard chance_1 = new ChanceCard(0,7);
		squares[7] = chance_1;
		Street vermont_ave = new Street(100,8);
		squares[8] = vermont_ave;
		Street connecticut_ave = new Street(120,9);
		squares[9] = connecticut_ave;
		Corner jail = new Corner(0,10);
		squares[10] = jail;
		Street st_charles_place = new Street(140,11);
		squares[11] = st_charles_place;
		Utilities electric = new Utilities(150,12);
		squares[12] = electric;
		Street states_ave = new Street(140,13);
		squares[13] = states_ave;
		Street virginia_ave = new Street(160,14);
		squares[14] = virginia_ave;
		Railroad pennsylvania_railroad = new Railroad(200,15);
		squares[15] = pennsylvania_railroad;
		Street st_james_place = new Street(180,16);
		squares[16] = st_james_place;
		CommunityChest com_chest_2 = new CommunityChest(0,17);
		squares[17] = com_chest_2;
		Street tennessee_ave = new Street(180,18);
		squares[18] = tennessee_ave;
		Street new_york_ave = new Street(200,19);
		squares[19] = new_york_ave;
		Corner free_parking = new Corner(0,20);
		squares[20] = free_parking;
		Street kentucky_ave = new Street(220,21);
		squares[21] = kentucky_ave;
		ChanceCard chance_2 = new ChanceCard(0,22);
		squares[22] = chance_2;
		Street indiana_ave = new Street(220, 23);
		squares[23] = indiana_ave;
		Street illinois_ave = new Street(240, 24);
		squares[24] = illinois_ave;
		Railroad BO_railroad = new Railroad(200, 25);
		squares[25] = BO_railroad;
		Street atlantic_ave = new Street(260, 26);
		squares[26] = atlantic_ave;
		Street ventnor_ave = new Street(260,27);
		squares[27] = ventnor_ave;
		Utilities water = new Utilities(150,28);
		squares[28] = water;
		Street marvin_gardens = new Street(280,29);
		squares[29] = marvin_gardens;
		Corner go_to_jail = new Corner(0,30);
		squares[30] = go_to_jail;
		Street pacific_ave = new Street(300,31);
		squares[31] = pacific_ave;
		Street north_carolina_ave = new Street(300,32);
		squares[32] = north_carolina_ave;
		CommunityChest com_chest_3 = new CommunityChest(0,33);
		squares[33] = com_chest_3;
		Street pen_ave = new Street(320,34);
		squares[34] = pen_ave;
		Railroad short_line = new Railroad(200,35);
		squares[35] = short_line;
		ChanceCard chance_3 = new ChanceCard(0,36);
		squares[36] = chance_3;
		Street park_place = new Street(350,37);
		squares[37] = park_place;
		Tax luxury_tax = new Tax(75, 38);
		squares[38] = luxury_tax;
		Street boardwalk = new Street(400, 39);
		squares[39] = boardwalk;
	}
	
	/* returns a player object and adds that player to the game
	 * with ID equal to the variable id passed as argument
	 * and sets the position for the token of the new player to START;
	 * */
	public Player addPlayer(int id, Token tok){
		tok.move(0);								//set token position to start
		Player player = new Player(id);			//create a new player with given id
		players.put(player, tok);				//add the player to the game and map it to the token.
		
		return player;
	}
	
	/*moves the player to the new position
	 * and calls square.perform(Player) on the square at index=newPosition
	 * */
	public void move(Player player, int newPosition){
		players.get(player).move(newPosition);		//change position of the player's token
		squares[newPosition].perform(player, this);		//perform the instructions the new square position
	}
	
	/*rolls the dice ONCE and returns a random number between 1 and 6*/
	public int roll(){
		return dice.roll();
	}
	
	/* returns the token of the player passed as argument*/
	public Token getToken(Player player){
		return players.get(player);
	}
}
