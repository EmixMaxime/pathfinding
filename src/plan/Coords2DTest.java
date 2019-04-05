package plan;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Coords2DTest {

  @BeforeEach
  void setUp() {}

  @Test
  void manhattanDistance() {
    Coords2D pointA = new Coords2D(3, 5);
    Coords2D pointB = new Coords2D(8, 9);

    Double ACTUAL = Coords2D.manhattanDistance(pointA, pointB);
    Double EXPECTED = 9.;
    assertEquals(EXPECTED, ACTUAL);

    Coords2D pointC = new Coords2D(7, 8);
    Coords2D pointD = new Coords2D(-9, -9);

    ACTUAL = Coords2D.manhattanDistance(pointC, pointD);
    EXPECTED = 33.;
    assertEquals(EXPECTED, ACTUAL);

  }

  @Test
  void euclideanDistance() {
    Coords2D pointA = new Coords2D(3, 5);
    Coords2D pointB = new Coords2D(8, 9);

    Double ACTUAL = Coords2D.euclideanDistance(pointA, pointB);
    Double EXPECTED = Math.sqrt(41);
    assertEquals(EXPECTED, ACTUAL);

    Coords2D pointC = new Coords2D(7, 8);
    Coords2D pointD = new Coords2D(-9, -9);

    ACTUAL = Coords2D.euclideanDistance(pointC, pointD);
    EXPECTED =  Math.sqrt(545);
    assertEquals(EXPECTED, ACTUAL);
  }
}
