package Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class TestToken {
	private Token tok;
	
	@Before 
	public void initialize(){
		tok = new Token();
		tok.setName("boot");
	}
	
	@Test
	public void testMove(){
		tok.move(10);
		assertEquals(10, tok.getPosition());
	}
	
	@Test
	public void testImprisonRelease(){
		tok.imprison();
		assertTrue(tok.inJail());	//test imprison
		
		tok.release();
		assertFalse(tok.inJail());	//test release
	}
	
	
}
