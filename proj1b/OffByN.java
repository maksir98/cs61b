public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int d){
        N = d;
    }

    public boolean equalChars(char x, char y){
        int diff = x - y;
        diff = Math.abs(diff);
        if (diff == N){
            return true;
        }else {
            return false;
        }
    }
}
