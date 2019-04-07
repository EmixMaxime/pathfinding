package fr.mx.pathfinding.traverse;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;

public class BreadthFirstIterator<S>
    extends CrossIterator<S, BreadthFirstIterator.SearchNodeData<S>> {

  private Deque<S> queue = new ArrayDeque<>();

  public BreadthFirstIterator(Explorable<S> explorable, S startStep) {
    super(explorable, startStep);
  }

  public BreadthFirstIterator(Explorable<S> explorable, S startStep, S endStep) {
    super(explorable, startStep, endStep);
  }

  public BreadthFirstIterator(
      Explorable<S> explorable, S startStep, Map<S, SearchNodeData<S>> seen, S endStep) {
    super(explorable, startStep, seen, endStep);
  }

  /** {@inheritDoc} */
  protected S nextStep() {
    return queue.removeFirst();
  }

  protected boolean isConnectedComponentExhausted() {
    return queue.isEmpty();
  }

  protected void encounterStep(S step, S fromStep) {
    Objects.requireNonNull(step);
    // when fromStep == null, it's the start step , so I have a depth of 0.
    int depth = (fromStep == null ? 0 : getSeenData(fromStep).depth + 1);

    putSeenData(step, new SearchNodeData<>(fromStep, depth));
    queue.add(step);
  }

  /** {@inheritDoc} */
  protected void encounterStepAgain(S step, S stepFrom) {}

  /**
   * Returns the depth of step in the data structure. The depth of a step is defined as the number
   * of steps traversed on the path from the beginning (first step) to step. The beginning has depth
   * 0. This method can only be invoked on a step once the iterator has visited step!
   *
   * @param step step
   * @return depth of step in the data structure.
   */
  public int getDepth(S step) {
    assert getSeenData(step) != null;
    return getSeenData(step).depth;
  }

  /**
   * Little class to encapsulate data about StepInterface that's been traversed.
   *
   * @param <S> the step type
   */
  static class SearchNodeData<S> implements TracableStep<S> {
    final S step;

    /** Depth of node in search tree */
    final int depth;

    SearchNodeData(S step, int depth) {
      this.step = step;
      this.depth = depth;
    }

    @Override
    public S getPredecessor() {
      return step;
    }
  }
}
