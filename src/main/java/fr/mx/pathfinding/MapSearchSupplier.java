package fr.mx.pathfinding;

import fr.mx.pathfinding.map.MapSearch;

import java.util.List;

public interface MapSearchSupplier {
  MapSearch get(String name);

  List<String> getAvailableMaps();
}
