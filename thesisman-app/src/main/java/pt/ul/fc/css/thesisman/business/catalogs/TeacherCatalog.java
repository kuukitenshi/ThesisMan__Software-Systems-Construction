package pt.ul.fc.css.thesisman.business.catalogs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.Teacher;
import pt.ul.fc.css.thesisman.business.repositories.TeacherRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherCatalog {

  @Autowired private TeacherRepository teacherRepository;

  public List<Teacher> getTeachers() {
    List<Teacher> teachers = new ArrayList<>();
    this.teacherRepository.findAll().forEach(teachers::add);
    return teachers;
  }

}
