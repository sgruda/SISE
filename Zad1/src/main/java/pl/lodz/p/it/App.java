package pl.lodz.p.it;


import pl.lodz.p.it.algorithm.Algorithm;
import pl.lodz.p.it.io.FileService;

import java.io.IOException;

public class App {
    public static void main( String[] args ) throws IOException {
        FileService fileService = new FileService(args[2], args[3], args[4]);
        Algorithm algorithm = AlgorithmFactory.getAlgorithm(args, fileService);
        fileService.saveData(algorithm.runWithTimeCounter(), algorithm);
    }
}
