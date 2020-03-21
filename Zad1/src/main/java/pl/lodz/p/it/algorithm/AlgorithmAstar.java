package pl.lodz.p.it.algorithm;

import lombok.Getter;
import pl.lodz.p.it.algorithm.heuristic.Heuristic;

import java.util.HashMap;
import java.util.Map;

@Getter
public class AlgorithmAstar extends Algorithm {
    private Heuristic heuristic;
    private Map<Integer, State> statesToVisitWithHeuristicNumber;

    public AlgorithmAstar(int COLUMN_NUMBER, int ROW_NUMBER, int[][] puzzles, Heuristic heuristic) {
        super(COLUMN_NUMBER, ROW_NUMBER, puzzles, "RDUL");
        this.heuristic = heuristic;
        this.statesToVisitWithHeuristicNumber = new HashMap<>();
    }
    @Override
    public int[][] solve() {
        this.statesToVisitWithHeuristicNumber.put(Integer.MAX_VALUE, super.getCurrentState());
        while (this.getStatesToVisitWithHeuristicNumber().isEmpty() && !super.isSolved()) {
            State newState ;//= findFrameWithTheLowestHeuristic();
            this.getVisitedStates().add(newState);
            for (int directionCharIndex = 0; directionCharIndex < super.getSearchOrder().length(); directionCharIndex++) {
                if(newState.canMoved(super.getDirectionToMove(directionCharIndex))) {
                    State movedState = new State(newState);
                    movedState.move(super.getDirectionToMove(directionCharIndex));

                    if (!this.getVisitedStates().contains(movedState)) {
                        this.getStatesToVisit().add(movedState);
                        int calculatedDistance = heuristic.calculateDistance(movedState);
                        calculatedDistance += movedState.getDepth();//tu moze byc problem
                        this.statesToVisitWithHeuristicNumber.put(calculatedDistance, movedState);
                    }
                }
            }
        }
        return new int[0][];
    }
}
