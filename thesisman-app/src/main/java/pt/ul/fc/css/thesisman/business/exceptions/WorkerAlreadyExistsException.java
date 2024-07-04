package pt.ul.fc.css.thesisman.business.exceptions;

public class WorkerAlreadyExistsException extends Exception {

  public WorkerAlreadyExistsException() {
  }

  public WorkerAlreadyExistsException(String message) {
    super(message);
  }

  public WorkerAlreadyExistsException(Throwable cause) {
    super(cause);
  }

  public WorkerAlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }
}
