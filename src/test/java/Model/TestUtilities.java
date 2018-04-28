package Model;
import org.junit.*;
import static org.junit.Assert.*;

public class TestUtilities {
	private Utilities electricCompany;
	private Utilities waterWorks;
	private Player player1;
    private Board board;
    
    @Before
    public void initialize(){
        electricCompany = new Utilities(150, 12);
        waterWorks = new Utilities(150, 28);
        player1 = new Player(1, null);
        board = new Board();
        player1.setDecision(true);
    }
    
    @Test
    public void testToString(){
        assertEquals("Electricity Company",electricCompany.toString());
        assertEquals("Water Works",waterWorks.toString());
    }

    @Test
    public void testPerform(){
    	player1.setDecision(true);
        electricCompany.perform(player1, board);
        assertEquals(player1.getMoney().getMoney(),1350);
        
        waterWorks.perform(player1, board);
        assertEquals(player1.getMoney().getMoney(), 1350-150);
    }
}
