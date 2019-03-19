package data_structure;

import org.junit.jupiter.api.Test;

import java.util.Deque;

import static org.junit.jupiter.api.Assertions.*;

class SimplyLinkedListTest {

  Deque<Integer> list;

  @org.junit.jupiter.api.BeforeEach
  void setUp() {
    list = new SimplyLinkedList<Integer>();
  }

  @org.junit.jupiter.api.AfterEach
  void tearDown() {}

  @Test
  void testAddFirst() {
    assertNull(list.getFirst());

    list.addFirst(12);
    assertEquals(12, list.getFirst());
    assertEquals(12, list.getLast());

    list.addFirst(20);
    assertEquals(20, list.getFirst());
    assertEquals(12, list.getLast());
  }

  @Test
  void testAddLast() {
    list.addLast(10);
    assertEquals(10, list.getLast());

    list.addLast(55);
    assertEquals(55, list.getLast());
  }

  @Test
  void testRemoveFirst() {
    list.addFirst(60);
    assertEquals(60, list.getFirst());

    list.removeFirst();
    assertNull(list.getFirst());

    list.addFirst(88);
    list.addFirst(99);
    list.removeFirst();

    assertEquals(88, list.getFirst());
  }
}
