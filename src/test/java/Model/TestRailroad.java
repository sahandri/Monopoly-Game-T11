package Model;
import org.junit.*;
import static org.junit.Assert.*;

public class TestRailroad {
    private Railroad railroad1;
    private Railroad railroad2;
    private Player player1;
    private Player player2;
    private Board board;

    @Before
    public void initialize(){
        railroad1 = new Railroad(300,15);
        railroad2 = new Railroad(200,5);
        board = new Board();
        Token token = new Token();
        player1 = board.addPlayer(1,token);
        player2 = board.addPlayer(2,token);
        railroad1.setOwner(player1);
    }

    @Test
    public void testSetOwner(){
        assertFalse(railroad1.setOwner(player2));
        assertTrue(railroad2.setOwner(player2));
    }

    @Test
    public void testGetOwner(){
        assertEquals(player1.getID(),railroad1.getOwner());
    }

    @Test
    public void testPerform(){
        player1.setDecision(true);
        //test buying Railroad
        board.getSquares()[5].perform(player1,board);
        assertEquals(1300, player1.getMoney().getMoney()); //testing to buy a railroad
        assertEquals(board.getSquares()[5].getOwner(), 1); // check the streets owner ID
        board.getSquares()[5].perform(player2,board); //player 2 moves to player1's railroad
        assertEquals(1475, player2.getMoney().getMoney()); //testing to pay rent for one railroad

        board.getSquares()[15].perform(player1,board); //player 1 buys second railroad
        assertEquals(1100, player1.getMoney().getMoney()); //testing to buy a railroad
        assertEquals(board.getSquares()[15].getOwner(), 1); // check the railroad owner ID
        board.getSquares()[15].perform(player2,board); //player 2 moves to player1's railroad
        assertEquals(1425, player2.getMoney().getMoney()); //testing to pay rent for two railroads

        player2.setDecision(false);
        board.getSquares()[25].perform(player2,board); //player2 moves to a railroad without owner
        assertEquals(board.getSquares()[25].getOwner(), -1); // check the railroad owner ID
        assertEquals(1425, player2.getMoney().getMoney()); //testing to not buy the railroad
    }

    @Test
    public void testToString(){
        assertEquals(railroad1.toString(),"Rail Road");
    }

}
