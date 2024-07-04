package pt.ul.fc.css.thesisman.business.handlers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pt.ul.fc.css.thesisman.business.entities.Worker;
import pt.ul.fc.css.thesisman.business.exceptions.InvalidPasswordException;
import pt.ul.fc.css.thesisman.business.exceptions.InvalidUsernameException;
import pt.ul.fc.css.thesisman.business.exceptions.WorkerNotFoundException;
import pt.ul.fc.css.thesisman.business.repositories.WorkerRepository;

import java.util.Optional;

@Component
public class WorkerLoginHandler {

  @Autowired private WorkerRepository workerRepository;

  public Worker authenticateWorker(String username, String password) throws InvalidUsernameException, InvalidPasswordException, WorkerNotFoundException {
    if (username == null || username.isBlank()) {
      throw new InvalidUsernameException();
    }
    if (password == null || password.isBlank()) {
      throw new InvalidPasswordException();
    }
    Optional<Worker> workerOpt = workerRepository.findByUsername(username);
    if (workerOpt.isEmpty()) {
      throw new WorkerNotFoundException();
    }
    return workerOpt.get();
  }
}
