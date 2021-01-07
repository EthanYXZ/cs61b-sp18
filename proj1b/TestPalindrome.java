import org.junit.Test;
import static org.junit.Assert.*;

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
    public void testisPalindrome() {
        assertFalse(palindrome.isPalindrome("ba"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("opps"));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("non"));
        assertTrue(palindrome.isPalindrome("noon"));

        OffByOne obo = new OffByOne();
        assertTrue(palindrome.isPalindrome("a", obo));
        assertTrue(palindrome.isPalindrome("ab", obo));
        assertTrue(palindrome.isPalindrome("avb", obo));
        palindrome.isPalindrome("afvgb", obo);
        //assertTrue(palindrome.isPalindrome("afvgb", obo));
        assertTrue(palindrome.isPalindrome("flake", obo));
        assertTrue(palindrome.isPalindrome("afgb", obo));
        assertFalse(palindrome.isPalindrome("ac", obo));
        assertFalse(palindrome.isPalindrome("oilp", obo));
        assertFalse(palindrome.isPalindrome("aoiipb", obo));

        OffByN ob5 = new OffByN(5);
        assertTrue(palindrome.isPalindrome("a", ob5));
        assertTrue(palindrome.isPalindrome("af", ob5));
        assertTrue(palindrome.isPalindrome("gvb", ob5));
        assertTrue(palindrome.isPalindrome("ahcf", ob5));
        assertTrue(palindrome.isPalindrome("ahvcf", ob5));
        assertFalse(palindrome.isPalindrome("ac", obo));
        assertFalse(palindrome.isPalindrome("acgf", obo));
        assertFalse(palindrome.isPalindrome("ijckkhed", obo));
    }
}
