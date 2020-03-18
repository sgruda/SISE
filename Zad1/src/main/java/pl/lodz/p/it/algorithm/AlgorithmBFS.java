package pl.lodz.p.it.algorithm;

import com.sun.tools.javac.util.Pair;
import pl.lodz.p.it.enums.Direction;

public class AlgorithmBFS extends Algorithm {

    private final String searchOrder;
    private int directionCharIndex;
    private Pair<Integer, Integer> zeroCoordinates;

    public AlgorithmBFS(int ROW_NUMBER, int COLUMN_NUMBER, int[][] puzzles, String searchOrder){
        super(ROW_NUMBER, COLUMN_NUMBER, puzzles);
        this.searchOrder = searchOrder.toUpperCase();
        zeroCoordinates = locateZero(puzzles);
        directionCharIndex = -1;
    }
    private Pair<Integer, Integer> locateZero(int[][] tab) {
        int i = 0;
        int j;
        for(int[] b : tab) {
            j = 0;
            for(int b2 : b) {
                if(b2 == 0) {
                    return new Pair<>(i, j);
                }
                j++;
            }
            i++;
        }
        return null;
    }
    private boolean canMoved(Direction direction) {
        switch (direction) {
            case up: {
                if (zeroCoordinates.fst == 0) {
                    return false;
                }
                return true;
            }
            case down: {
                if (zeroCoordinates.fst == super.getROW_NUMBER() - 1) {
                    return false;
                }
                return true;
            }
            case left: {
                if (zeroCoordinates.snd == 0) {
                    return false;
                }
                return true;
            }
            case right: {
                if (zeroCoordinates.snd == super.getCOLUMN_NUMBER() - 1) {
                    return false;
                }
                return true;
            }
        }
        return  false;
    }

}
