//Make sure to make this class a part of the synthesizer package
package synthesizer;
//Make sure to make this class extend AbstractBoundedQueue<t>

import java.util.Iterator;

public class ArrayRingBuffer<T>  extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // Create new array with capacity elements.
        rb = (T[]) new Object[capacity];
        //       first, last, and fillCount should all be set to 0.
        first = 0;
        last = 0;
        fillCount = 0;
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        this.capacity = capacity;
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        //Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        }
        rb[last] = x;
        last = (last + 1) % capacity;
        fillCount += 1;
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        //Dequeue the first item. Don't forget to decrease fillCount and update 
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        T ans = rb[first];
        first = (first + 1) % capacity;
        fillCount -= 1;
        return ans;
    }

    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        //Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        }
        return rb[first];
    }

    //When you get to part 5, implement the needed code to support iteration.
    public Iterator<T> iterator() {
        return new KeyIterator();
    }
    private class KeyIterator implements Iterator<T> {
        private int ptr;
        public KeyIterator() {
            ptr = first;
        }
        public boolean hasNext() {
            return ptr != last;
        }
        public T next() {
            T ans = rb[ptr];
            ptr = (ptr + 1) % capacity;
            return ans;
        }
        
    }
}
