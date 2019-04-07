package fr.mx.pathfinding.traverse;

import java.util.Set;

/**
 * Make any data structure Explorable.
 * @param <S> type of data that could be traversed.
 */

public interface Explorable<S> {

  Set<S> getReachableStepsFrom(S step);

  /**
   * Returns a set of all steps from the specified step.
   *
   * @param step the step for which the list of steps to be returned.
   * @param reachable Set of reachable steps.
   *
   * @return a set of all reachable steps from the specified step.
   * @throws IllegalArgumentException if step is not found in the data structure.
   * @throws NullPointerException if step is <code>null</code>.
   */

  Set<S> getReachableStepsFrom(S step, Set<S> reachable);
}
