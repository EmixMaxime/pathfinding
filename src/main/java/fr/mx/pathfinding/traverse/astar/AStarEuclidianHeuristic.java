package fr.mx.pathfinding.traverse.astar;

import fr.mx.pathfinding.map.MapMatrix;
import fr.mx.pathfinding.plan.Coords2D;
import fr.mx.pathfinding.plan.Step2D;

public class AStarEuclidianHeuristic implements AStarHeuristic<Step2D<MapMatrix.Values>> {

  @Override
  public double getCostEstimate(Step2D<MapMatrix.Values> sourceStep, Step2D<MapMatrix.Values> targetStep) {
    return Coords2D.euclideanDistance(new Coords2D(sourceStep.getX(), sourceStep.getY()), new Coords2D(targetStep.getX(), targetStep.getY()));
  }
}
