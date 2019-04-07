package fr.mx.pathfinding.plan;

public class Coords2D {
  private double x;
  private double y;

  public Coords2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public static double manhattanDistance(Coords2D a, Coords2D b) {
    return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
  }

  public static double euclideanDistance(Coords2D a, Coords2D b) {
    double yDelta = Math.abs(a.y - b.y);
    double xDelta = Math.abs(a.x - b.x);

    return Math.sqrt(Math.pow(yDelta, 2) + Math.pow(xDelta, 2));
  }

  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  @Override
  public String toString() {
    return "(" + x + "," + y + ")";
  }

  public boolean equals(Coords2D c) {
    return this.x == c.x && this.y == c.y;
  }
}
