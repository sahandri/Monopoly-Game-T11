package Model;
import org.junit.*;
import static org.junit.Assert.*;

public class TestStreet {

    private Street street;
    private Player player;
    private Player player2;

    @Before
    public void initialize(){
        street = new Street(300,31, 26);
        player = new Player(1);
        player = new Player(2);
        street.setOwner(player);
    }

    @Test
    public void testGetColor(){
        assertEquals(Street.colors.GREEN,street.getColor());
    }

    @Test
    public void testGetID(){
        assertEquals(6,street.getID());
    }

    @Test
    public void testSetOwner(){
        assertFalse(street.setOwner(player2));
    }

    @Test
    public void testGetOwner(){
        assertEquals(player.getID(),street.getOwner());
    }

    @Test
    public void testInitialize(){
        street.setColor(1);
        assertEquals(Street.colors.BROWN,street.getColor());
    }

    @Test
    public void testToString(){
        assertEquals(street.toString(),"Green");
    }

}
