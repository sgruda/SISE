package pl.lodz.p.it.algorithm;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
public abstract class Algorithm {
    private final int COLUMN_NUMBER;
    private final int ROW_NUMBER;
    private final State puzzles;

    @Setter
    private Queue<State> visitedStates;
    @Setter
    private Queue<State> statesToVisit;
    @Setter
    private State currentState;

    public Algorithm(int COLUMN_NUMBER, int ROW_NUMBER, int[][] puzzles) {
        this.COLUMN_NUMBER = COLUMN_NUMBER;
        this.ROW_NUMBER = ROW_NUMBER;
        this.puzzles = new State(puzzles);

        this.visitedStates = new LinkedList<>();
        this.statesToVisit = new LinkedList<>();
        this.currentState = this.puzzles.clone();
    }
    private boolean isSolved() {
        int i = 1;
        for(int[] b : getCurrentState().getPuzzle()) {
            for(int b2 : b) {
                if(b2 == i) {
                    i++;
                    if(i == 16){
                        if(getCurrentState().getPuzzle()[getROW_NUMBER()-1][getCOLUMN_NUMBER()-1] == 0){
                            return true;
                        }
                    }
                }else{
                    return false;
                }
            }
        }
        return true;
    }
    public abstract int[][] solve();
    protected boolean statesContains(State stateToCheck) {
        for (State state : visitedStates) {
//            System.out.println("w historii");
//            state.printPuzzle();
            if(state.equals(stateToCheck)) {
                return true;
            }
        }
        return false;
    }
}
