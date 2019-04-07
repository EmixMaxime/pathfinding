package fr.mx.pathfinding.ui.console;

import fr.mx.pathfinding.map.MapBuilder;
import fr.mx.pathfinding.map.MapSearch;
import fr.mx.pathfinding.traverse.BreadthFirstIterator;

class ConsoleView {

  private MapSearch mapSearch;
  private MapBuilder mapBuilder;

  ConsoleView(MapBuilder mapBuilder) {
    this.mapSearch = mapBuilder.getMapSearch();
    this.mapBuilder = mapBuilder;
  }

  void show() {
    var map = mapBuilder.getRawMatrix();

    // add entrance/exit points
    var start = mapSearch.getStart();
    var goal = mapSearch.getGoal();

    map[(int) start.getX()][(int) start.getY()] = 'A';

    System.out.println(goal);
    map[(int) goal.getX()][(int) goal.getY()] = 'B';

    System.out.println(mapBuilder.getMapSearch().getMap().getXSize());

    System.out.println(mapBuilder.matrixToString());

    var s = mapSearch.getMap().getElement(mapSearch.getStart());
    var g = mapSearch.getMap().getElement(mapSearch.getGoal());

    var it = new BreadthFirstIterator<>(mapSearch.getMap(), s, g);

    while (it.hasNext()) {
      System.out.println(it.next());
    }

//    var path = it.path();
//    for (var current : path) {
//      System.out.println("x,y of path " + current.getX() + " " + current.getY());
//      fr.mx.pathfinding.data_structure.map[current.getX()][current.getY()] = 'x';
//    }

//    System.out.println(mapBuilder.matrixToString());
  }
}
