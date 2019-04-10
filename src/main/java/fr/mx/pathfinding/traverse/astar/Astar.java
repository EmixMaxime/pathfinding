package main.java.fr.mx.pathfinding.traverse.astar;

import fr.mx.maths.ToleranceDoubleComparator;
import fr.mx.pathfinding.traverse.Explorable;
import fr.mx.pathfinding.traverse.TracableStep;

import java.util.*;

/**
 * Typical implementations of A* use a priority queue to perform the repeated selection of minimum
 * (estimated) cost nodes to expand.
 *
 * <p>f(n) = g(n) + h(n) where n is the next node on the path, g(n) is the cost of the path from the
 * start node to n, and h(n) is a heuristic function that estimates the cost of the cheapest path
 * from n to the goal.
 */

/**
 * A linked list is not a good data structure for the open set. You have to find the node with the
 * smallest F from it, you can either search through the list in O(n) or insert in sorted position
 * in O(n), either way it's O(n). With a heap it's only O(log n). Updating the G score would remain
 * O(n) (since you have to find the node first), unless you also added a HashTable from nodes to
 * indexes in the heap.
 *
 * <p>A linked list is also not a good data structure for the closed set, where you need fast
 * "Contains", which is O(n) in a linked list. You should use a HashSet for that.
 */
public class Astar<S> implements AStarHeuristic {
    @Override
    public double getCostEstimate(Object sourceStep, Object targetStep) {
        return fr.mx.pathfinding.plan.Coords2D.manhattanDistance((fr.mx.pathfinding.plan.Coords2D) sourceStep, (fr.mx.pathfinding.plan.Coords2D) targetStep);
    }

    class NodeComparator implements Comparator<NodeData<S>> {

        /**
         * Compare two Node by the score
         *
         * @param nodeFirst
         * @param nodeSecond
         * @return
         */
        public int compare(NodeData<S> nodeFirst, NodeData<S> nodeSecond) {
            if (nodeFirst.getfScore() > nodeSecond.getfScore()) return 1;
            if (nodeSecond.getfScore() > nodeFirst.getfScore()) return -1;
            return 0;
        }
    }

    // Comparator for comparing doubles with tolerance
    private Comparator<Double> comparator;

    //  protected Set<S> openList;
    protected Set<S> closedList;
    //  private AStarHeuristic<S> heuristic;

    public Astar(Explorable<S> explorable, S startStep, S endStep) {
        //    this.heuristic = heuristic;
        this.comparator = new ToleranceDoubleComparator();

        // The set of currently discovered nodes that are not evaluated yet.
        // Initially, only the start node is known.
        PriorityQueue<NodeData<S>> openSet = new PriorityQueue<>(new NodeComparator());
        Set<NodeData<S>> closedSet = new HashSet<>();

        //Add in the openSet the startStep
        openSet.add(new NodeData<S>(startStep, null, getCostEstimate(startStep, endStep), 0));

        // For each node, which node it can most efficiently be reached from.
        // If a node can be reached from many nodes, cameFrom will eventually contain the
        // most efficient previous step.
        //    Map<S, NodeData<S>> cameFrom = new HashMap<>();

        while (!openSet.isEmpty()) {
            // get the node in openSet having the lowest fScore value
            // openSet is a sorted map on fScore.
            NodeData<S> current = openSet.peek();

            if (current.step.equals(endStep)) {
                System.out.println("done");
                //return path
                return;
            }

            for (S step : explorable.getReachableStepsFrom(startStep)) {
                if (closedSet.contains(step)) {
                    //Ignore the neighboor which is already evaluated;
                    continue;
                }

                // The distance from start to a neighbor
                double gScore = current.getgScore() + getCostEstimate(current.step, step);

                // discover a new step
                if (!openSet.contains(step)) {
                    openSet.add(
                            new NodeData<S>(
                                    step,
                                    current.fromStep,
                                    getCostEstimate(step, endStep),
                                    gScore + 1));
                }
            }
            closedSet.add(current);
        }
        System.out.println("Chemin introuvable");
    }

    /**
     * Little class to encapsulate data about StepInterface that's been traversed.
     *
     * @param <S> the step type
     */
    static class NodeData<S> implements TracableStep<S> {
        private S fromStep;

        private S step;

        public S getStep() {
            return step;
        }

        public void setStep(S step) {
            this.step = step;
        }

        // heuristic of destination
        final double heuristic;

        // gScore is the distance from the source
        private double gScore;

        // fScore = gScore + heuristic
        private double fScore;

        NodeData(S step, S fromStep, double heuristic, double gScore) {
            this.step = step;
            this.fromStep = fromStep;
            this.heuristic = heuristic;
            this.gScore = gScore;

            this.fScore = gScore + heuristic;
        }

        //    public boolean equals(NodeData n) {}

        @Override
        public S getPredecessor() {
            return fromStep;
        }

        public double getgScore() {
            return gScore;
        }

        public void setgScore(double gScore) {
            this.gScore = gScore;
        }

        public double getfScore() {
            return fScore;
        }

        public void setfScore(double fScore) {
            this.fScore = fScore;
        }

        public S getFromStep() {
            return fromStep;
        }

        public void setFromStep(S fromStep) {
            this.fromStep = fromStep;
        }
    }
}
