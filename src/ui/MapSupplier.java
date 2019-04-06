package ui;

import map.MapBuilder;
import map.MapMatrix;

import java.util.function.Supplier;

public interface MapSupplier {
  MapBuilder get(String name);
}
