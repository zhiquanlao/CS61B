public class Palindrome {
    /*
     * Given a string, return a deque where character appear in the same order of string
     */
    public Deque<Character> wordToDeque(String word) {
        LinkedListDeque<Character>Word = new LinkedListDeque<Character>();
        for(int i = 0; i < word.length(); ++i){
            Word.addLast(word.charAt(i));
        }
        return Word;
    }

    /*
     * Return true if String word is a palindrome
     */
    public boolean isPalindrome(String word) {
        Deque wordDeque = wordToDeque(word);
        return isPalindrome(wordDeque);
    }

    /*
     * Return true if a Deque word is palindrome
     */
    private boolean isPalindrome(Deque word) {
        if (word.size() == 0 || word.size() == 1){
            return true;
        }
        if(word.removeFirst() != word.removeLast()){
            return false;
        }
        return isPalindrome(word);
    }
    
    /*
     * return true if String word is a palindrome according
     * to the rule of CharacterComparator cc
     */
    public boolean  isPalindrome(String word, CharacterComparator cc) {
        Deque wDeque = wordToDeque(word);
        return isPalindrome(wDeque, cc);
    }
    
    /*
     * return true if deque word is a palindrome according
     * to the rule of CharacterComparator cc
     */
    private boolean isPalindrome(Deque<Character> word, CharacterComparator cc) {
        if (word.size() == 0 || word.size() == 1){
            return true;
        }
        if(!cc.equalChars(word.removeFirst(),word.removeLast())){
            return false;
        }
        return isPalindrome(word, cc);
    }
}
