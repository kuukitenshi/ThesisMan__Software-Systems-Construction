package pt.ul.fc.css.thesismandesktop.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.ul.fc.css.thesismandesktop.model.objects.Application;

public class ApplicationModel {

  private final ObservableList<Application> applications = FXCollections.observableArrayList();

  public ObservableList<Application> getApplications() {
    return applications;
  }
}
