package pl.lodz.p.it.abstraction;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class Solution {
    private final int WIDTH;
    private final int HEIGHT;
    private final int SIZE = WIDTH * HEIGHT;
    private final byte[] puzzles;
}
