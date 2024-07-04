package pt.ul.fc.css.thesismandesktop.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import pt.ul.fc.css.thesismandesktop.binds.UserProfileImageBinding;
import pt.ul.fc.css.thesismandesktop.model.SessionModel;
import pt.ul.fc.css.thesismandesktop.model.objects.Student;
import pt.ul.fc.css.thesismandesktop.services.AuthenticationService;
import pt.ul.fc.css.thesismandesktop.services.exceptions.HttpRequestException;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {

  private final AuthenticationService authenticationService = new AuthenticationService();

  @FXML private Label usernameLabel;
  @FXML private Label mailLabel;
  @FXML private Label welcomeLabel;
  @FXML private ImageView userImage;
  @FXML private BorderPane menuPane;

  @FXML
  public void logout(ActionEvent event) throws IOException {
    try {
      authenticationService.logout();
      FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/logout.fxml"));
      Scene scene = ((Node) event.getSource()).getScene();
      scene.setRoot(loader.load());
    } catch (HttpRequestException e) {
      e.printStackTrace();
    }
  }

  @FXML
  public void themesOpen(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/themes.fxml"));
    menuPane.setCenter(loader.load());
  }

  @FXML
  public void applicationsOpen(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/applications.fxml"));
    menuPane.setCenter(loader.load());
  }

  @FXML
  public void thesisOpen(ActionEvent event) throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/thesis.fxml"));
    menuPane.setCenter(loader.load());
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    mailLabel.textProperty().bind(Bindings.selectString(SessionModel.getInstance().studentProperty(), "name"));
    usernameLabel.textProperty().bind(Bindings.selectString(SessionModel.getInstance().studentProperty(), "username"));
    ObjectProperty<Student> student = SessionModel.getInstance().studentProperty();
    ObservableValue<?> nameValue = Bindings.select(student, "name");
    StringBinding usernameValue = Bindings.selectString(student, "username");
    welcomeLabel.textProperty().bind(Bindings.concat("Welcome ", nameValue , " to ThesisMan!"));
    userImage.imageProperty().bind(new UserProfileImageBinding(usernameValue));
  }
}
