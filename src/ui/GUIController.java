package ui;

import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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

  private final int DEFAULT_MAP_INDEX = 2;

  private MapSearchSupplier mapSearchSupplier;
  private MapSearch mapSearch;

  @FXML ChoiceBox<String> mapSelector;

  @FXML AnchorPane mazeContainer;
  private TilePane maze;

  private List<String> availableMaps;

  private double xTileSize, yTitleSize;

  public GUIController(MapSearchSupplier mapSearchSupplier) {
    this.mapSearchSupplier = mapSearchSupplier;

    var files = getAvailableMaps();
    List<String> list = new ArrayList<>(files.length);
    for (File file : files) {
      String toString = file.toString();
      list.add(toString);
    }

    this.availableMaps = list;
  }

  @FXML
  public void initialize() throws FileNotFoundException {
    // Populate mapSelector
    ObservableList<String> data = FXCollections.observableList(availableMaps);

    // set default selected value.
    mapSelector.setValue(availableMaps.get(DEFAULT_MAP_INDEX));
    setMap(availableMaps.get(DEFAULT_MAP_INDEX));
    mapSelector.setItems(data);

    mapSelector
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                setMap(newValue));

    // Create maze TilePane.
    maze = new TilePane(0, 0);
    maze.setPrefColumns(mapSearch.getMap().getXSize());
    maze.setPrefRows(mapSearch.getMap().getYSize());
    mazeContainer.getChildren().add(maze);

    int xSize = mapSearch.getMap().getXSize();
    int ySize = mapSearch.getMap().getYSize();

    // @TODO fix that computation...
    xTileSize = (GUIMain.WIDTH * 1.0 / xSize);
    yTitleSize = (GUIMain.HEIGHT * 1.0 / ySize);
    drawMaze();
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

  private void setMap(String mapName) {
    assertMapExists(mapName);
    this.mapSearch = mapSearchSupplier.get(mapName);
    // @TODO to continue...
  }

  private File[] getAvailableMaps() {
    return new File("resources").listFiles((dir, name) -> name.endsWith(".txt"));
  }

  private int computeIndexFromXY(Coords2D coords) {
    return (int) ((coords.getY() * (maze.getPrefColumns() - 1)) + coords.getX());
  }

  private void drawMaze() throws FileNotFoundException {
    var mapMatrix = mapSearch.getMap();

    Image road =
        new Image(
            new FileInputStream("resources/imgs/sand_tile.png"),
            xTileSize,
            yTitleSize,
            true,
            false);

    Image obstacle =
        new Image(
            new FileInputStream("resources/imgs/grass_tile_1.png"),
            xTileSize,
            yTitleSize,
            true,
            false);

    //    Image path = new Image(new FileInputStream("resources/imgs/path.png"));

    Image perso =
        new Image(
            new FileInputStream("resources/imgs/perso.png"), xTileSize, yTitleSize, true, false);

    ImageView win =
        new ImageView(
            new Image(
                new FileInputStream("resources/imgs/gem.png"), xTileSize, yTitleSize, true, false));

    //     Add things into the grid. Obstacle or road!
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
      System.out.println();
    }

    var start = mapSearch.getStart();
    var end = mapSearch.getGoal();

    System.out.println("start " + start);
    System.out.println("end " + end);

    maze.getChildren().set(computeIndexFromXY(start), new ImageView(perso));
    maze.getChildren().set(computeIndexFromXY(end), win);

    var s = mapSearch.getMap().getElement(mapSearch.getStart());
    var g = mapSearch.getMap().getElement(mapSearch.getGoal());

    var it = new BreadthFirstIterator<>(mapSearch.getMap(), s, g);
    while (it.hasNext()) {
      System.out.println(it.next());
    }

//    var list = it.path();
//    for (var item : list) {
//      System.out.println("x,y of path " + item.getX() + " " + item.getY());
//      //          maze.getChildren().set(computeIndexFromXY(item.getCoords()), new
//      // ImageView(perso));
//    }
  }
}
