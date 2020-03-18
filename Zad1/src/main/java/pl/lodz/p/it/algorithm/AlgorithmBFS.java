package pl.lodz.p.it.algorithm;

import com.sun.tools.javac.util.Pair;
import pl.lodz.p.it.enums.Direction;

public class AlgorithmBFS extends Algorithm {

    private final String searchOrder;
    private int directionCharIndex;
    private Pair<Integer, Integer> zeroCoordinates;
    private boolean repeatedState;

    public AlgorithmBFS(int ROW_NUMBER, int COLUMN_NUMBER, int[][] puzzles, String searchOrder){
        super(ROW_NUMBER, COLUMN_NUMBER, puzzles);
        this.searchOrder = searchOrder.toUpperCase();
        zeroCoordinates = locateZero(puzzles);
        directionCharIndex = -1;
        repeatedState = false;
    }
    private Pair<Integer, Integer> locateZero(int[][] tab) {
        int i = 0;
        int j;
        for(int[] b : tab) {
            j = 0;
            for(int b2 : b) {
                if(b2 == 0) {
                    return new Pair<>(i, j);
                }
                j++;
            }
            i++;
        }
        return null;
    }

    private void printPuzzle(int [][] tab){
        for(int [] b : tab){
            for(int element : b){
                System.out.print(element+" ");
            }
            System.out.println();
        }
    }

    private boolean isSolved() {
        int i = 1;
        for(int[] b : super.getCurrentState().getPuzzle()) {
            for(int b2 : b) {
                if(b2 == i) {
                    i++;
                    if(i == 16){
                        if(super.getCurrentState().getPuzzle()[super.getROW_NUMBER()-1][super.getCOLUMN_NUMBER()-1] == 0){
                            return true;
                        }
                    }
                }else{
                    return false;
                }
            }
        }
        return true;
    }

    private boolean canMoved(Direction direction) {
        switch (direction) {
            case up: {
                if (zeroCoordinates.fst == 0) {
                    return false;
                }
                return true;
            }
            case down: {
                if (zeroCoordinates.fst == super.getROW_NUMBER() - 1) {
                    return false;
                }
                return true;
            }
            case left: {
                if (zeroCoordinates.snd == 0) {
                    return false;
                }
                return true;
            }
            case right: {
                if (zeroCoordinates.snd == super.getCOLUMN_NUMBER() - 1) {
                    return false;
                }
                return true;
            }
        }
        return  false;
    }

    private State move(Direction direction) {
        super.getCurrentState().setVisited(true);
        super.getVisitedStates().add(super.getCurrentState());
        super.getCurrentState().printPuzzle();
        State newState = super.getCurrentState().clone();
        int newX = zeroCoordinates.fst;
        int newY = zeroCoordinates.snd;
        System.out.println(direction);
        switch (direction) {
            case up: {
                newX = zeroCoordinates.fst - 1;
                newY = zeroCoordinates.snd;
                break;
            }
            case down: {
                newX = zeroCoordinates.fst + 1;
                newY = zeroCoordinates.snd;
                break;
            }
            case left: {
                System.out.println(zeroCoordinates.fst + ", " + zeroCoordinates.snd);
                newX = zeroCoordinates.fst;
                newY = zeroCoordinates.snd - 1;
                break;
            }
            case right: {
                newX = zeroCoordinates.fst;
                newY = zeroCoordinates.snd + 1;
                break;
            }
        }
        newState.getPuzzle()[zeroCoordinates.fst][zeroCoordinates.snd] = newState.getPuzzle()[newX][newY];
        newState.getPuzzle()[newX][newY] = 0;
        if(super.statesContains(newState)) {
            repeatedState = true;
            return getCurrentState();
        }
        else {
            zeroCoordinates = new Pair<>(newX, newY);
            return newState;
        }
    }

    private Direction getDirectionToMove() { //TO DO ogarnac czy nie pomyliłem bfs z dfs, przechodzi po tych kierunkach systematycznie
        directionCharIndex++;
        if(directionCharIndex == searchOrder.length()) {
            directionCharIndex = 0;
        }
        switch (searchOrder.charAt(directionCharIndex)) {
            case 'U':
                return Direction.up;
            case 'D':
                return Direction.down;
            case 'L':
                return Direction.left;
            case 'R':
                return Direction.right;
            default:
                return null;
        }
    }
    public int[][] solve() {
        Direction direction = getDirectionToMove();
        do{
            if(canMoved(direction)) {
                State state = move(direction);
                if(repeatedState) {
                    repeatedState = false;
                    direction = getDirectionToMove();
                }
//                if(state.isVisited()) {
//                    direction = getDirectionToMove();
//                }
                super.setCurrentState(state);
            }
            else direction = getDirectionToMove();
        } while (!isSolved() && !super.getStatesToVisit().isEmpty());
        return super.getCurrentState().getPuzzle();
    }
}
