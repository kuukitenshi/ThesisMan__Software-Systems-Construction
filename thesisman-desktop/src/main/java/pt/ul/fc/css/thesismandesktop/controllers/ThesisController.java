package pt.ul.fc.css.thesismandesktop.controllers;

import javafx.beans.binding.Bindings;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.stage.FileChooser;
import pt.ul.fc.css.thesismandesktop.binds.DateFormattingBinding;
import pt.ul.fc.css.thesismandesktop.factories.SubmissionCellFactory;
import pt.ul.fc.css.thesismandesktop.model.SessionModel;
import pt.ul.fc.css.thesismandesktop.model.ThesisModel;
import pt.ul.fc.css.thesismandesktop.model.objects.Student;
import pt.ul.fc.css.thesismandesktop.model.objects.Submission;
import pt.ul.fc.css.thesismandesktop.model.objects.SubmissionType;
import pt.ul.fc.css.thesismandesktop.model.objects.Thesis;
import pt.ul.fc.css.thesismandesktop.services.SubmissionService;
import pt.ul.fc.css.thesismandesktop.services.ThesisService;
import pt.ul.fc.css.thesismandesktop.services.exceptions.HttpRequestException;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.ResourceBundle;

public class ThesisController implements Initializable {

  private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy HH:mm");

  @FXML private Label titleThemeLabel;
  @FXML private Label descriptionLabel;
  @FXML private Label advisorLabel;
  @FXML private Label advisorLabelInput;
  @FXML private Label enterpriseAdvisorLabel;
  @FXML private Label enterpriseAdvisorLabelInput;
  @FXML private Label remunerationLabel;
  @FXML private Label typeThemeLabel;
  @FXML private Button submitFinalBtn;
  @FXML private Button submitProposalBtn;
  @FXML private Label fileNameFinalLabel;
  @FXML private Label fileNameFinalLabelInput;
  @FXML private Label dateFinalFileLabel;
  @FXML private Label noValueFinalSubmission;
  @FXML private Label noValueProposeSubmission;
  @FXML private Label noValueThesis;
  @FXML private Label errorFinal;
  @FXML private Label errorProposal;

  @FXML
  private ListView<Submission> listViewProposals;
  private final FileChooser fileChooser = new FileChooser();
  private final ThesisService thesisService = new ThesisService();
  private final SubmissionService submissionService = new SubmissionService();
  private final ThesisModel model = new ThesisModel();

  @FXML
  public void submitProposal(ActionEvent event) {
    model.proposalErrorProperty().set(false);
    File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
    if (file == null) {
      return;
    }
    try {
      Thesis thesis = model.currentThesisProperty().get();
      Submission submission = this.submissionService.submitProposeDocument(thesis.getId(), file);
      model.getProposals().add(submission);
    } catch (HttpRequestException e) {
      e.printStackTrace();
      model.proposalErrorProperty().set(true);
    }
  }

  @FXML
  public void submitFinal(ActionEvent event) {
    model.finalErrorProperty().set(false);
    File file = fileChooser.showOpenDialog(((Node) event.getSource()).getScene().getWindow());
    if (file == null) {
      return;
    }
    try {
      Thesis thesis = model.currentThesisProperty().get();
      Submission submission = this.submissionService.submitFinalDocument(thesis.getId(), file);
      model.finalSubmissionProperty().set(submission);
    } catch (HttpRequestException e) {
      model.finalErrorProperty().set(true);
    }
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    setupThemeBindings();
    setupNoValueBindings();
    setupErrorBindings();
    setupFinalBindings();
    setupProposalBindings();
    Student student = SessionModel.getInstance().studentProperty().get();
    listViewProposals.setCellFactory(new SubmissionCellFactory());
    listViewProposals.setItems(model.getProposals());
    try {
      Thesis thesis = this.thesisService.getThesisFromStudent(student.getId());
      model.currentThesisProperty().set(thesis);
      List<Submission> submissions = thesis.getSubmissions();
      model.getProposals().addAll(submissions.stream().filter(s -> s.typeProperty().get() != SubmissionType.FINAL).toList());
      model.finalSubmissionProperty().set(submissions.stream().filter(s -> s.typeProperty().get() == SubmissionType.FINAL).findFirst().orElse(null));
    } catch (HttpRequestException e) { }
  }

