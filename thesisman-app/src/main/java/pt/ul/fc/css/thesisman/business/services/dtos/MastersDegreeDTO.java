package pt.ul.fc.css.thesisman.business.services.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pt.ul.fc.css.thesisman.business.entities.MastersDegree;

public record MastersDegreeDTO(Long id, @NotBlank @Size(min = 1, max = 50) String name, @NotNull TeacherDTO admin) {

  public static MastersDegreeDTO fromMastersDegree(MastersDegree mastersDegree) {
    if (mastersDegree == null)
      return null;
    Long id = mastersDegree.getId();
    String name = mastersDegree.getName();
    TeacherDTO admin = TeacherDTO.fromTeacher(mastersDegree.getAdmin());
    return new MastersDegreeDTO(id, name, admin);
  }
}
