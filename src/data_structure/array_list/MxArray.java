package data_structure.array_list;

/**
 *
 * Java doesn't have the concept of a "count" of the used elements in an array.
 * So there is!
 */
public class MxArray<E> {

  private Object[] plainArray;
  private int size = 0;

  public MxArray(Object[] array) {
    // @TODO COPY OF ARRAY!!!!
    plainArray = array;
  }

  public void add(E item, int index) {
    size++;
    plainArray[index] = item;
  }

  /**
   * Add to the end!
   * @param e
   */
  public void add(E e) {
    add(e, size());
  }

  public int size() {
    return size;
  }

  public boolean isFull() {
    return size == length();
  }

  public int length() {
    return plainArray.length;
  }

  @SuppressWarnings("unchecked")
  public E get(int index) {
    return (E) plainArray[index];
  }
}
