package pl.lodz.p.it.algorithm;

import com.sun.tools.javac.util.Pair;
import pl.lodz.p.it.abstraction.Direction;
import pl.lodz.p.it.abstraction.Solution;

import java.util.LinkedList;

public class BFS extends Solution {

    private LinkedList<byte[][]> states;
    private byte[][] currentState;
    private Pair<Byte, Byte> zeroCoordinates;
    private final String searchOrder;
    private int directionCharIndex;

    public BFS(int COLUMN_NUMBER, int ROW_NUMBER, byte[][] puzzles, String searchOrder){
        super(COLUMN_NUMBER, ROW_NUMBER, puzzles);
        this.searchOrder = searchOrder.toUpperCase();
        states = new LinkedList<byte[][]>();
        currentState = new byte[super.getCOLUMN_NUMBER()][super.getROW_NUMBER()];
        zeroCoordinates = locateZero(puzzles);
        directionCharIndex = -1;
        System.out.println(puzzles);
    }
    private Pair<Byte, Byte> locateZero(byte[][] tab) {
        byte i = 0;
        for(byte[] b : tab) {
            for(byte b2 : b) {
                if(b2 == 0) {
                   // System.out.println("zero coords " + i + " " + b2);
                    return new Pair<>(i, b2);
                }
            }
            i++;
        }
        return null;
    }
    private boolean isSolved() {
        int i = 0;
        for(byte[] b : currentState) {
            for(byte b2 : b) {
                if(b2 != i) {
                    return false;
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
    private byte[][] move(Direction direction) {
        states.add(currentState);
        byte[][] newState = currentState.clone();
        byte newX = zeroCoordinates.fst;
        byte newY = zeroCoordinates.snd;
        switch (direction) {
            case up: {
                newX = zeroCoordinates.fst;
                newY = (byte) (zeroCoordinates.snd + 1);
                break;
            }
            case down: {
                newX = zeroCoordinates.fst;
                newY = (byte) (zeroCoordinates.snd - 1);
                break;
            }
            case left: {
                newX = (byte) (zeroCoordinates.fst - 1);
                newY = zeroCoordinates.snd;
                break;
            }
            case right: {
                newX = (byte) (zeroCoordinates.fst + 1);
                newY = zeroCoordinates.snd;
                break;
            }
        }
//        System.out.println("zeroCoordinates.fst = " + zeroCoordinates.fst + " zeroCoordinates.snd = " + zeroCoordinates.snd);
        //System.out.println("newX = " + newX + " newY = " + newY);
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
    public byte[][] solve() {
        Direction direction;
        while (!isSolved()){
            direction = getDirectionToMove();
            if(canMoved(direction))
                currentState = move(direction);
        }
        return currentState;
    }

}
