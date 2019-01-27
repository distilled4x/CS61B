import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
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
        assertTrue(palindrome.isPalindrome("racecar"));
        assertFalse(palindrome.isPalindrome("a"));
        assertFalse(palindrome.isPalindrome("matthew"));

        CharacterComparator cc = new OffByOne();
        assertTrue(palindrome.isPalindrome("sbdecar", cc));
        assertFalse(palindrome.isPalindrome("redder", cc));

    }
}
