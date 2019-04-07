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

  private MapSearch mapSearch;
  private MapBuilder mapBuilder;
  private String mapName;
  private String algorithmName;

  //ConsoleView(MapBuilder mapBuilder) {
  ConsoleView() {
    //this.mapSearch = mapBuilder.getMapSearch();
    //this.mapBuilder = mapBuilder;
    mapName = "";
    algorithmName = "";
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

    var path = it.path();
    for (var current : path) {
      System.out.println("x,y of path " + current.getX() + " " + current.getY());
      map[current.getX()][current.getY()] = 'x';
    }

    System.out.println(mapBuilder.matrixToString());
  }

  void menu() {
    System.out.println("a - chose an algorithm\n" +
      "m - chose a map\n" +
      "l - launch\n" +
      "h - display the menu\n" +
      "q - quit\n" +
      "> ");
  }

  void choseAlgorithm() {

    System.out.println("0 - Depth\n" +
      "1 - Length\n" +
      "2 - AStar\n" +
      "3 - Other");

    int chose = -1;
    do {
      chose = Clavier.lireInt();
      if (chose == 0)
        algorithmName = "Depth";
      else if (chose == 1)
        algorithmName = "Length";
      else if (chose == 2)
        algorithmName = "AStar";
      else if (chose == 3)
        algorithmName = "Other";
      else
        chose = -1;


    } while (chose == -1);
  }

  void choseMap() {

    Path rootFolder = Paths.get(System.getProperty("user.dir"), "src/main/resources/fr/mx/pathfinding");
    File folder = new File(rootFolder.toString());
    List<String> listMap = new ArrayList<>();
    for (File file : folder.listFiles()) {
      if (file.getName().contains(".txt")) {
        listMap.add(file.getName());
        System.out.println(listMap.size()-1 + " - " + file.getName());
      }
    }

    int chose = -1;
    do {
      chose = Clavier.lireInt();
      if (chose <= listMap.size())
        mapName = listMap.get(chose);
      else if (chose < 0)
        chose = -1;


    } while (chose == -1);
  }

  void run(String chose) {

    // Prevent from user stupidity
    chose = chose.trim().intern();
    if (chose == "a" || chose == "A") {
      choseAlgorithm();
    } else if (chose == "m" || chose == "M") {
      choseMap();
    } else if (chose == "h" || chose == "H") {
      menu();
    } else if (chose == "l" || chose == "L") {
      if (!mapName.isEmpty() && !algorithmName.isEmpty()) {
        this.mapBuilder = new MapBuilder("/" + mapName);
        this.mapSearch = mapBuilder.getMapSearch();

        show();
      } else {
        System.out.println("please chose an Algorithm and a map first >");
      }
    }
    if(chose != "q") {
      menu();
    }
  }
}
