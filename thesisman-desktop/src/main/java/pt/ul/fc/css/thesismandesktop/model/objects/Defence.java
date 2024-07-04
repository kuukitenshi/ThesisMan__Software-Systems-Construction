package pt.ul.fc.css.thesismandesktop.model.objects;

import javafx.beans.property.*;

import java.util.Date;

public class Defence {

  private final LongProperty id = new SimpleLongProperty();
  private final FloatProperty grade = new SimpleFloatProperty();
  private final ObjectProperty<Date> dateTime = new SimpleObjectProperty<>();
  private final IntegerProperty duration = new SimpleIntegerProperty();
  private final ObjectProperty<DefenceType> type = new SimpleObjectProperty<>();
  private final ObjectProperty<Teacher> arguer = new SimpleObjectProperty<>();
  private final ObjectProperty<Teacher> president = new SimpleObjectProperty<>();
  private final ObjectProperty<ClassRoom> classRoom = new SimpleObjectProperty<>();

  public LongProperty idProperty() {
    return this.id;
  }
  public final long getId() {
    return idProperty().get();
  }
  public final void setId(long id) {
    idProperty().set(id);
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

  public ObjectProperty<Date> dateTimeProperty() {
    return this.dateTime;
  }
  public final Date getDateTime() {
    return dateTimeProperty().get();
  }
  public final void setDateTime(Date dateTime) {
    dateTimeProperty().set(dateTime);
  }

  public IntegerProperty durationProperty() {
    return this.duration;
  }
  public final int getDuration() {
    return durationProperty().get();
  }
  public final void setDuration(int duration) {
    durationProperty().set(duration);
  }

  public ObjectProperty<DefenceType> typeProperty() {
    return this.type;
  }
  public final DefenceType getType() {
    return typeProperty().get();
  }
  public final void setType(DefenceType type) {
    typeProperty().set(type);
  }

  public ObjectProperty<Teacher> arguerProperty() {
    return this.arguer;
  }
  public final Teacher getArguer() {
    return arguerProperty().get();
  }
  public final void setArguer(Teacher arguer) {
    arguerProperty().set(arguer);
  }

  public ObjectProperty<Teacher> presidentProperty() {
    return this.president;
  }
  public final Teacher getPresident() {
    return presidentProperty().get();
  }
  public final void setPresident(Teacher president) {
    presidentProperty().set(president);
  }

  public ObjectProperty<ClassRoom> classRoomProperty() {
    return classRoom;
  }

  public ClassRoom getClassRoom() {
    return classRoomProperty().get();
  }

  public void setClassRoom(ClassRoom classRoom) {
    classRoomProperty().set(classRoom);
  }
}
