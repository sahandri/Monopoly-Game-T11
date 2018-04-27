package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board {
	private Square[] squares;				//stores the 40 squares on the board
	private Map<Player, Token> players;	//stores the players and the token associated with it
	private Map<Integer,Player> deeds;	//map to keep track of deeds: < position_of_property, player >
	private Dice dice;					//dice
	long initialTime = System.currentTimeMillis(), endTime;
	private int[][] streetArray;           //contains position and color group ID of each street
	private ArrayList<Player> playersP = new ArrayList<Player>();
	
	/*constructor*/
	public Board(){
		squares = new Square[40]; 	//initialize the array of Squares
		players = new HashMap<>();	//Stores the players and their token
		deeds = new HashMap<>();		/*add to it using deeds.put(position,Player);		retrieve from it using deeds.get(position)*/
		dice = new Dice();
		streetArraySetUp();
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
		start.setName("Start");
		squares[0] = start;
		Street mediterranean_ave = new Street(60,1, 2);
		mediterranean_ave.setName("Mediterranean ave.");
		squares[1] = mediterranean_ave;
		CommunityChest com_chest_1 = new CommunityChest(0,2);
		com_chest_1.setName("Community Chest");
		squares[2] = com_chest_1;
		Street baltic_ave = new Street(60,3, 4);
		baltic_ave.setName("Baltic ave.");
		squares[3] = baltic_ave;
		Tax income_tax = new Tax(200,4);
		income_tax.setName("Income Tax");
		squares[4] = income_tax;
		Railroad reading_railroad = new Railroad(200,5);
		reading_railroad.setName("Reading Railroad");
		squares[5] = reading_railroad;
		Street central_ave = new Street(100,6, 6);
		central_ave.setName("Central ave.");
		squares[6] = central_ave;
		ChanceCard chance_1 = new ChanceCard(0,7);
		chance_1.setName("Chance Card");
		squares[7] = chance_1;
		Street vermont_ave = new Street(100,8, 6);
		vermont_ave.setName("Vermont ave.");
		squares[8] = vermont_ave;
		Street connecticut_ave = new Street(120,9, 8);
		connecticut_ave.setName("Connecticut ave.");
		squares[9] = connecticut_ave;
		Corner jail = new Corner(0,10);
		jail.setName("Jail");
		squares[10] = jail;
		Street st_charles_place = new Street(140,11, 10);
		st_charles_place.setName("St. Charles Pl.");
		squares[11] = st_charles_place;
		Utilities electric = new Utilities(150,12);
		electric.setName("Electric Utility");
		squares[12] = electric;
		Street states_ave = new Street(140,13, 10);
		states_ave.setName("States ave.");
		squares[13] = states_ave;
		Street virginia_ave = new Street(160,14, 12);
		virginia_ave.setName("Virginia ave.");
		squares[14] = virginia_ave;
		Railroad pennsylvania_railroad = new Railroad(200,15);
		pennsylvania_railroad.setName("Pennsylvania Railroad");
		squares[15] = pennsylvania_railroad;
		Street st_james_place = new Street(180,16, 14);
		st_james_place.setName("St. James Pl.");
		squares[16] = st_james_place;
		CommunityChest com_chest_2 = new CommunityChest(0,17);
		com_chest_2.setName("Community Chest");
		squares[17] = com_chest_2;
		Street tennessee_ave = new Street(180,18, 14);
		tennessee_ave.setName("Tennessee ave.");
		squares[18] = tennessee_ave;
		Street new_york_ave = new Street(200,19, 16);
		new_york_ave.setName("New York ave.");
		squares[19] = new_york_ave;
		Corner free_parking = new Corner(0,20);
		free_parking.setName("Free Parking");
		squares[20] = free_parking;
		Street kentucky_ave = new Street(220,21, 18);
		kentucky_ave.setName("Kentucky ave.");
		squares[21] = kentucky_ave;
		ChanceCard chance_2 = new ChanceCard(0,22);
		chance_2.setName("Chance Card");
		squares[22] = chance_2;
		Street indiana_ave = new Street(220, 23, 18);
		indiana_ave.setName("Indiana ave.");
		squares[23] = indiana_ave;
		Street illinois_ave = new Street(240, 24, 20);
		illinois_ave.setName("Illinois ave.");
		squares[24] = illinois_ave;
		Railroad BO_railroad = new Railroad(200, 25);
		BO_railroad.setName("B&O Railroad");
		squares[25] = BO_railroad;
		Street atlantic_ave = new Street(260, 26, 22);
		atlantic_ave.setName("Atlantic ave.");
		squares[26] = atlantic_ave;
		Street ventnor_ave = new Street(260,27, 22);
		ventnor_ave.setName("Ventnor ave.");
		squares[27] = ventnor_ave;
		Utilities water = new Utilities(150,28);
		water.setName("Water Utility");
		squares[28] = water;
		Street marvin_gardens = new Street(280,29, 24);
		marvin_gardens.setName("Marvin Gardens");
		squares[29] = marvin_gardens;
		Corner go_to_jail = new Corner(0,30);
		go_to_jail.setName("Go To Jail");
		squares[30] = go_to_jail;
		Street pacific_ave = new Street(300,31, 26);
		pacific_ave.setName("Pacific ave.");
		squares[31] = pacific_ave;
		Street north_carolina_ave = new Street(300,32, 26);
		north_carolina_ave.setName("North Carolina ave.");
		squares[32] = north_carolina_ave;
		CommunityChest com_chest_3 = new CommunityChest(0,33);
		com_chest_3.setName("Community Chest");
		squares[33] = com_chest_3;
		Street pen_ave = new Street(320,34, 28);
		pen_ave.setName("Pennsylvania ave.");
		squares[34] = pen_ave;
		Railroad short_line = new Railroad(200,35);
		short_line.setName("Short Line Railroad");
		squares[35] = short_line;
		ChanceCard chance_3 = new ChanceCard(0,36);
		chance_3.setName("Chance Card");
		squares[36] = chance_3;
		Street park_place = new Street(350,37, 35);
		park_place.setName("Park pl.");
		squares[37] = park_place;
		Tax luxury_tax = new Tax(75, 38);
		luxury_tax.setName("Luxury Tax");
		squares[38] = luxury_tax;
		Street boardwalk = new Street(400, 39, 50);
		boardwalk.setName("Boardwalk");
		squares[39] = boardwalk;
	}


	/**
	 * initializing the street variable
	 * street is a 2D array which each row represents a color group ID
	 * we have 8 street groups and one Railroad group and one utility group
	 * The row index represents the IDs starting from 0 to 9
	 * and ID 8 is reserved for Railroad and ID 9 is for utilities
	 * each column is the position for each single street
	 */
	private void streetArraySetUp(){
		streetArray = new int[][]{{1,3}, 	//color group brown
							{6,8,9}, 	//color group Light Blue
							{11,13,14},	//color group Pink
							{16,18,19},	//color group Orange
							{21,23,24},	//color group Red
							{26,27,29},	//color group Yellow
							{31,32,34},	//color group Green
							{37,39},	//color group Dark Blue
							{5,15,25,35},//group Railroad
							{12,28}};	//group utility
	}


	public int[][] getStreetArray(){
		return streetArray;
	}

	
	/* returns a player object and adds that player to the game
	 * with ID equal to the variable id passed as argument
	 * and sets the position for the token of the new player to START;
	 * */
	public Player addPlayer(int id, Token tok){
		tok.move(0);								//set token position to start
		Player player = new Player(id);			//create a new player with given id
		players.put(player, tok);				//add the player to the game and map it to the token.
		playersP.add(player);
		return player;
	}
	
	public Player addPlayer(int id, Token tok, String name){
		tok.move(0);								//set token position to start
		Player player = new Player(id, tok, name);			//create a new player with given id, token and name
		players.put(player, tok);						//add the player to the game and map it to the token.
		playersP.add(player);
		return player;
	}
	
	/*moves the player to the new position
	 * and calls square.perform(Player) on the square at index=newPosition
	 * */
	public String move(Player player, int newPosition){
 		players.get(player).move(newPosition);		//change position of the player's token
 		squares[newPosition].perform(player, this);		//perform the instructions the new square position
		return player.getName() + squares[newPosition].toString();
 	}
	
	/*rolls the dice ONCE and returns a random number between 1 and 6*/
	
	public int roll(){
		return dice.roll();
	}
	
	/* returns the token of the player passed as argument*/
	public Token getToken(Player player){
		return players.get(player);
	}
	
	/*creates a deed and maps it to the player who purchased the property 
	 * then subtract the price from the player's money
	 * */
	public void purchaseProperty(Player player, int propertyPosition){
		deeds.put(propertyPosition, player);
		player.getMoney().sbustractMoney(squares[propertyPosition].getPrice());
	}
	
	/*returns the player who owns the property at the given position */
	public Player getPropertyOwner(int position){
		return deeds.get(position);
	}
	
	public Square[] getSquares() {
		return squares;
	}
	
	public ArrayList getOwnedStreets(Player player) {
    	ArrayList<Integer> streets = new ArrayList<>();
    	int squares[][] = getStreetArray();
    	for(int i=0; i<8; i++) {
    		for(int j=0; j<squares[i].length; j++) {
    			if(getSquares()[squares[i][j]].getOwner()==player.getID()) {
    				streets.add(squares[i][j]);
    			}
    		}
    	}
    	return streets;
    }
    
    public ArrayList getOwnedHouses(Player player) {
    	ArrayList<Integer> houses = new ArrayList<>();
    	int squares[][] = getStreetArray();
    	for(int i=0; i<8; i++) {
    		for(int j=0; j<squares[i].length; j++) {
    			if(getSquares()[squares[i][j]].getOwner()==player.getID() && ((Street) getSquares()[squares[i][j]]).getHouse() > 0) {
    				houses.add(squares[i][j]);
    			}
    		}
    	}
    	return houses;
    }
    
    public ArrayList getOwnedHotels(Player player) {
    	ArrayList<Integer> hotels = new ArrayList<>();
    	int squares[][] = getStreetArray();
    	for(int i=0; i<8; i++) {
    		for(int j=0; j<squares[i].length; j++) {
    			if(getSquares()[squares[i][j]].getOwner()==player.getID() && ((Street) getSquares()[squares[i][j]]).getHotel() > 0) {
    				hotels.add(squares[i][j]);
    			}
    		}
    	}
    	return hotels;
    }
    
    public ArrayList<Player> getPlayers(){
    	return playersP;
    }
}
