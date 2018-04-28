package Model;
import org.junit.*;
import static org.junit.Assert.*;

public class TestStreet {

    private Street streetB, streetLB, streetP, streetO,
        streetR, streetY,streetG,streetG2,streetG3,streetDB;
    private Player player1;
    private Player player2;
    private Board board;

    @Before
    public void initialize(){
        streetB = new Street(60,1, 2); //Brown
        streetLB = new Street(100,6, 6); //LightBlue
        streetP = new Street(140,11, 10); //Pink
        streetO = new Street(180,16, 14); //Orange
        streetR = new Street(220,21, 18); //Red
        streetY = new Street(260, 26, 22); //Yellow
        streetG = new Street(300,31, 26); //Green
        streetG2 = new Street(300,32, 26);
        streetG3 = new Street(320,34, 28);
        streetDB = new Street(350,37, 35); //DarkBlue
        board = new Board();
        Token token = new Token();
        player1 = board.addPlayer(1,token);
        player2 = board.addPlayer(2,token);
        streetG.setOwner(player1);
        streetP.setOwner(player2);
    }

    @Test
    public void testGetColor(){
        assertEquals(Street.colors.BROWN, streetB.getColor());
        assertEquals(Street.colors.LIGHT_BLUE, streetLB.getColor());
        assertEquals(Street.colors.PINK, streetP.getColor());
        assertEquals(Street.colors.ORANGE, streetO.getColor());
        assertEquals(Street.colors.RED, streetR.getColor());
        assertEquals(Street.colors.YELLOW, streetY.getColor());
        assertEquals(Street.colors.GREEN, streetG.getColor());
        assertEquals(Street.colors.DARK_BLUE, streetDB.getColor());
    }

    @Test
    public void testGetID(){
        assertEquals(0, streetB.getID());
        assertEquals(1, streetLB.getID());
        assertEquals(2, streetP.getID());
        assertEquals(3, streetO.getID());
        assertEquals(4, streetR.getID());
        assertEquals(5, streetY.getID());
        assertEquals(6, streetG.getID());
        assertEquals(7, streetDB.getID());
    }

    @Test
    public void testSetOwner(){
        assertFalse(streetG.setOwner(player2)); //streetG has an owner
        assertTrue(streetB.setOwner(player2));  //streetB doesn't have owner
        assertEquals(player2.getID(),streetB.getOwner());
    }

    @Test
    public void testGetOwner(){
        assertEquals(player1.getID(),streetG.getOwner());
        assertEquals(player2.getID(),streetP.getOwner());
    }
    
    @Test
    public void testBuyHouse() {
    	streetG2.setOwner(player1);
    	streetG3.setOwner(player1);
    	assertTrue(streetG.buyHouse(player1, board));
    	assertEquals(1,streetG.getHouse());
    }

    @Test
    public void testPerform(){
        player1.setDecision(true);
        //test buying street
        board.getSquares()[1].perform(player1,board);
        assertEquals(1440, player1.getMoney().getMoney()); //testing to buy a street
        assertEquals(board.getSquares()[1].getOwner(), 1); // check the streets owner ID
        board.getSquares()[1].perform(player2,board); //player 2 moves to player1's street
        assertEquals(1498, player2.getMoney().getMoney()); //testing to pay rent for one street

        board.getSquares()[3].perform(player1,board); //player 1 buys second street from same color
        assertEquals(1382, player1.getMoney().getMoney()); //testing to buy a street
        assertEquals(board.getSquares()[3].getOwner(), 1); // check the streets owner ID
        board.getSquares()[3].perform(player2,board); //player 2 moves to player1's street
        assertEquals(1490, player2.getMoney().getMoney()); //testing to pay rent for two streets

        player2.setDecision(false);
        board.getSquares()[6].perform(player2,board); //player2 moves to a street without owner
        assertEquals(board.getSquares()[6].getOwner(), -1); // check the streets owner ID
        assertEquals(1490, player2.getMoney().getMoney()); //testing to not buy the street
    }

    @Test
    public void testToString(){
    	assertEquals(" at Mediterranian Avenue. \n", board.getSquares()[1].toString());
    }
}
