package Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestDice {
	private Dice dice;
	
	@Before 
	public void initialize(){
		dice = new Dice();
	}
	
	@Test
	public void testRoll(){
		int n = dice.roll();
		assertTrue((1<=n)&&(n<=6));
	}
}
