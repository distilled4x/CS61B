package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(3);
        arb.enqueue(0);
        arb.enqueue(1);
        arb.enqueue(2);
        assertEquals(arb.dequeue(), 0);
        assertEquals(arb.dequeue(), 1);
        arb.enqueue(3);
        assertEquals(arb.peek(), 2);
        arb.dequeue();
        assertEquals(arb.dequeue(), 3);
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
