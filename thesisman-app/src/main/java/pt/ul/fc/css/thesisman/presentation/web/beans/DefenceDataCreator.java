package pt.ul.fc.css.thesisman.presentation.web.beans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.services.SubmissionService;
import pt.ul.fc.css.thesisman.business.services.ThesisService;
import pt.ul.fc.css.thesisman.business.services.dtos.DefenceDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.SubmissionDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.ThesisDTO;
import pt.ul.fc.css.thesisman.presentation.web.viewdata.DefenceData;

import java.util.Optional;

@Component
public class DefenceDataCreator {

  @Autowired private SubmissionService submissionService;
  @Autowired private ThesisService thesisService;

  public Optional<DefenceData> createDataFromDefence(DefenceDTO defence) {
    Optional<SubmissionDTO> submissionOpt = this.submissionService.getByDefenceId(defence.id());
    if (submissionOpt.isEmpty())
      return Optional.empty();
    SubmissionDTO submission = submissionOpt.get();
    Optional<ThesisDTO> thesisOpt = this.thesisService.getBySubmissionId(submission.id());
    if (thesisOpt.isEmpty())
      return Optional.empty();
    ThesisDTO thesis = thesisOpt.get();
    return Optional.of(new DefenceData(defence, submission, thesis));
  }
}
