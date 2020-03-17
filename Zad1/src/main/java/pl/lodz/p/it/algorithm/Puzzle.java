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
        Puzzle puzzle1 = (Puzzle) o;
        return Arrays.equals(puzzle, puzzle1.puzzle);
    }

    @Override
    public Puzzle clone() {
        return new Puzzle(Arrays.copyOf(puzzle, puzzle.length));
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
