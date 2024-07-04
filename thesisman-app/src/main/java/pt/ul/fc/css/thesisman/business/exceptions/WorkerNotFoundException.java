package pt.ul.fc.css.thesisman.business.exceptions;

public class WorkerNotFoundException extends Exception {

  public WorkerNotFoundException() {
  }

  public WorkerNotFoundException(String message) {
    super(message);
  }

  public WorkerNotFoundException(Throwable cause) {
    super(cause);
  }

  public WorkerNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }
}
