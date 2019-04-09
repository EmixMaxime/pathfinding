package data_structure;
public class Pile <E> {
  SimplyLinkedList<E> pc = new SimplyLinkedList();

  public E pileRemove() {
    return pc.removeFirst();
  }
  public void pileAdd(E e) {
    pc.addFirst(e);
  }

}
