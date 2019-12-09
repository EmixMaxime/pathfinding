package fr.mx.pathfinding;

import fr.mx.pathfinding.map.MapBuilder;
import fr.mx.pathfinding.map.MapSearch;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MapSearchSearchProvider implements MapSearchSupplier {

  @Override
  public MapSearch get(String name) {
    return new MapBuilder(name).getMapSearch();
  }

  @Override
  public List<String> getAvailableMaps() {
    // @TODO FIX THIS ABSOLUTE PATH !!
    Path path = Paths.get(System.getProperty("user.dir") , "src/main/resources/fr/mx/pathfinding");
    File[] files = new File(path.toString()).listFiles((dir, name) -> name.endsWith(".txt"));

    if (files == null) {
      throw new Error("No map files found");
    }

    List<String> list = new ArrayList<>(files.length);
    for (File file : files) {
      String toString = file.getName();
      list.add(toString);
    }

    return list;
  }
}
