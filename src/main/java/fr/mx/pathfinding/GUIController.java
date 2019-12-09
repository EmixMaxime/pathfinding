package fr.mx.pathfinding;

import com.sun.jdi.Value;
import fr.mx.pathfinding.map.MapBuilder;
import fr.mx.pathfinding.map.MapMatrix;
import fr.mx.pathfinding.map.MapSearch;
import fr.mx.pathfinding.plan.Coords2D;
import fr.mx.pathfinding.plan.Step2D;
import fr.mx.pathfinding.traverse.BreadthFirstIterator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.*;

public class GUIController {

  private class MapService extends Service<MapSearch> {

    private String mapName;

    @Override
    protected Task<MapSearch> createTask() {

      return new Task<>() {
        private int computeIndexFromXY(Coords2D coords) {
          return (int) ((coords.getY() * (maze.getPrefColumns() - 1)) + coords.getX());
        }

        @Override
        protected void succeeded() {
          MapSearch mapSearch = getValue();
          var mapMatrix = mapSearch.getMap();

          Image road = null;
          try {
            road =
              new Image(
                new FileInputStream("resources/imgs/sand_tile.png"),
                xTileSize,
                yTitleSize,
                true,
                false);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }

          Image obstacle = null;
          try {
            obstacle =
              new Image(
                new FileInputStream("resources/imgs/grass_tile_1.png"),
                xTileSize,
                yTitleSize,
                true,
                false);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }


          Image perso = null;
          try {
            perso =
              new Image(
                new FileInputStream("resources/imgs/perso.png"),
                xTileSize,
                yTitleSize,
                true,
                false);
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }

          Image path = perso;


          ImageView win = null;
          try {
            win =
              new ImageView(
                new Image(
                  new FileInputStream("resources/imgs/gem.png"),
                  xTileSize,
                  yTitleSize,
                  true,
                  false));
          } catch (FileNotFoundException e) {
            e.printStackTrace();
          }

          var start = mapSearch.getStart();
          var end = mapSearch.getGoal();

          // Add things into the grid. Obstacle or road!
          for (int y = 0; y < mapMatrix.getYSize(); y++) {
            for (int x = 0; x < mapMatrix.getXSize(); x++) {
              ImageView img = null;

              if (start.equals(new Coords2D(x, y))) {
                img = new ImageView(perso);
              } else if (end.equals(new Coords2D(x, y))) {
                img = win;
              } else if (mapMatrix.isWalkable(x, y) != null) {
                img = new ImageView(road);
              } else {
                img = new ImageView(obstacle);
              }

              maze.getChildren().add(img);
            }
          }


          var map = mapSearch.getMap();
          var s = map.getElement(start);
          var g = map.getElement(end);

          var it = new BreadthFirstIterator<>(map, s, g);
          while (it.hasNext()) {
            System.out.println(it.next());
          }

          List<Step2D<MapMatrix.Values>> list = it.path();
          maze.getChildren().clear();

          // Redraw everything!
          for (int y = 0; y < mapMatrix.getYSize(); y++) {
            for (int x = 0; x < mapMatrix.getXSize(); x++) {
              ImageView img = null;

              if (list.contains(mapMatrix.getElement(new Coords2D(x, y)))) {
                img = new ImageView(path);
              } else if (start.equals(new Coords2D(x, y))) {
                img = new ImageView(perso);
              } else if (end.equals(new Coords2D(x, y))) {
                img = win;
              } else if (mapMatrix.isWalkable(x, y) != null) {
                img = new ImageView(road);
              } else {
                img = new ImageView(obstacle);
              }

              maze.getChildren().add(img);
            }
          }

//          for (var item : list) {
//            System.out.println("x,y of path " + item.getX() + " " + item.getY());
//            maze.getChildren().set(computeIndexFromXY(item.getCoords()), new ImageView(perso));
//          }
        }

        @Override
        protected MapSearch call() {
          System.out.println("call in thread for map " + mapName);
          MapSearch mapSearch = mapSearchSupplier.get("/" + mapName);

          var start = mapSearch.getStart();
          var end = mapSearch.getGoal();

          var map = mapSearch.getMap();
          var s = map.getElement(start);
          var g = map.getElement(end);

          var it = new BreadthFirstIterator<>(map, s, g);
          while (it.hasNext()) {
            System.out.println(it.next());
          }

          maze.setPrefColumns(mapSearch.getMap().getXSize());
          maze.setPrefRows(mapSearch.getMap().getYSize());

          int xSize = mapSearch.getMap().getXSize();
          int ySize = mapSearch.getMap().getYSize();

          xTileSize = (GUIMain.WIDTH * 1.0 / xSize);
          yTitleSize = (GUIMain.HEIGHT * 1.0 / ySize);

          return mapSearch;
        }

        @Override
        protected void failed() {
          // @TODO handle this!
          System.out.println("FAILED IN THREAD" + Arrays.toString(getException().getStackTrace()));
        }
      };
    }
  }

  private final int DEFAULT_MAP_INDEX = 0;

  private MapService mapService = new MapService();

  private MapSearchSupplier mapSearchSupplier;

  @FXML
  ChoiceBox<String> mapSelector, algoSelector;
  @FXML
  private Button runBtn;

  @FXML
  AnchorPane mazeContainer;
  private TilePane maze;

  private List<String> availableMaps;

  private double xTileSize, yTitleSize;

  public GUIController(MapSearchSupplier mapSearchSupplier) {
    this.mapSearchSupplier = mapSearchSupplier;
    this.availableMaps = mapSearchSupplier.getAvailableMaps();

    System.out.println(availableMaps);
  }

  /**
   * run mapService with desired fr.mx.pathfinding.data_structure.map & algo.
   *
   * @param mouseEvent mouseEvent
   */
  private void runPathFinding(MouseEvent mouseEvent) {
    String algoName = algoSelector.getValue();
    String mapName = mapSelector.getValue();

    maze.getChildren().clear();

    //    assertMapExists(mapName);

    System.out.println("run new thread for map " + mapName);

    mapService.mapName = mapName;
    mapService.restart();
  }

  @FXML
  public void initialize() throws FileNotFoundException {
    // Populate mapSelector
    ObservableList<String> data = FXCollections.observableList(availableMaps);

    runBtn.setOnMouseClicked(this::runPathFinding);

//     set default selected value.
    mapSelector.setValue(availableMaps.get(DEFAULT_MAP_INDEX));
    mapSelector.setItems(data);

    // Create maze TilePane.
    maze = new TilePane(0, 0);
    mazeContainer.getChildren().add(maze);
  }

  private void assertMapExists(String mapName) {
    String file = Paths.get(mapName).toString();
    System.out.println(file);
    System.out.println(Arrays.toString(availableMaps.toArray()));

    boolean contains = availableMaps.contains(file);

    if (!contains) {
      throw new NoSuchElementException();
    }
  }
}
