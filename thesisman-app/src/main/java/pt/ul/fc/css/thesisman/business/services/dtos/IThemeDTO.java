package pt.ul.fc.css.thesisman.business.services.dtos;

import pt.ul.fc.css.thesisman.business.entities.Dissertation;
import pt.ul.fc.css.thesisman.business.entities.Project;
import pt.ul.fc.css.thesisman.business.entities.Theme;

import java.util.List;

public interface IThemeDTO {

  Long id();

  String year();

  String title();

  String description();

  List<MastersDegreeDTO> compatibleMasters();

  TeacherDTO advisor();

  String state();

  float remuneration();

  static IThemeDTO fromTheme(Theme theme) {
    if (theme == null) {
      return null;
    }
    if (theme instanceof Project project) {
      return ProjectDTO.fromProject(project);
    }
    if (theme instanceof Dissertation dissertation) {
      return DissertationDTO.fromDissertation(dissertation);
    }
    throw new IllegalArgumentException("Invalid theme!");
  }
}
