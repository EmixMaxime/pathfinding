package fr.mx.pathfinding.map;

import fr.mx.pathfinding.plan.Coords2D;

/**
 * Give start coords, goal coords and the fr.mx.pathfinding.data_structure.map. Use it to find a path using some searching
 * algorithms.
 */
public class MapSearch {

  private final MapMatrix map;
  private Coords2D start;
  private Coords2D goal;

  public MapSearch(MapMatrix map, Coords2D start, Coords2D goal) {
    this.map = map;
    this.start = start;
    this.goal = goal;
  }

  public Coords2D getStart() {
    return start;
  }

  public void setStart(Coords2D start) {
    this.start = start;
  }

  public Coords2D getGoal() {
    return goal;
  }

  public void setGoal(Coords2D goal) {
    this.goal = goal;
  }

  public MapMatrix getMap() {
    return map;
  }
}
