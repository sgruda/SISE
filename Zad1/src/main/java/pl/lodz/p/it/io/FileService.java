package pl.lodz.p.it.io;

import com.sun.tools.javac.util.Pair;
import lombok.Getter;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;


@Getter
public class FileService {

    private final String PUZZLE_FILE;
    private final String SOLVED_FILE;
    private final String ADDITIONAL_DATA_FILE;
    private final String ALGORITHM_FILE;
    private int ROW_NUMBER;
    private int COLUMN_NUMBER;
    private byte[][] puzzle;


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
            byte [][] puzzle = null;
            while (sc.hasNextLine()) {
                if(0 == line) {
                    this.ROW_NUMBER = sc.nextByte();
                    this.COLUMN_NUMBER = sc.nextByte();
                    puzzle = new byte[this.ROW_NUMBER][this.COLUMN_NUMBER];
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
    public void saveData(final byte[][] solvedPuzzle)  {
        File file = new File(this.SOLVED_FILE);
        try (FileOutputStream fout = new FileOutputStream(file)) {
            byte i = 0;
            for(byte [] b : solvedPuzzle) {
                for(byte b2 : b) {
                    fout.write(String.valueOf(b2).getBytes());
                    i++;
                }
                fout.write(System.getProperty("line.separator").getBytes());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}