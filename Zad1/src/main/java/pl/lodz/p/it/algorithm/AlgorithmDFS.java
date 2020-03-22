package pl.lodz.p.it.algorithm;

import lombok.Getter;

import java.util.Stack;


public class AlgorithmDFS extends Algorithm {

    @Getter
    private Stack<State> states;
    private final static int MAX_DEPTH = 22;

    public AlgorithmDFS(int ROW_NUMBER, int COLUMN_NUMBER, int[][] puzzles, String searchOrder) {
        super(ROW_NUMBER, COLUMN_NUMBER, puzzles, searchOrder);
        this.states = new Stack<>();
    }

    @Override
    public int[][] solve() {

        this.states.add(this.getCurrentState());

        while (!states.isEmpty()) {
            State newState = new State(states.pop());
            if(this.getVisitedStates().contains(newState)){
                continue;
            }
            this.getVisitedStates().add(newState);

            if(newState.getSolutionSteps().length() >= MAX_DEPTH){
                continue;
            }

            if (this.isSolved()) {
                this.generateStatistics();
                this.getCurrentState().printPuzzle();
                System.out.println(this.getStatistics());
                return super.getCurrentState().getPuzzle();
            }

            for (int directionCharIndex = 0; directionCharIndex < super.getSearchOrder().length(); directionCharIndex++) {
                if (newState.canMoved(this.getDirectionToMove(directionCharIndex))) {
                    State movedState = newState.clone();
                    movedState.move(super.getDirectionToMove(directionCharIndex));
                    this.setCurrentState(movedState);
                    if (!this.getVisitedStates().contains(movedState))
                        states.add(movedState);

                }
            }
        }
        this.generateStatistics();

        System.out.print("Statystyki: ");
        System.out.println(this.getStatistics());

        System.out.print("Rozwiazanie: ");
        this.getCurrentState().printPuzzle();
        return super.getCurrentState().getPuzzle();
    }
}

