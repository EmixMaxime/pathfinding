package path_finding;

import traverse.CrossIterator;

/**
 *
 * @param <S> step type.
 */
public class PathFinding<S> {

  private CrossIterator iterator;
  private S endStep;

  public PathFinding(CrossIterator it, S endStep) {
    iterator = it;
    this.endStep = endStep;
  }

  private void findPath() {
    boolean findTheEnd = false;
    S currentStep;

    while (iterator.hasNext() && !findTheEnd) {
//      currentStep = iterator.next();
//      if (currentStep.equals(endStep)) {
//        findTheEnd = true;
//      } else {
//        // Mark the path
//      }
    }
  }
}
