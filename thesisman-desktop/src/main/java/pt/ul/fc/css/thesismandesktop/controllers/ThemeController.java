package pt.ul.fc.css.thesismandesktop.controllers;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import pt.ul.fc.css.thesismandesktop.factories.ThemeCellFactory;
import pt.ul.fc.css.thesismandesktop.model.SessionModel;
import pt.ul.fc.css.thesismandesktop.model.ThemeModel;
import pt.ul.fc.css.thesismandesktop.model.objects.Application;
import pt.ul.fc.css.thesismandesktop.model.objects.Student;
import pt.ul.fc.css.thesismandesktop.model.objects.Theme;
import pt.ul.fc.css.thesismandesktop.services.ApplicationService;
import pt.ul.fc.css.thesismandesktop.services.ThemeService;
import pt.ul.fc.css.thesismandesktop.services.YearCalculatorService;
import pt.ul.fc.css.thesismandesktop.services.exceptions.HttpRequestException;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ThemeController implements Initializable {

  @FXML private ListView<Theme> listView;
  @FXML private Label yearLabel;
  @FXML private Label noValueLabel;

  private final ThemeService themeService = new ThemeService();
  private final ApplicationService applicationService = new ApplicationService();
  private final YearCalculatorService yearCalculatorService = new YearCalculatorService();
  private final ThemeModel model = new ThemeModel();

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    noValueLabel.visibleProperty().bind(Bindings.isEmpty(model.getThemes()));
    listView.styleProperty().bind(
            Bindings.when(Bindings.isEmpty(model.getThemes()))
                    .then("-fx-background-color: white;")
                    .otherwise("-fx-background-color: transparent;"));
    listView.setItems(model.getThemes());
    listView.setCellFactory(new ThemeCellFactory(model.getApplications()));
    String year = yearCalculatorService.calculateYear();
    yearLabel.setText(year);
    try {
      List<Theme> themes = themeService.listThemeByYear(year);
      this.model.getThemes().addAll(themes);
    } catch (HttpRequestException e) { }
    try {
      Student student = SessionModel.getInstance().studentProperty().get();
      List<Application> apps = applicationService.studentApplicationsList(student.getId());
      this.model.getApplications().addAll(apps);
    } catch (HttpRequestException e) { }
  }
}
