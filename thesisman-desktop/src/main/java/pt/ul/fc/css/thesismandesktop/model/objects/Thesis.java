package pt.ul.fc.css.thesismandesktop.model.objects;

import javafx.beans.property.*;

import java.util.List;

public class Thesis {

  private final LongProperty id = new SimpleLongProperty();
  private final FloatProperty finalGrade = new SimpleFloatProperty();
  private final ObjectProperty<Student> student = new SimpleObjectProperty<>();
  private final ObjectProperty<Theme> theme = new SimpleObjectProperty<>();
  private final ObjectProperty<List<Submission>> submissions = new SimpleObjectProperty<>();

  public FloatProperty finalGradeProperty() {
    return finalGrade;
  }

  public void setFinalGrade(float grade) {
    this.finalGrade.set(grade);
  }

  public float getFinalGrade() {
    return finalGrade.get();
  }

  public ObjectProperty<Student> studentProperty() {
    return student;
  }

  public void setStudent(Student student) {
    this.student.set(student);
  }

  public Student getStudent() {
    return student.get();
  }

  public ObjectProperty<Theme> themeProperty() {
    return theme;
  }

  public void setTheme(Theme theme) {
    this.theme.set(theme);
  }

  public Theme getTheme() {
    return theme.get();
  }

  public ObjectProperty<List<Submission>> submissionsProperty() {
    return submissions;
  }

  public void setSubmissions(List<Submission> submissions) {
    this.submissions.set(submissions);
  }

  public List<Submission> getSubmissions() {
    return submissions.get();
  }

  public LongProperty idProperty() {
    return id;
  }

  public void setId(long id) {
    this.id.set(id);
  }

  public long getId() {
    return id.get();
  }

}
