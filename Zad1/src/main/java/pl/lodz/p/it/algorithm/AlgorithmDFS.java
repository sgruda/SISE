package pl.lodz.p.it.algorithm;

import lombok.Getter;

import java.util.Stack;


public class AlgorithmDFS extends Algorithm {
    private final int DEPTH_LIMIT = 22;
    private Stack<State> toVisit;

    public AlgorithmDFS(int ROW_NUMBER, int COLUMN_NUMBER, int[][] puzzles, String searchOrder) {
        super(ROW_NUMBER, COLUMN_NUMBER, puzzles, searchOrder);
        this.toVisit = new Stack<>();
    }

    @Override
    public int[][] solve() {

        toVisit.add(this.getCurrentState());

        while(!toVisit.isEmpty()) {
            State newFrame = new State(toVisit.pop());
            if(this.getVisitedStates().contains(newFrame)) {
                continue;
            }
            this.getVisitedStates().add(newFrame);
            //this.incraseDepth(newFrame);

            if(newFrame.getDepth() >= DEPTH_LIMIT) {
                continue;
            }

            if(this.isSolved(newFrame)) {
                this.setCurrentState(newFrame);
                //this.generateDetails();
                System.out.print("Rozwiazanie: ");
                this.getCurrentState().printPuzzle();
                return this.getCurrentState().getPuzzle();
            }

            for (int directionCharIndex = 0; directionCharIndex < super.getSearchOrder().length(); directionCharIndex++) {
                if (newFrame.canMoved(super.getDirectionToMove(directionCharIndex))) {
                    State movedFrame = new State(newFrame);
                    movedFrame.move(super.getDirectionToMove(directionCharIndex));

                    if(!this.getVisitedStates().contains(movedFrame))
                        toVisit.add(movedFrame);
                }
            }
        }

        System.out.print("Rozwiazanie: ");
        this.getCurrentState().printPuzzle();
        return super.getCurrentState().getPuzzle();
    }
}

