package pt.ul.fc.css.thesisman.business.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.ul.fc.css.thesisman.business.entities.Submission;
import pt.ul.fc.css.thesisman.business.entities.Thesis;
import pt.ul.fc.css.thesisman.business.enums.SubmissionType;
import pt.ul.fc.css.thesisman.business.exceptions.AlreadyHasFinalSubmissionException;
import pt.ul.fc.css.thesisman.business.exceptions.InvalidDocumentException;
import pt.ul.fc.css.thesisman.business.exceptions.InvalidNameException;
import pt.ul.fc.css.thesisman.business.exceptions.ThesisNotFoundException;
import pt.ul.fc.css.thesisman.business.repositories.SubmissionRepository;
import pt.ul.fc.css.thesisman.business.repositories.ThesisRepository;

import java.util.Optional;

@Component
public class SubmitFinalDocumentHandler {

  @Autowired private ThesisRepository thesisRepository;
  @Autowired private SubmissionRepository submissionRepository;

  @Transactional
  public Submission submitFinalDocument(Long thesisId, byte[] bytes, String filename) throws ThesisNotFoundException, InvalidDocumentException, AlreadyHasFinalSubmissionException, InvalidNameException {
    if (filename == null || filename.isBlank()) {
      throw new InvalidNameException();
    }
    if (bytes == null) {
      throw new InvalidDocumentException();
    }
    Optional<Thesis> thesisOpt = this.thesisRepository.findById(thesisId);
    if (thesisOpt.isEmpty()) {
      throw new ThesisNotFoundException();
    }
    Thesis thesis = thesisOpt.get();
    if (thesis.getSubmissions().stream().anyMatch(s -> s.getType() == SubmissionType.FINAL)) {
      throw new AlreadyHasFinalSubmissionException();
    }
    Submission submission = new Submission(SubmissionType.FINAL, bytes, filename);
    this.submissionRepository.save(submission);
    thesis.addSubmission(submission);
    this.thesisRepository.save(thesis);
    return submission;
  }
}
