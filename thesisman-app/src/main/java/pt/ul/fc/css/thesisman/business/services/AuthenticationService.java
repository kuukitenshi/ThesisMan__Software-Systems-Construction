package pt.ul.fc.css.thesisman.business.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pt.ul.fc.css.thesisman.business.entities.Student;
import pt.ul.fc.css.thesisman.business.entities.Teacher;
import pt.ul.fc.css.thesisman.business.entities.Worker;
import pt.ul.fc.css.thesisman.business.exceptions.*;
import pt.ul.fc.css.thesisman.business.handlers.UniversityLoginHandler;
import pt.ul.fc.css.thesisman.business.handlers.WorkerLoginHandler;
import pt.ul.fc.css.thesisman.business.handlers.WorkerRegisterHandler;
import pt.ul.fc.css.thesisman.business.services.dtos.IUserDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.StudentDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.TeacherDTO;
import pt.ul.fc.css.thesisman.business.services.dtos.WorkerDTO;
import pt.ul.fc.css.thesisman.business.services.exceptions.AlreadyExistsException;
import pt.ul.fc.css.thesisman.business.services.exceptions.InvalidFieldException;
import pt.ul.fc.css.thesisman.business.services.exceptions.NotFoundException;

@Service
public class AuthenticationService {

  @Autowired private UniversityLoginHandler userAuthHandler;
  @Autowired private WorkerLoginHandler workerLoginHandler;
  @Autowired private WorkerRegisterHandler workerRegisterHandler;

  public IUserDTO authenticate(String username, String password) throws NotFoundException, InvalidFieldException {
    try {
      try {
        Teacher teacher = this.userAuthHandler.authenticateTeacher(username, password);
        return TeacherDTO.fromTeacher(teacher);
      } catch (TeacherNotFoundException e) {
        try {
          Student student = this.userAuthHandler.authenticateStudent(username, password);
          return StudentDTO.fromStudent(student);
        } catch (StudentNotFoundException e2) {
          try {
            Worker worker = this.workerLoginHandler.authenticateWorker(username, password);
            return WorkerDTO.fromWorker(worker);
          } catch (WorkerNotFoundException e3) {
            throw new NotFoundException("No user found with username " + username + "!");
          }
        }
      }
    } catch (InvalidPasswordException e) {
      throw new InvalidFieldException("Invalid password used!", e);
    } catch (InvalidUsernameException e) {
      throw new InvalidFieldException("Invalid username used!", e);
    }
  }

  public WorkerDTO registerWorker(String username, String password, String name, String company) throws InvalidFieldException, AlreadyExistsException {
    try {
      Worker worker = this.workerRegisterHandler.registerWorker(username, password, name, company);
      return WorkerDTO.fromWorker(worker);
    } catch (InvalidCompanyException e) {
      throw new InvalidFieldException("Company is invalid!", e);
    } catch (InvalidNameException e) {
      throw new InvalidFieldException("Name is invalid!", e);
    } catch (InvalidPasswordException e) {
      throw new InvalidFieldException("Password is invalid!", e);
    } catch (InvalidUsernameException e) {
      throw new InvalidFieldException("Username is invalid!", e);
    } catch (WorkerAlreadyExistsException e) {
      throw new AlreadyExistsException("There is already a worker registered with that username!");
    }
  }
}
