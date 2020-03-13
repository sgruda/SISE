package pl.lodz.p.it.algorithm;

import com.sun.tools.javac.util.Pair;
import pl.lodz.p.it.abstraction.Direction;
import pl.lodz.p.it.abstraction.Solution;

import java.util.LinkedList;

public class BFS extends Solution {

    private LinkedList<int[][]> states;
    private int[][] currentState;
    private Pair<Integer, Integer> zeroCoordinates;
    private final String searchOrder;
    private int directionCharIndex;

    public BFS(int COLUMN_NUMBER, int ROW_NUMBER, int[][] puzzles, String searchOrder){
        super(COLUMN_NUMBER, ROW_NUMBER, puzzles);
        this.searchOrder = searchOrder.toUpperCase();
        states = new LinkedList<>();
        currentState = puzzles.clone();
        zeroCoordinates = locateZero(puzzles);
        directionCharIndex = -1;
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
    private boolean isSolved() {
        int i = 0;
        for(int[] b : currentState) {
            for(int b2 : b) {
                if(b2 != i) {
                    return currentState[super.getROW_NUMBER()-1][super.getCOLUMN_NUMBER()-1] == 0 ?  true :  false;
                }
                i++;
            }
        }
        return true;
    }
    private boolean canMoved(Direction direction) {
        switch (direction) {
            case up: {
                if (zeroCoordinates.snd == super.getROW_NUMBER()) {
                    return false;
                }
                return true;
            }
            case down: {
                if (zeroCoordinates.snd == 0) {
                    return false;
                }
                return true;
            }
            case left: {
                if (zeroCoordinates.fst == 0) {
                    return false;
                }
                return true;
            }
            case right: {
                if (zeroCoordinates.fst == super.getCOLUMN_NUMBER()) {
                    return false;
                }
                return true;
            }
        }
        return  false;
    }
    private int[][] move(Direction direction) {
        states.add(currentState);
        int[][] newState = currentState.clone();
        int newX = zeroCoordinates.fst;
        int newY = zeroCoordinates.snd;

        switch (direction) {
            case up: {
                newX = zeroCoordinates.fst;
                newY = zeroCoordinates.snd + 1;
                break;
            }
            case down: {
                newX = zeroCoordinates.fst;
                newY = zeroCoordinates.snd - 1;
                break;
            }
            case left: {
                newX = zeroCoordinates.fst - 1;
                newY = zeroCoordinates.snd;
                break;
            }
            case right: {
                newX = zeroCoordinates.fst + 1;
                newY = zeroCoordinates.snd;
                break;
            }
        }
        newState[zeroCoordinates.fst][zeroCoordinates.snd] = newState[newX][newY];
        newState[newX][newY] = 0;
        zeroCoordinates = new Pair<>(newX, newY);
        return newState;
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
        Direction direction;
        while (!isSolved()){
            direction = getDirectionToMove();
            if(canMoved(direction))
                currentState = move(direction);
        }
        return currentState;
    }
}
