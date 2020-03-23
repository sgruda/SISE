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
        this.statesToVisitWithHeuristicNumber.put(Integer.MAX_VALUE-1, super.getCurrentState());
        while (!statesToVisitWithHeuristicNumber.isEmpty() && !isSolved()) {
            State newState = findBestStateForHeuristic();
            this.getVisitedStates().add(newState);

            for (int directionCharIndex = 0; directionCharIndex < super.getSearchOrder().length(); directionCharIndex++) {
                if (newState.canMoved(this.getDirectionToMove(directionCharIndex))) {
                    State movedState = newState.clone();
                    movedState.move(super.getDirectionToMove(directionCharIndex));
                    this.setCurrentState(movedState);
                    if (!this.getVisitedStates().contains(movedState)) {
                        int calculatedDistance = heuristic.calculateDistance(movedState) + movedState.getDepth();
                        this.statesToVisitWithHeuristicNumber.put(calculatedDistance, movedState);
                    }
                    if (this.isSolved()) {
                        this.getCurrentState().printPuzzle();
                        return super.getCurrentState().getPuzzle();
                    }
                }
            }
        }
        this.getCurrentState().printPuzzle();
        return super.getCurrentState().getPuzzle();
    }
    private State findBestStateForHeuristic() {
        State bestState = new State(super.getCurrentState());
        int bestHeuristicNumber = Integer.MAX_VALUE;

        for(int heuristicNumber : this.statesToVisitWithHeuristicNumber.keySet()) {
            if(heuristicNumber < bestHeuristicNumber) {
                bestHeuristicNumber = heuristicNumber;
            }
        }
        if(this.statesToVisitWithHeuristicNumber.containsKey(bestHeuristicNumber)) {
            bestState = statesToVisitWithHeuristicNumber.get(bestHeuristicNumber);
            this.statesToVisitWithHeuristicNumber.remove(bestHeuristicNumber);
        }

        return bestState;
    }
}
