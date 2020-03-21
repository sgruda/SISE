package pl.lodz.p.it;

import pl.lodz.p.it.algorithm.Algorithm;
import pl.lodz.p.it.algorithm.AlgorithmBFS;
import pl.lodz.p.it.algorithm.AlgorithmDFS;
import pl.lodz.p.it.io.FileService;

public class AlgorithmFactory {
    public static Algorithm getAlgorithm(String[] args, FileService fileService) {
        switch (args[0]) {
            case "bfs":
            case "BFS":
                return new AlgorithmBFS(fileService.getCOLUMN_NUMBER(), fileService.getROW_NUMBER(), fileService.getPuzzle(), args[1]);
            case "dfs":
            case "DFS":
                return new AlgorithmDFS(fileService.getCOLUMN_NUMBER(), fileService.getROW_NUMBER(), fileService.getPuzzle(), args[1]);
            case "astr":
            case "ASTR":
                return null; //TODO
        }
        return null;
    }
}
