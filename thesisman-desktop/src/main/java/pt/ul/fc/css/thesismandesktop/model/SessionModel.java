package pt.ul.fc.css.thesismandesktop.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import pt.ul.fc.css.thesismandesktop.model.objects.Student;

public class SessionModel {

  private static SessionModel instance;

  private ObjectProperty<Student> student = new SimpleObjectProperty<>();

  private SessionModel() { }

  public ObjectProperty<Student> studentProperty() {
    return student;
  }

  public static synchronized SessionModel getInstance() {
    if (instance == null)
      instance = new SessionModel();
    return instance;
  }
}
