package ui;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GUIMain extends Application {

  public final static int WIDTH = 1300;
  public final static int HEIGHT = 700;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("gui.fxml"));
    loader.setControllerFactory(t -> new GUIController(new MapProvider()));

    Parent root = loader.load();

    primaryStage.setTitle("Hello World");
    primaryStage.setScene(new Scene(root, WIDTH, HEIGHT));
    primaryStage.show();
  }
}
