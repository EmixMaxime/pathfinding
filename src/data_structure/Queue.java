package data_structure;

public class Queue  <E>{
    SimplyLinkedList<E> pc = new SimplyLinkedList();

  /**
   * Removes and returns the last element from the queue.
   * @return
   */
    public E queueRemove()
    {
      return pc.removeLast();
    }

  /**
   *
   * @param e the value to add at the queue.
   */
    public void queueAdd(E e) {
      pc.addFirst(e);
    }

}
