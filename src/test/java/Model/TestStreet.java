package Model;
import org.junit.*;
import static org.junit.Assert.*;

public class TestStreet {

    private Street street;

    @Before
    public void initialize(){
        street = new Street(300,31);
    }

    @Test
    public void testGetColor(){
        assertEquals(Street.colors.GREEN,street.getColor());
    }

    @Test
    public void testInitialize(){
        street.setColor(1);
        assertEquals(Street.colors.BROWN,street.getColor());
    }

}
