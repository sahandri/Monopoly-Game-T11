package View;

import javax.swing.*;
import Model.*;

import java.awt.*;
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
    	int position = player.getToken().getPosition();
    	int[][] streets = board.getStreetArray();
    	for(int i=0; i<streets.length;i++) {
    		for(int j=0; j<streets[i].length;j++) {
    			if(position==board.getStreetArray()[i][j]) {
    				player.setDecision(true);
    		    	board.getSquares()[position].perform(player, board);
    		    	player.setDecision(false);
    		    	return true;
    			}
    		}
    	}
    	return false;
    }
    
    public void sellProperty(Player buyer, int position) {
    	board.getSquares()[position].sellProperty(buyer, board);
    }
    
    public int getDiceRoll() {
    	roll = board.roll();
    	return roll;
    }
    
    /**Returns the history string and moves the player*/
    public String move() {
    	int newPosition = (players.get(currentPlayer).getToken().getPosition()+roll)%40;
    	return board.move(players.get(currentPlayer), newPosition);
    }
    
    public ArrayList getProperty(Player player) {
    	ArrayList<Integer> property = new ArrayList<>();
    	for (int i = 0; i < 38; i ++) {
    		if(board.getPropertyOwner(i).equals(player)){
    			property.add(i);
    		}
    	}
    	return property;
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
    
    public int getCurrentPlayerPosition(){
    	return board.getToken(players.get(currentPlayer)).getPosition();
    }
    
    public int checkOwner(int position) {
    	int s=0;
    	int[][] streets = board.getStreetArray(); 
    	for(int i=0; i<streets.length;i++) {
    		for(int j=0; j<streets[i].length;j++) {
    			if(i+j == position) {
    				s = streets[i][j];
    			}
    		}
    	}
    	if(board.getSquares()[s].getOwner()==getPlayer().getID()) {//player owns property
    		return 0;
    	}
    	else if(board.getSquares()[s].getOwner()==-1) {//no one owns property
    		return -1;
    	}else {return 1;}//some body else owns property
    }
}
