package ui;

import map.MapBuilder;
import map.MapSearch;

public class MapSearchSearchProvider implements MapSearchSupplier {

  @Override
  public MapSearch get(String name) {
    return new MapBuilder(name).getMapSearch();
  }
}
