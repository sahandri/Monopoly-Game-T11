package View;

import javax.swing.*;
import Model.*;


import java.util.ArrayList;

public class Monopoly{
    // monopoly is the Main class for this assignment, used to initialize a monopoly game.

	private Board board;
	private int roll;
	private ArrayList<Player> players = new ArrayList<Player>();
	private int currentPlayer = 0; //index of current player
	
    public static void main(String[] args) {
        // 2 long variables are used to check the game time(i.e., 15 mins or 20 mins), if time exceeded the
        // requirement, then game automatically ends, and whoever has the highest amount of money wins
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){
            e.printStackTrace();
        }
        Monopoly monopoly = new Monopoly();
        MonopolyPanel monopolyPanel = new MonopolyPanel(monopoly);
    }
    
    public void startGame(int numPlayers, ArrayList<String> names) {
    	board = new Board();
    	for(int i = 1; i <= numPlayers; i++) {
    		Token token = new Token();
    		Player tempP = board.addPlayer(i, token, names.get(i-1));
    		players.add(tempP);		//add player to board
    	}
    	
    }
    
    public boolean buyProperty(Player player) {
    	boolean b=false;
    	int squares[][] = board.getStreetArray();
    	for(int i=0; i<squares.length; i++) {
    		for(int j=0; j<squares[i].length; j++) {
    			if(squares[i][j] == player.getToken().getPosition()) {
    				b = true;
    			}
    		}
    	}
    	if(b==false) {return false;}
    	
    	int temp = player.getMoney().getMoney();
    	int position = player.getToken().getPosition();
    	player.setDecision(true);
    	Player owner = board.getPropertyOwner(position);
    	if(owner != null) {
    		owner.setDecision(true);
    	}
    	
    	board.getSquares()[position].perform(player, board);
    	if(owner != null) {
    		owner.setDecision(false);
    	}
    	player.setDecision(false);
    	if(player.getMoney().getMoney() == temp){
    		return false;
    	}
    	return true;
    }
    
   
    public int getOwnerID(Player player) {
    	int position = player.getToken().getPosition();
    	return board.getSquares()[position].getOwner();
    }
    
    public String getOwnerName(Player player){
    	int position = player.getToken().getPosition();
    	int ownerID = board.getSquares()[position].getOwner();
    	for(Player p : players){
    		if(p.getID() == ownerID){return p.getName();}
    	}
    	return "Owner not found";
    }
    
    public int getDiceRoll() {
    	roll = board.roll() + board.roll();
    	return roll;
    }
    
    /**Returns the history string and moves the player*/
    public String move() {
    	int newPosition = (players.get(currentPlayer).getToken().getPosition()+roll)%40;
    	return board.move(players.get(currentPlayer), newPosition);
    }
    
    
    public String getSquareName(int position) {
    	return board.getSquares()[position].toString();
    }
    
    public String getStreetName(int position){
    	return board.getSquares()[position].getName();
    }
    
    public int getMoney(Player player) {
    	return player.getMoney().getMoney();
    }
    
    public String getName(Player player) {
    	return player.getName();
    }
    
    public Token getToken(Player player) {
    	return player.getToken();
    }

    
    public Player getPlayer() {
    	return players.get(currentPlayer);
    }
    
    public void changePlayer() {
    	if(players.size() == 2) {
    		switch(currentPlayer) {
	    		case 0: currentPlayer = 1;
	    			break;
	    		case 1: currentPlayer = 0;
	    			break;
    		}
    	}
    	if(players.size() == 3) {
    		switch(currentPlayer) {
	    		case 0: currentPlayer = 1;
	    			break;
	    		case 1: currentPlayer = 2;
	    			break;
	    		case 2: currentPlayer = 0;
    		}
    	}
    	if(players.size() == 4) {
    		switch(currentPlayer) {
	    		case 0: currentPlayer = 1;
					break;
	    		case 1: currentPlayer = 2;
					break;
	    		case 2: currentPlayer = 3;
	    			break;
	    		case 3: currentPlayer = 0;
    		}
    	}
    }
    
    public Player selectWinner(){
    	Player winner = players.get(0);
    	for(int i=0; i<players.size();i++){
    		if(players.get(i).getMoney().getMoney() > winner.getMoney().getMoney()){
    			winner = players.get(i);
    		}
    	}
    	return winner;
    }
    
    public int getCurrentPlayerPosition(){
    	return board.getToken(players.get(currentPlayer)).getPosition();
    }
    
    public int checkOwner(int position) {
    	int s=0;
    	int counter = 0;
    	int[][] streets = board.getStreetArray(); 
    	for(int i=0; i<streets.length;i++) {
    		for(int j=0; j<streets[i].length;j++) {
    			if(counter == position) {
    				s = streets[i][j];
    			}
    			counter++;
    		}
    	}
    	if(board.getSquares()[s].getOwner()==getPlayer().getID()) {//player owns property
    		return 0;
    	}
    	else if(board.getSquares()[s].getOwner()==-1) {//no one owns property
    		return -1;
    	}else {return 1;}//some body else owns property
    }
  
    
    public int getPropertyPosition(String name) {
    	for(int i=0; i<=39;i++) {
    		if (board.getSquares()[i].getName().equals(name)) {
    			return i;
    		}
    	}
    	return 0;
    }
    
    public boolean mortgage(int location) {
    	return ((Street) board.getSquares()[location]).mortgage(getPlayer());
    }
   
    public boolean unmortgage(int location) {
    	return ((Street) board.getSquares()[location]).unmortgage(getPlayer());
    }
    
    public boolean buyHouse(int location) {
    	return ((Street) board.getSquares()[location]).buyHouse(getPlayer(),board);
    }
    
    public boolean buyHotel(int location) {
    	return ((Street) board.getSquares()[location]).buyHotel(getPlayer());
    }
    
    public boolean sellHouse(int location) {
    	return ((Street) board.getSquares()[location]).sellHouse(getPlayer());
    }
    
    public boolean sellHotel(int location) {
    	return ((Street) board.getSquares()[location]).sellHotel(getPlayer());
    }
    
    public ArrayList getOwnedStreets() {
    	return board.getOwnedStreets(getPlayer());
    }
    
    public ArrayList getOwnedHouses() {
    	return board.getOwnedHouses(getPlayer());
    }
    
    public ArrayList getOwnedHotels() {
    	return board.getOwnedHotels(getPlayer());
    }
    
    public ArrayList getPossibleHotels(){
    	ArrayList<Integer> ownedHouses = getOwnedHouses();
    	ArrayList<Integer> result = new ArrayList<>();
    	for(int i=0; i< ownedHouses.size(); i++){
    		int count =0;
    		for(int j=1; j<ownedHouses.size(); j++){
    			if(ownedHouses.get(i) == ownedHouses.get(j))
    				count++;
    			if((count == 4) && notInArray(i, result))
    				result.add(i);
    		}
    	}
    	return result;
    }
    
    private boolean notInArray(int v, ArrayList<Integer> array){
    	for(int a : array){
    		if(a == v)
    			return false;
    	}
    	return true;
    }
    
    public void perform(int position) {
    	board.getSquares()[position].perform(getPlayer(), board);;
    }
}
