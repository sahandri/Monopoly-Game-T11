package Model;
import org.junit.*;
import static org.junit.Assert.*;

public class TestRailroad {
    private Railroad railroad;
    private Player player;
    private Player player2;

    @Before
    public void initialize(){
        railroad = new Railroad(300,15);
        player = new Player(1);
        player = new Player(2);
        railroad.setOwner(player);
    }

    @Test
    public void testSetOwner(){
        assertTrue(railroad.setOwner(player2));
    }

    @Test
    public void testGetOwner(){
        assertEquals(player.getID(),railroad.getOwner());
    }

    @Test
    public void testToString(){
        assertEquals(railroad.toString(),"Rail Road");
    }

}
