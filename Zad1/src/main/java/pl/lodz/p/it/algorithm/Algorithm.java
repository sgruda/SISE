package pl.lodz.p.it.algorithm;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
public abstract class Algorithm {
    private final int COLUMN_NUMBER;
    private final int ROW_NUMBER;
    private final int[][] puzzles;

    @Setter
    private LinkedList<int[][]> states;
    @Setter
    private int[][] currentState;

    public Algorithm(int COLUMN_NUMBER, int ROW_NUMBER, int[][] puzzles) {
        this.COLUMN_NUMBER = COLUMN_NUMBER;
        this.ROW_NUMBER = ROW_NUMBER;
        this.puzzles = puzzles;

        this.states = new LinkedList<>();
        this.currentState = puzzles.clone();
    }
    public abstract int[][] solve();
    protected boolean statesContains(int [][] stateToCheck) {
        for (int [][] state : states) {
            if(isEquals(stateToCheck, state)) {
                return true;
            }
        }
        return false;
    }
    private boolean isEquals(int [][] stateToCheck, int [][] pattern) {
        for (int i = 0; i < stateToCheck.length; i++) {
            for(int j = 0; j < stateToCheck.length; j++) {
                if(stateToCheck[i][j] != pattern[i][j] ) {
                    return false;
                }
            }
        }
        return true;
    }
}
