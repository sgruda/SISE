package pl.lodz.p.it.algorithm;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.Queue;

@Getter
public abstract class Algorithm {
    @Setter
    private State currentState;

    @Setter
    private Queue<State> visitedStates;
    @Setter
    private Queue<State> statesToVisit;

    public Algorithm(int COLUMN_NUMBER, int ROW_NUMBER, int[][] puzzles) {
        this.visitedStates = new LinkedList<>();
        this.statesToVisit = new LinkedList<>();
        this.currentState = new State(puzzles, COLUMN_NUMBER, ROW_NUMBER);
    }
    protected boolean isSolved() {
        int i = 1;
        for(int[] b : getCurrentState().getPuzzle()) {
            for(int b2 : b) {
                if(b2 == i) {
                    i++;
                    if(i == 16){
                        int[][] puzzleTemp = getCurrentState().getPuzzle();
                        if(puzzleTemp[getCurrentState().getROW_NUMBER()-1][getCurrentState().getCOLUMN_NUMBER()-1] == 0){
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
    protected boolean visitedStatesContains(State stateToCheck) {
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
