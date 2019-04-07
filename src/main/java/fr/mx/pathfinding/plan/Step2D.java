package fr.mx.pathfinding.plan;

import fr.mx.pathfinding.traverse.StepInterface;

public class Step2D<D> implements StepInterface<D> {

  private final int x;
  private final int y;
  private final Coords2D coords;
  private D data;

  public Step2D(int x, int y, D data) {
    this.coords = new Coords2D(x,y);
    this.x = x;
    this.y = y;
    this.data = data;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public D getData() {
    return data;
  }

  public boolean equals(StepInterface stepCompare) {
    Step2D step = (Step2D) stepCompare;
    return step.getX() == x && step.getY() == y && step.getData().equals(data);
  }

  @Override
  public String toString() {
    return "(" + x + "," + y + ")" + "=" + data.toString();
  }

  public Coords2D getCoords() {
    return coords;
  }
}
