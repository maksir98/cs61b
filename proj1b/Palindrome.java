public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> result = new LinkedListDeque<Character>();
        for (int i = word.length() - 1;i >= 0; i-- ) {
            char letter = word.charAt(i);
            result.addFirst(letter);
        }
        return result;
    }

    public boolean isPalindrome(String word){
        if (word.length() == 0){
            return true;
        }

        for(int i = 0; i <= word.length()/2; i++){
            char letterF = word.charAt(i);
            char letterE = word.charAt(word.length() - 1 - i);
            if (letterE != letterF){
                return false;
            }
        }
        return true;
    }

    public boolean isPalindrome(String word, CharacterComparator cc){
        if (word.length() == 0 || word.length() == 1){
            return true;
        }

        if (cc.equalChars(word.charAt(0), word.charAt(word.length() - 1))){
            return isPalindrome(word.substring(1, word.length() - 1), cc);
        } else {
            return false;
        }
    }
}
