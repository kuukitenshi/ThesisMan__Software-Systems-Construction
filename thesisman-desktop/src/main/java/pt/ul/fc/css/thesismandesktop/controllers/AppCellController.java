package pt.ul.fc.css.thesismandesktop.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import pt.ul.fc.css.thesismandesktop.binds.DateFormattingBinding;
import pt.ul.fc.css.thesismandesktop.model.AppCellModel;
import pt.ul.fc.css.thesismandesktop.model.objects.Application;
import pt.ul.fc.css.thesismandesktop.model.objects.MastersDegree;
import pt.ul.fc.css.thesismandesktop.services.ApplicationService;
import pt.ul.fc.css.thesismandesktop.services.exceptions.HttpRequestException;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

public class AppCellController implements Initializable {

  private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm");

  private final AppCellModel model = new AppCellModel();
  private final ApplicationService applicationService = new ApplicationService();

  @FXML private Label advisorLabelInput;
  @FXML private Label descriptionLabel;
  @FXML private Label remunerationLabel;
  @FXML private Label enterpriseAdvisorLabel;
  @FXML private Label enterpriseAdvisorLabelInput;
  @FXML private Label titleThemeLabel;
  @FXML private Label statusLabel;
  @FXML private Label dateLabel;
  @FXML private Label typeThemeLabel;
  @FXML private Label compMastersLabelInput;

  private ListView<Application> applications;

  public void initList(ListView<Application> applications) {
    this.applications = applications;
  }

  public void updateApplication(Application application) {
    model.applicationProperty().set(application);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    advisorLabelInput.textProperty().bind(Bindings.select(model.applicationProperty(), "theme", "advisor", "name"));
    descriptionLabel.textProperty().bind(Bindings.select(model.applicationProperty(), "theme", "description"));
    remunerationLabel.textProperty().bind(
            Bindings.concat(Bindings.select(model.applicationProperty(), "theme", "remuneration"), " €/month"));
    enterpriseAdvisorLabel.visibleProperty().bind(Bindings.isNotNull(Bindings.select(model.applicationProperty(), "theme", "worker")));
    enterpriseAdvisorLabelInput.visibleProperty().bind(Bindings.isNotNull(Bindings.select(model.applicationProperty(), "theme", "worker")));
    enterpriseAdvisorLabelInput.textProperty().bind(Bindings.select(model.applicationProperty(), "theme", "worker", "name"));
    titleThemeLabel.textProperty().bind(Bindings.selectString(model.applicationProperty(), "theme", "title"));
    statusLabel.textProperty().bind(Bindings.selectString(model.applicationProperty(), "state"));
    dateLabel.textProperty().bind(new DateFormattingBinding(Bindings.select(model.applicationProperty(), "date"), DATE_FORMATTER));
    typeThemeLabel.textProperty().bind(
            Bindings.when(Bindings.isNotNull(Bindings.select(model.applicationProperty(), "theme", "worker")))
                    .then("Project")
                    .otherwise("Dissertation")
    );
    compMastersLabelInput.textProperty().bind(
            Bindings.createStringBinding(() -> {
              Application app = model.applicationProperty().get();
              if (app == null || app.getTheme() == null)
                return null;
              List<MastersDegree> masters = app.getTheme().getCompatibleMasters();
              if (masters == null)
                return null;
              StringBuilder sb = new StringBuilder();
              for (int i = 0; i < masters.size(); i++) {
                sb.append(masters.get(i).getName());
                if (i != masters.size() - 1)
                  sb.append(", ");
              }
              return sb.toString();
            }, Bindings.select(model.applicationProperty(), "theme", "compatibleMasters")));
  }

  public void cancelClick() {
    Application application = model.applicationProperty().get();
    Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
    confirmAlert.setTitle("Confirm removal");
    confirmAlert.setHeaderText("Do you want to remove your application for " + application.getTheme().getTitle() + "?");
    confirmAlert.showAndWait().ifPresent(response -> {
      if (response == ButtonType.OK) {
        try {
          this.applicationService.cancelApplication(application.getId());
          this.applications.getItems().remove(application);
        } catch (HttpRequestException e) {
          Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setHeaderText("An error occurred when trying to remove application!");
          alert.show();
        }
      }
    });
  }
}
