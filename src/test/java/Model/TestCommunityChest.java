package Model;

import org.junit.*;
import static org.junit.Assert.*;

public class TestCommunityChest{
	private CommunityChest communityChest2;
	private CommunityChest communityChest17;
	private CommunityChest communityChest33;
	private Board board;
	private Player player;
	private Token tok;
	
	
	@Before
	public void initialize(){
		communityChest2 = new CommunityChest(0,2);
		communityChest17 = new CommunityChest(0,17);
		communityChest33 = new CommunityChest(0,33);
		board = new Board();
		tok = new Token();
		player = board.addPlayer(0, tok);

	}
	
	@Test
	public void testPerform0() {
		assertEquals(communityChest2.getPosition(), 2);
		communityChest2.setCard(0);
		communityChest2.perform(player, board);
		assertEquals(1700, player.getMoney().getMoney());
		assertEquals("Advance to Go (Collect $200)", communityChest2.getMessage());
		assertEquals(0, player.getToken().getPosition()); 
	}
	
	@Test
	public void testPerform1() {
		assertEquals(communityChest17.getPosition(), 17);
		communityChest17.setCard(1);
		communityChest17.perform(player, board);
		assertEquals(1700, player.getMoney().getMoney());
		assertEquals("Bank error in your favor – Collect $200", communityChest17.getMessage());
	}
	
	@Test
	public void testPerform2(){
		assertEquals(communityChest33.getPosition(), 33);
		communityChest33.setCard(2); 
		communityChest33.perform(player, board);
		assertEquals("Doctor's fees – Pay $50 ", communityChest33.getMessage()); 
		assertEquals(1450, player.getMoney().getMoney()); // Did player's money change?
	}
	
	@Test
	public void testPerform3_12(){
		communityChest2.setCard(3); // Advance to nearest utility
		communityChest2.perform(player, board);
		assertEquals("From sale of stock you get $50", communityChest2.getMessage()); 
		assertEquals(1550, player.getMoney().getMoney()); // Did player's money change?
	}
	
	@Test
	public void testPerform4(){
		communityChest33.setCard(4); 
		communityChest33.perform(player, board);
		assertEquals("Get out of Jail Free – This card may be kept until needed, or traded/sold", communityChest33.getMessage()); 
		assertEquals(true, player.getOutOfJailCard());
		player.setGetOutOfJail(false);
	}
	
	@Test
	public void testPerform5(){
		communityChest33.setCard(5); 
		communityChest33.perform(player, board);
		assertEquals("Go to Jail – Go directly to Jail – Do not pass Go, do not collect $200", communityChest33.getMessage()); 
		assertEquals(1500, player.getMoney().getMoney()); // Did player's money change?
		assertEquals(10, player.getToken().getPosition()); // Is player in correct position?

		//assertTrue(player.getToken().inJail());
		player.getToken().release();
	}
	
	@Test
	public void testPerform6(){
		communityChest33.setCard(6); 
		board.addPlayer(1, new Token());
		board.addPlayer(2, new Token());
		communityChest33.perform(player, board);
		assertEquals("Grand Opera Night – Collect $50 from every player for opening night seats", communityChest33.getMessage()); 
		assertEquals(1600, player.getMoney().getMoney()); // Did player's money change?
		assertEquals(1450, board.getPlayers().get(1).getMoney().getMoney()); // Did player's money change?
		assertEquals(1450, board.getPlayers().get(2).getMoney().getMoney()); // Did player's money change?

	}
	
	@Test
	public void testPerform7(){
		communityChest33.setCard(7);
		communityChest33.perform(player, board);
		assertEquals("Holiday Fund matures - Receive $100", communityChest33.getMessage()); 
		assertEquals(1600, player.getMoney().getMoney()); // Did player's money change?
	}
	
	@Test
	public void testPerform8(){
		communityChest33.setCard(8);
		communityChest33.perform(player, board);
		assertEquals("Income tax refund – Collect $20", communityChest33.getMessage()); 
		assertEquals(1520, player.getMoney().getMoney()); // Did player's money change?
	}
	
	@Test
	public void testPerform9(){
		communityChest33.setCard(9);
		board.addPlayer(1, new Token());
		board.addPlayer(2, new Token());
		communityChest33.perform(player, board);
		assertEquals("It is your birthday - Collect $10 from each player", communityChest33.getMessage()); 
		assertEquals(1520, player.getMoney().getMoney()); // Did player's money change?
		assertEquals(1490, board.getPlayers().get(1).getMoney().getMoney()); // Did player's money change?
		assertEquals(1490, board.getPlayers().get(2).getMoney().getMoney()); // Did player's money change?
	}
	
	@Test
	public void testPerform10(){
		communityChest33.setCard(10);
		communityChest33.perform(player, board);
		assertEquals("Life insurance matures – Collect $100", communityChest33.getMessage()); 
		assertEquals(1600, player.getMoney().getMoney()); // Did player's money change?
	}
	
	@Test
	public void testPerform11(){
		communityChest33.setCard(11);
		communityChest33.perform(player, board);
		assertEquals("Pay hospital fees of $100", communityChest33.getMessage()); 
		assertEquals(1400, player.getMoney().getMoney()); // Did player's money change?
	}
	
	@Test
	public void testPerform12(){
		communityChest33.setCard(12);
		communityChest33.perform(player, board);
		assertEquals("Pay school fees of $150", communityChest33.getMessage()); 
		assertEquals(1350, player.getMoney().getMoney()); // Did player's money change?
	}
	
	@Test
	public void testPerform13(){
		communityChest33.setCard(13);
		communityChest33.perform(player, board);
		assertEquals("Receive $25 consultancy fee", communityChest33.getMessage()); 
		assertEquals(1525, player.getMoney().getMoney()); // Did player's money change?
	}
	
	@Test
	public void testPerform14(){
		communityChest33.setCard(14);
		communityChest33.perform(player, board);
		assertEquals("You are assessed for street repairs – $40 per house – $115 per hotel", communityChest33.getMessage()); 
		assertEquals(1500, player.getMoney().getMoney()); // Did player's money change?
	}
	
	@Test
	public void testPerform15(){
		communityChest33.setCard(15);
		communityChest33.perform(player, board);
		assertEquals("You have won second prize in a beauty contest – Collect $10", communityChest33.getMessage()); 
		assertEquals(1510, player.getMoney().getMoney()); // Did player's money change?
	}
	
	@Test
	public void testPerform16(){
		communityChest33.setCard(16);
		communityChest33.perform(player, board);
		assertEquals("You inherit $100", communityChest33.getMessage()); 
		assertEquals(1600, player.getMoney().getMoney()); // Did player's money change?
	}
	
	@Test
	public void testPerformDefault(){
		communityChest33.setCard(17);
		communityChest33.perform(player, board);
		assertEquals("Card not in deck!", communityChest33.getMessage()); 
		assertEquals(1500, player.getMoney().getMoney()); // Did player's money change?
		assertEquals(0, player.getToken().getPosition()); // Is player in correct position?
	}
	
}
