package map;

import traverse.Explorable;

import java.util.ArrayList;
import java.util.Set;

/** @param <S> the step type */
public class MapMatrix<S> implements Explorable<S> {

  private Object[][] matrix;

  public MapMatrix(Object[][] matrix) {
    this.matrix = matrix;
  }

  /** {@inheritDoc} */
  public Set<S> getReachableStepsFrom(S step) {
    return null;
  }
}
