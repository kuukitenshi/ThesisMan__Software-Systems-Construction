package pt.ul.fc.css.thesisman.business.services.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import pt.ul.fc.css.thesisman.business.entities.Student;

public record StudentDTO(Long id, @NotBlank @Size(min = 1, max = 50) String username, @NotBlank @Size(min = 1, max = 30) String name, @NotNull MastersDegreeDTO mastersDegree, Float grade, @NotBlank @Size(min = 1, max = 50) String password) implements IUserDTO {

  public static StudentDTO fromStudent(Student student) {
    if (student == null)
      return null;
    Long id = student.getId();
    String username = student.getUsername();
    String name = student.getName();
    MastersDegreeDTO mastersDegree = MastersDegreeDTO.fromMastersDegree(student.getMastersDegree());
    Float grade = student.getGrade();
    String password = student.getPassword();
    return new StudentDTO(id, username, name, mastersDegree, grade, password);
  }
}
