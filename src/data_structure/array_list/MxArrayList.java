package data_structure.array_list;

import java.util.*;

/** @param <E> the type of elements in this list */
public class MxArrayList<E> extends AbstractList<E> implements List<E>, RandomAccess {

  /** The array buffer into which the arrays which the elements of the MxArrayList are stored. */
  private transient ArrayList<MxArray<E>> arrayContainer;

  /**
   * The size of the ArrayList (the number of elements it contains).
   *
   * @serial
   */
  private int size;

  /**
   * The maximum size of array to allocate (unless necessary). Some VMs reserve some header words in
   * an array. Attempts to allocate larger arrays may result in OutOfMemoryError: Requested array
   * size exceeds VM limit
   *
   * @see java.util.ArrayList (same variable).
   */
  private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;

  /** Default initial capacity. */
  private static final int DEFAULT_CAPACITY = 10;

  public MxArrayList() {
    this.arrayContainer = new ArrayList<>();

    Object[] defaultArray = new Object[DEFAULT_CAPACITY];
    MxArray defaultMxArray = new MxArray<E>(defaultArray);

    arrayContainer.add(defaultMxArray);
  }

  public int size() {
    return size;
  }

  private MxArray<E> getLastArrayContainer() {
    //  If the list is empty, get throws an IndexOutOfBoundsException.
    if (arrayContainer != null && !arrayContainer.isEmpty()) {
      return arrayContainer.get(arrayContainer.size() - 1);
    }

    throw new Error("No last element...");
  }

  /**
   * @param index
   * @return
   */
  private int[] getIndexes(int index) {
    int indexInArray = index % DEFAULT_CAPACITY;
    int indexInArrayContainer = index / DEFAULT_CAPACITY;

    return new int[] {indexInArrayContainer, indexInArray};
  }

  /** A version of rangeCheck used by add and addAll. */
  private void rangeCheckForAdd(int index) {
    int[] indexes = getIndexes(index);

    if (arrayContainer.size() < indexes[0] || arrayContainer.get(indexes[0]).length() < indexes[1]) {
      throw new IndexOutOfBoundsException(outOfBoundsMsg(index));
    }
  }

  /**
   * Constructs an IndexOutOfBoundsException detail message. Of the many possible refactorings of
   * the error handling code, this "outlining" performs best with both server and client VMs.
   */
  private String outOfBoundsMsg(int index) {
    return "Index: " + index + ", Size: " + size;
  }

  /** A version used in checking (fromIndex > toIndex) condition */
  private static String outOfBoundsMsg(int fromIndex, int toIndex) {
    return "From Index: " + fromIndex + " > To Index: " + toIndex;
  }

  /**
   * Appends the specified element to the end of this list.
   *
   * @param e element to be appended to this list
   * @return {@code true} (as specified by {@link Collection#add})
   */
  public boolean add(E e) {
    modCount++;
    add(e, getLastArrayContainer());
    return true;
  }

  private void add(E e, MxArray<E> elementData) {
    if (elementData.isFull()) {
      System.out.println("Full, so grow!");
      elementData = grow();
    }

    elementData.add(e);
    size++;
  }

  /**
   * Returns the element at the specified position in this list.
   *
   * @param index index of the element to return
   * @return the element at the specified position in this list
   * @throws IndexOutOfBoundsException {@inheritDoc}
   */
  @SuppressWarnings("unchecked")
  public E get(int index) {
    if (index+1 > DEFAULT_CAPACITY) {
      int indexInRepo = index % DEFAULT_CAPACITY;
      int indexOfRepo = index / DEFAULT_CAPACITY;

      return (E) arrayContainer.get(indexOfRepo).get(indexInRepo);
    }

    return (E) arrayContainer.get(0).get(index);
  }

  /**
   * Increases the capacity to ensure that it can hold at least the number of elements specified by
   * the minimum capacity argument.
   *
   * @param minCapacity the desired minimum capacity
   * @throws OutOfMemoryError if minCapacity is less than zero
   * @return the new data array
   */
  private MxArray<E> grow(int minCapacity) {
    int capacity = newCapacity(minCapacity);

    Object[] a = new Object[capacity];
    MxArray mxArray = new MxArray<>(a);

    arrayContainer.add(mxArray);
    return mxArray;
  }

  private MxArray<E> grow() {
    return grow(size + 1);
  }

  /**
   * Returns a capacity at least as large as the given minimum capacity. Returns the current
   * capacity increased by 50% if that suffices.
   *
   * <p>Will not return a capacity greater than MAX_ARRAY_SIZE.
   *
   * @param minCapacity the desired minimum capacity
   */
  private int newCapacity(int minCapacity) {
    int oldCapacity = getLastArrayContainer().length();
    int newCapacity = oldCapacity + (oldCapacity >> 1);

    int capacity = Math.max(newCapacity, minCapacity);

    if (capacity > MAX_ARRAY_SIZE) {
      return MAX_ARRAY_SIZE;
    }

    return capacity;
  }
}
