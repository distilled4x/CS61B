package synthesizer;
import synthesizer.AbstractBoundedQueue;

import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    private int first;
    private int last;
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        this.rb = (T[]) new Object[capacity];
        this.fillCount = 0;
        this.first = 0;
        this.last = 0;
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        if (isFull()) {
            throw new RuntimeException("Ring Buffer overflow");
        } else {
            rb[last] = x;

            if (last + 1 > capacity - 1) {
                last = 0;
            } else {
                last++;
            }
            fillCount++;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T getFirst = rb[first];
        rb[first] = null;

        if (first + 1 > capacity - 1) {
            first = 0;
        } else {
            first++;
        }
        fillCount--;
        return getFirst;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        if (isEmpty()) {
            throw new RuntimeException("ring buffer underflow");
        }
        return rb[first];
    }


    public Iterator<T> iterator() {
        return new arbIterator();
    }

    private class arbIterator implements Iterator<T> {
        private int pointer;

        private arbIterator() {
            pointer = 0;
        }

        public boolean hasNext() {
            return (pointer != capacity - 1);
        }

        public T next() {
            T getItem = rb[pointer];
            pointer++;
            return getItem;
        }
    }
}
