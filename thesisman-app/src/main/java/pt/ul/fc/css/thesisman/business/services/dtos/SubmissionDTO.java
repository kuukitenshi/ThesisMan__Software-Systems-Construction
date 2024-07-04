package pt.ul.fc.css.thesisman.business.services.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pt.ul.fc.css.thesisman.business.entities.Submission;

import java.time.LocalDateTime;

public record SubmissionDTO(Long id, @NotNull LocalDateTime date, @NotNull byte[] bytes, @NotBlank @Size(min = 1, max = 80) String filename, @NotBlank @Schema(type = "string", allowableValues = { "PROPOSAL", "FINAL" }) String type, @NotNull DefenceDTO defence) {

  public static SubmissionDTO fromSubmission(Submission submission) {
    if (submission == null)
      return null;
    Long id = submission.getId();
    LocalDateTime dateTime = submission.getDate();
    byte[] bytes = submission.getBytes();
    String filename = submission.getFilename();
    String type = submission.getType().toString();
    DefenceDTO defence = DefenceDTO.fromDefence(submission.getDefence());
    return new SubmissionDTO(id, dateTime, bytes, filename, type, defence);
  }
}
