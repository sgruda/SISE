package pl.lodz.p.it.algorithm;

import com.sun.tools.javac.util.Pair;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pl.lodz.p.it.enums.Direction;


@Getter
@Setter
@AllArgsConstructor
public class State {
    private int [][] puzzle;
    private final int COLUMN_NUMBER;
    private final int ROW_NUMBER;
    private Pair<Integer, Integer> zeroCoordinates;
    private String solutionSteps = "";
    private int depth=0;

    public State(int[][] puzzle, int COLUMN_NUMBER, int ROW_NUMBER) {
        this.puzzle = puzzle;
        this.COLUMN_NUMBER = COLUMN_NUMBER;
        this.ROW_NUMBER = ROW_NUMBER;
        this.zeroCoordinates = locateZero();
    }

    private Pair<Integer, Integer> locateZero() {
        for(int i = 0 ; i < ROW_NUMBER; i++) {
            for(int j = 0 ; j < COLUMN_NUMBER ; j++) {
                if(puzzle[i][j] == 0) {
                    return new Pair<>(i,j);
                }
            }
        }
        return null;
    }

    public void move(Direction direction) {
            solutionSteps += direction + " ";
            depth++;
            int newX = zeroCoordinates.fst;
            int newY = zeroCoordinates.snd;
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
        int temp = puzzle[zeroCoordinates.fst][zeroCoordinates.snd];
        puzzle[zeroCoordinates.fst][zeroCoordinates.snd] = puzzle[newX][newY];
        puzzle[newX][newY] = temp;
        this.zeroCoordinates = locateZero();

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
            ret[i] = java.util.Arrays.copyOf(row, row.length);
            i++;
        }
        return new State(ret, getCOLUMN_NUMBER(), getROW_NUMBER(), getZeroCoordinates(), getSolutionSteps(), getDepth());
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
