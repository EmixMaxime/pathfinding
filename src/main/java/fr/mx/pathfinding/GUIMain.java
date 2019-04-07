package fr.mx.pathfinding;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;

public class GUIMain extends Application {

  public static final int WIDTH = 1300;
  public static final int HEIGHT = 700;

  public static class Launcher {
    public static void main(String[] args) {
      Application.launch(GUIMain.class, args);
    }
  }

//  public static void main(String[] args) {
//    launch(args);
//  }

  public void start(Stage primaryStage) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
    loader.setControllerFactory(t -> new GUIController(new MapSearchSearchProvider()));
    Parent root = loader.load();

    primaryStage.setTitle("Hello World");
    primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
    primaryStage.show();
  }
}
