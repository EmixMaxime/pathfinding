package fr.mx.pathfinding.data_structure;

import java.util.ArrayList;

/**
 *
 * @param <E> the type of elements in this list
 */
public class MxArrayList<E> {

  private ArrayList<ArrayList<E>> listOfArrays;

  /**
   * Default initial capacity.
   */
  private static final int DEFAULT_CAPACITY = 10;


  public MxArrayList() {
    this.listOfArrays = new ArrayList<>();
    this.createNewRepo();
  }

  private void createNewRepo() {
    this.listOfArrays.add(new ArrayList<>());
  }

  private ArrayList<E> getLastRepo() {
    if (listOfArrays.get(listOfArrays.size() -1).size() > DEFAULT_CAPACITY) {
      System.out.println("CREATE NEW REPO!");
      this.createNewRepo();
    }

    return listOfArrays.get(listOfArrays.size() -1);
  }

//  private boolean isLastRepoFull() {
//    ArrayList<E> ar = getLastRepo();
//    return ar.size() > DEFAULT_CAPACITY;
//  }

  public void add(E data) {
    getLastRepo().add(data);
  }

  public E get(int index) {
    if (index > DEFAULT_CAPACITY) {
      int indexInRepo = index % DEFAULT_CAPACITY;
      int indexOfRepo = index / DEFAULT_CAPACITY;

      return this.listOfArrays.get(indexOfRepo).get(indexInRepo);
    }

    return this.listOfArrays.get(0).get(index);
  }

}
