package pt.ul.fc.css.thesisman.business.services.exceptions;

public class AlreadyExistsException extends Exception {

  public AlreadyExistsException() {}

  public AlreadyExistsException(String message) {
    super(message);
  }

  public AlreadyExistsException(String message, Throwable cause) {
    super(message, cause);
  }

  public AlreadyExistsException(Throwable cause) {
    super(cause);
  }
}
