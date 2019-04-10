package fr.mx.pathfinding.ui.console;

import fr.mx.pathfinding.map.MapBuilder;
import fr.mx.pathfinding.map.MapSearch;
import fr.mx.pathfinding.util.Clavier;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ConsoleControler {
  private MapSearch mapSearch;
  private MapBuilder mapBuilder;
  private String mapName;
  private String algorithmName;

  public MapSearch getMapSearch(){return mapSearch;}
  public void setMapSearch(){mapSearch = mapBuilder.getMapSearch();}
  public MapBuilder getMapBuilder(){return mapBuilder;}
  public void setMapBuilder(String mapName){mapBuilder = new MapBuilder("/" + mapName);;}
  public String getMapName(){return mapName;}
  public String getAlgorithmName(){return algorithmName;}

  public ConsoleControler(){
    mapName = "";
    algorithmName = "";
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
}
