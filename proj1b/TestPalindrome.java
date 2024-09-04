import org.junit.Test;
import static org.junit.Assert.*;

import java.beans.Transient;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }

    @Test 
    public void testIsPalindrome() {
        assertTrue(palindrome.isPalindrome(""));
        assertFalse(palindrome.isPalindrome("word"));
        assertTrue(palindrome.isPalindrome("A"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertFalse(palindrome.isPalindrome("Racecar"));
        assertFalse(palindrome.isPalindrome("abc"));
    }
    public static void main(String[] args) {
        jh61b.junit.TestRunner.runTests("failed", TestPalindrome.class);
    }
}
