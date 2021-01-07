public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> rd = new ArrayDeque<>();
        for (int i = 0; i < word.length(); i ++) {
            rd.addLast(word.charAt(i));
        }
        return rd;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> d = wordToDeque(word);
        if (word.length() <= 1) {
            return true;
        }
        if (word.charAt(0) == word.charAt(word.length() - 1)) {
            if (word.length() <= 3) {
                return true;
            }
            else {
                return isPalindrome(word.substring(1, word.length() - 1));
            }
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        if (word.length() <= 1) {
            return true;
        }
        if (cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))) {
            if (word.length() <= 3) {
                return true;
            }
            else{
                return isPalindrome(word.substring(1, word.length() - 1), cc);
            }
        }
        return false;
    }
}
