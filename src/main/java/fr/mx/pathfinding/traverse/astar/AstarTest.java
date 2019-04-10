package fr.mx.pathfinding.traverse.astar;

import fr.mx.pathfinding.MapSearchSearchProvider;
import fr.mx.pathfinding.MapSearchSupplier;
import fr.mx.pathfinding.map.MapMatrix;
import fr.mx.pathfinding.map.MapSearch;
import fr.mx.pathfinding.plan.Step2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AstarTest {

  @Test
  void yolo() {
    MapSearchSupplier provider = new MapSearchSearchProvider();

    MapSearch mapSearch = provider.get("/map2.txt");

    var start = mapSearch.getStart();
    var end = mapSearch.getGoal();
    var map = mapSearch.getMap();


    AStarHeuristic heuristic = new AStarEuclidianHeuristic();
    Astar astar = new Astar<>(heuristic, map, map.getElement(start), map.getElement(end));

    AStarHeuristic heuristic2 = new AStarManhattanHeuristic();
    Astar astar2 = new Astar<>(heuristic2, map, map.getElement(start), map.getElement(end));

    var s = astar.path();
    System.out.println(s.size());

    for (var i : s) {
      System.out.println(i);
    }

  }

}
