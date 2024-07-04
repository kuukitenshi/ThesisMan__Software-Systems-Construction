package pt.ul.fc.css.thesismandesktop.model.objects;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleObjectProperty;

public class Student extends GeneralUser {

  private final ObjectProperty<MastersDegree> mastersDegree = new SimpleObjectProperty<>();
  private final FloatProperty grade = new SimpleFloatProperty();

  public ObjectProperty<MastersDegree> mastersDegreeProperty() {
    return this.mastersDegree;
  }

  public final MastersDegree getMastersDegree() {
    return mastersDegreeProperty().get();
  }

  public final void setMastersDegree(MastersDegree mastersDegree) {
    mastersDegreeProperty().set(mastersDegree);
  }

  public FloatProperty gradeProperty() {
    return this.grade;
  }

  public final float getGrade() {
    return gradeProperty().get();
  }

  public final void setGrade(float grade) {
    gradeProperty().set(grade);
  }
}
