package pt.ul.fc.css.thesismandesktop.controllers;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.BooleanBinding;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import pt.ul.fc.css.thesismandesktop.model.SessionModel;
import pt.ul.fc.css.thesismandesktop.model.ThemeCellModel;
import pt.ul.fc.css.thesismandesktop.model.objects.*;
import pt.ul.fc.css.thesismandesktop.services.ApplicationService;
import pt.ul.fc.css.thesismandesktop.services.exceptions.HttpRequestException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ThemeCellController implements Initializable {

  private final ApplicationService applicationService = new ApplicationService();
  private final ThemeCellModel model = new ThemeCellModel();

  @FXML private Label advisorLabelInput;
  @FXML private Label descriptionLabel;
  @FXML private Label enterpriseAdvisorLabel;
  @FXML private Label enterpriseAdvisorLabelInput;
  @FXML private Label remunerationLabel;
  @FXML private Label statusLabel;
  @FXML private Label titleThemeLabel;
  @FXML private Label typeThemeLabel;
  @FXML private Label compMastersLabelInput;
  @FXML private Button applyBtn;

  public void initList(ObservableList<Application> applications) {
    this.model.setApplications(applications);
    BooleanBinding listContains = Bindings.createBooleanBinding(() ->
            applications.stream()
                    .anyMatch(a -> model.getTheme() != null && a.getTheme().getId() == model.getTheme().getId()),
            applications, model.themeProperty());
    applyBtn.disableProperty().bind(listContains);
    applyBtn.textProperty().bind(
            Bindings.when(listContains)
                    .then("Applied")
                    .otherwise("Apply"));
  }

  public void updateTheme(Theme theme) {
    model.setTheme(theme);
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    advisorLabelInput.textProperty().bind(Bindings.selectString(model.themeProperty(), "advisor", "name"));
    descriptionLabel.textProperty().bind(Bindings.selectString(model.themeProperty(), "description"));
    enterpriseAdvisorLabel.visibleProperty().bind(Bindings.isNotNull(Bindings.select(model.themeProperty(), "worker")));
    enterpriseAdvisorLabelInput.visibleProperty().bind(enterpriseAdvisorLabel.visibleProperty());
    enterpriseAdvisorLabelInput.textProperty().bind(Bindings.select(model.themeProperty(), "worker", "name"));
    remunerationLabel.textProperty().bind(
            Bindings.concat(Bindings.selectString(model.themeProperty(), "remuneration"), "€/month"));
    statusLabel.textProperty().bind(Bindings.selectString(model.themeProperty(), "state"));
    titleThemeLabel.textProperty().bind(Bindings.selectString(model.themeProperty(), "title"));
    typeThemeLabel.textProperty().bind(
            Bindings.when(Bindings.isNull(Bindings.select(model.themeProperty(), "worker")))
                    .then("Dissertation")
                    .otherwise("Project"));
    compMastersLabelInput.textProperty().bind(
            Bindings.createStringBinding(() -> {
              Theme theme = model.getTheme();
              if (theme == null || theme.getCompatibleMasters() == null)
                return null;
              List<MastersDegree> masters = theme.getCompatibleMasters();
              StringBuilder sb = new StringBuilder();
              for (int i = 0; i < masters.size(); i++) {
                sb.append(masters.get(i).getName());
                if (i != masters.size() - 1)
                  sb.append(", ");
              }
              return sb.toString();
            }, Bindings.select(model.themeProperty(), "theme", "compatibleMasters")));
    applyBtn.visibleProperty().bind(Bindings.notEqual(ThemeState.TAKEN, Bindings.select(model.themeProperty(), "state")));
  }

  public void applyClick() {
    Student student = SessionModel.getInstance().studentProperty().get();
    Theme theme = model.getTheme();
    try {
      Application app = this.applicationService.addApplication(student.getId(), theme.getId());
      model.getApplications().add(app);
    } catch (HttpRequestException e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
          alert.setHeaderText("An error occurred when trying to apply!");
          alert.show();
    }
  }
}
