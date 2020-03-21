package pl.lodz.p.it.algorithm;

import pl.lodz.p.it.algorithm.heuristic.Heuristic;

import java.util.HashMap;
import java.util.Map;

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
        return new int[0][];
    }
}
