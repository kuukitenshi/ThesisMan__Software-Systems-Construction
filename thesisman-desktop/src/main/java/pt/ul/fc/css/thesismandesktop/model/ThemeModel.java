package pt.ul.fc.css.thesismandesktop.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.ul.fc.css.thesismandesktop.model.objects.Application;
import pt.ul.fc.css.thesismandesktop.model.objects.Theme;

public class ThemeModel {

  private final ObservableList<Theme> themes = FXCollections.observableArrayList();
  private final ObservableList<Application> applications = FXCollections.observableArrayList();

  public ObservableList<Theme> getThemes() {
    return themes;
  }

  public ObservableList<Application> getApplications() {
    return applications;
  }
}
