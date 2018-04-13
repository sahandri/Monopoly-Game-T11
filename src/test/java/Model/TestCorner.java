package Model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mockito.*;
import static org.mockito.Matchers.*;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestCorner {
	private Corner start;
	private Corner jail;
	private Corner free_parking;
	private Corner go_to_jail;
	
	@Before
    public void initialize(){
		start = new Corner(0,0);
		jail = new Corner(0,10);
		free_parking = new Corner(0,20);
		go_to_jail = new Corner(0,30);
    }
	
	@Test
	public void testToString() {
		assertEquals("GO", start.toString());
		assertEquals("Jail", jail.toString());
		assertEquals("Free Parking", free_parking.toString());
		assertEquals("Go To Jail", go_to_jail.toString());
	}
	
	@Test
	public void testPerform(){
		Board b = new Board();
		Token tok = new Token();
		Player p = b.addPlayer(1, tok);
		
		//test START
		b.getToken(p).move(0);		//move the player to GO square
		start.perform(p,b);			//test perform() with player on start corner			
		assertEquals(b,b);			//no changes should have been made
		
		//test FREE_PARKING
		b.getToken(p).move(20);		//move player to Free_parking square
		free_parking.perform(p, b);
		assertEquals(b,b);			//no changes should have been made
		
		//test GO_TO_JAIL
		b.getToken(p).move(30);		//move player to Go To Jail square
		go_to_jail.perform(p, b);	//test perform() with player on go_to_jail corner
		assertTrue(b.getToken(p).inJail());	//check that the player's token has been set to JAIL mode
		assertEquals(10, b.getToken(p).getPosition());	//check that the token was moved to the jail Square 
				
		//test JAIL
		Board boardMock = mock(Board.class); 
		Token temp = new Token();
		
		temp.move(10);
		temp.imprison();
		when(boardMock.getToken(any(Player.class))).thenReturn(temp);
		when(boardMock.roll()).thenReturn(1);
		jail.perform(p, boardMock);				//test perform() with player in jail if he rolls 2 equal numbers
		assertEquals(1500, p.getMoney().money); //check the money wasn't subtracted
		assertFalse(temp.inJail()); 			//check the player's token is no longer in jail mode
		
		temp.move(10);
		temp.imprison();
		p.setDecision(false);						//set player decision to pay = false
		jail.perform(p, boardMock);					//test perform() with player in jail if he DOES NOT roll 2 equal numbers and he DOES NOT DECIDE to pay
		assertEquals(1500, p.getMoney().money);	//check the money was subtracted
		assertFalse(temp.inJail());	
		
		temp.move(10);
		temp.imprison();
		when(boardMock.roll()).thenReturn(1,2);
		when(boardMock.getToken(any(Player.class))).thenReturn(temp);
		p.setDecision(true);						//set player decision to pay = true
		jail.perform(p, boardMock);					//test perform() with player in jail if he DOES NOT roll 2 equal numbers and he DECIDES to pay
		assertEquals(1500-50, p.getMoney().money);	//check the money was subtracted
		assertFalse(temp.inJail());					//check the player's token is no longer in jail mode
			
		
	}

}
