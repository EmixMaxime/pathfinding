package ui;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

public class GUIController {

  private MapSupplier mapSupplier;

  public GUIController(MapSupplier mapSupplier) {
    this.mapSupplier = mapSupplier;
  }

  @FXML AnchorPane maze;

  private Model model;

  private void drawMaze() throws FileNotFoundException {
    ImageView img1 =
        new ImageView(new Image(new FileInputStream("resources/imgs/sand_tile.png")));
    maze.getChildren().add(img1);
  }

  @FXML
  public void initialize() throws FileNotFoundException {
    drawMaze();
  }
}
