package pl.lodz.p.it.abstraction;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public abstract class Solution {
    private final int COLUMN_NUMBER;
    private final int ROW_NUMBER;
    private final int SIZE;
    private final byte[][] puzzles;

    public Solution(int COLUMN_NUMBER, int ROW_NUMBER, byte[][] puzzles) {
        this.COLUMN_NUMBER = COLUMN_NUMBER;
        this.ROW_NUMBER = ROW_NUMBER;
        this.SIZE = COLUMN_NUMBER * ROW_NUMBER;
        this.puzzles = puzzles;
    }
}
