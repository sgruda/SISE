package pl.lodz.p.it.algorithm;

import com.google.common.base.Stopwatch;
import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.enums.Direction;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

@Getter
public abstract class Algorithm {
    @Setter
    private State currentState;
    @Setter
    private HashSet<State> visitedStates;
    @Setter
    private Queue<State> statesToVisit;
    private String searchOrder;
    @Setter
    private double executionTime;

    public Algorithm(int COLUMN_NUMBER, int ROW_NUMBER, int[][] puzzles, String searchOrder) {
        this.visitedStates = new HashSet<State>();
        this.statesToVisit = new LinkedList<>();
        this.currentState = new State(puzzles, COLUMN_NUMBER, ROW_NUMBER);
        this.searchOrder = searchOrder.toUpperCase();
    }

    public abstract int[][] solve();

    public int[][] runWithTimeCounter(){
        long startTime = System.currentTimeMillis();
        int[][] result = this.solve();
        this.setExecutionTime((System.currentTimeMillis()-startTime));
        return result;
    }

    protected boolean isSolved() {
        int i = 1;
        for (int[] b : getCurrentState().getPuzzle()) {
            for (int b2 : b) {
                if (b2 == i) {
                    if (i == 15) {
                        int[][] puzzleTemp = getCurrentState().getPuzzle();
                        if (puzzleTemp[getCurrentState().getROW_NUMBER() - 1][getCurrentState().getCOLUMN_NUMBER() - 1] == 0) {
                            return true;
                        }
                    }
                    i++;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    protected Direction getDirectionToMove(int directionCharIndex) {
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

    public String generateStatistics() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.getCurrentState().getSolutionLetters().length()+"\n");
        sb.append(this.getVisitedStates().size()+"\n");
        sb.append(this.getVisitedStates().size()+"\n");
        sb.append(this.getCurrentState().getDepth());
        return sb.toString();
    }

}
