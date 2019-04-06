package map;

import org.junit.jupiter.api.Test;
import plan.Coords2D;

import java.nio.file.Paths;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MapBuilderTest {
  @Test
  void readAndCreateMatrix(){
    MapBuilder map = new MapBuilder("resources\\map.txt");
    String expected_name = "Atelier1";
    Path path = Paths.get("resources/map.txt");

    Coords2D expectedStart = new Coords2D(1,20);
    Coords2D expectedEnd = new Coords2D(30, 18);

    assertTrue(expectedStart.equals(map.getMapSearch().getStart()));
    assertTrue(expectedEnd.equals(map.getMapSearch().getGoal()));
    assertEquals(80, map.getMapSearch().getMap().getXSize());
    assertEquals(40, map.getMapSearch().getMap().getYSize());

    assertEquals(path, map.getPath());
  }
}
