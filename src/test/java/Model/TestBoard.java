package Model;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito.*;
import static org.mockito.Matchers.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

public class TestBoard {
	private Board board;
	private Token tok;
	private Player p ;
	private final int[] streets = {1,3,6,8,9,11,13,14,16,18,19,21,23,24,26,27,29,31,32,34,37,39};
	private final int[] corners = {0,10,20,30};
	private final int[] railroads = {5,15,25,35};
	private final int[] communityChest = {2,17,33};
	private final int[] chanceCards = {7,22,36};
	
	@Before
    public void initialize(){
		board = new Board();
		tok = new Token();
		p = board.addPlayer(1, tok);
    }
	
	@Before
	public void testAddPlayer(){
		Player p1 = board.addPlayer(2, tok, "player1");
		assertEquals(2, p1.getID());							//check the player id was added correctly
		assertEquals(tok, board.getToken(p1)); 				//check the token was mapped correctly
		assertEquals(0, board.getToken(p1).getPosition());	//check the token was correctly placed on the START square
		assertEquals("player1", p1.getName());
	}
	
	@Test
	public void testSetUp(){
		
		//check the board has the right number of squares (40).
		assertEquals(40, board.getSquares().length);	
		//check that all the squares were successfully initialized
		for(int i=0;i<40;i++){assertTrue(board.getSquares()[i]!=null);}
		
		//check all taxes were placed correctly on the board
		assertTrue(board.getSquares()[4] instanceof Tax);
		assertTrue(board.getSquares()[38] instanceof Tax);
		
		//check all utilities were placed correctly on the board
		assertTrue(board.getSquares()[12] instanceof Utilities);
		assertTrue(board.getSquares()[28] instanceof Utilities);
		
		//check all streets were placed correctly on the board
		for(int position : streets){assertTrue(board.getSquares()[position] instanceof Street);}
		
		//check all corners were placed correctly on the board
		for(int position : corners){assertTrue(board.getSquares()[position] instanceof Corner);}
		
		//check all railroads were placed correctly on the board
		for(int position : railroads){assertTrue(board.getSquares()[position] instanceof Railroad);}
		
		//check all community chest cards were placed correctly on the board
		for(int position : communityChest){assertTrue(board.getSquares()[position] instanceof CommunityChest);}
		
		//check all chance cards were placed correctly on the board
		for(int position : chanceCards){assertTrue(board.getSquares()[position] instanceof ChanceCard);}
	}
	
	@Test
	public void testMove(){
		Square mockSquare = mock(Corner.class);
		board.getSquares()[20] = mockSquare;
		
		board.move(p, 20);									//move token of player to free_parking
		assertEquals(board.getToken(p).getPosition(), 20); 	//check the token was moved correctly
		
		verify(mockSquare, times(1)).perform(any(Player.class), any(Board.class)); //check that perform() method of the square in the newPosition has been called once.
	}
	
	@Test
	public void testPurchaseProperty(){
		board.purchaseProperty(p, 3);
		
		assertEquals(p, board.getPropertyOwner(3));			//check the deed was created successfully 
		assertEquals((1500-60), p.getMoney().getMoney());			//check player got charged
	}
	
	@Test
	public void testOwnedStreets() {
		board.getSquares()[1].setOwner(p);
		board.getSquares()[3].setOwner(p);
		ArrayList s = new ArrayList();
		s.add(1);
		s.add(3);
		assertEquals(s,board.getOwnedStreets(p));
	}
	
	@Test
	public void testOwnedHouses() {
		ArrayList h = new ArrayList();
		h.add(31);
		board.getSquares()[31].setOwner(p);
    	board.getSquares()[32].setOwner(p);
    	board.getSquares()[34].setOwner(p);
    	((Street) board.getSquares()[31]).buyHouse(p, board);
		assertEquals(h,board.getOwnedHouses(p));
	}
	
	@Test
	public void testOwnedHotels() {
		ArrayList h = new ArrayList();
		h.add(31);
		board.getSquares()[31].setOwner(p);
    	board.getSquares()[32].setOwner(p);
    	board.getSquares()[34].setOwner(p);
    	((Street) board.getSquares()[31]).buyHouse(p, board);
    	((Street) board.getSquares()[31]).buyHouse(p, board);
    	((Street) board.getSquares()[31]).buyHouse(p, board);
    	((Street) board.getSquares()[31]).buyHouse(p, board);
    	((Street) board.getSquares()[31]).buyHotel(p);
		assertEquals(h,board.getOwnedHotels(p));
	}
}
