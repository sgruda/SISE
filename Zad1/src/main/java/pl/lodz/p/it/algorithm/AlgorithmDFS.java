package pl.lodz.p.it.algorithm;

import lombok.Getter;
import pl.lodz.p.it.enums.Direction;

import java.util.Stack;


public class AlgorithmDFS  extends Algorithm {

    private String searchOrder;

    public Stack<State> getStates() {
        return states;
    }

    private Stack<State> states;

    private final static int MAX_DEPTH = 20;

    public AlgorithmDFS(int ROW_NUMBER, int COLUMN_NUMBER, int[][] puzzles, String searchOrder) {
        super(ROW_NUMBER, COLUMN_NUMBER, puzzles);
        this.searchOrder = searchOrder.toUpperCase();
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

            for (int directionCharIndex = 0; directionCharIndex < searchOrder.length(); directionCharIndex++) {
                if (newState.canMoved(this.getDirectionToMove(directionCharIndex))) {
                    State movedState = newState.clone();
                    movedState.move(this.getDirectionToMove(directionCharIndex));
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

    private Direction getDirectionToMove(int directionCharIndex) {
        switch (searchOrder.charAt(directionCharIndex)) {
            case 'U':
            case 'u':
                return Direction.up;
            case 'D':
            case 'd':
                return Direction.down;
            case 'L':
            case 'l':
                return Direction.left;
            case 'R':
            case 'r':
                return Direction.right;
            default:
                return null;
        }
    }
}

