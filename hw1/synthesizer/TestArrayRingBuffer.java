package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.Transient;



public class TestArrayRingBuffer {
    @Test
    //test enqueue and see if it throws exception whn the array is full
    public void testFull() {
        BoundedQueue<Integer> arb = new ArrayRingBuffer<Integer>(10);
        for (int i=0; i<10; ++i) {
            arb.enqueue(i);
            assertEquals(arb.fillCount(),i+1);
        }
        arb.enqueue(0);
    }

    @Test 
    public void testEmpty() {
        BoundedQueue<Integer> arb = new ArrayRingBuffer(5);
        arb.dequeue();
    }
    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
