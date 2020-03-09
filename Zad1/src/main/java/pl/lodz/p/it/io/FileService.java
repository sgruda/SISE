package pl.lodz.p.it.io;

import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


@Getter
public class FileService {

    private final String PUZZLE_FILE;
    private final String SOLVED_FILE;
    private final String ADDITIONAL_DATA_FILE;
    private final String ALGORITHM_FILE;
    private int ROW_NUMBER;
    private int COLUMN_NUMBER;



    public FileService(String PUZZLE_FILE, String SOLVED_FILE, String ADDITIONAL_DATA_FILE, String ALGORITHM_FILE) {
        this.PUZZLE_FILE = PUZZLE_FILE;
        this.SOLVED_FILE = SOLVED_FILE;
        this.ADDITIONAL_DATA_FILE = ADDITIONAL_DATA_FILE;
        this.ALGORITHM_FILE = ALGORITHM_FILE;
    }

    public byte[][] getPuzzle() {
        File file = new File(this.PUZZLE_FILE);
        try (Scanner sc = new Scanner(file)) {
            int line = 0;
            byte [][] puzzle = null;
            while (sc.hasNextLine()) {
                if(0 == line) {
                    this.ROW_NUMBER = sc.nextByte();
                    this.COLUMN_NUMBER = sc.nextByte();
                    puzzle = new byte[this.ROW_NUMBER][this.COLUMN_NUMBER];
                } else {
                    int i = 0;
                    while(sc.hasNextByte()) {
                        puzzle[line][i] = sc.nextByte();
                        i++;
                    }
                }
                line++;
            }
            return puzzle;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    return null;
    }
}
