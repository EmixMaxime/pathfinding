package map;

import org.junit.jupiter.api.Test;

import java.nio.file.Paths;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class MapBuilderTest {
  @Test
  void readAndCreateMatrix(){
    MapBuilder map = new MapBuilder("map");
    int expected_startX = 20;
    int expected_startY = 1;
    int expected_endX = 18;
    int expected_endY = 30;
    String expected_name = "Atelier1";
    Path path = Paths.get("resources/map.txt");

    assertEquals(expected_startX, map.getMapInformation().getStartX());
    assertEquals(expected_startY, map.getMapInformation().getStartY());
    assertEquals(expected_endX, map.getMapInformation().getEndX());
    assertEquals(expected_endY, map.getMapInformation().getEndY());
    assertEquals(expected_name, map.getMapInformation().getTitle());
    assertEquals(path, map.getPath());


    for (int i = 0; i < map.getMatrix().length; ++i){
      for (int j = 0; j < map.getMatrix()[0].length; ++j){
        System.out.print((map.getMatrix()[i][j]));
      }
      System.out.println();
    }
  }
}
