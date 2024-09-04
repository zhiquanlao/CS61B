import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.Transient;

public class TestOffByOne{
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator obo = new OffByOne();

    @Test
    public void TestequalChars() {
        assertFalse(obo.equalChars('x', 'x'));

        assertTrue(obo.equalChars('a', 'b'));
        assertTrue(obo.equalChars('b', 'a'));

    }
    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests("failed", TestOffByOne.class);

    }
}
