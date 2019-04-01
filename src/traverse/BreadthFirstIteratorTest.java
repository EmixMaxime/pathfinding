package traverse;

import map.MapMatrix;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BreadthFirstIteratorTest {

  @Test
  void yolo() {
    int xSize = 5;
    int ySize = 5;

    Step2D[][] matrix = new Step2D[xSize][ySize];

    for (int x = 0; x < xSize; x++) {
      for (int y = 0; y < ySize; y++) {
        matrix[x][y] = new Step2D<Integer>(x, y, 1);
      }
    }

    MapMatrix mapMatrix = new MapMatrix(matrix);

    BreadthFirstIterator it = new BreadthFirstIterator(mapMatrix, matrix[0][0]);
    assertEquals(true, it.hasNext());

//    it.next();

    while(it.hasNext()) {
      System.out.println(it.next());
    }
  }

}
