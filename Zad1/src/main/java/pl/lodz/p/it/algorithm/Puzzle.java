package pl.lodz.p.it.algorithm;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;

@AllArgsConstructor
@Getter
@Setter
public class Puzzle {
    private int [][] puzzle;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Puzzle))
            return false;
        Puzzle puzzleToCompare = (Puzzle) o;
        for(int i = 0; i < puzzle.length; i++) {
            for(int j =0; j < puzzle[i].length; j++)
                if(puzzle[i][j] != puzzleToCompare.getPuzzle()[i][j])
                    return false;
        }
        return true;
    }

    @Override
    public Puzzle clone() {
        int ret[][] = new int[puzzle.length][];
        int i = 0;
        for(int[] row : puzzle) {
            ret[i] = Arrays.copyOf(row, row.length);
            i++;
        }
        return new Puzzle(ret);
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
