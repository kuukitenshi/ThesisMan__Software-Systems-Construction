package pt.ul.fc.css.thesisman.business.services.dtos;

import jakarta.validation.constraints.NotNull;
import pt.ul.fc.css.thesisman.business.entities.Thesis;

import java.util.List;

public record ThesisDTO(Long id, @NotNull IThemeDTO theme, @NotNull List<SubmissionDTO> submissions, @NotNull StudentDTO student, Float finalGrade) {

  public static ThesisDTO fromThesis(Thesis thesis) {
    if (thesis == null)
      return null;
    Long id = thesis.getId();
    IThemeDTO theme = IThemeDTO.fromTheme(thesis.getTheme());
    List<SubmissionDTO> submissions = thesis.getSubmissions().stream().map(SubmissionDTO::fromSubmission).toList();
    StudentDTO student = StudentDTO.fromStudent(thesis.getStudent());
    Float finalGrade = thesis.getFinalGrade();
    return new ThesisDTO(id, theme, submissions, student, finalGrade);
  }
}
