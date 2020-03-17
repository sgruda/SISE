package pl.lodz.p.it.algorithm;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
public abstract class Algorithm {
    private final int COLUMN_NUMBER;
    private final int ROW_NUMBER;
    private final Puzzle puzzles;

    @Setter
    private LinkedList<Puzzle> states;
    @Setter
    private Puzzle currentState;

    public Algorithm(int COLUMN_NUMBER, int ROW_NUMBER, int[][] puzzles) {
        this.COLUMN_NUMBER = COLUMN_NUMBER;
        this.ROW_NUMBER = ROW_NUMBER;
        this.puzzles = new Puzzle(puzzles);

        this.states = new LinkedList<>();
        this.currentState = this.puzzles.clone();
    }
    public abstract int[][] solve();
    protected boolean statesContains(Puzzle stateToCheck) {
        for (Puzzle state : states) {
//            System.out.println("w historii");
//            state.printPuzzle();
            if(state.equals(stateToCheck)) {
                return true;
            }
        }
        return false;
    }
}
