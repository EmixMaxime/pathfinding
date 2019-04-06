package ui;

import map.MapBuilder;
import map.MapMatrix;
import map.MapSearch;

import java.util.function.Supplier;

public interface MapSearchSupplier {
  MapSearch get(String name);
}
