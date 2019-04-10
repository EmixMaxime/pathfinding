package fr.mx.pathfinding.ui.console;

import fr.mx.pathfinding.map.MapBuilder;
import fr.mx.pathfinding.map.MapSearch;
import fr.mx.pathfinding.traverse.BreadthFirstIterator;
import fr.mx.pathfinding.util.Clavier;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

class ConsoleView {

  private ConsoleControler consoleControler;

  ConsoleView() {
    consoleControler = new ConsoleControler();
    consoleControler.menu();
  }

  void show() {
    var map = consoleControler.getMapBuilder().getRawMatrix();

    // add entrance/exit points
    var start = consoleControler.getMapSearch().getStart();
    var goal = consoleControler.getMapSearch().getGoal();

    map[(int) start.getX()][(int) start.getY()] = 'A';

    System.out.println(goal);
    map[(int) goal.getX()][(int) goal.getY()] = 'B';

    System.out.println(consoleControler.getMapBuilder().getMapSearch().getMap().getXSize());

    System.out.println(consoleControler.getMapBuilder().matrixToString());

    var s = consoleControler.getMapSearch().getMap().getElement(consoleControler.getMapSearch().getStart());
    var g = consoleControler.getMapSearch().getMap().getElement(consoleControler.getMapSearch().getGoal());

    var it = new BreadthFirstIterator<>(consoleControler.getMapSearch().getMap(), s, g);

    while (it.hasNext()) {
      System.out.println(it.next());
    }

    var path = it.path();
    for (var current : path) {
      System.out.println("x,y of path " + current.getX() + " " + current.getY());
      map[current.getX()][current.getY()] = 'x';
    }

    System.out.println(consoleControler.getMapBuilder().matrixToString());
  }

  void run(String chose) {

    // Prevent from user stupidity
    chose = chose.trim().intern();
    if (chose == "a" || chose == "A") {
      consoleControler.choseAlgorithm();
    } else if (chose == "m" || chose == "M") {
      consoleControler.choseMap();
    } else if (chose == "h" || chose == "H") {

    } else if (chose == "l" || chose == "L") {
      if (!consoleControler.getMapName().isEmpty() && !consoleControler.getAlgorithmName().isEmpty()) {
        consoleControler.setMapBuilder(consoleControler.getMapName());
        consoleControler.setMapSearch();

        show();
      } else {
        System.out.println("please chose an Algorithm and a map first >");
      }
    }
    if(chose != "q") {
      consoleControler.menu();
    }
  }
}
