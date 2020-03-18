package pl.lodz.p.it.algorithm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@Getter
@Setter
@AllArgsConstructor
public class State {
    private int [][] puzzle;
    private boolean visited;

    public State(int[][] puzzle) {
        this.puzzle = puzzle;
        this.visited = false;
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
        return new State(ret, this.visited);
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
