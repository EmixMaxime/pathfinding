package traverse;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.NoSuchElementException;

/** @param <D> type of data associated to seen steps. */
public abstract class CrossIterator<D> implements Iterator<StepInterface> {

  /**
   * Stores the steps that have been seen during iteration and (optionally) some additional
   * traversal info regarding each step. Can be used to store the path if the data contains the
   * predecessor.
   */
  private Map<StepInterface, D> seen;

  private StepInterface startStep;
  private StepInterface endStep;
  private boolean foundEndStep = false;

  private Explorable explorable;

  /**
   * The default seen data structure is HashMap.
   *
   * @param explorable explorable data structure.
   * @param startStep startStep
   */
  public CrossIterator(Explorable explorable, StepInterface startStep) {
    this(explorable, startStep, new HashMap<>(), null);
  }

  public CrossIterator(Explorable explorable, StepInterface startStep, StepInterface endStep) {
    this(explorable, startStep, new HashMap<>(), endStep);
  }

  public CrossIterator(
      Explorable explorable,
      StepInterface startStep,
      Map<StepInterface, D> seen,
      StepInterface endStep) {
    this.explorable = explorable;
    this.startStep = startStep;
    this.endStep = endStep;
    this.seen = seen;
  }

  /**
   * Depends on the Iterator, have to be defined.
   *
   * @return the next step to be iterated.
   */
  protected abstract StepInterface nextStep();

  /**
   * Determines whether a step has been seen yet by this traversal.
   *
   * @param step step in question
   * @return <tt>true</tt> if step has already been seen
   */
  protected boolean isSeenStep(StepInterface step) {
    return seen.containsKey(step);
  }

  public boolean hasNext() {
    if (foundEndStep) {
      return false;
    }

    if (startStep != null) {
      encounterStartStep();
    }

    if (!isConnectedComponentExhausted()) {
      return true;
    } else {
      // @TODO strategy for graph with multiple components.
      return false;
    }
  }

  private boolean isEndStep(StepInterface step) {
    return endStep != null && step.equals(endStep);
  }

  public StepInterface next() {
    if (startStep != null) {
      encounterStartStep();
    }

    if (hasNext()) {
      StepInterface nextStep = nextStep();

      if (!isEndStep(nextStep)) {
        addUnseenStepsOf(nextStep);
      } else {
        foundEndStep = true;
      }

      return nextStep;
    }

    throw new NoSuchElementException();
  }

  private void encounterStartStep() {
    encounterStep(startStep, null);
    startStep = null;
  }

  /**
   * For graph data structure, could have multiple components (see "linked graph).
   *
   * @return <tt>true</tt> if there are no more un-iterated steps in the currently iterated
   *     connected component.
   */
  protected abstract boolean isConnectedComponentExhausted();

  /**
   * Update data structures the first time we see a step.
   *
   * @param step the step encountered
   * @param fromStep the step via which the step was encountered, or null if the step is a starting
   *     point
   */
  protected abstract void encounterStep(StepInterface step, StepInterface fromStep);

  /**
   * Called whenever we re-encounter a step. The default implementation does nothing. Could be used
   * to optimize the path.s
   *
   * @param step the step re-encountered
   * @param stepFrom the step via which the step was re-encountered
   */
  protected abstract void encounterStepAgain(StepInterface step, StepInterface stepFrom);

  private void addUnseenStepsOf(StepInterface fromStep) {
    for (StepInterface step : explorable.getReachableStepsFrom(fromStep)) {
      if (isSeenStep(step)) {
        encounterStepAgain(step, fromStep);
      } else {
        encounterStep(step, fromStep);
      }
    }
  }

  /**
   * Access the data stored for a seen step.
   *
   * @param step a step which has already been seen.
   * @return data associated with the seen step or <code>null</code> if no data was associated with
   *     the step.
   */
  protected D getSeenData(StepInterface step) {
    return seen.get(step);
  }

  /**
   * Stores iterator-dependent data for a step that has been seen.
   *
   * @param step a step which has been seen.
   * @param data data to be associated with the seen step.
   * @return previous value associated with specified step or <code>
   * null</code> if no data was associated with the step. A <code>null</code> return can also
   *     indicate that the step was explicitly associated with <code>null</code>.
   */
  protected D putSeenData(StepInterface step, D data) {
    return seen.put(step, data);
  }
}
