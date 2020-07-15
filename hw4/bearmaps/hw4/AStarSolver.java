package bearmaps.hw4;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private LinkedList<Vertex> solution = new LinkedList<>();
    private double timeSpent;
    private double solutionWeight = 0;
    private int numStatesExplored = 0;

    private HashMap<Vertex, Double> distTo = new HashMap<>(); //vertex与start的距离
    private HashMap<Vertex, Vertex> edgeTo = new HashMap<>();

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        DoubleMapPQ<Vertex> fringe = new DoubleMapPQ<>(); //fringe队列

        fringe.add(start, input.estimatedDistanceToGoal(start, end));//fringe加入起点
        distTo.put(start, 0.0);
        edgeTo.put(start, null);
        while (fringe.size() != 0) {
            Vertex p = fringe.removeSmallest();
            //找到end，SOLVED
            if (p.equals(end)) {
                outcome = SolverOutcome.SOLVED; //outcome
                timeSpent = sw.elapsedTime(); //timeSpent
                Vertex temVertex = p;
                do {
                    solution.addFirst(temVertex);
                    temVertex = edgeTo.get(temVertex);
                    numStatesExplored ++; //numStatesExplored
                } while (temVertex != null); //solution

                solutionWeight = distTo.get(p); //solutionWeight

                return;
            }

            List<WeightedEdge<Vertex>> neighbors = input.neighbors(p);
            for(WeightedEdge<Vertex> neighbor: neighbors) {
                Vertex q = neighbor.to();
                double newDist = distTo.get(p) + neighbor.weight();
                //若distTo不存在q，则意味着q未被读取过。或者distTo有更优解。需要更新
                if (!distTo.containsKey(q) || newDist < distTo.get(q)) {
                    //Relax edge
                    if (fringe.contains(q)) {
                        fringe.changePriority(q, newDist + input.estimatedDistanceToGoal(q, end));
                    } else {
                        fringe.add(q, newDist + input.estimatedDistanceToGoal(q, end));
                    }
                    edgeTo.put(q, p);
                    distTo.put(q, newDist);
                }

                //超时,TIMEOUT
                if (sw.elapsedTime() > timeout) {
                    outcome = SolverOutcome.TIMEOUT;
                    return;
                }
            }
        }
        outcome = SolverOutcome.UNSOLVABLE;
    }


    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
