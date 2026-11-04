package Algoritmi.lab3.model;

import java.util.Collections;
import java.util.List;

public class PathResult {
    private final long minPenalty;
    private final List<Point> path;
    private final boolean reachable;

    public PathResult(long minPenalty, List<Point> path, boolean reachable) {
        this.minPenalty = minPenalty;
        this.path = path != null ? path : Collections.emptyList();
        this.reachable = reachable;
    }

    public static PathResult unreachable() {
        return new PathResult(-1, Collections.emptyList(), false);
    }

    public long getMinPenalty() {
        return minPenalty;
    }

    public List<Point> getPath() {
        return path;
    }

    public boolean isReachable() {
        return reachable;
    }
}