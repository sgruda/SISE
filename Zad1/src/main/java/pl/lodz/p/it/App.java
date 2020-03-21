package pl.lodz.p.it;


import pl.lodz.p.it.algorithm.Algorithm;
import pl.lodz.p.it.io.FileService;

import java.io.IOException;

public class App {
    public static void main( String[] args ) throws IOException {
        /*  ARGS
            0 - Acronym of algorithm
            1 - Order of searching in algorithms: BFS, DFS
                Acronym of heuristics
            2 - Path to file with puzzle to solve
            3 - Path to file for solved puzzle
            4 - Path to file for additional data
         */
        FileService fileService = new FileService(args[2], args[3], args[4]);
        Algorithm algorithm = AlgorithmFactory.getAlgorithm(args, fileService);
        fileService.saveData(algorithm.solveWithTimeCalculation(),algorithm.getStatistics(),algorithm.getExecutionTime());
    }
}
