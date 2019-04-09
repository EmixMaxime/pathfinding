package fr.mx.pathfinding.traverse.astar;

/**
 * Interface for an admissible heuristic used in A* search.
 *
 * @param <S> step type
 */
public interface AStarHeuristic<S> {

  /**
   * An admissible "heuristic estimate" of the distance from the sourceStep, to the goal (usually
   * denoted $h(x)$). This is the good guess function which must never overestimate the distance.
   *
   * @param sourceStep the source step
   * @param targetStep the target step
   * @return an estimate of the distance from the source to the target vertex
   */
  double getCostEstimate(S sourceStep, S targetStep);
}
