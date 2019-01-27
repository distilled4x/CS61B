public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        if (word.equals("")) {
            return null;
        }

        Deque<Character> d = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            d.addLast(word.charAt(i));
        }

        return d;
    }

    public Deque<Character> wordToDequeReverse(String word) {
        if (word.equals("")) {
            return null;
        }

        Deque<Character> d = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            d.addFirst(word.charAt(i));
        }

        return d;
    }

    public boolean isPalindrome(String word) {
        if (word.length() == 0 || word.length() == 1) {
            return false;
        }
        Deque<Character> d = wordToDeque(word);
        Deque<Character> dd = wordToDequeReverse(word);

        for (int i = 0; i < word.length(); i++) {
            if (!d.get(i).equals(dd.get(i))) {
                return false;
            }
        }

        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
       if (word.length() == 0 || word.length() == 1) {
           return false;
       }
       Deque<Character> d = wordToDeque(word);
       Deque<Character> dd = wordToDequeReverse(word);

       for (int i = 0; i < word.length() / 2; i++ )  {
           if (!cc.equalChars(d.get(i), dd.get(i))) {
               return false;
           }
       }

       return true;
    }
}
