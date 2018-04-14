import View.MonopolyPanel;

import javax.swing.*;

import Model.Board;
import Model.Player;
import Model.Square;
import Model.Token;

import java.awt.*;
import java.util.ArrayList;

public class Monopoly {
    // monopoly is the Main class for this assignment, used to initialize a monopoly game.

	private Board board;
	private int roll;
	
    public static void main(String[] args) {
        // 2 long variables are used to check the game time(i.e., 15 mins or 20 mins), if time exceeded the
        // requirement, then game automatically ends, and whoever has the highest amount of money wins
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e){
            e.printStackTrace();
        }

        MonopolyPanel monopolyPanel = new MonopolyPanel();
        monopolyPanel.setVisible(true);
        monopolyPanel.setExtendedState(Frame.MAXIMIZED_BOTH);
    }
    
    public void startGame(int numPlayers) {
    	board = new Board();
    	for(int i = 0; i < numPlayers; i++) {
    		board.addPlayer(i, null);
    	}
    }
    
    public void buyProperty(Player player, int position) {
    	board.purchaseProperty(player, position);
    }
    
    public void sellProperty(Player buyer, int position) {
    	board.getSquares()[position].sellProperty(buyer, board);
    }
    
    public int getDiceRoll() {
    	roll = board.roll();
    	return roll;
    }
    
    public String getHistory(int position) {
    	return board.getSquares()[position].toString();
    }
    
    public void move(Player player) {
    	board.getToken(player).move(roll);
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
}
