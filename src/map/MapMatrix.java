package map;

import traverse.Explorable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/** @param <S> the step type */
public class MapMatrix<S> implements Explorable<S> {

  private Object[][] matrix;
  private final boolean allowsDiagonals;

  /**
   * Bounds of matrix.
   */
  private int xSize;
  private int ySize;

  public MapMatrix(Object[][] matrix) {
    this(matrix, false);
  }

  public MapMatrix(Object[][] matrix, boolean allowsDiagonals) {
    this.allowsDiagonals = allowsDiagonals;
  }

  private S goTo() {

    return null;
  }

  /** {@inheritDoc} */
  public Set<S> getReachableStepsFrom(S step) {
    Set<S> reachable = new HashSet<S>();

    if (allowsDiagonals) {

    }

    return reachable;
  }
}
