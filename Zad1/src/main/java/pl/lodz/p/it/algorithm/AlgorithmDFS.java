package pl.lodz.p.it.algorithm;

import lombok.Getter;

import java.util.Stack;


public class AlgorithmDFS extends Algorithm {

    @Getter
    private Stack<State> states;
    private final static int MAX_DEPTH = 20;

    public AlgorithmDFS(int ROW_NUMBER, int COLUMN_NUMBER, int[][] puzzles, String searchOrder) {
        super(ROW_NUMBER, COLUMN_NUMBER, puzzles, searchOrder);
        this.states = new Stack<>();
    }

    @Override
    public int[][] solve() {
        this.states.add(super.getCurrentState());

        while (!this.getStates().isEmpty() && !this.isSolved()) {
            State newState = states.pop().clone();
            if(this.getVisitedStates().contains((newState))){
                continue;
            }
            this.getVisitedStates().add(newState);
            if(newState.getSolutionSteps().length() >= MAX_DEPTH){
                continue;
            }

            for (int directionCharIndex = 0; directionCharIndex < super.getSearchOrder().length(); directionCharIndex++) {
                if (newState.canMoved(super.getDirectionToMove(directionCharIndex))) {
                    State movedState = newState.clone();
                    movedState.move(super.getDirectionToMove(directionCharIndex));
                    this.setCurrentState(movedState);

                    if (!this.getVisitedStates().contains(movedState)) {
                        this.states.add(movedState);
                    }
                }
            }
        }
        System.out.println(this.getCurrentState().getSolutionSteps());
        System.out.println(this.getCurrentState().getDepth());
        this.getCurrentState().printPuzzle();
        return this.getCurrentState().getPuzzle();
    }
}

