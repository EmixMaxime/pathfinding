package data_structure;

public class Pile <E> {
  SimplyLinkedList<E> pc = new SimplyLinkedList();

  /**
   *Removes and returns the first element from the pile
   * @return
   */
  public E pileRemove() {
    return pc.removeFirst();
  }

  /**
   *
   * @param e the value to add at the pile
   */
  public void pileAdd(E e) {
    pc.addFirst(e);
  }

}
