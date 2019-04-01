package map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import traverse.Step2D;
import traverse.StepInterface;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MapMatrixTest {

  @BeforeEach
  void setUp() {
  }

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

    Step2D[][] matrix = new Step2D[xSize][ySize];

    for (int x = 0; x < xSize; x++) {
      for (int y = 0; y < ySize; y++) {
        matrix[x][y] = new Step2D<Integer>(x, y, 1);
      }
    }

    MapMatrix mapMatrix = new MapMatrix(matrix);

    Step2D first = matrix[1][1];
    System.out.println(first.getX() + "," + first.getY());
    Set<StepInterface> reachable = mapMatrix.getReachableStepsFrom(first, new HashSet<>());

    Set<Step2D> expectedReachableSteps = new HashSet<>();
    expectedReachableSteps.add(matrix[0][1]);
    expectedReachableSteps.add(matrix[1][0]);
    expectedReachableSteps.add(matrix[2][1]);
    expectedReachableSteps.add(matrix[1][2]);

    assertEquals(expectedReachableSteps, reachable);

    matrix[0][1] = null;
    matrix[2][1] = null;

    mapMatrix = new MapMatrix(matrix);
    reachable = mapMatrix.getReachableStepsFrom(first, new HashSet<>());

    Set<Step2D> expectedReachableStepsWithoutNull = new HashSet<>();
    expectedReachableStepsWithoutNull.add(matrix[1][0]);
    expectedReachableStepsWithoutNull.add(matrix[1][2]);

    assertEquals(expectedReachableStepsWithoutNull, reachable);

    for (StepInterface st : reachable) {
      Step2D s = (Step2D) st;
      System.out.println("reachable x,y " + s.getX() + "," + s.getY());
    }

  }
}
