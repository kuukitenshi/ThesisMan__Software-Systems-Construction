package pt.ul.fc.css.thesisman.business.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pt.ul.fc.css.thesisman.business.entities.Defence;
import pt.ul.fc.css.thesisman.business.entities.Submission;
import pt.ul.fc.css.thesisman.business.enums.SubmissionType;
import pt.ul.fc.css.thesisman.business.exceptions.DefenceNotFoundException;
import pt.ul.fc.css.thesisman.business.exceptions.InvalidGradeException;
import pt.ul.fc.css.thesisman.business.exceptions.InvalidSubmissionTypeException;
import pt.ul.fc.css.thesisman.business.exceptions.SubmissionNotFoundException;
import pt.ul.fc.css.thesisman.business.repositories.DefenceRepository;
import pt.ul.fc.css.thesisman.business.repositories.SubmissionRepository;

import java.util.Optional;

@Component
public class GradeProposalDefenceHandler {

  @Autowired private DefenceRepository defenceRepository;
  @Autowired private SubmissionRepository submissionRepository;

  @Transactional
  public void gradeProposalDefence(Long defenceId, Float grade) throws InvalidGradeException, DefenceNotFoundException, SubmissionNotFoundException, InvalidSubmissionTypeException {
    if (grade == null || grade < 0 || grade > 20) {
      throw new InvalidGradeException();
    }
    Optional<Defence> defenceOpt = this.defenceRepository.findById(defenceId);
    if (defenceOpt.isEmpty()) {
      throw new DefenceNotFoundException();
    }
    Optional<Submission> submissionOpt = this.submissionRepository.findSubmissionByDefenceId(defenceId);
    if (submissionOpt.isEmpty()) {
      throw new SubmissionNotFoundException();
    }
    Submission submission = submissionOpt.get();
    if (submission.getType() != SubmissionType.PROPOSAL) {
      throw new InvalidSubmissionTypeException();
    }
    Defence defence = defenceOpt.get();
    if(defence.getGrade() != null) {
      throw new InvalidGradeException();
    }
    defence.setGrade(grade);
    this.defenceRepository.save(defence);
  }
}
