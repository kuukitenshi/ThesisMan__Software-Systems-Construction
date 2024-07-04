package pt.ul.fc.css.thesismandesktop.model.objects;

import javafx.beans.property.*;

import java.util.List;

public class Theme {

  private final LongProperty id = new SimpleLongProperty();
  private final StringProperty year = new SimpleStringProperty();
  private final StringProperty title = new SimpleStringProperty();
  private final StringProperty description = new SimpleStringProperty();
  private final ObjectProperty<List<MastersDegree>> compatibleMasters = new SimpleObjectProperty<>();
  private final ObjectProperty<Teacher> advisor = new SimpleObjectProperty<>();
  private final ObjectProperty<ThemeState> state = new SimpleObjectProperty<>();
  private final FloatProperty remuneration = new SimpleFloatProperty();
  private final ObjectProperty<Worker> worker = new SimpleObjectProperty<>();
  private final StringProperty type = new SimpleStringProperty();

  public LongProperty idProperty() {
    return this.id;
  }
  public final long getId() {
    return idProperty().get();
  }
  public final void setId(long id) {
    idProperty().set(id);
  }

  public StringProperty typeProperty() {
    return type;
  }

  public String getType() {
    return type.get();
  }

  public void setType(String type) {
    this.type.set(type);
  }

  public StringProperty yearProperty() {
    return this.year;
  }

  public final String getYear() {
    return yearProperty().get();
  }

  public final void setYear(String year) {
    yearProperty().set(year);
  }

  public StringProperty titleProperty() {
    return this.title;
  }

  public final String getTitle() {
    return titleProperty().get();
  }

  public final void setTitle(String title) {
    titleProperty().set(title);
  }

  public StringProperty descriptionProperty() {
    return this.description;
  }

  public final String getDescription() {
    return descriptionProperty().get();
  }

  public final void setDescription(String description) {
    descriptionProperty().set(description);
  }

  public ObjectProperty<List<MastersDegree>> compatibleMastersProperty() {
    return this.compatibleMasters;
  }

  public final List<MastersDegree> getCompatibleMasters() {
    return compatibleMastersProperty().get();
  }

  public final void setCompatibleMasters(List<MastersDegree> compatibleMasters) {
    compatibleMastersProperty().set(compatibleMasters);
  }

  public ObjectProperty<Teacher> advisorProperty() {
    return this.advisor;
  }

  public final Teacher getAdvisor() {
    return advisorProperty().get();
  }

  public final void setAdvisor(Teacher advisor) {
    advisorProperty().set(advisor);
  }

  public ObjectProperty<ThemeState> stateProperty() {
    return this.state;
  }

  public final ThemeState getState() {
    return stateProperty().get();
  }

  public final void setState(ThemeState state) {
    stateProperty().set(state);
  }

  public FloatProperty remunerationProperty() {
    return this.remuneration;
  }

  public final float getRemuneration() {
    return remunerationProperty().get();
  }

  public final void setRemuneration(float remuneration) {
    remunerationProperty().set(remuneration);
  }

  public ObjectProperty<Worker> workerProperty() {
    return this.worker;
  }

  public final Worker getWorker() {
    return workerProperty().get();
  }

  public final void setWorker(Worker worker) {
    workerProperty().set(worker);
  }
}
