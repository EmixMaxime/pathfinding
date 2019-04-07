package fr.mx.pathfinding.ui.console;

import fr.mx.pathfinding.map.MapBuilder;
import fr.mx.pathfinding.util.Clavier;

public class ConsoleMain {

  public static void main(String[] args) {
    //MapBuilder map = new MapBuilder("/map.txt");
    //var c = new ConsoleView(map);
    var c = new ConsoleView();
    c.menu();
    String chose;
    do {
      // See the class "Clavier"
      chose = Clavier.lireString();
      c.run(chose);
    }while(chose.intern() != "q");
  }
}


