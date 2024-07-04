package pt.ul.fc.css.thesisman.business.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.Student;
import pt.ul.fc.css.thesisman.business.entities.Teacher;
import pt.ul.fc.css.thesisman.business.exceptions.InvalidPasswordException;
import pt.ul.fc.css.thesisman.business.exceptions.InvalidUsernameException;
import pt.ul.fc.css.thesisman.business.exceptions.StudentNotFoundException;
import pt.ul.fc.css.thesisman.business.exceptions.TeacherNotFoundException;
import pt.ul.fc.css.thesisman.business.repositories.StudentRepository;
import pt.ul.fc.css.thesisman.business.repositories.TeacherRepository;

import java.util.Optional;

@Component
public class UniversityLoginHandler {

  @Autowired private StudentRepository studentRepository;
  @Autowired private TeacherRepository teacherRepository;

  public Teacher authenticateTeacher(String username, String password) throws InvalidUsernameException, InvalidPasswordException, TeacherNotFoundException {
    if (username == null || username.isBlank()) {
      throw new InvalidUsernameException();
    }
    if (password == null || password.isBlank()) {
      throw new InvalidPasswordException();
    }
    Optional<Teacher> teacherOpt = this.teacherRepository.findByUsername(username);
    if (teacherOpt.isEmpty()) {
      throw new TeacherNotFoundException();
    }
    return teacherOpt.get();
  }

  public Student authenticateStudent(String username, String password) throws InvalidUsernameException, InvalidPasswordException, StudentNotFoundException {
    if (username == null || username.isBlank()) {
      throw new InvalidUsernameException();
    }
    if (password == null || password.isBlank()) {
      throw new InvalidPasswordException();
    }
    Optional<Student> studentOpt = this.studentRepository.findByUsername(username);
    if (studentOpt.isEmpty()) {
      throw new StudentNotFoundException();
    }
    return studentOpt.get();
  }
}
