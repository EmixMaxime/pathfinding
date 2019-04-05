package traverse;

import java.util.*;

/** @param <S> type of steps. */
public class CrossIterator<S, D> implements Iterator<S> {

  /**
   * Stores the steps that have been seen during iteration and (optionally) some additional
   * traversal info regarding each step. Can be used to store the path if the data contains the
   * predecessor.
   */
  private Map<S, D> seen;

  private S startStep;

  private Explorable<S> explorable;

  private ExplorableIterator<S> explorableIterator;

  /**
   * The default seen data structure is HashMap.
   *
   * @param explorable explorable data structure.
   * @param startStep startStep
   */
  public CrossIterator(ExplorableIterator<S> explorableIterator, Explorable<S> explorable, S startStep) {
    this(explorableIterator, explorable, startStep, new HashMap<>());
  }

  public CrossIterator(ExplorableIterator<S> explorableIterator, Explorable<S> explorable, S startStep, Map<S, D> seen) {
    this.explorable = explorable;
    this.startStep = startStep;
    this.seen = seen;
    this.explorableIterator = explorableIterator;
  }

  /**
   * Determines whether a step has been seen yet by this traversal.
   *
   * @param step step in question
   * @return <tt>true</tt> if step has already been seen
   */
  protected boolean isSeenStep(S step) {
    return seen.containsKey(step);
  }

  public boolean hasNext() {
    if (startStep != null) {
      encounterStartStep();
    }

    if (!explorableIterator.isConnectedComponentExhausted()) {
      return true;
    } else {
      // @TODO strategy for graph with multiple components.
      return false;
    }
  }

  public S next() {
    if (startStep != null) {
      encounterStartStep();
    }

    if (hasNext()) {
      S nextStep = explorableIterator.nextStep();
      addUnseenStepsOf(nextStep);
      return nextStep;
    }

    throw new NoSuchElementException();
  }

  private void encounterStartStep() {
    explorableIterator.encounterStep(startStep, null);
    startStep = null;
  }

  private void addUnseenStepsOf(S fromStep) {
    for (S step : explorable.getReachableStepsFrom(fromStep)) {
      if (isSeenStep(step)) {
        explorableIterator.encounterStepAgain(step, fromStep);
      } else {
        explorableIterator.encounterStep(step, fromStep);
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
  protected D getSeenData(S step) {
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
  protected D putSeenData(S step, D data) {
    return seen.put(step, data);
  }
}
