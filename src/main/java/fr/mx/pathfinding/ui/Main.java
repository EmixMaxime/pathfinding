package fr.mx.pathfinding.ui;

import fr.mx.pathfinding.map.MapBuilder;
import fr.mx.pathfinding.map.MapSearch;
import fr.mx.pathfinding.traverse.BreadthFirstIterator;

public class Main {
  public static void main(String[] args) {
    MapSearch mapSearch = new MapBuilder("resources\\map3.txt").getMapSearch();

    var start = mapSearch.getStart();
    var end = mapSearch.getGoal();

    var map = mapSearch.getMap();
    var s = map.getElement(start);
    var g = map.getElement(end);

    var it = new BreadthFirstIterator<>(map, s, g);
    while (it.hasNext()) {
      System.out.println(it.next());
    }
  }
}
