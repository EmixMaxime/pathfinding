package ui;

import map.MapBuilder;
import map.MapSearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class MapSearchSearchProvider implements MapSearchSupplier {

  @Override
  public MapSearch get(String name) {
    return new MapBuilder(name).getMapSearch();
  }

  @Override
  public List<String> getAvailableMaps() {
    File[] files = new File("resources").listFiles((dir, name) -> name.endsWith(".txt"));

    if (files == null) {
      throw new Error("No map files found");
    }

    List<String> list = new ArrayList<>(files.length);
    for (File file : files) {
      String toString = file.toString();
      list.add(toString);
    }

    return list;
  }
}
