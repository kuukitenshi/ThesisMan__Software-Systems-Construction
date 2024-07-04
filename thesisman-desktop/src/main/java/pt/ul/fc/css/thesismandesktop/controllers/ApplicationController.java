package pt.ul.fc.css.thesismandesktop.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import pt.ul.fc.css.thesismandesktop.factories.ApplicationCellFactory;
import pt.ul.fc.css.thesismandesktop.model.ApplicationModel;
import pt.ul.fc.css.thesismandesktop.model.SessionModel;
import pt.ul.fc.css.thesismandesktop.model.objects.Application;
import pt.ul.fc.css.thesismandesktop.model.objects.Student;
import pt.ul.fc.css.thesismandesktop.services.ApplicationService;
import pt.ul.fc.css.thesismandesktop.services.exceptions.HttpRequestException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ApplicationController implements Initializable {

  private final ApplicationService applicationService = new ApplicationService();
  private final ApplicationModel model = new ApplicationModel();

  @FXML private Label counterAppsLabel;
  @FXML private ListView<Application> listView;
  @FXML private Label noValueLabel;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    noValueLabel.visibleProperty().bind(Bindings.isEmpty(model.getApplications()));
    listView.styleProperty().bind(
            Bindings.when(noValueLabel.visibleProperty())
                    .then("-fx-background-color: transparent;")
                    .otherwise("-fx-background-color: white;"));
    counterAppsLabel.textProperty().bind(
            Bindings.concat(Bindings.size(model.getApplications()), "/5"));
    listView.setCellFactory(new ApplicationCellFactory());
    listView.setItems(model.getApplications());
    try {
      Student student = SessionModel.getInstance().studentProperty().get();
      List<Application> applications = applicationService.studentApplicationsList(student.getId());
      model.getApplications().addAll(applications);
    } catch (HttpRequestException e) { }
  }
}
