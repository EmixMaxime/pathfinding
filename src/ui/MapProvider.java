package ui;

import map.MapBuilder;

public class MapProvider implements MapSupplier {

  @Override
  public MapBuilder get(String name) {
    return new MapBuilder(name);
  }
}
