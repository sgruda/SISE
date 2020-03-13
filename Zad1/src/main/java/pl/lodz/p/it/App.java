package pl.lodz.p.it;


import pl.lodz.p.it.algorithm.BFS;
import pl.lodz.p.it.io.FileService;

import java.io.IOException;

public class App {
    public static void main( String[] args ) throws IOException {
        FileService fileService = new FileService(args[2], args[3], "","");
        BFS bfs = new BFS(fileService.getCOLUMN_NUMBER(), fileService.getROW_NUMBER(), fileService.getPuzzle(), args[1]);
        fileService.saveData(bfs.solve());
    }
}