  private void setupProposalBindings() {
    submitProposalBtn.visibleProperty().bind(Bindings.isNotNull(model.currentThesisProperty()));
  }

  private void setupFinalBindings() {
    submitFinalBtn.visibleProperty().bind(
            Bindings.isNull(model.finalSubmissionProperty())
                    .and(Bindings.isNotNull(model.currentThesisProperty())));
    fileNameFinalLabel.visibleProperty().bind(Bindings.isNotNull(model.finalSubmissionProperty()));
    fileNameFinalLabelInput.visibleProperty().bind(Bindings.isNotNull(model.finalSubmissionProperty()));
    dateFinalFileLabel.visibleProperty().bind(Bindings.isNotNull(model.finalSubmissionProperty()));

    fileNameFinalLabelInput.textProperty().bind(Bindings.select(model.finalSubmissionProperty(), "filename"));
    DateFormattingBinding dateBinding = new DateFormattingBinding(Bindings.select(model.finalSubmissionProperty(), "date"), DATE_FORMATTER);
    dateFinalFileLabel.textProperty().bind(dateBinding);
//    model.finalSubmissionProperty().addListener((observable, oldValue, newValue) -> {
//      SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
//      dateFinalFileLabel.textProperty().set(formatter.format(model.finalSubmissionProperty().get()));
//    });
////    dateFinalFileLabel.textProperty().bind(
////            Bindings.date
////            Bindings.selectString(model.finalSubmissionProperty(), "date")
////    );
  }

  private void setupErrorBindings() {
    errorFinal.visibleProperty().bind(model.finalErrorProperty());
    errorProposal.visibleProperty().bind(model.proposalErrorProperty());
  }

  private void setupNoValueBindings() {
    noValueThesis.visibleProperty().bind(Bindings.isNull(model.currentThesisProperty()));
    noValueFinalSubmission.visibleProperty().bind(Bindings.isNull(model.finalSubmissionProperty()));
    noValueProposeSubmission.visibleProperty().bind(Bindings.isEmpty(model.getProposals()));
  }

  private void setupThemeBindings() {
    titleThemeLabel.visibleProperty().bind(Bindings.isNotNull(model.currentThesisProperty()));
    descriptionLabel.visibleProperty().bind(Bindings.isNotNull(model.currentThesisProperty()));
    advisorLabel.visibleProperty().bind(Bindings.isNotNull(model.currentThesisProperty()));
    advisorLabelInput.visibleProperty().bind(Bindings.isNotNull(model.currentThesisProperty()));
    enterpriseAdvisorLabel.visibleProperty().bind(Bindings.isNotNull(Bindings.select(model.currentThesisProperty(), "theme", "worker")));
    enterpriseAdvisorLabelInput.visibleProperty().bind(enterpriseAdvisorLabel.visibleProperty());
    remunerationLabel.visibleProperty().bind(Bindings.isNotNull(model.currentThesisProperty()));
    typeThemeLabel.visibleProperty().bind(Bindings.isNotNull(model.currentThesisProperty()));

    titleThemeLabel.textProperty().bind(Bindings.selectString(model.currentThesisProperty(), "theme", "title"));
    descriptionLabel.textProperty().bind(Bindings.selectString(model.currentThesisProperty(), "theme", "description"));
    advisorLabelInput.textProperty().bind(Bindings.selectString(model.currentThesisProperty(), "theme", "advisor", "name"));
    enterpriseAdvisorLabelInput.textProperty().bind(Bindings.select(model.currentThesisProperty(), "theme", "worker", "name"));
    remunerationLabel.textProperty().bind(
            Bindings.concat(Bindings.selectString(model.currentThesisProperty(), "theme", "remuneration"), " €/month")
    );
    typeThemeLabel.textProperty()
            .bind(Bindings.when(enterpriseAdvisorLabel.visibleProperty()).then("PROJECT").otherwise("DISSERTATION"));
  }
}
