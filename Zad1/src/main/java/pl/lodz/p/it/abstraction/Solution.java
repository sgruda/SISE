package pl.lodz.p.it.abstraction;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter
public abstract class Solution {
    private final int COLUMN_NUMBER;
    private final int ROW_NUMBER;
    private final int[][] puzzles;

    @Setter
    private LinkedList<int[][]> states;
    @Setter
    private int[][] currentState;

    public Solution(int COLUMN_NUMBER, int ROW_NUMBER, int[][] puzzles) {
        this.COLUMN_NUMBER = COLUMN_NUMBER;
        this.ROW_NUMBER = ROW_NUMBER;
        this.puzzles = puzzles;

        this.states = new LinkedList<>();
        this.currentState = puzzles.clone();
    }
}
