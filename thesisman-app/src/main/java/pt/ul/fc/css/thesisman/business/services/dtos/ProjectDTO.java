package pt.ul.fc.css.thesisman.business.services.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pt.ul.fc.css.thesisman.business.entities.Project;

import java.util.List;

public record ProjectDTO(Long id, @NotBlank @Size(min = 1, max = 8) String year, @NotBlank @Size(min = 1, max = 100) String title, @Size(min = 1, max = 300) String description, @NotNull List<MastersDegreeDTO> compatibleMasters, @NotNull TeacherDTO advisor, @NotBlank @Schema(type = "string", allowableValues = { "AVAILABLE", "TAKEN" }) String state, float remuneration, @NotNull WorkerDTO worker) implements IThemeDTO {

  public static ProjectDTO fromProject(Project project) {
    if (project == null)
      return null;
    Long id = project.getId();
    String year = project.getYear();
    String title = project.getTitle();
    String description = project.getDescription();
    List<MastersDegreeDTO> compatibleMasters = project.getCompatibleMasters().stream().map(MastersDegreeDTO::fromMastersDegree).toList();
    TeacherDTO advisor = TeacherDTO.fromTeacher(project.getAdvisor());
    String state = project.getState().toString();
    float remuneration = project.getRemuneration();
    WorkerDTO worker = WorkerDTO.fromWorker(project.getEnterpriseAdvisor());
    return new ProjectDTO(id, year, title, description, compatibleMasters, advisor, state, remuneration, worker);
  }

}
