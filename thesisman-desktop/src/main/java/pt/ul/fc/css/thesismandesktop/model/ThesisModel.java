package pt.ul.fc.css.thesismandesktop.model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import pt.ul.fc.css.thesismandesktop.model.objects.Submission;
import pt.ul.fc.css.thesismandesktop.model.objects.Thesis;

public class ThesisModel {

  private final ObservableList<Submission> proposals = FXCollections.observableArrayList();
  private final ObjectProperty<Submission> finalSubmission = new SimpleObjectProperty<>();
  private final ObjectProperty<Thesis> currentThesis = new SimpleObjectProperty<>();
  private final BooleanProperty proposalError = new SimpleBooleanProperty();
  private final BooleanProperty finalError = new SimpleBooleanProperty();

  public ObservableList<Submission> getProposals() {
    return proposals;
  }

  public ObjectProperty<Submission> finalSubmissionProperty() {
    return finalSubmission;
  }

  public ObjectProperty<Thesis> currentThesisProperty() {
    return currentThesis;
  }


  public BooleanProperty proposalErrorProperty() {
    return proposalError;
  }

  public BooleanProperty finalErrorProperty() {
    return finalError;
  }
}
