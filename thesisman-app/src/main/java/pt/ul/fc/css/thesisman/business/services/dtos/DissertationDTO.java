package pt.ul.fc.css.thesisman.business.services.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pt.ul.fc.css.thesisman.business.entities.Dissertation;

import java.util.List;

public record DissertationDTO(Long id, @NotBlank @Size(min = 1, max = 8) String year, @NotBlank @Size(min = 1, max = 100) String title, @Size(min = 1, max = 300) String description, @NotNull List<MastersDegreeDTO> compatibleMasters, @NotNull TeacherDTO advisor, @NotBlank @Schema(type = "string", allowableValues = { "AVAILABLE", "TAKEN" }) String state, float remuneration) implements IThemeDTO {

  public static DissertationDTO fromDissertation(Dissertation dissertation) {
    if (dissertation == null)
      return null;
    Long id = dissertation.getId();
    String year = dissertation.getYear();
    String title = dissertation.getTitle();
    String description = dissertation.getDescription();
    List<MastersDegreeDTO> compatibleMasters= dissertation.getCompatibleMasters().stream().map(MastersDegreeDTO::fromMastersDegree).toList();
    TeacherDTO advisor = TeacherDTO.fromTeacher(dissertation.getAdvisor());
    String state = dissertation.getState().toString();
    float remuneration = dissertation.getRemuneration();
    return new DissertationDTO(id, year, title, description, compatibleMasters, advisor, state, remuneration);
  }
}
