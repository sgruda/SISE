package pl.lodz.p.it.algorithm;

import com.sun.tools.javac.util.Pair;
import pl.lodz.p.it.enums.Direction;

public class AlgorithmBFS extends Algorithm {

    private final String searchOrder;
    private int directionCharIndex;

    public AlgorithmBFS(int ROW_NUMBER, int COLUMN_NUMBER, int[][] puzzles, String searchOrder){
        super(ROW_NUMBER, COLUMN_NUMBER, puzzles);
        this.searchOrder = searchOrder.toUpperCase();
        directionCharIndex = -1;
    }

    @Override
    public int[][] solve() {
        return new int[0][];
    }
}
