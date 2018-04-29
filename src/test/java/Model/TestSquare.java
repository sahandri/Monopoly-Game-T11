package Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestSquare {
	Square square;
	Player player1;
	Player player2;
	Board board;
	
	@Before
	public void initialize(){
		//square = new Utilities(100, 12);
		//square.setName("GO square");
		board = new Board();
		player1 = board.addPlayer(0, new Token());
		player1.setDecision(true);
		player2 = board.addPlayer(1, new Token());
		player2.setDecision(true);
		board.purchaseProperty(player2, 12);
	}
	
	@Test
	public void testSellProperty(){
		//assertEquals("GO square", square.getName());
		assertEquals(1500, player1.getMoney().getMoney());
		board.getSquares()[12].sellProperty(player1, board);
		assertEquals(1350, player1.getMoney().getMoney());
		assertEquals(1500, player2.getMoney().getMoney());
		board.getSquares()[12].sellProperty(player2, board);
		assertEquals(1350, player2.getMoney().getMoney());
		player2.setDecision(false);
		assertEquals(1500, player1.getMoney().getMoney());
		assertEquals(1350, player2.getMoney().getMoney());
		board.getSquares()[12].sellProperty(player1, board);
		assertEquals(1500, player1.getMoney().getMoney());
		assertEquals(1350, player2.getMoney().getMoney());
	}
	
	@Test
	public void testGetName() {
		assertEquals("Electric Utility", board.getSquares()[12].getName());
	}
	
}
