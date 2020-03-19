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
    private final int COLUMN_NUMBER;
    private final int ROW_NUMBER;
    private int zeroX, zeroY;

    public State(int[][] puzzle, int COLUMN_NUMBER, int ROW_NUMBER) {
        this.puzzle = puzzle;
        this.COLUMN_NUMBER = COLUMN_NUMBER;
        this.ROW_NUMBER = ROW_NUMBER;
        locateZero();
    }

    public State(State newState){
        this.COLUMN_NUMBER = newState.COLUMN_NUMBER;
        this.ROW_NUMBER = newState.ROW_NUMBER;
        this.puzzle = new int[ROW_NUMBER][COLUMN_NUMBER];
        for(int i=0; i<ROW_NUMBER; i++) {
            if (COLUMN_NUMBER >= 0) System.arraycopy(newState.puzzle[i], 0, puzzle[i], 0, COLUMN_NUMBER);
        }

        locateZero();

    }
    private void  locateZero() {
        for(int i = 0 ; i < ROW_NUMBER; i++) {
            for(int j = 0 ; j < COLUMN_NUMBER ; j++) {
                if(puzzle[i][j] == 0) {
                    this.zeroX = i;
                    this.zeroY = j;
                }
            }
        }
    }

    public void swapFields(int x1, int y1, int x2, int y2) {
        int tmp = puzzle[x1][y1];
        puzzle[x1][y1] = puzzle[x2][y2];
        puzzle[x2][y2] = tmp;
        locateZero();
    }

    public void move(String direction) {
        this.printPuzzle();
        switch (direction) {
            case "U": {
                swapFields(zeroX,zeroY,zeroX -1 ,zeroY);
                this.printPuzzle();
                break;
            }
            case "D": {
                swapFields(zeroX,zeroY,zeroX + 1 ,zeroY);
                this.printPuzzle();
                break;
            }
            case "L": {
                swapFields(zeroX,zeroY,zeroX ,zeroY - 1);
                this.printPuzzle();
                break;
            }
            case "R": {
                swapFields(zeroX,zeroY,zeroX ,zeroY + 1);
                this.printPuzzle();
                break;
            }
        }
    }
    public boolean canMoved(String direction) {
        switch (direction) {
            case "U": {
                if (zeroX == 0) {
                    return false;
                }
                System.out.println("A teraz idziemy w =" + direction);
                return true;
            }
            case "D": {
                if (zeroX == getROW_NUMBER() - 1) {
                    return false;
                }
                System.out.println("A teraz idziemy w =" + direction);
                return true;
            }
            case "L": {
                if (zeroY == 0) {
                    return false;
                }
                System.out.println("A teraz idziemy w =" + direction);
                return true;
            }
            case "R": {
                if (zeroY == getCOLUMN_NUMBER() - 1) {
                    return false;
                }
                System.out.println("A teraz idziemy w =" + direction);
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
        return new State(ret, getCOLUMN_NUMBER(), getROW_NUMBER());
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
