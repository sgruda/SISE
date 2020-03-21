package pl.lodz.p.it.algorithm;

public class AlgorithmBFS extends Algorithm {

    public AlgorithmBFS(int ROW_NUMBER, int COLUMN_NUMBER, int[][] puzzles, String searchOrder) {
        super(ROW_NUMBER, COLUMN_NUMBER, puzzles, searchOrder);
    }

    @Override
    public int[][] solve() {
        this.getStatesToVisit().add(super.getCurrentState());
        while (!this.getStatesToVisit().isEmpty() && !this.isSolved()) {
            State newState = this.getStatesToVisit().poll();
            this.getVisitedStates().add(newState);
            for (int directionCharIndex = 0; directionCharIndex < super.getSearchOrder().length(); directionCharIndex++) {
                if (newState.canMoved(super.getDirectionToMove(directionCharIndex))) {
                    State movedState = newState.clone();
                    movedState.move(super.getDirectionToMove(directionCharIndex));
                    this.setCurrentState(movedState);
                    if (!this.getVisitedStates().contains(movedState)) {
                        this.getStatesToVisit().add(movedState);
                    }
                    if (this.isSolved()) {
                        System.out.println(this.getCurrentState().getSolutionSteps());
                        System.out.println(this.getCurrentState().getDepth());
                        this.getCurrentState().printPuzzle();
                        return super.getCurrentState().getPuzzle();
                    }
                }
            }
        }

        System.out.println(this.getCurrentState().getSolutionSteps());
        System.out.println(this.getCurrentState().getDepth());
        this.getCurrentState().printPuzzle();
        return this.getCurrentState().getPuzzle();
    }
}
