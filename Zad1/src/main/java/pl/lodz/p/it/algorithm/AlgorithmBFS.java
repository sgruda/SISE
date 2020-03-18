package pl.lodz.p.it.algorithm;

import com.sun.tools.javac.util.Pair;
import pl.lodz.p.it.enums.Direction;

public class AlgorithmBFS extends Algorithm {

    private final String searchOrder;
    private int directionCharIndex;

    public AlgorithmBFS(int ROW_NUMBER, int COLUMN_NUMBER, int[][] puzzles, String searchOrder){
        super(ROW_NUMBER, COLUMN_NUMBER, puzzles);
        this.searchOrder = searchOrder.toUpperCase();
        directionCharIndex = -1;
    }

    @Override
    public int[][] solve() {
        this.getStatesToVisit().add(super.getCurrentState());
        Direction direction = getDirectionToMove();
        do {
            State state = this.getStatesToVisit().poll();
            this.getVisitedStates().add(state);

            //for (int i=0; i<this.searchOrder.length(); i++) {
                if(state.canMoved(direction)) {

                    State newState = state.move(direction);
                    if(!super.visitedStatesContains(newState))
                        this.getStatesToVisit().add(newState);
                } else {
                    direction = getDirectionToMove();
                }
            //}
        } while(!super.isSolved() && !this.getStatesToVisit().isEmpty());
        return super.getCurrentState().getPuzzle();
    }
    private Direction getDirectionToMove() { //TO DO ogarnac czy nie pomyliłem bfs z dfs, przechodzi po tych kierunkach systematycznie
        directionCharIndex++;
        if(directionCharIndex == searchOrder.length()) {
            directionCharIndex = 0;
        }
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
