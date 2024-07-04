package pt.ul.fc.css.thesisman.business.services.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pt.ul.fc.css.thesisman.business.entities.Application;

import java.time.LocalDate;

public record ApplicationDTO(Long id, @NotNull LocalDate date, @NotNull StudentDTO student, @NotNull IThemeDTO theme, @NotBlank @Schema(type = "string", allowableValues = { "ACCEPTED", "REJECTED", "SUBMITTED" }) String state) {

  public static ApplicationDTO fromApplication(Application application) {
    if (application == null)
      return null;
    Long id = application.getId();
    LocalDate date = application.getDate();
    StudentDTO student = StudentDTO.fromStudent(application.getStudent());
    IThemeDTO theme = IThemeDTO.fromTheme(application.getTheme());
    String state = application.getState().toString();
    return new ApplicationDTO(id, date, student, theme, state);
  }

}
