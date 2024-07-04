package pt.ul.fc.css.thesismandesktop.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pt.ul.fc.css.thesismandesktop.model.objects.Application;

public class AppCellModel {

  private final ObjectProperty<Application> application = new SimpleObjectProperty<>();

  public ObjectProperty<Application> applicationProperty() {
    return application;
  }
}
