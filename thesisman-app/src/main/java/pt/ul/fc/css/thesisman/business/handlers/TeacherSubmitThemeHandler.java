package pt.ul.fc.css.thesisman.business.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.Dissertation;
import pt.ul.fc.css.thesisman.business.entities.MastersDegree;
import pt.ul.fc.css.thesisman.business.entities.Teacher;
import pt.ul.fc.css.thesisman.business.exceptions.*;
import pt.ul.fc.css.thesisman.business.repositories.DissertationRepository;
import pt.ul.fc.css.thesisman.business.repositories.MastersRepository;
import pt.ul.fc.css.thesisman.business.repositories.TeacherRepository;

import java.util.List;
import java.util.Optional;

@Component
public class TeacherSubmitThemeHandler {

  @Autowired private DissertationRepository dissertationRepository;
  @Autowired private TeacherRepository teacherRepository;
  @Autowired private MastersRepository mastersRepository;

  public Dissertation submitDissertationTheme(String title, String description, Long advisorId, Float remuneration, List<Long> compatibleMasters) throws InvalidTitleException, InvalidDescriptionException, TeacherNotFoundException, InvalidRemunerationException, MastersDegreeNotFoundException {
    if (title == null || title.isBlank()) {
      throw new InvalidTitleException();
    }
    if (description == null || description.isBlank()) {
      throw new InvalidDescriptionException();
    }
    Optional<Teacher> teacherOpt = this.teacherRepository.findById(advisorId);
    if (teacherOpt.isEmpty()) {
      throw new TeacherNotFoundException();
    }
    Teacher teacher = teacherOpt.get();
    if (remuneration == null || remuneration <= 0.0f) {
      throw new InvalidRemunerationException();
    }
    List<MastersDegree> masters = compatibleMasters.stream()
            .map(this.mastersRepository::findById)
            .filter(Optional::isPresent)
            .map(Optional::get)
            .toList();
    if (masters.size() != compatibleMasters.size()) {
      throw new MastersDegreeNotFoundException();
    }
    Dissertation dissertation = new Dissertation(title, teacher);
    dissertation.setDescription(description);
    dissertation.setRemuneration(remuneration);
    masters.forEach(dissertation::addCompatibleMasters);
    this.dissertationRepository.save(dissertation);
    return dissertation;
  }
}
