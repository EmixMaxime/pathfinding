package ui;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.TilePane;
import map.MapBuilder;
import map.MapInformation;
import traverse.BreadthFirstIterator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.*;

public class GUIController {

  private MapSupplier mapSupplier;
  private MapBuilder mapBuilder;

  @FXML ChoiceBox<String> mapSelector;

  @FXML AnchorPane mazeContainer;
  private TilePane maze;

  private List<String> availableMaps;

  private double xTileSize, yTitleSize;

  public GUIController(MapSupplier mapSupplier) {
    this.mapSupplier = mapSupplier;

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
    mapSelector.setValue(availableMaps.get(3));
    setMap(availableMaps.get(3));
    mapSelector.setItems(data);

    mapSelector
        .getSelectionModel()
        .selectedItemProperty()
        .addListener(
            (ObservableValue<? extends String> observable, String oldValue, String newValue) ->
                setMap(newValue));

    // Create maze TilePane.
    maze = new TilePane(0, 0);
    maze.setPrefColumns(mapBuilder.getMapMatrix().getYSize());
    maze.setPrefRows(mapBuilder.getMapMatrix().getXSize());
    mazeContainer.getChildren().add(maze);

    int xSize = mapBuilder.getMapMatrix().getXSize();
    int ySize = mapBuilder.getMapMatrix().getYSize();

    xTileSize = GUIMain.WIDTH * 1.0 / xSize;
    yTitleSize = GUIMain.HEIGHT * 1.0 / ySize;
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
    this.mapBuilder = mapSupplier.get(mapName);
    // @TODO to continue...
  }

  private File[] getAvailableMaps() {
    return new File("resources").listFiles((dir, name) -> name.endsWith(".txt"));
  }

  private void drawMaze() throws FileNotFoundException {
    var mapMatrix = mapBuilder.getMapMatrix();
    var info = mapBuilder.getMapInformation();

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

    ImageView perso =
      new ImageView(
        new Image(
            new FileInputStream("resources/imgs/perso.png"), xTileSize, yTitleSize, true, false));

    ImageView win =
      new ImageView(
        new Image(
            new FileInputStream("resources/imgs/gem.png"), xTileSize, yTitleSize, true, false));

    // Add things into the grid. Obstacle or road!
    for (int x = 0; x < mapMatrix.getXSize(); x++) {
      for (int y = 0; y < mapMatrix.getYSize(); y++) {
        if (mapMatrix.isWalkable(x, y) != null) {
          ImageView img = new ImageView(road);
          maze.getChildren().add(img);
        } else {
          ImageView img = new ImageView(obstacle);
          maze.getChildren().add(img);
        }
      }
    }

    new BreadthFirstIterator<>(mapMatrix, )

    maze.getChildren().set(info.getEndX() * info.getEndY(), win);
    maze.getChildren().set(info.getStartX() * info.getStartY(), perso);

    //    maze.getChildren().add(img);
    //    maze.getChildren().add(img);
  }
}
