package pt.ul.fc.css.thesismandesktop.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import pt.ul.fc.css.thesismandesktop.model.SessionModel;
import pt.ul.fc.css.thesismandesktop.model.objects.Student;
import pt.ul.fc.css.thesismandesktop.services.AuthenticationService;
import pt.ul.fc.css.thesismandesktop.services.exceptions.HttpRequestException;

import java.io.IOException;

public class LoginController {

  private final AuthenticationService studentService = new AuthenticationService();

  @FXML private TextField emailInput;
  @FXML private PasswordField passwordInput;
  @FXML private Label errorLabel;

  @FXML
  public void login(ActionEvent event) {
    String email = emailInput.getText();
    String password = passwordInput.getText();
    if (email.isBlank()) {
      errorLabel.setText("Email cannot be blank!");
      return;
    }
    if (password.isBlank()) {
      errorLabel.setText("Password cannot be blank!");
      return;
    }
    Student student = null;
    try{
      student = studentService.authenticateStudent(email, password);
    } catch (HttpRequestException e) {
      e.printStackTrace();
      errorLabel.setText("Error trying to authenticate user!");
      return;
    }
    if(student == null){
      errorLabel.setText("Invalid email or password!");
      return;
    }
    errorLabel.setVisible(false);
    Scene scene = ((Node) event.getSource()).getScene();
    SessionModel.getInstance().studentProperty().set(student);
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/menu.fxml"));
    try {
      scene.setRoot(loader.load());
    } catch (IOException e) {
      e.printStackTrace();
      System.exit(-1);
    }
  }
}
