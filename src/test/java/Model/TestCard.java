package Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestCard {
	private ChanceCard chance;
	private CommunityChest com_chest;
	private Player player;
	private Board board;
	
	@Before
	public void initialize(){
		chance = new ChanceCard(0,7);
		com_chest = new CommunityChest(0,0);
		player = new Player(0, new Token(), "elliott");
		board  = new Board();
	}
	
	@Test
    public void testToString(){
        assertEquals(" Chance! /n",chance.toString());
    }
}
