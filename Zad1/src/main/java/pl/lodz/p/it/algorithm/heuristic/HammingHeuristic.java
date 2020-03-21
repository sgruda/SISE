package pl.lodz.p.it.algorithm.heuristic;

import pl.lodz.p.it.algorithm.State;

public class HammingHeuristic implements Heuristic {
    @Override
    public int calculateDistance(State state) {
        int distance = 0;
        int i = 1;
        for (int[] tab : state.getPuzzle()) {
            for (int value : tab) {
                if (value == i) {
                    distance++;
                    if (i == 15) {
                        int[][] puzzleTemp = state.getPuzzle();
                        if (puzzleTemp[state.getROW_NUMBER() - 1][state.getCOLUMN_NUMBER() - 1] == 0) {
                            distance++;
                        }
                    }
                    i++;
                }
            }
        }
        return distance;
    }
}
