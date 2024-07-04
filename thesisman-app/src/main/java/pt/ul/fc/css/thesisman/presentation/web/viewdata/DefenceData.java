package pt.ul.fc.css.thesisman.presentation.web.viewdata;

import pt.ul.fc.css.thesisman.business.services.dtos.DefenceDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.SubmissionDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.ThesisDTO;

public record DefenceData(DefenceDTO defence, SubmissionDTO submission, ThesisDTO thesis) { }
