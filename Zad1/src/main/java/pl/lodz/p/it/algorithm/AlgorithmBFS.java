package pl.lodz.p.it.algorithm;

import pl.lodz.p.it.enums.Direction;

public class AlgorithmBFS extends Algorithm {

    private String searchOrder;
    private String[] searchOrderArray;



    public AlgorithmBFS(int ROW_NUMBER, int COLUMN_NUMBER, int[][] puzzles, String searchOrder){
        super(ROW_NUMBER, COLUMN_NUMBER, puzzles);
        this.searchOrder = searchOrder.toUpperCase();
        this.searchOrderArray = searchOrder.split("");
    }

    @Override
    public int[][] solve(){
        this.getStatesToVisit().add(super.getCurrentState());
        while(!this.getStatesToVisit().isEmpty() && !this.isSolved()){
            State newState = this.getStatesToVisit().poll();
            this.getVisitedStates().add(newState);

            if(this.isSolved()){
                System.out.println("Powinno być już rozwiązane");
                this.setCurrentState(newState);
                this.getCurrentState().printPuzzle();
                return super.getCurrentState().getPuzzle();
            }

            for(int i = 0 ; i < this.searchOrder.length();i ++){
                if(newState.canMoved(this.searchOrderArray[i])){
                    State movedState = new State(newState);
                    System.out.println("Przed przesunięciem");
                    movedState.printPuzzle();
                    movedState.move(searchOrderArray[i]);
                    System.out.println("Po przesunięciu");
                    movedState.printPuzzle();
                    this.setCurrentState(movedState);
                    System.out.println("Rozwiazane ? " + this.isSolved());
                    this.getCurrentState().printPuzzle();
                    if(!this.getVisitedStates().contains(movedState)){
                        this.getStatesToVisit().add(movedState);
                    }
                    if(this.isSolved()){
                        break;
                    }
                }
            }
        }
        super.getCurrentState().printPuzzle();
        return this.getCurrentState().getPuzzle();
    }

    private Direction getDirectionToMove(int directionCharIndex) { //TO DO ogarnac czy nie pomyliłem bfs z dfs, przechodzi po tych kierunkach systematycznie
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
}
