package map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import plan.Step2D;
import traverse.StepInterface;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MapMatrixTest {

  @BeforeEach
  void setUp() {}

  @Test
  void testXSizeYSize() {
    int xSize = 5;
    int ySize = 5;

    Step2D[][] matrix = new Step2D[xSize][ySize];
    MapMatrix mapMatrix = new MapMatrix(matrix);

    assertEquals(mapMatrix.getXSize(), xSize);
    assertEquals(mapMatrix.getYSize(), ySize);
  }

  @Test
  void getReachableStepsFrom() {
    int xSize = 5;
    int ySize = 5;

    Step2D<MapMatrix.Values>[][] matrix = new Step2D[xSize][ySize];

    for (int x = 0; x < xSize; x++) {
      for (int y = 0; y < ySize; y++) {
        matrix[x][y] = new Step2D<>(x, y, MapMatrix.Values.ROAD);
      }
    }

    MapMatrix mapMatrix = new MapMatrix(matrix);

    Step2D<MapMatrix.Values> first = matrix[1][1];

    //    System.out.println(first.getX() + "," + first.getY());
    var reachable = mapMatrix.getReachableStepsFrom(first, new HashSet<>());

    var expectedReachableSteps = new HashSet<>();
    expectedReachableSteps.add(matrix[0][1]);
    expectedReachableSteps.add(matrix[1][0]);
    expectedReachableSteps.add(matrix[2][1]);
    expectedReachableSteps.add(matrix[1][2]);

    assertEquals(expectedReachableSteps, reachable);

    matrix[0][1] = new Step2D<>(0, 1, MapMatrix.Values.OBSTACLE);
    matrix[2][1] = new Step2D<>(2, 1, MapMatrix.Values.OBSTACLE);

    mapMatrix = new MapMatrix(matrix);
    reachable = mapMatrix.getReachableStepsFrom(first, new HashSet<>());

    Set<Step2D> expectedReachableStepsWithoutNull = new HashSet<>();
    expectedReachableStepsWithoutNull.add(matrix[1][0]);
    expectedReachableStepsWithoutNull.add(matrix[1][2]);

    assertEquals(expectedReachableStepsWithoutNull, reachable);

    for (Object st : reachable) {
      Step2D<MapMatrix.Values> s = (Step2D<MapMatrix.Values>) st;
      System.out.println("reachable x,y " + s.getX() + "," + s.getY());
    }
  }
}
