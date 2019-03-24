package traverse;

import java.util.ArrayDeque;
import java.util.Deque;

/** @param <S> the step type */
public class BreadthFirstIterator<S>
    extends CrossIterator<S, BreadthFirstIterator.SearchNodeData<S>> {

  private Deque<S> queue = new ArrayDeque<>();

  /**
   * Creates a new breadth-first iterator for the specified Explorable.Iteration will start at the
   * specified start step.
   *
   * @param explorable the data structure to be iterated.
   * @param startStep the step iteration to be started.
   */
  public BreadthFirstIterator(Explorable<S> explorable, S startStep) {
    super(explorable, startStep);
  }

  /** {@inheritDoc} */
  //  @Override
  protected S nextStep() {
    return queue.removeFirst();
  }

  protected boolean isConnectedComponentExhausted() {
    return queue.isEmpty();
  }

  protected void encounterStep(S step, S fromStep) {
    int depth = (fromStep == null ? 0 : getSeenData(step).depth + 1);

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
   * Little class to encapsulate data about Step that's been traversed.
   *
   * @param <S> the step type
   */
  static class SearchNodeData<S> {
    final S step;

    /** Depth of node in search tree */
    final int depth;

    SearchNodeData(S step, int depth) {
      this.step = step;
      this.depth = depth;
    }
  }
}
