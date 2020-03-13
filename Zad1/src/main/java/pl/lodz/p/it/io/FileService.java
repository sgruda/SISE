package pl.lodz.p.it.io;

import lombok.Getter;

import java.io.*;
import java.util.Scanner;


@Getter
public class FileService {

    private final String PUZZLE_FILE;
    private final String SOLVED_FILE;
    private final String ADDITIONAL_DATA_FILE;
    private final String ALGORITHM_FILE;
    private int ROW_NUMBER;
    private int COLUMN_NUMBER;
    private int[][] puzzle;


    public FileService(String PUZZLE_FILE, String SOLVED_FILE, String ADDITIONAL_DATA_FILE, String ALGORITHM_FILE) {
        this.PUZZLE_FILE = PUZZLE_FILE;
        this.SOLVED_FILE = SOLVED_FILE;
        this.ADDITIONAL_DATA_FILE = ADDITIONAL_DATA_FILE;
        this.ALGORITHM_FILE = ALGORITHM_FILE;
        this.loadData();
    }

    private void loadData() {
        File file = new File(this.PUZZLE_FILE);
        try (Scanner sc = new Scanner(file)) {
            int line = 0;
            int [][] puzzle = null;
            while (sc.hasNextLine()) {
                if(0 == line) {
                    this.ROW_NUMBER = sc.nextByte();
                    this.COLUMN_NUMBER = sc.nextByte();
                    puzzle = new int[this.ROW_NUMBER][this.COLUMN_NUMBER];
                } else {
                    int i = 0;
                    while(i < this.COLUMN_NUMBER) {
                        puzzle[line - 1][i] = sc.nextByte();
                        i++;
                    }
                }
                line++;
            }
            this.puzzle = puzzle;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void saveData(final int[][] solvedPuzzle) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < solvedPuzzle.length; i++) {
            for (int j = 0; j < solvedPuzzle.length; j++) {
                builder.append(solvedPuzzle[i][j] + "");
                if (j < solvedPuzzle.length - 1)
                    builder.append(" ");
            }
            builder.append("\n");
        }
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File(this.SOLVED_FILE)))) {
            writer.write(builder.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
