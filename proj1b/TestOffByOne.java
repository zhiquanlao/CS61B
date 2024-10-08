import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.Transient;

public class TestOffByOne {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = new OffByOne();

    @Test
    public void testEqualChars() {
        assertFalse(offByOne.equalChars('x', 'x'));
        assertTrue(offByOne.equalChars('a', 'b'));
        assertTrue(offByOne.equalChars('b', 'a'));
        assertTrue(offByOne.equalChars('x', 'y'));
        assertFalse(offByOne.equalChars('A', 'b'));
        assertTrue(offByOne.equalChars('A', 'B'));
        assertTrue(offByOne.equalChars('>', '?'));
        assertFalse(offByOne.equalChars(' ', 'A'));
        assertFalse(offByOne.equalChars('}', '!'));
    }
    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests("failed", TestOffByOne.class);

    }
}
