package fr.mx.pathfinding.traverse;

import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/** Recursive algorithm of iterative with FIFO heap => a stack. */
public class DepthFirstSearch<S, D> extends CrossIterator<S, DepthFirstSearch.SearchNodeData<S>> {

  private Stack<S> stack = new Stack<>();

  public DepthFirstSearch(Explorable<S> explorable, S startStep) {
    super(explorable, startStep);
  }

  public DepthFirstSearch(Explorable<S> explorable, S startStep, S endStep) {
    super(explorable, startStep, endStep);
  }

  public DepthFirstSearch(
    Explorable<S> explorable,
    S startStep,
    Map<S, DepthFirstSearch.SearchNodeData<S>> seen,
    S endStep) {
    super(explorable, startStep, seen, endStep);
  }

  @Override
  protected S nextStep() {
    return stack.pop();
  }

  @Override
  protected boolean isConnectedComponentExhausted() {
    return stack.isEmpty();
  }

  @Override
  protected void encounterStep(S step, S fromStep) {
    Objects.requireNonNull(step);
    // when fromStep == null, it's the start step , so I have a depth of 0.
    int depth = (fromStep == null ? 0 : getSeenData(fromStep).depth + 1);

    putSeenData(step, new DepthFirstSearch.SearchNodeData<>(fromStep, depth));
    stack.add(step);
  }

  @Override
  protected void encounterStepAgain(S step, S stepFrom) {}

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
