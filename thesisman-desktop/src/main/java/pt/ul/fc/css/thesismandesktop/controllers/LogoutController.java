package pt.ul.fc.css.thesismandesktop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;

import java.io.IOException;

public class LogoutController {

  @FXML
  public void login(ActionEvent event) {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/login.fxml"));
    Scene scene = ((Node) event.getSource()).getScene();
    try {
      scene.setRoot(loader.load());
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }
}
