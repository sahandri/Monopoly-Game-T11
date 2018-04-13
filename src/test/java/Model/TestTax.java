package Model;
import org.junit.*;
import static org.junit.Assert.*;

public class TestTax {
    private Tax buffetTax;
    private Tax corporateTax;
    private Player player1;
    private Board board;

    @Before
    public void initialize(){
        buffetTax = new Tax(200,4);
        corporateTax = new Tax(75, 38);
        player1 = new Player(1);
        board = new Board();
        player1.setDecision(false);
    }

    @Test
    public void testToString(){
        assertEquals("Buffet Tax",buffetTax.toString());
        assertEquals("Corporate Tax",corporateTax.toString());
    }

    @Test
    public void testPerform(){
        buffetTax.perform(player1, board);
        assertEquals(player1.getMoney().money,1300);
        corporateTax.perform(player1, board);
        assertEquals(player1.getMoney().money, 1225);
    }

    @Test
    public void testTenPercent() {
        assert(150.0 == buffetTax.tenPercent(player1.getMoney().money));
        player1.setDecision(true);
        buffetTax.perform(player1, board);
        assertEquals(1350, player1.getMoney().money);
    }
}