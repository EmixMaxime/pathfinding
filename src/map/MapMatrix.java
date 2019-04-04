package map;

import traverse.Explorable;
import plan.Step2D;

import java.util.HashSet;
import java.util.Set;

public class MapMatrix implements Explorable<Step2D<MapMatrix.Values>> {

  private Step2D<Values>[][] matrix;
  private final boolean allowsDiagonals;

  public enum Values {
    ROAD,
    OBSTACLE
  }

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

  private boolean isObstacle(Step2D step) {
    var data = step.getData();
    return data == Values.OBSTACLE;
  }

  public boolean isWalkable(int x, int y) {
    if ((x >= 0 && x < xSize) && (y >= 0 && y < ySize)) {
      // Get the step & test it.
      Step2D step = matrix[x][y];
      return isObstacle(step);
    }

    // Out of the map!
    return false;
  }

  @Override
  public Set<Step2D<Values>> getReachableStepsFrom(Step2D<Values> step) {
    return getReachableStepsFrom(step, new HashSet<>());
  }

  public Set<Step2D<Values>> getReachableStepsFrom(
      Step2D<Values> step2D, Set<Step2D<Values>> reachable) {

    if (allowsDiagonals) {
      // @TODO
    }

    // top
    if (step2D.getY() + 1 < ySize) {
      var step = matrix[step2D.getX()][step2D.getY() + 1];
      if (!isObstacle(step)) {
        reachable.add(step);
      }
    }
    // right
    if (step2D.getX() + 1 < xSize) {
      var step = matrix[step2D.getX() + 1][step2D.getY()];
      if (!isObstacle(step)) {
        reachable.add(step);
      }
    }
    // bottom
    if (step2D.getY() - 1 >= 0) {
      var step = matrix[step2D.getX()][step2D.getY() - 1];
      if (!isObstacle(step)) {
        reachable.add(step);

      }
    }
    // left
    if (step2D.getX() - 1 >= 0) {
      var step = matrix[step2D.getX() - 1][step2D.getY()];
      if (!isObstacle(step)) {
        reachable.add(step);
      }
    }

    return reachable;
  }

}
