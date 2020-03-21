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
        while (!this.getStatesToVisitWithHeuristicNumber().isEmpty() && !super.isSolved()) {
            State newState = this.findBestStateForHeuristic();
            this.getVisitedStates().add(newState);
            System.out.println();
            newState.printPuzzle();
            System.out.println();

            for (int directionCharIndex = 0; directionCharIndex < this.getSearchOrder().length(); directionCharIndex++) {
                if(newState.canMoved(this.getDirectionToMove(directionCharIndex))) {
//                    State movedState = newState.clone();
                    State movedState = new State(newState);
                    movedState.move(this.getDirectionToMove(directionCharIndex));
                    this.setCurrentState(movedState);
                    System.out.println(!this.getVisitedStates().contains(movedState));
                    if (!this.getVisitedStates().contains(movedState)) {
//                        this.getStatesToVisit().add(movedState);
                        int calculatedDistance = heuristic.calculateDistance(movedState);
                        calculatedDistance += movedState.getDepth();//tu moze byc problem
                        System.out.println("calculatedDistance = " + calculatedDistance);
                        this.statesToVisitWithHeuristicNumber.put(calculatedDistance, movedState);
                    }
                }
            }
        }
        System.out.println("Rozwiazanie:");
        super.getCurrentState().printPuzzle();
        return super.getCurrentState().getPuzzle();
    }
    private State findBestStateForHeuristic() {
//        State bestState = super.getCurrentState().clone();
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
