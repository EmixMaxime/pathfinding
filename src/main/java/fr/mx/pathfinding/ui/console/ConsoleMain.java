package fr.mx.pathfinding.ui.console;

import fr.mx.pathfinding.map.MapBuilder;

public class ConsoleMain {

  public static void main(String[] args) {
    MapBuilder map = new MapBuilder("resources\\map3.txt");
    var c = new ConsoleView(map);

    c.show();
  }
}


