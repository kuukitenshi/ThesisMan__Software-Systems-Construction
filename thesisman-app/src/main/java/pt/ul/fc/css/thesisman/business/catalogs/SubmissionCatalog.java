package pt.ul.fc.css.thesisman.business.catalogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.Submission;
import pt.ul.fc.css.thesisman.business.repositories.SubmissionRepository;

import java.util.Optional;

@Component
public class SubmissionCatalog {

  @Autowired private SubmissionRepository submissionRepository;

  public Optional<Submission> getById(Long submissionId) {
    return this.submissionRepository.findById(submissionId);
  }

  public Optional<Submission> getByDefenceId(Long defenceId) {
    return this.submissionRepository.findSubmissionByDefenceId(defenceId);
  }

}
