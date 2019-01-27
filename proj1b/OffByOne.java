public class OffByOne implements CharacterComparator {
    @Override
    // int diff = 'a' - 'b';
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        if (diff == -1 || diff == 1) {
            return true;
        }

        return false;
    }
}
