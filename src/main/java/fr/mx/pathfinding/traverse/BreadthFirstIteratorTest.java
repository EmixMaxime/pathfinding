package fr.mx.pathfinding.traverse;

import fr.mx.pathfinding.map.MapMatrix;
import org.junit.jupiter.api.Test;
import fr.mx.pathfinding.plan.Step2D;

import static org.junit.jupiter.api.Assertions.*;

class BreadthFirstIteratorTest {

  @Test
  void yolo() {
    int xSize = 5;
    int ySize = 5;

    Step2D<MapMatrix.Values>[][] matrix = new Step2D[xSize][ySize];

    for (int x = 0; x < xSize; x++) {
      for (int y = 0; y < ySize; y++) {
        matrix[x][y] = new Step2D<>(x, y, MapMatrix.Values.ROAD);
      }
    }

    MapMatrix mapMatrix = new MapMatrix(matrix);

    BreadthFirstIterator it = new BreadthFirstIterator<>(mapMatrix, matrix[0][0], matrix[3][3]);
    assertTrue(it.hasNext());

    while (it.hasNext()) {
      System.out.println(it.next());
    }

    System.out.println("path:");
    var steps = it.path();
    for (var s : steps) {
      System.out.println(s);
    }
  }

  @Test
  void testStepByStep() {
    int xSize = 5;
    int ySize = 5;

    Step2D<MapMatrix.Values>[][] matrix = new Step2D[xSize][ySize];

    matrix[0][0] = new Step2D<>(0, 0, MapMatrix.Values.ROAD);
    matrix[0][1] = new Step2D<>(0, 1, MapMatrix.Values.ROAD);
    matrix[0][2] = new Step2D<>(0, 2, MapMatrix.Values.ROAD);
    matrix[0][3] = new Step2D<>(0, 3, MapMatrix.Values.ROAD);
    matrix[0][4] = new Step2D<>(0, 4, MapMatrix.Values.OBSTACLE);

    matrix[1][0] = new Step2D<>(1, 0, MapMatrix.Values.ROAD);
    matrix[1][1] = new Step2D<>(1, 1, MapMatrix.Values.ROAD);
    matrix[1][2] = new Step2D<>(1, 2, MapMatrix.Values.ROAD);
    matrix[1][3] = new Step2D<>(1, 3, MapMatrix.Values.ROAD);
    matrix[1][4] = new Step2D<>(1, 4, MapMatrix.Values.OBSTACLE);

    matrix[2][0] = new Step2D<>(2, 0, MapMatrix.Values.ROAD);
    matrix[2][1] = new Step2D<>(2, 1, MapMatrix.Values.OBSTACLE);
    matrix[2][2] = new Step2D<>(2, 2, MapMatrix.Values.OBSTACLE);
    matrix[2][3] = new Step2D<>(2, 3, MapMatrix.Values.ROAD);
    matrix[2][4] = new Step2D<>(2, 4, MapMatrix.Values.ROAD);

    matrix[3][0] = new Step2D<>(3, 0, MapMatrix.Values.ROAD);
    matrix[3][1] = new Step2D<>(3, 1, MapMatrix.Values.ROAD);
    matrix[3][2] = new Step2D<>(3, 2, MapMatrix.Values.ROAD);
    matrix[3][3] = new Step2D<>(3, 3, MapMatrix.Values.ROAD);
    matrix[3][4] = new Step2D<>(3, 4, MapMatrix.Values.OBSTACLE);

    matrix[4][0] = new Step2D<>(4, 0, MapMatrix.Values.ROAD);
    matrix[4][1] = new Step2D<>(4, 1, MapMatrix.Values.OBSTACLE);
    matrix[4][2] = new Step2D<>(4, 2, MapMatrix.Values.OBSTACLE);
    matrix[4][3] = new Step2D<>(4, 3, MapMatrix.Values.ROAD);
    matrix[4][4] = new Step2D<>(4, 4, MapMatrix.Values.ROAD);

    MapMatrix mapMatrix = new MapMatrix(matrix);

    BreadthFirstIterator it = new BreadthFirstIterator<>(mapMatrix, matrix[3][0], matrix[0][3]);

    //Etat de la pile théorique avec comme sens de rotation TOP RIGHT BOT LEFT
    //(3,0)
    //(2,0); (3,1); (4,0)
    //(3,1); (4,0); (1,0)
    //(4,0); (1,0); (3,2)
    //(1,0); (3,2); (3,4)
    //(3,2); (3,4); (0,0); (1,1)
    //(3,4); (0,0); (1,1); (3,3)
    //(1,1); (3,3); (0,1); (4,4)
    //(3,3); (0,1); (4,4); (1,2)
    //(0,1); (4,4); (1,2); (2,3)
    //(4,4); (1,2); (2,3); (0,2)
    //(1,2); (2,3); (0,2)
    //(2,3); (0,2); (1,3)
    //(0,2); (1,3); (2,4)
    //(1,3); (2,4); (0,3)
    //(2,4); (0,3)
    //(0,3);
    //Fin
    while (it.hasNext()) {
      System.out.println(it.next());
    }

    System.out.println("path:");
    var steps = it.path();
    for (var s : steps) {
      System.out.println(s);
    }
  }
}
