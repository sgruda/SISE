package pl.lodz.p.it.algorithm.heuristic;

import pl.lodz.p.it.algorithm.State;

public interface Heuristic {
    public int calculateDistance(State state);
}
