package pl.lodz.p.it.algorithm.heuristic;

import com.sun.tools.javac.util.Pair;
import pl.lodz.p.it.algorithm.State;

public class ManhattanHeuristic implements Heuristic {
    private int[][] correctPuzzle;

    @Override
    public int calculateDistance(State state) {
        int distance = 0;
        this.createCorrectPuzzle(state.getROW_NUMBER(), state.getCOLUMN_NUMBER());
        for (int x = 0 ; x < state.getROW_NUMBER(); x++) {
            for (int y = 0; y < state.getCOLUMN_NUMBER(); y++) {
                if (state.getPuzzle()[x][y] != 0) {
                    Pair<Integer, Integer> coords = locateValue(state.getPuzzle()[x][y]);
                    distance += Math.abs(x - coords.fst) + Math.abs(y - coords.snd);
                }
            }
        }
        return distance;
    }
    private Pair<Integer, Integer> locateValue(int value) {
        for(int i = 0 ; i < this.correctPuzzle.length; i++) {
            for(int j = 0 ; j < this.correctPuzzle[i].length ; j++) {
                if(correctPuzzle[i][j] == value) {
                    return new Pair<>(i,j);
                }
            }
        }
        return null;
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
