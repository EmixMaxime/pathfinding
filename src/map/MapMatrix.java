package map;

import traverse.Explorable;
import traverse.Step2D;
import traverse.StepInterface;

import java.util.HashSet;
import java.util.Set;

public class MapMatrix implements Explorable {

  private Step2D[][] matrix;
  private final boolean allowsDiagonals;

  /** Bounds of matrix. */
  private int xSize;

  private int ySize;

  public MapMatrix(Step2D[][] matrix) {
    this(matrix, false);
  }

  public int getXSize() {
    return xSize;
  }

  public int getYSize() {
    return ySize;
  }

  public MapMatrix(Step2D[][] matrix, boolean allowsDiagonals) {
    this.allowsDiagonals = allowsDiagonals;
    this.matrix = matrix;
    xSize = matrix.length;
    ySize = matrix[0].length;
  }

  //  public MapMatrix(int xSize, int ySize) {
  //    Object[] x = new Object[xSize];
  //    Object[] y = new Object[ySize];
  //  }

  public Set<StepInterface> getReachableStepsFrom(StepInterface step) {
    Set<StepInterface> reachable = new HashSet<>();
    return getReachableStepsFrom(step, reachable);
  }

  public Set<StepInterface> getReachableStepsFrom(
      StepInterface step, Set<StepInterface> reachable) {
    return getReachableStepsFrom((Step2D) step, reachable);
  }

  private Set<StepInterface> getReachableStepsFrom(Step2D step2D, Set<StepInterface> reachable) {

    if (allowsDiagonals) {
      // @TODO
    }

    // top
    if (step2D.getY() + 1 < ySize) {
      reachable.add(matrix[step2D.getX()][step2D.getY() + 1]);
    }
    // right
    if (step2D.getX() + 1 < xSize) {
      reachable.add(matrix[step2D.getX() + 1][step2D.getY()]);
    }
    // bottom
    if (step2D.getY() - 1 >= 0) {
      reachable.add(matrix[step2D.getX()][step2D.getY() - 1]);
    }
    // left
    if (step2D.getX() - 1 >= 0) {
      reachable.add(matrix[step2D.getX() - 1][step2D.getY()]);
    }

    // Remove obstacles
    reachable.remove(null);

    return reachable;
  }
}
