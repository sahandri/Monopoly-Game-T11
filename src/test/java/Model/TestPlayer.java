package Model;

import org.junit.*;
import static org.junit.Assert.*;

public class TestPlayer {
    private Board board;
    private Player player1;
    private Player player2;
    private Token token1;
    private Token token2;

    @Before
    public void initialize(){
        token1 = new Token();
        token1.setName("Boot");
        player1 = new Player(1, token1, "Joe");
        player1.setDecision(false);

        token2 = new Token();
        token2.setName("Battleship");
        player2 = new Player(2, token2, "Tom");
        player2.setDecision(true);

        board = new Board();
    }

    @Test
    public void testGetMoney(){
        assertEquals(1500, player1.getMoney().money);
        assertEquals(1500, player2.getMoney().money);
    }

    @Test
    public void testGetID(){
        assertEquals(1, player1.getID());
        assertEquals(2, player2.getID());
    }

    @Test
    public void testGetDecision(){
        assertEquals(false, player1.getDecision());
        assertEquals(true, player2.getDecision());
    }

    @Test
    public void testGetName(){
        assertEquals("Joe", player1.getName());
        assertEquals("Tom", player2.getName());
    }

    @Test
    public void testGetToken(){
        assertEquals("Boot", player1.getToken().getName());
        assertEquals("Battleship", player2.getToken().getName());
    }

}
