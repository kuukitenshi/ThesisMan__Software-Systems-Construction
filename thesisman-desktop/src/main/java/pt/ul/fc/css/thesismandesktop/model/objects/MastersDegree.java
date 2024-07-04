package pt.ul.fc.css.thesismandesktop.model.objects;

import javafx.beans.property.*;

public class MastersDegree {

  private final LongProperty id = new SimpleLongProperty();
  private final StringProperty name = new SimpleStringProperty();
  private final ObjectProperty<Teacher> admin = new SimpleObjectProperty<>();

  public LongProperty idProperty() {
    return this.id;
  }
  public final long getId() {
    return idProperty().get();
  }
  public final void setId(long id) {
    idProperty().set(id);
  }

  public StringProperty nameProperty() {
    return this.name;
  }

  public final String getName() {
    return nameProperty().get();
  }

  public final void setName(String name) {
    nameProperty().set(name);
  }

  public ObjectProperty<Teacher> adminProperty() {
    return this.admin;
  }

  public final Teacher getAdmin() {
    return adminProperty().get();
  }

  public final void setAdmin(Teacher admin) {
    adminProperty().set(admin);
  }
}
