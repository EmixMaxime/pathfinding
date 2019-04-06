package ui.console;

import map.MapBuilder;

public class ConsoleMain {

  public static void main(String[] args) {
    MapBuilder map = new MapBuilder("resources\\map.txt");
    var c = new ConsoleView(map);

    c.show();
  }
}


