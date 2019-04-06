package ui.console;

import map.MapBuilder;

public class ConsoleMain {

  public static void main(String[] args) {
    MapBuilder map = new MapBuilder("map3");
    var c = new ConsoleView(map);

    c.show();
  }
}


