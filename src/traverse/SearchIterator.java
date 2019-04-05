package traverse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Using CrossIterator to iterate over an Explorable data structure, but here I'm searching a Node.
 *
 * @param <S>
 * @param <D>
 */
public final class SearchIterator<S, D> {
  private S endStep;
  private boolean isEndFound = false;
  private CrossIterator<S, D> iterator;

  public SearchIterator(S endStep, CrossIterator<S, D> iterator) {
    this.endStep = endStep;
    this.iterator = iterator;
  }

  private boolean isEndStep(S step) {
    return step.equals(endStep);
  }

  public boolean hasNext() {
    return !isEndFound && iterator.hasNext();
  }

  public S next() {
    S nextStep = iterator.next();
    if (isEndStep(nextStep)) {
      isEndFound = true;
    }

    return nextStep;
  }

//  public List<S> path() {
//    if (endStep == null) {
//      return null;
//    }
//
//    ArrayList<S> path = new ArrayList<>();
//    TracableStep<S> endStepData = getSeenData(endStep);
//
//    S predecessor = endStepData.getPredecessor();
//
//    while (predecessor != null) {
//      path.add(predecessor);
//      predecessor = getSeenData(predecessor).getPredecessor();
//    }
//
//    return path;
//  }
}
