package pt.ul.fc.css.thesisman.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ul.fc.css.thesisman.business.catalogs.TeacherCatalog;
import pt.ul.fc.css.thesisman.business.services.dtos.TeacherDTO;

import java.util.List;

@Service
public class TeacherService {

  @Autowired private TeacherCatalog teacherCatalog;

  public List<TeacherDTO> getTeachers() {
    return this.teacherCatalog.getTeachers().stream().map(TeacherDTO::fromTeacher).toList();
  }

}
