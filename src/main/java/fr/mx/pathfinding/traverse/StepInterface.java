package fr.mx.pathfinding.traverse;

/**
 *
 * @param <D> data type.
 */
public interface StepInterface<D> {
  /** Mark the step as seen */
//  void mark();

  /**
   * Get if the step had already been seen
   *
   * @return true if the step had already been seen
   */
//  boolean isMarked();

  /**
   * Get the Value of the StepInterface
   *
   * @returnIf value is empty it's the path, else it can be a wall, the start or the end.
   */
  D getData();

  /**
   * Compare 2 steps
   *
   * @param stepCompare
   * @return true if the step are the same
   */
  boolean equals(StepInterface stepCompare);
}
