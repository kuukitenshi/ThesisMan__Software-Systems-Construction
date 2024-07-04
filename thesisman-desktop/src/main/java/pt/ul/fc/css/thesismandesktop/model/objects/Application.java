package pt.ul.fc.css.thesismandesktop.model.objects;

import javafx.beans.property.LongProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Application {

  private final LongProperty id = new SimpleLongProperty();
  private final ObjectProperty<String> date = new SimpleObjectProperty<>();
  private final ObjectProperty<ApplicationState> state = new SimpleObjectProperty<>();
  private final ObjectProperty<Theme> theme = new SimpleObjectProperty<>();
  private final ObjectProperty<Student> student = new SimpleObjectProperty<>();

  public ObjectProperty<String> dateProperty() {
    return this.date;
  }
  public final String getDate() {
    return dateProperty().get();
  }
  public final void setDate(String date) {
    dateProperty().set(date);
  }

  public ObjectProperty<ApplicationState> stateProperty() {
    return this.state;
  }
  public final ApplicationState getState() {
    return stateProperty().get();
  }
  public final void setState(ApplicationState state) {
    stateProperty().set(state);
  }

  public ObjectProperty<Theme> themeProperty() {
    return this.theme;
  }
  public final Theme getTheme() {
    return themeProperty().get();
  }
  public final void setTheme(Theme theme) {
    themeProperty().set(theme);
  }

  public ObjectProperty<Student> studentProperty() {
    return this.student;
  }
  public final Student getStudent() {
    return studentProperty().get();
  }
  public final void setStudent(Student student) {
    studentProperty().set(student);
  }

  public LongProperty idProperty() {
    return this.id;
  }

  public final long getId(){
    return idProperty().get();
  }

  public final void setId(long id) {
    idProperty().set(id);
  }

}
