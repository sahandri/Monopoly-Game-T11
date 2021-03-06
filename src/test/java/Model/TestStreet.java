package Model;
import org.junit.*;
import static org.junit.Assert.*;

public class TestStreet {

    private Street streetB, streetLB, streetP, streetO,
        streetR, streetY,streetG,streetDB;
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
    public void testMortgage() {
    	assertTrue(streetG.mortgage(player1));
    	assertEquals(1650,player1.getMoney().getMoney());
    	assertFalse(streetG.mortgage(player1));
    }
    
    @Test
    public void testUnMortgage() {
    	assertTrue(streetG.mortgage(player1));
    	assertTrue(streetG.unmortgage(player1));
    	assertEquals(1470,player1.getMoney().getMoney());
    	assertFalse(streetG.unmortgage(player1));
    }
    
    @Test
    public void testBuyHotel() {
    	board.getSquares()[31].setOwner(player1);
    	assertFalse(((Street) board.getSquares()[31]).buyHotel(player1));
    	board.getSquares()[32].setOwner(player1);
    	board.getSquares()[34].setOwner(player1);
    	((Street) board.getSquares()[31]).buyHouse(player1, board);
    	assertFalse(((Street) board.getSquares()[31]).buyHotel(player1));
    	((Street) board.getSquares()[31]).buyHouse(player1, board);
    	((Street) board.getSquares()[31]).buyHouse(player1, board);
    	((Street) board.getSquares()[31]).buyHouse(player1, board);
    	((Street) board.getSquares()[31]).buyHotel(player1);
    	assertEquals(1,((Street) board.getSquares()[31]).getHotel());
    }
    
    @Test
    public void testBuyHouse() {
    	board.getSquares()[31].setOwner(player1);
    	assertFalse(((Street) board.getSquares()[31]).buyHouse(player1, board));
    	board.getSquares()[32].setOwner(player1);
    	board.getSquares()[34].setOwner(player1);
    	assertTrue(((Street) board.getSquares()[31]).buyHouse(player1, board));
    	assertEquals(1,((Street) board.getSquares()[31]).getHouse());
    }
    
    @Test
    public void testSellhouse() {
    	assertFalse(((Street) board.getSquares()[31]).sellHouse(player1));
    	board.getSquares()[31].setOwner(player1);
    	board.getSquares()[32].setOwner(player1);
    	board.getSquares()[34].setOwner(player1);
    	((Street) board.getSquares()[31]).buyHouse(player1, board);
    	assertTrue(((Street) board.getSquares()[31]).sellHouse(player1));
    	assertEquals(0,((Street) board.getSquares()[31]).getHouse());
    }
    
    @Test
    public void testSellHotel() {
    	board.getSquares()[31].setOwner(player1);
    	board.getSquares()[32].setOwner(player1);
    	board.getSquares()[34].setOwner(player1);
    	((Street) board.getSquares()[31]).buyHouse(player1, board);
    	((Street) board.getSquares()[31]).buyHouse(player1, board);
    	((Street) board.getSquares()[31]).buyHouse(player1, board);
    	((Street) board.getSquares()[31]).buyHouse(player1, board);
    	assertFalse(((Street) board.getSquares()[31]).sellHotel(player1));
    	((Street) board.getSquares()[31]).buyHotel(player1);
    	assertTrue(((Street) board.getSquares()[31]).sellHotel(player1));
    	assertEquals(0,((Street) board.getSquares()[31]).getHotel());
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
        ((Street) board.getSquares()[3]).buyHouse(player1,board);// pay rent with one house
        board.getSquares()[3].perform(player2,board);
        assertEquals(1470, player2.getMoney().getMoney());
        ((Street) board.getSquares()[3]).buyHouse(player1,board);//pay rent with two hoses
        board.getSquares()[3].perform(player2,board);
        assertEquals(1410, player2.getMoney().getMoney());
        ((Street) board.getSquares()[3]).buyHouse(player1,board);//pay rent with three houses
        board.getSquares()[3].perform(player2,board);
        assertEquals(1230, player2.getMoney().getMoney());
        ((Street) board.getSquares()[3]).buyHouse(player1,board);//pay rent with four houses
        board.getSquares()[3].perform(player2,board);
        assertEquals(970, player2.getMoney().getMoney());
        ((Street) board.getSquares()[3]).buyHotel(player1);//pay rent with a hotel
        board.getSquares()[3].perform(player2,board);
        assertEquals(630, player2.getMoney().getMoney());
        
        

        player2.setDecision(false);
        board.getSquares()[6].perform(player2,board); //player2 moves to a street without owner
        assertEquals(board.getSquares()[6].getOwner(), -1); // check the streets owner ID
        assertEquals(630, player2.getMoney().getMoney()); //testing to not buy the street
    }

    @Test
    public void testToString(){
    	assertEquals(" at Mediterranian Avenue. \n", board.getSquares()[1].toString());
    	assertEquals(" at Baltic Avenue. \n", board.getSquares()[3].toString());
    	assertEquals(" at Oriental Avenue. \n", board.getSquares()[6].toString());
    	assertEquals(" at Vermont Avenue. \n", board.getSquares()[8].toString());
    	assertEquals(" at Connecticut Avenue. \n", board.getSquares()[9].toString());
    	assertEquals(" at St. Charles Place. \n", board.getSquares()[11].toString());
    	assertEquals(" at States Avenue. \n", board.getSquares()[13].toString());
    	assertEquals(" at Virginia Avenue. \n", board.getSquares()[14].toString());
    	assertEquals(" at St. James Place. \n", board.getSquares()[16].toString());
    	assertEquals(" at Tennessee Avenue. \n", board.getSquares()[18].toString());
    	assertEquals(" at New York Avenue. \n", board.getSquares()[19].toString());
    	assertEquals(" at Kentucky Avenue. \n", board.getSquares()[21].toString());
    	assertEquals(" at Indiana Avenue. \n", board.getSquares()[23].toString());
    	assertEquals(" at Illinois Avenue. \n", board.getSquares()[24].toString());
    	assertEquals(" at Atlantic Avenue. \n", board.getSquares()[26].toString());
    	assertEquals(" at Ventnor Avenue. \n", board.getSquares()[27].toString());
    	assertEquals(" at Marvin Gardens. \n", board.getSquares()[29].toString());
    	assertEquals(" at Pacific Avenue. \n", board.getSquares()[31].toString());
    	assertEquals(" at North Carolina Avenue. \n", board.getSquares()[32].toString());
    	assertEquals(" at Pennsylvania Avenue. \n", board.getSquares()[34].toString());
    	assertEquals(" at Park Place. \n", board.getSquares()[37].toString());
    	assertEquals(" at Boardwalk. \n", board.getSquares()[39].toString());
    }
}
