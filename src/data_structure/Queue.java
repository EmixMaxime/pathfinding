package data_structure;

public class Queue  <E>{
    SimplyLinkedList<E> pc = new SimplyLinkedList();

    public E queueRemove() {
      return pc.removeLast();
    }
    public void queueAdd(E e) {
      pc.addFirst(e);
    }

}
