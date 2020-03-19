package pl.lodz.p.it.algorithm;

import pl.lodz.p.it.enums.Direction;

public class AlgorithmBFS extends Algorithm {

    private String searchOrder;
    private String[] searchOrderArray;


    public AlgorithmBFS(int ROW_NUMBER, int COLUMN_NUMBER, int[][] puzzles, String searchOrder) {
        super(ROW_NUMBER, COLUMN_NUMBER, puzzles);
        this.searchOrder = searchOrder.toUpperCase();
        this.searchOrderArray = searchOrder.split("");
    }

    @Override
    public int[][] solve() {
        this.getStatesToVisit().add(super.getCurrentState());
        while (!this.getStatesToVisit().isEmpty() && !this.isSolved()) {
            State newState = this.getStatesToVisit().poll();
            this.getVisitedStates().add(newState);
            for (int directionCharIndex = 0; directionCharIndex < searchOrder.length(); directionCharIndex++) {
                if (newState.canMoved(this.getDirectionToMove(directionCharIndex))) {
                    State movedState = new State(newState);
                    movedState.move(this.getDirectionToMove(directionCharIndex));
                    this.setCurrentState(movedState);
                    if (!this.getVisitedStates().contains(movedState)) {
                        this.getStatesToVisit().add(movedState);
                    }
                    if (this.isSolved()) {
                        System.out.println(this.getCurrentState().getSolutionSteps());
                        System.out.println(this.getCurrentState().getDepth());
                        this.getCurrentState().printPuzzle();
                        return super.getCurrentState().getPuzzle();
                    }
                }
            }
        }

        System.out.println(this.getCurrentState().getSolutionSteps());
        System.out.println(this.getCurrentState().getDepth());
        this.getCurrentState().printPuzzle();
        return this.getCurrentState().getPuzzle();
    }

    private Direction getDirectionToMove(int directionCharIndex) { //TO DO ogarnac czy nie pomyliłem bfs z dfs, przechodzi po tych kierunkach systematycznie
        switch (searchOrder.charAt(directionCharIndex)) {
            case 'U':
                return Direction.up;
            case 'D':
                return Direction.down;
            case 'L':
                return Direction.left;
            case 'R':
                return Direction.right;
            default:
                return null;
        }
    }
}
