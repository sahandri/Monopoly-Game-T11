package Model;
import org.junit.*;
import static org.junit.Assert.*;

public class TestUtilities {
	private Utilities electricCompany;
	private Utilities waterWorks;
	private Utilities badPos;
	private Player player1;
	private Player player2;
    private Board board;
    
    @Before
    public void initialize(){
        electricCompany = new Utilities(150, 12);
        waterWorks = new Utilities(150, 28);
        player1 = new Player(1, null);
        player2 = new Player(2, null);
        board = new Board();
        //player1.setDecision(true);
        player2.setDecision(true);
        //board.purchaseProperty(player2, 12);
        player1.setDecision(true);
    }
    
    @Test
    public void testToString(){
        assertEquals(" at Electricity Company. \n",electricCompany.toString());
        assertEquals(" at Water Works. \n",waterWorks.toString());
    }

    @Test
    public void testPerform(){
        electricCompany.perform(player1, board);
        assertEquals(player1.getMoney().getMoney(),1350);
        
        assertEquals(1500,player2.getMoney().getMoney());
        electricCompany.perform(player2, board);
        assertEquals(1350,player2.getMoney().getMoney());
        assertEquals(1500, player1.getMoney().getMoney());
        
        
        waterWorks.perform(player1, board);
        assertEquals(1350, player1.getMoney().getMoney());
        player2.setDecision(false);
        waterWorks.perform(player2, board);
        assertEquals(1350,player2.getMoney().getMoney());
        
        waterWorks.setOwner(new Player(-1,new Token()));
        waterWorks.perform(player2, board);
        assertEquals(1350,player2.getMoney().getMoney());
    }
    
    @Test
    public void testsetUp() {
    	badPos = new Utilities(0,3);
    }
}
