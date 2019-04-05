package traverse;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;

public class BreadthFirstIterator<S> implements ExplorableIterator<S> {

  private Deque<S> queue = new ArrayDeque<>();

  /** {@inheritDoc} */
  public S nextStep() {
    return queue.removeFirst();
  }

  public boolean isConnectedComponentExhausted() {
    return queue.isEmpty();
  }

  /**
   * Pb: je peux retourner une Map pour récupérer la valeur dans CrossIterator => pb: CrossIterator
   * a un type générique qui ne va pas forcément correspondre au type d'ici => erreur
   *
   * @param step the step encountered
   * @param fromStep the step via which the step was encountered, or null if the step is a starting
   */
  public void encounterStep(S step, S fromStep) {
    Objects.requireNonNull(step);
    // when fromStep == null, it's the start step , so I have a depth of 0.
    //    int depth = (fromStep == null ? 0 : getSeenData(fromStep).depth + 1);

    //    putSeenData(step, new SearchNodeData<>(fromStep, depth));
    queue.add(step);
  }

  /** {@inheritDoc} */
  public void encounterStepAgain(S step, S stepFrom) {}

  /**
   * Returns the depth of step in the data structure. The depth of a step is defined as the number
   * of steps traversed on the path from the beginning (first step) to step. The beginning has depth
   * 0. This method can only be invoked on a step once the iterator has visited step!
   *
   * @param step step
   * @return depth of step in the data structure.
   */
  //  public int getDepth(S step) {
  //    assert getSeenData(step) != null;
  //    return getSeenData(step).depth;
  //  }

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
