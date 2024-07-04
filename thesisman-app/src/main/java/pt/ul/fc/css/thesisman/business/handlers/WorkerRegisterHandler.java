package pt.ul.fc.css.thesisman.business.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.Worker;
import pt.ul.fc.css.thesisman.business.exceptions.*;
import pt.ul.fc.css.thesisman.business.repositories.WorkerRepository;

@Component
public class WorkerRegisterHandler {

  @Autowired private WorkerRepository workerRepository;

  public Worker registerWorker(String username, String password, String name, String company) throws InvalidCompanyException, InvalidNameException, InvalidPasswordException, InvalidUsernameException, WorkerAlreadyExistsException {
    if (username == null || username.isBlank()) {
      throw new InvalidUsernameException();
    }
    if (password == null || password.isBlank()) {
      throw new InvalidPasswordException();
    }
    if (name == null || name.isBlank()) {
      throw new InvalidNameException();
    }
    if (company == null || company.isBlank()) {
      throw new InvalidCompanyException();
    }
    try {
      Worker worker = new Worker(username, password, name, company);
      this.workerRepository.save(worker);
      return worker;
    } catch (DataIntegrityViolationException e) {
      throw new WorkerAlreadyExistsException();
    }
  }
}
