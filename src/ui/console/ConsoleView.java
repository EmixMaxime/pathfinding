package ui.console;

import map.MapBuilder;
import map.MapMatrix;
import plan.Step2D;
import traverse.BreadthFirstIterator;

public class ConsoleView {

  private MapBuilder mapMatrix;

  public ConsoleView(MapBuilder mapMatrix) {
    this.mapMatrix = mapMatrix;
  }

  public void show() {
    var map = mapMatrix.getMatrix();

    // add entrance/exit points
    var info = mapMatrix.getMapInformation();
    map[info.getEndX()][info.getEndY()] = 'B';

    map[info.getStartX()][info.getStartY()] = 'A';

    for (char[] chars : map) {
      for (int j = 0; j < map[0].length; ++j) {
        System.out.print((chars[j]));
      }
      System.out.println();
    }

    System.out.println(mapMatrix.getMapMatrix().toString());

    var matrix = mapMatrix.getMapMatrix();
    Step2D<MapMatrix.Values> start = matrix.getElement(info.getStartX(), info.getStartY());
    Step2D<MapMatrix.Values> end = matrix.getElement(info.getEndX(), info.getEndY());

    var it = new BreadthFirstIterator<>(matrix, start, end);
    while(it.hasNext()) {
//      System.out.println(it.next());
      it.next();
    }

    var path = it.path();
    for (var current : path) {
      map[current.getX()][current.getY()] = 'x';
    }

    for (char[] chars : map) {
      for (int j = 0; j < map[0].length; ++j) {
        System.out.print((chars[j]));
      }
      System.out.println();
    }
  }
}
