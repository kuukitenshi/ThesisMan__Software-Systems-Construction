package pt.ul.fc.css.thesisman.business.services.dtos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import pt.ul.fc.css.thesisman.business.entities.Defence;

import java.time.LocalDateTime;

public record DefenceDTO(Long id, Float grade, @NotNull LocalDateTime dateTime, int duration, @NotBlank @Schema(type = "string", allowableValues = { "REMOTE", "IN_PERSON" }) String type, @NotNull TeacherDTO arguer, @NotNull TeacherDTO president, @NotNull ClassRoomDTO classRoom) {

  public static DefenceDTO fromDefence(Defence defence) {
    if (defence == null)
      return null;
    Long id = defence.getId();
    Float grade = defence.getGrade();
    LocalDateTime dateTime = defence.getDateTime();
    int duration = defence.getDuration();
    String type = defence.getType().toString();
    TeacherDTO arguer = TeacherDTO.fromTeacher(defence.getArguer());
    TeacherDTO president = TeacherDTO.fromTeacher(defence.getPresident());
    ClassRoomDTO classRoom = ClassRoomDTO.fromClassRoom(defence.getClassRoom());
    return new DefenceDTO(id, grade, dateTime, duration, type, arguer, president, classRoom);
  }
}
