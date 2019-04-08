package fr.mx.pathfinding.map;

import fr.mx.pathfinding.plan.Coords2D;
import fr.mx.pathfinding.traverse.Explorable;
import fr.mx.pathfinding.plan.Step2D;

import java.util.HashSet;
import java.util.Set;

public class MapMatrix implements Explorable<Step2D<MapMatrix.Values>> {

  private Step2D<Values>[][] matrix;
  private final boolean allowsDiagonals;

  public enum Values {
    ROAD,
    OBSTACLE
  }

  public enum Directions {
    TOP,
    RIGHT,
    BOTTOM,
    LEFT
  }

  /** Bounds of matrix. */
  private int xSize;

  private int ySize;

  public MapMatrix(Step2D<Values>[][] matrix) {
    this(matrix, false);
  }

  public int getXSize() {
    return xSize;
  }

  public int getYSize() {
    return ySize;
  }

  public MapMatrix(Step2D<Values>[][] matrix, boolean allowsDiagonals) {
    this.allowsDiagonals = allowsDiagonals;
    this.matrix = matrix;
    xSize = matrix.length;
    ySize = matrix[0].length;
  }

  private boolean isObstacle(Step2D step) {
    var data = step.getData();
    return data == Values.OBSTACLE;
  }

  /**
   * This method is specific to Matrix data structure.
   *
   * @param from
   * @param direction
   * @return
   */
  private Step2D<Values> isWalkable(Step2D<Values> from, Directions direction) {
    int x = from.getX(), y = from.getY();

    switch (direction) {
      case TOP:
        y = y + 1;
        break;
      case BOTTOM:
        y = y - 1;
        break;
      case LEFT:
        x = x - 1;
        break;
      case RIGHT:
        x = x + 1;
        break;
    }

    return isWalkable(x, y);
  }

  /**
   * This method is specific to the matrix.
   *
   * @param x
   * @param y
   * @return the step if walkable or null.
   */
  public Step2D<Values> isWalkable(int x, int y) {
    if ((x >= 0 && x < xSize) && (y >= 0 && y < ySize)) {
      // Get the step & test it.
      var step = matrix[x][y];
      return !isObstacle(step) ? step : null;
    }

    // Out of the fr.mx.pathfinding.data_structure.map!
    return null;
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

    for (Directions direction : Directions.values()) {
      var targetStep = isWalkable(step2D, direction);

      if (targetStep != null) {
        reachable.add((targetStep));
      }
    }

    return reachable;
  }

  @Override
  public String toString() {
    StringBuilder str = new StringBuilder();

    for (int y = 0; y < matrix[0].length; y++) {
      for (int x = 0; x < matrix.length; x++) {
        str.append(matrix[x][y]).append(" ");
      }
      str.append("\n");
    }

    return str.toString();
  }

  public Step2D<Values> getElement(int x, int y) {
    return matrix[x][y];
  }

  public Step2D<Values> getElement(Coords2D c) {
    return getElement((int) c.getX(), (int) c.getY());
  }
}
