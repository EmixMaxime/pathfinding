package ui;

import javafx.beans.value.ObservableValue;
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
import map.MapMatrix;
import map.MapSearch;
import plan.Coords2D;
import plan.Step2D;
import traverse.BreadthFirstIterator;

import java.io.File;
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

          // Add things into the grid. Obstacle or road!
          for (int y = 0; y < mapMatrix.getYSize(); y++) {
            for (int x = 0; x < mapMatrix.getXSize(); x++) {
              if (mapMatrix.isWalkable(x, y) != null) {
                ImageView img = new ImageView(road);
                maze.getChildren().add(img);
              } else {
                ImageView img = new ImageView(obstacle);
                maze.getChildren().add(img);
              }
            }
          }

          var start = mapSearch.getStart();
          var end = mapSearch.getGoal();

          maze.getChildren().set(computeIndexFromXY(start), new ImageView(perso));
          maze.getChildren().set(computeIndexFromXY(end), win);

          var s = mapSearch.getMap().getElement(mapSearch.getStart());
          var g = mapSearch.getMap().getElement(mapSearch.getGoal());

          var it = new BreadthFirstIterator<>(mapSearch.getMap(), s, g);
          while (it.hasNext()) {
            System.out.println(it.next());
          }

          var list = it.path();
          for (var item : list) {
            System.out.println("x,y of path " + item.getX() + " " + item.getY());
            maze.getChildren().set(computeIndexFromXY(item.getCoords()), new ImageView(perso));
          }
        }

        @Override
        protected MapSearch call() throws Exception {
          System.out.println("call in thread for map " + mapName);
          MapSearch mapSearch = mapSearchSupplier.get(mapName);

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
          System.out.println("FAILED " + Arrays.toString(getException().getStackTrace()));
        }
      };
    }
  }

  private final int DEFAULT_MAP_INDEX = 2;

  private MapService mapService = new MapService();

  private MapSearchSupplier mapSearchSupplier;
  private MapSearch mapSearch;

  @FXML ChoiceBox<String> mapSelector, algoSelector;
  @FXML private Button runBtn;

  @FXML AnchorPane mazeContainer;
  private TilePane maze;

  private List<String> availableMaps;

  private double xTileSize, yTitleSize;

  public GUIController(MapSearchSupplier mapSearchSupplier) {
    this.mapSearchSupplier = mapSearchSupplier;
    this.availableMaps = mapSearchSupplier.getAvailableMaps();
  }

  /**
   * run mapService with desired map & algo.
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

    // set default selected value.
    mapSelector.setValue(availableMaps.get(DEFAULT_MAP_INDEX));
    mapSelector.setItems(data);

    //    mapSelector
    //        .getSelectionModel()
    //        .selectedItemProperty()
    //        .addListener(
    //            (ObservableValue<? extends String> observable, String oldValue, String newValue)
    // ->
    //                setMap(newValue));

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
