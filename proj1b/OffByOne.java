public class OffByOne implements CharacterComparator {
    /** Returns true if characters are equal. */
    @Override
    public boolean equalChars(char x, char y) {
        if (x == y - 1 | x == y + 1) {
            return true;
        }
        return false;
    }
}
