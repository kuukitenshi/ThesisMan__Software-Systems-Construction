package pt.ul.fc.css.thesismandesktop.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.ul.fc.css.thesismandesktop.model.objects.Application;
import pt.ul.fc.css.thesismandesktop.model.objects.Theme;

public class ThemeCellModel {

  private final ObjectProperty<Theme> theme = new SimpleObjectProperty<>();
  private ObservableList<Application> applications = FXCollections.observableArrayList();

  public ObjectProperty<Theme> themeProperty() {
    return theme;
  }

  public Theme getTheme() {
    return themeProperty().get();
  }

  public void setTheme(Theme theme) {
    themeProperty().set(theme);
  }

  public ObservableList<Application> getApplications() {
    return applications;
  }

  public void setApplications(ObservableList<Application> applications) {
    this.applications = applications;
  }
}
