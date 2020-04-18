import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = new OffByOne();

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
    public void testIsPalindrome1(){
        String input = "racecar";
        Boolean actual = palindrome.isPalindrome(input);
        assertTrue(actual);
    }

    @Test
    public void testIsPalindrome2(){
        String input = "raaceecaar";
        Boolean actual = palindrome.isPalindrome(input);
        assertTrue(actual);
    }

    @Test
    public void testIsPalindrome3(){
        String input = "rbacecaar";
        Boolean actual = palindrome.isPalindrome(input);
        assertFalse(actual);
    }

    @Test
    public void testIsPalindrome4(){
        String input = "raacedcaar";
        Boolean actual = palindrome.isPalindrome(input);
        assertFalse(actual);
    }

    @Test
    public void testIsPalindrome5(){
        String input = "g";
        Boolean actual = palindrome.isPalindrome(input);
        assertTrue(actual);
    }

    @Test
    public void testIsPalindrome6(){
        String input = "";
        Boolean actual = palindrome.isPalindrome(input);
        assertTrue(actual);
    }

    @Test
    public void testIsPalindrome7() {
        String input = "flake";
        Boolean actual = palindrome.isPalindrome(input, offByOne);
        assertTrue(actual);
    }

    @Test
    public void testIsPalindrome8() {
        String input = "flaake";
        Boolean actual = palindrome.isPalindrome(input, offByOne);
        assertFalse(actual);
    }
}
