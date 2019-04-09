package data_structure;

import java.util.*;

public class SimplyLinkedList<E>
        extends AbstractSequentialList<E>
        implements List<E>, Deque<E>, Cloneable
{
  // no
  private static class Node<E>
  {
    private E element;
    private Node<E> next;
    public Node(E e, Node<E> n){
      element = e;
      next = n;
    }

    /**
     *
     * @return the element node
     */
    public E getElement() {
      return element;
    }

    /**
     *
     * @return the next node
     */
    public Node<E> getNext() {
      return next;
    }

    /**
     *
     * @param n configure the next node
     */
    public void setNext(Node<E> n){
      next = n;
    }

  }
private Node<E> head = null;
private Node<E> tail = null;
private int size= 0;

  /**
   *
   * @return if  the simply linked list is empty
   */
  public boolean isEmpty(){
  return size == 0;
}

  {}
    /**
     * Constructs an empty list.
     */
    public SimplyLinkedList () {
  }

    @Override
    public ListIterator<E> listIterator(int index) {
        return null;
    }

    @Override
    /**
     * Inserts the specified element at the beginning of this list.
     */
    public void addFirst(E e) {
      head = new Node<>(e, head);
      if(size == 0) {
        tail = head;
      }
      size++;
    }
  /**
   * Appends the specified element to the end of this list.For add in last on the simple linked list
   */
  @Override
  public void addLast(E e) {
      Node<E> newNode = new Node<>(e, null);
      if(isEmpty()) {
        head = newNode;
      }else{
        tail.setNext(newNode);
      }
      tail = newNode;
      size++;
    }

    @Override
    public boolean offerFirst(E e) {
        return false;
    }

    @Override
    public boolean offerLast(E e) {
        return false;
    }

    @Override
    /**
     * Removes and returns the first element from this list.
     */
    public E removeFirst() {
      if (isEmpty()){
        return null;
      }
      E answer = head.getElement();
      head = head.getNext();
      size--;
      if(size ==0) {
        tail = null;
      }
      return answer;
    }

  @Override
  public E removeLast() {
    Node<E> current = head;
    Node<E> previous = head;
    while (current != null) {
      previous = current;
      current = current.getNext();

      if (tail == current) {
        tail = previous;
        tail.setNext(null);
      }
  @Override
  /**
   * Removes and returns the last element from this list.
   */
  public E removeLast() {
    Node<E> current = head;
    Node<E> previous = head;
    while (current != null) {
      previous = current;
      current = current.getNext();

      if (tail == current) {
        tail = previous;
        tail.setNext(null);
      }
    }
      size--;
      return tail.getElement();

  }


    @Override
    public E pollFirst() {
        return null;
    }

    @Override
    public E pollLast() {
        return null;
    }

    @Override
    /**
     * Returns the first element in this list.
     */
    public E getFirst() {
    if (isEmpty()) {
      return null;
        }
     return head.getElement();
    }
    @Override
    /**
     *
     Returns the last element in this list.
     */
    public E getLast() {
    if (isEmpty()) {
      return null;
        }
    return tail.getElement();
    }

    @Override
    public E peekFirst() {
        return null;
    }

    @Override
    public E peekLast() {
        return null;
    }

    @Override
    public boolean removeFirstOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean removeLastOccurrence(Object o) {
        return false;
    }

    @Override
    public boolean offer(E e) {
        return false;
    }

    @Override
    public E remove() {
        return null;
    }

    @Override
    public E poll() {
        return null;
    }

    @Override
    public E element() {
        return null;
    }

    @Override
    public E peek() {
        return null;
    }

    @Override
    public void push(E e) {

    }

    @Override
    public E pop() {
        return null;
    }

    @Override
    /**
     * Returns the number of elements in this list.
     */
    public int size() {
        return 0;
    }

    @Override
    public Iterator<E> descendingIterator() {
        return null;
    }
}
