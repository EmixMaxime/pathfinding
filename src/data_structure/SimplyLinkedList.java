package data_structure;

import java.util.*;

public class SimplyLinkedList<E> extends AbstractSequentialList<E>
    implements List<E>, Deque<E>, Cloneable {
  private static class Node<E> {
    private E element;
    private Node<E> next;

    public Node(E e, Node<E> n) {
      element = e;
      next = n;
    }

    public E getElement() {
      return element;
    }

    public Node<E> getNext() {
      return next;
    }

    public void setNext(Node<E> n) {
      next = n;
    }
  }

  private Node<E> head = null;
  private Node<E> tail = null;
  private int size = 0;

  public boolean isEmpty() {
    return size == 0;
  }

  {
  }
  /** Constructs an empty list. */
  public SimplyLinkedList() {}

  @Override
  public ListIterator<E> listIterator(int index) {
    return null;
  }

  @Override
  public void addFirst(E e) {
    head = new Node<>(e, head);
    if (size == 0) {
      tail = head;
    }
    size++;
  }

  @Override
  public void addLast(E e) {
    Node<E> newNode = new Node<>(e, null);
    if (isEmpty()) {
      head = newNode;
    } else {
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
  public E removeFirst() {
    if (isEmpty()) {
      return null;
    }
    E answer = head.getElement();
    head = head.getNext();
    size--;
    if (size == 0) {
      tail = null;
    }
    return answer;
  }

  @Override
  public E removeLast() {
    return null;
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
  public E getFirst() {
    if (isEmpty()) {
      return null;
    }
    return head.getElement();
  }

  @Override
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
  public void push(E e) {}

  @Override
  public E pop() {
    return null;
  }

  @Override
  public int size() {
    return 0;
  }

  @Override
  public Iterator<E> descendingIterator() {
    return null;
  }
}
