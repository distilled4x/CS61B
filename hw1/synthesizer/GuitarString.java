
package synthesizer;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        double x = Math.round(SR / frequency);
        int capacity = (int) x;
        buffer = new ArrayRingBuffer(capacity);
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int stop = buffer.fillCount();
        for (int i = 0; i < stop; i++) {
            buffer.dequeue();
        }

        for (int i = 0; i < buffer.capacity(); i++) {
            buffer.enqueue(Math.random() - .5);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        if (!buffer.isEmpty()) {
            double sampleOne = buffer.dequeue();
            double sampleTwo = buffer.peek();

            double toAdd = ((sampleOne + sampleTwo) / 2) * DECAY;

            buffer.enqueue(toAdd);
        }


    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        if (!buffer.isEmpty()) {
            return buffer.peek();
        }
        return 0;
    }
}
