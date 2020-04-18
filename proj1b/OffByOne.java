public class OffByOne implements CharacterComparator {

    public boolean equalChars(char x, char y){
        int diff = x - y;
        diff = Math.abs(diff);
        if (diff == 1){
            return true;
        }else {
            return false;
        }
    }
}
