package Model;

import org.junit.*;
import static org.junit.Assert.*;

public class TestCard {
	private ChanceCard chance;
	private CommunityChest com_chest;
	private Board board;
	private Player player;
	
	
	@Before
	public void initialize(){
		chance = new ChanceCard(0,7);
		com_chest = new CommunityChest(0,0);
		board = new Board();
		Token tok = new Token();
		player = board.addPlayer(1, tok);
	}
	
	@Test
	public void testMethods(){
		assertEquals(chance.getPosition(), 7);
		chance.setCard(0);
		chance.perform(player, board);
		assertEquals("Advance to Go (Collect $200)", chance.getMessage());
		assertEquals(1700, player.getMoney().getMoney());
		assertEquals(0, player.getToken().getPosition());
		
		chance.setCard(1);
		chance.perform(player, board);
		assertEquals(1700, player.getMoney().getMoney());
		board.move(player, 24);
		assertEquals(24, player.getToken().getPosition());
		assertEquals("Advance to Illinois Ave. - If you pass Go, collect $200", chance.getMessage());
	}
}
