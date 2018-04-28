package Model;

import org.junit.*;
import static org.junit.Assert.*;

public class TestCard {
	private ChanceCard chance7;
	private ChanceCard chance22;
	private ChanceCard chance36;
	private CommunityChest com_chest;
	private Board board;
	private Player player;
	private Token tok;
	
	
	@Before
	public void initialize(){
		chance7 = new ChanceCard(0,7);
		chance22 = new ChanceCard(0,22);
		chance36 = new ChanceCard(0,36);
		board = new Board();
		tok = new Token();
		player = board.addPlayer(1, tok);
	}
	
	@Test
	public void testPerform0() {
		assertEquals(chance7.getPosition(), 7);
		chance7.setCard(0);
		chance7.perform(player, board);
		assertEquals(1700, player.getMoney().getMoney());
		assertEquals("Advance to Go (Collect $200)", chance7.getMessage());
		assertEquals(0, player.getToken().getPosition()); //Should have position 24
	}
	
	@Test
	public void testPerform1() {
		assertEquals(chance22.getPosition(), 22);
		chance22.setCard(1);
		chance22.perform(player, board);
		assertEquals(1500, player.getMoney().getMoney());
		assertEquals("Advance to Illinois Ave. - If you pass Go, collect $200", chance22.getMessage());
		assertEquals(24, player.getToken().getPosition()); //Should have position 24
	}
	
	@Test
	public void testPerform2(){
		assertEquals(chance36.getPosition(), 36);
		chance36.setCard(2); // Advance to Go (Collect $200) card
		chance36.perform(player, board);
		assertEquals("Advance to St. Charles Place – If you pass Go, collect $200", chance36.getMessage()); 
		assertEquals(1500, player.getMoney().getMoney()); // Did player's money change?
		assertEquals(11, player.getToken().getPosition()); // Is player in correct position?
	}
	
	@Test
	public void testPerform3_12(){
		chance7.setCard(3); // Advance to nearest utility
		chance7.perform(player, board);
		assertEquals("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.", chance7.getMessage()); 
		assertTrue(1500 > player.getMoney().getMoney()); // Did player's money change? Pay utility rent base on roll.
		assertEquals(12, player.getToken().getPosition()); // Is player in correct position?
	}
	
	@Test
	public void testPerform3_28() {
		chance22.setCard(3);
		board.move(player, 27);
		chance22.perform(player, board);
		assertEquals("Advance token to nearest Utility. If unowned, you may buy it from the Bank. If owned, throw dice and pay owner a total ten times the amount thrown.", chance22.getMessage()); 
		assertTrue(1500 > player.getMoney().getMoney()); // Did player's money change? Pay utility rent base on roll.
		assertEquals(28, player.getToken().getPosition()); // Is player in correct position?
	}
	
	@Test
	public void testPerform4_1(){
		chance36.setCard(4); 
		chance36.perform(player, board);
		assertEquals("Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.", chance36.getMessage()); 
		assertEquals(1500, player.getMoney().getMoney()); // Did player's money change?
		assertEquals(5, player.getToken().getPosition()); // Is player in correct position?
	}
	
	@Test
	public void testPerform4_2(){
		chance36.setCard(4); 
		board.move(player, 14);
		chance36.perform(player, board);
		assertEquals("Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.", chance36.getMessage()); 
		assertEquals(1500, player.getMoney().getMoney()); // Did player's money change?
		assertEquals(15, player.getToken().getPosition()); // Is player in correct position?
	}
	
	@Test
	public void testPerform4_3(){
		chance36.setCard(4); 
		board.move(player, 24);
		chance36.perform(player, board);
		assertEquals("Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.", chance36.getMessage()); 
		assertEquals(1500, player.getMoney().getMoney()); // Did player's money change?
		assertEquals(25, player.getToken().getPosition()); // Is player in correct position?
	}
	
	@Test
	public void testPerform4_4(){
		chance36.setCard(4); 
		board.move(player, 34);
		chance36.perform(player, board);
		assertEquals("Advance token to the nearest Railroad and pay owner twice the rental to which he/she is otherwise entitled. If Railroad is unowned, you may buy it from the Bank.", chance36.getMessage()); 
		assertEquals(1500, player.getMoney().getMoney()); // Did player's money change?
		assertEquals(35, player.getToken().getPosition()); // Is player in correct position?
	}
	
	@Test
	public void testPerform5(){
		chance36.setCard(5); 
		chance36.perform(player, board);
		assertEquals("Bank pays you dividend of $50", chance36.getMessage()); 
		assertEquals(1550, player.getMoney().getMoney()); // Did player's money change?
		assertEquals(0, player.getToken().getPosition()); // Is player in correct position?
	}
	
	@Test
	public void testPerform6(){
		chance36.setCard(6); 
		chance36.perform(player, board);
		assertEquals("Get out of Jail Free – This card may be kept until needed, or traded/sold", chance36.getMessage()); 
		assertEquals(1500, player.getMoney().getMoney()); // Did player's money change?
		assertEquals(0, player.getToken().getPosition()); // Is player in correct position?
		assertEquals(true, player.getOutOfJailCard());
	}
	
	@Test
	public void testPerform7(){
		chance36.setCard(7);
		board.move(player, 1);
		chance36.perform(player, board);
		assertEquals("Go Back 3 Spaces", chance36.getMessage()); 
		assertEquals(1500, player.getMoney().getMoney()); // Did player's money change?
		assertEquals(37, player.getToken().getPosition()); // Is player in correct position?
	}
	
	@Test
	public void testPerform8(){
		chance36.setCard(8);
		chance36.perform(player, board);
		assertEquals("Go to Jail – Go directly to Jail – Do not pass Go, do not collect $200", chance36.getMessage()); 
		assertEquals(1500, player.getMoney().getMoney()); // Did player's money change?
		assertEquals(10, player.getToken().getPosition()); // Is player in correct position?
		assertEquals(true, player.getToken().inJail());
	}
	
	@Test
	public void testPerform9(){
		chance36.setCard(9);
		chance36.perform(player, board);
		assertEquals("Make general repairs on all your property – For each house pay $25 – For each hotel $100", chance36.getMessage()); 
		assertEquals(1500, player.getMoney().getMoney()); // Did player's money change?
		assertEquals(0, player.getToken().getPosition()); // Is player in correct position?
	}
}
