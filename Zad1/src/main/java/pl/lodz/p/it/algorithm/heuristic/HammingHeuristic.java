package pl.lodz.p.it.algorithm.heuristic;

import pl.lodz.p.it.algorithm.State;

public class HammingHeuristic implements Heuristic {
    private int[][] correctPuzzle;

    @Override
    public int calculateDistance(State state) {
        int distance = 0;
        createCorrectPuzzle(state.getROW_NUMBER(), state.getCOLUMN_NUMBER());
        for(int i = 0; i < state.getROW_NUMBER(); i++) {
            for(int j = 0; j < state.getCOLUMN_NUMBER(); j++) {
                if(state.getPuzzle()[i][j] != this.correctPuzzle[i][j])
                    distance++;
            }
        }
        return distance;
    }
    private void createCorrectPuzzle(final int ROW_NUMBER, final int COLUMN_NUMBER) {
        correctPuzzle = new int[ROW_NUMBER][COLUMN_NUMBER];
        int value = 1;
        for(int i = 0; i < ROW_NUMBER; i++) {
            for(int j = 0; j<COLUMN_NUMBER; j++) {
                if(value == 16) {
                    correctPuzzle[i][j] = 0;
                }
                else {
                    correctPuzzle[i][j] = value;
                }
                value++;
            }
        }
    }
}
