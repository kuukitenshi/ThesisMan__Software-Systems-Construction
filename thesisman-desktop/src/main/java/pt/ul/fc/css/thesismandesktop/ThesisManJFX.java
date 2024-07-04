package pt.ul.fc.css.thesismandesktop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class ThesisManJFX extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
    Pane root = loginLoader.load();
    Scene scene = new Scene(root);
    primaryStage.setTitle("ThesisMan Students");
    primaryStage.setScene(scene);
    primaryStage.getIcons().add(new Image("/static/icon/icon.png"));
    primaryStage.show();
  }
}
