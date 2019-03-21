package data_structure;

import data_structure.array_list.MxArrayList;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MxArrayListTest {

  @Test
  void testAdd() {
    MxArrayList<Integer> ar = new MxArrayList<>();
    for (int i = 0; i < 15; i++) {
      ar.add(i);
    }
    assertEquals(0, ar.get(0));
    assertEquals(10, ar.get(10));
    assertEquals(12, ar.get(11));
  }
}
