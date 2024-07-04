package pt.ul.fc.css.thesismandesktop.model.objects;

import javafx.beans.property.LongProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public abstract class GeneralUser {

  private final LongProperty id = new SimpleLongProperty();
  private final StringProperty username = new SimpleStringProperty();
  private final StringProperty password = new SimpleStringProperty();
  private final StringProperty name = new SimpleStringProperty();

  public LongProperty idProperty() {
    return this.id;
  }
  public final long getId() {
    return idProperty().get();
  }
  public final void setId(long id) {
    idProperty().set(id);
  }

  public StringProperty usernameProperty() {
    return this.username;
  }

  public final String getUsername() {
    return usernameProperty().get();
  }

  public final void setUsername(String username) {
    usernameProperty().set(username);
  }

  public StringProperty passwordProperty() {
    return this.password;
  }

  public final String getPassword() {
    return passwordProperty().get();
  }

  public final void setPassword(String password) {
    passwordProperty().set(password);
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
}
