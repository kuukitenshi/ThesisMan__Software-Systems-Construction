package pt.ul.fc.css.thesismandesktop.model.objects;

import javafx.beans.property.*;

import java.util.Date;

public class Submission {

  private final LongProperty id = new SimpleLongProperty();
  private final ObjectProperty<Date> date = new SimpleObjectProperty<>();
  private final ObjectProperty<byte[]> bytes = new SimpleObjectProperty<>();
  private final ObjectProperty<SubmissionType> type = new SimpleObjectProperty<>();
  private final ObjectProperty<Defence> defence = new SimpleObjectProperty<>();
  private final StringProperty filename = new SimpleStringProperty();

  public ObjectProperty<Date> dateProperty() {
    return this.date;
  }
  public final Date getDate() {
    return dateProperty().get();
  }
  public final void setDate(Date date) {
    dateProperty().set(date);
  }

  public ObjectProperty<byte[]> bytesProperty() {
    return this.bytes;
  }
  public final byte[] getBytes() {
    return bytesProperty().get();
  }
  public final void setBytes(byte[] bytes) {
    bytesProperty().set(bytes);
  }

  public ObjectProperty<SubmissionType> typeProperty() {
    return this.type;
  }
  public final SubmissionType getType() {
    return typeProperty().get();
  }
  public final void setType(SubmissionType type) {
    typeProperty().set(type);
  }

  public ObjectProperty<Defence> defenceProperty() {
    return this.defence;
  }
  public final Defence getDefence() {
    return defenceProperty().get();
  }
  public final void setDefence(Defence student) {
    defenceProperty().set(student);
  }

  public StringProperty filenameProperty() {
    return this.filename;
  }
  public final String getFilename() {
    return filenameProperty().get();
  }
  public final void setFilename(String fileName) {
    filenameProperty().set(fileName);
  }

  public LongProperty idProperty() {
    return id;
  }

  public Long getId() {
    return idProperty().get();
  }

  public final void setId(Long id) {
    idProperty().set(id);
  }
}
