package pt.ul.fc.css.thesisman.business.services.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import pt.ul.fc.css.thesisman.business.entities.Teacher;

public record TeacherDTO(Long id, @NotBlank @Size(min = 1, max = 50) String username, @NotBlank @Size(min = 1, max = 30) String name) implements IUserDTO  {

  public static TeacherDTO fromTeacher(Teacher teacher) {
    if (teacher == null)
      return null;
    return new TeacherDTO(teacher.getId(), teacher.getUsername(), teacher.getName());
  }
}
