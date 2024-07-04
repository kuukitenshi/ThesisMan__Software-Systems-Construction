package pt.ul.fc.css.thesisman.business.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.MastersDegree;
import pt.ul.fc.css.thesisman.business.entities.Project;
import pt.ul.fc.css.thesisman.business.entities.Teacher;
import pt.ul.fc.css.thesisman.business.entities.Worker;
import pt.ul.fc.css.thesisman.business.exceptions.*;
import pt.ul.fc.css.thesisman.business.repositories.MastersRepository;
import pt.ul.fc.css.thesisman.business.repositories.ProjectRepository;
import pt.ul.fc.css.thesisman.business.repositories.TeacherRepository;
import pt.ul.fc.css.thesisman.business.repositories.WorkerRepository;

import java.util.List;
import java.util.Optional;

@Component
public class WorkerSubmitThemeHandler {

  @Autowired private ProjectRepository projectRepository;
  @Autowired private WorkerRepository workerRepository;
  @Autowired private TeacherRepository teacherRepository;
  @Autowired private MastersRepository mastersRepository;

  public Project submitProjectTheme(String title, String description, Long advisorId, Float remuneration, List<Long> compatibleMasters, Long workerId) throws InvalidDescriptionException, InvalidTitleException, TeacherNotFoundException, InvalidRemunerationException, MastersDegreeNotFoundException, WorkerNotFoundException {
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
    Teacher advisor = teacherOpt.get();
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
    Optional<Worker> workerOpt = this.workerRepository.findById(workerId);
    if (workerOpt.isEmpty()) {
      throw new WorkerNotFoundException();
    }
    Worker worker = workerOpt.get();
    Project project = new Project(title, advisor, worker);
    project.setDescription(description);
    project.setRemuneration(remuneration);
    masters.forEach(project::addCompatibleMasters);
    this.projectRepository.save(project);
    return project;
  }
}
