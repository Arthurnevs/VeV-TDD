import org.junit.Test;
import static org.junit.Assert.*;

public class ShowTest {

    @Test
    public void testCreateShow() {
        Show show = new Show("2024-12-31", "Madonna", 1000.0, 2000.0, true);
        assertEquals("2024-12-31", show.getDate());
        assertEquals("Madonna", show.getArtist());
        assertEquals(1000.0, show.getFee(), 0.01);
        assertEquals(2000.0, show.getInfrastructureCost(), 0.01);
        assertTrue(show.isSpecialDate());
    }
}