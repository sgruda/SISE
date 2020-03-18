package pl.lodz.p.it.algorithm;

import com.sun.tools.javac.util.Pair;
import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.enums.Direction;

import java.util.Arrays;

@Getter
@Setter
public class State {
    private int [][] puzzle;
    private boolean visited;
    private final int COLUMN_NUMBER;
    private final int ROW_NUMBER;
    private Pair<Integer, Integer> zeroCoordinates;

    public State(int[][] puzzle, int COLUMN_NUMBER, int ROW_NUMBER) {
        this.puzzle = puzzle;
        this.visited = false;
        this.COLUMN_NUMBER = COLUMN_NUMBER;
        this.ROW_NUMBER = ROW_NUMBER;
        this.zeroCoordinates = locateZero();
    }
    public State(int[][] puzzle, boolean visited, int COLUMN_NUMBER, int ROW_NUMBER) {
        this.puzzle = puzzle;
        this.visited = visited;
        this.COLUMN_NUMBER = COLUMN_NUMBER;
        this.ROW_NUMBER = ROW_NUMBER;
        this.zeroCoordinates = locateZero();
    }
    private Pair<Integer, Integer> locateZero() {
        int i = 0;
        int j;
        for(int[] tab : puzzle) {
            j = 0;
            for(int value : tab) {
                if(value == 0) {
                    return new Pair<>(i, j);
                }
                j++;
            }
            i++;
        }
        return null;
    }
    public State move(Direction direction) {
        State newState = this.clone();
        int newX = zeroCoordinates.fst;
        int newY = zeroCoordinates.snd;
        System.out.println(direction);
        switch (direction) {
            case up: {
                newX = zeroCoordinates.fst - 1;
                newY = zeroCoordinates.snd;
                break;
            }
            case down: {
                newX = zeroCoordinates.fst + 1;
                newY = zeroCoordinates.snd;
                break;
            }
            case left: {
                System.out.println(zeroCoordinates.fst + ", " + zeroCoordinates.snd);
                newX = zeroCoordinates.fst;
                newY = zeroCoordinates.snd - 1;
                break;
            }
            case right: {
                newX = zeroCoordinates.fst;
                newY = zeroCoordinates.snd + 1;
                break;
            }
        }
        newState.getPuzzle()[zeroCoordinates.fst][zeroCoordinates.snd] = newState.getPuzzle()[newX][newY];
        newState.getPuzzle()[newX][newY] = 0;

        zeroCoordinates = new Pair<>(newX, newY);
        return newState;

    }
    public boolean canMoved(Direction direction) {
        switch (direction) {
            case up: {
                if (zeroCoordinates.fst == 0) {
                    return false;
                }
                return true;
            }
            case down: {
                if (zeroCoordinates.fst == getROW_NUMBER() - 1) {
                    return false;
                }
                return true;
            }
            case left: {
                if (zeroCoordinates.snd == 0) {
                    return false;
                }
                return true;
            }
            case right: {
                if (zeroCoordinates.snd == getCOLUMN_NUMBER() - 1) {
                    return false;
                }
                return true;
            }
        }
        return  false;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof State))
            return false;
        State stateToCompare = (State) o;
        for(int i = 0; i < puzzle.length; i++) {
            for(int j =0; j < puzzle[i].length; j++)
                if(puzzle[i][j] != stateToCompare.getPuzzle()[i][j])
                    return false;
        }
        return true;
    }

    @Override
    public State clone() {
        int ret[][] = new int[puzzle.length][];
        int i = 0;
        for(int[] row : puzzle) {
            ret[i] = Arrays.copyOf(row, row.length);
            i++;
        }
        return new State(ret, this.visited, getCOLUMN_NUMBER(), getROW_NUMBER());
    }

    public void printPuzzle(){
        for(int [] b : puzzle){
            for(int element : b){
                System.out.print(element+" ");
            }
            System.out.println();
        }
    }
}
