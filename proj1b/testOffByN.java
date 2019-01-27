import org.junit.Test;
import static org.junit.Assert.*;

public class testOffByN {

    static OffByN o = new OffByN(5);

    @Test
    public void testEqualChars() {
        assertTrue(o.equalChars('a', 'f'));
        assertFalse(o.equalChars('b', 'z'));
    }

}
