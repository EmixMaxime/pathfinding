package traverse;

public interface ExplorableIterator<S> {

  /**
   * Depends on the Iterator, have to be defined.
   *
   * @return the next step to be iterated.
   */
  S nextStep();

  /**
   * For graph data structure, could have multiple components (see "linked graph).
   *
   * @return <tt>true</tt> if there are no more un-iterated steps in the currently iterated
   *     connected component.
   */
  boolean isConnectedComponentExhausted();

  /**
   * Update data structures the first time we see a step.
   *
   * @param step the step encountered
   * @param fromStep the step via which the step was encountered, or null if the step is a starting
   *     point
   */
  void encounterStep(S step, S fromStep);

  /**
   * Called whenever we re-encounter a step. The default implementation does nothing. Could be used
   * to optimize the path.s
   *
   * @param step the step re-encountered
   * @param stepFrom the step via which the step was re-encountered
   */
  void encounterStepAgain(S step, S stepFrom);
}
