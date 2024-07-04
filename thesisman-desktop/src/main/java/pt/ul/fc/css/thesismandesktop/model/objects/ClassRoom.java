package pt.ul.fc.css.thesismandesktop.model.objects;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClassRoom {

  private final LongProperty id = new SimpleLongProperty();
  private final StringProperty designation = new SimpleStringProperty();

  public LongProperty idProperty() {
    return this.id;
  }
  public final long getId() {
    return idProperty().get();
  }
  public final void setId(long id) {
    idProperty().set(id);
  }

  public StringProperty designationProperty() {
    return this.designation;
  }

  public String getDesignation() {
    return designationProperty().get();
  }

  public void setDesignation(String designation) {
    designationProperty().set(designation);
  }
}
