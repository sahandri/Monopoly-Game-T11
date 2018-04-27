package Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestCard {
	private ChanceCard chance;
	private CommunityChest com_chest;
	
	@Before
	public void initialize(){
		chance = new ChanceCard(0,0);
		com_chest = new CommunityChest(0,0);
		Player player = new Player(0, new Token(), "elliott");
		Board board  = new Board();
	}
	
	@Test
	public void testChanceCard(){
		
	}
}
