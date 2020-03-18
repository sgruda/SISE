package pl.lodz.p.it.algorithm;

import pl.lodz.p.it.enums.Direction;

public class AlgorithmBFS extends Algorithm {

    private final String searchOrder;

    public AlgorithmBFS(int ROW_NUMBER, int COLUMN_NUMBER, int[][] puzzles, String searchOrder){
        super(ROW_NUMBER, COLUMN_NUMBER, puzzles);
        this.searchOrder = searchOrder.toUpperCase();
    }

    @Override
    public int[][] solve() {
        this.getStatesToVisit().add(super.getCurrentState());
        do {
            State state = this.getStatesToVisit().poll();
            this.getVisitedStates().add(state);

            for (int directionCharIndex = 0; directionCharIndex < searchOrder.length(); directionCharIndex++) {
                if(state.canMoved(this.getDirectionToMove(directionCharIndex))) {
                    State newState = state.move(this.getDirectionToMove(directionCharIndex));
                    if(!super.visitedStatesContains(newState))
                        this.getStatesToVisit().add(newState);
                }
            }
        } while(!super.isSolved() && !this.getStatesToVisit().isEmpty());
        super.getCurrentState().printPuzzle();
        return super.getCurrentState().getPuzzle();
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
