package data_structure.array_list;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MxArrayTest {

  @BeforeEach
  void setUp() {}

  @AfterEach
  void tearDown() {}

  @Test
  void add() {
    Integer[] baseArray = new Integer[5];
    MxArray mxArray = new MxArray(baseArray);

    assertEquals(0, mxArray.size());

    mxArray.add(5);

    assertEquals(1, mxArray.size());

    mxArray.add(5);
    mxArray.add(5);

    assertEquals(3, mxArray.size());
  }

  @Test
  void add1() {}

  @Test
  void size() {}

  @Test
  void isFull() {
    Integer[] baseArray = new Integer[1];
    MxArray mxArray = new MxArray(baseArray);

    mxArray.add(6);
    assertEquals(true, mxArray.isFull());
  }

  @Test
  void length() {}

  @Test
  void get() {
    Integer[] baseArray = new Integer[5];
    MxArray mxArray = new MxArray(baseArray);

    for (int i = 0; i < 5; i++) {
      mxArray.add(i);
      assertEquals(i, mxArray.get(i));
    }
  }
}
